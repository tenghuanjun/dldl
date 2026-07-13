package com.volcengine.cloudcore.common.mode;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public enum LocalVideoStreamState {
    LOCAL_VIDEO_STREAM_STATE_STOPPED(0),
    LOCAL_VIDEO_STREAM_STATE_RECORDING(1),
    LOCAL_VIDEO_STREAM_STATE_ENCODING(2),
    LOCAL_VIDEO_STREAM_STATE_FAILED(3);

    public final int value;

    LocalVideoStreamState(int i) {
        this.value = i;
    }

    public static LocalVideoStreamState from(int i) {
        return i != 0 ? i != 1 ? i != 2 ? LOCAL_VIDEO_STREAM_STATE_FAILED : LOCAL_VIDEO_STREAM_STATE_ENCODING : LOCAL_VIDEO_STREAM_STATE_RECORDING : LOCAL_VIDEO_STREAM_STATE_STOPPED;
    }
}
