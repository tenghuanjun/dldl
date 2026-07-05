package com.youme.voiceengine;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
public interface YouMeCallBackInterfacePcm {
    void onPcmDataMix(int i, int i2, int i3, byte[] bArr);

    void onPcmDataRecord(int i, int i2, int i3, byte[] bArr);

    void onPcmDataRemote(int i, int i2, int i3, byte[] bArr);
}
