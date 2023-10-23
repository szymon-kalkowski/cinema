package com.example.cinema.models;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document
public class Movie {
    @Id
    private String id;
    private String title;
    private String description;
    private List<Genre> genres;
    private int duration;
    private int year;
    private List<String> directors;
    private List<String> actors;
    private String imageURL;

    public Movie(String title, String description, List<Genre> genres, int duration, int year, List<String> directors,
            List<String> actors, String imageURL) {
        this.title = title;
        this.description = description;
        this.genres = genres;
        this.duration = duration;
        this.year = year;
        this.directors = directors;
        this.actors = actors;
        this.imageURL = imageURL;
    }
}
