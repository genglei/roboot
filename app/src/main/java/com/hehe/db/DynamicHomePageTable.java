package com.hehe.db;

import com.j256.ormlite.field.DatabaseField;

import java.io.Serializable;

/* loaded from: classes.dex */
public class DynamicHomePageTable implements Serializable {
    @DatabaseField(columnName = "code")
    public String code;
    @DatabaseField(columnName = "commands")
    public String commands;
    @DatabaseField(columnName = "describe")
    public String describe;
    @DatabaseField(columnName = "icon")
    public String icon;
    @DatabaseField(generatedId = true)
    public Integer id;
    @DatabaseField(columnName = "lang")
    public String lang;
    @DatabaseField(columnName = "order")
    public int order;
    @DatabaseField(columnName = "title")
    public String title;
    @DatabaseField(columnName = "type")
    public int type;
    @DatabaseField(columnName = "updateTime")
    public long updateTime;

    public DynamicHomePageTable() {
    }

    public DynamicHomePageTable(Integer num, String str, String str2, int i, int i2, String str3, String str4, String str5, String str6, long j) {
        this.id = num;
        this.commands = str;
        this.lang = str2;
        this.type = i;
        this.order = i2;
        this.title = str3;
        this.code = str4;
        this.icon = str5;
        this.describe = str6;
        this.updateTime = j;
    }

    public String toString() {
        return "DynamicHomePageTable{id=" + this.id + ", commands=" + this.commands + ", lang='" + this.lang + "', type=" + this.type + ", order=" + this.order + ", title=" + this.title + ", code=" + this.code + ", icon=" + this.icon + ", describe=" + this.describe + ", updateTime=" + this.updateTime + '}';
    }
}
