package com.example.seatingarrangement.dto;

import com.example.seatingarrangement.model.Team;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.hibernate.annotations.UuidGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;
import java.util.List;


@Data
public class TeamOutputDto {

   @JsonProperty("layoutId")
    private String layoutId;
    private List<Team.TeamInfo> teams;
    private Date createdDated;
    private Date lastModifiedDate;
    @Data
    public static class TeamInfo{
        private String teamName;
        private int totalCount;
    }
}

