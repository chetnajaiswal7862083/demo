package com.motrss.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Class to detect and handle network connection status
 *
 * @Author Dhananjay Kulkarni.
 */

public class NetworkUtil {

    /**
     * Wifi.
     */
    public static final int TYPE_WIFI = 1;
    /**
     * Mobile.
     */
    public static final int TYPE_MOBILE = 2;
    /**
     * Not connected.
     */
    public static final int TYPE_NOT_CONNECTED = 0;
    /**
     * Network status of wifi and mobile.
     */
    public static final int NETWORK_STATUS_NOT_CONNECTED = 0, NETWORK_STAUS_WIFI = 1, NETWORK_STATUS_MOBILE = 2;

    /**
     * Retrieving network connectivity status.
     *
     * @param context Context.
     * @return status of network connection.
     */
    public static int getConnectivityStatus(Context context) {
        if (context != null) {
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            if (cm != null) {
                NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
                if (null != activeNetwork) {
                    if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI)
                        return TYPE_WIFI;

                    if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE)
                        return TYPE_MOBILE;
                }
            }
        }
        return TYPE_NOT_CONNECTED;
    }

    /**
     * Get type(mobile or wifi) of network connection.
     *
     * @param context Context.
     * @return Network type.
     */
    public static int getConnectionType(Context context) {
        int status = 0;
        if (context != null) {
            int conn = NetworkUtil.getConnectivityStatus(context);
            if (conn == NetworkUtil.TYPE_WIFI) {
                status = NETWORK_STAUS_WIFI;
            } else if (conn == NetworkUtil.TYPE_MOBILE) {
                status = NETWORK_STATUS_MOBILE;
            } else if (conn == NetworkUtil.TYPE_NOT_CONNECTED) {
                status = NETWORK_STATUS_NOT_CONNECTED;
            }
        }
        return status;
    }
}
