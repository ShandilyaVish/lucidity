package com.lucidity.delivery.service;

import com.lucidity.delivery.domain.GeoLocation;

public interface DistanceService {
    double distanceKm(GeoLocation a, GeoLocation b);
    default double travelMinutes(GeoLocation a, GeoLocation b) {
        return distanceKm(a, b) * 60.0 / 20.0;
    }
} 