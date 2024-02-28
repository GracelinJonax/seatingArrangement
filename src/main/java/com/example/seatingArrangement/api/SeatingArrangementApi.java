package com.example.seatingArrangement.api;

import com.example.seatingArrangement.dto.SeatingDto;
import com.example.seatingArrangement.dto.TeamDto;
import com.example.seatingArrangement.model.DefaultLayout;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public interface SeatingArrangementApi {
    @PostMapping("/defaultLayout")
    ResponseEntity<DefaultLayout> saveLayout(@RequestBody DefaultLayout layout);

    @PostMapping("/team")
    ResponseEntity<SeatingDto> createLayout(@RequestBody TeamDto teamDtoList);

    @GetMapping("getDefaultLayout")
    ResponseEntity<int[][]> getLayout();
}
