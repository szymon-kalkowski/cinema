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

import com.example.cinema.models.Director;
import com.example.cinema.repositories.DirectorRepository;

@ExtendWith(MockitoExtension.class)
public class DirectorServiceTest {
    private IDirectorService directorService;
    @Mock
    private DirectorRepository directorRepository;

    @BeforeEach
    void setUp() {
        directorService = new DirectorService(directorRepository);
    }

    @Test
    void testGetAllDirectors() {
        // given
        Mockito.when(directorRepository.findAll()).thenReturn(List.of(new Director(), new Director()));
        // when
        List<Director> directors = directorService.getAllDirectors();
        // then
        assertEquals(directors.size(), 2);
    }

    @Test
    void testGetDirectorById() {
        // given
        Director director = new Director();
        director.setId("1");
        Mockito.when(directorRepository.findById("1")).thenReturn(Optional.of(director));
        // when
        Director foundDirector = directorService.getDirectorById("1");
        // then
        assertEquals(foundDirector.getId(), "1");
    }

    @Test
    void testCreateDirector() {
        // given
        Director director = new Director();
        director.setId("1");
        Mockito.when(directorRepository.insert(director)).thenReturn(director);
        // when
        Director createdDirector = directorService.createDirector(director);
        // then
        assertEquals(createdDirector.getId(), "1");
    }

    @Test
    void testUpdateDirector() {
        // given
        Director director = new Director();
        director.setId("1");
        Mockito.when(directorRepository.save(director)).thenReturn(director);
        // when
        Director updatedDirector = directorService.updateDirector(director);
        // then
        assertEquals(updatedDirector.getId(), "1");
    }

    @Test
    void testDeleteDirector() {
        // given
        Director director = new Director();
        director.setId("1");
        // when
        directorService.deleteDirector(director.getId());
        // then
        Mockito.verify(directorRepository, Mockito.times(1)).deleteById(director.getId());
    }
}
