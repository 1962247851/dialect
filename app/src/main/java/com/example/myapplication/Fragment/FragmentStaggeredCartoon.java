package com.example.myapplication.Fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.Activity.DubbingDetailsActivity;
import com.example.myapplication.Adapter.RecyclerView.MyAdapterStaggered;
import com.example.myapplication.R;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.listener.OnMultiPurposeListener;

public class FragmentStaggeredCartoon extends Fragment {
    private static final String TAG = "FragmentStaggered----->";
    private RecyclerView mRV;
    private SmartRefreshLayout mSRL;
    private MyAdapterStaggered adapterStaggered;
    private IStaggeredListeners iStaggeredListeners;


    public FragmentStaggeredCartoon() {
    }

    @SuppressLint("ValidFragment")
    public FragmentStaggeredCartoon(IStaggeredListeners i) {
        this.iStaggeredListeners = i;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_staggered, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        adapterStaggered = new MyAdapterStaggered(getContext(), new MyAdapterStaggered.IOnStaggeredClickListener() {
            @Override
            public void OnClick(View v, int position) {
                // TODO: 2019/3/25
                Intent intent = new Intent(getContext(), DubbingDetailsActivity.class);
                startActivity(intent);
                switch (v.getId()) {
                    case R.id.imageView_recycler_staggered:
                        Log.e(TAG, "OnClick: imageView"+position);
                        break;
                    case R.id.textView_recycler_staggered_title:
                        Log.e(TAG, "OnClick: title"+position);
                        break;
                    case R.id.cardView_recycler_view_staggered:
                        Log.e(TAG, "OnClick: cardView"+position);
                        break;
                }
            }
        });
        mRV = view.findViewById(R.id.recyclerView_fragment_staggered);
        mSRL = view.findViewById(R.id.smartRefreshLayout_fragment_staggered);
        mRV.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        mRV.setAdapter(adapterStaggered);

        //设置监听器
        mSRL.setOnMultiPurposeListener(new OnMultiPurposeListener() {
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

            @Override
            public void onStateChanged(@NonNull RefreshLayout refreshLayout, @NonNull RefreshState oldState, @NonNull RefreshState newState) {
                iStaggeredListeners.OnStageChange(refreshLayout, oldState, newState);
            }
        });

    }


    public interface IStaggeredListeners {
        void OnStageChange(RefreshLayout refreshLayout, RefreshState oldStage, RefreshState newStage);
    }

}
