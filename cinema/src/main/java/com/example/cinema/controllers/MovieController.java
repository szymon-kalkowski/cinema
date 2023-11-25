package com.example.cinema.controllers;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.cinema.models.Actor;
import com.example.cinema.models.Director;
import com.example.cinema.models.FileUpload;
import com.example.cinema.models.Genre;
import com.example.cinema.models.Movie;
import com.example.cinema.services.ActorService;
import com.example.cinema.services.DirectorService;
import com.example.cinema.services.MovieService;

import org.springframework.ui.Model;

@Controller
public class MovieController {
    private final FileUpload fileUpload;
    private final MovieService movieService;
    private final ActorService actorService;
    private final DirectorService directorService;

    public MovieController(FileUpload fileUpload, MovieService movieService, ActorService actorService,
            DirectorService directorService) {
        this.fileUpload = fileUpload;
        this.movieService = movieService;
        this.actorService = actorService;
        this.directorService = directorService;
    }

    @GetMapping("/add-movie")
    public String home(Model model) {
        model.addAttribute("genres", Genre.values());
        model.addAttribute("actors", actorService.getAllActors());
        model.addAttribute("directors", directorService.getAllDirectors());
        return "add-movie";
    }

    @PostMapping("/upload-movie")
    public String uploadMovie(@RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("genres") List<String> genreStrings,
            @RequestParam("duration") int duration,
            @RequestParam("year") int year,
            @RequestParam("directors") List<String> directorsIds,
            @RequestParam("actors") List<String> actorsIds,
            @RequestParam("image") MultipartFile multipartFile,
            Model model) throws IOException {
        String imageURL = fileUpload.uploadFile(multipartFile);
        List<Genre> genres = genreStrings.stream()
                .map(Genre::valueOf)
                .collect(Collectors.toList());

        List<Director> directors = directorsIds.stream()
                .map(directorService::getDirectorById)
                .collect(Collectors.toList());

        List<Actor> actors = actorsIds.stream()
                .map(actorService::getActorById)
                .collect(Collectors.toList());

        Movie movie = new Movie(
                title,
                description,
                genres,
                duration,
                year,
                directors,
                actors,
                imageURL);

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

    @PostMapping("/add-director")
    public String addDirector(@RequestParam("name") String name,
            @RequestParam("birthYear") int birthYear,
            Model model) {
        Director director = new Director(name, birthYear);
        directorService.createDirector(director);
        return "redirect:/";
    }

    @GetMapping("/movie/{id}")
    public String getMovie(@PathVariable("id") String id, Model model) {
        Movie movie = movieService.getMovieById(id);
        model.addAttribute("movie", movie);
        return "movie";
    }
}
