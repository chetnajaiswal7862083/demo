package com.motrss.core;

import android.app.Application;
import android.support.multidex.MultiDexApplication;

import com.motrss.utils.GlobalConstants;

import io.realm.Realm;
import io.realm.RealmConfiguration;


/**
 * @Author Dhananjay Kulkarni.
 */

public class MotrssApp extends MultiDexApplication {

    private static volatile MotrssApp sInstance;

    public MotrssApp() {
        if(sInstance != null){
            throw new RuntimeException("Use getInstance() method to get instance of this calss");
        }
    }

    public static synchronized MotrssApp getInstance(){
        if(sInstance == null){
            synchronized (MotrssApp.class){
                sInstance = new MotrssApp();
            }
        }
        return sInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        Realm.init(this);
        RealmConfiguration configuration = new RealmConfiguration.Builder()
                .name(GlobalConstants.RealmConstants.DB_NAME)
                .schemaVersion(GlobalConstants.RealmConstants.SCHEMA_VERSION)
                .build();
        Realm.setDefaultConfiguration(configuration);

    }
}
