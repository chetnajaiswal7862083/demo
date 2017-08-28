package com.motrss.core.retrofit.response;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @Author Dhananjay Kulkarni.
 */

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY )
@JsonIgnoreProperties(ignoreUnknown = true)
public class Products {

    private String shared;

    private String display_name;

    private String upfront_fare_enabled;

    private String short_description;

    private String product_id;

    private String description;

    private String cash_enabled;

    private String image;

    private String capacity;

    private String product_group;

    public String getShared() {
        return shared;
    }

    public void setShared(String shared) {
        this.shared = shared;
    }

    public String getDisplay_name() {
        return display_name;
    }

    public void setDisplay_name(String display_name) {
        this.display_name = display_name;
    }

    public String getUpfront_fare_enabled() {
        return upfront_fare_enabled;
    }

    public void setUpfront_fare_enabled(String upfront_fare_enabled) {
        this.upfront_fare_enabled = upfront_fare_enabled;
    }

    public String getShort_description() {
        return short_description;
    }

    public void setShort_description(String short_description) {
        this.short_description = short_description;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCash_enabled() {
        return cash_enabled;
    }

    public void setCash_enabled(String cash_enabled) {
        this.cash_enabled = cash_enabled;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public String getProduct_group() {
        return product_group;
    }

    public void setProduct_group(String product_group) {
        this.product_group = product_group;
    }
}
