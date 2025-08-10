package com.lucidity.delivery.dto;

import com.lucidity.delivery.domain.DeliveryPerson;
import com.lucidity.delivery.domain.GeoLocation;
import com.lucidity.delivery.domain.OrderEta;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class RoutePlan {
    private final DeliveryPerson deliveryPerson;
    private GeoLocation currentLocation;
    private double currentMinutes;
    private final List<String> steps = new ArrayList<>();
    private final Map<String, OrderEta> orderEtas = new HashMap<>();

    public RoutePlan(DeliveryPerson deliveryPerson) {
        this.deliveryPerson = deliveryPerson;
        this.currentLocation = deliveryPerson.getCurrentLocation();
        this.currentMinutes = 0.0;
    }

    public void recordOrderEta(String orderId, String consumerName, double etaMinutes) {
        orderEtas.put(orderId, new OrderEta(orderId, consumerName, etaMinutes));
    }
} 