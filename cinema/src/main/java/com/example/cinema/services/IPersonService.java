package com.example.cinema.services;

import java.util.List;

import com.example.cinema.models.Person;

public interface IPersonService {

    List<Person> getAllPersons();

    Person getPersonById(String id);

    Person getPersonByEmail(String email);

    Person createPerson(Person person);

    Person updatePerson(Person person);

    void deletePerson(String id);

}