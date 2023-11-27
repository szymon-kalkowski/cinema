package com.example.cinema.models;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@Document
@AllArgsConstructor
public class Hall {
    @Id
    private String id;
    private List<List<Person>> seats;

    public Hall() {
        seats = new ArrayList<List<Person>>();
        for (int i = 0; i < 10; i++) {
            ArrayList<Person> row = new ArrayList<Person>();
            for (int j = 0; j < 10; j++) {
                row.add(null);
            }
            seats.add(row);
        }
    }

    public void reserveSeat(int row, int col, Person person) {
        seats.get(row).set(col, person);
    }

    public void cancelSeat(int row, int col) {
        seats.get(row).set(col, null);
    }

    public boolean isSeatAvailable(int row, int col) {
        return seats.get(row).get(col) == null;
    }

    public Person getPerson(int row, int col) {
        return seats.get(row).get(col);
    }

    public List<List<Person>> getSeats() {
        return seats;
    }

    public List<String> getAvailableSeats() {
        List<String> availableSeats = new ArrayList<String>();
        for (int i = 0; i < seats.size(); i++) {
            for (int j = 0; j < seats.get(i).size(); j++) {
                if (isSeatAvailable(j, i)) {
                    availableSeats.add("row-" + j + "-seat-" + i);
                }
            }
        }
        return availableSeats;
    }
}