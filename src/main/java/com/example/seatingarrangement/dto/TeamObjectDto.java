package com.example.seatingarrangement.dto;

import lombok.Data;

import java.util.List;

@Data
public class TeamObjectDto {

    private String layoutId;
    private String teamId;
    private List<TeamDto> teamDtoList;
    private int preference;
    @Data
    public static class TeamDto{
    private String teamName;
    private int totalCount;
}}
