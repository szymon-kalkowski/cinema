package com.example.cinema.controllers;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.cinema.models.Hall;
import com.example.cinema.models.Order;
import com.example.cinema.models.Person;
import com.example.cinema.models.Seance;
import com.example.cinema.services.MovieService;
import com.example.cinema.services.OrderService;
import com.example.cinema.services.PersonService;
import com.example.cinema.services.SeanceService;

import org.springframework.ui.Model;

@Controller
public class SeanceController {
    private final MovieService movieService;
    private final SeanceService seanceService;
    private final OrderService orderService;
    private final PersonService personService;

    public SeanceController(MovieService movieService, SeanceService seanceService, OrderService orderService,
            PersonService personService) {
        this.movieService = movieService;
        this.seanceService = seanceService;
        this.orderService = orderService;
        this.personService = personService;
    }

    @GetMapping("/add-seance")
    public String addSeance(Model model) {
        model.addAttribute("movies", movieService.getAllMovies());
        return "add-seance";
    }

    @GetMapping("/repertoire")
    public String repertoire() {
        return "redirect:/repertoire/" + LocalDate.now().toString();
    }

    @GetMapping("/repertoire/{date}")
    public String repertoireByDate(@PathVariable("date") String date, Model model) {
        model.addAttribute("repertoire", seanceService.getRepertoire(LocalDate.parse(date)));
        model.addAttribute("date", date);
        return "repertoire";
    }

    @PostMapping("/repertoire")
    public String repertoireRedirect(@RequestParam("date") String date) {
        return "redirect:/repertoire/" + date;
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

    @GetMapping("/seance/{id}")
    public String getSeance(@PathVariable("id") String id, Model model) {
        Seance seance = seanceService.getSeanceById(id);
        model.addAttribute("seance", seance);
        model.addAttribute("seats", seance.getHall().getSeats());
        model.addAttribute("availableSeats", seance.getHall().getAvailableSeats());
        return "seance";
    }

    @PostMapping("/seance/{id}")
    public String buyTicket(@PathVariable("id") String id, @RequestParam("seats") List<String> seats,
            @RequestParam String name, @RequestParam String email, Model model) {
        Seance seance = seanceService.getSeanceById(id);

        Person person = personService.getPersonByEmail(email);
        if (person == null) {
            person = new Person(name, email);
            personService.createPerson(person);
        }

        for (String seat : seats) {
            String[] seatParts = seat.split("-");
            int row = Integer.parseInt(seatParts[1]);
            int col = Integer.parseInt(seatParts[3]);
            seance.getHall().reserveSeat(row, col, person);
        }

        seanceService.updateSeance(seance);

        Order order = new Order(seance, person, seats);

        orderService.createOrder(order);

        model.addAttribute("seance", seance);
        model.addAttribute("order", order);

        return "order-summary";
    }

}
