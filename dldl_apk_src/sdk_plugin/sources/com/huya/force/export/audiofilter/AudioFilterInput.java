package com.huya.force.export.audiofilter;

import com.huya.force.export.audiofilter.BaseAudioFilter;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class AudioFilterInput {
    int mBitsPerSample;
    int mBytesPerFrame;
    int mChannels;
    BaseAudioFilter.FilterType mFilterType;
    int mSampleRate;

    public AudioFilterInput(int i, int i2, int i3, int i4, BaseAudioFilter.FilterType filterType) {
        this.mSampleRate = i;
        this.mChannels = i2;
        this.mBitsPerSample = i3;
        this.mBytesPerFrame = i4;
        this.mFilterType = filterType;
    }

    public int getSampleRate() {
        return this.mSampleRate;
    }

    public int getChannels() {
        return this.mChannels;
    }

    public int getBitsPerSample() {
        return this.mBitsPerSample;
    }

    public int getBytesPerFrame() {
        return this.mBytesPerFrame;
    }

    public BaseAudioFilter.FilterType getFilterType() {
        return this.mFilterType;
    }
}
