package com.motrss.core.retrofit.response;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @Author Dhananjay Kulkarni.
 */

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY )
@JsonIgnoreProperties(ignoreUnknown = true)
public class UberEstimate {

    @JsonProperty("fare")
    private UberFare fare;

    @JsonProperty("trip")
    private UberTrip trip;


    private String pickup_estimate;

    public UberFare getFare() {
        return fare;
    }

    public void setFare(UberFare fare) {
        this.fare = fare;
    }

    public UberTrip getTrip() {
        return trip;
    }

    public void setTrip(UberTrip trip) {
        this.trip = trip;
    }

    public String getPickup_estimate ()
    {
        return pickup_estimate;
    }

    public void setPickup_estimate (String pickup_estimate)
    {
        this.pickup_estimate = pickup_estimate;
    }
}
