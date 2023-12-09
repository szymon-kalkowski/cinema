package com.example.cinema.controllers;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.cinema.services.IMovieService;

@Controller
public class HomeController {
    private final IMovieService movieService;

    public HomeController(IMovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/")
    public String home(Model model, @AuthenticationPrincipal OidcUser principal) {
        model.addAttribute("movies", movieService.getAllMovies());
        return "home";
    }
}
