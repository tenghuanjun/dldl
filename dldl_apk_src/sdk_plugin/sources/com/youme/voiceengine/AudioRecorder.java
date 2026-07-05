package com.youme.voiceengine;

import android.media.AudioFormat;
import android.media.AudioRecord;
import android.os.Build;
import android.util.Log;
import com.taptap.sdk.common.oaid.helper.OAIDHelper;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
public class AudioRecorder {
    private static String AudioName = null;
    private static String AudioRecordError = null;
    private static final boolean DEBUG = false;
    private static final int DEFAULT_BYTES_PER_SAMPLE = 2;
    private static final int DEFAULT_CHANNEL_NUM = 2;
    private static final int DEFAULT_SAMPLE_RATE = 44100;
    private static final int DEFAULT_SOURCE = 1;
    private static final String TAG = "YoumeAudioRecorder";
    private static boolean isReleased = true;
    private static AudioRecord mAudioRecord = null;
    private static int mBytesPerSample = 0;
    private static int mChannelNum = 0;
    private static int mCounter = 1;
    private static int mInitStatus = 100;
    private static boolean mInitSuceed = false;
    private static boolean mIsLoopExit = false;
    private static boolean mIsRecorderStarted = false;
    private static int mLoopCounter = 1;
    private static int mMicSource = 0;
    private static int mMinBufferSize = 0;
    public static ByteBuffer mOutBuffer = null;
    private static int mRecordStatus = 0;
    private static Thread mRecorderThread = null;
    private static int mSamplerate = 0;
    private static int mTypeSpeech = 1;
    private static boolean needChangeMode;
    private static boolean pauseRecord;
    private static int readBufSize;

    static /* synthetic */ int access$908() {
        int i = mLoopCounter;
        mLoopCounter = i + 1;
        return i;
    }

    public static boolean isRecorderStarted() {
        return mIsRecorderStarted;
    }

    public static int getRecorderStatus() {
        return mRecordStatus;
    }

    public static int getRecorderInitStatus() {
        return mInitStatus;
    }

    public static void initRecorder() {
        initRecorder(44100, 2, 2, mTypeSpeech);
    }

    private static AudioRecord createAudioRecordOnMarshmallowOrHigher(int i, int i2, int i3, int i4) {
        Log.d(TAG, "createAudioRecordOnMarshmallowOrHigher audioSource:" + i);
        return new AudioRecord.Builder().setAudioSource(i).setAudioFormat(new AudioFormat.Builder().setEncoding(2).setSampleRate(i2).setChannelMask(i3).build()).setBufferSizeInBytes(i4).build();
    }

    public static void initRecorder(int i, int i2, int i3, int i4) {
        try {
            if (pauseRecord) {
                pauseRecord = false;
                if (needChangeMode) {
                    AudioMgr.setVoiceModeYouMeCoutum();
                }
            }
            mTypeSpeech = i4;
            if (Build.VERSION.SDK_INT >= 11) {
                mMicSource = i4 == 1 ? 7 : 1;
            } else {
                mMicSource = i4 == 1 ? 4 : 1;
            }
            if (mMicSource == 7 || mMicSource == 4) {
                Log.i(TAG, "Record: stream type: voip");
            } else {
                Log.i(TAG, "Record: stream type: mic");
            }
            mSamplerate = i;
            mChannelNum = i2;
            mBytesPerSample = i3;
            mLoopCounter = 1;
            mInitSuceed = true;
            mRecordStatus = 0;
            isReleased = false;
            int i5 = (i2 == 1 || i2 != 2) ? 16 : 12;
            int i6 = i3 != 1 ? 2 : 3;
            int i7 = (((mSamplerate * mChannelNum) * mBytesPerSample) / 100) * 2;
            readBufSize = i7;
            mOutBuffer = ByteBuffer.allocateDirect(i7);
            int minBufferSize = AudioRecord.getMinBufferSize(mSamplerate, i5, i6);
            mMinBufferSize = minBufferSize;
            if (minBufferSize == -2) {
                Log.e(TAG, "Invalid parameter !");
                mInitStatus = -2;
                mInitSuceed = false;
                isReleased = true;
            }
            mMinBufferSize *= 2;
            Log.d(TAG, "getMinBufferSize = " + mMinBufferSize + " bytes");
            if (Build.VERSION.SDK_INT >= 23) {
                mAudioRecord = createAudioRecordOnMarshmallowOrHigher(mMicSource, mSamplerate, i5, mMinBufferSize);
            } else {
                mAudioRecord = new AudioRecord(mMicSource, mSamplerate, i5, i6, mMinBufferSize);
            }
            if (mAudioRecord.getState() == 0) {
                Log.e(TAG, "AudioRecord initialize fail !");
                mInitStatus = 0;
                mAudioRecord.release();
                mInitSuceed = false;
                isReleased = true;
            }
            if (!mInitSuceed || readBufSize <= mMinBufferSize) {
                return;
            }
            Log.e(TAG, "Error record buffer overflow!");
        } catch (Throwable th) {
            Log.e(TAG, "Error create record");
            mInitSuceed = false;
            isReleased = true;
            th.printStackTrace();
        }
    }

    private static void initWithLatestParm() {
        initRecorder(mSamplerate, mChannelNum, mBytesPerSample, mTypeSpeech);
    }

    public static boolean startRecorder() {
        if (mIsRecorderStarted) {
            Log.e(TAG, "Recorder already started !");
            return false;
        }
        if (mInitSuceed) {
            if (isReleased) {
                initWithLatestParm();
            }
            mAudioRecord.startRecording();
        } else {
            Log.e(TAG, "Recorder not init successed.");
            Log.e(TAG, "Output mute data");
        }
        mIsLoopExit = false;
        Thread thread = new Thread(new AudioRecorderRunnable());
        mRecorderThread = thread;
        thread.start();
        mIsRecorderStarted = true;
        Log.d(TAG, "Start audio recorder success !");
        return true;
    }

    public static void stopRecorder() {
        if (mIsRecorderStarted) {
            mIsLoopExit = true;
            try {
                mRecorderThread.interrupt();
                mRecorderThread.join(OAIDHelper.TIMEOUT);
            } catch (Throwable th) {
                th.printStackTrace();
            }
            if (mInitSuceed && mAudioRecord.getRecordingState() == 3) {
                try {
                    mAudioRecord.stop();
                    mAudioRecord.release();
                } catch (Throwable th2) {
                    th2.printStackTrace();
                }
            }
            isReleased = true;
            mIsRecorderStarted = false;
            mOutBuffer = null;
            Log.d(TAG, "Stop audio recorder success !");
        }
    }

    private static class AudioRecorderRunnable implements Runnable {
        private AudioRecorderRunnable() {
        }

        @Override // java.lang.Runnable
        public void run() {
            while (!AudioRecorder.mIsLoopExit && !Thread.interrupted()) {
                try {
                    if (AudioRecorder.mInitSuceed && AudioRecorder.readBufSize > AudioRecorder.mMinBufferSize) {
                        Log.e(AudioRecorder.TAG, "Error record buffer overflow!");
                    }
                    if (AudioRecorder.mInitSuceed) {
                        if (AudioRecorder.mOutBuffer == null) {
                            AudioRecorder.mOutBuffer = ByteBuffer.allocateDirect((((AudioRecorder.mSamplerate * AudioRecorder.mChannelNum) * AudioRecorder.mBytesPerSample) / 100) * 2);
                        }
                        int i = AudioRecorder.mAudioRecord.read(AudioRecorder.mOutBuffer, AudioRecorder.mOutBuffer.capacity());
                        if (i > 0) {
                            if (AudioRecorder.mLoopCounter < 5 && AudioRecorder.mLoopCounter >= 0) {
                                Log.e(AudioRecorder.TAG, "Record success: ret=" + i);
                            }
                            AudioRecorder.OnAudioRecorderRefresh(AudioRecorder.mOutBuffer, AudioRecorder.mSamplerate, AudioRecorder.mChannelNum, AudioRecorder.mBytesPerSample);
                            int unused = AudioRecorder.mRecordStatus = 3;
                        } else {
                            if (i == -3) {
                                int unused2 = AudioRecorder.mInitStatus = i;
                                String unused3 = AudioRecorder.AudioRecordError = "Error ERROR_INVALID_OPERATION";
                            } else if (i == -2) {
                                int unused4 = AudioRecorder.mInitStatus = i;
                                String unused5 = AudioRecorder.AudioRecordError = "Error ERROR_BAD_VALUE";
                            } else if (i == -1) {
                                int unused6 = AudioRecorder.mInitStatus = i;
                                String unused7 = AudioRecorder.AudioRecordError = "Error Other ERRORs";
                            } else if (i == 0) {
                                int unused8 = AudioRecorder.mInitStatus = -5;
                                String unused9 = AudioRecorder.AudioRecordError = "Error Record Size=0, maybe record right NOT be enabled in some special android phone!!";
                            }
                            if (i == 0) {
                                int unused10 = AudioRecorder.mRecordStatus = -4;
                            }
                            int unused11 = AudioRecorder.mRecordStatus = i;
                            Arrays.fill(AudioRecorder.mOutBuffer.array(), (byte) 0);
                            AudioRecorder.OnAudioRecorderRefresh(AudioRecorder.mOutBuffer, AudioRecorder.mSamplerate, AudioRecorder.mChannelNum, AudioRecorder.mBytesPerSample);
                            Thread.sleep(20L);
                            if (AudioRecorder.mLoopCounter < 5 && AudioRecorder.mLoopCounter >= 0) {
                                Log.e(AudioRecorder.TAG, AudioRecorder.AudioRecordError);
                            }
                        }
                        AudioRecorder.access$908();
                    } else {
                        if (AudioRecorder.mOutBuffer == null) {
                            AudioRecorder.mOutBuffer = ByteBuffer.allocateDirect((((AudioRecorder.mSamplerate * AudioRecorder.mChannelNum) * AudioRecorder.mBytesPerSample) / 100) * 2);
                        }
                        if (AudioRecorder.mOutBuffer != null) {
                            Arrays.fill(AudioRecorder.mOutBuffer.array(), (byte) 0);
                            AudioRecorder.OnAudioRecorderRefresh(AudioRecorder.mOutBuffer, AudioRecorder.mSamplerate, AudioRecorder.mChannelNum, AudioRecorder.mBytesPerSample);
                        }
                        Thread.sleep(20L);
                    }
                } catch (Throwable th) {
                    th.printStackTrace();
                    Log.e(AudioRecorder.TAG, "Recorder thread exit!");
                    return;
                }
            }
            Log.d(AudioRecorder.TAG, "Recorder thread exit!");
        }
    }

    public static void OnAudioRecorder(int i) {
        try {
            if (i != 0) {
                startRecorder();
            } else {
                stopRecorder();
                mInitSuceed = false;
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public static void OnAudioRecorderTmp(int i) {
        try {
            Log.d(TAG, "enter OnAudioRecorderTmp : " + i);
            if (!mInitSuceed) {
                Log.e(TAG, "OnAudioRecorderTmp return because already stop avsessionMgr");
                return;
            }
            if (i != 0) {
                if (pauseRecord) {
                    if (AudioMgr.hasChangedCoutum()) {
                        needChangeMode = true;
                        AudioMgr.restoreOldMode();
                    } else {
                        needChangeMode = false;
                    }
                    Log.e(TAG, "delay  150ms");
                    new Timer().schedule(new TimerTask() { // from class: com.youme.voiceengine.AudioRecorder.1
                        @Override // java.util.TimerTask, java.lang.Runnable
                        public void run() {
                            try {
                                if (AudioRecorder.pauseRecord) {
                                    AudioRecorder.startRecorder();
                                }
                            } catch (Throwable th) {
                                th.printStackTrace();
                            }
                        }
                    }, 150L);
                }
            } else if (mIsRecorderStarted) {
                stopRecorder();
                pauseRecord = true;
            }
            Log.d(TAG, "leave OnAudioRecorderTmp : " + i);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public static void OnAudioRecorderRefresh(ByteBuffer byteBuffer, int i, int i2, int i3) {
        try {
            NativeEngine.AudioRecorderBufRefresh(byteBuffer, i, i2, i3);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }
}
