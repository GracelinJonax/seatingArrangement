package com.example.seatingArrangement.service.impl;

import com.example.seatingArrangement.dto.SeatingDto;
import com.example.seatingArrangement.dto.TeamDto;
import com.example.seatingArrangement.model.DefaultLayout;
import com.example.seatingArrangement.model.Team;
import com.example.seatingArrangement.repository.DefaultLayoutRepository;
import com.example.seatingArrangement.repository.TeamRepository;
import com.example.seatingArrangement.service.SeatingService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class SeatingServiceImpl implements SeatingService {
    TreeSet<Integer> clusters = new TreeSet<>();
    String[][] arrangement;
    int[][] tempLayout;
    int[][] totalSeating;
    boolean[][] track;
    int lastx = -1;
    int lasty = -1;
    int count = 0;
    int minSteps = 100;
    int[][] trace;
    private final DefaultLayoutRepository defaultLayoutRepository;
    private final ModelMapper modelMapper;
    private final TeamRepository teamRepository;

    public SeatingServiceImpl(DefaultLayoutRepository defaultLayoutRepository, ModelMapper modelMapper,
                              TeamRepository teamRepository) {
        this.defaultLayoutRepository = defaultLayoutRepository;
        this.modelMapper = modelMapper;
        this.teamRepository = teamRepository;
    }

    @Override
    public int[][] getLayoutService() {
        return defaultLayoutRepository.findAll().get(0).getDefaultLayout();
    }


    @Override
    public DefaultLayout saveLayoutService(DefaultLayout defaultLayout) {
        int cnt = 0;
        String[][] layout = new String[defaultLayout.getDefaultLayout().length][defaultLayout.getDefaultLayout()[0].length];
        for (int i = 0; i < defaultLayout.getDefaultLayout().length; i++) {
            for (int j = 0; j < defaultLayout.getDefaultLayout()[0].length; j++) {
                if (defaultLayout.getDefaultLayout()[i][j] == 1)
                    cnt++;
            }
        }
        Arrays.stream(layout).forEach(a -> Arrays.fill(a, ""));
        defaultLayout.setTotalSpace(cnt);
        defaultLayout.setLayout(layout);
        return defaultLayoutRepository.save(defaultLayout);
    }

    @Override
    public SeatingDto createLayoutService(TeamDto teamDto) {
        int wantedSpace = 0;
        for (TeamDto.Team teamList : teamDto.getTeamList())
            wantedSpace += teamList.getTotalMembers();
        int totalSpace = defaultLayoutRepository.findAll().get(0).getTotalSpace();
        if (wantedSpace > totalSpace) {
            return new SeatingDto();
        }
        teamRepository.deleteAll();
        List<Team> teamList = new ArrayList<>();
        int total = 0;
        for (TeamDto.Team teams : teamDto.getTeamList()) {
            Team team = new Team();
            modelMapper.map(teams, team);
            String teamCode = createTeamCode(++total);
            team.setTeamCode(teamCode);
            teamRepository.save(team);
            teamList.add(team);
        }
        if (teamDto.getPreference() == 1)
            teamList.sort(Comparator.comparing(Team::getTotalMembers).reversed());
        else if (teamDto.getPreference() == 2)
            teamList.sort(Comparator.comparing(Team::getTotalMembers));
        else
            teamList = new HashSet<>(teamList).stream().toList();
        DefaultLayout defaultLayoutClass = defaultLayoutRepository.findAll().get(0);
        int[][] defaultLayout = defaultLayoutClass.getDefaultLayout();
        arrangement = new String[defaultLayout.length][defaultLayout[0].length];
        findArrangement(teamList);
        SeatingDto seatingDto = new SeatingDto();
        List<SeatingDto.Team> teams = teamList.stream().map(a -> modelMapper.map(a, SeatingDto.Team.class))
                .collect(Collectors.toList());
        seatingDto.setTeams(teams);
        seatingDto.setArrangement(arrangement);
        defaultLayoutClass.setLayout(arrangement);
        defaultLayoutRepository.save(defaultLayoutClass);
        defaultLayoutRepository.save(defaultLayoutClass);
        return seatingDto;
    }

    private void findArrangement(List<Team> teamList) {
        tempLayout = defaultLayoutRepository.findAll().get(0).getDefaultLayout();
        track = new boolean[tempLayout.length][tempLayout[0].length];
        totalSeating = findTotalSeating(tempLayout);

        for (Team team : teamList) {
            lastx = -1;
            lasty = -1;
            int totalMembers = team.getTotalMembers();
            int total = totalMembers;
            int max = (int) clusters.toArray()[clusters.size() - 1];
            count = 0;
            int c = count;
            while (total > 0) {
                if (total > max) {
                    findStartSeating(max, team.getTeamCode());
                    total = totalMembers - count;
                } else {
                    findStartSeating(total, team.getTeamCode());
                    total = totalMembers - count;
                }
                if (count == c) {
                    break;
                }
                c = count;
            }
        }
    }

    private void findStartSeating(int totalMembers, String teamCode) {
        int wantedx = 0;
        int wantedy = 0;
        int minDis = 100;
        int total = totalMembers;
        if (!clusters.contains(totalMembers)) {
            for (Integer check : clusters) {
                if (check < totalMembers) {
                    total = check;
                } else
                    break;
            }
        }
        for (int i = 1; i <= tempLayout.length; i++) {
            int c = 0;
            for (int j = 1; j <= tempLayout[0].length; j++) {
                if (totalSeating[i][j] <= total && totalSeating[i][j] != 0) {
                    if (lasty == -1 && lastx == -1 && totalSeating[i][j] == total) {
                        c = 1;
                        wantedx = i;
                        wantedy = j;
                        break;
                    } else if (lasty != -1 && lastx != -1) {
                        int calculatedDis = findDistance(i, j, teamCode);
                        if (calculatedDis < minDis || (calculatedDis == minDis && ((totalSeating[i][j] == total) || totalSeating[wantedx][wantedy] < totalSeating[i][j]))) {
                            wantedx = i;
                            wantedy = j;
                            minDis = calculatedDis;
                        }
                    }
                }
            }
            if (lasty == -1 && lastx == -1 && c == 1)
                break;
        }

        lastx = wantedx;
        lasty = wantedy;
        if (totalMembers > totalSeating[wantedx][wantedy])
            totalMembers = totalSeating[wantedx][wantedy];
        markSeating(wantedx, wantedy, teamCode, totalMembers + count);
        totalSeating = findTotalSeating(tempLayout);
    }

    int findDistance(int x, int y, String teamCode) {
        minSteps = 100;
        trace = new int[arrangement.length][arrangement[0].length];
        findSteps(lastx, lasty, x, y, 0, teamCode);
        return minSteps;
    }

    boolean findSteps(int x, int y, int resultx, int resulty, int steps, String teamCode) {
        if (x == resultx && y == resulty) {
            steps += 1;
            if (steps < minSteps)
                minSteps = steps;
            return false;
        }
        if ((lastx > resultx && x > lastx) || (lastx < resultx && x < lastx) || (lasty > resulty && y > lasty) || (lasty < resulty && y < lasty))
            return false;

        if (steps > minSteps)
            return false;
        if (x > 0 && y > 0 && x <= arrangement.length && y <= arrangement[0].length && trace[x - 1][y - 1] == 0) {
            trace[x - 1][y - 1] = 1;
            if ((track[x - 1][y - 1] && arrangement[x - 1][y - 1].contains(
                    teamCode)) || (!track[x - 1][y - 1] && totalSeating[x][y] == 0))
                steps -= 1;
            if (tempLayout[x - 1][y - 1] == -1)
                steps += 2;
            if (findSteps(x, y + 1, resultx, resulty, steps + 1, teamCode))
                return true;
            if (findSteps(x - 1, y, resultx, resulty, steps + 1, teamCode))
                return true;
            if (findSteps(x + 1, y, resultx, resulty, steps + 1, teamCode))
                return true;
            if (findSteps(x, y - 1, resultx, resulty, steps + 1, teamCode))
                return true;
            return false;
        }
        return false;
    }

    private boolean markSeating(int x, int y, String teamCode, int totalMembers) {
        if (count == totalMembers) {
            return true;
        }
        if (x > 0 && y > 0 && x <= arrangement.length && y <= arrangement[0].length && totalSeating[x][y] != 0 && !track[x - 1][y - 1] && arrangement[x - 1][y - 1] == null) {
            tempLayout[x - 1][y - 1] = 0;
            track[x - 1][y - 1] = true;
            arrangement[x - 1][y - 1] = teamCode + (++count);
            if (markSeating(x, y - 1, teamCode, totalMembers))
                return true;
            if (markSeating(x - 1, y, teamCode, totalMembers))
                return true;
            if (markSeating(x, y + 1, teamCode, totalMembers))
                return true;
            if (markSeating(x + 1, y, teamCode, totalMembers))
                return true;
            track[x - 1][y - 1] = false;
            return false;
        }
        return false;
    }

    private int[][] findTotalSeating(int[][] tempLayout) {
        clusters.clear();
        int[][] totalSeating = new int[tempLayout.length + 1][tempLayout[0].length + 1];
        for (int i = 1; i <= tempLayout.length; i++) {
            for (int j = 1; j <= tempLayout[0].length; j++) {
                if (tempLayout[i - 1][j - 1] == 0 || tempLayout[i - 1][j - 1] == -1)
                    totalSeating[i][j] = 0;
                else if (totalSeating[i][j - 1] == 0 || totalSeating[i - 1][j] == 0)
                    totalSeating[i][j] = totalSeating[i][j - 1] + totalSeating[i - 1][j] + tempLayout[i - 1][j - 1];
                else
                    totalSeating[i][j] = totalSeating[i][j - 1] + totalSeating[i - 1][j] - totalSeating[i - 1][j - 1] + tempLayout[i - 1][j - 1];
                clusters.add(totalSeating[i][j]);
            }
        }
        return totalSeating;
    }

    private String createTeamCode(int total) {
        String[] alph = {"", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R",
                "S", "T", "U", "V", "W", "X", "Y", "Z"};
        String teamCode = "";
        if (total % 26 == 0)
            teamCode += alph[(total / 26) - 1] + "Z";
        else
            teamCode += alph[total / 26] + alph[total % 26];
        return teamCode;
    }
}
