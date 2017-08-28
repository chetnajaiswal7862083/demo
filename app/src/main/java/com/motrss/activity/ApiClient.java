package com.motrss.activity;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
/**
 * Created by wel come on 24-08-2017.
 */

public class ApiClient {

    public static final String BASE_URL = "https://api.uber.com/";
    private static Retrofit retrofit = null;


    public static Retrofit getClient() {

        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        System.out.println("Check retrofit--->   "+retrofit.toString());
        return retrofit;
    }
}
