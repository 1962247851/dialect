package com.example.myapplication.Fragment;

import android.annotation.SuppressLint;
import android.content.Context;
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

import com.bumptech.glide.Glide;
import com.example.myapplication.Activity.DubbingDetailsActivity;
import com.example.myapplication.Activity.UserDetailsActivity;
import com.example.myapplication.Adapter.RecyclerView.MyAdapterDiscover;
import com.example.myapplication.R;
import com.example.myapplication.Util.GlobalUtil;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnMultiPurposeListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class FragmentDiscover extends Fragment {
    private static final String TAG = "FragmentDiscover----->";
    private RecyclerView mRV;
    private Banner mB;
    private SmartRefreshLayout mSRL;
    private MyAdapterDiscover adapterDiscover;
    private IDiscoverListeners iDiscoverListeners;
    private int intentType;
    private List<Integer> imageUrls = new ArrayList<Integer>();


    public FragmentDiscover() {
    }

    @SuppressLint("ValidFragment")
    public FragmentDiscover(IDiscoverListeners i) {
        this.iDiscoverListeners = i;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_discover, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iDiscoverListeners.OnClick(v);
            }
        };
        adapterDiscover = new MyAdapterDiscover(getContext(), new MyAdapterDiscover.IOnDiscoverClickListener() {
            @Override
            public void OnClick(View view) {
                Intent intent = null;
                switch (view.getId()) {
                    // TODO: 2019/3/12
                    case R.id.imageButton_recycler_discover_user_head:
                        intent = new Intent(getContext(), UserDetailsActivity.class);
                        startActivity(intent);
                        Log.e(TAG, "OnClick: user head");
                        break;
                    case R.id.imageView_fragment_discover:
                        // TODO: 2019/3/12 修改intentType
                        intentType = GlobalUtil.INTENT_TYPE_DUBBING_DETAILS;
                        break;
                    case R.id.textView_recycler_discover_title:
                        intentType = GlobalUtil.INTENT_TYPE_DUBBING_DETAILS;
                        break;
                    case R.id.textView_recycler_discover_type:
                        Log.e(TAG, "OnClick: type");
                        break;
                    case R.id.textView_recycler_discover_user_name:
                        intent = new Intent(getContext(), UserDetailsActivity.class);
                        startActivity(intent);
                        Log.e(TAG, "OnClick: user name");
                        break;
                    case R.id.textView_recycler_discover_date:
                        Log.e(TAG, "OnClick: date");
                        break;
                    case R.id.cardView_recycler_view_discover:
                        intentType = GlobalUtil.INTENT_TYPE_DUBBING_DETAILS;
                        break;
                }
                switch (intentType) {
                    case GlobalUtil.INTENT_TYPE_DUBBING_DETAILS:
                        if (intent == null) {
                            intent = new Intent(getContext(), DubbingDetailsActivity.class);
                        }
                        startActivity(intent);
                        break;

                }
                intentType = -1;
            }
        });
        mRV = view.findViewById(R.id.recyclerView_fragment_discover);
        mB = view.findViewById(R.id.banner_fragment_discover);
        // TODO: 2019/3/16 修改banner图片资源
        imageUrls.add(R.drawable.banner2);
        imageUrls.add(R.drawable.banner3);
        imageUrls.add(R.drawable.banner4);
        mB.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                Glide.with(context).load(path).into(imageView);
            }
        });
        mB.setImages(imageUrls);
        mB.start();

        mSRL = view.findViewById(R.id.smartRefreshLayout_fragment_discover);
        mRV.setLayoutManager(new LinearLayoutManager(getContext()));
        mRV.setAdapter(adapterDiscover);

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
                iDiscoverListeners.OnStageChange(refreshLayout, oldState, newState);
            }
        });
        mB.setOnClickListener(onClickListener);
        mB.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                iDiscoverListeners.OnBanner(position);
            }
        });
    }


    public interface IDiscoverListeners {
        void OnClick(View view);

        void OnBanner(int position);

        void OnStageChange(RefreshLayout refreshLayout, RefreshState oldStage, RefreshState newStage);
    }

}
