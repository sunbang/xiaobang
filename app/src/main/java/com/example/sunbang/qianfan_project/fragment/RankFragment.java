package com.example.sunbang.qianfan_project.fragment;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sunbang.qianfan_project.R;
import com.example.sunbang.qianfan_project.adapter.RankAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class RankFragment extends Fragment {
    private TabLayout tabLayout_rank;
    private ViewPager viewPager;
    private String[] titles={"明星榜","富豪榜","人气榜","周星榜"};

    private List<Fragment> list;

    private RankAdapter adapter;
    private View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_rank, container, false);
        init();
        return view;
    }

    private void init() {
        //实例化控件
        viewPager = (ViewPager)view.findViewById(R.id.viewPager_rank);
        tabLayout_rank = (TabLayout)view.findViewById(R.id.tabLayout_rank);

        list = new ArrayList<>();//创建fragment的集合对象
        adapter = new RankAdapter(getChildFragmentManager(),list,titles);

        for (int i= 0;i<3;i++){
            Fragment subFragment = new SubFragment_rank();
            Bundle bundle = new Bundle();
            bundle.putInt("index",i);
            subFragment.setArguments(bundle);
            list.add(subFragment);
        }
        Fragment fragment = new SubFragment_week();
        list.add(fragment);
        viewPager.setAdapter(adapter);
        // 将tablayout填充到viewPager里
        tabLayout_rank.setupWithViewPager(viewPager);
    }

}
