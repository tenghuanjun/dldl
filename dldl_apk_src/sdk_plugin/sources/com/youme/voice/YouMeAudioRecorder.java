package com.youme.voice;

import android.content.Context;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
public class YouMeAudioRecorder implements IAudioRecordListener {
    private static YouMeAudioRecorder s_instance;
    private IAudioRecordListener m_recordListener = null;
    private IAudioRecordListener m_recognizeListener = null;
    private AudioRecognizeType m_recognizeType = AudioRecognizeType.RECOGNIZETYPE_NO;

    public void Init(String str) {
    }

    @Override // com.youme.voice.IAudioRecordListener
    public void OnRecordVolumeChange(int i, AudioRecognizeType audioRecognizeType) {
    }

    public void SetSpeechRecognizeParam(String str, String str2) {
    }

    public void UnInit() {
    }

    public static YouMeAudioRecorder Instance() {
        if (s_instance == null) {
            s_instance = new YouMeAudioRecorder();
        }
        return s_instance;
    }

    public boolean Init(Context context) {
        AudioRecorder.Instance().Init(context);
        AudioRecorder.Instance().SetRecordListener(this);
        return true;
    }

    public void SetAudioRecordListener(IAudioRecordListener iAudioRecordListener) {
        this.m_recordListener = iAudioRecordListener;
    }

    public void SetRecognizeListener(IAudioRecordListener iAudioRecordListener) {
        this.m_recognizeListener = iAudioRecordListener;
    }

    public void SetAudioRecordParam(int i, int i2, int i3) {
        AudioRecorder.Instance().SetAudioRecordParam(i, i2, i3);
    }

    public AudioErrorCode StartSpeech(String str, long j, AudioRecognizeType audioRecognizeType) {
        this.m_recognizeType = audioRecognizeType;
        return AudioRecorder.Instance().StartRecord(j, str);
    }

    public AudioErrorCode StopSpeech() {
        return AudioRecorder.Instance().StopRecord();
    }

    public AudioErrorCode CancleSpeech() {
        return AudioRecorder.Instance().CancelRecord();
    }

    public AudioDeviceStatus GetMicrophoneStatus() {
        return AudioRecorder.Instance().GetMicrophoneStatus();
    }

    @Override // com.youme.voice.IAudioRecordListener
    public void OnRecordFinish(int i, String str, long j, String str2) {
        if (AudioRecognizeType.RECOGNIZETYPE_NO == this.m_recognizeType) {
            IAudioRecordListener iAudioRecordListener = this.m_recordListener;
            if (iAudioRecordListener != null) {
                iAudioRecordListener.OnRecordFinish(i, str, j, str2);
                return;
            }
            return;
        }
        IAudioRecordListener iAudioRecordListener2 = this.m_recognizeListener;
        if (iAudioRecordListener2 != null) {
            iAudioRecordListener2.OnRecordFinish(i, str, j, str2);
        }
    }

    @Override // com.youme.voice.IAudioRecordListener
    public byte[] OnRecordData(byte[] bArr, int i) {
        if (AudioRecognizeType.RECOGNIZETYPE_NO == this.m_recognizeType) {
            IAudioRecordListener iAudioRecordListener = this.m_recordListener;
            if (iAudioRecordListener == null) {
                return null;
            }
            iAudioRecordListener.OnRecordData(bArr, i);
            return null;
        }
        IAudioRecordListener iAudioRecordListener2 = this.m_recognizeListener;
        if (iAudioRecordListener2 == null) {
            return null;
        }
        iAudioRecordListener2.OnRecordData(bArr, i);
        return null;
    }
}
