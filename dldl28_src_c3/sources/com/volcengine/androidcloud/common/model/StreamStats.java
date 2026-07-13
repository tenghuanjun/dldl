package com.volcengine.androidcloud.common.model;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public class StreamStats {
    public static final int NETWORK_QUALITY_BAD = 4;
    public static final int NETWORK_QUALITY_DOWN = 6;
    public static final int NETWORK_QUALITY_EXCELLENT = 1;
    public static final int NETWORK_QUALITY_GOOD = 2;
    public static final int NETWORK_QUALITY_POOR = 3;
    public static final int NETWORK_QUALITY_UNKNOWN = 0;
    public static final int NETWORK_QUALITY_VERY_BAD = 5;
    private final int decoderOutputFrameRate;
    private final long e2eDelay;
    private final int frozenRate;
    private final int receivedAudioBitRate;
    private final int receivedResolutionHeight;
    private final int receivedResolutionWidth;
    private final int receivedVideoBitRate;
    private final int rendererOutputFrameRate;
    private final int rtt;
    private final int stallCount;
    private final int stallDuration;
    private final String uid;
    private final float videoLossRate;

    public StreamStats(int i, int i2, int i3, int i4, int i5, int i6, int i7, float f, int i8, String str, int i9, int i10, long j) {
        this.receivedAudioBitRate = i;
        this.receivedVideoBitRate = i2;
        this.rtt = i3;
        this.decoderOutputFrameRate = i4;
        this.rendererOutputFrameRate = i5;
        this.receivedResolutionWidth = i7;
        this.receivedResolutionHeight = i6;
        this.videoLossRate = f;
        this.stallCount = i8;
        this.uid = str;
        this.stallDuration = i9;
        this.frozenRate = i10;
        this.e2eDelay = j;
    }

    public int getDecoderOutputFrameRate() {
        return this.decoderOutputFrameRate;
    }

    public long getE2eDelay() {
        return this.e2eDelay;
    }

    public int getFrozenRate() {
        return this.frozenRate;
    }

    public int getReceivedAudioBitRate() {
        return this.receivedAudioBitRate;
    }

    public int getReceivedResolutionHeight() {
        return this.receivedResolutionHeight;
    }

    public int getReceivedResolutionWidth() {
        return this.receivedResolutionWidth;
    }

    public int getReceivedVideoBitRate() {
        return this.receivedVideoBitRate;
    }

    public int getRendererOutputFrameRate() {
        return this.rendererOutputFrameRate;
    }

    public int getRtt() {
        return this.rtt;
    }

    public int getStallCount() {
        return this.stallCount;
    }

    public int getStallDuration() {
        return this.stallDuration;
    }

    public String getUid() {
        return this.uid;
    }

    public float getVideoLossRate() {
        return this.videoLossRate;
    }

    public String toString() {
        return "StreamStats{receivedVideoBitRate=" + this.receivedVideoBitRate + ", receivedAudioBitRate=" + this.receivedAudioBitRate + ", decoderOutputFrameRate=" + this.decoderOutputFrameRate + ", rendererOutputFrameRate=" + this.rendererOutputFrameRate + ", receivedResolutionHeight=" + this.receivedResolutionHeight + ", receivedResolutionWidth=" + this.receivedResolutionWidth + ", videoLossRate=" + this.videoLossRate + ", stallCount=" + this.stallCount + ", rtt=" + this.rtt + ", uid='" + this.uid + "', stallDuration=" + this.stallDuration + ", frozenRate=" + this.frozenRate + ", e2eDelay=" + this.e2eDelay + '}';
    }
}
