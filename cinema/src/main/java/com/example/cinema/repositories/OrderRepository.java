package com.example.cinema.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.cinema.models.Order;

public interface OrderRepository extends MongoRepository<Order, String> {
    List<Order> findBySeanceDate(LocalDate date);
}
