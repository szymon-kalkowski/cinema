package com.example.cinema.dto;

import java.util.List;

public record WriteMovie(
        String title,
        int year,
        int duration,
        List<String> genres,
        List<WriteDirector> directors,
        List<WriteActor> actors,
        String description,
        String imageURL) {

}
