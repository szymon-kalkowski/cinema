package com.example.cinema.movie;

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
    private List<Genre> genres;
    private int duration;
    private int year;
    private List<Director> directors;
    private List<Actor> actors;
    private String imageURL;

    public Movie(String title, List<Genre> genres, int duration, int year, List<Director> directors,
            List<Actor> actors, String imageURL) {
        this.title = title;
        this.genres = genres;
        this.duration = duration;
        this.year = year;
        this.directors = directors;
        this.actors = actors;
        this.imageURL = imageURL;
    }
}
