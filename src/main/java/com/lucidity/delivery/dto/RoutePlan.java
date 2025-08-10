package com.lucidity.delivery.dto;

import com.lucidity.delivery.domain.DeliveryPerson;
import com.lucidity.delivery.domain.GeoLocation;
import com.lucidity.delivery.domain.OrderEta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public DeliveryPerson getDeliveryPerson() {
        return deliveryPerson;
    }

    public GeoLocation getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(GeoLocation currentLocation) {
        this.currentLocation = currentLocation;
    }

    public double getCurrentMinutes() {
        return currentMinutes;
    }

    public void setCurrentMinutes(double currentMinutes) {
        this.currentMinutes = currentMinutes;
    }

    public List<String> getSteps() {
        return steps;
    }

    public Map<String, OrderEta> getOrderEtas() {
        return orderEtas;
    }

    public void recordOrderEta(String orderId, String consumerName, double etaMinutes) {
        orderEtas.put(orderId, new OrderEta(orderId, consumerName, etaMinutes));
    }
} 