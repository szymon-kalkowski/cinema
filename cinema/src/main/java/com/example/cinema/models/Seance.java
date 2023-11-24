package com.example.cinema.models;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Document
@AllArgsConstructor
@NoArgsConstructor
public class Seance {
    @Id
    private String id;
    private Movie movie;
    private int room;
    private LocalDate date;
    private LocalTime time;
    private Hall hall;

    public Seance(Movie movie, int room, LocalDate date, LocalTime time, Hall hall) {
        this.movie = movie;
        this.room = room;
        this.date = date;
        this.time = time;
        this.hall = hall;
    }
}
