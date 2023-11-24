package com.example.cinema.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Document
@AllArgsConstructor
@NoArgsConstructor
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
