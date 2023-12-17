package com.example.cinema.controllers.api;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.cinema.models.Order;
import com.example.cinema.services.IOrderService;

import org.springframework.web.bind.annotation.GetMapping;

@CrossOrigin
@RestController
public class ApiOrdersController {
    private final IOrderService orderService;

    public ApiOrdersController(IOrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/api/orders")
    public List<Order> getOrders() {
        return orderService.getAllOrders();
    }

    @DeleteMapping("/api/orders/{id}")
    public String deleteOrder(@PathVariable("id") String id) {
        orderService.deleteOrder(id);
        return id;
    }
}
