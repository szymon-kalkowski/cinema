package com.example.cinema.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.cinema.models.Seance;

public interface SeanceRepository extends MongoRepository<Seance, String> {
    List<Seance> findByDate(LocalDate date);
}
