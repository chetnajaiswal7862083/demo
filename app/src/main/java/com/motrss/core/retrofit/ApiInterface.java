package com.motrss.core.retrofit;


import com.motrss.core.UberAuthTokenResponse;
import com.motrss.core.retrofit.request.UberCabRequest;
import com.motrss.core.retrofit.response.Authorize;
import com.motrss.core.retrofit.response.EstimatePriceGet;
import com.motrss.core.retrofit.response.ProductsMaster;
import com.motrss.core.retrofit.response.RequestEstimateFare;
import com.motrss.core.retrofit.response.UberRequest;

import java.net.URL;
import java.util.LinkedHashMap;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface ApiInterface {

   /* @GET("v2/authorize?")
    Call<UberAuthTokenResponse> getUberAuthToken(@QueryMap LinkedHashMap<String, String> map);
*/

    @GET("v2/authorize?")
    Call getUberAuthToken(@QueryMap LinkedHashMap<String, String> map);

    @GET("v1.2/products?")
    Call<ProductsMaster> getUberProducts(@QueryMap LinkedHashMap<String, String> map);

    /*@POST("v1.2/requests/estimate?")
    Call<UberEstimate> getUberestimate(@Body UberEstimateRequest request);
*/
    /*
        @GET("v1.2/estimates/time?")
        Call<EstimatePriceGet> getEstimatePrice(@Body UberEstimateRequest request);
    */
    @GET("v1.2/estimates/time?")
    Call<EstimatePriceGet> getEstimatePrice(@Query("start_latitude") String apiKey, @Query("start_longitude") String lng);


    @GET("v1.2/estimates/price?")
    Call<EstimatePriceGet> getUberEstimate(@Query("start_latitude") String apiKey, @Query("start_longitude") String lng, @Query("end_latitude") String apiKey1, @Query("end_longitude") String apiKey2);


    @POST("v1.2/requests")
    Call<UberRequest> requestUber(@Body UberCabRequest request);

    /*@GET("v1.2/requests/estimate?")
    Call<RequestEstimateFare> getRequestEstimateFare(@Query("start_latitude") String apiKey, @Query("start_longitude") String lng,@Query("end_latitude") String lng1,@Query("end_longitude") String lng2,@Query("product_id") String lng3);
*/

 /*   @POST("v1.2/requests/estimate?")
    Call<RequestEstimateFare> getRequestEstimateFare(@Header("Authorization") String auth,@Header("Content-Type") String auth1,@Query("product_id") String product_id, @Query("start_latitude") String apiKey, @Query("start_longitude") String lng, @Query("end_latitude") String lng1, @Query("end_longitude") String lng2,@Query("scopes") String lng3);
*/
//
    @POST("v1.2/requests/estimate?")
    Call<RequestEstimateFare> getRequestEstimateFare( @Query("start_latitude") String apiKey, @Query("start_longitude") String lng, @Query("end_latitude") String lng1, @Query("end_longitude") String lng2);


    @POST("oauth/v2/authorize?")
    Call<Authorize> getAuth(@Query("client_id") String apiKey, @Query("response_type") String lng);

  /*  @GET("oauth/v2/authorize?")
    Call<Authorize> getAuth(@QueryMap LinkedHashMap<String, String> map);
*/
   @FormUrlEncoded
    @POST("oauth/v2/token?")
    Call<UberAuthTokenResponse> getAuthToken(@Field("client_id") String scopes,  @Field("client_secret") String apiKey,  @Field("grant_type") String lng,  @Field("redirect_uri") String lng1,  @Field("code") String lng2);


    @FormUrlEncoded
    @POST("oauth/v2/token?")
    Call<UberAuthTokenResponse> getRefreshToken(@Field("client_id") String client_id, @Field("client_secret") String client_secret,@Field("grant_type") String grant_type,@Field("refresh_token") String refresh_token);



    @GET("oauth/v2/authorize?")
    Call<String> getAuthorizedCode(@Query("client_id") String client_id, @Query("response_type") String client_secret);

}
