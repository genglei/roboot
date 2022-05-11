package com.hehe.db;

import com.j256.ormlite.field.DatabaseField;

import java.io.Serializable;

/* loaded from: classes.dex */
public class PointTable implements Serializable {
    private static final String TAG = "PointTable";
    @DatabaseField(generatedId = true)
    public Integer id;
    @DatabaseField(columnName = "pointType")
    public Integer pointType;
    @DatabaseField(columnName = "ringNum")
    public byte ringNum;
    @DatabaseField(columnName = "tableAliasName")
    public String tableAliasName;
    @DatabaseField(columnName = "tableRealPoint")
    public String tableRealPoint;

    public PointTable() {
    }

    public PointTable(String str, String str2, Integer num) {
        this.tableAliasName = str;
        this.tableRealPoint = str2;
        this.pointType = num;
    }

    public PointTable(Integer num, String str, String str2, Integer num2, byte b) {
        this.id = num;
        this.tableAliasName = str;
        this.tableRealPoint = str2;
        this.pointType = num2;
        this.ringNum = b;
    }

    public String toString() {
        return "PointTable{id=" + this.id + ", tableAliasName='" + this.tableAliasName + "', tableRealPoint='" + this.tableRealPoint + "', pointType=" + this.pointType + ", ringNum=" + ((int) this.ringNum) + '}';
    }

    public String getTableAliasName() {
        return this.tableAliasName;
    }

    public void setTableAliasName(String str) {
        this.tableAliasName = str;
    }

    public String getTableRealPoint() {
        return this.tableRealPoint;
    }

    public void setTableRealPoint(String str) {
        this.tableRealPoint = str;
    }

    public Integer getPointType() {
        return this.pointType;
    }

    public void setPointType(Integer num) {
        this.pointType = num;
    }

    public byte getRingNum() {
        return this.ringNum;
    }

    public void setRingNum(byte b) {
        this.ringNum = b;
    }
}
