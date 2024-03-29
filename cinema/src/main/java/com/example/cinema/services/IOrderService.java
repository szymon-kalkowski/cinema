package com.example.cinema.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import com.example.cinema.dto.ReadStatistics;
import com.example.cinema.models.Movie;
import com.example.cinema.models.Order;

public interface IOrderService {

    List<Order> getAllOrders();

    Order getOrderById(String id);

    Order createOrder(Order order);

    Order updateOrder(Order order);

    void deleteOrder(String id);

    Map<Movie, Integer> getMoviesDailyStats(LocalDate date);

    Double getDailyTotalIncome(LocalDate date);

    ReadStatistics getReadStatistics(LocalDate date);

    List<Order> getOrdersBySeanceDate(LocalDate date);

}