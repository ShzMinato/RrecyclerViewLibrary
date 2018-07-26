package com.example.minato.minastore;

import android.app.Application;

/**
 * Created by minato on 2018/7/24.
 * 该module的Application
 */

public class RecyclerApplication extends Application{
    private static RecyclerApplication mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext=this;
    }

    public static RecyclerApplication getContext() {
        return mContext;
    }
}
