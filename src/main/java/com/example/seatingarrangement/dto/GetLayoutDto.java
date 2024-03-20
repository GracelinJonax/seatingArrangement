package com.example.seatingarrangement.dto;

import lombok.Data;

@Data
public class GetLayoutDto {
    private int[][] defaultLayout;
    private int totalSpace;
}
