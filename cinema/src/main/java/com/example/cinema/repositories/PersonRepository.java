package com.example.cinema.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.cinema.models.Person;

public interface PersonRepository extends MongoRepository<Person, String> {
    Person findByEmail(String email);
}
