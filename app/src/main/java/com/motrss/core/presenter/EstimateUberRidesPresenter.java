package com.motrss.core.presenter;

import com.motrss.core.retrofit.ApiInterface;
import com.motrss.core.retrofit.BaseCallback;
import com.motrss.core.retrofit.RetrofitConfig;
import com.motrss.core.retrofit.request.UberEstimateRequest;
import com.motrss.core.retrofit.response.UberEstimate;
import com.motrss.core.view.UberEstimateViewContract;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @Author Dhananjay Kulkarni.
 */

public class EstimateUberRidesPresenter extends BaseCallback<UberEstimate> {

    private UberEstimateViewContract contract;
    private UberEstimateRequest request;


    public EstimateUberRidesPresenter(UberEstimateViewContract contract, UberEstimateRequest request) {
        this.contract = contract;
        this.request = request;
    }

    public void start() {
        execute();
    }

    @Override
    protected void execute() {
// Uncomment  when you want------

       /* RetrofitConfig retrofitConfig = new RetrofitConfig(contract.getAppBaseContext());
        ApiInterface apiInterface = retrofitConfig.createService(ApiInterface.class);

        apiInterface.getUberEstimate(this.request).enqueue(new Callback<UberEstimate>() {
            @Override
            public void onResponse(Call<UberEstimate> call, Response<UberEstimate> response) {
                if(null != response && response.code() == 200){
                    contract.onUberEstimateSuccess(response.body());
                }else{
                    contract.onnUberEstimateFailure("[Error] Response Null..!!!");
                }
            }

            @Override
            public void onFailure(Call<UberEstimate> call, Throwable t) {
                t.printStackTrace();
                contract.onnUberEstimateFailure(t.getMessage());
            }
        });
        */
    }

    @Override
    protected void handleResponse(UberEstimate customResponse) {
        contract.onUberEstimateSuccess(customResponse);
    }

    @Override
    protected void handleError(String cause) {
        contract.onnUberEstimateFailure(cause);
    }
}
