package com.hehe.utils;

import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.hehe.HiggsApplication;
import com.hehe.R;

/* loaded from: classes.dex */
public class CusToast {
    private static String oldMsg;
    private static long oneTime;
    protected static Toast toast;
    private static TextView tv_toast;
    private static long twoTime;

    public static void showToast(String str) {
        if (toast == null) {
            toast = new Toast(HiggsApplication.getInstance());
            View inflate = View.inflate(HiggsApplication.getInstance(), R.layout.view_toast, null);
            tv_toast = (TextView) inflate.findViewById(R.id.toast_textview);
            tv_toast.setText(str);
            toast.setView(inflate);
            toast.setDuration(Toast.LENGTH_SHORT);
            toast.setGravity(87, 0, 100);
            toast.show();
            oneTime = System.currentTimeMillis();
        } else {
            TextView textView = tv_toast;
            if (textView != null) {
                textView.setText(str);
            }
            twoTime = System.currentTimeMillis();
            if (!str.equals(oldMsg)) {
                oldMsg = str;
                toast.show();
            } else if (twoTime - oneTime > 0) {
                toast.show();
            }
        }
        oneTime = twoTime;
    }
}
