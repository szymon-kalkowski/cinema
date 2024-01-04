package com.example.cinema.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import com.example.cinema.models.Movie;
import com.example.cinema.models.Seance;

public interface ISeanceService {

    List<Seance> getAllSeances();

    Seance getSeanceById(String id);

    Seance createSeance(Seance seance);

    Seance updateSeance(Seance seance);

    void deleteSeance(String id);

    List<Seance> getSeancesByDate(LocalDate date);

    Map<Movie, List<Seance>> getRepertoire(LocalDate date);

    Map<String, List<Seance>> getApiRepertoire(LocalDate date);

}