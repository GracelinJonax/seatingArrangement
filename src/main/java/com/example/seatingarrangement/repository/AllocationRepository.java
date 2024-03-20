package com.example.seatingarrangement.repository;

import com.example.seatingarrangement.model.Allocation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AllocationRepository extends MongoRepository<Allocation,String> {
}
