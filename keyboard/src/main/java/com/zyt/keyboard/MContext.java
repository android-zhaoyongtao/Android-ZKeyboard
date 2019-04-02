package com.zyt.keyboard;

import android.content.Context;

import com.zyt.keyboard.utiles.SPUtils;


public class MContext {
    public static boolean DIRECT;
    public static boolean ENTER;
    public static float DP1;
    private static Context context;

    public static Context getContext() {
        return context;
    }

    public static void init(Context context) {
        MContext.context = context;
        DP1 = context.getResources().getDisplayMetrics().density;
        DIRECT = !SPUtils.getBooleanFromSP(SPUtils.NODIRECT, false);
        ENTER = !SPUtils.getBooleanFromSP(SPUtils.NOENTER, false);
    }
}
