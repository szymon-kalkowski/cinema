package com.example.cinema;

// import java.util.List;

// import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
// import org.springframework.context.annotation.Bean;

// import com.example.cinema.movie.Actor;
// import com.example.cinema.movie.Director;
// import com.example.cinema.movie.Genre;
// import com.example.cinema.movie.Movie;
// import com.example.cinema.movie.MovieRepository;

@SpringBootApplication
public class CinemaApplication {

	public static void main(String[] args) {
		SpringApplication.run(CinemaApplication.class, args);
	}

	// @Bean
	// CommandLineRunner runner(MovieRepository movieRepository) {
	// return args -> {
	// List<Director> directors = List.of(
	// new Director("George Lucas", 1944));
	// List<Actor> actors = List.of(
	// new Actor("Mark Hamill", 1951),
	// new Actor("Harrison Ford", 1942),
	// new Actor("Carrie Fisher", 1956));
	// List<Genre> genres = List.of(
	// Genre.ACTION,
	// Genre.ADVENTURE,
	// Genre.FANTASY);
	// Movie movie = new Movie(
	// "Star Wars: Episode IV - A New Hope",
	// genres,
	// 121,
	// 1977,
	// directors,
	// actors);

	// movieRepository.insert(movie);
	// };
	// }
}
