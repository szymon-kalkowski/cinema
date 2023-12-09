package com.example.cinema.services;

import java.util.List;

import com.example.cinema.models.Order;

public interface IOrderService {

    List<Order> getAllOrders();

    Order getOrderById(String id);

    Order createOrder(Order order);

    Order updateOrder(Order order);

    void deleteOrder(String id);

}