package com.zyt.keyboard;

import android.app.Application;

import com.umeng.analytics.MobclickAgent;

/**
 * Created by zhaoyongtao on 2018/3/14.
 */

public class CustomApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        MContext.init(this);
        MobclickAgent.setDebugMode(BuildConfig.DEBUG);
    }
}
