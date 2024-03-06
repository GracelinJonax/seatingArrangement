package com.example.seatingArrangement.Controller;

import com.example.seatingArrangement.Api.seatingArrangementApi;
import com.example.seatingArrangement.Dto.SeatingDto;
import com.example.seatingArrangement.Dto.TeamDto;
import com.example.seatingArrangement.Service.SeatingService;
import com.example.seatingArrangement.model.DefaultLayout;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class apiController implements seatingArrangementApi {
    private final SeatingService seatingService;
    public apiController(SeatingService seatingService){
        this.seatingService=seatingService;
    }
    @Override
    public ResponseEntity<DefaultLayout> saveLayout(DefaultLayout layout) {
        return new ResponseEntity<>(seatingService.saveLayoutService(layout), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<SeatingDto> createLayout(TeamDto teamDtoList) {
       return new ResponseEntity<>(seatingService.createLayoutService(teamDtoList),HttpStatus.OK);
    }

    @Override
    public ResponseEntity<int[][]> getLayout() {
        return new ResponseEntity<>(seatingService.getLayoutService(),HttpStatus.OK);
    }
}
