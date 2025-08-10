package com.lucidity.delivery.dto;

import com.lucidity.delivery.domain.GeoLocation;
import lombok.Value;

@Value
public class AssignmentOption {
    int routeIndex;
    String orderId;
    String restaurantId;
    String consumerName;
    GeoLocation consumerLocation;
    double deltaMinutes;
    double resultingMakespan;
} 