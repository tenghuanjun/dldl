package com.huyaudbunify.inter;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public interface IIHuyaAuthCoreCallBack {
    void log(String str);

    void receiveMsg(long j, byte[] bArr);

    void sendNet(long j, int i, byte[] bArr);
}
