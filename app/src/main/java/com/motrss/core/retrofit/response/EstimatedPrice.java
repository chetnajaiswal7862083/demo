package com.motrss.core.retrofit.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by Khushboo on 8/22/2017.
 */

public class EstimatedPrice {
    //String productId, eta, price, name;

    @JsonProperty("product_id")
    private String productId;

    @JsonProperty("duration")
    private String eta;
    @JsonProperty("estimate")
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
    private String currency_code;

    public EstimatedPrice(String productId, String eta, String price, String name, String localized_display_name, String distance, String high_estimate,
                          String low_estimate, String currency_code) {
        this.productId = productId;
        this.eta = eta;
        this.price = price;
        this.name = name;
        this.localized_display_name = localized_display_name;
        this.distance = distance;
        this.low_estimate = low_estimate;
        this.high_estimate = high_estimate;
        this.currency_code = currency_code;
    }

    /*  public EstimatedPrice(String statu) {
          this.status = statu;
      }
  */

      public EstimatedPrice() {

     }
    public String getlocalized_display_name() {
        return localized_display_name;
    }

    public String getdistance() {
        return distance;
    }

    public String getlow_estimate() {
        return low_estimate;
    }

    public String gethigh_estimate() {
        return high_estimate;
    }

    public String getcurrency_code() {
        return currency_code;
    }


    public void setlocalized_display_name(String localized_display_name) {
        this.localized_display_name = localized_display_name;
    }

    public void setdistance(String distance) {
        this.distance = distance;
    }

    public void setlow_estimate(String low_estimate) {
        this.low_estimate = low_estimate;
    }

    public void sethigh_estimate(String high_estimate) {
        this.high_estimate = high_estimate;
    }

    public void setcurrency_code(String currency_code) {
        this.currency_code = currency_code;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String pId) {
        this.productId = pId;
    }

    String status;


    public String getETA() {
        return eta;
    }

    public void setETA(String eta) {
        this.eta = eta;
    }

    //    first
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

  /*  public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    String farePrice;
    String time;
    String place;*/
}
