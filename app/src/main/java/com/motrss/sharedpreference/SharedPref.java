package com.motrss.sharedpreference;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.ArrayList;

public class SharedPref {

    Context mContext;
    Intent intent;
    SharedPreferences mSettingPrefs;

    SharedPreferences.Editor mSettingPrefEditor;
    ArrayList arrayList;

    public SharedPref(Context mContext) {
        this.mContext = mContext;

        // Get the xml/configuration_activity.xml preferences
        mSettingPrefs = PreferenceManager.getDefaultSharedPreferences(mContext);

    }



    public SharedPref(Context mContext, Intent intent) {
        this.mContext = mContext;
        this.intent = intent;
        // Get the xml/configuration_activity.xml preferences
        mSettingPrefs = PreferenceManager.getDefaultSharedPreferences(mContext);
    }

    // store splash data
    public void setStringData(String mKey, String mItem) {
        mSettingPrefEditor = mSettingPrefs.edit();
        mSettingPrefEditor.putString(mKey, mItem);
        mSettingPrefEditor.commit();
    }

    public String getStringData(String mKey) {
        String mStringData = mSettingPrefs.getString(mKey, "");
        return mStringData;
    }

    public boolean setStringarray(String[] array, String arrayName) {
        mSettingPrefEditor = mSettingPrefs.edit();

        mSettingPrefEditor.putInt(arrayName + "_size", array.length);
        for (int i = 0; i < array.length; i++)
            mSettingPrefEditor.putString(arrayName + "_" + i, array[i]);
        return mSettingPrefEditor.commit();

    }

    public boolean setStringAtArrayIndex(String data, int index, String arrayName) {
        mSettingPrefEditor = mSettingPrefs.edit();

        //mSettingPrefEditor.putInt(arrayName + "_index", array.length);
        for (int i = 0; i < index; i++)
            mSettingPrefEditor.putString(arrayName + "_" + index, data);
        return mSettingPrefEditor.commit();

    }

    public boolean setWrapperString(ArrayList<String> al, String arrayName) {
        mSettingPrefEditor = mSettingPrefs.edit();

        mSettingPrefEditor.putInt(arrayName + "_size", al.size());
        for (int i = 0; i < al.size(); i++)
            mSettingPrefEditor.putString(arrayName + "_" + i, al.get(i));
        return mSettingPrefEditor.commit();

    }

    public String[] getWrapperString(String arrayName) {
        int size = mSettingPrefs.getInt(arrayName + "_size", 0);

        String array[] = new String[size];
        for (int i = 0; i < size; i++) {
            array[i] = mSettingPrefs.getString(arrayName + "_" + i, null);

        }
        array.toString();
        return array;
    }


    public String[] getStringArray(String arrayName) {
        int size = mSettingPrefs.getInt(arrayName + "_size", 0);

        String array[] = new String[size];
        for (int i = 0; i < size; i++) {
            array[i] = mSettingPrefs.getString(arrayName + "_" + i, null);

        }
        array.toString();
        return array;
    }

    public boolean setIntegerarray(Integer[] array, String arrayName) {
        mSettingPrefEditor = mSettingPrefs.edit();

        mSettingPrefEditor.putInt(arrayName + "_size", array.length);
        for (int i = 0; i < array.length; i++) {
            mSettingPrefEditor.putInt(arrayName + "_" + i, array[i]);

        }
        return mSettingPrefEditor.commit();

    }

    public boolean setIntegerarrayAtIndex(Integer data, int index, String arrayName) {
        mSettingPrefEditor = mSettingPrefs.edit();

        for (int i = 0; i < index + 1; i++) {
            if (i == index) {
                mSettingPrefEditor.putInt(arrayName + "_" + index, data);
            }
        }
        return mSettingPrefEditor.commit();

    }


    public Integer[] getIntegerArray(String arrayName) {
        int size = mSettingPrefs.getInt(arrayName + "_size", 0);

        Integer array[] = new Integer[size];
        for (int i = 0; i < size; i++) {
            array[i] = mSettingPrefs.getInt(arrayName + "_" + i, 0);

        }

        return array;
    }


    public void setBooleanData(String mKey, boolean mItem) {
        mSettingPrefEditor = mSettingPrefs.edit();
        mSettingPrefEditor.putBoolean(mKey, mItem);
        mSettingPrefEditor.commit();
    }

    public void setIntegerData(String mKey, int mItem) {
        mSettingPrefEditor = mSettingPrefs.edit();
        mSettingPrefEditor.putInt(mKey, mItem);
        mSettingPrefEditor.commit();
    }


    public boolean getBooleanData(String mKey) {
        boolean isPlaying = mSettingPrefs.getBoolean(mKey, false);
        return isPlaying;
    }

    public int getIntegerData(String mKey) {
        int getint = mSettingPrefs.getInt(mKey, 0);

        return getint;
    }

    public void setFloatData(String mKey, float mItem) {
        mSettingPrefEditor = mSettingPrefs.edit();
        mSettingPrefEditor.putFloat(mKey, mItem);
        mSettingPrefEditor.commit();
    }

    public float getFloatData(String mKey) {
        float getfloat = mSettingPrefs.getFloat(mKey, 0);
        return getfloat;
    }


    public void removeFromSharedPreferences(Context mContext, String key) {
       mSettingPrefs.edit().remove(key).commit();
    }

}
