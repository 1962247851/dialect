package com.example.myapplication.Fragment;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.myapplication.Activity.MySelfActivity;
import com.example.myapplication.Adapter.ViewPager.MyFragmentPagerAdapter;
import com.example.myapplication.R;
import com.flyco.tablayout.SlidingTabLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MainFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String TAG = "MainFragment----->";


    private SlidingTabLayout mTL;
    private List<Fragment> fragments = new ArrayList<>();
    private ViewPager mVP;
    private MyFragmentPagerAdapter pagerAdapter;
    private Button mBtnUserHead, mBtnSearch;
    private IOnClickListener iOnClickListener;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public MainFragment() {
        // Required empty public constructor
    }

    @SuppressLint("ValidFragment")
    public MainFragment(IOnClickListener i) {
        this.iOnClickListener = i;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MainFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MainFragment newInstance(String param1, String param2) {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        mBtnUserHead = view.findViewById(R.id.button_main_user_head);
        mBtnSearch = view.findViewById(R.id.button_main_search);
        mTL = view.findViewById(R.id.tabLayout_main);
        mVP = view.findViewById(R.id.viewPager_main);

        FragmentDiscuss.IOnClickListener iOnClickListener = new FragmentDiscuss.IOnClickListener() {
            @Override
            public void OnClick(View view) {
                switch (view.getId()) {
                    case R.id.imageView_fragment_discuss:
                        // TODO: 2019/3/11
                        Log.e(TAG, "OnClick: ImageView");
                        break;
                }
            }
        };

        FragmentDiscover.IOnClickListener iOnClickListener1 = new FragmentDiscover.IOnClickListener() {
            @Override
            public void OnClick(View view) {
                switch (view.getId()) {
                    case R.id.banner_fragment_discover:
                        Log.e(TAG, "OnClick: banner");
                        break;
                }
            }
        };

        FragmentDynamic.IOnClickListener iOnClickListener2 = new FragmentDynamic.IOnClickListener() {
            @Override
            public void OnClick(View view) {
                switch (view.getId()) {
                    case R.id.banner_fragment_dynamic:
                        Log.e(TAG, "OnClick: banner");
                        break;
                }
            }
        };
        FragmentDiscuss fragmentDiscuss = new FragmentDiscuss(iOnClickListener, new FragmentDiscuss.IOnRefreshLoadMoreListener() {
            @Override
            public void OnRefresh(RefreshLayout refreshLayout) {
                // TODO: 2019/3/12
                Log.e(TAG, "OnRefresh: " + refreshLayout.getState());
            }

            @Override
            public void OnLoadMore(RefreshLayout refreshLayout) {
                // TODO: 2019/3/12
                Log.e(TAG, "OnLoadMore: " + refreshLayout.getState());
            }
        });


        FragmentDiscover fragmentDiscover = new FragmentDiscover(iOnClickListener1, new FragmentDiscover.IOnRefreshListener() {
            @Override
            public void OnRefresh(final RefreshLayout refreshLayout) {
                // TODO: 2019/3/11 重写发现页面下拉刷新事件 可以通过refreshLayout.getState()获取状态
                switch (refreshLayout.getState()) {
                    case Refreshing:
                        Handler handler = new Handler();
                        Runnable runnable = new Runnable() {
                            @Override
                            public void run() {
                                refreshLayout.finishRefresh(true);
                            }
                        };
                        handler.postDelayed(runnable, 1000);
                        Log.e(TAG, "OnRefresh: refreshing");
                        break;
                }
            }
        }, new FragmentDiscover.IOnLoadMoreListener() {
            @Override
            public void OnLoadMore(RefreshLayout refreshLayout) {
                // TODO: 2019/3/11 与下拉同理
                switch (refreshLayout.getState()) {
                    case PullUpToLoad:
                        Log.e(TAG, "OnLoadMore: pull to load");
                        break;
                    case Loading:
                        Log.e(TAG, "OnLoadMore: loading");
                        break;
                    case LoadFinish:
                        Log.e(TAG, "OnLoadMore: load finish");
                        break;

                }
            }
        });
        FragmentDynamic fragmentDynamic = new FragmentDynamic(iOnClickListener2, new FragmentDynamic.IOnRefreshLoadMoreListener() {
            @Override
            public void OnRefresh(RefreshLayout refreshLayout) {
                // TODO: 2019/3/12  
                Log.e(TAG, "OnRefresh: " + refreshLayout.getState());
            }

            @Override
            public void OnLoadMore(RefreshLayout refreshLayout) {
                // TODO: 2019/3/12  
                Log.e(TAG, "OnLoadMore: " + refreshLayout.getState());
            }
        });

        fragments.add(fragmentDiscuss);
        fragments.add(fragmentDiscover);
        fragments.add(fragmentDynamic);
        pagerAdapter = new MyFragmentPagerAdapter(getChildFragmentManager(), fragments);
        mVP.setOffscreenPageLimit(3);
        mVP.setAdapter(pagerAdapter);
        mTL.setViewPager(mVP);

        MyOnClick myOnClick = new MyOnClick();
        mBtnUserHead.setOnClickListener(myOnClick);
        mBtnSearch.setOnClickListener(myOnClick);
    }

    private class MyOnClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.button_main_user_head:
                    // TODO: 2019/3/11
                    Log.e(TAG, "onClick: User Head");
                    Intent intent = new Intent(getContext(), MySelfActivity.class);
                    startActivity(intent);
                    break;
                case R.id.button_main_search:
                    // TODO: 2019/3/11
                    Log.e(TAG, "onClick: Search");
                    break;
            }
        }
    }

    public interface IOnClickListener {
        void OnClick(View view);
    }
}
