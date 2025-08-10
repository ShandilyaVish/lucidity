package com.lucidity.delivery.dto;

import com.lucidity.delivery.domain.GeoLocation;

public class AssignmentOption {
    private final int routeIndex;
    private final String orderId;
    private final String restaurantId;
    private final String consumerName;
    private final GeoLocation consumerLocation;
    private final double deltaMinutes;
    private final double resultingMakespan;

    public AssignmentOption(int routeIndex, String orderId, String restaurantId, String consumerName, GeoLocation consumerLocation,
                            double deltaMinutes, double resultingMakespan) {
        this.routeIndex = routeIndex;
        this.orderId = orderId;
        this.restaurantId = restaurantId;
        this.consumerName = consumerName;
        this.consumerLocation = consumerLocation;
        this.deltaMinutes = deltaMinutes;
        this.resultingMakespan = resultingMakespan;
    }

    public int getRouteIndex() { return routeIndex; }
    public String getOrderId() { return orderId; }
    public String getRestaurantId() { return restaurantId; }
    public String getConsumerName() { return consumerName; }
    public GeoLocation getConsumerLocation() { return consumerLocation; }
    public double getDeltaMinutes() { return deltaMinutes; }
    public double getResultingMakespan() { return resultingMakespan; }
} 