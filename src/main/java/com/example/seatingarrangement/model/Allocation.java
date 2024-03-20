package com.example.seatingarrangement.model;

import jakarta.persistence.Enumerated;
import lombok.Data;
import org.hibernate.annotations.UuidGenerator;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document
public class Allocation {
    @Id
    @UuidGenerator
    private String allocationId;
    private String defaultLayoutId;
    private String teamId;
    @Enumerated
    private Type allocationType;
    private String[][] allocatedLayout;
}
