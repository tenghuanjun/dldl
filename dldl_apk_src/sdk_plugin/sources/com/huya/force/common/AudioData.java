package com.huya.force.common;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class AudioData {
    public byte[] data;
    public boolean isHeader;
    public long pts;

    public AudioData(byte[] bArr, long j) {
        this(bArr, j, false);
    }

    public AudioData(byte[] bArr, long j, boolean z) {
        this.data = bArr;
        this.pts = j;
        this.isHeader = z;
    }
}
