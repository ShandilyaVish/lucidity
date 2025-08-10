package com.lucidity.delivery.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Restaurant {
    private String id;
    private String name;
    private GeoLocation location;
    // average preparation time in minutes
    private int averagePrepMinutes;
} 