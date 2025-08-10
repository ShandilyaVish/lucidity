package com.lucidity.delivery.service;

import com.lucidity.delivery.domain.Restaurant;

public interface RestaurantService {
    Restaurant saveRestaurant(Restaurant restaurant);
    Restaurant getRestaurant(String id);
} 