package com.hehe.bean;

/* loaded from: classes.dex */
public class CriusePointModel {
    public boolean isSelect;
    public String pointAlias;
    public String pointReal;
    public int selectNum;

    public CriusePointModel() {
    }

    public CriusePointModel(String str, String str2, boolean z) {
        this.pointAlias = str;
        this.pointReal = str2;
        this.isSelect = z;
    }

    public String toString() {
        return "CriusePointModel{pointAlias='" + this.pointAlias + "', pointReal='" + this.pointReal + "', isSelect=" + this.isSelect + ", selectNum=" + this.selectNum + '}';
    }
}
