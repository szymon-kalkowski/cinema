package com.example.cinema.controllers.api;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.example.cinema.dto.WriteActor;
import com.example.cinema.dto.WriteDirector;
import com.example.cinema.dto.WriteMovie;
import com.example.cinema.models.Actor;
import com.example.cinema.models.Director;
import com.example.cinema.models.Genre;
import com.example.cinema.models.Movie;
import com.example.cinema.services.IActorService;
import com.example.cinema.services.IDirectorService;
import com.example.cinema.services.IFileUpload;
import com.example.cinema.services.IMovieService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@CrossOrigin
@RestController
public class ApiMoviesController {
    private final IMovieService movieService;
    private final IActorService actorService;
    private final IDirectorService directorService;

    public ApiMoviesController(IMovieService movieService, IFileUpload fileUpload, IActorService actorService,
            IDirectorService directorService) {
        this.movieService = movieService;
        this.actorService = actorService;
        this.directorService = directorService;
    }

    @GetMapping("/api/movies")
    public List<Movie> getMovies() {
        return movieService.getAllMovies();
    }

    @PostMapping("/api/movies")
    public Movie postMovie(
            @RequestBody WriteMovie body) {

        List<Genre> genresList = body.genres().stream().map(Genre::valueOf).collect(Collectors.toList());
        List<Actor> actorsList = new ArrayList<>();
        List<Director> directorsList = new ArrayList<>();
        for (WriteActor actor : body.actors()) {
            Actor a = actorService.getActorByName(actor.name());
            if (a == null) {
                Actor newActor = new Actor(actor.name(), actor.birthYear());
                actorService.createActor(newActor);
                actorsList.add(newActor);
            } else {
                actorsList.add(a);
            }
        }
        for (WriteDirector director : body.directors()) {
            Director d = directorService.getDirectorByName(director.name());
            if (d == null) {
                Director newDirector = new Director(director.name(), director.birthYear());
                directorService.createDirector(newDirector);
                directorsList.add(newDirector);
            } else {
                directorsList.add(d);
            }
        }
        Movie movie = new Movie(body.title(), body.description(), genresList, body.duration(), body.year(),
                directorsList,
                actorsList,
                body.imageURL());
        movieService.createMovie(movie);

        return movie;
    }

    @GetMapping("/api/movies/{id}")
    public Movie getMovieById(@PathVariable("id") String id) {
        return movieService.getMovieById(id);
    }

    @PutMapping("/api/movies/{id}")
    public Movie updateMovie(@PathVariable("id") String id, @RequestBody WriteMovie body) {
        Movie movie = movieService.getMovieById(id);
        movie.setTitle(body.title());
        movie.setDescription(body.description());
        movie.setDuration(body.duration());
        movie.setYear(body.year());
        movie.setImageURL(body.imageURL());
        List<Genre> genresList = body.genres().stream().map(Genre::valueOf).collect(Collectors.toList());
        movie.setGenres(genresList);
        List<Actor> actorsList = new ArrayList<>();
        List<Director> directorsList = new ArrayList<>();
        for (WriteActor actor : body.actors()) {
            Actor a = actorService.getActorByName(actor.name());
            if (a == null) {
                Actor newActor = new Actor(actor.name(), actor.birthYear());
                actorService.createActor(newActor);
                actorsList.add(newActor);
            } else {
                actorsList.add(a);
            }
        }
        for (WriteDirector director : body.directors()) {
            Director d = directorService.getDirectorByName(director.name());
            if (d == null) {
                Director newDirector = new Director(director.name(), director.birthYear());
                directorService.createDirector(newDirector);
                directorsList.add(newDirector);
            } else {
                directorsList.add(d);
            }
        }
        movie.setActors(actorsList);
        movie.setDirectors(directorsList);
        movieService.updateMovie(movie);
        return movie;
    }

    @GetMapping("/api/genres")
    public Genre[] getGenres() {
        return Genre.values();
    }

}
