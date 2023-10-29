package com.example.cinema.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document
public class Director {
    @Id
    private String id;
    private String name;
    private int birthYear;

    public Director(String name, int birthYear) {
        this.name = name;
        this.birthYear = birthYear;
    }
}
