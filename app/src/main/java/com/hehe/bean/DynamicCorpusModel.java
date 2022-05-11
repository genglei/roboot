package com.hehe.bean;

/* loaded from: classes.dex */
public class DynamicCorpusModel {
    public int arriveWaitPointCircleInterval;
    public int arriveWaitPointCircleNum;
    public String arriveWaitPointCircleText;
    public int functionEndCircleInterval;
    public int functionEndCircleNum;
    public String functionEndCircleText;
    public int functionRunningCircleInterval;
    public int functionRunningCircleNum;
    public String functionRunningCircleText;
    public int functionStartCircleInterval;
    public int functionStartCircleNum;
    public String functionStartCircleText;
    public Integer id;
    public int targetWaitingCircleInterval;
    public int targetWaitingCircleNum;
    public String targetWaitingCircleText;
    public int type;
    public long updateTime;
    public int waitPointWaitCircleInterval;
    public int waitPointWaitCircleNum;
    public String waitPointWaitCircleText;

    public DynamicCorpusModel() {
    }

    public DynamicCorpusModel(int i, int i2, String str, int i3, int i4, String str2, int i5, int i6, String str3, int i7, int i8, String str4, int i9, int i10, String str5, int i11, int i12, String str6, int i13, long j) {
        this.functionStartCircleNum = i;
        this.functionStartCircleInterval = i2;
        this.functionStartCircleText = str;
        this.functionRunningCircleNum = i3;
        this.functionRunningCircleInterval = i4;
        this.functionRunningCircleText = str2;
        this.functionEndCircleNum = i5;
        this.functionEndCircleInterval = i6;
        this.functionEndCircleText = str3;
        this.arriveWaitPointCircleNum = i7;
        this.arriveWaitPointCircleInterval = i8;
        this.arriveWaitPointCircleText = str4;
        this.waitPointWaitCircleNum = i9;
        this.waitPointWaitCircleInterval = i10;
        this.waitPointWaitCircleText = str5;
        this.targetWaitingCircleNum = i11;
        this.targetWaitingCircleInterval = i12;
        this.targetWaitingCircleText = str6;
        this.type = i13;
        this.updateTime = j;
    }

    public String toString() {
        return "DynamicCorpusModel{id=" + this.id + ", functionStartCircleNum=" + this.functionStartCircleNum + ", functionStartCircleInterval=" + this.functionStartCircleInterval + ", functionStartCircleText='" + this.functionStartCircleText + "', functionRunningCircleNum=" + this.functionRunningCircleNum + ", functionRunningCircleInterval=" + this.functionRunningCircleInterval + ", functionRunningCircleText='" + this.functionRunningCircleText + "', functionEndCircleNum=" + this.functionEndCircleNum + ", functionEndCircleInterval=" + this.functionEndCircleInterval + ", functionEndCircleText='" + this.functionEndCircleText + "', arriveWaitPointCircleNum=" + this.arriveWaitPointCircleNum + ", arriveWaitPointCircleInterval=" + this.arriveWaitPointCircleInterval + ", arriveWaitPointCircleText='" + this.arriveWaitPointCircleText + "', waitPointWaitCircleNum=" + this.waitPointWaitCircleNum + ", waitPointWaitCircleInterval=" + this.waitPointWaitCircleInterval + ", waitPointWaitCircleText='" + this.waitPointWaitCircleText + "', targetWaitingCircleNum=" + this.targetWaitingCircleNum + ", targetWaitingCircleInterval=" + this.targetWaitingCircleInterval + ", targetWaitingCircleText='" + this.targetWaitingCircleText + "', type=" + this.type + ", updateTime=" + this.updateTime + '}';
    }
}
