package com.motrss.core.retrofit.response;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @Author Dhananjay Kulkarni.
 */

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY )
@JsonIgnoreProperties(ignoreUnknown = true)
public class Pickup {

    private String eta;

    private String longitude;

    private String latitude;

    public String getEta ()
    {
        return eta;
    }

    public void setEta (String eta)
    {
        this.eta = eta;
    }

    public String getLongitude ()
    {
        return longitude;
    }

    public void setLongitude (String longitude)
    {
        this.longitude = longitude;
    }

    public String getLatitude ()
    {
        return latitude;
    }

    public void setLatitude (String latitude)
    {
        this.latitude = latitude;
    }
}
