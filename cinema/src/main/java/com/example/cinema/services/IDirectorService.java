package com.example.cinema.services;

import java.util.List;

import com.example.cinema.models.Director;

public interface IDirectorService {

    List<Director> getAllDirectors();

    Director getDirectorById(String id);

    Director getDirectorByName(String name);

    Director createDirector(Director director);

    Director updateDirector(Director director);

    void deleteDirector(String id);

}