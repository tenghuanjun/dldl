package com.youme.voiceengine;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
public interface YouMeCallBackInterfacePcmForUnity {
    void onPcmDataMix(int i, int i2, int i3, YouMePcmDataForUnity youMePcmDataForUnity);

    void onPcmDataRecord(int i, int i2, int i3, YouMePcmDataForUnity youMePcmDataForUnity);

    void onPcmDataRemote(int i, int i2, int i3, YouMePcmDataForUnity youMePcmDataForUnity);
}
