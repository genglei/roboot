package com.hehe.bean;

/* loaded from: classes.dex */
public class Hostess {
    private static Hostess instance = new Hostess();
    private String TableName;
    private String TablePoint;

    private Hostess() {
    }

    public static Hostess getHostessInstance() {
        return instance;
    }

    public String getTableName() {
        return this.TableName;
    }

    public void setTableName(String str) {
        this.TableName = str;
    }

    public String getTablePoint() {
        return this.TablePoint;
    }

    public void setTablePoint(String str) {
        this.TablePoint = str;
    }
}
