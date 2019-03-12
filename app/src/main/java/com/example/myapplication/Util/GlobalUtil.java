package com.example.myapplication.Util;

import android.content.Context;

public class GlobalUtil {

    public static final int INTENT_TYPE_DUBBING_DETAILS = 0;
    public static final int INTENT_TYPE_USER_DETAILS = 1;
    public static GlobalUtil instance;
    private Context context;

    public GlobalUtil() {

    }

    public Context getContext() {
        return this.context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public static GlobalUtil getInstance() {
        if (instance == null) {
            instance = new GlobalUtil();
        }
        return instance;
    }
}
