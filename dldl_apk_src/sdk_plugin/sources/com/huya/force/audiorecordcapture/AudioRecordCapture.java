package com.huya.force.audiorecordcapture;

import android.media.AudioRecord;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import com.huya.force.export.audiocapture.AudioCaptureInput;
import com.huya.force.export.audiocapture.BaseAudioCapture;
import com.huya.force.log.ForceLog;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class AudioRecordCapture extends BaseAudioCapture {
    private static final String TAG = "AudioRecordCapture";
    private static final HandlerThread mAudioWorkThread;
    AudioCaptureInput mAudioCaptureInput;
    private AudioH mAudioHandler;
    private AudioRecord mAudioRecord;
    private int mBufferSize;
    private ByteBuffer mCaptureBuf;
    private byte[] mFrameContainer;

    @Override // com.huya.force.export.audiocapture.BaseAudioCapture
    public boolean enableEchoCancellation(boolean z) {
        return false;
    }

    @Override // com.huya.force.export.audiocapture.BaseAudioCapture
    public boolean enableNoiseSuppression(boolean z) {
        return false;
    }

    @Override // com.huya.force.export.audiocapture.BaseAudioCapture
    public byte[] mix(byte[][] bArr) {
        return new byte[0];
    }

    static {
        HandlerThread handlerThread = new HandlerThread("audio capture thread");
        mAudioWorkThread = handlerThread;
        handlerThread.start();
    }

    private class AudioH extends Handler {
        static final int MSG_ENCODE_FRAME = 1;
        static final int MSG_RESTART_ENCODE = 5;
        static final int MSG_START_ENCODE = 2;
        static final int MSG_STOP_ENCODE = 3;
        private int mFrameCount;
        private int mFrameInterval;
        private boolean mIsStart;
        private long mStartTime;

        AudioH(Looper looper) {
            super(looper);
            this.mFrameCount = 0;
            this.mFrameInterval = 10;
            this.mIsStart = false;
        }

        void startEncode() {
            sendMessage(obtainMessage(2, 0));
        }

        void stopEncode() {
            sendEmptyMessage(3);
        }

        public void restart() {
            sendMessage(obtainMessage(2, 0));
        }

        public void reset() {
            removeMessages(1);
            removeMessages(2);
            removeMessages(3);
            removeMessages(5);
            sendEmptyMessage(5);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            int i = message.what;
            if (i != 1) {
                if (i == 2) {
                    if (this.mIsStart) {
                        return;
                    }
                    this.mIsStart = true;
                    this.mFrameCount = 0;
                    this.mStartTime = SystemClock.uptimeMillis();
                    sendEmptyMessage(1);
                    return;
                }
                if (i != 3) {
                    if (i != 5) {
                        return;
                    }
                    this.mIsStart = false;
                    return;
                } else {
                    if (this.mIsStart) {
                        this.mIsStart = false;
                        return;
                    }
                    return;
                }
            }
            if (this.mIsStart) {
                try {
                    long jUptimeMillis = SystemClock.uptimeMillis();
                    int iFetchPcmData = AudioRecordCapture.this.fetchPcmData(AudioRecordCapture.this.mFrameContainer);
                    if (iFetchPcmData > 0) {
                        if (AudioRecordCapture.this.mListener != null) {
                            AudioRecordCapture.this.mListener.onCaptureData(AudioRecordCapture.this.mFrameContainer, iFetchPcmData, SystemClock.uptimeMillis());
                        }
                        int i2 = this.mFrameCount + 1;
                        this.mFrameCount = i2;
                        long j = this.mStartTime + ((long) (i2 * this.mFrameInterval));
                        sendEmptyMessageDelayed(1, j < jUptimeMillis ? 0L : j - jUptimeMillis);
                        return;
                    }
                    sendEmptyMessageDelayed(1, 10L);
                } catch (Exception e) {
                    ForceLog.info(AudioRecordCapture.TAG, "Failed to encode audio, exception msg: " + e.getMessage());
                }
            }
        }
    }

    public AudioRecordCapture(AudioCaptureInput audioCaptureInput) {
        super(audioCaptureInput);
        this.mAudioRecord = null;
        this.mFrameContainer = new byte[81920];
        this.mAudioHandler = null;
        this.mAudioCaptureInput = audioCaptureInput;
    }

    @Override // com.huya.force.export.audiocapture.BaseAudioCapture
    public void init() {
        ForceLog.info(TAG, "init");
    }

    @Override // com.huya.force.export.audiocapture.BaseAudioCapture
    public void start() {
        ForceLog.info(TAG, "start");
        int sampleRate = this.mAudioCaptureInput.getSampleRate();
        int i = this.mAudioCaptureInput.getChannels() == 1 ? 16 : 12;
        int i2 = this.mAudioCaptureInput.getBitsPerSample() == 8 ? 3 : 2;
        int minBufferSize = AudioRecord.getMinBufferSize(sampleRate, i, i2);
        try {
            if (this.mAudioRecord == null) {
                this.mAudioRecord = new AudioRecord(1, sampleRate, i, i2, minBufferSize);
            }
            this.mAudioRecord.startRecording();
            if (this.mAudioRecord.getRecordingState() != 3) {
                ForceLog.info(TAG, "no audio record permission.");
            }
        } catch (IllegalArgumentException e) {
            ForceLog.info(TAG, "startHardEncode IllegalArgumentException." + e);
        } catch (IllegalStateException e2) {
            ForceLog.info(TAG, "startHardEncode IllegalStateException." + e2);
        }
        int channels = (((this.mAudioCaptureInput.getChannels() * sampleRate) * 2) * 10) / 1000;
        this.mBufferSize = channels;
        this.mCaptureBuf = ByteBuffer.allocateDirect(channels);
        AudioH audioH = new AudioH(mAudioWorkThread.getLooper());
        this.mAudioHandler = audioH;
        audioH.startEncode();
    }

    @Override // com.huya.force.export.audiocapture.BaseAudioCapture
    public void stop() {
        ForceLog.info(TAG, "stop");
        try {
            if (this.mAudioRecord != null) {
                this.mAudioRecord.stop();
                this.mAudioRecord = null;
            }
            this.mAudioHandler.stopEncode();
            ForceLog.info(TAG, "Stop audio hard encode.");
        } catch (IllegalStateException e) {
            ForceLog.info(TAG, "stopHardEncode IllegalStateException." + e);
        }
    }

    @Override // com.huya.force.export.audiocapture.BaseAudioCapture
    public void uninit() {
        ForceLog.info(TAG, "uninit");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int fetchPcmData(byte[] bArr) {
        this.mCaptureBuf.clear();
        int i = this.mAudioRecord.read(this.mCaptureBuf, this.mBufferSize);
        if (i <= 0) {
            return 0;
        }
        this.mCaptureBuf.position(i);
        this.mCaptureBuf.flip();
        byte[] bArr2 = new byte[i];
        if (!this.mMute) {
            this.mCaptureBuf.get(bArr2);
        }
        System.arraycopy(bArr2, 0, bArr, 0, i);
        return i;
    }
}
