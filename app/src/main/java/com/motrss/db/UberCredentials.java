package com.motrss.db;

import io.realm.RealmObject;

/**
 * @Author Dhananjay Kulkarni.
 */

public class UberCredentials extends RealmObject {

    private String client_id;
    private String client_secret;
    private String authToken;

    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    public String getClient_secret() {
        return client_secret;
    }

    public void setClient_secret(String client_secret) {
        this.client_secret = client_secret;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }
}
