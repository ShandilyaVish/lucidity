package com.lucidity.delivery.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class BatchPlan {
    private final List<String> steps = new ArrayList<>();
    private double totalMinutes;
    private final Map<String, OrderEta> orderIdToEta = new HashMap<>();

    public void addStep(String step) {
        steps.add(step);
    }

    public void recordOrderEta(String orderId, String consumerName, double etaMinutes) {
        orderIdToEta.put(orderId, new OrderEta(orderId, consumerName, etaMinutes));
    }
} 