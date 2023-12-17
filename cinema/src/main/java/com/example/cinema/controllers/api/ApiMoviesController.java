package com.example.cinema.controllers.api;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.example.cinema.models.Movie;
import com.example.cinema.services.IMovieService;
import org.springframework.web.bind.annotation.GetMapping;

@CrossOrigin
@RestController
public class ApiMoviesController {
    private final IMovieService movieService;

    public ApiMoviesController(IMovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/api/movies")
    public List<Movie> getMovies() {
        return movieService.getAllMovies();
    }

}
