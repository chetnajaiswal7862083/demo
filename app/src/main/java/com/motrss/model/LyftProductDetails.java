package com.motrss.model;

/**
 * Created by wel come on 24-08-2017.
 */

public class LyftProductDetails {
    String displayName, minCost, maxCost, duration, rideType;

    public LyftProductDetails(String displayName, String minCost, String maxCost, String duration, String rideType) {
        this.displayName = displayName;
        this.minCost = minCost;
        this.maxCost = maxCost;
        this.duration = duration;
        this.rideType = rideType;
        System.out.println("inside constructor--->   "+displayName);
    }

    public String getDisplayName() {
        return this.displayName;
    }

    public String getMinCost() {
        return this.minCost;
    }

    public String getMaxCost() {
        return this.maxCost;
    }

    public String getDuration() {
        return this.duration;
    }

    public String getRideType() {
        return this.rideType;
    }

    public void setDisplayName(String name) {

        this.displayName = name;
    }

    public void setMinCost(String minCost) {
        this.minCost = minCost;
    }

    public void setMaxCost(String maxCost) {
        this.maxCost=maxCost;
    }

    public void setDuration(String duration) {
        this.duration=duration;
    }

    public void setRideType(String rideType) {
        this.rideType=rideType;
    }
}
