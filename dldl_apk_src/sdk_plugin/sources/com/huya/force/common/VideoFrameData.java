package com.huya.force.common;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class VideoFrameData {
    public int frameBufferId = -1;
    public int textureId;
    public int textureTarget;
    public long timestamp;
    public float[] transform;

    public VideoFrameData(int i, int i2, float[] fArr, long j) {
        this.textureId = i;
        this.textureTarget = i2;
        this.transform = fArr;
        this.timestamp = j;
    }
}
