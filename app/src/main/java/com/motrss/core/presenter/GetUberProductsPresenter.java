package com.motrss.core.presenter;

import com.motrss.core.UberAuthTokenResponse;
import com.motrss.core.retrofit.ApiInterface;
import com.motrss.core.retrofit.BaseCallback;
import com.motrss.core.retrofit.RetrofitConfig;
import com.motrss.core.retrofit.response.Products;
import com.motrss.core.retrofit.response.ProductsMaster;
import com.motrss.core.view.GetUberProductsViewContract;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @Author Dhananjay Kulkarni.
 */

public class GetUberProductsPresenter extends BaseCallback<ProductsMaster> {

    private GetUberProductsViewContract contract;
    private Callback<ProductsMaster> callback;
    LinkedHashMap<String, String> map;

    public GetUberProductsPresenter(GetUberProductsViewContract contract, LinkedHashMap<String, String> map) {
        this.contract = contract;
        this.map = map;
    }

    public void start() {
        execute();
    }

    @Override
    protected void executeService(Callback<ProductsMaster> callback) {
        this.callback = callback;
    }

    @Override
    protected void execute() {
        RetrofitConfig retrofitConfig = new RetrofitConfig(contract.getAppBaseContext());
        retrofitConfig.changeApiBaseUrl("https://sandbox-api.uber.com/");
        ApiInterface apiInterface = retrofitConfig.createService(ApiInterface.class);
        apiInterface.getUberAuthToken(this.map).enqueue(new Callback<UberAuthTokenResponse>() {
            @Override
            public void onResponse(Call<UberAuthTokenResponse> call, Response<UberAuthTokenResponse> response) {
                System.out.println("Auth Response---->>>   " + response.body());
            }

            @Override
            public void onFailure(Call<UberAuthTokenResponse> call, Throwable t) {

            }
        });
        LinkedHashMap<String, String> map1 = new LinkedHashMap<>();
        map1.put("latitude", "22.7196");
        map1.put("longitude", "75.8577");
        retrofitConfig.changeApiBaseUrl("https://api.uber.com/");
        apiInterface.getUberProducts(map1).enqueue(new Callback<ProductsMaster>() {
            @Override
            public void onResponse(Call<ProductsMaster> call, Response<ProductsMaster> response) {
                System.out.println("Response---->>>   " + response.body().getList().get(0).getProduct_id());
                contract.onUberProductsSuccess(response.body().getList());
            }

            @Override
            public void onFailure(Call<ProductsMaster> call, Throwable t) {
                contract.onUberProductsFailure(t.getMessage());
            }
        });
    }

    @Override
    protected void handleResponse(ProductsMaster customResponse) {

    }

    @Override
    protected void handleError(String cause) {

    }


}
