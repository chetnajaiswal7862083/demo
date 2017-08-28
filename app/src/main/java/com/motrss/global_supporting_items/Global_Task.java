package com.motrss.global_supporting_items;

import android.content.Context;

import com.motrss.sharedpreference.SharedPref;


/**
 * Created by hp on 01-07-2016.
 */
public class Global_Task {
    private Context context;
    SharedPref _sharedPref;
    Global_Data _globalData;
    Global_Method _globalMethod;

    public Global_Task(Context context){
        this.context = context;

        _sharedPref = new SharedPref(context);
        _globalData = new Global_Data();
        _globalMethod = new Global_Method(context);
    }





}
