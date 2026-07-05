package com.ss.android.socialbase.downloader.segment;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes.dex */
public final class Buffer {
    public final byte[] data;
    public Buffer next;
    IOutput output;
    public int size;

    public Buffer(int i) {
        this.data = new byte[i];
    }
}
