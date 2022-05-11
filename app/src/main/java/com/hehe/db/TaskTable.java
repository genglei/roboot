package com.hehe.db;

import com.j256.ormlite.field.DatabaseField;

import java.io.Serializable;

/* loaded from: classes.dex */
public class TaskTable implements Serializable {
    private static final long serialVersionUID = 1;
    @DatabaseField(generatedId = true)
    public Integer id;
    @DatabaseField(columnName = "isCancel")
    public Boolean isCancel;
    @DatabaseField(columnName = "isShow")
    public Boolean isShow;
    @DatabaseField(columnName = "tablePointId")
    public Integer tablePointId;
    @DatabaseField(columnName = "tablePointName")
    public String tablePointName;
    @DatabaseField(columnName = "taskLayer")
    public Integer taskLayer;
    @DatabaseField(columnName = "taskState")
    public Integer taskState;

    public TaskTable() {
    }

    public TaskTable(Integer num, Integer num2, Integer num3, String str, Integer num4, Boolean bool, Boolean bool2) {
        this.id = num;
        this.taskLayer = num2;
        this.tablePointId = num3;
        this.tablePointName = str;
        this.taskState = num4;
        this.isCancel = bool;
        this.isShow = bool2;
    }

    public String toString() {
        return "TaskTable{id=" + this.id + ", taskLayer=" + this.taskLayer + ", tablePointId=" + this.tablePointId + ", tablePointName='" + this.tablePointName + "', taskState=" + this.taskState + ", isCancel=" + this.isCancel + ", isShow=" + this.isShow + '}';
    }
}
