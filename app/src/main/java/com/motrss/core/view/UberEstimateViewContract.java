package com.motrss.core.view;

import com.motrss.core.retrofit.response.UberEstimate;
import com.uber.sdk.rides.client.model.RideEstimate;

/**
 * @Author Dhananjay Kulkarni.
 */

public interface UberEstimateViewContract extends BaseContract {

    void onUberEstimateSuccess(UberEstimate uberEstimate);

    void onnUberEstimateFailure(String cause);
}
