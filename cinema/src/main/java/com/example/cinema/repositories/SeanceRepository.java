package com.example.cinema.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.cinema.models.Seance;

public interface SeanceRepository extends MongoRepository<Seance, String> {

}
