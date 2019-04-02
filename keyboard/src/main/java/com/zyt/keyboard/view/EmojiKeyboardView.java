package com.zyt.keyboard.view;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.zyt.keyboard.R;
import com.zyt.keyboard.ZKeyboardService;
import com.zyt.keyboard.adapter.ViewPagerAdapter;
import com.zyt.keyboard.utiles.SPUtils;

public class EmojiKeyboardView extends View implements SharedPreferences.OnSharedPreferenceChangeListener {

    private ViewPager viewPager;
    private TabLayout tableLayout;
    private LinearLayout layout;

    private ViewPagerAdapter emojiPagerAdapter;
    private ZKeyboardService emojiKeyboardService;
    private int width;
    private int height;

    public EmojiKeyboardView(Context context) {
        super(context);
        initialize(context);
    }

    public EmojiKeyboardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize(context);
    }

    public EmojiKeyboardView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize(context);
    }

    private void initialize(Context context) {

        emojiKeyboardService = (ZKeyboardService) context;

        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        layout = (LinearLayout) inflater.inflate(R.layout.keyboard_main, null);

        viewPager = (ViewPager) layout.findViewById(R.id.emojiKeyboard);

        emojiPagerAdapter = new ViewPagerAdapter(context, viewPager, height);

        viewPager.setAdapter(emojiPagerAdapter);


        tableLayout = (TabLayout) layout.findViewById(R.id.tabView);
        refreshSomeView();
        tableLayout.setupWithViewPager(viewPager);
        viewPager.setCurrentItem(1);
        viewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                if (position == 0) {//
                    emojiPagerAdapter.notifyHistoryView();
                }
            }
        });
        setupSingleButton();
        PreferenceManager.getDefaultSharedPreferences(context).registerOnSharedPreferenceChangeListener(this);
    }

    public void refreshSomeView() {
        if (SPUtils.getBooleanFromSP(SPUtils.TITLESRCROLL, false)) {
            tableLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        } else {
            tableLayout.setTabMode(TabLayout.MODE_FIXED);
        }
        emojiPagerAdapter.notifyHistoryView();
    }

    public View getView() {
        return layout;
    }

    public void notifyDataSetChanged() {
        emojiPagerAdapter.notifyDataSetChanged();
        viewPager.refreshDrawableState();
    }

    private void setupSingleButton() {
        OnClickListener listener = new OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.collectButton:
                        emojiKeyboardService.hideInputMethod();
                        break;
                    case R.id.deleteButton:
                        emojiKeyboardService.sendDownAndUpKeyEvent(KeyEvent.KEYCODE_DEL, 0);
                        break;
                    case R.id.clearButton:
                        emojiKeyboardService.deleteAllText();
                        break;
                    case R.id.changeButton:
                        emojiKeyboardService.showInputMethodPicker();
                        break;
                    case R.id.enterButton:
                        emojiKeyboardService.sendDownAndUpKeyEvent(KeyEvent.KEYCODE_ENTER, 0);
//                        Intent intent = new Intent(emojiKeyboardService, MainActivity.class);
//                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                        emojiKeyboardService.startActivity(intent);
                        break;
                }

            }
        };
        layout.findViewById(R.id.collectButton).setOnClickListener(listener);
        View deleteButton = layout.findViewById(R.id.deleteButton);
        deleteButton.setOnClickListener(listener);
        layout.findViewById(R.id.clearButton).setOnClickListener(listener);
        layout.findViewById(R.id.changeButton).setOnClickListener(listener);
        layout.findViewById(R.id.enterButton).setOnClickListener(listener);
        deleteButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
//                emojiKeyboardService.switchToPreviousInputMethod();
                emojiKeyboardService.sendDownAndUpKeyEvent(KeyEvent.KEYCODE_DEL, 0);
                return false;
            }
        });

    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        width = View.MeasureSpec.getSize(widthMeasureSpec);
        height = View.MeasureSpec.getSize(heightMeasureSpec);

        Log.d("emojiKeyboardView", width + " : " + height);
        setMeasuredDimension(width, height);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {

        Log.d("sharedPreferenceChange", "function called on change of shared preferences with key " + key);
        if (key.equals("icon_set")) {
            emojiPagerAdapter = new ViewPagerAdapter(getContext(), viewPager, height);
            viewPager.setAdapter(emojiPagerAdapter);
            this.invalidate();
        }
    }
}
