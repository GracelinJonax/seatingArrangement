package com.example.seatingArrangement.Service;

import com.example.seatingArrangement.Dto.SeatingDto;
import com.example.seatingArrangement.Dto.TeamDto;
import com.example.seatingArrangement.model.DefaultLayout;
import org.springframework.stereotype.Service;

@Service
public interface SeatingService {
    DefaultLayout saveLayoutService(DefaultLayout defaultLayout);

    SeatingDto createLayoutService(TeamDto teamDtoList);

    int[][] getLayoutService();
    void find();
}
