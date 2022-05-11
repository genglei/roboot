package com.hehe.bean;

/* loaded from: classes.dex */
public class SocketMessageBean {
    public String message;
    public boolean noJson;
    public int type;
    public String userId;

    public String toString() {
        return "SocketMessage{type=" + this.type + ", message='" + this.message + "', userID='" + this.userId + "'}";
    }
}
