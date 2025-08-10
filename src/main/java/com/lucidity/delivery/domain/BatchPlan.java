package com.lucidity.delivery.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BatchPlan {
    private final List<String> steps = new ArrayList<>();
    private double totalMinutes;
    private final Map<String, OrderEta> orderIdToEta = new HashMap<>();

    public void addStep(String step) {
        steps.add(step);
    }

    public List<String> getSteps() {
        return steps;
    }

    public double getTotalMinutes() {
        return totalMinutes;
    }

    public void setTotalMinutes(double totalMinutes) {
        this.totalMinutes = totalMinutes;
    }

    public Map<String, OrderEta> getOrderIdToEta() {
        return orderIdToEta;
    }

    public void recordOrderEta(String orderId, String consumerName, double etaMinutes) {
        orderIdToEta.put(orderId, new OrderEta(orderId, consumerName, etaMinutes));
    }
} 