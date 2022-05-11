package com.hehe.bean;

import java.util.Comparator;

/* loaded from: classes.dex */
public class CriuseRouteCompare implements Comparator<CriusePointModel> {
    public int compare(CriusePointModel criusePointModel, CriusePointModel criusePointModel2) {
        return Integer.valueOf(criusePointModel.selectNum).compareTo(Integer.valueOf(criusePointModel2.selectNum));
    }
}
