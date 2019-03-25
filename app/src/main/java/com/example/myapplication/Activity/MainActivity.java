package com.example.myapplication.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.myapplication.Fragment.AuditionFragment;
import com.example.myapplication.Fragment.CompareFragment;
import com.example.myapplication.Fragment.DubbingFragment;
import com.example.myapplication.Fragment.MainFragment;
import com.example.myapplication.R;
import com.example.myapplication.Util.GlobalUtil;
import com.robinhood.ticker.TickerView;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;

import java.util.ArrayList;
import java.util.List;

import me.sugarkawhi.bottomnavigationbar.BottomNavigationBar;
import me.sugarkawhi.bottomnavigationbar.BottomNavigationEntity;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity----->";

    private FragmentManager fragmentManager;
    private Fragment currentFragment;
    private BottomNavigationBar bottomNavigationBar;
    private DubbingFragment.IOnClickListener iOnClickListener;
    private AuditionFragment.IAuditionListener iAuditionListener;
    private int pastPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        initListener();
    }

    private void initView() {
        setContentView(R.layout.activity_main);
        bottomNavigationBar = findViewById(R.id.navigationBar_main);
        List<BottomNavigationEntity> mEntities = new ArrayList<>();
        mEntities.add(new BottomNavigationEntity(
                "首页",
                R.drawable.icon_home,
                R.drawable.icon_home
        ));
        mEntities.add(new BottomNavigationEntity(
                "配音",
                R.drawable.icon_dubibng,
                R.drawable.icon_dubibng
        ));
        mEntities.add(new BottomNavigationEntity(
                R.drawable.icon_add,
                R.drawable.icon_add
        ));
        mEntities.add(new BottomNavigationEntity(
                "听音",
                R.drawable.icon_story,
                R.drawable.icon_story
        ));
        mEntities.add(new BottomNavigationEntity(
                "比音",
                R.drawable.icon_bifangyan,
                R.drawable.icon_bifangyan
        ));
        bottomNavigationBar.setEntities(mEntities);
        bottomNavigationBar.setCurrentPosition(0);

        MainFragment mainFragment = new MainFragment();
        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().add(R.id.frameLayout_main, mainFragment, GlobalUtil.FRAGMENT_TAG.MAIN).commitAllowingStateLoss();
        currentFragment = mainFragment;
    }

    private void initListener() {
        bottomNavigationBar.setBnbItemSelectListener(new BottomNavigationBar.IBnbItemSelectListener() {
            @Override
            public void onBnbItemSelect(int position) {
                switch (position) {
                    case 0:
                        switchFragment(GlobalUtil.FRAGMENT_TAG.MAIN);
                        break;
                    case 1:
                        switchFragment(GlobalUtil.FRAGMENT_TAG.DUBBING);
                        break;
                    case 2:
                        // TODO: 2019/3/11
                        bottomNavigationBar.setCurrentPosition(pastPosition);
                        Log.e(TAG, "onNavigationItemSelected: More");
                        Intent intent = new Intent(MainActivity.this, ChatActivity.class);
                        startActivity(intent);
                        break;
                    case 3:
                        switchFragment(GlobalUtil.FRAGMENT_TAG.AUDITION);
                        break;
                    case 4:
                        switchFragment(GlobalUtil.FRAGMENT_TAG.COMPARE);
                        break;
                }
                if (position != 2) {
                    pastPosition = position;
                }
            }
        });
        bottomNavigationBar.setBnbItemDoubleClickListener(new BottomNavigationBar.IBnbItemDoubleClickListener() {
            @Override
            public void onBnbItemDoubleClick(int position) {
                // TODO: 2019/3/13 双击刷新或其他
                Log.e(TAG, "onBnbItemDoubleClick: " + position);
            }
        });

        iAuditionListener = new AuditionFragment.IAuditionListener() {
            @Override
            public void OnClick(View view) {
                // TODO: 2019/3/25 与Activity传递消息时用,需在AuditionFragment调用此方法
            }

            @Override
            public void OnStageChange(RefreshLayout refreshLayout, RefreshState oldState, RefreshState newState) {
                switch (newState) {
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
                        Toast.makeText(MainActivity.this, "刷新中", Toast.LENGTH_SHORT).show();
                        break;
                    case RefreshFinish:
                        Toast.makeText(MainActivity.this, "刷新完成", Toast.LENGTH_SHORT).show();
                        break;
                    case Loading:
                        refreshLayout.finishLoadMore(1000, true, true);
                        Toast.makeText(MainActivity.this, "加载中", Toast.LENGTH_SHORT).show();
                        break;
                    case LoadFinish:
                        Toast.makeText(MainActivity.this, "加载完成", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        };

        iOnClickListener = new DubbingFragment.IOnClickListener() {
            @Override
            public void OnClick(View view) {
                switch (view.getId()) {
                    case R.id.tickerView_dubbing_count_down_num:
                        // TODO: 2019/3/11
                        Log.e(TAG, "onClick: count down num");
                        TickerView tickerView = (TickerView) view;
                        tickerView.setText(String.valueOf(Integer.valueOf(tickerView.getText()) + 1));
                        break;
                    case R.id.imageButton_dubbing_back:
                        // TODO: 2019/3/11
                        Log.e(TAG, "onClick: back");
                        break;
                    case R.id.imageButton_dubbing_restart:
                        // TODO: 2019/3/11
                        Log.e(TAG, "onClick: restart");
                        break;
                    case R.id.imageButton_dubbing_start:
                        // TODO: 2019/3/11
                        Log.e(TAG, "onClick: start");
                        break;
                    case R.id.imageButton_dubbing_finish:
                        // TODO: 2019/3/11
                        Log.e(TAG, "onClick: finish");
                        break;
                }
            }
        };
    }

    //切换Fragment
    private void switchFragment(String currentFragmentTag) {
        Fragment fragment = null;
        switch (currentFragmentTag) {
            case GlobalUtil.FRAGMENT_TAG.MAIN:
                fragment = fragmentManager.findFragmentByTag(currentFragmentTag);
                if (fragment == null) {
                    fragment = new MainFragment();
                }
                break;
            case GlobalUtil.FRAGMENT_TAG.DUBBING:
                fragment = fragmentManager.findFragmentByTag(currentFragmentTag);
                if (fragment == null) {
                    fragment = new DubbingFragment(iOnClickListener);
                }
                break;
            case GlobalUtil.FRAGMENT_TAG.AUDITION:
                fragment = fragmentManager.findFragmentByTag(currentFragmentTag);
                if (fragment == null) {
                    fragment = new AuditionFragment(iAuditionListener);
                }
                break;
            case GlobalUtil.FRAGMENT_TAG.COMPARE:
                fragment = fragmentManager.findFragmentByTag(currentFragmentTag);
                if (fragment == null) {
                    fragment = new CompareFragment();
                }
                break;
        }
        if (currentFragment != fragment) {
            if (!fragment.isAdded()) {
                fragmentManager.beginTransaction().hide(currentFragment).add(R.id.frameLayout_main, fragment, currentFragmentTag).commitAllowingStateLoss();
            } else {
                fragmentManager.beginTransaction().hide(currentFragment).show(fragment).commitAllowingStateLoss();
            }
            currentFragment = fragment;
        }
    }
}
