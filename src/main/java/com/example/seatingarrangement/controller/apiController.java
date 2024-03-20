package com.example.seatingarrangement.controller;

import com.example.seatingarrangement.api.seatingArrangementApi;
import com.example.seatingarrangement.dto.CompanyDto;
import com.example.seatingarrangement.dto.LayoutDto;
import com.example.seatingarrangement.dto.SeatingDto;
import com.example.seatingarrangement.dto.TeamObjectDto;
import com.example.seatingarrangement.service.SeatingService;
import com.example.seatingarrangement.model.Company;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin
public class apiController implements seatingArrangementApi {
    private final SeatingService seatingService;
    public apiController(SeatingService seatingService){
        this.seatingService=seatingService;
    }
    @Override
    public ResponseEntity<Company> saveCompany(CompanyDto companyDto) {
        return new ResponseEntity<>(seatingService.saveCompanyService(companyDto), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<SeatingDto> allocateTeams(TeamObjectDto teamObjectDtoList) {
       return new ResponseEntity<>(seatingService.allocateTeamsService(teamObjectDtoList),HttpStatus.OK);
    }

    @Override
    public ResponseEntity<LayoutDto> updateLayout(LayoutDto layoutDto) {
        return new ResponseEntity<>(seatingService.updateLayoutService(layoutDto),HttpStatus.OK);
    }

    @Override
    public ResponseEntity<int[][]> getLayout(String layoutId) {
        return new ResponseEntity<>(seatingService.getLayoutService(layoutId),HttpStatus.OK);
    }

}
