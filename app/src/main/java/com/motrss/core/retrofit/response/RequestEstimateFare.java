package com.motrss.core.retrofit.response;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * @Author Dhananjay Kulkarni.
 */

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class RequestEstimateFare {

    @JsonProperty("fare")
    private String fareList;

    @JsonProperty("trip")
    private String tripList;

    @JsonProperty("pickup_estimate")
    private String pickup_estimate;




    public String getFare() {
        return fareList;
    }

   /* public void setFare(String fareList) {
        this.fareList = fareList;
    }
*/
    public String gettrip() {
        return tripList;
    }

 /*   public void settrip(String tripList) {
        this.tripList = tripList;
    }
*/
    public String getpickup_estimate() {
        return pickup_estimate;
    }

 /*   public void setpickup_estimate(String pickup_estimate) {
        this.pickup_estimate = pickup_estimate;
    }*/
  /*  public UberTrip getTrip() {
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
    }*/
}
