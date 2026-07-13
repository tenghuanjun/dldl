package com.volcengine.cloudcore.common.mode;

/* JADX INFO: loaded from: classes3.dex */
public class LocalStreamStats {
    public final LocalAudioStreamStats mLocalAudioStreamStats;
    public final LocalVideoStreamStats mLocalVideoStreamStats;

    public static class LocalAudioStreamStats {
        public float audioLossRate;
        public int numChannels;
        public int recordSampleRate;
        public int rtt;
        public float sendKBitrate;
        public int sentSampleRate;
        public int statsInterval;

        public LocalAudioStreamStats(float f, float f2, int i, int i2, int i3, int i4, int i5) {
            this.audioLossRate = f;
            this.sendKBitrate = f2;
            this.recordSampleRate = i;
            this.statsInterval = i2;
            this.rtt = i3;
            this.numChannels = i4;
            this.sentSampleRate = i5;
        }

        public String toString() {
            return "LocalAudioStreamStats{audioLossRate=" + this.audioLossRate + ", sendKBitrate=" + this.sendKBitrate + ", recordSampleRate=" + this.recordSampleRate + ", statsInterval=" + this.statsInterval + ", rtt=" + this.rtt + ", numChannels=" + this.numChannels + ", sentSampleRate=" + this.sentSampleRate + '}';
        }
    }

    public static class LocalVideoStreamStats {
        public int codecType;
        public int encodedBitrate;
        public int encodedFrameCount;
        public int encodedFrameHeight;
        public int encodedFrameWidth;
        public int encoderOutputFrameRate;
        public int inputFrameRate;
        public int rendererOutputFrameRate;
        public int rtt;
        public int sentFrameRate;
        public float sentKBitrate;
        public int statsInterval;
        public int targetFrameRate;
        public int targetKBitrate;
        public float videoLossRate;

        public LocalVideoStreamStats(float f, int i, int i2, int i3, int i4, int i5, float f2, int i6, int i7, int i8, int i9, int i10, int i11) {
            this.sentKBitrate = f;
            this.inputFrameRate = i;
            this.sentFrameRate = i2;
            this.encoderOutputFrameRate = i3;
            this.rendererOutputFrameRate = i4;
            this.statsInterval = i5;
            this.videoLossRate = f2;
            this.rtt = i6;
            this.encodedBitrate = i7;
            this.encodedFrameWidth = i8;
            this.encodedFrameHeight = i9;
            this.encodedFrameCount = i10;
            this.codecType = i11;
        }

        public String toString() {
            return "LocalVideoStreamStats{sentKBitrate=" + this.sentKBitrate + ", inputFrameRate=" + this.inputFrameRate + ", sentFrameRate=" + this.sentFrameRate + ", encoderOutputFrameRate=" + this.encoderOutputFrameRate + ", rendererOutputFrameRate=" + this.rendererOutputFrameRate + ", targetKBitrate=" + this.targetKBitrate + ", targetFrameRate=" + this.targetFrameRate + ", statsInterval=" + this.statsInterval + ", videoLossRate=" + this.videoLossRate + ", rtt=" + this.rtt + ", encodedBitrate=" + this.encodedBitrate + ", encodedFrameWidth=" + this.encodedFrameWidth + ", encodedFrameHeight=" + this.encodedFrameHeight + ", encodedFrameCount=" + this.encodedFrameCount + ", codecType=" + this.codecType + '}';
        }
    }

    public LocalStreamStats(LocalVideoStreamStats localVideoStreamStats, LocalAudioStreamStats localAudioStreamStats) {
        this.mLocalVideoStreamStats = localVideoStreamStats;
        this.mLocalAudioStreamStats = localAudioStreamStats;
    }

    public String toString() {
        return "LocalStreamStats{mLocalVideoStreamStats=" + this.mLocalVideoStreamStats + ", mLocalAudioStreamStats=" + this.mLocalAudioStreamStats + '}';
    }
}
