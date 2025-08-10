package com.lucidity.delivery.domain;

public class Order {
    private String id;
    private String restaurantId;
    private String consumerName;
    private GeoLocation consumerLocation;

    public Order() {}

    public Order(String id, String consumerName, GeoLocation consumerLocation, String restaurantId) {
        this.id = id;
        this.consumerName = consumerName;
        this.consumerLocation = consumerLocation;
        this.restaurantId = restaurantId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getConsumerName() {
        return consumerName;
    }

    public void setConsumerName(String consumerName) {
        this.consumerName = consumerName;
    }

    public GeoLocation getConsumerLocation() {
        return consumerLocation;
    }

    public void setConsumerLocation(GeoLocation consumerLocation) {
        this.consumerLocation = consumerLocation;
    }
} 