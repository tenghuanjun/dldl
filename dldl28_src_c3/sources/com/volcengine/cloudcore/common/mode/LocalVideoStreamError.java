package com.volcengine.cloudcore.common.mode;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public enum LocalVideoStreamError {
    LOCAL_VIDEO_STREAM_ERROR_OK(0),
    LOCAL_VIDEO_STREAM_ERROR_FAILURE(1),
    LOCAL_VIDEO_STREAM_ERROR_DEVICE_NO_PERMISSION(2),
    LOCAL_VIDEO_STREAM_ERROR_DEVICE_BUSY(3),
    LOCAL_VIDEO_STREAM_ERROR_DEVICE_NOT_FOUND(4),
    LOCAL_VIDEO_STREAM_ERROR_CAPTURE_FAILURE(5),
    LOCAL_VIDEO_STREAM_ERROR_ENCODE_FAILURE(6),
    LOCAL_VIDEO_STREAM_ERROR_DEVICE_DISCONNECTED(7);

    private int value;

    LocalVideoStreamError(int i) {
        this.value = i;
    }

    public static LocalVideoStreamError from(int i) {
        switch (i) {
            case 0:
                return LOCAL_VIDEO_STREAM_ERROR_OK;
            case 1:
                return LOCAL_VIDEO_STREAM_ERROR_FAILURE;
            case 2:
                return LOCAL_VIDEO_STREAM_ERROR_DEVICE_NO_PERMISSION;
            case 3:
                return LOCAL_VIDEO_STREAM_ERROR_DEVICE_BUSY;
            case 4:
                return LOCAL_VIDEO_STREAM_ERROR_DEVICE_NOT_FOUND;
            case 5:
                return LOCAL_VIDEO_STREAM_ERROR_CAPTURE_FAILURE;
            case 6:
                return LOCAL_VIDEO_STREAM_ERROR_ENCODE_FAILURE;
            default:
                return LOCAL_VIDEO_STREAM_ERROR_DEVICE_DISCONNECTED;
        }
    }
}
