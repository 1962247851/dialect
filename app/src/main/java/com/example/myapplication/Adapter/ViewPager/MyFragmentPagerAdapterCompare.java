package com.example.myapplication.Adapter.ViewPager;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import java.util.Arrays;
import java.util.List;

public class MyFragmentPagerAdapterCompare extends FragmentPagerAdapter {

    private FragmentManager fragmentManager;
    private List<Fragment> fragments;
    private static final List<String> titles = Arrays.asList("最新", "热门", "电影","动漫","电视剧","动画片","原创视频");

    public MyFragmentPagerAdapterCompare(FragmentManager fragmentManager, List<Fragment> fragments) {
        super(fragmentManager);
        this.fragmentManager = fragmentManager;
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int i) {
        return fragments.get(i);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
    }
}
