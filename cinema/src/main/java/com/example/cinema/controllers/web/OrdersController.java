package com.example.cinema.controllers.web;

import java.time.LocalDate;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.cinema.services.IOrderService;

@Controller
public class OrdersController {
    private final IOrderService orderService;

    public OrdersController(IOrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/orders")
    public String orders(Model model, @AuthenticationPrincipal OidcUser principal) {
        model.addAttribute("orders", orderService.getAllOrders());
        return "orders";
    }

    @GetMapping("/statistics")
    public String statistics(@AuthenticationPrincipal OidcUser principal) {
        return "redirect:/statistics/" + LocalDate.now().toString();
    }

    @GetMapping("/statistics/{date}")
    public String statisticsByDay(@PathVariable("date") String date, Model model,
            @AuthenticationPrincipal OidcUser principal) {
        model.addAttribute("statistics", orderService.getMoviesDailyStats(LocalDate.parse(date)));
        model.addAttribute("total", orderService.getDailyTotalIncome(LocalDate.parse(date)));
        model.addAttribute("date", date);
        return "statistics";
    }

    @PostMapping("/statistics")
    public String statisticsRedirect(@RequestParam("date") String date, @AuthenticationPrincipal OidcUser principal) {
        return "redirect:/statistics/" + date;
    }
}
