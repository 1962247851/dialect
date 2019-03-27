package com.example.myapplication.Fragment;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.myapplication.Activity.MySelfActivity;
import com.example.myapplication.Adapter.ViewPager.MyFragmentPagerAdapterHome;
import com.example.myapplication.R;
import com.flyco.tablayout.SlidingTabLayout;
import com.makeramen.roundedimageview.RoundedImageView;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.youth.xframe.XFrame;

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
    private MyFragmentPagerAdapterHome pagerAdapter;
    private IOnClickListener iOnClickListener;
    private RoundedImageView  roundedImageView;


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
        XFrame.init(getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        roundedImageView = view.findViewById(R.id.roundedImageView_fragment_main_user_head);
        mTL = view.findViewById(R.id.tabLayout_main);
        mVP = view.findViewById(R.id.viewPager_main);
        //设置三个Fragment的接口
        FragmentDiscuss.IDiscussListeners iDiscussListeners = new FragmentDiscuss.IDiscussListeners() {
            @Override
            public void OnClick(View view) {
                switch (view.getId()) {
                    case R.id.imageView_fragment_discuss:
                        // TODO: 2019/3/11
                        Log.e(TAG, "OnClick: ImageView");
                        break;
                }
            }

            @Override
            public void OnStageChange(RefreshLayout refreshLayout, RefreshState oldStage, RefreshState newStage) {
                switch (newStage) {
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
                        Toast.makeText(getContext(), "刷新中", Toast.LENGTH_SHORT).show();
                        break;
                    case RefreshFinish:
                        Toast.makeText(getContext(), "刷新完成", Toast.LENGTH_SHORT).show();
                        break;
                    case TwoLevel:
                        Toast.makeText(getContext(), "二楼", Toast.LENGTH_SHORT).show();
                        break;
                    case Loading:
                        refreshLayout.finishLoadMore(1000, true, true);
                        Toast.makeText(getContext(), "加载中", Toast.LENGTH_SHORT).show();
                        break;
                    case LoadFinish:
                        Toast.makeText(getContext(), "加载完成", Toast.LENGTH_SHORT).show();
                        break;

                }
            }
        };
        FragmentDiscover.IDiscoverListeners iDiscoverListeners = new FragmentDiscover.IDiscoverListeners() {
            @Override
            public void OnClick(View view) {
                switch (view.getId()) {
                    case R.id.banner_fragment_discover:
                        Log.e(TAG, "OnClick: banner");
                        break;
                }
            }

            @Override
            public void OnBanner(int position) {
                Log.e(TAG, "OnBanner: banner position" + position);
            }

            @Override
            public void OnStageChange(RefreshLayout refreshLayout, RefreshState oldStage, RefreshState newStage) {
                switch (newStage) {
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
                        Toast.makeText(getContext(), "刷新中", Toast.LENGTH_SHORT).show();
                        break;
                    case RefreshFinish:
                        Toast.makeText(getContext(), "刷新完成", Toast.LENGTH_SHORT).show();
                        break;
                    case Loading:
                        refreshLayout.finishLoadMore(1000, true, true);
                        Toast.makeText(getContext(), "加载中", Toast.LENGTH_SHORT).show();
                        break;
                    case LoadFinish:
                        Toast.makeText(getContext(), "加载完成", Toast.LENGTH_SHORT).show();
                        break;

                }
            }
        };
        FragmentDynamic.IDynamicListeners iDynamicListeners = new FragmentDynamic.IDynamicListeners() {
            @Override
            public void OnClick(View view) {
                switch (view.getId()) {
                    case R.id.banner_fragment_dynamic:
                        Log.e(TAG, "OnClick: banner");
                        break;
                }
            }

            @Override
            public void OnBanner(int position) {
                Log.e(TAG, "OnBanner: banner position " + position);
            }

            @Override
            public void OnStageChange(RefreshLayout refreshLayout, RefreshState oldStage, RefreshState newStage) {
                switch (newStage) {
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
                        Toast.makeText(getContext(), "刷新中", Toast.LENGTH_SHORT).show();
                        break;
                    case RefreshFinish:
                        Toast.makeText(getContext(), "刷新完成", Toast.LENGTH_SHORT).show();
                        break;
                    case Loading:
                        refreshLayout.finishLoadMore(1000, true, true);
                        Toast.makeText(getContext(), "加载中", Toast.LENGTH_SHORT).show();
                        break;
                    case LoadFinish:
                        Toast.makeText(getContext(), "加载完成", Toast.LENGTH_SHORT).show();
                        break;

                }
            }

        };

        //实例化三个Fragment
        FragmentDiscuss fragmentDiscuss = new FragmentDiscuss(iDiscussListeners);
        FragmentDiscover fragmentDiscover = new FragmentDiscover(iDiscoverListeners);
        FragmentDynamic fragmentDynamic = new FragmentDynamic(iDynamicListeners);

        fragments.add(fragmentDiscuss);
        fragments.add(fragmentDiscover);
        fragments.add(fragmentDynamic);

        pagerAdapter = new MyFragmentPagerAdapterHome(getChildFragmentManager(), fragments);

        mVP.setOffscreenPageLimit(3);
        mVP.setAdapter(pagerAdapter);
        mTL.setViewPager(mVP);
        MyOnClick myOnClick = new MyOnClick();
        roundedImageView.setOnClickListener(myOnClick);
    }

    private class MyOnClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.roundedImageView_fragment_main_user_head:
                    // TODO: 2019/3/11
                    Log.e(TAG, "onClick: User Head");
                    Intent intent = new Intent(getContext(), MySelfActivity.class);
                    startActivity(intent);
                    break;
                case R.id.button_fragment_main_search:
                    // TODO: 2019/3/11
                    Log.e(TAG, "onClick: Search");
                    break;
            }
        }
    }

    //与Activity互相传递消息要用
    public interface IOnClickListener {
        void OnClick(View view);
    }
}
