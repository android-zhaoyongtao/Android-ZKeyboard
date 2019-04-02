package com.zyt.keyboard.utiles;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;

import com.zyt.keyboard.MContext;

public class SPUtils {

    public static final String NODIRECT = "direct";
    public static final String NOENTER = "enter";
    public static final String TITLESRCROLL = "scroll";
    private static final String SPNAME = "keybord_sp";

    /**
     * 保存String
     */
    public static void setString2SP(String key, String value) {
        if (TextUtils.isEmpty(key) || value == null) {
            return;
        }
        SharedPreferences sp = MContext.getContext().getSharedPreferences(SPNAME, Context.MODE_PRIVATE);
        Editor editor = sp.edit();
        editor.putString(key, value);
        editor.commit();
    }

    /**
     * 获取String
     */
    public static String getStringFromSP(String key, String defaultValue) {
        if (TextUtils.isEmpty(key)) {
            return defaultValue;
        }
        SharedPreferences sp = MContext.getContext().getSharedPreferences(SPNAME, Context.MODE_PRIVATE);
        return sp.getString(key, defaultValue);
    }

    public static void setBoolean2SP(String key, boolean value) {
        if (TextUtils.isEmpty(key)) {
            return;
        }
        SharedPreferences sp = MContext.getContext().getSharedPreferences(SPNAME, Context.MODE_PRIVATE);
        Editor editor = sp.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    public static boolean getBooleanFromSP(String key, boolean defaultValue) {
        if (TextUtils.isEmpty(key)) {
            return defaultValue;
        }
        SharedPreferences sp = MContext.getContext().getSharedPreferences(SPNAME, Context.MODE_PRIVATE);
        return sp.getBoolean(key, defaultValue);
    }

}
