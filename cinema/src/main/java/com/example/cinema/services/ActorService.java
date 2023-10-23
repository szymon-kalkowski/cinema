package com.example.cinema.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.cinema.models.Actor;
import com.example.cinema.repositories.ActorRepository;

@Service
public class ActorService {
    private final ActorRepository actorRepository;

    public ActorService(ActorRepository actorRepository) {
        this.actorRepository = actorRepository;
    }

    public List<Actor> getAllActors() {
        return actorRepository.findAll();
    }

    public Actor getActorById(String id) {
        return actorRepository.findById(id).orElse(null);
    }

    public Actor createActor(Actor actor) {
        return actorRepository.insert(actor);
    }

    public Actor updateActor(Actor actor) {
        return actorRepository.save(actor);
    }

    public void deleteActor(String id) {
        actorRepository.deleteById(id);
    }
}
