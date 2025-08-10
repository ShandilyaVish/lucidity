package com.lucidity.delivery.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryPerson {
    private String id;
    private String name;
    private GeoLocation currentLocation;
} 