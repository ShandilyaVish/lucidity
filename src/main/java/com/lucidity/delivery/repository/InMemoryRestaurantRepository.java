package com.lucidity.delivery.repository;

import com.lucidity.delivery.domain.Restaurant;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class InMemoryRestaurantRepository {
    private final Map<String, Restaurant> restaurants = new ConcurrentHashMap<>();

    public Restaurant save(Restaurant restaurant) {
        restaurants.put(restaurant.getId(), restaurant);
        return restaurant;
    }

    public Restaurant findById(String id) {
        return restaurants.get(id);
    }

    public Collection<Restaurant> findAll() {
        return restaurants.values();
    }
} 