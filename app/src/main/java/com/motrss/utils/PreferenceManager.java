package com.motrss.utils;


import android.content.Context;
import android.content.SharedPreferences;

public class PreferenceManager {

    private Context mContext;
    private static SharedPreferences mPreferences;
    private static SharedPreferences.Editor mEditor;

    public PreferenceManager(Context mContext) {
        this.mContext = mContext;
        mPreferences = mContext.getSharedPreferences(GlobalConstants.APP_PREFERENCES, Context.MODE_PRIVATE);
    }
    public void setUberAuthToken(String userId){
        mEditor = mPreferences.edit();
        mEditor.putString(GlobalConstants.UBER_AUTH_TOKEN, userId);
        mEditor.commit();
    }

    public String getUberAuthToken(){
        return mPreferences.getString(GlobalConstants.UBER_AUTH_TOKEN, "");
    }


}
