package com.example.seatingarrangement.model;

import jakarta.persistence.Enumerated;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Allocation {
    private String allocationId;
    private String defaultLayoutId;
    private String teamId;
    @Enumerated
    private Type allocationType;
    private String[][] allocatedLayout;
}
