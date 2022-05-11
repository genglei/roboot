package com.hehe.bean;

import java.io.Serializable;

/* loaded from: classes.dex */
public class SwitchMode implements Serializable {
    private static final long serialVersionUID = 1;
    public int imgId;
    public String name;

    public SwitchMode(String str, int i) {
        this.name = str;
        this.imgId = i;
    }
}
