package com.huya.ciku.apm.model;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class MediaSample {
    public static final int INVALID_VALUE = 0;
    public long beautyProcessTime = 0;
    public long bigEyeProcessTime = 0;
    public long animationProcessTime = 0;
    public long videoLinkProcessTime = 0;
    private int videoFrameRate = 0;
    private int audioFrameRate = 0;
    private int videoEmptyPacket = 0;
    private int audioEmptyPacket = 0;
    public int totalStartLive = 0;
    public int totalEndLive = 0;
    public int internetLag = 0;
    public int liveInterrupt = 0;
    public int pkTotalCount = 0;
    public int pkSuccessCount = 0;
    public int linkMicSuccessCount = 0;
    public int linkMicTotalCount = 0;

    public void tickAudioEmptyPacket() {
        this.audioEmptyPacket += 100;
    }

    public void tickVideoEmptyPacket() {
        this.videoEmptyPacket += 100;
    }

    public int getVideoEmptyPacket() {
        return this.videoEmptyPacket;
    }

    public void setVideoEmptyPacket(int i) {
        this.videoEmptyPacket = i;
    }

    public int getAudioEmptyPacket() {
        return this.audioEmptyPacket;
    }

    public void setAudioEmptyPacket(int i) {
        this.audioEmptyPacket = i;
    }

    public long getBeautyProcessTime() {
        return this.beautyProcessTime;
    }

    public void setBeautyProcessTime(long j) {
        this.beautyProcessTime = j;
    }

    public int getVideoFrameRate() {
        return this.videoFrameRate;
    }

    public void setVideoFrameRate(int i) {
        this.videoFrameRate = i;
    }

    public int getAudioFrameRate() {
        return this.audioFrameRate;
    }

    public void setAudioFrameRate(int i) {
        this.audioFrameRate = i;
    }

    public void reset() {
        this.videoLinkProcessTime = 0L;
        this.animationProcessTime = 0L;
        this.beautyProcessTime = 0L;
        this.bigEyeProcessTime = 0L;
        this.videoFrameRate = 0;
        this.audioFrameRate = 0;
        this.audioEmptyPacket = 0;
        this.videoEmptyPacket = 0;
        this.totalStartLive = 0;
        this.totalEndLive = 0;
        this.internetLag = 0;
        this.liveInterrupt = 0;
        this.pkTotalCount = 0;
        this.linkMicSuccessCount = 0;
        this.linkMicTotalCount = 0;
        this.pkSuccessCount = 0;
    }

    public void increase(MediaSample mediaSample) {
        this.beautyProcessTime += mediaSample.beautyProcessTime;
        if (mediaSample.audioFrameRate != 0) {
            this.audioFrameRate++;
        }
        if (mediaSample.videoFrameRate != 0) {
            this.videoFrameRate++;
        }
    }

    public void increaseVideoFrameRate() {
        this.videoFrameRate++;
    }

    public void increaseAudioFrameRate() {
        this.audioFrameRate++;
    }

    public void clone(MediaSample mediaSample) {
        if (mediaSample != null) {
            this.videoLinkProcessTime = mediaSample.videoLinkProcessTime;
            this.animationProcessTime = mediaSample.animationProcessTime;
            this.beautyProcessTime = mediaSample.beautyProcessTime;
            this.audioFrameRate = mediaSample.audioFrameRate;
            this.videoFrameRate = mediaSample.videoFrameRate;
            this.audioEmptyPacket = mediaSample.audioEmptyPacket;
            this.videoEmptyPacket = mediaSample.videoEmptyPacket;
            this.bigEyeProcessTime = mediaSample.bigEyeProcessTime;
            this.totalStartLive = mediaSample.totalStartLive;
            this.totalEndLive = mediaSample.totalEndLive;
            this.internetLag = mediaSample.internetLag;
            this.liveInterrupt = mediaSample.liveInterrupt;
            this.pkTotalCount = mediaSample.pkTotalCount;
            this.linkMicSuccessCount = mediaSample.linkMicSuccessCount;
            this.linkMicTotalCount = mediaSample.linkMicTotalCount;
            this.pkSuccessCount = mediaSample.pkSuccessCount;
        }
    }
}
