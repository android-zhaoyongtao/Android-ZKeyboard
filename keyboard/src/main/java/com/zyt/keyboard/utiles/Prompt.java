package com.zyt.keyboard.utiles;

import android.text.TextUtils;
import android.view.Gravity;
import android.widget.TextView;
import android.widget.Toast;

import com.zyt.keyboard.MContext;

/**
 * 提示
 *
 * @author lee
 */
public class Prompt {

    private static Toast toast;
    private static TextView text;

    /**
     * @param tipStr 提示内容
     */
    public static void showToast(CharSequence tipStr) {
        if (!TextUtils.isEmpty(tipStr)) {
            if (toast == null) {
                toast = Toast.makeText(MContext.getContext(), tipStr, Toast.LENGTH_SHORT);
                text = getDefaultText();
                toast.setView(text);
                toast.setGravity(Gravity.CENTER, 0, 0);
            }
            text.setText(tipStr);
            toast.show();
        }
    }

//    /**
//     * @param tipStr 提示内容
//     */
//    public static void showToast(String tipStr, int gravity) {
//        if (!TextUtils.isEmpty(tipStr)) {
//            Toast toast = Toast.makeText(MContext.getContext(), tipStr, Toast.LENGTH_SHORT);
//            toast.setText(tipStr);
//            toast.setGravity(gravity, 0, 0);
//            toast.show();
//        }
//    }
//
//    /**
//     * 自定义文本内入，例如：
//     */
//    public static void showToast(View view) {
//        Toast toast = Toast.makeText(MContext.getContext(), "", Toast.LENGTH_SHORT);
//        toast.setGravity(Gravity.CENTER, toast.getXOffset(), toast.getYOffset());
//        LinearLayout toastView = (LinearLayout) toast.getView();
//        toastView.addView(view, 0);
//        toast.show();
//    }
//
//
//    /**
//     * @param tip
//     */
//    public static void showToast(int tip, int gravity) {
//        Toast toast = Toast.makeText(MContext.getContext(), tip, Toast.LENGTH_SHORT);
//        toast.setText(tip);
//        toast.setGravity(gravity, 0, 0);
//        toast.show();
//    }
//
//    /**
//     * @param tip
//     */
//    public static void showToast(int tip) {
//        if (tip > 0) {
//            try {
//                if (toast == null) {
//                    toast = Toast.makeText(MContext.getContext(), tip, Toast.LENGTH_SHORT);
//                    text = getDefaultText();
//                    toast.setView(text);
//                    toast.setGravity(Gravity.CENTER, 0, 0);
//                }
//                text.setText(tip);
//                toast.show();
//            } catch (Throwable e) {
//            }
//        }
//    }

    private static TextView getDefaultText() {
        TextRoundBothView view = new TextRoundBothView(MContext.getContext());
        int dp1 = (int) (MContext.DP1 + 0.5f);
        view.setPadding(dp1 * 20, dp1 * 15, dp1 * 20, dp1 * 15);
        view.setBackground(0xaa1e1e1e, 0xaa1e1e1e);
        view.setTextSize(15);
        view.setTextColor(0xffffffff);
        return view;
    }
}
