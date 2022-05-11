package com.hehe.soketjar.common_bean.RobotStatus;

/* loaded from: classes.dex */
public class CurrentPoseBean {
    private float theta;
    private float x;
    private float y;

    public float getTheta() {
        return this.theta;
    }

    public void setTheta(float f) {
        this.theta = f;
    }

    public float getX() {
        return this.x;
    }

    public void setX(float f) {
        this.x = f;
    }

    public float getY() {
        return this.y;
    }

    public void setY(float f) {
        this.y = f;
    }

    public String toString() {
        return "{x=" + this.x + ", y=" + this.y + ", theta=" + this.theta + '}';
    }
}
