package com.motrss.core.retrofit.response;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Khushboo on 8/22/2017.
 */

public class EstimatedFareDetail {
    //String productId, eta, price, name;

    @JsonProperty("value")
    private String value;

    @JsonProperty("fare_id")
    private String fare_id;
   /* @JsonProperty("estimate")
    private String price;

    @JsonProperty("display_name")
    private String name;

    @JsonProperty("localized_display_name")
    private String localized_display_name;

    @JsonProperty("distance")
    private String distance;

    @JsonProperty("high_estimate")
    private String high_estimate;

    @JsonProperty("low_estimate")
    private String low_estimate;

    @JsonProperty("currency_code")
    private String currency_code;*/

    public EstimatedFareDetail(String value, String fare_id) {
        this.value = value;
        this.fare_id = fare_id;
       /* this.price = price;
        this.name = name;
        this.localized_display_name = localized_display_name;
        this.distance = distance;
        this.low_estimate = low_estimate;
        this.high_estimate = high_estimate;
        this.currency_code = currency_code;*/
    }

    /*  public EstimatedPrice(String statu) {
          this.status = statu;
      }
  */

      public EstimatedFareDetail() {

     }


    public String getValue() {
        return value;
    }

   /* public void setValue(String value) {
        this.value = value;
    }

    String status;
*/

    public String getFareId() {
        return fare_id;
    }

   /* public void setFareId(String fare_id) {
        this.fare_id = fare_id;
    }
*/
 /*   //    first
    int image;

    public String getFarePrice() {
        return price;
    }

    public void setFarePrice(String farePrice) {
        this.price = farePrice;
    }

    public String getName() {
        return name;
    }

    public void setName(String nm) {
        this.name = nm;
    }

  *//*  public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    String farePrice;
    String time;
    String place;*/
}
