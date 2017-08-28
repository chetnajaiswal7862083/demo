package com.motrss.core.retrofit.request;

/**
 * @Author Dhananjay Kulkarni.
 */

public class UberEstimateRequest {

    private String product_id;
    private String start_latitude;
    private String start_longitude;
    private String end_latitude;
    private String end_longitude;

   /* public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }
*/
    public String getStart_latitude() {
        return start_latitude;
    }

    public void setStart_latitude(String start_latitude) {
        this.start_latitude = start_latitude;
    }

    public String getStart_longitude() {
        return start_longitude;
    }

    public void setStart_longitude(String start_longitude) {
        this.start_longitude = start_longitude;
    }

  /*  public String getEnd_latitude() {
        return end_latitude;
    }

    public void setEnd_latitude(String end_latitude) {
        this.end_latitude = end_latitude;
    }

    public String getEnd_longitude() {
        return end_longitude;
    }

    public void setEnd_longitude(String end_longitude) {
        this.end_longitude = end_longitude;
    }*/
}
