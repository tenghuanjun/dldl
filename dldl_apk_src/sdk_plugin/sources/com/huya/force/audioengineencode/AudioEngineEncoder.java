package com.huya.force.audioengineencode;

import com.huya.force.export.audioencode.BaseAudioEncoder;
import com.huya.force.export.audioencode.IAudioEncodeInput;
import com.huya.force.log.ForceLog;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class AudioEngineEncoder extends BaseAudioEncoder {
    static final String TAG = "AudioEngineEncoder";
    AudioEngineEncodeInput mAudioEngineEncodeInput;
    private BaseAudioEncoder.Listener mListener;

    public enum AudioQuality {
        kLowQuality,
        kHighQuality
    }

    private native void Init();

    private native void PushPcmData(byte[] bArr, int i, long j);

    private native void SetCallback(Object obj);

    private native void Start(int i);

    private native void Stop();

    private native void Uninit();

    static {
        System.loadLibrary("hyaudioengine");
        System.loadLibrary("hyaudioengineencode");
    }

    public AudioEngineEncoder(IAudioEncodeInput iAudioEncodeInput) {
        super(iAudioEncodeInput);
        this.mAudioEngineEncodeInput = (AudioEngineEncodeInput) iAudioEncodeInput;
    }

    @Override // com.huya.force.export.audioencode.BaseAudioEncoder
    public void init() {
        ForceLog.info(TAG, "init");
        Init();
    }

    @Override // com.huya.force.export.audioencode.BaseAudioEncoder
    public void setListener(BaseAudioEncoder.Listener listener) {
        this.mListener = listener;
        SetCallback(this);
    }

    @Override // com.huya.force.export.audioencode.BaseAudioEncoder
    public void start() {
        ForceLog.info(TAG, "start");
        Start(this.mAudioEngineEncodeInput.getAudioQuality().ordinal());
    }

    @Override // com.huya.force.export.audioencode.BaseAudioEncoder
    public void pushPcmData(byte[] bArr, int i, long j) {
        PushPcmData(bArr, i, j);
    }

    @Override // com.huya.force.export.audioencode.BaseAudioEncoder
    public void stop() {
        ForceLog.info(TAG, "stop");
        Stop();
    }

    @Override // com.huya.force.export.audioencode.BaseAudioEncoder
    public void uninit() {
        ForceLog.info(TAG, "uninit");
        Uninit();
    }

    public void onEncodedData(byte[] bArr, long j) {
        BaseAudioEncoder.Listener listener = this.mListener;
        if (listener != null) {
            listener.onEncodedData(bArr, j, false);
        }
    }

    public void onEncodeError(int i) {
        BaseAudioEncoder.Listener listener = this.mListener;
        if (listener != null) {
            listener.onEncodeError(i);
        }
    }
}
