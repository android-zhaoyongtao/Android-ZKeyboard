package com.zyt.keyboard.utiles;

import android.os.CountDownTimer;

import com.zyt.keyboard.Json;
import com.zyt.keyboard.bean.PairsBean;

import java.util.ArrayList;
import java.util.List;

public class HistoryUtils {
    private static final String KEY = "historys";//保存的historys,key
    private static final int MAXLENGTH = 20;//保存的最大个数
    private static List<PairsBean> list;
    private static final CountDownTimer timer = new CountDownTimer(500, 500) {
        @Override
        public void onTick(long millisUntilFinished) {

        }

        @Override
        public void onFinish() {
            saveEntitys(Json.getInstance().toJson(list));
        }
    };

    /**
     * 保存搜索历史
     *
     * @param bean bean
     */
    public static void addRentEntity(PairsBean bean) {
        if (list == null) {
            list = getHistoryList();
        }
        if (list == null) {
            list = new ArrayList<>();
        }

        // 遍历list，查看是否之前存储过相同的
        if (list.contains(bean)) {
            list.remove(bean);
        }

        if (list.size() >= MAXLENGTH) {
            list.remove(list.size() - 1);
        }
        list.add(0, bean);
        timer.cancel();
        timer.start();
    }


    private static void saveEntitys(String value) {
        SPUtils.setString2SP(KEY, value);
    }

    /**
     * 获取保存的历史记录列表
     */
    public static List<PairsBean> getAllHistoryList() {
        if (list == null) {
            list = getHistoryList();
        }
        return list;
    }

    private static List<PairsBean> getHistoryList() {

        String json = SPUtils.getStringFromSP(KEY, "");
        List<PairsBean> list = null;
        try {
            list = Json.getInstance().fromJson(json,
                    new Json.TypeToken<ArrayList<PairsBean>>() {
                    }.getType());
        } catch (Exception e) {
            e.printStackTrace();
            //异常就清空
            saveEntitys("");
        }

        return list;
    }

    /**
     * 清空所有的历史记录
     */
    public static void clearAllHistory() {
        timer.cancel();
        list = null;
        saveEntitys("");
    }

}
