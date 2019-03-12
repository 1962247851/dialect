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
import android.widget.ImageView;

import com.example.myapplication.Adapter.RecyclerView.MyAdapterDiscuss;
import com.example.myapplication.R;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

public class FragmentDiscuss extends Fragment {
    private static final String TAG = "FragmentDiscuss----->";
    private RecyclerView mRV;
    private SmartRefreshLayout mSRL;
    private MyAdapterDiscuss adapterDiscuss;
    private IOnClickListener iOnClickListener;
    private IOnRefreshLoadMoreListener iOnRefreshLoadMoreListener;
    private ImageView mIV;

    public FragmentDiscuss() {
    }

    @SuppressLint("ValidFragment")
    public FragmentDiscuss(IOnClickListener i) {
        this.iOnClickListener = i;
    }

    @SuppressLint("ValidFragment")
    public FragmentDiscuss(IOnClickListener i, IOnRefreshLoadMoreListener iOnRefreshLoadMoreListener) {
        this.iOnClickListener = i;
        this.iOnRefreshLoadMoreListener = iOnRefreshLoadMoreListener;
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
                iOnClickListener.OnClick(v);
            }
        };
        adapterDiscuss = new MyAdapterDiscuss(getContext(), new MyAdapterDiscuss.IOnDiscussClickListener() {
            @Override
            public void OnClick(View view) {
                switch (view.getId()) {
                    // TODO: 2019/3/12  
                    case R.id.imageButton_recycler_discover_user_head:
                        Log.e(TAG, "OnClick: user head");
                        break;
                    case R.id.textView_recycler_discover_title:
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
        mIV.setOnClickListener(clickListener);
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
    }

    public interface IOnClickListener {
        void OnClick(View view);
    }

    public interface IOnRefreshLoadMoreListener {
        void OnRefresh(RefreshLayout refreshLayout);

        void OnLoadMore(RefreshLayout refreshLayout);
    }
}
