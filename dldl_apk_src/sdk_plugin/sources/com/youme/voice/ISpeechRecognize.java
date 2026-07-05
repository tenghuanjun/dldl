package com.youme.voice;

import android.content.Context;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
public interface ISpeechRecognize {
    AudioErrorCode CancleSpeech();

    boolean Init(Context context);

    void InitSpeechRecognizer(String str, String str2, AudioRecognizeType audioRecognizeType);

    void SetAudioRecordListener(IAudioRecordListener iAudioRecordListener);

    void SetAudioRecordParam(int i, int i2, int i3);

    void SetRecognizeLanguage(RecognizeLanguage recognizeLanguage);

    int StartSpeech(String str, long j);

    AudioErrorCode StopSpeech();

    void UnInit();

    void UpdateToken(String str);

    public enum RecognizeLanguage {
        RECOGNIZELANG_MANDARIN(0),
        RECOGNIZELANG_YUEYU(1),
        RECOGNIZELANG_SICHUAN(2),
        RECOGNIZELANG_HENAN(3),
        RECOGNIZELANG_ENGLISH(4),
        RECOGNIZELANG_TRADITIONAL(5);

        private int value;

        RecognizeLanguage(int i) {
            this.value = i;
        }

        public int getValue() {
            return this.value;
        }
    }
}
