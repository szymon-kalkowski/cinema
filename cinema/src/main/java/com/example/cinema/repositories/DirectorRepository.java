package com.example.cinema.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.cinema.models.Director;

public interface DirectorRepository extends MongoRepository<Director, String> {

}
