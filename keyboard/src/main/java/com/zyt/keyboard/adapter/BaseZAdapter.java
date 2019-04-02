package com.zyt.keyboard.adapter;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zyt.keyboard.MContext;
import com.zyt.keyboard.R;
import com.zyt.keyboard.ZKeyboardService;
import com.zyt.keyboard.bean.PairsBean;
import com.zyt.keyboard.utiles.HistoryUtils;

import java.util.List;

public abstract class BaseZAdapter extends BaseAdapter {

    protected ZKeyboardService emojiKeyboardService;
    protected List<PairsBean> pairs;//按钮+文字

    public BaseZAdapter(ZKeyboardService emojiKeyboardService) {
        this.emojiKeyboardService = emojiKeyboardService;
    }

    @Override
    public int getCount() {
        return pairs == null ? 0 : pairs.size();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final TextView textView;
        if (convertView == null) {
            textView = (TextView) LayoutInflater.from(emojiKeyboardService).inflate(R.layout.item_keyboard_button, null);
        } else {
            textView = (TextView) convertView;
        }

        textView.setText(pairs.get(position).icon);
        textView.setBackgroundResource(R.drawable.bg_btn);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PairsBean bean = pairs.get(position);
                HistoryUtils.addRentEntity(bean);
                if (MContext.DIRECT) {//直接发送
                    emojiKeyboardService.sendText(randomStrings(bean), true);
                } else if (MContext.ENTER) {//开enter
                    emojiKeyboardService.sendText(randomStrings(bean), false);
                    emojiKeyboardService.sendDownAndUpKeyEvent(KeyEvent.KEYCODE_ENTER, 0);
                } else {//不发送还没enter
                    emojiKeyboardService.sendText(randomStrings(bean), false);
                }

            }
        });

        return textView;
    }

    @Override
    public Object getItem(int position) {
        return pairs.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    private String randomStrings(PairsBean pairsBean) {
        List<String> strs = pairsBean.sendtext;
        String s;
        try {
            s = strs.get(pairsBean.randomIndex++);
        } catch (Exception e) {
            pairsBean.randomIndex = 0;
            s = strs.get(pairsBean.randomIndex++);
        }
        return s;
    }
}
