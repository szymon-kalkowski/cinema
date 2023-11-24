package com.example.cinema.controllers;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.cinema.models.Hall;
import com.example.cinema.models.Seance;
import com.example.cinema.services.MovieService;
import com.example.cinema.services.SeanceService;

import org.springframework.ui.Model;

@Controller
public class SeanceController {
    private final MovieService movieService;
    private final SeanceService seanceService;

    public SeanceController(MovieService movieService, SeanceService seanceService) {
        this.movieService = movieService;
        this.seanceService = seanceService;
    }

    @GetMapping("/add-seance")
    public String addSeance(Model model) {
        model.addAttribute("movies", movieService.getAllMovies());
        return "add-seance";
    }

    @PostMapping("/upload-seance")
    public String addSeance(@RequestParam("movie") String movieId,
            @RequestParam("room") int room,
            @RequestParam("date") String date,
            @RequestParam("time") String time,
            Model model) {

        Seance seance = new Seance(
                movieService.getMovieById(movieId),
                room,
                LocalDate.parse(date),
                LocalTime.parse(time),
                new Hall());

        seanceService.createSeance(seance);

        return "redirect:/";
    }
}
