package com.huya.force.common;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class VideoData {
    public byte[] data;
    public long dts;
    public boolean isHeader;
    public boolean isKeyFrame;
    public int len;
    public long pts;

    public VideoData(byte[] bArr, int i, long j, long j2, boolean z, boolean z2) {
        this.data = bArr;
        this.len = i;
        this.pts = j;
        this.dts = j2;
        this.isKeyFrame = z;
        this.isHeader = z2;
    }

    public VideoData() {
    }
}
