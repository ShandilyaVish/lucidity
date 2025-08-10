package com.lucidity.delivery.domain;

public class Restaurant {
    private String id;
    private String name;
    private GeoLocation location;
    // average preparation time in minutes
    private int averagePrepMinutes;

    public Restaurant() {}

    public Restaurant(String id, String name, GeoLocation location, int averagePrepMinutes) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.averagePrepMinutes = averagePrepMinutes;
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

    public GeoLocation getLocation() {
        return location;
    }

    public void setLocation(GeoLocation location) {
        this.location = location;
    }

    public int getAveragePrepMinutes() {
        return averagePrepMinutes;
    }

    public void setAveragePrepMinutes(int averagePrepMinutes) {
        this.averagePrepMinutes = averagePrepMinutes;
    }
} 