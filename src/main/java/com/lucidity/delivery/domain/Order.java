package com.lucidity.delivery.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    private String id;
    private String consumerName;
    private GeoLocation consumerLocation;
    private String restaurantId;
} 