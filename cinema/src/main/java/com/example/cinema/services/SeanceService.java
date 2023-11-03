package com.example.cinema.services;

import java.util.List;

import org.springframework.stereotype.Service;

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
}
