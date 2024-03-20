package com.example.seatingarrangement.api;

import com.example.seatingarrangement.dto.CompanyDto;
import com.example.seatingarrangement.dto.LayoutDto;
import com.example.seatingarrangement.dto.SeatingDto;
import com.example.seatingarrangement.dto.TeamObjectDto;
import com.example.seatingarrangement.model.Company;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
public interface seatingArrangementApi {
    @PostMapping("/saveCompany")
    ResponseEntity<Company> saveCompany(@RequestBody CompanyDto companyDto);
    @PostMapping("/updateLayout")
    ResponseEntity<LayoutDto> updateLayout(@RequestBody LayoutDto layoutDto);
    @PostMapping("/team")
    ResponseEntity<SeatingDto> allocateTeams(@RequestBody TeamObjectDto teamObjectDtoList);
    @GetMapping("getLayout")
    ResponseEntity<int [][]> getLayout(@RequestBody String layoutId);
}
