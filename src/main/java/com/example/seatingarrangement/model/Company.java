package com.example.seatingarrangement.model;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document
@Data
public class Company {
    private String companyId;
    private String companyName;
    private List<DefaultLayout> companyLayout;
    @CreatedDate
    private Date createdDate;
    @LastModifiedDate
    private Date lastModifiedDate;
    @Data
    public static class DefaultLayout{
        private String layoutId;
        private int[][] companyLayout;
        private int totalSpace;
    }
}
