package com.huya.force.export.audioencode;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public abstract class BaseAudioEncoder {

    public interface Listener {
        void onEncodeError(int i);

        void onEncodedData(byte[] bArr, long j, boolean z);
    }

    public abstract void init();

    public abstract void pushPcmData(byte[] bArr, int i, long j);

    public abstract void setListener(Listener listener);

    public abstract void start();

    public abstract void stop();

    public abstract void uninit();

    public BaseAudioEncoder(IAudioEncodeInput iAudioEncodeInput) {
    }
}
