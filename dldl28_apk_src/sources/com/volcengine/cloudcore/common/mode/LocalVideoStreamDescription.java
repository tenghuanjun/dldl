package com.volcengine.cloudcore.common.mode;

/* JADX INFO: loaded from: classes3.dex */
public class LocalVideoStreamDescription {
    public final int frameRate;
    public final int height;
    public final int maxKbps;
    public final int minKbs;
    public final int width;

    public LocalVideoStreamDescription(int i, int i2, int i3, int i4, int i5) {
        this.width = i;
        this.height = i2;
        this.frameRate = i3;
        this.maxKbps = i4;
        this.minKbs = i5;
    }

    public String toString() {
        return "LocalVideoStreamDescription{width=" + this.width + ", height=" + this.height + ", frameRate=" + this.frameRate + ", maxKbps=" + this.maxKbps + '}';
    }
}
