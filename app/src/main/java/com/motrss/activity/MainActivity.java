package com.motrss.activity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.motrss.R;
import com.motrss.core.presenter.GetUberProductsPresenter;
import com.motrss.core.retrofit.response.Products;
import com.motrss.core.view.GetUberProductsViewContract;
import com.motrss.uber.UberConfig;
import com.uber.sdk.android.core.auth.LoginButton;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class MainActivity extends BaseActivity implements View.OnClickListener,
        GetUberProductsViewContract{

    private static final String TAG = MainActivity.class.getSimpleName();
    private LoginButton btnUberLogin;
    private UberConfig uberConfig;
    private Button btnBookUber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    "com.motrss",
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {

        } catch (NoSuchAlgorithmException e) {

        }

        uberConfig = new UberConfig(this,MainActivity.this);
        uberConfig.initializeUber();
        initUI();
    }

    private void initUI(){
        btnUberLogin = (LoginButton) findViewById(R.id.btn_uber_login);
        btnUberLogin.setOnClickListener(this);

        btnBookUber = (Button) findViewById(R.id.btn_book_uber);
        btnBookUber.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_uber_login:
                uberConfig.getLoginManager().login(MainActivity.this);
                break;
            case R.id.btn_book_uber:
                startActivity(new Intent(this, BookUberActivity.class));
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        uberConfig.getLoginManager().onActivityResult(MainActivity.this, requestCode, resultCode, data);
    }

    @Override
    public void onUberProductsSuccess(ArrayList<Products> customResponse) {

    }

    @Override
    public void onUberProductsFailure(String cause) {

    }

    @Override
    public Context getAppBaseContext() {
        return MainActivity.this;
    }
}
