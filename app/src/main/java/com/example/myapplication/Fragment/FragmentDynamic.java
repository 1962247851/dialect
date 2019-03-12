package com.example.myapplication.Fragment;

import android.annotation.SuppressLint;
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

import com.example.myapplication.Adapter.RecyclerView.MyAdapterDynamic;
import com.example.myapplication.R;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.youth.banner.Banner;

public class FragmentDynamic extends Fragment {
    private static final String TAG = "FragmentDynamic----->";
    private RecyclerView mRV;
    private SmartRefreshLayout mSRL;
    private IOnRefreshLoadMoreListener iOnRefreshLoadMoreListener;
    private Banner mB;
    private MyAdapterDynamic adapterDynamic;
    private IOnClickListener iOnClickListener;

    public FragmentDynamic() {
    }

    @SuppressLint("ValidFragment")
    public FragmentDynamic(IOnClickListener i) {
        this.iOnClickListener = i;
    }

    @SuppressLint("ValidFragment")
    public FragmentDynamic(IOnClickListener i, IOnRefreshLoadMoreListener iOnRefreshLoadMoreListener) {
        this.iOnClickListener = i;
        this.iOnRefreshLoadMoreListener = iOnRefreshLoadMoreListener;
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
                iOnClickListener.OnClick(v);
            }
        };
        adapterDynamic = new MyAdapterDynamic(getContext(), new MyAdapterDynamic.IOnDynamicClickListener() {
            @Override
            public void OnClick(View view) {
                switch (view.getId()) {
                    // TODO: 2019/3/12 重写点击事件
                    case R.id.imageButton_recycler_dynamic_user_head:
                        Log.e(TAG, "OnClick: user head");
                        break;
                    case R.id.textView_recycler_dynamic_user_name:
                        Log.e(TAG, "OnClick: user name");
                        break;
                    case R.id.textView_recycler_dynamic_user_content:
                        Log.e(TAG, "OnClick: content");
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
                        Log.e(TAG, "OnClick: image view");
                        break;
                    case R.id.cardView_recycler_view_dynamic:
                        Log.e(TAG, "OnClick: card view");
                        break;
                }
            }
        });
        mRV = view.findViewById(R.id.recyclerView_fragment_dynamic);
        mB = view.findViewById(R.id.banner_fragment_dynamic);
        mSRL = view.findViewById(R.id.smartRefreshLayout_fragment_dynamic);

        mRV.setLayoutManager(new LinearLayoutManager(getContext()));
        mRV.setAdapter(adapterDynamic);

        //设置监听器
        mSRL.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                iOnRefreshLoadMoreListener.OnRefresh(refreshLayout);
            }
        });
        mSRL.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                iOnRefreshLoadMoreListener.OnLoadMore(refreshLayout);
            }
        });
        mB.setOnClickListener(onClickListener);
    }


    public interface IOnClickListener {
        void OnClick(View view);
    }

    public interface IOnRefreshLoadMoreListener {
        void OnRefresh(RefreshLayout refreshLayout);

        void OnLoadMore(RefreshLayout refreshLayout);
    }
}
