package com.example.cinema.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.cinema.models.Actor;
import com.example.cinema.repositories.ActorRepository;

@ExtendWith(MockitoExtension.class)
public class ActorServiceTest {
    private IActorService actorService;
    @Mock
    private ActorRepository actorRepository;

    @BeforeEach
    void setUp() {
        actorService = new ActorService(actorRepository);
    }

    @Test
    void testGetAllActors() {
        // given
        Mockito.when(actorRepository.findAll()).thenReturn(List.of(new Actor(), new Actor()));
        // when
        List<Actor> actors = actorService.getAllActors();
        // then
        assertEquals(actors.size(), 2);
    }

    @Test
    void testGetActorById() {
        // given
        Actor actor = new Actor();
        actor.setId("1");
        Mockito.when(actorRepository.findById("1")).thenReturn(Optional.of(actor));
        // when
        Actor foundActor = actorService.getActorById("1");
        // then
        assertEquals(foundActor.getId(), "1");
    }

    @Test
    void testCreateActor() {
        // given
        Actor actor = new Actor();
        actor.setId("1");
        Mockito.when(actorRepository.insert(actor)).thenReturn(actor);
        // when
        Actor createdActor = actorService.createActor(actor);
        // then
        assertEquals(createdActor.getId(), "1");
    }

    @Test
    void testUpdateActor() {
        // given
        Actor actor = new Actor();
        actor.setId("1");
        Mockito.when(actorRepository.save(actor)).thenReturn(actor);
        // when
        Actor updatedActor = actorService.updateActor(actor);
        // then
        assertEquals(updatedActor.getId(), "1");
    }

    @Test
    void testDeleteActor() {
        // given
        Actor actor = new Actor();
        actor.setId("1");
        // when
        actorService.deleteActor(actor.getId());
        // then
        Mockito.verify(actorRepository, Mockito.times(1)).deleteById(actor.getId());
    }
}
