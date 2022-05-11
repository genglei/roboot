package com.hehe.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;

/* loaded from: classes.dex */
public class DensityUtil {
    private static final String TAG = "DensityUtil";
    private static DisplayMetrics dm;
    private static float dmDensityDpi;
    private static float scale;

    public DensityUtil(Context context) {
        dm = new DisplayMetrics();
        dm = context.getApplicationContext().getResources().getDisplayMetrics();
        setDmDensityDpi(dm.densityDpi);
        scale = getDmDensityDpi() / 160.0f;
    }

    public static float getDmDensityDpi() {
        return dmDensityDpi;
    }

    public static void setDmDensityDpi(float f) {
        dmDensityDpi = f;
    }

    public static int dip2px(float f) {
        return (int) ((f * scale) + 0.5f);
    }

    public int px2dip(float f) {
        return (int) ((f / scale) + 0.5f);
    }

    public String toString() {
        return " dmDensityDpi:" + dmDensityDpi;
    }

    private DensityUtil() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    public static int getScreenWidth(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }

    public static int getScreenHeight(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.heightPixels;
    }

    public static int getStatusHeight(Context context) {
        try {
            Class<?> cls = Class.forName("com.android.internal.R$dimen.xml");
            return context.getResources().getDimensionPixelSize(Integer.parseInt(cls.getField("status_bar_height").get(cls.newInstance()).toString()));
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static Bitmap snapShotWithStatusBar(Activity activity) {
        View decorView = activity.getWindow().getDecorView();
        decorView.setDrawingCacheEnabled(true);
        decorView.buildDrawingCache();
        Bitmap createBitmap = Bitmap.createBitmap(decorView.getDrawingCache(), 0, 0, getScreenWidth(activity), getScreenHeight(activity));
        decorView.destroyDrawingCache();
        return createBitmap;
    }

    public static Bitmap snapShotWithoutStatusBar(Activity activity) {
        View decorView = activity.getWindow().getDecorView();
        decorView.setDrawingCacheEnabled(true);
        decorView.buildDrawingCache();
        Bitmap drawingCache = decorView.getDrawingCache();
        Rect rect = new Rect();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
        int i = rect.top;
        Bitmap createBitmap = Bitmap.createBitmap(drawingCache, 0, i, getScreenWidth(activity), getScreenHeight(activity) - i);
        decorView.destroyDrawingCache();
        return createBitmap;
    }
}
