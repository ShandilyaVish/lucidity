package com.lucidity.delivery.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GeoLocation {
    private double latitude;
    private double longitude;

    @Override
    public String toString() {
        return "(" + latitude + ", " + longitude + ")";
    }
} 