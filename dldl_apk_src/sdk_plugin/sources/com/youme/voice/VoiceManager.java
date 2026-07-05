package com.youme.voice;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.util.Log;
import com.anthonycr.grant.PermissionsManager;
import com.anthonycr.grant.PermissionsResultAction;
import com.youme.im.CommonConst;
import com.youme.im.IMEngine;
import com.youme.im.NativeEngine;
import com.youme.im.NetworkStatusReceiver2;
import com.youme.speech.AliSpeechRecognizer;
import com.youme.speech.IflySpeechRecognizer;
import com.youme.speech.UscSpeechRecognizer;
import com.youme.voice.ISpeechRecognize;
import java.io.File;
import java.util.UUID;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
public class VoiceManager implements IAudioRecordListener, IAudioPlayListener {
    private static VoiceManager s_instance;
    private Context m_context;
    private String m_recognizeAppID;
    private String m_recognizeSecret;
    private String m_recordCacheDir = "";
    private int m_sampleRate = CommonConst.SAMPLERATE_16K;
    private int m_channel = 1;
    private int m_sampleBitSize = 16;
    private ISpeechRecognize m_speechRecognizer = null;
    private boolean m_speechOnly = false;
    private ISpeechRecognize.RecognizeLanguage m_recognizeLanguage = ISpeechRecognize.RecognizeLanguage.RECOGNIZELANG_MANDARIN;
    private AudioRecognizeType m_recognizeType = AudioRecognizeType.RECOGNIZETYPE_NO;
    private boolean m_isInit = false;
    private AudioPlayer m_audioPlayer = null;
    private float m_playVolume = 1.0f;

    public static VoiceManager Instance() {
        if (s_instance == null) {
            s_instance = new VoiceManager();
        }
        return s_instance;
    }

    public void Init(Context context) {
        Instance().m_context = context;
        if (this.m_recordCacheDir.isEmpty()) {
            SetAudioRecordCacheDir("");
        }
    }

    public static void UnInit() {
        if (Instance().m_speechRecognizer != null) {
            Instance().m_speechRecognizer.UnInit();
        }
    }

    public static void InitSpeechRecognizer(String str, String str2, int i) {
        Instance().m_recognizeAppID = str;
        Instance().m_recognizeSecret = str2;
        Instance().m_recognizeType = AudioRecognizeType.values()[i];
        if (Instance().m_recognizeType != AudioRecognizeType.RECOGNIZETYPE_IFLY_RECORD) {
            InitYouMeAudioRecord();
        }
    }

    private static void InitYouMeAudioRecord() {
        if (Instance().m_isInit) {
            return;
        }
        Instance().m_isInit = true;
        YouMeAudioRecorder.Instance().Init(Instance().m_context);
        YouMeAudioRecorder.Instance().SetAudioRecordListener(Instance());
        YouMeAudioRecorder.Instance().SetAudioRecordParam(Instance().m_sampleRate, Instance().m_channel, Instance().m_sampleBitSize);
    }

    private static void InitSpeechRecognizer() {
        if (Instance().m_speechRecognizer != null) {
            return;
        }
        if (AudioRecognizeType.RECOGNIZETYPE_IFLY_STREAM == Instance().m_recognizeType || AudioRecognizeType.RECOGNIZETYPE_IFLY_RECORD == Instance().m_recognizeType) {
            Instance().m_speechRecognizer = new IflySpeechRecognizer();
        } else if (AudioRecognizeType.RECOGNIZETYPE_USC == Instance().m_recognizeType) {
            Instance().m_speechRecognizer = new UscSpeechRecognizer();
        } else {
            if (AudioRecognizeType.RECOGNIZETYPE_ALI != Instance().m_recognizeType) {
                return;
            }
            Instance().m_speechRecognizer = new AliSpeechRecognizer();
        }
        Instance().m_speechRecognizer.Init(Instance().m_context);
        Instance().m_speechRecognizer.InitSpeechRecognizer(Instance().m_recognizeAppID, Instance().m_recognizeSecret, Instance().m_recognizeType);
        Instance().m_speechRecognizer.SetAudioRecordListener(Instance());
        Instance().m_speechRecognizer.SetAudioRecordParam(Instance().m_sampleRate, Instance().m_channel, Instance().m_sampleBitSize);
        Instance().m_speechRecognizer.SetRecognizeLanguage(Instance().m_recognizeLanguage);
    }

    public static void SetAudioRecordCacheDir(String str) {
        if (str.length() == 0) {
            Instance().m_recordCacheDir = Instance().m_context.getCacheDir().getAbsolutePath() + "/AudioRecord/";
            File file = new File(Instance().m_recordCacheDir);
            if (file.exists()) {
                return;
            }
            file.mkdirs();
            return;
        }
        File file2 = new File(str);
        if ((file2.exists() && file2.isDirectory()) || file2.mkdirs()) {
            Instance().m_recordCacheDir = str;
        } else {
            IMEngine.WriteLog(CommonConst.LogLevel.LOG_LEVEL_ERROR, "create dir failed:" + str);
        }
        if (Instance().m_recordCacheDir.isEmpty() || str.charAt(str.length() - 1) == '/') {
            return;
        }
        StringBuilder sb = new StringBuilder();
        VoiceManager voiceManagerInstance = Instance();
        sb.append(voiceManagerInstance.m_recordCacheDir);
        sb.append("/");
        voiceManagerInstance.m_recordCacheDir = sb.toString();
    }

    public static String GetAudioRecordCacheDir() {
        return Instance().m_recordCacheDir;
    }

    public static void SetAudioRecordParam(int i, int i2, int i3) {
        Instance().m_sampleRate = i;
        Instance().m_channel = i2;
        Instance().m_sampleBitSize = i3;
        if (Instance().m_recognizeType != AudioRecognizeType.RECOGNIZETYPE_IFLY_RECORD) {
            YouMeAudioRecorder.Instance().SetAudioRecordParam(Instance().m_sampleRate, Instance().m_channel, Instance().m_sampleBitSize);
        }
        if (Instance().m_speechRecognizer != null) {
            Instance().m_speechRecognizer.SetAudioRecordParam(i, i2, i3);
        }
    }

    public static void UpdateToken(String str) {
        Instance().m_recognizeSecret = str;
        if (Instance().m_speechRecognizer != null) {
            Instance().m_speechRecognizer.UpdateToken(str);
        }
    }

    public static int StartSpeech(long j) {
        if (Build.VERSION.SDK_INT >= 23 && Instance().m_context != null && (Instance().m_context instanceof Activity) && Instance().m_context.getApplicationInfo().targetSdkVersion > 22) {
            try {
                if (!PermissionsManager.getInstance().hasPermission(Instance().m_context, "android.permission.RECORD_AUDIO")) {
                    PermissionsManager.getInstance().requestPermissionsIfNecessaryForResult((Activity) Instance().m_context, new String[]{"android.permission.RECORD_AUDIO"}, new PermissionsResultAction() { // from class: com.youme.voice.VoiceManager.1
                        @Override // com.anthonycr.grant.PermissionsResultAction
                        public void onGranted() {
                            Log.e(CommonConst.LOG_TAG, " Success granted RECORD_AUDIO permission.");
                        }

                        @Override // com.anthonycr.grant.PermissionsResultAction
                        public void onDenied(String str) {
                            IMEngine.WriteLog(CommonConst.LogLevel.LOG_LEVEL_WARNING, "Decline granted RECORD_AUDIO permission.");
                        }
                    });
                    IMEngine.WriteLog(CommonConst.LogLevel.LOG_LEVEL_WARNING, "Not granted RECORD_AUDIO permission.");
                    return AudioErrorCode.AUDIOERROR_AUTHORIZE.getValue();
                }
            } catch (Throwable th) {
                Log.e(CommonConst.LOG_TAG, th.toString());
                IMEngine.WriteLog(CommonConst.LogLevel.LOG_LEVEL_ERROR, "record permission exception");
                return -1;
            }
        }
        if (Instance().m_speechRecognizer == null) {
            InitSpeechRecognizer();
        }
        String str = Instance().m_recordCacheDir + UUID.randomUUID().toString() + ".wav";
        Instance().m_speechOnly = false;
        return Instance().m_speechRecognizer.StartSpeech(str, j);
    }

    public static int StartOnlySpeech(long j) {
        if (Build.VERSION.SDK_INT >= 23 && Instance().m_context != null && (Instance().m_context instanceof Activity) && Instance().m_context.getApplicationInfo().targetSdkVersion > 22) {
            try {
                if (!PermissionsManager.getInstance().hasPermission(Instance().m_context, "android.permission.RECORD_AUDIO")) {
                    PermissionsManager.getInstance().requestPermissionsIfNecessaryForResult((Activity) Instance().m_context, new String[]{"android.permission.RECORD_AUDIO"}, new PermissionsResultAction() { // from class: com.youme.voice.VoiceManager.2
                        @Override // com.anthonycr.grant.PermissionsResultAction
                        public void onGranted() {
                            Log.e(CommonConst.LOG_TAG, " Success granted RECORD_AUDIO permission.");
                        }

                        @Override // com.anthonycr.grant.PermissionsResultAction
                        public void onDenied(String str) {
                            IMEngine.WriteLog(CommonConst.LogLevel.LOG_LEVEL_WARNING, "Decline granted RECORD_AUDIO permission.");
                        }
                    });
                    IMEngine.WriteLog(CommonConst.LogLevel.LOG_LEVEL_WARNING, "Not granted RECORD_AUDIO permission.");
                    return AudioErrorCode.AUDIOERROR_AUTHORIZE.getValue();
                }
            } catch (Throwable th) {
                Log.e(CommonConst.LOG_TAG, th.toString());
                IMEngine.WriteLog(CommonConst.LogLevel.LOG_LEVEL_ERROR, "record permission exception");
            }
        }
        try {
            if (!Instance().m_isInit) {
                InitYouMeAudioRecord();
            }
            String str = Instance().m_recordCacheDir + UUID.randomUUID().toString() + ".wav";
            Instance().m_speechOnly = true;
            return YouMeAudioRecorder.Instance().StartSpeech(str, j, AudioRecognizeType.RECOGNIZETYPE_NO).getValue();
        } catch (Throwable th2) {
            Log.e(CommonConst.LOG_TAG, th2.toString());
            IMEngine.WriteLog(CommonConst.LogLevel.LOG_LEVEL_ERROR, "speechOnly init error");
            return -1;
        }
    }

    public static int StopSpeech() {
        try {
            if (Instance().m_speechOnly) {
                if (!Instance().m_isInit) {
                    IMEngine.WriteLog(CommonConst.LogLevel.LOG_LEVEL_ERROR, "VoiceManager not init");
                    return AudioErrorCode.AUDIOERROR_NOT_START_RECORD.getValue();
                }
                return YouMeAudioRecorder.Instance().StopSpeech().getValue();
            }
            if (Instance().m_speechRecognizer == null) {
                IMEngine.WriteLog(CommonConst.LogLevel.LOG_LEVEL_ERROR, "m_speechRecognizer is null");
                return AudioErrorCode.AUDIOERROR_NOT_START_RECORD.getValue();
            }
            return Instance().m_speechRecognizer.StopSpeech().getValue();
        } catch (Throwable th) {
            Log.e(CommonConst.LOG_TAG, th.toString());
            return AudioErrorCode.AUDIOERROR_OTHREERROR.getValue();
        }
    }

    public static int CancleSpeech() {
        try {
            if (Instance().m_speechOnly) {
                if (!Instance().m_isInit) {
                    return AudioErrorCode.AUDIOERROR_NOT_START_RECORD.getValue();
                }
                return YouMeAudioRecorder.Instance().CancleSpeech().getValue();
            }
            if (Instance().m_speechRecognizer == null) {
                return AudioErrorCode.AUDIOERROR_NOT_START_RECORD.getValue();
            }
            return Instance().m_speechRecognizer.CancleSpeech().getValue();
        } catch (Throwable th) {
            Log.e(CommonConst.LOG_TAG, th.toString());
            return AudioErrorCode.AUDIOERROR_OTHREERROR.getValue();
        }
    }

    public static void SetRecognizeLanguage(int i) {
        Instance().m_recognizeLanguage = ISpeechRecognize.RecognizeLanguage.values()[i];
        if (Instance().m_speechRecognizer != null) {
            Instance().m_speechRecognizer.SetRecognizeLanguage(Instance().m_recognizeLanguage);
        }
    }

    @Override // com.youme.voice.IAudioRecordListener
    public void OnRecordFinish(int i, String str, long j, String str2) {
        Log.d(CommonConst.LOG_TAG, "VoiceManager OnRecordFinish errorcode:" + i + " serial:" + j + " path:" + str + " text:" + str2);
        NativeEngine.OnRecordFinish(i, str, j, str2);
    }

    @Override // com.youme.voice.IAudioRecordListener
    public byte[] OnRecordData(byte[] bArr, int i) {
        int i2;
        if ((AudioRecognizeType.RECOGNIZETYPE_IFLY_STREAM == this.m_recognizeType || AudioRecognizeType.RECOGNIZETYPE_USC == this.m_recognizeType) && (i2 = this.m_sampleRate) != 16000 && i2 != 8000) {
            return NativeEngine.OnRecordData(bArr, i, true);
        }
        if (AudioRecognizeType.RECOGNIZETYPE_ALI == this.m_recognizeType) {
            return NativeEngine.OnRecordData(bArr, i, this.m_sampleRate != 16000);
        }
        if (AudioRecognizeType.RECOGNIZETYPE_NO == this.m_recognizeType || this.m_speechOnly) {
            return NativeEngine.OnRecordData(bArr, i, false);
        }
        return null;
    }

    @Override // com.youme.voice.IAudioRecordListener
    public void OnRecordVolumeChange(int i, AudioRecognizeType audioRecognizeType) {
        NativeEngine.NotifyVolumeChange(i, audioRecognizeType.ordinal());
    }

    void InitAudioPlayer() {
        try {
            if (Instance().m_audioPlayer != null) {
                return;
            }
            Instance().m_audioPlayer = new AudioPlayer();
            Instance().m_audioPlayer.SetPlayListener(this);
            Instance().m_audioPlayer.SetPlayVolume(this.m_playVolume);
        } catch (Throwable th) {
            Log.e(CommonConst.LOG_TAG, th.toString());
        }
    }

    public static int StartPlayAudio(String str) {
        try {
            if (Instance().m_audioPlayer == null) {
                Instance().InitAudioPlayer();
            }
            return Instance().m_audioPlayer.Play(str).getValue();
        } catch (Throwable th) {
            Log.e(CommonConst.LOG_TAG, th.toString());
            return AudioErrorCode.AUDIOERROR_OTHREERROR.getValue();
        }
    }

    public static int StopPlayAudio() {
        try {
            if (Instance().m_audioPlayer == null) {
                return AudioErrorCode.AUDIOERROR_NOT_START_PLAY.getValue();
            }
            return Instance().m_audioPlayer.Stop().getValue();
        } catch (Throwable th) {
            Log.e(CommonConst.LOG_TAG, th.toString());
            return AudioErrorCode.AUDIOERROR_OTHREERROR.getValue();
        }
    }

    public static boolean IsPlaying() {
        try {
            if (Instance().m_audioPlayer == null) {
                return false;
            }
            return Instance().m_audioPlayer.IsPlaying();
        } catch (Throwable th) {
            Log.e(CommonConst.LOG_TAG, th.toString());
            return false;
        }
    }

    public static void SetPlayVolume(float f) {
        Instance().m_playVolume = f;
        if (Instance().m_audioPlayer != null) {
            Instance().m_audioPlayer.SetPlayVolume(f);
        }
    }

    public static int GetMicrophoneStatus() {
        if (!Instance().m_isInit) {
            InitYouMeAudioRecord();
        }
        return YouMeAudioRecorder.Instance().GetMicrophoneStatus().ordinal();
    }

    @Override // com.youme.voice.IAudioPlayListener
    public void OnPlayFinish(AudioErrorCode audioErrorCode, String str) {
        NativeEngine.OnPlayFinish(audioErrorCode.getValue(), str);
    }

    @Override // com.youme.voice.IAudioPlayListener
    public void OnPlayData(byte[] bArr, int i) {
        NativeEngine.OnPlayData(bArr, i);
    }

    public static int GetNetworkType() {
        return NetworkStatusReceiver2.GetNetworkType(Instance().m_context).ordinal();
    }
}
