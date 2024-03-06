package com.example.seatingArrangement.Api;

import com.example.seatingArrangement.Dto.SeatingDto;
import com.example.seatingArrangement.Dto.TeamDto;
import com.example.seatingArrangement.model.DefaultLayout;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public interface seatingArrangementApi {
    @PostMapping("/defaultLayout")
    ResponseEntity<DefaultLayout> saveLayout(@RequestBody DefaultLayout layout);
    @PostMapping("/team")
    ResponseEntity<SeatingDto> createLayout(@RequestBody TeamDto teamDtoList);
    @GetMapping("getDefaultLayout")
    ResponseEntity<int [][]> getLayout();
}
