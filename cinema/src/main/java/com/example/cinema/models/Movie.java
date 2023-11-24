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
public class Movie {
    @Id
    private String id;
    private String title;
    private String description;
    private List<Genre> genres;
    private int duration;
    private int year;
    private List<Director> directors;
    private List<Actor> actors;
    private String imageURL;

    public Movie(String title, String description, List<Genre> genres, int duration, int year, List<Director> directors,
            List<Actor> actors, String imageURL) {
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
