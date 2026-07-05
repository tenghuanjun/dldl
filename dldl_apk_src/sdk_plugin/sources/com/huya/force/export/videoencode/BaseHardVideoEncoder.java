package com.huya.force.export.videoencode;

import com.huya.force.common.VideoFrameData;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public abstract class BaseHardVideoEncoder extends BaseVideoEncoder {
    public abstract void drainData(VideoFrameData videoFrameData);

    public BaseHardVideoEncoder(VideoEncodeInput videoEncodeInput) {
        super(videoEncodeInput);
    }
}
