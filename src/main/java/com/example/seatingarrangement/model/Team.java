package com.example.seatingarrangement.model;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document
@Data
public class Team {
    private String teamId;
    private String layoutId;
    private List<TeamInfo> teams;
    @CreatedDate
    private Date createdDated;
    @LastModifiedDate
    private Date lastModifiedDate;
    @Data
    public static class TeamInfo{
        private String teamName;
        private int totalCount;
        private String teamCode;
    }
}