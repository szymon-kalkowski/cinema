package com.example.cinema.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.cinema.models.Movie;

public interface MovieRepository extends MongoRepository<Movie, String> {

}
