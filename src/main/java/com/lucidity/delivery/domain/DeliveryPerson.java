package com.lucidity.delivery.domain;

public class DeliveryPerson {
    private String id;
    private String name;
    private GeoLocation currentLocation;

    public DeliveryPerson() {}

    public DeliveryPerson(String id, String name, GeoLocation currentLocation) {
        this.id = id;
        this.name = name;
        this.currentLocation = currentLocation;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public GeoLocation getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(GeoLocation currentLocation) {
        this.currentLocation = currentLocation;
    }
} 