package com.example.cinema.movie;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.cinema.FileUpload;

import org.springframework.ui.Model;

@Controller
public class MovieController {
    private final MovieService movieService;
    private final FileUpload fileUpload;
    private final MovieRepository movieRepository;

    public MovieController(MovieService movieService, FileUpload fileUpload, MovieRepository movieRepository) {
        this.movieService = movieService;
        this.fileUpload = fileUpload;
        this.movieRepository = movieRepository;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("movies", movieService.getAllMovies());
        return "home";
    }

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("image") MultipartFile multipartFile,
            Model model) throws IOException {
        String imageURL = fileUpload.uploadFile(multipartFile);

        List<Director> directors = List.of(
                new Director("George Lucas", 1944));
        List<Actor> actors = List.of(
                new Actor("Mark Hamill", 1951),
                new Actor("Harrison Ford", 1942),
                new Actor("Carrie Fisher", 1956));
        List<Genre> genres = List.of(
                Genre.ACTION,
                Genre.ADVENTURE,
                Genre.FANTASY);
        Movie movie = new Movie(
                "Star Wars: Episode IV - A New Hope",
                genres,
                121,
                1977,
                directors,
                actors, imageURL);

        movieRepository.insert(movie);
        return "redirect:/";
    }
}
