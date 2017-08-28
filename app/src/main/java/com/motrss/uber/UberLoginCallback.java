package com.motrss.uber;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.motrss.utils.PreferenceManager;
import com.uber.sdk.android.core.auth.AuthenticationError;
import com.uber.sdk.android.core.auth.LoginCallback;
import com.uber.sdk.core.auth.AccessToken;


/**
 * @Author Dhananjay Kulkarni.
 */

public class UberLoginCallback implements LoginCallback {

    private static final String TAG = UberLoginCallback.class.getSimpleName();
    private Context context;

    public UberLoginCallback(Context context) {
        this.context = context;
    }

    @Override
    public void onLoginCancel() {
        // User canceled login
        Log.i(TAG, "onLoginCancel");
        new PreferenceManager(context).setUberAuthToken("");
    }

    @Override
    public void onLoginError(@NonNull AuthenticationError error) {
        // Error occurred during login
        Log.i(TAG, "onLoginError : " +error.toStandardString());
        new PreferenceManager(context).setUberAuthToken("");
    }

    @Override
    public void onLoginSuccess(@NonNull AccessToken accessToken) {
        // Successful login!  The AccessToken will have already been saved.

        Log.i(TAG, "onLoginSuccess : " +accessToken.getToken());
        new PreferenceManager(context).setUberAuthToken(accessToken.getToken());
    }

    @Override
    public void onAuthorizationCodeReceived(@NonNull String authorizationCode) {
        Log.i(TAG, "onAuthorizationCodeReceived : " +authorizationCode);
    }
}
