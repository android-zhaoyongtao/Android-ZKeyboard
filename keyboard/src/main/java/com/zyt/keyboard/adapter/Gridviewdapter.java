package com.zyt.keyboard.adapter;

import android.content.Context;

import com.zyt.keyboard.ZKeyboardService;
import com.zyt.keyboard.bean.PairsBean;

import java.util.List;

public class Gridviewdapter extends BaseZAdapter {

    public Gridviewdapter(Context context, List<PairsBean> pairs) {
        super((ZKeyboardService) context);
        this.pairs = pairs;
    }

    public void setData(List<PairsBean> pairs) {
        this.pairs = pairs;
        notifyDataSetChanged();
    }
}