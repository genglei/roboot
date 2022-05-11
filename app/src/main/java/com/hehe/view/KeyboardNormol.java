package com.hehe.view;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.media.SoundPool;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;

import com.hehe.R;
import com.hehe.ui.RobotMainActivity;

import java.util.List;

/* loaded from: classes.dex */
public class KeyboardNormol {
    private EditText ed;
    private Keyboard k1;
    private Keyboard k2;
    private KeyboardView keyboardView;
    private Activity mActivity;
    private int mSoundID;
    private SoundPool mSoundPool;
    public boolean isnun = false;
    public boolean isupper = true;
    private KeyboardView.OnKeyboardActionListener listener = new KeyboardView.OnKeyboardActionListener() { // from class: com.higgs.deliveryrobot.view.KeyboardNormol.1
        @Override // android.inputmethodservice.KeyboardView.OnKeyboardActionListener
        public void onRelease(int i) {
        }

        @Override // android.inputmethodservice.KeyboardView.OnKeyboardActionListener
        public void onText(CharSequence charSequence) {
        }

        @Override // android.inputmethodservice.KeyboardView.OnKeyboardActionListener
        public void swipeDown() {
        }

        @Override // android.inputmethodservice.KeyboardView.OnKeyboardActionListener
        public void swipeLeft() {
        }

        @Override // android.inputmethodservice.KeyboardView.OnKeyboardActionListener
        public void swipeRight() {
        }

        @Override // android.inputmethodservice.KeyboardView.OnKeyboardActionListener
        public void swipeUp() {
        }

        @Override // android.inputmethodservice.KeyboardView.OnKeyboardActionListener
        public void onPress(int i) {
            playSound();
        }

        @Override // android.inputmethodservice.KeyboardView.OnKeyboardActionListener
        public void onKey(int i, int[] iArr) {
            Editable text = ed.getText();
            int selectionStart = ed.getSelectionStart();
            if (i == -3) {
                KeyboardNormol.this.hideKeyboard();
            } else if (i == -5) {
                if (text != null && text.length() > 0 && selectionStart > 0) {
                    text.delete(selectionStart - 1, selectionStart);
                }
            } else if (i == -1) {
                changeKey();
                keyboardView.setKeyboard(k1);
            } else if (i == -2) {
                if (KeyboardNormol.this.isnun) {
                    KeyboardNormol keyboardNormol = KeyboardNormol.this;
                    keyboardNormol.isnun = false;
                    keyboardView.setKeyboard(k1);
                    return;
                }
                KeyboardNormol keyboardNormol2 = KeyboardNormol.this;
                keyboardNormol2.isnun = true;
                keyboardView.setKeyboard(k2);
            } else if (i == 57419) {
                if (selectionStart > 0) {
                    ed.setSelection(selectionStart - 1);
                }
            } else if (i == 57421) {
                if (selectionStart < ed.length()) {
                    ed.setSelection(selectionStart + 1);
                }
            } else if (((RobotMainActivity) mActivity).getLayerList().get(0).status != -1 && ((RobotMainActivity) mActivity).getLayerList().get(1).status != -1 && text.length() < 5) {
                text.insert(selectionStart, Character.toString((char) i));
            }
        }
    };

    public KeyboardNormol(Activity activity, Context context, EditText editText, int i) {
        this.ed = editText;
        this.k1 = new Keyboard(context, R.xml.symbols);
        this.k2 = new Keyboard(context, R.xml.qwerty);
        this.keyboardView = (KeyboardView) activity.findViewById(i);//view  id
        this.keyboardView.setKeyboard(this.k1);
        this.keyboardView.setEnabled(true);
        this.keyboardView.setPreviewEnabled(false);
        this.keyboardView.setOnKeyboardActionListener(listener);
        this.mActivity = activity;
//        initSound();
    }

    public void setEditText(EditText editText) {
        this.ed = editText;
    }

    @SuppressLint({"DefaultLocale"})
    private void changeKey() {
        List<Keyboard.Key> keys = this.k1.getKeys();
        if (this.isupper) {
            this.isupper = false;
            for (Keyboard.Key key : keys) {
                if (key.label != null && isword(key.label.toString())) {
                    key.label = key.label.toString().toLowerCase();
                    key.codes[0] = key.codes[0] + 32;
                }
            }
            return;
        }
        this.isupper = true;
        for (Keyboard.Key key2 : keys) {
            if (key2.label != null && isword(key2.label.toString())) {
                key2.label = key2.label.toString().toUpperCase();
                key2.codes[0] = key2.codes[0] - 32;
            }
        }
    }

    public void showKeyboard() {
        int visibility = this.keyboardView.getVisibility();
        if (visibility == View.GONE || visibility == View.INVISIBLE) {
            this.keyboardView.setVisibility(View.VISIBLE);
        }
    }

    public void hideKeyboard() {
        if (this.keyboardView.getVisibility() == View.VISIBLE) {
            this.keyboardView.setVisibility(View.GONE);
        }
    }

    @SuppressLint({"DefaultLocale"})
    private boolean isword(String str) {
        return "abcdefghijklmnopqrstuvwxyz".contains(str.toLowerCase());
    }

    private void initSound() {
        this.mSoundPool = new SoundPool.Builder().build();
        this.mSoundID = this.mSoundPool.load(this.mActivity, R.raw.pressed, 1);
    }

    private void playSound() {
       // this.mSoundPool.play(this.mSoundID, 0.1f, 0.5f, 0, 0, 1.0f);
    }
}
