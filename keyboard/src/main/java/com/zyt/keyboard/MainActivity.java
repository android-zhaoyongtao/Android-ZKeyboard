package com.zyt.keyboard;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.KeyEvent;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.zyt.keyboard.utiles.HistoryUtils;
import com.zyt.keyboard.utiles.Prompt;
import com.zyt.keyboard.utiles.SPUtils;

public class MainActivity extends BaseActivity implements View.OnClickListener {
    private Switch switch1;
    private Switch switch2;
    private Switch switch3;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.button1).setOnClickListener(this);
        findViewById(R.id.buttonAbout).setOnClickListener(this);
        switch1 = (Switch) findViewById(R.id.switch1);
        switch1.setChecked(!SPUtils.getBooleanFromSP(SPUtils.NODIRECT, false));
        switch2 = (Switch) findViewById(R.id.switch2);
        switch2.setChecked(!SPUtils.getBooleanFromSP(SPUtils.NOENTER, false));
        switch3 = (Switch) findViewById(R.id.switch3);
        switch3.setChecked(SPUtils.getBooleanFromSP(SPUtils.TITLESRCROLL, false));
        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    SPUtils.setBoolean2SP(SPUtils.NODIRECT, false);
                    MContext.DIRECT = true;
                } else {
                    SPUtils.setBoolean2SP(SPUtils.NODIRECT, true);
                    MContext.DIRECT = false;
                }
            }
        });
        switch2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    SPUtils.setBoolean2SP(SPUtils.NOENTER, false);
                    MContext.ENTER = true;
                } else {
                    SPUtils.setBoolean2SP(SPUtils.NOENTER, true);
                    MContext.ENTER = false;
                }
            }
        });
        switch3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    SPUtils.setBoolean2SP(SPUtils.TITLESRCROLL, true);
//                    MContext.ENTER = true;
                } else {
                    SPUtils.setBoolean2SP(SPUtils.TITLESRCROLL, false);
//                    MContext.ENTER = false;
                }
            }
        });
        if (BuildConfig.DEBUG) {
            View button01 = findViewById(R.id.button01);
            View button02 = findViewById(R.id.button02);
            button01.setVisibility(View.VISIBLE);
            button02.setVisibility(View.VISIBLE);
            button01.setOnClickListener(this);
            button02.setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button1:
                HistoryUtils.clearAllHistory();
                Prompt.showToast("清空最近发送成功");
                break;
            case R.id.buttonAbout:
                AlertDialog dialog = new AlertDialog.Builder(this).create();
                dialog.setTitle("3062341937@qq.com\n欢迎模板投稿,祝大家玩的愉快~");
                dialog.setButton(DialogInterface.BUTTON_POSITIVE, "感谢", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                dialog.show();
                break;
            case R.id.button01:
                startActivity(new Intent(this, PortraitActivity.class));
                break;
            case R.id.button02:
                startActivity(new Intent(this, LandScapeActivity.class));
                break;
        }
    }

    private double mExitTime;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && (System.currentTimeMillis() - mExitTime) > 2000) {
            Prompt.showToast("再按一次退出程序");
            mExitTime = System.currentTimeMillis();
            return true;
        } else {
            return super.onKeyDown(keyCode, event);
        }
    }

}
