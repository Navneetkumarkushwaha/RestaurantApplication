package com.nav.model;


import java.util.HashMap;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Restaurant {
    private String name;
    private double rating;
    private int maxOrders;
    private int activeOrders;
    private Map<String, Integer> menu = new HashMap<>();

    public Restaurant(String name, double rating, int maxOrders) {
        this.name = name;
        this.rating = rating;
        this.maxOrders = maxOrders;
        this.activeOrders = 0;
    }

    public boolean canAcceptOrder() {
        return activeOrders < maxOrders;
    }

    public boolean canFulfillOrder(Map<String, Integer> orderItems) {
        return orderItems.keySet().stream().allMatch(menu::containsKey);
    }

    public int calculateCost(Map<String, Integer> orderItems) {
        int total = 0;
        for (Map.Entry<String, Integer> entry : orderItems.entrySet()) {
            total += menu.get(entry.getKey()) * entry.getValue();
        }
        return total;
    }

    public void incrementOrders() {
        activeOrders++;
    }

    public void decrementOrders() {
        activeOrders--;
    }
}
