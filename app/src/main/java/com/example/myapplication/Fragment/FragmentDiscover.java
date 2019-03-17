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
    private List<String> imageUrls = new ArrayList<String>();


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
        imageUrls.add("http://img5.imgtn.bdimg.com/it/u=781132934,1707749139&fm=26&gp=0.jpg");
        imageUrls.add("data:image/jpeg;base64,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");
        imageUrls.add("http://img0.imgtn.bdimg.com/it/u=929738525,3909848291&fm=26&gp=0.jpg");
        imageUrls.add("http://img2.imgtn.bdimg.com/it/u=1196912449,2675532703&fm=26&gp=0.jpg");
        imageUrls.add("http://img0.imgtn.bdimg.com/it/u=1822308649,3772848099&fm=26&gp=0.jpg");
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
