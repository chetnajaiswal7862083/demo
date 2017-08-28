package com.motrss.db;

import com.motrss.core.MotrssApp;

import io.realm.Realm;

/**
 * Class to perform CRUD operations on Ream
 *
 * @Author Dhananjay Kulkarni.
 */

public class RealmOperations {

    private static RealmOperations mInstance;
    private static Realm realm;

    public RealmOperations() {
        realm = Realm.getDefaultInstance();
    }

    public static RealmOperations getInstance(){
        if(mInstance == null){
            mInstance = new RealmOperations();
        }
        return mInstance;
    }
}
