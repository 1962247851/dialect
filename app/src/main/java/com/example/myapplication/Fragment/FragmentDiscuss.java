package com.example.myapplication.Fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.myapplication.Activity.UserDetailsActivity;
import com.example.myapplication.Adapter.RecyclerView.MyAdapterDiscuss;
import com.example.myapplication.R;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.listener.OnMultiPurposeListener;

public class FragmentDiscuss extends Fragment {
    private static final String TAG = "FragmentDiscuss----->";
    private RecyclerView mRV;
    private SmartRefreshLayout mSRL;
    private MyAdapterDiscuss adapterDiscuss;
    private IDiscussListeners iDiscussListeners;
    private ImageView mIV;

    public FragmentDiscuss() {
    }

    @SuppressLint("ValidFragment")
    public FragmentDiscuss(IDiscussListeners iDiscussListeners) {
        this.iDiscussListeners = iDiscussListeners;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_discuss, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iDiscussListeners.OnClick(v);
            }
        };
        adapterDiscuss = new MyAdapterDiscuss(getContext(), new MyAdapterDiscuss.IOnDiscussClickListener() {
            @Override
            public void OnClick(View view) {
                Intent intent = null;
                switch (view.getId()) {
                    // TODO: 2019/3/12  
                    case R.id.imageButton_recycler_discuss_user_head:
                        Log.e(TAG, "OnClick: user head");
                        intent = new Intent(getContext(), UserDetailsActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.textView_recycler_discuss_title:
                        intent = new Intent(getContext(), UserDetailsActivity.class);
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
        });
        mIV = view.findViewById(R.id.imageView_fragment_discuss);
        mRV = view.findViewById(R.id.RecyclerView_fragment_discuss);
        mSRL = view.findViewById(R.id.smartRefreshLayout_fragment_discuss);
        mRV.setLayoutManager(new LinearLayoutManager(getContext()));
        mRV.setAdapter(adapterDiscuss);

        //设置监听事件需要在activity中实现接口
        mSRL.setOnMultiPurposeListener(new OnMultiPurposeListener() {
            @Override
            public void onStateChanged(@NonNull RefreshLayout refreshLayout, @NonNull RefreshState oldState, @NonNull RefreshState newState) {
                iDiscussListeners.OnStageChange(refreshLayout, oldState, newState);
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

        });
        mIV.setOnClickListener(clickListener);
    }

    public interface IDiscussListeners {
        void OnClick(View view);

        void OnStageChange(RefreshLayout refreshLayout, RefreshState oldStage, RefreshState newStage);
    }
}
