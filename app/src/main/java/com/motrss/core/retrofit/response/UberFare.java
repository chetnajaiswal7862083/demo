package com.motrss.core.retrofit.response;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @Author Dhananjay Kulkarni.
 */

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY )
@JsonIgnoreProperties(ignoreUnknown = true)
public class UberFare {

    private String expires_at;

    private String value;

    private String display;

    private String currency_code;

    private String fare_id;

    public String getExpires_at ()
    {
        return expires_at;
    }

    public void setExpires_at (String expires_at)
    {
        this.expires_at = expires_at;
    }

    public String getValue ()
    {
        return value;
    }

    public void setValue (String value)
    {
        this.value = value;
    }

    public String getDisplay ()
    {
        return display;
    }

    public void setDisplay (String display)
    {
        this.display = display;
    }

    public String getCurrency_code ()
    {
        return currency_code;
    }

    public void setCurrency_code (String currency_code)
    {
        this.currency_code = currency_code;
    }

    public String getFare_id ()
    {
        return fare_id;
    }

    public void setFare_id (String fare_id)
    {
        this.fare_id = fare_id;
    }
}
