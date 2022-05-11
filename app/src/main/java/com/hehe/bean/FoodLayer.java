package com.hehe.bean;

import java.io.Serializable;

/* loaded from: classes.dex */
public class FoodLayer implements Serializable {
    private static final long serialVersionUID = 1;
    public int hasValue;
    public String name;
    public String name2;
    public int status = -1;

    public FoodLayer() {
    }

    public FoodLayer(String str, String str2) {
        this.name = str;
        this.name2 = str2;
    }

    public String toString() {
        return "Layer{name='" + this.name + "', name2='" + this.name2 + "'}";
    }
}
