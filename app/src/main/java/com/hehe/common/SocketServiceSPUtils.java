package com.hehe.common;

import android.content.Context;
import android.content.SharedPreferences;

/* loaded from: classes.dex */
public class SocketServiceSPUtils {
    private static final String KEY_SOCKETSERVICESTATUS = "isSocketService_started";
    private static final String SP_NAME = "com.higgs.socket_service";
    private static final String TAG = "SocketServiceSPUtils";
    private static SocketServiceSPUtils mInstance;
    private SharedPreferences mPreferences;

    public static final SocketServiceSPUtils getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new SocketServiceSPUtils(context);
        }
        return mInstance;
    }

    private SocketServiceSPUtils(Context context) {
        this.mPreferences = context.getSharedPreferences("com.higgs.socket_service", 0);
    }

    public void saveSocketServiceStatus(boolean z) {
        SharedPreferences.Editor edit = this.mPreferences.edit();
        edit.putBoolean("isSocketService_started", z);
        edit.commit();
    }

    public boolean getSocketServiceStatus() {
        return this.mPreferences.getBoolean("isSocketService_started", false);
    }
}
