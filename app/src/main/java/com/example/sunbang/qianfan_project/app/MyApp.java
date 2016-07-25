package com.example.sunbang.qianfan_project.app;

import android.app.Application;

import org.xutils.x;

/**
 * Created by SunBang on 2016/7/24.
 */

public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        x.Ext.setDebug(true);
    }
}
