package com.example.seatingarrangement.service;

import com.example.seatingarrangement.dto.CompanyDto;
import com.example.seatingarrangement.dto.LayoutDto;
import com.example.seatingarrangement.dto.SeatingDto;
import com.example.seatingarrangement.dto.TeamObjectDto;
import com.example.seatingarrangement.model.Company;
import org.springframework.stereotype.Service;

@Service
public interface SeatingService {
    Company saveCompanyService(CompanyDto companyDto);

    SeatingDto allocateTeamsService(TeamObjectDto teamObjectDtoList);

    int[][] getLayoutService(String layoutId);

    LayoutDto updateLayoutService(LayoutDto layoutDto);
}
