package com.example.cinema.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.cinema.models.Director;
import com.example.cinema.repositories.DirectorRepository;

@Service
public class DirectorService implements IDirectorService {
    private final DirectorRepository directorRepository;

    public DirectorService(DirectorRepository directorRepository) {
        this.directorRepository = directorRepository;
    }

    @Override
    public List<Director> getAllDirectors() {
        return directorRepository.findAll();
    }

    @Override
    public Director getDirectorById(String id) {
        return directorRepository.findById(id).orElse(null);
    }

    @Override
    public Director createDirector(Director director) {
        return directorRepository.insert(director);
    }

    @Override
    public Director updateDirector(Director director) {
        return directorRepository.save(director);
    }

    @Override
    public void deleteDirector(String id) {
        directorRepository.deleteById(id);
    }
}
