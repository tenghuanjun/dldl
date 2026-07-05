package com.huya.force.export.videoencode;

import com.huya.force.common.VideoEncodeType;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class VideoEncodeInput {
    int mBitrate;
    BitrateMode mBitrateMode;
    int mFrameRate;
    int mHeight;
    VideoEncodeType mVideoEncodeType;
    int mWidth;

    public enum BitrateMode {
        kModeNone,
        kModeVbr,
        kModeCbr
    }

    public VideoEncodeInput(int i, int i2, int i3, int i4, BitrateMode bitrateMode, VideoEncodeType videoEncodeType) {
        this.mWidth = i;
        this.mHeight = i2;
        this.mBitrate = i3;
        this.mFrameRate = i4;
        this.mBitrateMode = bitrateMode;
        this.mVideoEncodeType = videoEncodeType;
    }

    public int getWidth() {
        return this.mWidth;
    }

    public int getHeight() {
        return this.mHeight;
    }

    public int getBitrate() {
        return this.mBitrate;
    }

    public int getFrameRate() {
        return this.mFrameRate;
    }

    public BitrateMode getBitrateMode() {
        return this.mBitrateMode;
    }

    public VideoEncodeType getVideoEncodeType() {
        return this.mVideoEncodeType;
    }
}
