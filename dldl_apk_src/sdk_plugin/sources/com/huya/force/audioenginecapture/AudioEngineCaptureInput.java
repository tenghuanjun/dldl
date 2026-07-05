package com.huya.force.audioenginecapture;

import android.content.Context;
import com.huya.force.export.audiocapture.AudioCaptureInput;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class AudioEngineCaptureInput extends AudioCaptureInput {
    private long mStreamId;
    private AudioStreamUsage mUsage;

    public enum AudioStreamUsage {
        kLocal,
        kNetwork
    }

    public AudioEngineCaptureInput(Context context, int i, int i2, int i3, AudioStreamUsage audioStreamUsage, long j) {
        super(context, i, i2, i3, AudioCaptureInput.AudioCaptureType.kAudioEngineCapture);
        this.mUsage = audioStreamUsage;
        this.mStreamId = j;
    }

    public AudioStreamUsage getUsage() {
        return this.mUsage;
    }

    public long getStreamId() {
        return this.mStreamId;
    }

    public void setStreamId(long j) {
        this.mStreamId = j;
    }
}
