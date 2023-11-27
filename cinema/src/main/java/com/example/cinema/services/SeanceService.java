package com.example.cinema.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.cinema.models.Movie;
import com.example.cinema.models.Seance;
import com.example.cinema.repositories.SeanceRepository;

@Service
public class SeanceService {
    private final SeanceRepository seanceRepository;

    public SeanceService(SeanceRepository seanceRepository) {
        this.seanceRepository = seanceRepository;
    }

    public List<Seance> getAllSeances() {
        return seanceRepository.findAll();
    }

    public Seance getSeanceById(String id) {
        return seanceRepository.findById(id).orElse(null);
    }

    public Seance createSeance(Seance seance) {
        return seanceRepository.insert(seance);
    }

    public Seance updateSeance(Seance seance) {
        return seanceRepository.save(seance);
    }

    public void deleteSeance(String id) {
        seanceRepository.deleteById(id);
    }

    public List<Seance> getSeancesByDate(LocalDate date) {
        return seanceRepository.findByDate(date);
    }

    public Map<Movie, List<Seance>> getRepertoire(LocalDate date) {
        List<Seance> seances = getSeancesByDate(date);

        Map<Movie, List<Seance>> groupedSeances = seances.stream()
                .collect(Collectors.groupingBy(Seance::getMovie, Collectors.toList()));

        groupedSeances.forEach((movie, seancesList) -> {
            seancesList.sort((s1, s2) -> s1.getTime().compareTo(s2.getTime()));
        });

        return groupedSeances;
    }
}
