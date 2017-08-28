package com.motrss.core.retrofit.response;

/**
 * @Author Dhananjay Kulkarni.
 */

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY )
@JsonIgnoreProperties(ignoreUnknown = true)
public class UberRequest {

    private String shared;

    private String request_id;

    private Pickup pickup;

    private String product_id;

    private String location;

    private String status;

    private String vehicle;

    private String driver;

    private Destination destination;

    public String getShared ()
    {
        return shared;
    }

    public void setShared (String shared)
    {
        this.shared = shared;
    }

    public String getRequest_id ()
    {
        return request_id;
    }

    public void setRequest_id (String request_id)
    {
        this.request_id = request_id;
    }

    public Pickup getPickup ()
    {
        return pickup;
    }

    public void setPickup (Pickup pickup)
    {
        this.pickup = pickup;
    }

    public String getProduct_id ()
    {
        return product_id;
    }

    public void setProduct_id (String product_id)
    {
        this.product_id = product_id;
    }

    public String getLocation ()
    {
        return location;
    }

    public void setLocation (String location)
    {
        this.location = location;
    }

    public String getStatus ()
    {
        return status;
    }

    public void setStatus (String status)
    {
        this.status = status;
    }

    public String getVehicle ()
    {
        return vehicle;
    }

    public void setVehicle (String vehicle)
    {
        this.vehicle = vehicle;
    }

    public String getDriver ()
    {
        return driver;
    }

    public void setDriver (String driver)
    {
        this.driver = driver;
    }

    public Destination getDestination ()
    {
        return destination;
    }

    public void setDestination (Destination destination)
    {
        this.destination = destination;
    }
}
