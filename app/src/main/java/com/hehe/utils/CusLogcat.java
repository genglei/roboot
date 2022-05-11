package com.hehe.utils;

import android.util.Log;

/* loaded from: classes.dex */
public class CusLogcat {
    static boolean Camera = true;
    static boolean ClickButton = true;
    static boolean CodeReview = true;
    static boolean CommandReceive = true;
    static boolean Debug = true;
    static boolean LOCK = false;
    static boolean LifeCycle = true;
    static boolean Shangbao = true;
    static boolean Socket = true;
    static boolean SocketTest = true;
    static boolean Test = false;
    static boolean Vi = true;

    public static void showLog(String str) {
        if (Debug) {
            Log.e("HiggsDynamics", str);
        }
    }

    public static void showELog(String str, String str2) {
        if (Debug) {
            Log.e(str, str2);
        }
    }

    public static void showLifeCycleELog(String str, String str2) {
        if (LifeCycle) {
            Log.e(str, str2);
        }
    }

    public static void showShangbaoELog(String str, String str2) {
        if (Shangbao) {
            Log.e(str, str2);
        }
    }

    public static void showLockELog(String str, String str2) {
        if (LOCK) {
            Log.e(str, str2);
        }
    }

    public static void showSocketTestELog(String str, String str2) {
        if (SocketTest) {
            Log.e(str, str2);
        }
    }

    public static void showCameraELog(String str, String str2) {
        if (Camera) {
            Log.e(str, str2);
        }
    }

    public static void showDLog(String str, String str2) {
        if (Debug) {
            Log.d(str, str2);
        }
    }

    public static void showTestLog(String str, String str2) {
        if (Test) {
            Log.e(str, str2);
        }
    }

    public static void showSocketLog(String str, String str2) {
        if (Socket) {
            Log.e(str, str2);
        }
    }

    public static void showCommandReceiveLog(String str, String str2) {
        if (CommandReceive) {
            Log.e(str, str2);
        }
    }

    public static void showViELog(String str, String str2) {
        if (Vi) {
            Log.e(str, str2);
        }
    }

    public static void showClickButtonELog(String str, String str2) {
        if (ClickButton) {
            Log.e(str, str2);
        }
    }

    public static void showCodeReviewELog(String str, String str2) {
        if (CodeReview) {
            Log.e(str, str2);
        }
    }
}
