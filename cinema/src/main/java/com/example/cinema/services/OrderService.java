package com.example.cinema.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.cinema.models.Movie;
import com.example.cinema.models.Order;
import com.example.cinema.repositories.OrderRepository;

@Service
public class OrderService implements IOrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Order getOrderById(String id) {
        return orderRepository.findById(id).orElse(null);
    }

    @Override
    public Order createOrder(Order order) {
        return orderRepository.insert(order);
    }

    @Override
    public Order updateOrder(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public void deleteOrder(String id) {
        Order order = getOrderById(id);
        if (order == null) {
            return;
        }
        orderRepository.deleteById(id);
    }

    @Override
    public Map<Movie, Integer> getMoviesDailyStats(LocalDate date) {
        List<Order> orders = getAllOrders();
        orders = orders.stream().filter(order -> order.getSeance().getDate().equals(date))
                .collect(Collectors.toList());

        Map<Movie, Integer> moviesStats = orders.stream()
                .collect(Collectors.groupingBy(order -> order.getSeance().getMovie(),
                        Collectors.summingInt(order -> order.getSeats().size())));

        return moviesStats;
    }

    @Override
    public Double getDailyTotalIncome(LocalDate date) {
        Map<Movie, Integer> moviesStats = getMoviesDailyStats(date);
        Double totalIncome = moviesStats.entrySet().stream()
                .map(entry -> entry.getValue() * 10.0).reduce(0.0, Double::sum);
        return totalIncome;
    }
}
