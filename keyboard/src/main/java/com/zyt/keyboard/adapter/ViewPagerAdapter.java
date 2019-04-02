package com.zyt.keyboard.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import com.zyt.keyboard.Json;
import com.zyt.keyboard.R;
import com.zyt.keyboard.bean.PairsBean;
import com.zyt.keyboard.bean.TextResouresBean;
import com.zyt.keyboard.utiles.HistoryUtils;
import com.zyt.keyboard.utiles.StreamUtils;
import com.zyt.keyboard.view.KeyboardSinglePageView;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapter extends PagerAdapter {
    private final Gridviewdapter rentadapter;
    private String[] TITLES;

    private ViewPager pager;
    private ArrayList<View> pages;
    private int keyboardHeight;

    public ViewPagerAdapter(Context context, ViewPager pager, int keyboardHeight) {
        super();

        this.pager = pager;
        this.keyboardHeight = keyboardHeight;
        this.pages = new ArrayList<>();
//        TITLES= new String[]{"最近", "骂人", "情话", "吹牛", "哲理", "鸡汤"};
        try {
            TITLES = Json.getInstance().fromJson(StreamUtils.get(context, R.raw.titlesresourse),
                    new Json.TypeToken<String[]>() {
                    }.getType());
        } catch (Exception e) {
            e.printStackTrace();
            TITLES = new String[0];
        }

        TextResouresBean textResoures = Json.getInstance().fromJson(StreamUtils.get(context, R.raw.textresourse), TextResouresBean.class);
        rentadapter = new Gridviewdapter(context, getNewHistory());
        pages.add(new KeyboardSinglePageView(context, rentadapter).getView());
        pages.add(new KeyboardSinglePageView(context, new Gridviewdapter(context, textResoures.marens)).getView());
        pages.add(new KeyboardSinglePageView(context, new Gridviewdapter(context, textResoures.loves)).getView());
        pages.add(new KeyboardSinglePageView(context, new Gridviewdapter(context, textResoures.chuinius)).getView());
        pages.add(new KeyboardSinglePageView(context, new Gridviewdapter(context, textResoures.zhelis)).getView());
        pages.add(new KeyboardSinglePageView(context, new Gridviewdapter(context, textResoures.jitangs)).getView());

    }

    @Override
    public View instantiateItem(ViewGroup container, int position) {
        pager.addView(pages.get(position), position, keyboardHeight);
        return pages.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        pager.removeView(pages.get(position));
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return TITLES[position];
    }

    @Override
    public int getCount() {
        return TITLES.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    public void notifyHistoryView() {
        rentadapter.setData(getNewHistory());
    }

    //避免历史记录调整影响页面变动
    private ArrayList<PairsBean> getNewHistory() {
        List<PairsBean> allHistoryList = HistoryUtils.getAllHistoryList();
        if (allHistoryList == null) {
            return null;
        } else {
            return new ArrayList<>(allHistoryList);
        }

    }
}
