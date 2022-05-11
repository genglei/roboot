package com.hehe.db;

import com.j256.ormlite.field.DatabaseField;

/* loaded from: classes.dex */
public class DefaultReplyTable {
    private static final String TAG = "DefaultReplyTable";
    @DatabaseField(generatedId = true)
    public Integer id;
    @DatabaseField(columnName = "replyMsg")
    public String replyMsg;
}
