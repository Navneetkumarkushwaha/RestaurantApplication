package com.nav.service;

import java.util.*;

import org.springframework.stereotype.Service;

import com.nav.model.Order;
import com.nav.model.Restaurant;
import com.nav.strategy.HighestRatingStrategy;
import com.nav.strategy.LowestCostStrategy;
import com.nav.strategy.SelectionStrategy;

@Service
public class OrderService {
    private final List<Restaurant> restaurants = new ArrayList<>();
    private final Map<Integer, Order> orders = new HashMap<>();
    private final Map<String, SelectionStrategy> strategies;

    public OrderService() {
        strategies = new HashMap<>();
        strategies.put("Lowest Cost", new LowestCostStrategy());
        strategies.put("Highest Rating", new HighestRatingStrategy());
    }

    public void onboardRestaurant(Restaurant restaurant) {
        restaurants.add(restaurant);
    }

    public void addOrUpdateMenu(String restaurantName, String item, int price) {
        for (Restaurant restaurant : restaurants) {
            if (restaurant.getName().equals(restaurantName)) {
                restaurant.getMenu().put(item, price);
                break;
            }
        }
    }

    public String placeOrder(String user, Map<String, Integer> items, String strategy) {
        if (!strategies.containsKey(strategy)) return "Invalid selection strategy";
        
        SelectionStrategy selectionStrategy = strategies.get(strategy);
        Restaurant selectedRestaurant = selectionStrategy.selectRestaurant(restaurants, items);
        
        if (selectedRestaurant == null) return "Cannot assign the order";

        Order order = new Order(user, items, strategy);
        order.assignRestaurant(selectedRestaurant);
        orders.put(order.getOrderId(), order);

        return "Order assigned to " + selectedRestaurant.getName();
    }

    public void completeOrder(int orderId) {
        if (orders.containsKey(orderId)) {
            orders.get(orderId).completeOrder();
        }
    }
}