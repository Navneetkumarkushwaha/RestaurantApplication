package com.nav.strategy;

import java.util.List;
import java.util.Map;

import com.nav.model.Restaurant;

public class HighestRatingStrategy implements SelectionStrategy {
    @Override
    public Restaurant selectRestaurant(List<Restaurant> restaurants, Map<String, Integer> items) {
        Restaurant bestRestaurant = null;
        double maxRating = -1;
        
        for (Restaurant restaurant : restaurants) {
            if (restaurant.canFulfillOrder(items) && restaurant.canAcceptOrder()) {
                if (restaurant.getRating() > maxRating) {
                    maxRating = restaurant.getRating();
                    bestRestaurant = restaurant;
                }
            }
        }
        return bestRestaurant;
    }
}
