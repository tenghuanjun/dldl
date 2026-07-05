package com.huya.force.export.upload;

import com.huya.force.common.VideoEncodeType;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class UploadInput {
    AudioParam mAudioParam;
    VideoParam mVideoParam;

    public enum AacPorfile {
        kInvalid,
        kMain,
        kLC
    }

    public static class VideoParam {
        int mFrameRate;
        int mHeight;
        int mVideoBitrate;
        VideoEncodeType mVideoEncodeType;
        int mWidth;

        public VideoParam(int i, int i2, int i3, int i4, VideoEncodeType videoEncodeType) {
            this.mWidth = i;
            this.mHeight = i2;
            this.mFrameRate = i3;
            this.mVideoBitrate = i4;
            this.mVideoEncodeType = videoEncodeType;
        }

        public int getWidth() {
            return this.mWidth;
        }

        public int getHeight() {
            return this.mHeight;
        }

        public int getFrameRate() {
            return this.mFrameRate;
        }

        public int getVideoBitrate() {
            return this.mVideoBitrate;
        }

        public VideoEncodeType getVideoEncodeType() {
            return this.mVideoEncodeType;
        }
    }

    public static class AudioParam {
        AacPorfile mAacProfile;
        int mAudioBitrate;
        int mBitsPerSample;
        int mChannels;
        int mSampleRate;

        public AudioParam(AacPorfile aacPorfile, int i, int i2, int i3, int i4) {
            this.mAacProfile = aacPorfile;
            this.mChannels = i;
            this.mSampleRate = i2;
            this.mBitsPerSample = i3;
            this.mAudioBitrate = i4;
        }

        public AacPorfile getAacProfile() {
            return this.mAacProfile;
        }

        public int getChannels() {
            return this.mChannels;
        }

        public int getSampleRate() {
            return this.mSampleRate;
        }

        public int getBitsPerSample() {
            return this.mBitsPerSample;
        }

        public int getAudioBitrate() {
            return this.mAudioBitrate;
        }
    }

    public UploadInput(VideoParam videoParam, AudioParam audioParam) {
        this.mVideoParam = videoParam;
        this.mAudioParam = audioParam;
    }

    public VideoParam getVideoParam() {
        return this.mVideoParam;
    }

    public AudioParam getAudioParam() {
        return this.mAudioParam;
    }
}
