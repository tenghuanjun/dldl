package com.youme.voice;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
public interface IAudioPlayListener {
    void OnPlayData(byte[] bArr, int i);

    void OnPlayFinish(AudioErrorCode audioErrorCode, String str);
}
