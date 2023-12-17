package com.example.cinema.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.cinema.models.Movie;
import com.example.cinema.models.Order;
import com.example.cinema.models.Seance;
import com.example.cinema.repositories.OrderRepository;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {
    private IOrderService orderService;
    @Mock
    private OrderRepository orderRepository;

    @BeforeEach
    void setUp() {
        orderService = new OrderService(orderRepository);
    }

    @Test
    void testGetAllOrders() {
        // given
        Mockito.when(orderRepository.findAll()).thenReturn(List.of(new Order(), new Order()));
        // when
        List<Order> orders = orderService.getAllOrders();
        // then
        assertEquals(orders.size(), 2);
    }

    @Test
    void testGetOrderById() {
        // given
        Order order = new Order();
        order.setId("1");
        Mockito.when(orderRepository.findById("1")).thenReturn(Optional.of(order));
        // when
        Order foundOrder = orderService.getOrderById("1");
        // then
        assertEquals(foundOrder.getId(), "1");
    }

    @Test
    void testCreateOrder() {
        // given
        Order order = new Order();
        order.setId("1");
        Mockito.when(orderRepository.insert(order)).thenReturn(order);
        // when
        Order createdOrder = orderService.createOrder(order);
        // then
        assertEquals(createdOrder.getId(), "1");
    }

    @Test
    void testUpdateOrder() {
        // given
        Order order = new Order();
        order.setId("1");
        Mockito.when(orderRepository.save(order)).thenReturn(order);
        // when
        Order updatedOrder = orderService.updateOrder(order);
        // then
        assertEquals(updatedOrder.getId(), "1");
    }

    @Test
    void testDeleteOrder() {
        // given
        Order order = new Order();
        order.setId("1");
        Mockito.when(orderRepository.findById("1")).thenReturn(Optional.of(order));
        // when
        orderService.deleteOrder(order.getId());
        // then
        Mockito.verify(orderRepository, Mockito.times(1)).deleteById(order.getId());
    }

    @Test
    void testGetMoviesDailyStats() {
        // given
        LocalDate date = LocalDate.of(2023, 1, 1);
        Movie movie = new Movie();
        movie.setTitle("Star Wars");

        Seance seance = new Seance();
        seance.setDate(date);
        seance.setMovie(movie);

        Order order1 = new Order();
        order1.setSeance(seance);
        order1.setSeats(List.of("row-1-col-2", "row-1-col-2"));

        Order order2 = new Order();
        order2.setSeance(seance);
        order2.setSeats(List.of("row-3-col-2"));

        List<Order> orders = List.of(order1, order2);
        Mockito.when(orderRepository.findAll()).thenReturn(orders);

        // when
        Map<Movie, Integer> result = orderService.getMoviesDailyStats(date);

        // then
        Mockito.verify(orderRepository, Mockito.times(1)).findAll();
        assertEquals(result.get(movie), 3);
    }

    @Test
    void testGetDailyTotalIncome() {
        // given
        LocalDate date = LocalDate.of(2023, 1, 1);
        Movie movie = new Movie();
        movie.setTitle("Star Wars");

        Seance seance = new Seance();
        seance.setDate(date);
        seance.setMovie(movie);

        Order order1 = new Order();
        order1.setSeance(seance);
        order1.setSeats(List.of("row-1-col-2", "row-1-col-2"));

        Order order2 = new Order();
        order2.setSeance(seance);
        order2.setSeats(List.of("row-3-col-2"));

        List<Order> orders = List.of(order1, order2);
        Mockito.when(orderRepository.findAll()).thenReturn(orders);

        // when
        Double result = orderService.getDailyTotalIncome(date);

        // then
        Mockito.verify(orderRepository, Mockito.times(1)).findAll();
        assertEquals(result, 30.0);
    }
}
