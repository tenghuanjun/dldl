package com.volcengine.androidcloud.common.model;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public class SimpleTouchEvent {
    public int action;
    public int pointerId;
    public float x;
    public float y;

    public SimpleTouchEvent() {
    }

    public SimpleTouchEvent(float f, float f2, int i, int i2) {
        this.x = f;
        this.y = f2;
        this.pointerId = i;
        this.action = i2;
    }

    public void set(float f, float f2, int i, int i2) {
        this.x = f;
        this.y = f2;
        this.pointerId = i;
        this.action = i2;
    }

    public String toString() {
        return "SimpleTouchEvent{x=" + this.x + ", y=" + this.y + ", pointerId=" + this.pointerId + ", action=" + this.action + '}';
    }
}
