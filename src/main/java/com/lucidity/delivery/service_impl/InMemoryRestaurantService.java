package com.lucidity.delivery.service_impl;

import com.lucidity.delivery.domain.Restaurant;
import com.lucidity.delivery.repository.InMemoryRestaurantRepository;
import com.lucidity.delivery.service.RestaurantService;
import org.springframework.stereotype.Service;

@Service
public class InMemoryRestaurantService implements RestaurantService {

    private final InMemoryRestaurantRepository repository;

    public InMemoryRestaurantService(InMemoryRestaurantRepository repository) {
        this.repository = repository;
    }

    @Override
    public Restaurant saveRestaurant(Restaurant restaurant) {
        return repository.save(restaurant);
    }

    @Override
    public Restaurant getRestaurant(String id) {
        return repository.findById(id);
    }
} 