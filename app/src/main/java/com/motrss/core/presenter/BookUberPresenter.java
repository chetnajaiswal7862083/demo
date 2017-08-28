package com.motrss.core.presenter;

import com.motrss.core.retrofit.ApiInterface;
import com.motrss.core.retrofit.BaseCallback;
import com.motrss.core.retrofit.RetrofitConfig;
import com.motrss.core.retrofit.request.UberCabRequest;
import com.motrss.core.retrofit.response.UberRequest;
import com.motrss.core.view.BookUberViewContract;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @Author Dhananjay Kulkarni.
 */

public class BookUberPresenter extends BaseCallback<UberRequest> {

    private BookUberViewContract contract;
    private UberCabRequest request;

    public BookUberPresenter(BookUberViewContract contract, UberCabRequest request) {
        this.contract = contract;
        this.request = request;
    }

    public void start(){
        execute();
    }

    @Override
    protected void execute() {
        final RetrofitConfig config = new RetrofitConfig(contract.getAppBaseContext());
        ApiInterface apiInterface = config.createService(ApiInterface.class);
        apiInterface.requestUber(this.request).enqueue(new Callback<UberRequest>() {
            @Override
            public void onResponse(Call<UberRequest> call, Response<UberRequest> response) {
                if(null != response && response.code() == 202){
                    contract.onBookUberSuccess(response.body());
                }else{
                    contract.onBookUberFailure("[Error] Response Null...!!");
                }
            }

            @Override
            public void onFailure(Call<UberRequest> call, Throwable t) {
                t.printStackTrace();
                contract.onBookUberFailure(t.getMessage());
            }
        });
    }

    @Override
    protected void handleResponse(UberRequest customResponse) {

    }

    @Override
    protected void handleError(String cause) {

    }
}
