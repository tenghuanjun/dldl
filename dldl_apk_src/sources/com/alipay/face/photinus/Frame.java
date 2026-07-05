package com.alipay.face.photinus;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public class Frame {
    public byte[] data;
    public FrameMetadata metadata = new FrameMetadata();
    public int rotation;

    public Frame(byte[] bArr) {
        this.data = bArr;
    }
}
