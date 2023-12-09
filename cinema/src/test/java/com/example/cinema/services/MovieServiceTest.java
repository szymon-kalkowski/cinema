package com.example.cinema.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.cinema.models.Movie;
import com.example.cinema.repositories.MovieRepository;

@ExtendWith(MockitoExtension.class)
public class MovieServiceTest {
    private IMovieService movieService;
    @Mock
    private MovieRepository movieRepository;

    @BeforeEach
    void setUp() {
        movieService = new MovieService(movieRepository);
    }

    @Test
    void testGetAllMovies() {
        // given
        Mockito.when(movieRepository.findAll()).thenReturn(List.of(new Movie(), new Movie()));
        // when
        List<Movie> movies = movieService.getAllMovies();
        // then
        assertEquals(movies.size(), 2);
    }

    @Test
    void testGetMovieById() {
        // given
        Movie movie = new Movie();
        movie.setId("1");
        Mockito.when(movieRepository.findById("1")).thenReturn(Optional.of(movie));
        // when
        Movie foundMovie = movieService.getMovieById("1");
        // then
        assertEquals(foundMovie.getId(), "1");
    }

    @Test
    void testCreateMovie() {
        // given
        Movie movie = new Movie();
        movie.setId("1");
        Mockito.when(movieRepository.insert(movie)).thenReturn(movie);
        // when
        Movie createdMovie = movieService.createMovie(movie);
        // then
        assertEquals(createdMovie.getId(), "1");
    }

    @Test
    void testUpdateMovie() {
        // given
        Movie movie = new Movie();
        movie.setId("1");
        Mockito.when(movieRepository.save(movie)).thenReturn(movie);
        // when
        Movie updatedMovie = movieService.updateMovie(movie);
        // then
        assertEquals(updatedMovie.getId(), "1");
    }

    @Test
    void testDeleteMovie() {
        // given
        Movie movie = new Movie();
        movie.setId("1");
        // when
        movieService.deleteMovie(movie.getId());
        // then
        Mockito.verify(movieRepository, Mockito.times(1)).deleteById(movie.getId());
    }
}
