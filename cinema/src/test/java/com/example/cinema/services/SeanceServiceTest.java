package com.example.cinema.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.cinema.models.Hall;
import com.example.cinema.models.Movie;
import com.example.cinema.models.Seance;
import com.example.cinema.repositories.SeanceRepository;

@ExtendWith(MockitoExtension.class)
public class SeanceServiceTest {
    private ISeanceService seanceService;
    @Mock
    private SeanceRepository seanceRepository;

    @BeforeEach
    void setUp() {
        seanceService = new SeanceService(seanceRepository);
    }

    @Test
    void testGetAllSeances() {
        // given
        Mockito.when(seanceRepository.findAll()).thenReturn(List.of(new Seance(), new Seance()));
        // when
        List<Seance> seances = seanceService.getAllSeances();
        // then
        assertEquals(seances.size(), 2);
    }

    @Test
    void testGetSeanceById() {
        // given
        Seance seance = new Seance();
        seance.setId("1");
        Mockito.when(seanceRepository.findById("1")).thenReturn(Optional.of(seance));
        // when
        Seance foundSeance = seanceService.getSeanceById("1");
        // then
        assertEquals(foundSeance.getId(), "1");
    }

    @Test
    void testCreateSeance() {
        // given
        Seance seance = new Seance();
        seance.setId("1");
        Mockito.when(seanceRepository.insert(seance)).thenReturn(seance);
        // when
        Seance createdSeance = seanceService.createSeance(seance);
        // then
        assertEquals(createdSeance.getId(), "1");
    }

    @Test
    void testUpdateSeance() {
        // given
        Seance seance = new Seance();
        seance.setId("1");
        Mockito.when(seanceRepository.save(seance)).thenReturn(seance);
        // when
        Seance updatedSeance = seanceService.updateSeance(seance);
        // then
        assertEquals(updatedSeance.getId(), "1");
    }

    @Test
    void testDeleteSeance() {
        // given
        Seance seance = new Seance();
        seance.setId("1");
        // when
        seanceService.deleteSeance(seance.getId());
        // then
        Mockito.verify(seanceRepository, Mockito.times(1)).deleteById(seance.getId());
    }

    @Test
    void testGetSeancesByDate() {
        // given
        Mockito.when(seanceRepository.findByDate(LocalDate.of(2023, 1, 1)))
                .thenReturn(List.of(new Seance(), new Seance()));
        // when
        List<Seance> seances = seanceService.getSeancesByDate(LocalDate.of(2023, 1, 1));
        // then
        assertEquals(seances.size(), 2);
    }

    @Test
    void testGetRepertoire() {
        // given
        Movie movie = new Movie();
        Mockito.when(seanceRepository.findByDate(LocalDate.of(2023, 1, 1)))
                .thenReturn(List.of(new Seance(movie, 1, LocalDate.of(2023, 1, 1), LocalTime.of(20, 0, 0), new Hall()),
                        new Seance(movie, 2, LocalDate.of(2023, 1, 1), LocalTime.of(20, 0, 0), new Hall())));
        // when
        Map<Movie, List<Seance>> seances = seanceService.getRepertoire(LocalDate.of(2023, 1, 1));
        // then
        assertEquals(seances.size(), 1);
        assertEquals(seances.get(movie).size(), 2);
    }
}
