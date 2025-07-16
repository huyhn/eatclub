package com.eatclub.takehome;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class RestaurantRepository {
    private final List<Restaurant> restaurants = new ArrayList<>();


    public void add(Restaurant restaurant) {
        restaurants.add(restaurant);
    }
    public List<Restaurant> all() {
        return restaurants;
    }
}
