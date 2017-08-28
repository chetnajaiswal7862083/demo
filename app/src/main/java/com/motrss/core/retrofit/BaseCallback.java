package com.motrss.core.retrofit;

import com.motrss.R;
import com.motrss.core.MotrssApp;
import com.motrss.utils.NetworkUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @Author Dhananjay Kulkarni.
 */

public abstract class BaseCallback<RESPONSE> {

    private final static String TAG = BaseCallback.class.getSimpleName();

    private final Callback<RESPONSE> callback = new Callback<RESPONSE>() {
        @Override
        public void onResponse(Call<RESPONSE> call, Response<RESPONSE> response) {
            if(response.isSuccessful()){
                handleResponse(response.body());
            }else {
                handleError(response.message());
            }
        }

        @Override
        public void onFailure(Call<RESPONSE> call, Throwable t) {

            handleError(t.getMessage());
        }
    };

    public final void request(Callback<RESPONSE> callback){
        if(NetworkUtil.getConnectionType(MotrssApp.getInstance()) == NetworkUtil.NETWORK_STATUS_NOT_CONNECTED){
            handleError(MotrssApp.getInstance().getString(R.string.err_no_connection));
        }else{
            executeService(callback);
        }
    }

    protected void executeService(Callback<RESPONSE> callback){

    }

    protected abstract void execute();

    protected abstract void handleResponse(RESPONSE customResponse);

    protected abstract void handleError(String cause);
}
