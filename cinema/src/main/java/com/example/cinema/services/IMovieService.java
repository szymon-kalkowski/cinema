package com.example.cinema.services;

import java.util.List;

import com.example.cinema.models.Movie;

public interface IMovieService {

    List<Movie> getAllMovies();

    Movie getMovieById(String id);

    Movie createMovie(Movie movie);

    Movie updateMovie(Movie movie);

    void deleteMovie(String id);

}