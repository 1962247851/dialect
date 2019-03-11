package com.example.myapplication.Activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.example.myapplication.Fragment.DubbingFragment;
import com.example.myapplication.Fragment.MainFragment;
import com.example.myapplication.R;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity----->";

    private FragmentManager fragmentManager;
    private Fragment currentFragment;
    private BottomNavigationView navigation;
    private DubbingFragment.IOnClickListener iOnClickListener;

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
        MainFragment mainFragment = new MainFragment();
        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().add(R.id.frameLayout_main, mainFragment, MainFragment.FragmentTag).commitAllowingStateLoss();
        currentFragment = mainFragment;
    }

    //设置监听事件
    private void initListener() {
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.navigation_home:
                        switchFragment(MainFragment.FragmentTag);
                        return true;
                    case R.id.navigation_dubbing:
                        switchFragment(DubbingFragment.FragmentTag);
                        return true;
                    case R.id.navigation_more:
                        Log.e(TAG, "onNavigationItemSelected: More");
                        // TODO: 2019/3/11
                        return true;
                    case R.id.navigation_audition:
                        Log.e(TAG, "onNavigationItemSelected: Audition");
                        // TODO: 2019/3/11
                        return true;
                    case R.id.navigation_compare:
                        Log.e(TAG, "onNavigationItemSelected: Compare");
                        // TODO: 2019/3/11
                        return true;
                }
                return false;
            }
        });
        iOnClickListener = new DubbingFragment.IOnClickListener() {
            @Override
            public void OnClick(View view) {
                switch (view.getId()) {
                    case R.id.textVIew_dubbing_count_down_num:
                        // TODO: 2019/3/11
                        Log.e(TAG, "onClick: count down num");
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
            case MainFragment.FragmentTag:
                fragment = fragmentManager.findFragmentByTag(currentFragmentTag);
                if (fragment == null) {
                    fragment = new MainFragment();
                }
                break;
            case DubbingFragment.FragmentTag:
                fragment = fragmentManager.findFragmentByTag(currentFragmentTag);
                if (fragment == null) {
                    fragment = new DubbingFragment(iOnClickListener);
                }
                break;
        }
        if (currentFragment != fragment) {
            if (!fragment.isAdded()) {
                fragmentManager.beginTransaction().hide(currentFragment).add(R.id.frameLayout_main, fragment).commitAllowingStateLoss();
            } else {
                fragmentManager.beginTransaction().hide(currentFragment).show(fragment).commitAllowingStateLoss();
            }
            currentFragment = fragment;
        }
    }
}
