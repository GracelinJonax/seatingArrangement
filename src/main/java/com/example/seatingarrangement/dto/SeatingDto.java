package com.example.seatingarrangement.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;
@Data
public class SeatingDto {
    @JsonProperty("allocation")
    private String[][] arrangement;
    @JsonProperty("teamReferenceList")
    private List<Team> teams;
    @Data
    public static class Team{
        @JsonProperty("name")
        private String teamName;
        @JsonProperty("count")
        private int totalCount;
        @JsonProperty("key")
        private String teamCode;
    }
}
