package com.volcengine.androidcloud.common.model;

/* JADX INFO: loaded from: classes3.dex */
public class BriefTouchEvent {
    public int action;
    public int actionPointerId;
    public int pointerId;
    public float x;
    public float y;

    public BriefTouchEvent() {
    }

    public BriefTouchEvent(float f, float f2, int i, int i2, int i3) {
        this.x = f;
        this.y = f2;
        this.actionPointerId = i;
        this.pointerId = i2;
        this.action = i3;
    }

    public void set(float f, float f2, int i, int i2, int i3) {
        this.x = f;
        this.y = f2;
        this.actionPointerId = i;
        this.pointerId = i2;
        this.action = i3;
    }

    public String toString() {
        return "BriefTouchEvent{x=" + this.x + ", y=" + this.y + ", actionPointerId=" + this.actionPointerId + ", pointerId=" + this.pointerId + ", action=" + this.action + '}';
    }
}
