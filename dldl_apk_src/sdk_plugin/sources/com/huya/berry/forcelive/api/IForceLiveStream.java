package com.huya.berry.forcelive.api;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public interface IForceLiveStream {
    boolean getIsFrontCamera();

    IForceClient getMediaClient();

    void setRtmpUrl(String str);

    boolean startPush();

    void stopLive();

    void stopMediaClient();

    void test();

    void uninit();
}
