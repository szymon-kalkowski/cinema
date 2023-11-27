package com.example.cinema.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.cinema.models.Order;

public interface OrderRepository extends MongoRepository<Order, String> {

}
