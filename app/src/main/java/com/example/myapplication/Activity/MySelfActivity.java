package com.example.myapplication.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.R;

import java.util.Objects;

public class MySelfActivity extends AppCompatActivity {

    private static final String TAG = "MyselfActivity----->";

    private Toolbar toolbar;
    private ImageView mIVUserHead;
    private TextView mTvUserName, mTvFanNum, mTvFollowNum, mTvFan, mTvFollow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initListener();
    }

    private void initListener() {
        MyOnClick myOnClick = new MyOnClick();
        mIVUserHead.setOnClickListener(myOnClick);
        mTvFanNum.setOnClickListener(myOnClick);
        mTvFan.setOnClickListener(myOnClick);
        mTvFollowNum.setOnClickListener(myOnClick);
        mTvFollow.setOnClickListener(myOnClick);
        mTvUserName.setOnClickListener(myOnClick);
    }

    private void initView() {
        setContentView(R.layout.activity_my_self);
        toolbar = findViewById(R.id.toolBar_myself);
        mIVUserHead = findViewById(R.id.imageView_myself_user_head);
        mTvFanNum = findViewById(R.id.textView_myself_fan_num);
        mTvFan = findViewById(R.id.textView_myself_fan);
        mTvFollow = findViewById(R.id.textView_myself_follow);
        mTvFollowNum = findViewById(R.id.textView_myself_follow_num);
        mTvUserName = findViewById(R.id.textView_myself_user_name);

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


    class MyOnClick implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.imageView_myself_user_head:
                    Log.e(TAG, "onClick: user head");
                    break;

                case R.id.textView_myself_user_name:
                    Log.e(TAG, "onClick: user name");
                    break;
                case R.id.textView_myself_fan_num:
                    Log.e(TAG, "onClick: fan num");
                    break;
                case R.id.textView_myself_fan:
                    Log.e(TAG, "onClick: fun");
                    break;
                case R.id.textView_myself_follow_num:
                    Log.e(TAG, "onClick: follow num");
                    break;
                case R.id.textView_myself_follow:
                    Log.e(TAG, "onClick: follow");
                    break;
            }
        }
    }
}
