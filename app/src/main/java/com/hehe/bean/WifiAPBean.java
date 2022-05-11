package com.hehe.bean;

/* loaded from: classes.dex */
public class WifiAPBean {
    private int address;
    private String capabilities;
    private int frequence;
    private boolean isConnect;
    private boolean isLock;
    private int level;
    private String name;
    private int signalIcon;

    public int getLevel() {
        return this.level;
    }

    public void setLevel(int i) {
        this.level = i;
    }

    public int getFrequence() {
        return this.frequence;
    }

    public String getCapabilities() {
        return this.capabilities;
    }

    public void setCapabilities(String str) {
        this.capabilities = str;
    }

    public void setFrequence(int i) {
        this.frequence = i;
    }

    public int getAddress() {
        return this.address;
    }

    public void setAddress(int i) {
        this.address = i;
    }

    public String getName() {
        return this.name;
    }

    public int getSignalIcon() {
        return this.signalIcon;
    }

    public void setName(String str) {
        this.name = str;
    }

    public void setSignalIcon(int i) {
        this.signalIcon = i;
    }

    public void setLock(boolean z) {
        this.isLock = z;
    }

    public boolean isLock() {
        return this.isLock;
    }

    public boolean isConnect() {
        return this.isConnect;
    }

    public void setConnect(boolean z) {
        this.isConnect = z;
    }
}
