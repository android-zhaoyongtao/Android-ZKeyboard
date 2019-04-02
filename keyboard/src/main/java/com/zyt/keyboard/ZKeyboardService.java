package com.zyt.keyboard;

import android.content.Context;
import android.inputmethodservice.InputMethodService;
import android.os.Build;
import android.os.IBinder;
import android.os.SystemClock;
import android.os.Vibrator;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputMethodManager;

import com.umeng.analytics.MobclickAgent;
import com.zyt.keyboard.utiles.Prompt;
import com.zyt.keyboard.view.EmojiKeyboardView;

public class ZKeyboardService extends InputMethodService {

    private EmojiKeyboardView emojiKeyboardView;
    private InputConnection inputConnection;
    private InputMethodManager previousInputMethodManager;
    private IBinder iBinder;

    @Override
    public void onWindowShown() {
        emojiKeyboardView.refreshSomeView();
        MobclickAgent.onResume(this);
    }

    @Override
    public void onWindowHidden() {
        MobclickAgent.onPause(this);
    }

    public ZKeyboardService() {
        super();
        if (Build.VERSION.SDK_INT >= 17) {
            enableHardwareAcceleration();
        }
    }


    @Override
    public View onCreateInputView() {
        previousInputMethodManager = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
        iBinder = this.getWindow().getWindow().getAttributes().token;
        emojiKeyboardView = (EmojiKeyboardView) getLayoutInflater().inflate(R.layout.emoji_keyboard_layout, null);
        return emojiKeyboardView.getView();
    }

    @Override
    public void onStartInput(EditorInfo attribute, boolean restarting) {
        super.onStartInputView(attribute, restarting);
        inputConnection = getCurrentInputConnection();
    }

    //让编辑器执行一个它可以完成的操作。 参数  editorAction  必须是动作常量EditorInfo.editorType中的一个
    public void sendText(String text, boolean directSend) {
        inputConnection.commitText(text, 1);
        if (directSend) {
            inputConnection.performEditorAction(EditorInfo.IME_ACTION_SEND);
        }
    }

    private void sendDownKeyEvent(int keyEventCode, int flags) {
        inputConnection.sendKeyEvent(
                new KeyEvent(
                        SystemClock.uptimeMillis(),
                        SystemClock.uptimeMillis(),
                        KeyEvent.ACTION_DOWN,
                        keyEventCode,
                        0,
                        flags
                )
        );
    }

    private void sendUpKeyEvent(int keyEventCode, int flags) {
        inputConnection.sendKeyEvent(
                new KeyEvent(
                        SystemClock.uptimeMillis(),
                        SystemClock.uptimeMillis(),
                        KeyEvent.ACTION_UP,
                        keyEventCode,
                        0,
                        flags
                )
        );
    }

    //按下某键
    public void sendDownAndUpKeyEvent(int keyEventCode, int flags) {
//        sendDownKeyEvent(keyEventCode, flags);
//        sendUpKeyEvent(keyEventCode, flags);
        sendDownUpKeyEvents(keyEventCode);
    }

    public void showInputMethodPicker() {
        Vibrator vib = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        vib.vibrate(25);
        previousInputMethodManager.showInputMethodPicker();
    }

    public void switchToPreviousInputMethod() {
        Vibrator vib = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        vib.vibrate(25);
        try {
            previousInputMethodManager.switchToLastInputMethod(iBinder);
        } catch (Throwable t) { // java.lang.NoSuchMethodError if API_level<11
            CharSequence text = "Unfortunately input method switching isn't supported in your version of Android! You will have to do it manually :(";
            Prompt.showToast(text);
        }
    }

    public void hideInputMethod() {
        hideWindow();
    }

    public void deleteAllText() {
        inputConnection.deleteSurroundingText(Integer.MAX_VALUE, Integer.MAX_VALUE);
    }
}
