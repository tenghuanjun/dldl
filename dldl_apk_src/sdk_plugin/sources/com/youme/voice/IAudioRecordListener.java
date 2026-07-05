package com.youme.voice;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
public interface IAudioRecordListener {
    byte[] OnRecordData(byte[] bArr, int i);

    void OnRecordFinish(int i, String str, long j, String str2);

    void OnRecordVolumeChange(int i, AudioRecognizeType audioRecognizeType);
}
