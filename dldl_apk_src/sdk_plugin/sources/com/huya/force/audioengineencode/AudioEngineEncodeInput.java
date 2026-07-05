package com.huya.force.audioengineencode;

import com.huya.force.audioengineencode.AudioEngineEncoder;
import com.huya.force.export.audioencode.IAudioEncodeInput;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class AudioEngineEncodeInput implements IAudioEncodeInput {
    AudioEngineEncoder.AudioQuality mAudioQuality;

    public AudioEngineEncodeInput(AudioEngineEncoder.AudioQuality audioQuality) {
        this.mAudioQuality = audioQuality;
    }

    public AudioEngineEncoder.AudioQuality getAudioQuality() {
        return this.mAudioQuality;
    }
}
