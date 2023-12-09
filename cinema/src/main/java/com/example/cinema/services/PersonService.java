package com.example.cinema.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.cinema.models.Person;
import com.example.cinema.repositories.PersonRepository;

@Service
public class PersonService implements IPersonService {
    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }

    @Override
    public Person getPersonById(String id) {
        return personRepository.findById(id).orElse(null);
    }

    @Override
    public Person getPersonByEmail(String email) {
        return personRepository.findByEmail(email);
    }

    @Override
    public Person createPerson(Person person) {
        return personRepository.insert(person);
    }

    @Override
    public Person updatePerson(Person person) {
        return personRepository.save(person);
    }

    @Override
    public void deletePerson(String id) {
        personRepository.deleteById(id);
    }
}
