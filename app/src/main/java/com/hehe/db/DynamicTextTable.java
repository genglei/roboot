package com.hehe.db;

import com.j256.ormlite.field.DatabaseField;

import java.io.Serializable;

/* loaded from: classes.dex */
public class DynamicTextTable implements Serializable {
    @DatabaseField(generatedId = true)
    public Integer id;
    @DatabaseField(columnName = "lang")
    public String lang;
    @DatabaseField(columnName = "text")
    public String text;
    @DatabaseField(columnName = "type")
    public int type;
    @DatabaseField(columnName = "updateTime")
    public long updateTime;

    public DynamicTextTable() {
    }

    public DynamicTextTable(Integer num, String str, String str2, int i, long j) {
        this.id = num;
        this.text = str;
        this.lang = str2;
        this.type = i;
        this.updateTime = j;
    }

    public String toString() {
        return "DynamicTextTable{id=" + this.id + ", text='" + this.text + "', lang='" + this.lang + "', type=" + this.type + ", updateTime=" + this.updateTime + '}';
    }
}
