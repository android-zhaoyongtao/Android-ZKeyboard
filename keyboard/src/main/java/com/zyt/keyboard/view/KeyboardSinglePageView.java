package com.zyt.keyboard.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.GridView;

import com.zyt.keyboard.MContext;
import com.zyt.keyboard.R;

public class KeyboardSinglePageView {

    private Context context;
    private BaseAdapter adapter;

    public KeyboardSinglePageView(Context context, BaseAdapter adapter) {
        this.context = context;
        this.adapter = adapter;
    }

    public View getView() {
        View view = LayoutInflater.from(context).inflate(R.layout.item_viewpager, null);
        final GridView emojiGrid = (GridView) view.findViewById(R.id.gridview);
//        emojiGrid.setHorizontalSpacing((int) (10*MContext.DP1));
//        emojiGrid.setVerticalSpacing((int) (10*MContext.DP1));
        emojiGrid.setColumnWidth((int) (100 * MContext.DP1));
        emojiGrid.setNumColumns(GridView.AUTO_FIT);

        emojiGrid.setAdapter(adapter);
        return view;
    }
}