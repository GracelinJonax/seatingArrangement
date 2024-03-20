package com.example.seatingarrangement.repository.service.impl;

import com.example.seatingarrangement.model.Allocation;
import com.example.seatingarrangement.model.Type;
import com.example.seatingarrangement.repository.AllocationRepository;
import com.example.seatingarrangement.repository.service.AllocationRepositoryService;
import org.springframework.stereotype.Service;

@Service
public class AllocationRepositoryServiceImpl implements AllocationRepositoryService {
    private final AllocationRepository allocationRepository;

    public AllocationRepositoryServiceImpl(AllocationRepository allocationRepository) {
        this.allocationRepository = allocationRepository;
    }

    @Override
    public Allocation findByDefaultLayoutIdAndAllocationType(String layoutId, Type allocationType) {
        return allocationRepository.findByDefaultLayoutIdAndAllocationType(layoutId,allocationType);
    }
}
