package com.example.seatingArrangement.model;

import lombok.Data;
import org.hibernate.annotations.UuidGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
@Data
public class Team {
    @Id
    @UuidGenerator
    private String id;
    private String teamName;
    private int totalMembers;
    private String teamCode;
    @CreatedDate
    private Date createdDated;
    @LastModifiedDate
    private Date lastModifiedDate;
}
