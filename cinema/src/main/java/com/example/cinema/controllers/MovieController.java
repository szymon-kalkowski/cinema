package com.example.cinema.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.cinema.models.Actor;
import com.example.cinema.models.FileUpload;
import com.example.cinema.models.Genre;
import com.example.cinema.models.Movie;
import com.example.cinema.services.ActorService;
import com.example.cinema.services.MovieService;

import org.springframework.ui.Model;

@Controller
public class MovieController {
        private final FileUpload fileUpload;
        private final MovieService movieService;
        private final ActorService actorService;

        @Autowired
        public MovieController(FileUpload fileUpload, MovieService movieService, ActorService actorService) {
                this.fileUpload = fileUpload;
                this.movieService = movieService;
                this.actorService = actorService;
        }

        @GetMapping("/add-movie")
        public String home(Model model) {
                model.addAttribute("actors", actorService.getAllActors());
                return "add-movie";
        }

        @PostMapping("/upload-movie")
        public String uploadFile(@RequestParam("image") MultipartFile multipartFile,
                        Model model) throws IOException {
                String imageURL = fileUpload.uploadFile(multipartFile);

                // List<Director> directors = List.of(
                // new Director("George Lucas", 1944));
                // List<Actor> actors = List.of(
                // new Actor("Mark Hamill", 1951),
                // new Actor("Harrison Ford", 1942),
                // new Actor("Carrie Fisher", 1956));
                List<String> directors = List.of("George Lucas");
                List<String> actors = List.of("Mark Hamill", "Harrison Ford", "Carrie Fisher");
                List<Genre> genres = List.of(
                                Genre.ACTION,
                                Genre.ADVENTURE,
                                Genre.FANTASY);
                Movie movie = new Movie(
                                "Star Wars: Episode IV - A New Hope",
                                "Luke Skywalker joins forces with a Jedi Knight, a cocky pilot, a Wookiee and two droids to save the galaxy from the Empire's world-destroying battle station, while also attempting to rescue Princess Leia from the mysterious Darth Vader.",
                                genres,
                                121,
                                1977,
                                directors,
                                actors, imageURL);

                movieService.createMovie(movie);
                return "redirect:/";
        }

        @PostMapping("/add-actor")
        public String addActor(@RequestParam("name") String name,
                        @RequestParam("birthYear") int birthYear,
                        Model model) {
                Actor actor = new Actor(name, birthYear);
                actorService.createActor(actor);
                return "redirect:/";
        }
}
