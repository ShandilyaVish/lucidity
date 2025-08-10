package com.lucidity.delivery.service_impl;

import com.lucidity.delivery.domain.GeoLocation;
import com.lucidity.delivery.service.DistanceService;
import org.springframework.stereotype.Service;

@Service
public class HaversineDistanceService implements DistanceService {

    private static final double EARTH_RADIUS_KM = 6371.0;

    @Override
    public double distanceKm(GeoLocation a, GeoLocation b) {
        double lat1 = Math.toRadians(a.getLatitude());
        double lon1 = Math.toRadians(a.getLongitude());
        double lat2 = Math.toRadians(b.getLatitude());
        double lon2 = Math.toRadians(b.getLongitude());

        double dlat = lat2 - lat1;
        double dlon = lon2 - lon1;

        double sinLat = Math.sin(dlat / 2);
        double sinLon = Math.sin(dlon / 2);

        double c = 2 * Math.asin(Math.sqrt(sinLat * sinLat + Math.cos(lat1) * Math.cos(lat2) * sinLon * sinLon));
        return EARTH_RADIUS_KM * c;
    }
} 