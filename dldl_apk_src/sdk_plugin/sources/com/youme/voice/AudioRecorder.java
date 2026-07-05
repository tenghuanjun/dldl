package com.youme.voice;

import android.content.Context;
import android.media.AudioManager;
import android.media.AudioRecord;
import android.util.Log;
import com.youme.im.CommonConst;
import com.youme.im.IMEngine;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
public class AudioRecorder {
    private static AudioRecorder s_instance;
    private Context m_context;
    private Thread recordThread;
    private int m_sampleRate = CommonConst.SAMPLERATE_16K;
    private int m_channel = 16;
    private int m_audioFormat = 2;
    private int m_recordBufferSize = 12800;
    private RecordStatus m_recordStatus = RecordStatus.RECORDSTATUS_IDLE;
    private WAVHeadInfo m_wavHeadInfo = null;
    private AudioRecord m_audioRecord = null;
    private AudioManager m_audioManager = null;
    private IAudioRecordListener m_recordListener = null;
    private boolean m_isInit = false;

    public static AudioRecorder Instance() {
        if (s_instance == null) {
            s_instance = new AudioRecorder();
        }
        return s_instance;
    }

    public boolean Init(Context context) {
        if (this.m_isInit) {
            return true;
        }
        this.m_context = context;
        AudioManager audioManager = (AudioManager) context.getSystemService("audio");
        this.m_audioManager = audioManager;
        if (audioManager == null) {
            IMEngine.WriteLog(CommonConst.LogLevel.LOG_LEVEL_ERROR, "audiomanager is null");
            return false;
        }
        this.m_wavHeadInfo = new WAVHeadInfo();
        SetAudioRecordParam(CommonConst.SAMPLERATE_16K, 1, 16);
        this.m_isInit = true;
        return true;
    }

    public void Uninit() {
        AudioRecord audioRecord = this.m_audioRecord;
        if (audioRecord != null) {
            audioRecord.stop();
            this.m_audioRecord.release();
            this.m_audioRecord = null;
        }
        this.m_isInit = false;
    }

    public void SetAudioRecordParam(int i, int i2, int i3) {
        this.m_sampleRate = i;
        if (i2 == 1) {
            this.m_channel = 16;
        } else if (i2 == 2) {
            this.m_channel = 12;
        }
        if (i3 == 8) {
            this.m_audioFormat = 3;
        } else if (i3 == 16) {
            this.m_audioFormat = 2;
        } else {
            this.m_audioFormat = 1;
        }
        this.m_wavHeadInfo.SetAudioProperty(i, i2, i3);
        this.m_recordBufferSize = AudioRecord.getMinBufferSize(this.m_sampleRate, this.m_channel, this.m_audioFormat) * 2;
    }

    public void SetRecordListener(IAudioRecordListener iAudioRecordListener) {
        this.m_recordListener = iAudioRecordListener;
    }

    public boolean IsRecording() {
        return this.m_recordStatus != RecordStatus.RECORDSTATUS_IDLE;
    }

    public AudioErrorCode StartRecord(final long j, final String str) {
        IMEngine.WriteLog(CommonConst.LogLevel.LOG_LEVEL_DEBUG, "AudioRecorder StartRecord enter");
        if (IsRecording()) {
            Log.i(CommonConst.LOG_TAG, "AudioRecorder StartRecord leave recording");
            return AudioErrorCode.AUDIOERROR_RECORDING;
        }
        try {
            if (this.m_audioRecord != null && this.m_audioRecord.getRecordingState() != 1) {
                IMEngine.WriteLog(CommonConst.LogLevel.LOG_LEVEL_DEBUG, "AudioRecorder StartRecord is recording");
                return AudioErrorCode.AUDIOERROR_RECORDING;
            }
            if (this.m_audioRecord != null) {
                this.m_audioRecord.release();
                this.m_audioRecord = null;
            }
            IMEngine.WriteLog(CommonConst.LogLevel.LOG_LEVEL_DEBUG, "create AudioRecorder");
            AudioRecord audioRecord = new AudioRecord(1, this.m_sampleRate, this.m_channel, this.m_audioFormat, this.m_recordBufferSize);
            this.m_audioRecord = audioRecord;
            if (audioRecord == null || audioRecord.getState() != 1 || this.m_audioRecord.getRecordingState() != 1) {
                IMEngine.WriteLog(CommonConst.LogLevel.LOG_LEVEL_DEBUG, "AudioRecorder StartRecord init failed");
                return AudioErrorCode.AUDIOERROR_INIT_FAILED;
            }
            this.m_recordStatus = RecordStatus.RECORDSTATUS_RECORDING;
            IMEngine.WriteLog(CommonConst.LogLevel.LOG_LEVEL_DEBUG, "create Record thread");
            Thread thread = new Thread(new Runnable() { // from class: com.youme.voice.AudioRecorder.1
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        AudioRecorder.this.Record(str, j);
                    } catch (Throwable th) {
                        th.printStackTrace();
                    }
                }
            }, "AudioRecorder Thread");
            this.recordThread = thread;
            thread.start();
            Log.i(CommonConst.LOG_TAG, "AudioRecorder StartRecord leave");
            return AudioErrorCode.AUDIOERROR_SUCCESS;
        } catch (Throwable th) {
            IMEngine.WriteLog(CommonConst.LogLevel.LOG_LEVEL_ERROR, "init audiorecord failed");
            th.printStackTrace();
            return AudioErrorCode.AUDIOERROR_INIT_FAILED;
        }
    }

    public AudioErrorCode StopRecord() {
        IMEngine.WriteLog(CommonConst.LogLevel.LOG_LEVEL_DEBUG, "AudioRecorder StopRecord enter status:" + this.m_recordStatus);
        if (!IsRecording()) {
            IMEngine.WriteLog(CommonConst.LogLevel.LOG_LEVEL_DEBUG, "record status:" + this.m_recordStatus);
            return AudioErrorCode.AUDIOERROR_NOT_START_RECORD;
        }
        this.m_recordStatus = RecordStatus.RECORDSTATUS_STOP;
        Log.i(CommonConst.LOG_TAG, "StopRecord leave");
        return AudioErrorCode.AUDIOERROR_SUCCESS;
    }

    public AudioErrorCode CancelRecord() {
        IMEngine.WriteLog(CommonConst.LogLevel.LOG_LEVEL_DEBUG, "CancelRecord enter");
        if (!IsRecording()) {
            return AudioErrorCode.AUDIOERROR_NOT_START_RECORD;
        }
        this.m_recordStatus = RecordStatus.RECORDSTATUS_CANCEL;
        Log.i(CommonConst.LOG_TAG, "CancelRecord leave");
        return AudioErrorCode.AUDIOERROR_SUCCESS;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:66:0x01c8  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x01e4  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x01f4  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x01fc  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void Record(java.lang.String r26, long r27) {
        /*
            Method dump skipped, instruction units count: 790
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.youme.voice.AudioRecorder.Record(java.lang.String, long):void");
    }

    private int GetVolume(byte[] bArr, int i) {
        int iAbs = 0;
        for (int i2 = 0; i2 < i; i2 += 2) {
            short s = (short) ((bArr[i2] & 255) | ((bArr[i2 + 1] & 255) << 8));
            if (Math.abs((int) s) > iAbs) {
                iAbs = Math.abs((int) s);
            }
        }
        return iAbs;
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x007c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean HasAudioPermission() {
        /*
            r12 = this;
            java.lang.String r0 = "android.permission.RECORD_AUDIO"
            int r1 = android.os.Build.VERSION.SDK_INT
            r2 = 0
            r3 = 23
            if (r1 < r3) goto L4e
            android.content.Context r1 = r12.m_context
            if (r1 == 0) goto L4e
            boolean r3 = r1 instanceof android.app.Activity
            if (r3 == 0) goto L4e
            android.content.pm.ApplicationInfo r1 = r1.getApplicationInfo()
            int r1 = r1.targetSdkVersion
            r3 = 22
            if (r1 <= r3) goto L4e
            com.anthonycr.grant.PermissionsManager r1 = com.anthonycr.grant.PermissionsManager.getInstance()     // Catch: java.lang.Exception -> L43
            android.content.Context r3 = r12.m_context     // Catch: java.lang.Exception -> L43
            boolean r1 = r1.hasPermission(r3, r0)     // Catch: java.lang.Exception -> L43
            if (r1 != 0) goto L4e
            com.anthonycr.grant.PermissionsManager r1 = com.anthonycr.grant.PermissionsManager.getInstance()     // Catch: java.lang.Exception -> L43
            android.content.Context r3 = r12.m_context     // Catch: java.lang.Exception -> L43
            android.app.Activity r3 = (android.app.Activity) r3     // Catch: java.lang.Exception -> L43
            java.lang.String[] r0 = new java.lang.String[]{r0}     // Catch: java.lang.Exception -> L43
            com.youme.voice.AudioRecorder$2 r4 = new com.youme.voice.AudioRecorder$2     // Catch: java.lang.Exception -> L43
            r4.<init>()     // Catch: java.lang.Exception -> L43
            r1.requestPermissionsIfNecessaryForResult(r3, r0, r4)     // Catch: java.lang.Exception -> L43
            com.youme.im.CommonConst$LogLevel r0 = com.youme.im.CommonConst.LogLevel.LOG_LEVEL_WARNING     // Catch: java.lang.Exception -> L43
            java.lang.String r1 = "Not granted ACCESS_FINE_LOCATION permission"
            com.youme.im.IMEngine.WriteLog(r0, r1)     // Catch: java.lang.Exception -> L43
            return r2
        L43:
            r0 = move-exception
            java.lang.String r0 = r0.toString()
            java.lang.String r1 = "YouMe_IM"
            android.util.Log.e(r1, r0)
            return r2
        L4e:
            android.media.AudioRecord r0 = r12.m_audioRecord
            r1 = 3
            r3 = 1
            if (r0 == 0) goto L5b
            int r0 = r0.getRecordingState()
            if (r0 != r1) goto L5b
            return r3
        L5b:
            r0 = 8000(0x1f40, float:1.121E-41)
            int r4 = r12.m_channel
            int r5 = r12.m_audioFormat
            int r0 = android.media.AudioRecord.getMinBufferSize(r0, r4, r5)
            android.media.AudioRecord r4 = new android.media.AudioRecord     // Catch: java.lang.Throwable -> La8
            r7 = 1
            r8 = 8000(0x1f40, float:1.121E-41)
            int r9 = r12.m_channel     // Catch: java.lang.Throwable -> La8
            int r10 = r12.m_audioFormat     // Catch: java.lang.Throwable -> La8
            r6 = r4
            r11 = r0
            r6.<init>(r7, r8, r9, r10, r11)     // Catch: java.lang.Throwable -> La8
            r4.startRecording()     // Catch: java.lang.Throwable -> La8
            int r5 = r4.getRecordingState()
            if (r5 == r1) goto L7e
        L7c:
            r3 = 0
            goto L9c
        L7e:
            r1 = 0
            r5 = 0
        L80:
            r6 = 20
            if (r1 >= r6) goto L99
            byte[] r7 = new byte[r0]
            int r8 = r4.read(r7, r2, r0)
            if (r8 > 0) goto L8e
            r3 = 0
            goto L99
        L8e:
            int r6 = r12.GetVolume(r7, r8)
            if (r6 != 0) goto L96
            int r5 = r5 + 1
        L96:
            int r1 = r1 + 1
            goto L80
        L99:
            if (r5 != r6) goto L9c
            goto L7c
        L9c:
            r4.stop()     // Catch: java.lang.Exception -> La3
            r4.release()     // Catch: java.lang.Exception -> La3
            return r3
        La3:
            r0 = move-exception
            r0.printStackTrace()
            return r2
        La8:
            r0 = move-exception
            r0.printStackTrace()
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.youme.voice.AudioRecorder.HasAudioPermission():boolean");
    }

    public AudioDeviceStatus GetMicrophoneStatus() {
        if (!HasAudioPermission()) {
            return AudioDeviceStatus.AUDIOSTATUS_NO_AUTHORIZE;
        }
        AudioManager audioManager = this.m_audioManager;
        if (audioManager == null) {
            Log.i(CommonConst.LOG_TAG, "AudioManager is null");
            return AudioDeviceStatus.AUDIOSTATUS_NOT_AVIAIBLE;
        }
        if (audioManager.isMicrophoneMute()) {
            return AudioDeviceStatus.AUDIOSTATUS_MUTE;
        }
        if (this.m_audioManager.getStreamVolume(3) == 0) {
            return AudioDeviceStatus.AUDIOSTATUS_MUTE;
        }
        return AudioDeviceStatus.AUDIOSTATUS_AVIAIBLE;
    }
}
