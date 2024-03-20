package com.example.seatingarrangement.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.UuidGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document
@Data
@NoArgsConstructor
public class Company {
    @Id
    @UuidGenerator
    private String companyId;
    private String companyName;
    private List<DefaultLayout> companyLayout;

    @CreatedDate
    private Date createdDated;
    @LastModifiedDate
    private Date lastModifiedDate;
    @Data
    @RequiredArgsConstructor
    public static class DefaultLayout{
        private String layoutId;
        private int[][] companyLayout;
        private int totalSpace;
    }
}
