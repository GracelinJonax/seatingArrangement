package com.example.seatingarrangement.repository.service.impl;

import com.example.seatingarrangement.dto.TeamObjectDto;
import com.example.seatingarrangement.model.Team;
import com.example.seatingarrangement.repository.TeamRepository;
import com.example.seatingarrangement.repository.service.TeamRepositoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeamRepositoryServiceImpl implements TeamRepositoryService {

    private final TeamRepository teamRepository;

    public TeamRepositoryServiceImpl(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @Override
    public Team findTeamsByTeamInfo(List<TeamObjectDto.TeamDto> teamInfoList, int size) {
        return teamRepository.findTeamsByTeamInfo(teamInfoList,size);
    }

    @Override
    public Optional<Team> findByTeamId(String teamId) {
        return teamRepository.findByTeamId(teamId);
    }
}
