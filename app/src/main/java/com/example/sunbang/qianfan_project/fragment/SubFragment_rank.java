package com.example.sunbang.qianfan_project.fragment;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sunbang.qianfan_project.R;
import com.example.sunbang.qianfan_project.adapter.RankAdapter;
import com.example.sunbang.qianfan_project.cans.Cans;
import com.example.sunbang.qianfan_project.fragment.grandFragment.GrandFragment_rank;
import com.example.sunbang.qianfan_project.fragment.grandFragment.GrandFragment_wealthrank;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class SubFragment_rank extends Fragment implements ViewPager.OnPageChangeListener{
    private View view;
    private List<Fragment> list;
    private ViewPager viewPager;
    private RankAdapter adapter;
    private TextView[] textes;
    //判断是在哪个title下
    private int indext;
    //标记viewPager的地址
    private int page;
    private boolean flags = false;//标记为生命周期
    private String[] pathes = {Cans.RANK_STAR, Cans.RANK_WEALTH, Cans.RANK_POPULARITY};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_sub_fragment_rank, container, false);
        init();
        //接收传来的title坐标值
        indext = getArguments().getInt("index");
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        flags = true;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        flags = false;
    }

    private void init() {
        viewPager = (ViewPager) view.findViewById(R.id.viewPager_sub_rank);
        //实例化textView
        ViewGroup vg = (ViewGroup)view.findViewById(R.id.texts);
        textes = new TextView[vg.getChildCount()];
        for (int i = 0; i < vg.getChildCount(); i++) {
            textes[i] = (TextView) vg.getChildAt(i * 2);//由于里面掺杂着“|”，所以要乘以2
            final int finalI = i/2;
            textes[i/2].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    viewPager.setCurrentItem(finalI);
                }
            });
        }
            //定义所获得的text的颜色
            textes[page].setTextColor(Color.parseColor("#CB9C46"));
            initFragment();
            adapter = new RankAdapter(getChildFragmentManager(),list);
            viewPager.setAdapter(adapter);
            //定义viewPager可监听
            viewPager.addOnPageChangeListener(this);
        }

    //实例化需要实现功能的fragment及判断需要实现哪个下标下的fragment
    private void initFragment() {
        list = new ArrayList<>();//创建fragment集合对象
        for (int i = 0;i<4;i++){
            Bundle bundle = new Bundle();
            bundle.putString("path",pathes[indext]+(i+1));//把所滑动的页面的URL+页面ID值传给实现的fragment
            Fragment fragment = null;
            if (indext == 0 || indext == 2) {
                fragment = new GrandFragment_rank();
            }
            if(indext==1){
                fragment = new GrandFragment_wealthrank();
            }
            bundle.putInt("index",indext);
            fragment.setArguments(bundle);
            list.add(fragment);
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        //页面开始滑动的时候调用此方法，知道滑动结束
    }

    @Override
    public void onPageSelected(int position) {
        //页面跳转之后得到此方法，arg0是当前选中的页面position（位置编号）
        //选中的viewPager
        textes[page].setTextColor(Color.BLACK);
        textes[position].setTextColor(Color.parseColor("#CB9C64"));
        page = position;
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        //表示滑动状态：state 0(没有滑动)  1(正在滑动)  2（滑动停止）
    }
}
