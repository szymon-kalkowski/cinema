package com.example.cinema.dto;

public record WriteSeance(
        String movieId,
        Integer room,
        String date,
        String time) {

}
