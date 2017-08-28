package com.motrss.uber;


import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.motrss.activity.Main2Activity;
import com.motrss.utils.PreferenceManager;
import com.uber.sdk.android.core.UberSdk;
import com.uber.sdk.core.auth.Scope;
import com.uber.sdk.rides.client.SessionConfiguration;
import com.uber.sdk.android.core.UberSdk;
import com.uber.sdk.android.core.auth.AccessTokenManager;
import com.uber.sdk.android.core.auth.AuthenticationError;
import com.uber.sdk.android.core.auth.LoginCallback;
import com.uber.sdk.android.core.auth.LoginManager;
import com.uber.sdk.core.auth.AccessToken;
import com.uber.sdk.core.auth.Scope;
import com.uber.sdk.rides.client.Session;
import com.uber.sdk.rides.client.SessionConfiguration;
import com.uber.sdk.rides.client.UberRidesApi;
import com.uber.sdk.rides.client.services.RidesService;

import java.util.Arrays;

/**
 * @Author Dhananjay Kulkarni.
 */

public class UberConfig {

    private static final String TAG = UberConfig.class.getSimpleName();
    private static final int LOGIN_CODE = 901;
    private LoginManager loginManager;
    private SessionConfiguration configuration;
    private AccessTokenManager accessTokenManager;
    private Context context;
    Activity activity;

    public UberConfig(Context context, Activity activity) {
        this.context = context;
        this.activity = activity;
    }

    public void initializeUber() {
        if (!UberSdk.isInitialized()) {
            configuration = new SessionConfiguration.Builder()
                    .setClientId("LWOUTh3AUBkVtaI-cK58-t_pspsvRFfk")
                    .setServerToken("J5MNweewRs8vj4-dC0r9OMI4-qjibix0xv6gncGs")
                    //  .setClientSecret("wI2gmJSaSto6d6rGePY3V4MC7VMxz9edDsF1t6xn")

                    .setRedirectUri("https://www.motrss.com")
                    .setScopes(Arrays.asList(Scope.REQUEST))
                    .setEnvironment(SessionConfiguration.Environment.SANDBOX)
                    .build();

            UberSdk.initialize(configuration);


            accessTokenManager = new AccessTokenManager(context);
            System.out.println("Configuration-  ---->   "+configuration.toString());
            loginManager = new LoginManager(accessTokenManager, new UberLoginCallback(context), configuration, LOGIN_CODE);
// System.out.println("Is authenticated--  "+loginManager.isAuthenticated());
      /*      AccessTokenManager accessTokenManager = new AccessTokenManager(context);
            System.out.println("Login callback-->   "+loginCallback.toString());
            LoginManager loginManager = new LoginManager(accessTokenManager, loginCallback);
            loginManager.login(activity);
            System.out.println("Is authenticated--  "+loginManager.isAuthenticated());
            *//*Session session = loginManager.getSession();
            RidesService service = UberRidesApi.with(session).build().createService();*//*
      */
        } else {
            Log.i(TAG, "Uber SDK already initialized");
        }

        //    System.out.println("Is authenticated--  "+loginManager.getAccessTokenManager().getAccessToken());
    }

    public LoginManager getLoginManager() {
      //  new PreferenceManager(context).setUberAuthToken(loginManager.getAccessTokenManager().getAccessToken().toString());
        System.out.println("Is authenticated--  " + loginManager.getAccessTokenManager().getAccessToken());
        return loginManager;
    }

    public void setLoginManager(LoginManager loginManager) {
        this.loginManager = loginManager;
    }
}
