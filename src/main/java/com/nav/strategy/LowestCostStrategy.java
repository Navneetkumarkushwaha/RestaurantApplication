package com.nav.strategy;

import java.util.List;
import java.util.Map;

import com.nav.model.Restaurant;

public class LowestCostStrategy implements SelectionStrategy {
    
    @Override
    public Restaurant selectRestaurant(List<Restaurant> restaurants, Map<String, Integer> items) {
        Restaurant bestRestaurant = null;
        int minCost = Integer.MAX_VALUE;
        
        for (Restaurant restaurant : restaurants) {
            if (restaurant.canFulfillOrder(items) && restaurant.canAcceptOrder()) {
                int cost = restaurant.calculateCost(items);
                if (cost < minCost) {
                    minCost = cost;
                    bestRestaurant = restaurant;
                }
            }
        }
        return bestRestaurant;
}
}
