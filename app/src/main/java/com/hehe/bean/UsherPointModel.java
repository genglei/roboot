package com.hehe.bean;

/* loaded from: classes.dex */
public class UsherPointModel {
    public boolean isSelect;
    public String pointAlias;
    public String pointReal;

    public UsherPointModel() {
    }

    public UsherPointModel(String str, String str2, boolean z) {
        this.pointAlias = str;
        this.pointReal = str2;
        this.isSelect = z;
    }

    public String toString() {
        return "UsherPointModel{pointAlias='" + this.pointAlias + "', pointReal='" + this.pointReal + "', isSelect=" + this.isSelect + '}';
    }
}
