package com.motrss.core.retrofit.response;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * @Author Dhananjay Kulkarni.
 */

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY )
@JsonIgnoreProperties(ignoreUnknown = true)
public class EstimatePriceGet {

    @JsonProperty("prices")
    private List<EstimatedPrice> pricesList;




    private String pickup_estimate;

    public List<EstimatedPrice> getPrice() {
        return pricesList;
    }

  /*  public void setFare(List<EstimatedPrice> priceList) {
        this.pricesList=priceList;
    }
*/
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
