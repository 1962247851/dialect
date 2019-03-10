package com.example.myapplication.Activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.myapplication.Adapter.RecyclerView.MyAdapterDiscuss;
import com.example.myapplication.Adapter.ViewPager.MyFragmentPagerAdapter;
import com.example.myapplication.Fragment.FragmentDiscover;
import com.example.myapplication.Fragment.FragmentDiscuss;
import com.example.myapplication.Fragment.FragmentDynamic;
import com.example.myapplication.R;
import com.flyco.tablayout.SlidingTabLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity----->";

    private BottomNavigationView navigation;
    private SlidingTabLayout mTL;
    private List<Fragment> fragments = new ArrayList<>();
    private ViewPager mVP;
    private MyFragmentPagerAdapter pagerAdapter;
    private Button mBtnUserHead, mBtnSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initListener();
    }


    //找到控件
    private void initView() {
        setContentView(R.layout.activity_main);
        navigation = findViewById(R.id.navigation_main);
        mBtnUserHead = findViewById(R.id.button_main_user_head);
        mBtnSearch = findViewById(R.id.button_main_search);
        mTL = findViewById(R.id.tabLayout_main);
        mVP = findViewById(R.id.viewPager_main);
        FragmentDiscuss.IOnClickListener iOnClickListener = new FragmentDiscuss.IOnClickListener() {
            @Override
            public void OnClick(View view) {
                switch (view.getId()) {
                    case R.id.imageView_fragment_discuss:
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
        FragmentDiscuss fragmentDiscuss = new FragmentDiscuss(iOnClickListener);
        FragmentDiscover fragmentDiscover = new FragmentDiscover(iOnClickListener1);
        FragmentDynamic fragmentDynamic = new FragmentDynamic(iOnClickListener2);

        fragments.add(fragmentDiscuss);
        fragments.add(fragmentDiscover);
        fragments.add(fragmentDynamic);
        pagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), fragments);
        mVP.setAdapter(pagerAdapter);
        mTL.setViewPager(mVP);
    }

    //设置监听事件
    private void initListener() {
        MyOnClick myOnClick = new MyOnClick();
        mBtnUserHead.setOnClickListener(myOnClick);
        mBtnSearch.setOnClickListener(myOnClick);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.navigation_home:
                        Log.e(TAG, "onNavigationItemSelected: Home");
                        return true;
                    case R.id.navigation_dubbing:
                        Log.e(TAG, "onNavigationItemSelected: Dubbing");
                        return true;
                    case R.id.navigation_more:
                        Log.e(TAG, "onNavigationItemSelected: More");
                        return true;
                    case R.id.navigation_audition:
                        Log.e(TAG, "onNavigationItemSelected: Audition");
                        return true;
                    case R.id.navigation_compare:
                        Log.e(TAG, "onNavigationItemSelected: Compare");
                        return true;
                }
                return false;
            }
        });
    }

    //重写点击方法
    private class MyOnClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.button_main_user_head:
                    Log.e(TAG, "onClick: User Head");
                    break;
                case R.id.button_main_search:
                    Log.e(TAG, "onClick: Search");
                    break;
            }
        }
    }
}
