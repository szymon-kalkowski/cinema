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

import com.example.cinema.models.Order;
import com.example.cinema.repositories.OrderRepository;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {
    private IOrderService orderService;
    @Mock
    private OrderRepository orderRepository;

    @BeforeEach
    void setUp() {
        orderService = new OrderService(orderRepository, null);
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
        // when
        orderService.deleteOrder(order.getId());
        // then
        Mockito.verify(orderRepository, Mockito.times(1)).deleteById(order.getId());
    }
}
