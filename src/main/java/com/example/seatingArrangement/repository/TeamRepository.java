package com.example.seatingArrangement.repository;

import com.example.seatingArrangement.model.Team;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends MongoRepository<Team,String> {
//    int CountOfTeam();
}
