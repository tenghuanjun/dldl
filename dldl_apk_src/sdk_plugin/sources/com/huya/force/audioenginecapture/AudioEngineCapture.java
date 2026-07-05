package com.huya.force.audioenginecapture;

import android.content.Context;
import android.os.SystemClock;
import com.huya.force.export.audiocapture.AudioCaptureInput;
import com.huya.force.export.audiocapture.BaseAudioCapture;
import com.huya.force.log.ForceLog;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class AudioEngineCapture extends BaseAudioCapture {
    static final String TAG = "AudioEngineCapture";
    private AudioBuffer mAudioBuffer;
    AudioEngineCaptureInput mAudioCaptureInput;
    private long mNativePtr;

    private native void EnableEchoCancellation(long j, boolean z);

    private native void EnableNoiseSuppression(long j, boolean z);

    private native long Init(Context context);

    private native void SetCallback(long j, Object obj, ByteBuffer byteBuffer);

    private native void SetMute(long j, boolean z);

    private native long StartCapture(long j, int i, int i2, int i3, int i4, long j2);

    private native void StopCapture(long j);

    private native void UnInit(long j);

    @Override // com.huya.force.export.audiocapture.BaseAudioCapture
    public byte[] mix(byte[][] bArr) {
        return new byte[0];
    }

    static {
        System.loadLibrary("hyaudioengine");
        System.loadLibrary("hyaudioenginecapture");
    }

    public AudioEngineCapture(AudioCaptureInput audioCaptureInput) {
        super(audioCaptureInput);
        this.mAudioBuffer = new AudioBuffer();
        this.mNativePtr = 0L;
        this.mAudioCaptureInput = (AudioEngineCaptureInput) audioCaptureInput;
    }

    @Override // com.huya.force.export.audiocapture.BaseAudioCapture
    public void init() {
        ForceLog.info(TAG, "init");
        this.mNativePtr = Init(this.mAudioCaptureInput.getContext());
    }

    @Override // com.huya.force.export.audiocapture.BaseAudioCapture
    public boolean enableEchoCancellation(boolean z) {
        if (!checkNativePtr()) {
            return false;
        }
        EnableEchoCancellation(this.mNativePtr, z);
        return true;
    }

    @Override // com.huya.force.export.audiocapture.BaseAudioCapture
    public boolean enableNoiseSuppression(boolean z) {
        if (!checkNativePtr()) {
            return false;
        }
        EnableNoiseSuppression(this.mNativePtr, z);
        return true;
    }

    @Override // com.huya.force.export.audiocapture.BaseAudioCapture
    public void setListener(BaseAudioCapture.Listener listener) {
        if (checkNativePtr()) {
            this.mListener = listener;
            SetCallback(this.mNativePtr, this, this.mAudioBuffer.getByteBuffer());
        }
    }

    @Override // com.huya.force.export.audiocapture.BaseAudioCapture
    public void start() {
        if (checkNativePtr()) {
            ForceLog.info(TAG, "start");
            this.mAudioCaptureInput.setStreamId(StartCapture(this.mNativePtr, this.mAudioCaptureInput.getSampleRate(), this.mAudioCaptureInput.getChannels(), this.mAudioCaptureInput.getBitsPerSample(), this.mAudioCaptureInput.getUsage().ordinal(), this.mAudioCaptureInput.getStreamId()));
        }
    }

    @Override // com.huya.force.export.audiocapture.BaseAudioCapture
    public void stop() {
        if (checkNativePtr()) {
            ForceLog.info(TAG, "stop");
            StopCapture(this.mNativePtr);
        }
    }

    @Override // com.huya.force.export.audiocapture.BaseAudioCapture
    public void uninit() {
        if (checkNativePtr()) {
            ForceLog.info(TAG, "uninit");
            UnInit(this.mNativePtr);
            this.mNativePtr = 0L;
        }
    }

    @Override // com.huya.force.export.audiocapture.BaseAudioCapture
    public void setMute(boolean z) {
        SetMute(this.mNativePtr, z);
    }

    private boolean checkNativePtr() {
        return this.mNativePtr != 0;
    }

    public void onCaptureData(int i, int i2) {
        if (this.mListener != null) {
            this.mListener.onCaptureData(this.mAudioBuffer.getBytes(), this.mAudioBuffer.updateBytes(i, i2), SystemClock.elapsedRealtime());
        }
    }
}
