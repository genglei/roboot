package com.hehe.db;

import com.j256.ormlite.field.DatabaseField;

import java.io.Serializable;

/* loaded from: classes.dex */
public class TimeFrame implements Serializable {
    @DatabaseField(columnName = "endTime")
    public String endTime;
    @DatabaseField(generatedId = true)
    public Integer id;
    @DatabaseField(columnName = "startTime")
    public String startTime;

    public TimeFrame() {
    }

    public TimeFrame(String str, String str2) {
        this.startTime = str;
        this.endTime = str2;
    }

    public Integer getId() {
        return this.id;
    }

    public String getStartTime() {
        return this.startTime;
    }

    public void setStartTime(String str) {
        this.startTime = str;
    }

    public String getEndTime() {
        return this.endTime;
    }

    public void setEndTime(String str) {
        this.endTime = str;
    }

    public String toString() {
        return "TimeFrame{id=" + this.id + ", startTime='" + this.startTime + "', endTime='" + this.endTime + "'}";
    }
}
