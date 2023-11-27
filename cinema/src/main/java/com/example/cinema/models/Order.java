package com.example.cinema.models;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Document
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @Id
    private String id;
    private Seance seance;
    private Person person;
    private List<String> seats;

    public Order(Seance seance, Person person, List<String> seats) {
        this.seance = seance;
        this.person = person;
        this.seats = seats;
    }

    public double totalCost() {
        return 10 * seats.size();
    }
}
