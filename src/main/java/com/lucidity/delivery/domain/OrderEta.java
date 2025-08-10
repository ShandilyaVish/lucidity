package com.lucidity.delivery.domain;

public class OrderEta {
    private String orderId;
    private String consumerName;
    private double etaMinutes;

    public OrderEta() {}

    public OrderEta(String orderId, String consumerName, double etaMinutes) {
        this.orderId = orderId;
        this.consumerName = consumerName;
        this.etaMinutes = etaMinutes;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getConsumerName() {
        return consumerName;
    }

    public void setConsumerName(String consumerName) {
        this.consumerName = consumerName;
    }

    public double getEtaMinutes() {
        return etaMinutes;
    }

    public void setEtaMinutes(double etaMinutes) {
        this.etaMinutes = etaMinutes;
    }
} 