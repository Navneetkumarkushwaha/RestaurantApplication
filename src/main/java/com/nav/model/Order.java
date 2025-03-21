package com.nav.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class Order {
    private static int counter = 1;
    private final int orderId;
    private final String user;
    private final Map<String, Integer> items;
    private final String selectionStrategy;
    private Restaurant assignedRestaurant;
    private String status;

    public Order(String user, Map<String, Integer> items, String selectionStrategy) {
        this.orderId = counter++;
        this.user = user;
        this.items = items;
        this.selectionStrategy = selectionStrategy;
        this.status = "PLACED";
    }

    public void assignRestaurant(Restaurant restaurant) {
        this.assignedRestaurant = restaurant;
        this.status = "ACCEPTED";
        restaurant.incrementOrders();
    }

    public void completeOrder() {
        if (assignedRestaurant != null) {
            assignedRestaurant.decrementOrders();
            this.status = "COMPLETED";
        }
    }
}