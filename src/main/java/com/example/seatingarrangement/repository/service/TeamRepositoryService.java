package com.example.seatingarrangement.repository.service;

import com.example.seatingarrangement.dto.TeamObjectDto;
import com.example.seatingarrangement.model.Team;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface TeamRepositoryService {
    Team findTeamsByTeamInfo(List<TeamObjectDto.TeamDto> teamInfoList, int size);
    Optional<Team> findByTeamId(String teamId);
}
