package com.example.myapplication.Util;

import android.content.Context;

public class GlobalUtil {

    public static final class FRAGMENT_TAG {
        public static final String DUBBING = "DubbingFragment";
        public static final String MAIN = "MainFragment";
        public static final String SETTING = "SettingFragment";
        public static final String USER_DETAILS = "UserDetailsFragment";
        public static final String AUDITION = "AuditionFragment";
        public static final String COMPARE = "CompareFragment";
    }

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
