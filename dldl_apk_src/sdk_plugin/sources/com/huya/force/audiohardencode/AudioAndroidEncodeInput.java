package com.huya.force.audiohardencode;

import com.huya.force.export.audioencode.IAudioEncodeInput;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class AudioAndroidEncodeInput implements IAudioEncodeInput {
    int mBitrate;
    int mBitsPerSample;
    int mChannels;
    int mSampleRate;

    public AudioAndroidEncodeInput(int i, int i2, int i3, int i4) {
        this.mSampleRate = i;
        this.mChannels = i2;
        this.mBitsPerSample = i3;
        this.mBitrate = i4;
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

    public int getBitrate() {
        return this.mBitrate;
    }
}
