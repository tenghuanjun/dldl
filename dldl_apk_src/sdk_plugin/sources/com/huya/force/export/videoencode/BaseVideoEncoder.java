package com.huya.force.export.videoencode;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public abstract class BaseVideoEncoder {
    protected Listener mListener;

    public interface Listener {
        void onEncodeError(int i);

        void onEncodedData(byte[] bArr, long j, long j2, boolean z, boolean z2);
    }

    public abstract void adjustBitrate(int i);

    public VideoEncodeInput getInput() {
        return null;
    }

    public abstract void init();

    public abstract boolean isHardwareEncoder();

    public abstract void start();

    public abstract void stop();

    public abstract void uninit();

    public BaseVideoEncoder(VideoEncodeInput videoEncodeInput) {
    }

    public void setListener(Listener listener) {
        this.mListener = listener;
    }
}
