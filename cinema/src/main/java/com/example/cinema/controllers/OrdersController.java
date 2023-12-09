package com.example.cinema.controllers;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.cinema.services.IOrderService;

@Controller
public class OrdersController {
    private final IOrderService orderService;

    public OrdersController(IOrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/orders")
    public String home(Model model, @AuthenticationPrincipal OidcUser principal) {
        model.addAttribute("orders", orderService.getAllOrders());
        return "orders";
    }
}
