package com.hehe.utils;


import com.hehe.HiggsApplication;

/* loaded from: classes.dex */
public class SharedPrefsUtil {
    public static final String SETTING = "select_index";

    public static void put(String str, int i) {
        HiggsApplication.getInstance().getSharedPreferences("select_index", 0).edit().putInt(str, i).commit();
    }

    public static void put(String str, long j) {
        HiggsApplication.getInstance().getSharedPreferences("select_index", 0).edit().putLong(str, j).commit();
    }

    public static void put(String str, boolean z) {
        HiggsApplication.getInstance().getSharedPreferences("select_index", 0).edit().putBoolean(str, z).commit();
    }

    public static void put(String str, String str2) {
        HiggsApplication.getInstance().getSharedPreferences("select_index", 0).edit().putString(str, str2).commit();
    }

    public static int get(String str, int i) {
        return HiggsApplication.getInstance().getSharedPreferences("select_index", 0).getInt(str, i);
    }

    public static long get(String str, long j) {
        return HiggsApplication.getInstance().getSharedPreferences("select_index", 0).getLong(str, j);
    }

    public static boolean get(String str, boolean z) {
        return HiggsApplication.getInstance().getSharedPreferences("select_index", 0).getBoolean(str, z);
    }

    public static String get(String str, String str2) {
        return HiggsApplication.getInstance().getSharedPreferences("select_index", 0).getString(str, str2);
    }

    public static void remove(String str) {
        HiggsApplication.getInstance().getSharedPreferences("select_index", 0).edit().remove(str).commit();
    }
}
