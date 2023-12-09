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

import com.example.cinema.models.Person;
import com.example.cinema.repositories.PersonRepository;

@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {
    private IPersonService personService;
    @Mock
    private PersonRepository personRepository;

    @BeforeEach
    void setUp() {
        personService = new PersonService(personRepository);
    }

    @Test
    void testGetAllPersons() {
        // given
        Mockito.when(personRepository.findAll()).thenReturn(List.of(new Person(), new Person()));
        // when
        List<Person> persons = personService.getAllPersons();
        // then
        assertEquals(persons.size(), 2);
    }

    @Test
    void testGetPersonById() {
        // given
        Person person = new Person();
        person.setId("1");
        Mockito.when(personRepository.findById("1")).thenReturn(Optional.of(person));
        // when
        Person foundPerson = personService.getPersonById("1");
        // then
        assertEquals(foundPerson.getId(), "1");
    }

    @Test
    void testCreatePerson() {
        // given
        Person person = new Person();
        person.setId("1");
        Mockito.when(personRepository.insert(person)).thenReturn(person);
        // when
        Person createdPerson = personService.createPerson(person);
        // then
        assertEquals(createdPerson.getId(), "1");
    }

    @Test
    void testUpdatePerson() {
        // given
        Person person = new Person();
        person.setId("1");
        Mockito.when(personRepository.save(person)).thenReturn(person);
        // when
        Person updatedPerson = personService.updatePerson(person);
        // then
        assertEquals(updatedPerson.getId(), "1");
    }

    @Test
    void testDeletePerson() {
        // given
        Person person = new Person();
        person.setId("1");
        // when
        personService.deletePerson(person.getId());
        // then
        Mockito.verify(personRepository, Mockito.times(1)).deleteById(person.getId());
    }
}
