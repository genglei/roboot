package com.hehe.bean;

import android.text.TextUtils;

import com.hehe.HiggsApplication;

/* loaded from: classes.dex */
public class AboutUs {
    public String name;
    public String value;

    public AboutUs(String str, String str2) {
        this.name = str + "：";
        this.value = TextUtils.isEmpty(str2) ? HiggsApplication.getInstance().getResources().getString(2131558597) : str2;
    }

    public String toString() {
        return "AboutUs{name='" + this.name + "', value='" + this.value + "'}";
    }
}
