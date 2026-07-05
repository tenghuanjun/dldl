package com.huya.force.export.audiocapture;

import android.content.Context;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class AudioCaptureInput {
    AudioCaptureType mAudioCaptureType;
    int mBitsPerSample;
    int mChannels;
    Context mContext;
    int mSampleRate;

    public enum AudioCaptureType {
        kAudioEngineCapture,
        kAudioHardCapture,
        kCustom
    }

    public AudioCaptureInput(Context context, int i, int i2, int i3, AudioCaptureType audioCaptureType) {
        this.mContext = context;
        this.mSampleRate = i;
        this.mChannels = i2;
        this.mBitsPerSample = i3;
        this.mAudioCaptureType = audioCaptureType;
    }

    public Context getContext() {
        return this.mContext;
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

    public AudioCaptureType getAudioCaptureType() {
        return this.mAudioCaptureType;
    }
}
