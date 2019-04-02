package com.zyt.keyboard;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;

import com.zyt.keyboard.utiles.HistoryUtils;
import com.zyt.keyboard.utiles.Prompt;

public class LandScapeActivity extends BaseActivity implements View.OnClickListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testinput);

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
                dialog.setTitle("祝大家玩的愉快~");
                dialog.setButton(DialogInterface.BUTTON_POSITIVE, "感谢", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                dialog.show();
                break;
        }
    }


}
