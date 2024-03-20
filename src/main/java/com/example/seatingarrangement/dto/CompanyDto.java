package com.example.seatingarrangement.dto;

import lombok.Data;

import java.util.List;

@Data
public class CompanyDto {
    private String companyName;
    private List<int[][]> companyLayoutList;
}
