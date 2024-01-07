package com.example.cinema.controllers.api;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.cinema.dto.Id;
import com.example.cinema.dto.Seat;
import com.example.cinema.dto.WriteOrder;
import com.example.cinema.dto.WriteSeance;
import com.example.cinema.models.Hall;
import com.example.cinema.models.Movie;
import com.example.cinema.models.Order;
import com.example.cinema.models.Person;
import com.example.cinema.models.Seance;
import com.example.cinema.services.IMovieService;
import com.example.cinema.services.IOrderService;
import com.example.cinema.services.IPersonService;
import com.example.cinema.services.ISeanceService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@CrossOrigin
@RestController
public class ApiSeanceController {
    private final ISeanceService seanceService;
    private final IMovieService movieService;
    private final IPersonService personService;
    private final IOrderService orderService;

    public ApiSeanceController(ISeanceService seanceService, IMovieService movieService, IPersonService personService,
            IOrderService orderService) {
        this.seanceService = seanceService;
        this.movieService = movieService;
        this.personService = personService;
        this.orderService = orderService;
    }

    @GetMapping("/api/repertoire/{date}")
    public Map<String, List<Seance>> getRepertoireByDate(@PathVariable("date") String date) {
        return seanceService.getApiRepertoire(LocalDate.parse(date));
    }

    @GetMapping("/api/repertoire/seances/{id}")
    public Seance getSeanceById(@PathVariable("id") String id) {
        return seanceService.getSeanceById(id);
    }

    @PostMapping("/api/repertoire/seances")
    public Seance postSeance(@RequestBody WriteSeance body) {
        Movie movie = movieService.getMovieById(body.movieId());
        LocalDate date = LocalDate.parse(body.date());
        LocalTime time = LocalTime.parse(body.time());
        Seance newSeance = new Seance(movie, body.room(), date, time, new Hall());
        Seance seance = seanceService.createSeance(newSeance);
        return seance;
    }

    @PutMapping("/api/repertoire/seances/{id}")
    public Seance putSeance(@PathVariable("id") String id, @RequestBody WriteSeance body) {
        Movie movie = movieService.getMovieById(body.movieId());
        LocalDate date = LocalDate.parse(body.date());
        LocalTime time = LocalTime.parse(body.time());
        Seance seanceToUpdate = seanceService.getSeanceById(id);
        seanceToUpdate.setMovie(movie);
        seanceToUpdate.setRoom(body.room());
        seanceToUpdate.setDate(date);
        seanceToUpdate.setTime(time);
        Seance seance = seanceService.updateSeance(seanceToUpdate);
        return seance;
    }

    @PostMapping("/api/repertoire/seances/orders/{id}")
    public Order addOrder(@PathVariable("id") String id, @RequestBody WriteOrder body) {
        Seance seance = seanceService.getSeanceById(id);

        Person person = personService.getPersonByEmail(body.email());
        if (person == null) {
            Person newPerson = new Person(body.name(), body.email());
            person = personService.createPerson(newPerson);
        }

        for (Seat seat : body.seats()) {
            seance.getHall().reserveSeat(seat.row(), seat.column(), person);
        }

        List<String> seatsAsString = body.seats().stream()
                .map(seat -> "row: " + seat.row() + ", column: " + seat.column()).toList();

        seanceService.updateSeance(seance);

        Order newOrder = new Order(seance, person, seatsAsString);

        return orderService.createOrder(newOrder);
    }

    @DeleteMapping("/api/repertoire/seances/{id}")
    public Id deleteSeance(@PathVariable("id") String id) {
        seanceService.deleteSeance(id);
        return new Id(id);
    }
}
