package com.example.seatingArrangement.service;

import com.example.seatingArrangement.dto.SeatingDto;
import com.example.seatingArrangement.dto.TeamDto;
import com.example.seatingArrangement.model.DefaultLayout;
import org.springframework.stereotype.Service;

@Service
public interface SeatingService {
    DefaultLayout saveLayoutService(DefaultLayout defaultLayout);

    SeatingDto createLayoutService(TeamDto teamDtoList);

    int[][] getLayoutService();
}
