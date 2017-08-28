package com.motrss.core.retrofit;


import android.content.Context;

import com.motrss.utils.PreferenceManager;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;


/**
 * This class initializes Retrofit with its configuration.
 */
public class RetrofitConfig implements NetworkConstants{

    private static final String AUTH = "Authorization";
    //public static String baseUrl = "https://api.uber.com/"; // Live
    public static String baseUrl = "https://sandbox-api.uber.com/"; // Sandbox

    private Context context;

    public RetrofitConfig(Context context) {
        this.context = context;
    }

    private static Retrofit.Builder builder =
            new Retrofit.Builder()
                    .addConverterFactory(JacksonConverterFactory.create())
                    .baseUrl("https://login.uber.com/oauth/");

    private static OkHttpClient.Builder httpClient;

    public static void changeApiBaseUrl(String newApiBaseUrl) {
        baseUrl = newApiBaseUrl;

        builder = new Retrofit.Builder()
                .addConverterFactory(JacksonConverterFactory.create())
                .baseUrl(baseUrl)
        ;
    }

    public <S> S createService(Class<S> serviceClass) {

        httpClient = new OkHttpClient()
                .newBuilder()

                .connectTimeout(API_TIME_OUT_MLSEC, TimeUnit.MILLISECONDS)
                .readTimeout(API_READ_TIME_OUT_MLSEC, TimeUnit.MILLISECONDS)
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request original = chain.request();

                        Request.Builder requestBuilder = original.newBuilder()
                               // .header("client_id","LWOUTh3AUBkVtaI-cK58-t_pspsvRFfk")
                              //  .header("Content-Type","")
                          // .header(AUTH, "Bearer MA.CAESEGJJqkwgLEKCqR9gIfvVUn0iATEoATIBMQ.7411kgC4HWthUDa1H6QGOw9cGk09fnoKYij1TnYXqvs.h7E1LQfFSi6C8SAqicIMzjXdSmKIdPOIhqGh_XRvGxM" );
                       .header(AUTH, "Bearer KA.eyJ2ZXJzaW9uIjoyLCJpZCI6InE1dG10WXRZVGIrMkR5K1V1MkVmN3c9PSIsImV4cGlyZXNfYXQiOjE1MDYxNjQxMzUsInBpcGVsaW5lX2tleV9pZCI6Ik1RPT0iLCJwaXBlbGluZV9pZCI6MX0.paz3ZYTvr2X68-UjtFwAclInVF4JaKxBLHdMgnxHuAg");
                        Request request = requestBuilder.build();
                        return chain.proceed(request);
                    }
                });

        Retrofit retrofit = builder
                .client(httpClient.build())
                .build();

        return retrofit.create(serviceClass);

    }

    public <S> S create_Service(Class<S> serviceClass) {

        httpClient = new OkHttpClient()
                .newBuilder()

                .connectTimeout(API_TIME_OUT_MLSEC, TimeUnit.MILLISECONDS)
                .readTimeout(API_READ_TIME_OUT_MLSEC, TimeUnit.MILLISECONDS)
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request original = chain.request();

                        Request.Builder requestBuilder = original.newBuilder();
                                // .header("client_id","LWOUTh3AUBkVtaI-cK58-t_pspsvRFfk")
                                //.header(AUTH, "Bearer " + new PreferenceManager(context).getUberAuthToken());
                             //   .header(AUTH, "Bearer KA.eyJ2ZXJzaW9uIjoyLCJpZCI6InE1dG10WXRZVGIrMkR5K1V1MkVmN3c9PSIsImV4cGlyZXNfYXQiOjE1MDYxNjQxMzUsInBpcGVsaW5lX2tleV9pZCI6Ik1RPT0iLCJwaXBlbGluZV9pZCI6MX0.paz3ZYTvr2X68-UjtFwAclInVF4JaKxBLHdMgnxHuAg");
                        Request request = requestBuilder.build();
                        return chain.proceed(request);
                    }
                });

        Retrofit retrofit = builder
                .client(httpClient.build())
                .build();

        return retrofit.create(serviceClass);

    }
}
