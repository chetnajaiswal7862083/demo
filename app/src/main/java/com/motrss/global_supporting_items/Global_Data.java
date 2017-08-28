package com.motrss.global_supporting_items;

import android.content.Context;
import android.view.LayoutInflater;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by hp on 18-05-2016.
 */
public class Global_Data {
    public static final int CORE_POOL_SIZE = 5;
    public static final int MAXIMUM_POOL_SIZE = 128;

    public static final BlockingQueue<Runnable> sWorkQueue = new LinkedBlockingQueue<Runnable>(10);

    public static final ThreadPoolExecutor sExecutor =

            new ThreadPoolExecutor(CORE_POOL_SIZE, MAXIMUM_POOL_SIZE, 200000, TimeUnit.SECONDS, sWorkQueue);
    public Context _mContext;
    public static LayoutInflater inflater = null;

    public static String _url = "http://itgenration.000webhostapp.com/jcbapp/index.php/";
    public static String firsTime = "first time open";

    public static String authorization_code ="AUTHORIZATION CODE";
    public static String getAccessToken="GET ACCESS TOKEN";
    public static String getRefreshToken="GET REFRESH TOKEN";


}
