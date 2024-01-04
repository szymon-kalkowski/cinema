package com.example.cinema.services;

import java.util.List;

import com.example.cinema.models.Actor;

public interface IActorService {

    List<Actor> getAllActors();

    Actor getActorById(String id);

    Actor getActorByName(String name);

    Actor createActor(Actor actor);

    Actor updateActor(Actor actor);

    void deleteActor(String id);

}