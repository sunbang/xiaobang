package com.example.sunbang.qianfan_project;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.sunbang.qianfan_project.fragment.FindFragment;
import com.example.sunbang.qianfan_project.fragment.HomeFragment;
import com.example.sunbang.qianfan_project.fragment.MineFragment;
import com.example.sunbang.qianfan_project.fragment.RankFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RadioGroup radioGroup;
    private RadioButton[] radioButtons;
    private LinearLayout linearLayout;

    private List<Fragment> list = new ArrayList<>();

    private int currentPage = 0;

    private String inde;
    private FragmentManager manager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        setView();
    }

    //实例化控件
    private void init() {
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup_main);
        //得到RadioGroup里的button个数
        radioButtons = new RadioButton[radioGroup.getChildCount()];
        linearLayout = (LinearLayout) findViewById(R.id.container_main);
        //实例化每个button按钮
        for (int i = 0;i<radioGroup.getChildCount();i++){
            radioButtons[i] = (RadioButton) radioGroup.getChildAt(i);
        }
        radioButtons[0].setChecked(true);
        radioButtons[0].setTextColor(Color.parseColor("#CB9C64"));

        //实例化fragment 并添加到集合中
        HomeFragment homeFragement = new HomeFragment();
        RankFragment rankFragment = new RankFragment();
        FindFragment findFragment = new FindFragment();
        MineFragment mineFragment = new MineFragment();
        list.add(homeFragement);
        list.add(rankFragment);
        list.add(findFragment);
        list.add(mineFragment);

        manager = getSupportFragmentManager();
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int id) {
                for (int i = 0;i<radioButtons.length;i++){
                    if (radioButtons[i].getId()== id){
                        changeFragment(i);
                    }
                }
            }
        });
    }

    //fragment之间的转换
    private void changeFragment(int i) {
        //颜色变换
        for (int j = 0;j<radioButtons.length;j++){
            if (j == i){
                radioButtons[i].setTextColor(Color.parseColor("#CB9C64"));
            }else {
                radioButtons[j].setTextColor(Color.GRAY);
            }
        }
        if (i>2) {
            i--;
        }
        //获取出发的fragment的焦点
        Fragment frag_current = list.get(currentPage);
        Fragment frag_to = list.get(i);
        if (frag_to.isAdded()) {
            manager.beginTransaction().show(frag_to).hide(frag_current).commit();
        } else {
            manager.beginTransaction().hide(frag_current).add(R.id.container_main, frag_to).commit();
        }
        currentPage = i;
    }

    private void setView() {
        //实例化定义第一个fragment
        manager.beginTransaction().add(R.id.container_main,list.get(0)).commit();
    }
}
