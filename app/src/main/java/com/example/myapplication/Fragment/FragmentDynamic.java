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
import com.example.myapplication.Adapter.RecyclerView.MyAdapterDynamic;
import com.example.myapplication.Adapter.RecyclerView.MyAdapterFollow;
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

public class FragmentDynamic extends Fragment {
    private static final String TAG = "FragmentDynamic----->";
    private RecyclerView mRV, mRVFollow;
    private SmartRefreshLayout mSRL;
    private Banner mB;
    private MyAdapterFollow adapterFollow;
    private MyAdapterDynamic adapterDynamic;
    private IDynamicListeners iDynamicListeners;
    private Intent intent;
    private int intentType = -1;//必须初始化,否则默认赋值为0
    private List<Integer> imageUrls = new ArrayList<Integer>();

    public FragmentDynamic() {
    }

    @SuppressLint("ValidFragment")
    public FragmentDynamic(IDynamicListeners i) {
        this.iDynamicListeners = i;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_dynamic, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iDynamicListeners.OnClick(v);
            }
        };
        adapterDynamic = new MyAdapterDynamic(getContext(), new MyAdapterDynamic.IOnDynamicClickListener() {
            @Override
            public void OnClick(View view) {
                switch (view.getId()) {
                    // TODO: 2019/3/12 重写点击事件
                    case R.id.imageButton_recycler_dynamic_user_head:
                        intentType = GlobalUtil.INTENT_TYPE_USER_DETAILS;
                        break;
                    case R.id.textView_recycler_dynamic_user_name:
                        intentType = GlobalUtil.INTENT_TYPE_USER_DETAILS;
                        break;
                    case R.id.textView_recycler_dynamic_user_content:
                        intentType = GlobalUtil.INTENT_TYPE_DUBBING_DETAILS;
                        break;
                    case R.id.imageButton_recycler_dynamic_comment:
                        Log.e(TAG, "OnClick: comment");
                        break;
                    case R.id.textView_recycler_dynamic_comment_num:
                        Log.e(TAG, "OnClick: comment num");
                        break;
                    case R.id.imageButton_recycler_dynamic_good:
                        Log.e(TAG, "OnClick: good");
                        break;
                    case R.id.tickerView_recycler_dynamic_good_num:
                        Log.e(TAG, "OnClick: good num");
                        break;
                    case R.id.imageButton_recycler_dynamic_share:
                        Log.e(TAG, "OnClick: share");
                        break;
                    case R.id.imageView_recycler_dynamic:
                        intentType = GlobalUtil.INTENT_TYPE_DUBBING_DETAILS;
                        break;
                    case R.id.cardView_recycler_view_dynamic:
                        intentType = GlobalUtil.INTENT_TYPE_DUBBING_DETAILS;
                        break;
                }
                switch (intentType) {
                    case GlobalUtil.INTENT_TYPE_DUBBING_DETAILS:
                        intent = new Intent(getContext(), DubbingDetailsActivity.class);
                        startActivity(intent);
                        break;
                    case GlobalUtil.INTENT_TYPE_USER_DETAILS:
                        intent = new Intent(getContext(), UserDetailsActivity.class);
                        startActivity(intent);
                        break;
                }
                intentType = -1;
            }
        });
        adapterFollow = new MyAdapterFollow(getContext(), new MyAdapterFollow.IOnFollowClickListener() {
            @Override
            public void OnClick(View v, int position) {
                // TODO: 2019/3/18
                Log.e(TAG, "OnClick: user head " + position);
            }
        });
        mRV = view.findViewById(R.id.recyclerView_fragment_dynamic);
        mRVFollow = view.findViewById(R.id.recyclerView_fragment_dynamic_follow);

        mRV.setLayoutManager(new LinearLayoutManager(getContext()));
        mRV.setAdapter(adapterDynamic);

        mRVFollow.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        mRVFollow.setAdapter(adapterFollow);

        mB = view.findViewById(R.id.banner_fragment_dynamic);
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
        mSRL = view.findViewById(R.id.smartRefreshLayout_fragment_dynamic);

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
                iDynamicListeners.OnStageChange(refreshLayout, oldState, newState);
            }
        });
        mB.setOnClickListener(onClickListener);
        mB.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                iDynamicListeners.OnBanner(position);
            }
        });
    }

    public interface IDynamicListeners {
        void OnClick(View view);

        void OnBanner(int position);

        void OnStageChange(RefreshLayout refreshLayout, RefreshState oldStage, RefreshState newStage);
    }

}
