package com.example.cinema.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.cinema.models.Actor;

public interface ActorRepository extends MongoRepository<Actor, String> {

}
