package com.example.sunbang.qianfan_project.fragment;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.sunbang.qianfan_project.R;
import com.example.sunbang.qianfan_project.adapter.RankAdapter;
import com.example.sunbang.qianfan_project.cans.Cans;
import com.example.sunbang.qianfan_project.fragment.grandFragment.GrandFragment_wealthrank;
import com.example.sunbang.qianfan_project.fragment.grandFragment.GrandFragment_weekrank;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class SubFragment_week extends Fragment implements ViewPager.OnPageChangeListener {
    private TextView[] textes;
    private List<Fragment> list;
    private ViewPager viewPager;
    private RankAdapter adapter;
    private int page;

    private String[] path = {Cans.RANK_FANS_THIS_WEEK,Cans.RANK_FANS_LAST_WEEK};
    private View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         view = inflater.inflate(R.layout.fragment_sub_fragment_week, container, false);
        init();
        return view;
    }

    private void init() {
        viewPager = (ViewPager) view.findViewById(R.id.viewPager_sub_rank_week);
        ViewGroup vg = (ViewGroup) view.findViewById(R.id.textes);

        textes = new TextView[vg.getChildCount()];
        //遍历vg里面的textView将其放到TextView数组里
        for (int i = 0;i<vg.getChildCount();i++){
            textes[i] = (TextView) vg.getChildAt(i);
            final int finalI =i/2;
            textes[i/2].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    viewPager.setCurrentItem(finalI);
                }
            });
            textes[i].setTextColor(Color.parseColor("#CB9C64"));
            initFragment();
            viewPager.setAdapter(adapter);
            viewPager.addOnPageChangeListener(this);
        }
    }

    private void initFragment() {
        list = new ArrayList<>();
        for (int i =0;i<2;i++){
            Bundle bundle = new Bundle();
            bundle.putString("index",path[i]);
            Fragment fragment = new GrandFragment_weekrank();
            fragment.setArguments(bundle);
            list.add(fragment);
        }
        //实例化适配器
        adapter = new RankAdapter(getChildFragmentManager(),list);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {
        textes[page].setTextColor(Color.BLACK);
        textes[position].setTextColor(Color.parseColor("#CB9C64"));
        page= position;
    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }
}
