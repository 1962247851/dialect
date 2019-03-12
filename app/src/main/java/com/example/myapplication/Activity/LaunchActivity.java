package com.example.myapplication.Activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.example.myapplication.R;

public class LaunchActivity extends AppCompatActivity {
    private boolean isFirstOpen = true;
    private Intent intent;
    private Button mBtnSkipAd;
    private Integer time = 5000;
    private Runnable runnable;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_launch);
        mBtnSkipAd = findViewById(R.id.button_launch_skip_ad);
        mBtnSkipAd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start(isFirstOpen);
            }
        });
        handler.sendEmptyMessage(0);
        runnable = new Runnable() {
            @Override
            public void run() {
                time -= 1000;
                mBtnSkipAd.setText("跳过广告 " + time / 1000);
                handler.sendEmptyMessage(0);
            }
        };
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (time != 0) {
                handler.postDelayed(runnable, 1000);
            } else {
                start(isFirstOpen);
            }
        }
    };

    private void start(boolean isFirstOpen) {
        if (isFirstOpen) {
            intent = new Intent(LaunchActivity.this, LoginActivity.class);
        } else {
            intent = new Intent(LaunchActivity.this, MainActivity.class);
        }
        handler.removeCallbacks(runnable);
        finish();
        startActivity(intent);
    }
}
