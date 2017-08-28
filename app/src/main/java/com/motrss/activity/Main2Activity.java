package com.motrss.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.motrss.R;
import com.uber.sdk.android.core.UberSdk;
import com.uber.sdk.android.core.auth.AccessTokenManager;
import com.uber.sdk.android.core.auth.AuthenticationError;
import com.uber.sdk.android.core.auth.LoginCallback;
import com.uber.sdk.android.core.auth.LoginManager;
import com.uber.sdk.core.auth.AccessToken;
import com.uber.sdk.core.auth.Scope;
import com.uber.sdk.rides.client.SessionConfiguration;

import java.util.Arrays;

public class Main2Activity extends AppCompatActivity {
    Button firstButton,secondButton;
    LoginManager loginManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1
        );
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        firstButton= (Button) findViewById(R.id.button);
        secondButton= (Button) findViewById(R.id.button2);
        firstButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SessionConfiguration config = new SessionConfiguration.Builder()
                        // mandatory
                        .setClientId("LWOUTh3AUBkVtaI-cK58-t_pspsvRFfk")
                        .setClientSecret("wI2gmJSaSto6d6rGePY3V4MC7VMxz9edDsF1t6xn")
                    //    .setRedirectUri("https://www.google.com")
                       // .setScopes(Arrays.asList(Scope.PROFILE))
                        // required scope for Ride Request Widget features
                        .setScopes(Arrays.asList(Scope.PROFILE, Scope.REQUEST))
                        // optional: set Sandbox as operating environment
                        .setEnvironment(SessionConfiguration.Environment.SANDBOX)
                        .build();
                UberSdk.initialize(config);
                LoginCallback loginCallback = new LoginCallback() {
                    @Override
                    public void onLoginCancel() {
                        // User canceled login
                        System.out.println("Login cancel-->   ");
                    }

                    @Override
                    public void onLoginError(@NonNull AuthenticationError error) {
                        System.out.println("Login error is-->   "+error);
                    }

                    @Override
                    public void onLoginSuccess(@NonNull AccessToken accessToken) {
                        System.out.println("login access token is--->   "+accessToken);
                    }

                    @Override
                    public void onAuthorizationCodeReceived(@NonNull String authorizationCode) {
                        System.out.println("Login authorization code -->  "+authorizationCode);
                    }

                };
                AccessTokenManager accessTokenManager = new AccessTokenManager(Main2Activity.this);
                 loginManager = new LoginManager(accessTokenManager, loginCallback);
                loginManager.login(Main2Activity.this);
            }
        });
        secondButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // startActivity(new Intent(Main2Activity.this,SecondScreenActivity.class));
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        System.out.println("request code-->   "+requestCode);
        loginManager.onActivityResult(Main2Activity.this, requestCode, resultCode, data);
    }



}
