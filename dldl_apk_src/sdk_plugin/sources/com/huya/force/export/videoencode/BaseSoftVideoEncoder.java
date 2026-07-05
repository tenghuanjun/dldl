package com.huya.force.export.videoencode;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public abstract class BaseSoftVideoEncoder extends BaseVideoEncoder {
    public abstract void drainData(byte[] bArr, long j);

    public BaseSoftVideoEncoder(VideoEncodeInput videoEncodeInput) {
        super(videoEncodeInput);
    }
}
