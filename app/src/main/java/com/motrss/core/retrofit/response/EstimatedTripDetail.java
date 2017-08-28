package com.motrss.core.retrofit.response;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Khushboo on 8/22/2017.
 */

public class EstimatedTripDetail {
    //String productId, eta, price, name;

    @JsonProperty("value")
    private String value;

    @JsonProperty("type")
    private String type;
    @JsonProperty("name")
    private String name;

   /* @JsonProperty("display_name")
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

    public EstimatedTripDetail(String value, String type,String name) {
        this.value = value;
        this.type = type;
        this.name=name;
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

      public EstimatedTripDetail() {

     }


    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    String status;


    public String gettype() {
        return type;
    }

    public void settype(String fare_id) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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
