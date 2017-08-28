package com.motrss.core.retrofit.response;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @Author Dhananjay Kulkarni.
 */

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY )
@JsonIgnoreProperties(ignoreUnknown = true)
public class UberTrip {

    private String distance_unit;

    private String distance_estimate;

    private String duration_estimate;

    public String getDistance_unit ()
    {
        return distance_unit;
    }

    public void setDistance_unit (String distance_unit)
    {
        this.distance_unit = distance_unit;
    }

    public String getDistance_estimate ()
    {
        return distance_estimate;
    }

    public void setDistance_estimate (String distance_estimate)
    {
        this.distance_estimate = distance_estimate;
    }

    public String getDuration_estimate ()
    {
        return duration_estimate;
    }

    public void setDuration_estimate (String duration_estimate)
    {
        this.duration_estimate = duration_estimate;
    }
}
