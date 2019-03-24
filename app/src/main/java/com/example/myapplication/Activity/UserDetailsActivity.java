package com.example.myapplication.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.myapplication.R;

import java.util.Objects;

public class UserDetailsActivity extends AppCompatActivity {

    private static final String TAG = "UserDetailsActi----->";
    private TextView mTvChat, mTvStar, mTvAchievement, mTvPublish, mTvUserName, mTvFollow, mTvFan, mTvFunNum, mTvFollowNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        initListener();
    }

    private void initView() {
        setContentView(R.layout.activity_user_details);
        mTvAchievement = findViewById(R.id.textView_user_details_achievement);
        mTvChat = findViewById(R.id.textView_user_details_chat);
        mTvFan = findViewById(R.id.textView_user_details_fan);
        mTvFunNum = findViewById(R.id.textView_user_details_fan_num);
        mTvFollow = findViewById(R.id.textView_user_details_follow);
        mTvFollowNum = findViewById(R.id.textView_user_details_follow_num);
        mTvPublish = findViewById(R.id.textView_user_details_publish);
        mTvStar = findViewById(R.id.textView_user_details_star);
        mTvUserName = findViewById(R.id.textView_user_details_user_name);
        Toolbar toolbar = findViewById(R.id.toolBar_User_Details);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initListener() {
        MyOnClick myOnClick = new MyOnClick();
        mTvUserName.setOnClickListener(myOnClick);
        mTvChat.setOnClickListener(myOnClick);
        mTvStar.setOnClickListener(myOnClick);
        mTvAchievement.setOnClickListener(myOnClick);
        mTvFan.setOnClickListener(myOnClick);
        mTvFollowNum.setOnClickListener(myOnClick);
        mTvFunNum.setOnClickListener(myOnClick);
        mTvPublish.setOnClickListener(myOnClick);
        mTvFollow.setOnClickListener(myOnClick);
    }

    private class MyOnClick implements View.OnClickListener {
        // TODO: 2019/3/16 重写点击事件
        @Override
        public void onClick(View v) {
            Intent intent = null;
            Boolean needFinish = false;
            switch (v.getId()) {
                case R.id.textView_user_details_user_name:
                    Log.e(TAG, "onClick: user name");
                    break;
                case R.id.textView_user_details_fan_num:
                    Log.e(TAG, "onClick: fan num");
                    break;
                case R.id.textView_user_details_follow_num:
                    Log.e(TAG, "onClick: follow num");
                    break;
                case R.id.textView_user_details_fan:
                    Log.e(TAG, "onClick: fan");
                    break;
                case R.id.textView_user_details_follow:
                    Log.e(TAG, "onClick: follow");
                    break;
                case R.id.textView_user_details_chat:
                    needFinish = true;
                    intent = new Intent(UserDetailsActivity.this, ChatActivity.class);
                    Log.e(TAG, "onClick: chat");
                    break;
                case R.id.textView_user_details_achievement:
                    Log.e(TAG, "onClick: achievement");
                    break;
                case R.id.textView_user_details_publish:
                    Log.e(TAG, "onClick: publish");
                    break;
                case R.id.textView_user_details_star:
                    Log.e(TAG, "onClick: star");
                    break;
            }
            // TODO: 2019/3/16 判断chat activity是否已经打开
            if (needFinish) {
                finish();
            } else {
                // TODO: 2019/3/16 根据点击的项目修改intent
                intent = new Intent(UserDetailsActivity.this, ChatActivity.class);
                startActivity(intent);
            }
        }
    }

}
