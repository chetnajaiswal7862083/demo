package com.motrss.core.retrofit.response;

/**
 * Created by wel come on 25-08-2017.
 */

public class Authorize {
    private String url;
    public String getRedirectURL() {
        return url;
    }
    public void setRedirectURL(String url) {
        this.url = url;
    }


}
