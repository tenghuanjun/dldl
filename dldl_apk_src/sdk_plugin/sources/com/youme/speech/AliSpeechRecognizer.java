package com.youme.speech;

import android.content.Context;
import com.youme.voice.AudioErrorCode;
import com.youme.voice.AudioRecognizeType;
import com.youme.voice.AudioRecorder;
import com.youme.voice.IAudioRecordListener;
import com.youme.voice.ISpeechRecognize;
import com.youme.voice.YouMeAudioRecorder;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
public class AliSpeechRecognizer implements ISpeechRecognize {
    @Override // com.youme.voice.ISpeechRecognize
    public void InitSpeechRecognizer(String str, String str2, AudioRecognizeType audioRecognizeType) {
    }

    @Override // com.youme.voice.ISpeechRecognize
    public void SetRecognizeLanguage(ISpeechRecognize.RecognizeLanguage recognizeLanguage) {
    }

    @Override // com.youme.voice.ISpeechRecognize
    public void UnInit() {
    }

    @Override // com.youme.voice.ISpeechRecognize
    public void UpdateToken(String str) {
    }

    @Override // com.youme.voice.ISpeechRecognize
    public boolean Init(Context context) {
        AudioRecorder.Instance().Init(context);
        return true;
    }

    @Override // com.youme.voice.ISpeechRecognize
    public void SetAudioRecordListener(IAudioRecordListener iAudioRecordListener) {
        YouMeAudioRecorder.Instance().SetAudioRecordListener(iAudioRecordListener);
    }

    @Override // com.youme.voice.ISpeechRecognize
    public void SetAudioRecordParam(int i, int i2, int i3) {
        YouMeAudioRecorder.Instance().SetAudioRecordParam(i, i2, i3);
    }

    @Override // com.youme.voice.ISpeechRecognize
    public int StartSpeech(String str, long j) {
        return YouMeAudioRecorder.Instance().StartSpeech(str, j, AudioRecognizeType.RECOGNIZETYPE_NO).ordinal();
    }

    @Override // com.youme.voice.ISpeechRecognize
    public AudioErrorCode StopSpeech() {
        return YouMeAudioRecorder.Instance().StopSpeech();
    }

    @Override // com.youme.voice.ISpeechRecognize
    public AudioErrorCode CancleSpeech() {
        return YouMeAudioRecorder.Instance().CancleSpeech();
    }
}
