package com.motrss.core.view;

import android.content.Context;

import com.motrss.core.retrofit.response.UberRequest;

/**
 * @Author Dhananjay Kulkarni.
 */

public interface BookUberViewContract extends BaseContract{

    void onBookUberSuccess(UberRequest request);

    void onBookUberFailure(String cause);
}
