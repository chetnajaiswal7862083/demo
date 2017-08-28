package com.motrss.lyft;


import android.content.Context;
import android.util.Log;

import com.lyft.networking.ApiConfig;
import com.motrss.uber.UberLoginCallback;
import com.uber.sdk.android.core.UberSdk;
import com.uber.sdk.android.core.auth.AccessTokenManager;
import com.uber.sdk.android.core.auth.LoginManager;
import com.uber.sdk.core.auth.Scope;
import com.uber.sdk.rides.client.SessionConfiguration;

import java.util.Arrays;

/**
 * @Author Dhananjay Kulkarni.
 */

public class LyftConfig  {

    private static final String TAG = LyftConfig.class.getSimpleName();

    private SessionConfiguration configuration;
    private AccessTokenManager accessTokenManager;
    private Context context;
    String clientId;String clientToken;
  /*  public LyftConfig(Context context) {
      this.context=context;

    }*/

 /*   private LyftConfig(String clientId, String clientToken) {
        super(clientId,clientToken);
//        this.clientId = clientId;
//        this.clientToken = clientToken;
    }
*/
    public void initializeLyft() {
        ApiConfig apiConfig = new ApiConfig.Builder()
                .setClientId("TyzaevHZs0sL")
                .setClientToken("NZzosQq2/p99oXzTtIZEBt2OQPzzBUM5jn9KIJKOPI6/ySs8pz8xAWDpcIEK8T+VWbInjLJU0ytZTvY/T+hKS2/TgHi3MvOWIlSVrK9kM9NUTeY4VjlNO4Y=")
                .build();
    }


}
