package com.hehe;

import android.app.AlarmManager;
import android.app.Application;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Process;

import com.hehe.ui.SplashShowActivity;
import com.hehe.utils.CusLogcat;

import ai.yunji.water.task.RobotConnectAction;

/* loaded from: classes.dex */
public class HiggsApplication extends Application implements Thread.UncaughtExceptionHandler {
    private static final String TAG = "HiggsApplication";
    public static HiggsApplication mApp;

    public static HiggsApplication getInstance() {
        return mApp;
    }

    @Override // android.app.Application
    public void onCreate() {
        super.onCreate();
        CusLogcat.showELog(TAG, "onCreate");
        mApp = this;
        RobotConnectAction.init(this);
    }

    @Override // java.lang.Thread.UncaughtExceptionHandler
    public void uncaughtException(Thread thread, Throwable th) {
        if (th != null && thread != null) {
            String str = TAG;
            CusLogcat.showDLog(str, "uncaughtException exception = " + th.toString() + "|| at Thread = " + thread.toString());
            Intent intent = new Intent(this, SplashShowActivity.class);
            ((AlarmManager) getSystemService("alarm")).set(1, System.currentTimeMillis() + 20, PendingIntent.getActivity(this, 0, intent, 0));
            Process.killProcess(Process.myPid());
        }
    }
}
