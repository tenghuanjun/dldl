package hy.org.webrtc.voiceengine;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTimestamp;
import android.media.AudioTrack;
import android.os.Build;
import android.os.Process;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.util.concurrent.locks.ReentrantLock;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
class WebRtcAudioTrack {
    private static final long MICROS_PER_SECOND = 1000000;
    private static final int MIN_GET_PENDING_DURATION_INTERVAL_US = 1000000;
    private static int currentStreamType;
    private int ChannelMode;
    private int SampleRate;
    private HyAudioLatencyTuner _LatencyTuner;
    private AudioManager _audioManager;
    private Context _context;
    private long _lastRawPlaybackHeadPosition;
    private ByteBuffer _playBuffer;
    private long _rawPlaybackHeadWrapCount;
    private Method getOutputLatencyMethod;
    private AudioTrack _audioTrack = null;
    private final ReentrantLock _playLock = new ReentrantLock();
    private boolean _doPlayInit = true;
    private boolean _doRecInit = true;
    private boolean _isRecording = false;
    private boolean _isPlaying = false;
    private boolean _enableLowlatency = false;
    private int _bufferedPlaySamples = 0;
    private int _playPosition = 0;
    private int _playBufSamples = 0;
    private int _minPlayBufSize = 0;
    private int _channelCount = 1;
    private long _NumSamplesWritten = 0;
    private long _lastGetPendingDurationUs = 0;
    private int _pendingDurationMs = 0;
    private boolean _haveGetPendingDurationOnce = false;
    private long _getPendingDurationCnt = 0;
    final String logTag = "[AudioTrack]";

    public static boolean runningOnLollipopOrHigher() {
        return Build.VERSION.SDK_INT >= 21;
    }

    WebRtcAudioTrack() {
        try {
            this._playBuffer = ByteBuffer.allocateDirect(1920);
        } catch (Exception e) {
            DoLog(e.getMessage());
        }
        if (Build.VERSION.SDK_INT >= 19) {
            try {
                this.getOutputLatencyMethod = AudioManager.class.getMethod("getOutputLatency", (Class[]) null);
            } catch (NoSuchMethodException e2) {
                DoLog("[error] getOutputLatency method is not exists" + e2.getMessage());
            }
        }
    }

    private boolean enableLowlatency(boolean z) {
        this._enableLowlatency = z;
        return z;
    }

    private int CreateAudioTrack(int i, int i2, int i3, int i4) {
        Context context;
        if (Build.VERSION.SDK_INT < 21) {
            this._audioTrack = new AudioTrack(i, i2, i3, 2, i4, 1);
            return 0;
        }
        AudioFormat audioFormatBuild = new AudioFormat.Builder().setSampleRate(i2).setEncoding(2).setChannelMask(i3).build();
        boolean z = true;
        AudioAttributes.Builder contentType = new AudioAttributes.Builder().setUsage(1).setContentType(2);
        if (Build.VERSION.SDK_INT < 24) {
            if (this._audioManager == null && (context = this._context) != null) {
                this._audioManager = (AudioManager) context.getSystemService("audio");
            }
            if (this._audioManager == null) {
                this._audioTrack = new AudioTrack(i, i2, i3, 2, i4, 1);
                return 0;
            }
            this._audioTrack = new AudioTrack(contentType.build(), audioFormatBuild, i4, 1, this._audioManager.generateAudioSessionId());
            return 0;
        }
        if (HyAudioLatencyTuner.isLowLatencySupported() && this._enableLowlatency) {
            DoLog("createAudioTrack() using LOW_LATENCY");
            contentType.setFlags(HyAudioLatencyTuner.getLowLatencyFlag());
        } else {
            z = false;
        }
        AudioAttributes audioAttributesBuild = contentType.build();
        AudioTrack.Builder audioFormat = new AudioTrack.Builder().setAudioAttributes(audioAttributesBuild).setAudioFormat(audioFormatBuild);
        if (z) {
            audioFormat.setBufferSizeInBytes(this._minPlayBufSize);
        }
        AudioTrack audioTrackBuild = audioFormat.build();
        this._audioTrack = audioTrackBuild;
        if (audioTrackBuild == null) {
            throw new RuntimeException("Could not make the Audio Track! attributes = " + audioAttributesBuild + ", format = " + audioFormatBuild);
        }
        this._LatencyTuner = new HyAudioLatencyTuner(audioTrackBuild, i2 / 100);
        return 0;
    }

    private int InitPlayback(int i, int i2, int i3) {
        Context context;
        int i4;
        DoLog("InitPlayback,sampleRate: " + i + " streamType: " + i2 + " performanceMode: " + i3);
        enableLowlatency(i3 == 1);
        int i5 = 4;
        this._channelCount = 1;
        if (i2 == 256) {
            i2 = 3;
            i5 = 12;
            this._channelCount = 2;
        }
        this.SampleRate = i;
        this.ChannelMode = i5;
        int minBufferSize = AudioTrack.getMinBufferSize(i, i5, 2);
        this._minPlayBufSize = minBufferSize;
        int i6 = (i / 100) * 2 * this._channelCount;
        if (minBufferSize % i6 != 0 && (minBufferSize = ((minBufferSize / i6) + 1) * i6) < (i4 = i6 * 10)) {
            minBufferSize = i4;
        }
        this._playBufSamples = (minBufferSize / this._channelCount) / 2;
        this._bufferedPlaySamples = 0;
        DoLog("play buf size is " + minBufferSize + ", " + this._minPlayBufSize + ", " + i6);
        DoLog("AudioTrack setting is " + i2 + ", " + i + ", " + i5);
        AudioTrack audioTrack = this._audioTrack;
        if (audioTrack != null) {
            audioTrack.release();
            this._audioTrack = null;
        }
        try {
            CreateAudioTrack(i2, i, i5, minBufferSize);
            if (this._audioTrack.getState() != 1) {
                return -1;
            }
            if (this._audioManager == null && (context = this._context) != null) {
                this._audioManager = (AudioManager) context.getSystemService("audio");
            }
            if (this._audioManager == null) {
                return 0;
            }
            currentStreamType = i2;
            DoLog("[Info][AudioTrack] AudioTrack streamType: " + i2);
            return this._audioManager.getStreamMaxVolume(currentStreamType);
        } catch (Exception e) {
            DoLog(e.getMessage());
            return -1;
        }
    }

    private int StartPlayback() {
        try {
            this._audioTrack.play();
            this._isPlaying = true;
            return 0;
        } catch (IllegalStateException e) {
            e.printStackTrace();
            return -1;
        }
    }

    private int StopPlayback() {
        this._playLock.lock();
        try {
            if (this._audioTrack.getPlayState() == 3) {
                try {
                    this._audioTrack.stop();
                    this._audioTrack.flush();
                } catch (IllegalStateException e) {
                    e.printStackTrace();
                    this._doPlayInit = true;
                    this._playLock.unlock();
                    return -1;
                }
            }
            resetSyncParams();
            this._audioTrack.release();
            this._audioTrack = null;
            this._doPlayInit = true;
            this._playLock.unlock();
            this._isPlaying = false;
            return 0;
        } catch (Throwable th) {
            this._doPlayInit = true;
            this._playLock.unlock();
            throw th;
        }
    }

    private int PlayAudio(int i) {
        int iWritePreLollipop;
        int i2;
        this._playLock.lock();
        try {
            if (this._audioTrack == null) {
                i2 = -2;
            } else {
                if (this._doPlayInit) {
                    try {
                        Process.setThreadPriority(-19);
                    } catch (Exception e) {
                        DoLog("Set play thread priority failed: " + e.getMessage());
                    }
                    this._doPlayInit = false;
                }
                if (runningOnLollipopOrHigher()) {
                    iWritePreLollipop = writeOnLollipop(this._audioTrack, this._playBuffer, i);
                } else {
                    iWritePreLollipop = writePreLollipop(this._audioTrack, this._playBuffer, i);
                }
                this._playBuffer.rewind();
                this._NumSamplesWritten += (long) ((iWritePreLollipop >> 1) / this._channelCount);
                CalculateDataDurationRemainInBuffer(iWritePreLollipop);
                long jNanoTime = System.nanoTime() / 1000;
                if (jNanoTime - this._lastGetPendingDurationUs >= MICROS_PER_SECOND || !this._haveGetPendingDurationOnce) {
                    this._pendingDurationMs = (int) (GetPendingAudioPlayoutDurationUs(jNanoTime) / 1000);
                    this._lastGetPendingDurationUs = jNanoTime;
                    this._haveGetPendingDurationOnce = true;
                    this._getPendingDurationCnt++;
                }
                if (iWritePreLollipop == i) {
                    this._playLock.unlock();
                    return this._pendingDurationMs;
                }
                i2 = -1;
            }
            return i2;
        } finally {
            this._playLock.unlock();
        }
    }

    private int SetPlayoutSpeaker(boolean z) {
        Context context;
        if (this._audioManager == null && (context = this._context) != null) {
            this._audioManager = (AudioManager) context.getSystemService("audio");
        }
        if (this._audioManager == null) {
            DoLogErr("Could not change audio routing - no audio manager");
            return -1;
        }
        int i = Build.VERSION.SDK_INT;
        try {
            if (3 == i || 4 == i) {
                if (z) {
                    this._audioManager.setMode(0);
                } else {
                    this._audioManager.setMode(2);
                }
            } else if ((!Build.BRAND.equals("Samsung") && !Build.BRAND.equals("samsung")) || (5 != i && 6 != i && 7 != i)) {
                this._audioManager.setSpeakerphoneOn(z);
            } else if (z) {
                this._audioManager.setMode(2);
                this._audioManager.setSpeakerphoneOn(z);
            } else {
                this._audioManager.setSpeakerphoneOn(z);
                this._audioManager.setMode(0);
            }
        } catch (Exception unused) {
        }
        DoLog("[Info][SetPlayoutSpeaker] AudioManager Mode: " + this._audioManager.getMode() + ", loudspeakerOn: " + z + ", isLoudSpeaker: " + this._audioManager.isSpeakerphoneOn());
        return 0;
    }

    private int SetPlayoutVolume(int i) {
        Context context;
        if (this._audioManager == null && (context = this._context) != null) {
            this._audioManager = (AudioManager) context.getSystemService("audio");
        }
        AudioManager audioManager = this._audioManager;
        if (audioManager == null) {
            return -1;
        }
        audioManager.setStreamVolume(currentStreamType, i, 8);
        DoLog("[Info][AudioTrack] setStreamVolume with FLAG_REMOVE_SOUND_AND_VIBRATE, stream type: " + currentStreamType);
        return 0;
    }

    private int GetPlayoutVolume() {
        Context context;
        if (this._audioManager == null && (context = this._context) != null) {
            this._audioManager = (AudioManager) context.getSystemService("audio");
        }
        try {
            if (this._audioManager != null) {
                return this._audioManager.getStreamVolume(currentStreamType);
            }
            return -1;
        } catch (Exception e) {
            DoLog("[error][AudioTrack] getStreamVolume fail: " + e.getMessage());
            return -1;
        }
    }

    private void resetSyncParams() {
        this._lastRawPlaybackHeadPosition = 0L;
        this._rawPlaybackHeadWrapCount = 0L;
        this._NumSamplesWritten = 0L;
        this._pendingDurationMs = 0;
        this._lastGetPendingDurationUs = 0L;
        this._getPendingDurationCnt = 0L;
        this._haveGetPendingDurationOnce = false;
        DoLog("resetSyncParams");
    }

    private long FramesToDurationUs(long j, int i) {
        return (j * MICROS_PER_SECOND) / ((long) i);
    }

    private long GetPendingAudioPlayoutDurationUs(long j) {
        long jCalculateDurationUsAtSampleRate = CalculateDurationUsAtSampleRate(this._NumSamplesWritten, this.SampleRate);
        long jGetPlayedOutAudioDurationUs = GetPlayedOutAudioDurationUs(j);
        if (!this._haveGetPendingDurationOnce || this._getPendingDurationCnt % 20 == 0) {
            if (Build.VERSION.SDK_INT >= 24) {
                DoLog("writtenAudioDurationUs: " + jCalculateDurationUsAtSampleRate + " playedDurationUs: " + jGetPlayedOutAudioDurationUs + " pendingDurationUs: " + (jCalculateDurationUsAtSampleRate - jGetPlayedOutAudioDurationUs) + " minPlayBufferSize: " + this._minPlayBufSize + " underRunCount: " + this._audioTrack.getUnderrunCount());
            } else {
                DoLog("writtenAudioDurationUs: " + jCalculateDurationUsAtSampleRate + " playedDurationUs: " + jGetPlayedOutAudioDurationUs + " pendingDurationUs: " + (jCalculateDurationUsAtSampleRate - jGetPlayedOutAudioDurationUs) + " minPlayBufferSize: " + this._minPlayBufSize);
            }
        }
        return jCalculateDurationUsAtSampleRate - jGetPlayedOutAudioDurationUs;
    }

    private long CalculateDurationUsAtSampleRate(long j, int i) {
        return (j * MICROS_PER_SECOND) / ((long) i);
    }

    private long GetPlayedOutAudioDurationUs(long j) {
        long jIntValue;
        AudioManager audioManager;
        if (Build.VERSION.SDK_INT >= 19) {
            AudioTimestamp audioTimestamp = new AudioTimestamp();
            if (this._audioTrack.getTimestamp(audioTimestamp)) {
                return (CalculateDurationUsAtSampleRate(audioTimestamp.framePosition, this.SampleRate) + j) - (audioTimestamp.nanoTime / 1000);
            }
        }
        Method method = this.getOutputLatencyMethod;
        if (method == null || (audioManager = this._audioManager) == null) {
            jIntValue = 0;
        } else {
            try {
                jIntValue = ((long) ((Integer) method.invoke(audioManager, (Object[]) null)).intValue()) * 1000;
            } catch (Exception e) {
                this.getOutputLatencyMethod = null;
                DoLog("[error][AudioTrack] getOutputLatency Method existed but does not work: " + e.getMessage());
                jIntValue = 0;
            }
        }
        return FramesToDurationUs(GetPlaybackHeadPosition(), this.SampleRate) - jIntValue;
    }

    private long GetPlaybackHeadPosition() {
        AudioTrack audioTrack = this._audioTrack;
        if (audioTrack == null) {
            return 0L;
        }
        int playState = audioTrack.getPlayState();
        AudioTrack audioTrack2 = this._audioTrack;
        if (playState == 1) {
            return 0L;
        }
        long playbackHeadPosition = ((long) audioTrack2.getPlaybackHeadPosition()) & 4294967295L;
        if (playbackHeadPosition == 0) {
            long j = this._lastRawPlaybackHeadPosition;
            if (j > 0 && playState == 3) {
                return j;
            }
        }
        if (this._lastRawPlaybackHeadPosition > playbackHeadPosition) {
            this._rawPlaybackHeadWrapCount++;
        }
        this._lastRawPlaybackHeadPosition = playbackHeadPosition;
        return playbackHeadPosition + (this._rawPlaybackHeadWrapCount << 32);
    }

    private void CalculateDataDurationRemainInBuffer(int i) {
        this._bufferedPlaySamples += (i >> 1) / this._channelCount;
        int playbackHeadPosition = this._audioTrack.getPlaybackHeadPosition();
        if (playbackHeadPosition < this._playPosition) {
            this._playPosition = 0;
        }
        this._bufferedPlaySamples -= playbackHeadPosition - this._playPosition;
        this._playPosition = playbackHeadPosition;
    }

    private int GetBufferingSampleSize() {
        return this._bufferedPlaySamples;
    }

    private void DoLog(String str) {
        AudioManagerAndroid.DoLog("[AudioTrack]" + str);
    }

    private void DoLogErr(String str) {
        AudioManagerAndroid.DoLog("[AudioTrack][Error]" + str);
    }

    private int writeOnLollipop(AudioTrack audioTrack, ByteBuffer byteBuffer, int i) {
        HyAudioLatencyTuner hyAudioLatencyTuner;
        int iWrite = audioTrack.write(byteBuffer, i, 0);
        if (iWrite > 0 && this._enableLowlatency && (hyAudioLatencyTuner = this._LatencyTuner) != null) {
            hyAudioLatencyTuner.update();
        }
        return iWrite;
    }

    private int writePreLollipop(AudioTrack audioTrack, ByteBuffer byteBuffer, int i) {
        return audioTrack.write(byteBuffer.array(), byteBuffer.arrayOffset(), i);
    }
}
