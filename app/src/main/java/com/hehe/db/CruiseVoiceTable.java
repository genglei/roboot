package com.hehe.db;

import com.j256.ormlite.field.DatabaseField;

/* loaded from: classes.dex */
public class CruiseVoiceTable {
    private static final String TAG = "CruiseVoiceTable";
    @DatabaseField(columnName = "circleInterval")
    public int circleInterval;
    @DatabaseField(columnName = "circleNum")
    public int circleNum;
    @DatabaseField(columnName = "criuseVoice")
    public String criuseVoice;
    @DatabaseField(generatedId = true)
    public Integer id;
    @DatabaseField(columnName = "pointRealName")
    public String pointRealName;
    @DatabaseField(columnName = "waitTime")
    public int waitTime;

    public CruiseVoiceTable() {
    }

    public CruiseVoiceTable(String str, String str2, int i, int i2, int i3) {
        this.criuseVoice = str;
        this.pointRealName = str2;
        this.waitTime = i;
        this.circleNum = i2;
        this.circleInterval = i3;
    }

    public String toString() {
        return "CruiseVoiceTable{id=" + this.id + ", criuseVoice='" + this.criuseVoice + "', pointRealName='" + this.pointRealName + "', waitTime=" + this.waitTime + ", circleNum=" + this.circleNum + ", circleInterval=" + this.circleInterval + '}';
    }
}
