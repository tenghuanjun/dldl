package com.huya.force.export.audiocapture;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public abstract class BaseAudioCapture {
    protected Listener mListener;
    protected boolean mMute = false;

    public interface Listener {
        void onCaptureData(byte[] bArr, int i, long j);
    }

    public abstract boolean enableEchoCancellation(boolean z);

    public abstract boolean enableNoiseSuppression(boolean z);

    public abstract void init();

    public abstract byte[] mix(byte[][] bArr);

    public abstract void start();

    public abstract void stop();

    public abstract void uninit();

    public BaseAudioCapture(AudioCaptureInput audioCaptureInput) {
    }

    public void setMute(boolean z) {
        this.mMute = z;
    }

    public void setListener(Listener listener) {
        this.mListener = listener;
    }
}
