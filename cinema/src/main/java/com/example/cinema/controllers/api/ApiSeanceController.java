package com.example.cinema.controllers.api;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.cinema.models.Seance;
import com.example.cinema.services.ISeanceService;

@CrossOrigin
@RestController
public class ApiSeanceController {
    private final ISeanceService seanceService;

    public ApiSeanceController(ISeanceService seanceService) {
        this.seanceService = seanceService;
    }

    @GetMapping("/api/repertoire/{date}")
    public Map<String, List<Seance>> getRepertoireByDate(@PathVariable("date") String date) {
        return seanceService.getApiRepertoire(LocalDate.parse(date));
    }

    @GetMapping("/api/repertoire/seances/{id}")
    public Seance getSeanceById(@PathVariable("id") String id) {
        return seanceService.getSeanceById(id);
    }
}
