package com.example.myapplication.Activity;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.myapplication.Adapter.RecyclerView.MyAdapterDiscuss;
import com.example.myapplication.R;
import com.makeramen.roundedimageview.RoundedImageView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.listener.OnMultiPurposeListener;

public class DubbingDetailsActivity extends AppCompatActivity {
    private static final String TAG = "DubbingDetailsAc----->";

    private RecyclerView mRV;
    private VideoView videoView;
    private MyAdapterDiscuss adapterDiscuss;
    private RoundedImageView mRIVUserHead;
    private TextView mTvUserDescribe, mTvFollow, mTvGoodNum;
    private ImageButton mIBGood, mIBComment, mIBStar, mIBShare, mIBBack;
    private SmartRefreshLayout smartRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        initListener();
    }

    private void initListener() {
        MyOnClick myOnClick = new MyOnClick();
        MyOnMultiPurposeListener myOnMultiPurposeListener = new MyOnMultiPurposeListener();
        mIBBack.setOnClickListener(myOnClick);
        mRIVUserHead.setOnClickListener(myOnClick);
        mTvFollow.setOnClickListener(myOnClick);
        mIBGood.setOnClickListener(myOnClick);
        mIBComment.setOnClickListener(myOnClick);
        mIBStar.setOnClickListener(myOnClick);
        mIBShare.setOnClickListener(myOnClick);
        mTvUserDescribe.setOnClickListener(myOnClick);
        smartRefreshLayout.setOnMultiPurposeListener(myOnMultiPurposeListener);
    }

    private void initView() {
        setContentView(R.layout.activity_dubbing_details);
        videoView = findViewById(R.id.videoView_dubbing_details);
        smartRefreshLayout = findViewById(R.id.smartRefreshLayout_dubbing_details);
        mRV = findViewById(R.id.recyclerView_dubbing_details);
        mTvFollow = findViewById(R.id.textView_dubbing_details_follow);
        mIBBack = findViewById(R.id.imageButton_dubbing_details_back);
        mRIVUserHead = findViewById(R.id.roundedImageView_dubbing_details_user_head);
        mIBGood = findViewById(R.id.imageButton_dubbing_details_good);
        mIBComment = findViewById(R.id.imageButton_dubbing_details_comment);
        mIBStar = findViewById(R.id.imageButton_dubbing_details_star);
        mIBShare = findViewById(R.id.imageButton_dubbing_details_share);
        mTvUserDescribe = findViewById(R.id.textVIew_dubbing_details_user_describe);
        mTvGoodNum = findViewById(R.id.textView_dubbing_details_good_num);
        mTvUserDescribe.setSelected(true);
        adapterDiscuss = new MyAdapterDiscuss(this, new MyAdapterDiscuss.IOnDiscussClickListener() {
            @Override
            public void OnClick(View view) {
                Intent intent = null;
                switch (view.getId()) {
                    // TODO: 2019/3/12
                    case R.id.roundedImageButton_recycler_discuss_user_head:
                        Log.e(TAG, "OnClick: user head");
                        intent = new Intent(DubbingDetailsActivity.this, UserDetailsActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.textView_recycler_discuss_title:
                        intent = new Intent(DubbingDetailsActivity.this, UserDetailsActivity.class);
                        startActivity(intent);
                        Log.e(TAG, "OnClick: user name");
                        break;
                    case R.id.textView_recycler_discuss_user_comment:
                        Log.e(TAG, "OnClick: user comment");
                        break;
                    case R.id.imageButton_recycler_discuss_good:
                        Log.e(TAG, "OnClick: good");
                        break;
                    case R.id.tickerView_recycler_discuss_good_num:
                        Log.e(TAG, "OnClick: good num");
                        break;
                    case R.id.cardView_recycler_view_discuss:
                        Log.e(TAG, "OnClick: card view");
                        break;
                }
            }
        }, false);
        mRV.setLayoutManager(new LinearLayoutManager(this));
        mRV.setAdapter(adapterDiscuss);

        videoView.setVideoURI(Uri.parse("http://ips.ifeng.com/video19.ifeng.com/video09/2018/04/10/24801401-102-009-224712.mp4?vid=0d16511e-1c1f-4106-8c24-3c9b46f15eda&uid=1ODCDv&from=v_Free&pver=vHTML5Player_v2.0.0&sver=&se=%E6%90%9E%E7%AC%91%E9%80%9F%E9%80%92&cat=105-107&ptype=105&platform=pc&sourceType=h5&dt=1523371596000&gid=xpYqAWm6nmxP&sign=c935e844201c2322b28c80025a907035&tm=1553614138450"));
        videoView.setMediaController(new MediaController(this));
        videoView.requestFocus();
        videoView.start();
    }


    private class MyOnMultiPurposeListener implements OnMultiPurposeListener {

        @Override
        public void onStateChanged(@NonNull RefreshLayout refreshLayout, @NonNull RefreshState oldState, @NonNull RefreshState newState) {
            switch (newState) {
                case Refreshing:
//                        Handler handler = new Handler();
//                        Runnable runnable = new Runnable() {
//                            @Override
//                            public void run() {
//                                refreshLayout.finishRefresh(true);
//                            }
//                        };
//                        handler.postDelayed(runnable, 1000);
                    refreshLayout.finishRefresh(1000, true);
                    Toast.makeText(DubbingDetailsActivity.this, "刷新中", Toast.LENGTH_SHORT).show();
                    break;
                case RefreshFinish:
                    Toast.makeText(DubbingDetailsActivity.this, "刷新完成", Toast.LENGTH_SHORT).show();
                    break;
                case Loading:
                    refreshLayout.finishLoadMore(1000, true, true);
                    Toast.makeText(DubbingDetailsActivity.this, "加载中", Toast.LENGTH_SHORT).show();
                    break;
                case LoadFinish:
                    Toast.makeText(DubbingDetailsActivity.this, "加载完成", Toast.LENGTH_SHORT).show();
                    break;
            }
        }

        @Override
        public void onHeaderMoving(RefreshHeader header, boolean isDragging, float percent, int offset, int headerHeight, int maxDragHeight) {

        }

        @Override
        public void onHeaderReleased(RefreshHeader header, int headerHeight, int maxDragHeight) {

        }

        @Override
        public void onHeaderStartAnimator(RefreshHeader header, int headerHeight, int maxDragHeight) {

        }

        @Override
        public void onHeaderFinish(RefreshHeader header, boolean success) {

        }

        @Override
        public void onFooterMoving(RefreshFooter footer, boolean isDragging, float percent, int offset, int footerHeight, int maxDragHeight) {

        }

        @Override
        public void onFooterReleased(RefreshFooter footer, int footerHeight, int maxDragHeight) {

        }

        @Override
        public void onFooterStartAnimator(RefreshFooter footer, int footerHeight, int maxDragHeight) {

        }

        @Override
        public void onFooterFinish(RefreshFooter footer, boolean success) {

        }

        @Override
        public void onLoadMore(@NonNull RefreshLayout refreshLayout) {

        }

        @Override
        public void onRefresh(@NonNull RefreshLayout refreshLayout) {

        }
    }


    private class MyOnClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.imageButton_dubbing_details_back:
                    finish();
                    break;
                case R.id.roundedImageView_dubbing_details_user_head:
                    // TODO: 2019/3/11
                    Log.e(TAG, "onClick: user head");
                    Intent intent = new Intent(DubbingDetailsActivity.this, UserDetailsActivity.class);
                    startActivity(intent);
                    break;
                case R.id.textView_dubbing_details_follow:
                    // TODO: 2019/3/11
                    Log.e(TAG, "onClick: follow");
                    break;
                case R.id.imageButton_dubbing_details_good:
                    // TODO: 2019/3/11
                    Log.e(TAG, "onClick: good");
                    mTvGoodNum.setText(String.valueOf(Integer.valueOf(mTvGoodNum.getText().toString()) + 1));
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
