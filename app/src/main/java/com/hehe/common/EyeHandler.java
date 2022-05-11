package com.hehe.common;

import android.os.Handler;
import android.os.Message;

import com.hehe.ui.RobotMainActivity;

import java.lang.ref.WeakReference;

/* loaded from: classes.dex */
public class EyeHandler extends Handler {
    private final WeakReference<RobotMainActivity> mActivity;

    @Override // android.os.Handler
    public void handleMessage(Message message) {
    }

    public EyeHandler(RobotMainActivity robotMainActivity) {
        this.mActivity = new WeakReference<>(robotMainActivity);
    }
}
