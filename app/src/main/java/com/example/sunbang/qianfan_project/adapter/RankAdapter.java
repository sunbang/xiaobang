package com.example.sunbang.qianfan_project.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.View;

import java.util.List;

/**
 * Created by SunBang on 2016/7/24.
 */
//Rank title 适配器
public class RankAdapter extends FragmentPagerAdapter{
    private List<Fragment> list;
    private String[] titles;

    public RankAdapter(FragmentManager fm, List<Fragment> list, String[] titles) {
        super(fm);
        this.list = list;
        this.titles = titles;
    }

    public RankAdapter(FragmentManager fm, List<Fragment> list) {
        super(fm);
        this.list = list;
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (titles != null){
            return titles[position];
        }
        return super.getPageTitle(position);
    }
}
