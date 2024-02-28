package com.example.seatingArrangement.dto;

import lombok.Data;

import java.util.List;

@Data
public class TeamDto {

    private List<Team> teamList;
    private int preference;

    @Data
    public static class Team {
        private String teamName;
        private int totalMembers;
    }
}
