package com.example.cinema.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.cinema.models.Movie;
import com.example.cinema.models.Order;
import com.example.cinema.models.Seance;
import com.example.cinema.repositories.OrderRepository;

@Service
public class OrderService implements IOrderService {
    private final OrderRepository orderRepository;
    private final SeanceService seanceService;

    public OrderService(OrderRepository orderRepository, SeanceService seanceService) {
        this.orderRepository = orderRepository;
        this.seanceService = seanceService;
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
        List<String> seats = order.getSeats();
        List<List<Integer>> seatCoordinates = seats.stream().map(x -> {
            String[] elems = x.split("-");
            return List.of(Integer.parseInt(elems[1]), Integer.parseInt(elems[3]));
        }).collect(Collectors.toList());

        Seance seance = seanceService.getSeanceById(order.getSeance().getId());
        for (List<Integer> seat : seatCoordinates) {
            seance.getHall().cancelSeat(seat.get(0), seat.get(1));
        }
        seanceService.updateSeance(seance);
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
}
