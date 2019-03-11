package com.example.myapplication.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.myapplication.Adapter.RecyclerView.MyAdapterDiscuss;
import com.example.myapplication.R;

public class DubbingDetailsActivity extends AppCompatActivity {
    private static final String TAG = "DubbingDetails----->";

    private RecyclerView mRV;
    private MyAdapterDiscuss adapterDiscuss;
    private ImageView mIVUserHead;
    private ImageButton mIBGood, mIBComment, mIBStar, mIBShare, mIBBack, mIBFollow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initListener();
    }

    private void initListener() {
        MyOnClick myOnClick = new MyOnClick();
        mIBBack.setOnClickListener(myOnClick);
        mIVUserHead.setOnClickListener(myOnClick);
        mIBFollow.setOnClickListener(myOnClick);
        mIBGood.setOnClickListener(myOnClick);
        mIBComment.setOnClickListener(myOnClick);
        mIBStar.setOnClickListener(myOnClick);
        mIBShare.setOnClickListener(myOnClick);
    }

    private void initView() {
        setContentView(R.layout.activity_dubbing_details);
        mRV = findViewById(R.id.recyclerView_dubbing_details);
        mIBFollow = findViewById(R.id.imageButton_dubbing_details_follow);
        mIBBack = findViewById(R.id.imageButton_dubbing_details_back);
        mIVUserHead = findViewById(R.id.imageView_dubbing_details_user_head);
        mIBGood = findViewById(R.id.imageButton_dubbing_details_good);
        mIBComment = findViewById(R.id.imageButton_dubbing_details_comment);
        mIBStar = findViewById(R.id.imageButton_dubbing_details_star);
        mIBShare = findViewById(R.id.imageButton_dubbing_details_share);
        adapterDiscuss = new MyAdapterDiscuss(this, new MyAdapterDiscuss.IOnDiscussClickListener() {
            @Override
            public void OnClick(View view) {

            }
        });
        mRV.setLayoutManager(new LinearLayoutManager(this));
        mRV.setAdapter(adapterDiscuss);
    }

    private class MyOnClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.imageButton_dubbing_details_back:
                    finish();
                    break;
                case R.id.imageView_dubbing_details_user_head:
                    // TODO: 2019/3/11
                    Log.e(TAG, "onClick: user head");
                    break;
                case R.id.imageButton_dubbing_details_follow:
                    // TODO: 2019/3/11
                    Log.e(TAG, "onClick: follow");
                    break;
                case R.id.imageButton_dubbing_details_good:
                    // TODO: 2019/3/11
                    Log.e(TAG, "onClick: good");
                    break;
                case R.id.imageButton_dubbing_details_comment:
                    // TODO: 2019/3/11
                    Log.e(TAG, "onClick: comment");
                    break;
                case R.id.imageButton_dubbing_details_star:
                    // TODO: 2019/3/11
                    Log.e(TAG, "onClick: star");
                    break;
                case R.id.imageButton_dubbing_details_share:
                    // TODO: 2019/3/11
                    Log.e(TAG, "onClick: share");
                    break;
            }
        }
    }
}