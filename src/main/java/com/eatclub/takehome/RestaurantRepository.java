package com.eatclub.takehome;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class RestaurantRepository {
    private final Map<String, Restaurant> restaurants = new HashMap<>();


    public void add(Restaurant restaurant) {
        restaurants.put(restaurant.objectId(), restaurant);
    }
    public Map<String, Restaurant> all() {
        return restaurants;
    }
}
