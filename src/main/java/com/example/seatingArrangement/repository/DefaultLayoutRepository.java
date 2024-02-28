package com.example.seatingArrangement.repository;

import com.example.seatingArrangement.model.DefaultLayout;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DefaultLayoutRepository extends MongoRepository<DefaultLayout, String> {
}
