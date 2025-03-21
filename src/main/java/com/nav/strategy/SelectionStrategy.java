package com.nav.strategy;


import java.util.List;
import java.util.Map;

import com.nav.model.Restaurant;

public interface SelectionStrategy {
    Restaurant selectRestaurant(List<Restaurant> restaurants, Map<String, Integer> items);
}
