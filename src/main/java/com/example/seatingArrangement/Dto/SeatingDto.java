package com.example.seatingArrangement.Dto;

import lombok.Data;

import java.util.List;
@Data
public class SeatingDto {
    private String[][] arrangement;
    private List<Team> teams;
    @Data
    public static class Team{
        private String teamName;
        private String teamCode;
    }
}
