package com.example.cinema.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.cinema.models.Director;
import com.example.cinema.repositories.DirectorRepository;

@Service
public class DirectorService {
    private final DirectorRepository directorRepository;

    public DirectorService(DirectorRepository directorRepository) {
        this.directorRepository = directorRepository;
    }

    public List<Director> getAllDirectors() {
        return directorRepository.findAll();
    }

    public Director getDirectorById(String id) {
        return directorRepository.findById(id).orElse(null);
    }

    public Director createDirector(Director director) {
        return directorRepository.insert(director);
    }

    public Director updateDirector(Director director) {
        return directorRepository.save(director);
    }

    public void deleteDirector(String id) {
        directorRepository.deleteById(id);
    }
}
