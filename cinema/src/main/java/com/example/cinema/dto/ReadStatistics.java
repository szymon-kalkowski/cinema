package com.example.cinema.dto;

import java.util.Map;

public record ReadStatistics(
        Map<String, Integer> moviesStatistics,
        Double totalIncome) {
}
