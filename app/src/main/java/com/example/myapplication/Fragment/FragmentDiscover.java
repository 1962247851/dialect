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

import com.example.myapplication.Activity.DubbingDetailsActivity;
import com.example.myapplication.Adapter.RecyclerView.MyAdapterDiscover;
import com.example.myapplication.R;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.youth.banner.Banner;

public class FragmentDiscover extends Fragment {
    private static final String TAG = "FragmentDiscover----->";
    private RecyclerView mRV;
    private Banner mB;
    private SmartRefreshLayout mSRL;
    private MyAdapterDiscover adapterDiscover;
    private IOnClickListener iOnClickListener;
    private IOnRefreshListener iOnRefreshListener;
    private IOnLoadMoreListener iOnLoadMoreListener;

    @SuppressLint("ValidFragment")
    public FragmentDiscover(IOnClickListener iOnClickListener, IOnRefreshListener iOnRefreshListener, IOnLoadMoreListener iOnLoadMoreListener) {
        this.iOnClickListener = iOnClickListener;
        this.iOnRefreshListener = iOnRefreshListener;
        this.iOnLoadMoreListener = iOnLoadMoreListener;
    }

    @SuppressLint("ValidFragment")
    public FragmentDiscover(IOnClickListener iOnClickListener) {
        this.iOnClickListener = iOnClickListener;
    }

    public FragmentDiscover() {
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
                iOnClickListener.OnClick(v);
            }
        };
        adapterDiscover = new MyAdapterDiscover(getContext(), new MyAdapterDiscover.IOnDiscoverClickListener() {
            @Override
            public void OnClick(View view) {
                switch (view.getId()) {
                    case R.id.imageButton_recycler_discover_user_head:
                        Log.e(TAG, "OnClick: user head");
                        break;
                    case R.id.imageView_fragment_discover:
                        Log.e(TAG, "OnClick: imageView");
                        Intent intent = new Intent(getContext(), DubbingDetailsActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.textView_recycler_discover_title:
                        Log.e(TAG, "OnClick: title");
                        break;
                    case R.id.textView_recycler_discover_type:
                        Log.e(TAG, "OnClick: type");
                        break;
                    case R.id.textView_recycler_discover_user_name:
                        Log.e(TAG, "OnClick: user name");
                        break;
                    case R.id.textView_recycler_discover_date:
                        Log.e(TAG, "OnClick: data");
                        break;
                }
            }
        });
        mRV = view.findViewById(R.id.recyclerView_fragment_discover);
        mB = view.findViewById(R.id.banner_fragment_discover);
        mSRL = view.findViewById(R.id.smartRefreshLayout_fragment_discover);
        mRV.setLayoutManager(new LinearLayoutManager(getContext()));
        mRV.setAdapter(adapterDiscover);

        //设置监听器
        mSRL.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                iOnRefreshListener.OnRefresh(refreshLayout);
            }
        });
        mSRL.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                iOnLoadMoreListener.OnLoadMore(refreshLayout);
            }
        });
        mB.setOnClickListener(onClickListener);
    }


    public interface IOnClickListener {
        void OnClick(View view);
    }

    public interface IOnRefreshListener {
        void OnRefresh(RefreshLayout refreshLayout);
    }

    public interface IOnLoadMoreListener {
        void OnLoadMore(RefreshLayout refreshLayout);
    }
}
