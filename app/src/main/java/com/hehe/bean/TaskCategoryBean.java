package com.hehe.bean;

import java.io.Serializable;

/* loaded from: classes.dex */
public class TaskCategoryBean implements Serializable {
    private static final long serialVersionUID = 1;
    public int id;
    public String title;

    public TaskCategoryBean(String str) {
        this.title = str;
    }
}
