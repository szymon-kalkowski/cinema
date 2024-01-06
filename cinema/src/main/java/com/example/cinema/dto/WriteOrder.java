package com.example.cinema.dto;

import java.util.List;

public record WriteOrder(
        String name,
        String email,
        List<Seat> seats) {

}
