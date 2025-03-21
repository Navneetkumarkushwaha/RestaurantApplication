package com.nav.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nav.model.Restaurant;
import com.nav.service.OrderService;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
public class OrderController {
    private final OrderService orderService;
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("api/path")
    public String getMethodName() {
        return new String("Hello");
    }
    

    @PostMapping("/restaurant")
    public ResponseEntity<String> addRestaurant(@RequestBody Restaurant restaurant) {
        orderService.onboardRestaurant(restaurant);
        return ResponseEntity.ok("Restaurant added: " + restaurant.getName());
    }

    @PostMapping("/restaurant/menu")
    public String updateMenu(@RequestParam String name, @RequestParam String item, @RequestParam int price) {
        orderService.addOrUpdateMenu(name, item, price);
        return "Menu updated for restaurant: " + name;
    }

    @PostMapping("/order")
    public String placeOrder(@RequestParam String user, @RequestBody Map<String, Integer> items, @RequestParam String strategy) {
        return orderService.placeOrder(user, items, strategy);
    }

    @PostMapping("/order/complete")
    public String completeOrder(@RequestParam int orderId) {
        orderService.completeOrder(orderId);
        return "Order " + orderId + " marked as completed.";
    }
}