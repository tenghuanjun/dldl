package com.alipay.face.photinus;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public enum VideoFormatConfig {
    S(50, 1000000),
    M(40, 2000000),
    L(30, 3000000);

    private int bitRate;
    private int frameRate;

    VideoFormatConfig(int i, int i2) {
        this.frameRate = i;
        this.bitRate = i2;
    }

    public int getFrameRate() {
        return this.frameRate;
    }

    public int getBitRate() {
        return this.bitRate;
    }
}
