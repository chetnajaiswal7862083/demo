package com.motrss.activity;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.motrss.R;
import com.motrss.adapters.UberProductsAdapter;
import com.motrss.core.UberAuthTokenResponse;
import com.motrss.core.presenter.BookUberPresenter;
import com.motrss.core.presenter.EstimateUberRidesPresenter;
import com.motrss.core.presenter.GetUberProductsPresenter;
import com.motrss.core.retrofit.ApiInterface;
import com.motrss.core.retrofit.RetrofitConfig;
import com.motrss.core.retrofit.request.UberCabRequest;
import com.motrss.core.retrofit.request.UberEstimateRequest;
import com.motrss.core.retrofit.response.Authorize;
import com.motrss.core.retrofit.response.Products;
import com.motrss.core.retrofit.response.RequestEstimateFare;
import com.motrss.core.retrofit.response.UberEstimate;
import com.motrss.core.retrofit.response.UberRequest;
import com.motrss.core.view.BookUberViewContract;
import com.motrss.core.view.GetUberProductsViewContract;
import com.motrss.core.view.UberEstimateViewContract;
import com.motrss.global_supporting_items.Global_Data;
import com.motrss.sharedpreference.SharedPref;
import com.motrss.uber.UberConfig;
import com.uber.sdk.android.core.auth.LoginButton;
import com.uber.sdk.rides.client.model.RideEstimate;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookUberActivity extends AppCompatActivity implements
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener,
        GetUberProductsViewContract,
        UberEstimateViewContract,
        UberProductsAdapter.onClickListener,
        BookUberViewContract, OnMapReadyCallback, TextView.OnEditorActionListener,
        View.OnClickListener {

    private static final String TAG = BookUberActivity.class.getSimpleName();
    private static final int GPS_CODE = 101;
    private static final int GPS_PERMISSION_CODE = 102;
    private static final long LOCATION_INTERVAL = 10000;
    private static final long LOCATION_FASTEST_INTERVAL = 5000;
    private GoogleApiClient mGoogleApiClient;
    private LocationRequest mLocationRequest;
    private AlertDialog dialog;
    private RecyclerView rvUberProducts;
    private double mLatitude, mLongitude;
    private GoogleMap googleMap;
    private Geocoder mGeocoder;
    private EditText etSource, etDestination;
    private ImageView ivCurrentLocation;
    private LatLng mSourceLatLng, mDestLatLng;
    private boolean isSourceClicked, isDestClicked;
    private LoginButton btnUberLogin;

    RetrofitConfig retrofitConfig;
    ApiInterface apiInterface;
    private UberConfig uberConfig;
    private Global_Data globalData;
    private SharedPref sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_uber);
        mGeocoder = new Geocoder(this, Locale.US);
        checkPermission();
        createLocationRequest();
        initUI();
        initToolbar();
        globalData = new Global_Data();
        sharedPref = new SharedPref(BookUberActivity.this);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            if (getIntent().getExtras().getString("book").equals("yes")) {
                uberConfig = new UberConfig(this, BookUberActivity.this);
                uberConfig.initializeUber();


                ProgressDialog pd = new ProgressDialog(BookUberActivity.this);


                pd.show();
                pd.setContentView(R.layout.popu_design);
                LoginButton btnUberLogin = (LoginButton) pd.findViewById(R.id.btn_uber_login1);


                btnUberLogin.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        uberConfig.getLoginManager().login(BookUberActivity.this);


                       /* retrofitConfig = new RetrofitConfig(BookUberActivity.this);
                        retrofitConfig.changeApiBaseUrl("https://api.uber.com/");
                        apiInterface = retrofitConfig.createService(ApiInterface.class);

                        retrofitConfig.changeApiBaseUrl("https://api.uber.com/");
                        apiInterface.getRequestEstimateFare("request",getIntent().getExtras().getString("product_id"), "37.7752315", "-122.418075", "37.7752415", "-122.518075").enqueue(new Callback<RequestEstimateFare>() {
                            @Override
                            public void onResponse(Call<RequestEstimateFare> call, Response<RequestEstimateFare> response) {
                                System.out.println("getRequestEstimateFare call---  --- in activity>>>   " + response.body() + "   /   " + call.toString());
                          //      System.out.println("Response of getRequestEstimateFare--- in activity>>>   " + response.body().getFare());
                           *//* // estimatedPriceList = response.body().getFare();
                            System.out.println("Size of list---   " + estimatedPriceList.size());
                            FirstAdapter mAdapter = new FirstAdapter(estimatedPriceList);
                            recyclerView.setAdapter(mAdapter);*//*
                                // contract.onUberProductsSuccess(response.body());
                            }

                            @Override
                            public void onFailure(Call<RequestEstimateFare> call, Throwable t) {
                                System.out.println("ttt---->   " + t.toString());
                            }

                        });*/


                        retrofitConfig = new RetrofitConfig(BookUberActivity.this);
                        retrofitConfig.changeApiBaseUrl("https://login.uber.com/");
                        apiInterface = retrofitConfig.create_Service(ApiInterface.class);
                        LinkedHashMap<String, String> map1 = new LinkedHashMap<>();
                        map1.put("client_id", "LWOUTh3AUBkVtaI-cK58-t_pspsvRFfk");
                        map1.put("response_type", "code");
                        //   System.out.println();
                        /* apiInterface.getAuth("LWOUTh3AUBkVtaI-cK58-t_pspsvRFfk","code").enqueue(new Callback<Authorize>() {
                            @Override
                            public void onResponse(Call<Authorize> call, Response<Authorize> response) {
                                System.out.println("getRequestEstimateFare call---  --- in activity>>>   " + response.body() + "   /   " + call.toString());

                            }

                            @Override
                            public void onFailure(Call<Authorize> call, Throwable t) {
                                System.out.println("ttt---->   " + t.toString()+"  /   call-->  "+call.request());
                            }

                        });*/
                        if (!sharedPref.getBooleanData(globalData.getAccessToken)) {
                            retrofitConfig.changeApiBaseUrl("https://login.uber.com/");

                            apiInterface.getAuthToken("LWOUTh3AUBkVtaI-cK58-t_pspsvRFfk", "wI2gmJSaSto6d6rGePY3V4MC7VMxz9edDsF1t6xn", "authorization_code", "https://www.motrss.com", "8ff1nHxTBUVwMK8PdZqueGEH9hYPEy#_").enqueue(new Callback<UberAuthTokenResponse>() {
                                @Override
                                public void onResponse(Call<UberAuthTokenResponse> call, Response<UberAuthTokenResponse> response) {
                                    System.out.println("getRequestEstimateFare call---  --- in activity>>>   " + response.body() + "   /   " + call.request().toString());

                                    if (response.body().getRefresh_token() != null) {
                                        refreshToken(response.body().getRefresh_token());
                                        sharedPref.setBooleanData(globalData.getAccessToken, true);
                                    }
                                }

                                @Override
                                public void onFailure(Call<UberAuthTokenResponse> call, Throwable t) {
                                    System.out.println("ttt---->   " + t.toString() + "   /   " + call.request().toString());
                                }

                            });
                        }else {
                            retrofitConfig.changeApiBaseUrl("https://api.uber.com/");
                            apiInterface = retrofitConfig.createService(ApiInterface.class);

                            retrofitConfig.changeApiBaseUrl("https://api.uber.com/");
                            apiInterface.getRequestEstimateFare("37.7752315", "-122.418075", "37.7752415", "-122.518075").enqueue(new Callback<RequestEstimateFare>() {
                                @Override
                                public void onResponse(Call<RequestEstimateFare> call, Response<RequestEstimateFare> response) {
                                    System.out.println("getRequestEstimateFare call---  --- in activity>>>   " + response.body() + "   /   " + call.request().toString());

                                    /*System.out.println("Size of list---   " + estimatedPriceList.size());
                                    FirstAdapter mAdapter = new FirstAdapter(estimatedPriceList);
                                    recyclerView.setAdapter(mAdapter);*/

                                }

                                @Override
                                public void onFailure(Call<RequestEstimateFare> call, Throwable t) {
                                    System.out.println("ttt---->   " + t.toString());
                                }

                            });


                        }
                    }
                });

            }

            bundle = null;
        }


    }

    private void refreshToken(String refreshToken) {

        apiInterface.getRefreshToken("LWOUTh3AUBkVtaI-cK58-t_pspsvRFfk", "wI2gmJSaSto6d6rGePY3V4MC7VMxz9edDsF1t6xn", "refresh_token", refreshToken).enqueue(new Callback<UberAuthTokenResponse>() {
            @Override
            public void onResponse(Call<UberAuthTokenResponse> call, Response<UberAuthTokenResponse> response) {
                System.out.println("getRefreshToken call---  ---    " + response.body() + "   /   " + call.request().toString());
                sharedPref.setBooleanData(globalData.getRefreshToken, true);
            }

            @Override
            public void onFailure(Call<UberAuthTokenResponse> call, Throwable t) {
                System.out.println("ttt---->   " + t.toString() + "   /   " + call.request().toString());
            }

        });
    }


    public void getAuthorizedCode() {
       /* Intent viewIntent =
                new Intent("android.intent.action.VIEW",
                        Uri.parse("https://login.uber.com/oauth/v2/authorize?client_id=LWOUTh3AUBkVtaI-cK58-t_pspsvRFfk&response_type=code"));
        startActivity(viewIntent);*/

        apiInterface.getAuthorizedCode("LWOUTh3AUBkVtaI-cK58-t_pspsvRFfk", "code").enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                System.out.println("getRefreshToken call---  ---    " + response.body() + "   /   " + call.request().toString());

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                System.out.println("ttt---->   " + t.toString() + "   /   " + call.request().toString());
            }

        });
    }


    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        TextView tvBook = (TextView) findViewById(R.id.tv_text);
        tvBook.setText("Book");
        tvBook.setOnClickListener(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
    }


    private void initUI() {
        rvUberProducts = (RecyclerView) findViewById(R.id.rv_uber_products);
        etSource = (EditText) findViewById(R.id.et_source);
        etDestination = (EditText) findViewById(R.id.et_desination);
        ivCurrentLocation = (ImageView) findViewById(R.id.iv_current_location);

        SupportMapFragment fragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        fragment.getMapAsync(this);

        etSource.setOnEditorActionListener(this);
        etDestination.setOnEditorActionListener(this);
        ivCurrentLocation.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_current_location:
                if (mGoogleApiClient.isConnected()) {
                    startLocationUpdates();
                }
                break;

            case R.id.et_source:
                break;

            case R.id.et_desination:
                break;

            case R.id.tv_text:
                if (mSourceLatLng != null) {
                    Toast.makeText(BookUberActivity.this, "Select Source", Toast.LENGTH_SHORT).show();
                } else if (mDestLatLng != null) {
                    Toast.makeText(BookUberActivity.this, "Select Destination", Toast.LENGTH_SHORT).show();
                } else {
                  /*  LinkedHashMap<String, String> map = new LinkedHashMap<>();
                    map.put("latitude", "22.7196");
                    map.put("longitude", "75.8577");
*/
                    Intent intent = new Intent(this, FirstScreenActivity.class);
                    startActivity(intent);

                    /*LinkedHashMap<String, String> map = new LinkedHashMap<>();
                    map.put("client_id", "LWOUTh3AUBkVtaI-cK58-t_pspsvRFfk");
                    map.put("response_type", "code");

                    GetUberProductsPresenter presenter = new GetUberProductsPresenter(this, map);
                    presenter.start();
*/
                }
                break;

        }
    }

    @Override
    public boolean onEditorAction(TextView textView, int actionCode, KeyEvent keyEvent) {
        if (actionCode == EditorInfo.IME_ACTION_DONE) {
            switch (textView.getId()) {
                case R.id.et_source:
                    isSourceClicked = true;
                    isDestClicked = false;
                    findLocationByName(etSource.getText().toString());
                    break;
                case R.id.et_desination:
                    isDestClicked = true;
                    isSourceClicked = false;
                    findLocationByName(etDestination.getText().toString());
                    break;
            }
            return true;
        }
        return false;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        Log.i(TAG, "GoogleMap Initiated");
        this.googleMap = googleMap;
        LatLng latLng = new LatLng(mLatitude, mLongitude);
        this.googleMap.addMarker(new MarkerOptions().position(latLng));
        this.googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 16F));
    }

    private void checkPermission() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, GPS_PERMISSION_CODE);
        } else {
            initLocationRequest();
        }
    }

    private void initLocationRequest() {
        connectGoogleAPIClient();
    }

    private void createLocationRequest() {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(LOCATION_INTERVAL);
        mLocationRequest.setFastestInterval(LOCATION_FASTEST_INTERVAL);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
    }

    private void connectGoogleAPIClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(BookUberActivity.this)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();

        mGoogleApiClient.connect();
    }

    private void startLocationUpdates() {
        //checkPermission();
        LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);

    }

    private void stopLocationUpdates() {
        LocationServices.FusedLocationApi.removeLocationUpdates(
                mGoogleApiClient, this);
        Log.d(TAG, "Stopping location updates");
    }

    private boolean isGPSEnabled() {
        LocationManager manager = (LocationManager) getSystemService(LOCATION_SERVICE);
        return manager.isProviderEnabled(LocationManager.GPS_PROVIDER);
    }

    private void createConfirmationDialog(String dialogmessage) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(BookUberActivity.this);
        builder.setTitle("Confirmation");
        builder.setCancelable(true);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivityForResult(intent, GPS_CODE);
            }
        });
        builder.setNeutralButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                Toast.makeText(BookUberActivity.this, getString(R.string.gps_not_enabled), Toast.LENGTH_SHORT).show();
            }
        });
        builder.setMessage(dialogmessage);
        dialog = builder.create();
        dialog.show();
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        Log.i(TAG, "onConnected");
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onLocationChanged(Location location) {
        Log.i(TAG, "" + location.getLatitude());
        Log.i(TAG, "" + location.getLongitude());
        findLocationFromCoordinates(location.getLatitude(), location.getLongitude());
        stopLocationUpdates();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == GPS_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Log.i(TAG, "Permission Granted");
                if (!isGPSEnabled()) {
                    createConfirmationDialog(getString(R.string.label_enable_gps));
                } else {
                    initLocationRequest();
                }
            } else {
                Log.i(TAG, "Permission Denied");
                Toast.makeText(this, "App will not work perfectly without location access", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GPS_CODE) {
            if (!isGPSEnabled()) {
                createConfirmationDialog(getString(R.string.label_enable_gps));
            } else {
                initLocationRequest();
            }
        } else {
            uberConfig.getLoginManager().onActivityResult(BookUberActivity.this, requestCode, resultCode, data);
        }
    }

    @Override
    public Context getAppBaseContext() {
        return BookUberActivity.this;
    }

    @Override
    public void onUberProductsSuccess(ArrayList<Products> customResponse) {
        UberProductsAdapter adapter = new UberProductsAdapter();
        adapter.setData(customResponse);
        adapter.setClickListener(this);
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true);
        rvUberProducts.setLayoutManager(manager);
        rvUberProducts.setAdapter(adapter);
    }


    @Override
    public void onUberProductsFailure(String cause) {
        Log.i(TAG, "[Failure] : " + cause);
    }

    @Override
    public void onUberEstimateSuccess(UberEstimate estimate) {
        Log.i(TAG, "onUberEstimateSuccess : " + estimate.getPickup_estimate());

        UberCabRequest request = new UberCabRequest();
        request.setFare_id(estimate.getFare().getFare_id());
        request.setStart_latitude(String.valueOf(mSourceLatLng.latitude));
        request.setStart_longitude(String.valueOf(mSourceLatLng.longitude));
        request.setEnd_latitude(String.valueOf(mDestLatLng.latitude));
        request.setEnd_longitude(String.valueOf(mDestLatLng.longitude));

        BookUberPresenter bookUberPresenter = new BookUberPresenter(this, request);
        bookUberPresenter.start();
    }

    @Override
    public void onnUberEstimateFailure(String cause) {
        Log.i(TAG, "onnUberEstimateFailure : " + cause);
    }

    @Override
    public void onUberProductClick(Products product) {
    /*    UberEstimateRequest request = new UberEstimateRequest();
        request.setProduct_id(product.getProduct_id());
        request.setStart_latitude(String.valueOf(mSourceLatLng.latitude));
        request.setStart_longitude(String.valueOf(mSourceLatLng.longitude));
        request.setEnd_latitude(String.valueOf(mDestLatLng.latitude));
        request.setEnd_longitude(String.valueOf(mDestLatLng.longitude));

        EstimateUberRidesPresenter presenter = new EstimateUberRidesPresenter(this, request);
        presenter.start();*/
    }

    @Override
    public void onBookUberSuccess(UberRequest request) {
        Log.i(TAG, "Uber Booked : ETA :" + request.getPickup().getEta());
        Toast.makeText(this, "Uber Booked Successfully..!!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBookUberFailure(String cause) {
        Log.i(TAG, "Uber Book Failure..." + cause);
    }

    private void findLocationByName(String name) {
        List<Address> list;
        try {
            list = mGeocoder.getFromLocationName(name, 1);
            Address address = list.get(0);
            Log.i(TAG, "Geocoder Address Success");
            Log.i(TAG, address.getCountryName());
            Log.i(TAG, address.getFeatureName());

            if (isSourceClicked) {
                mSourceLatLng = new LatLng(address.getLatitude(), address.getLongitude());
            } else if (isDestClicked) {
                mDestLatLng = new LatLng(address.getLatitude(), address.getLongitude());
            }

            LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());
            this.googleMap.addMarker(new MarkerOptions().position(latLng));
            this.googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 16F));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void findLocationFromCoordinates(double lat, double lng) {
        List<Address> list;
        try {
            list = mGeocoder.getFromLocation(lat, lng, 1);
            Address address = list.get(0);
            Log.i(TAG, "Geocoder LatLng Success");
            Log.i(TAG, address.getCountryName());
            Log.i(TAG, address.getFeatureName());
            mSourceLatLng = new LatLng(address.getLatitude(), address.getLongitude());
            etSource.setText(address.getFeatureName() + ", " + address.getCountryName());
            LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());
            this.googleMap.addMarker(new MarkerOptions().position(latLng));
            this.googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 16F));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
