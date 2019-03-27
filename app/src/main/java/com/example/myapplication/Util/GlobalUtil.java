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

    public static final String[] USER_NAMES = {"不惧下一场", "海伦妍", "啊嚏uuu", "千纸鹤z", "文雅一点的名"};
    public static final String[] USER_COMMENTS_DISCUSS = {"大城市也有乡音", "心里有就有", "不见得", "不能忘记方言", "最是乡音解乡愁"};
    public static final String[] USER_COMMENTS = {"呀,红皇后!", "来了来了", "没有人吗", "有人知道什么电影吗", "不错不错!"};
    public static final String[] TITLES = {"你说爱这个东西值多少钱", "离别的岛，重逢的岛", "配音星爷经典电影", "心中的愿（欢颜）", "猜猜这是什么电影","【清风碎城】千年之约，再相见","外国语学院\"一院一品\"| 第五届英文电影配音","《神秘海域》配音男主","寻找世界游学奇趣体验官——万圣节英文电影趣配音","为周星驰电影配音"};


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
