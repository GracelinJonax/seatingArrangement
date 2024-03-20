package com.example.seatingarrangement.repository.service;

import com.example.seatingarrangement.model.Allocation;
import com.example.seatingarrangement.model.Type;
import org.springframework.stereotype.Service;

@Service
public interface AllocationRepositoryService {
    Allocation findByDefaultLayoutIdAndAllocationType(String layoutId, Type allocationType);
}
