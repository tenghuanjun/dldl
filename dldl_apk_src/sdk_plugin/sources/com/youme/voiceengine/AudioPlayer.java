package com.youme.voiceengine;

import android.media.AudioAttributes;
import android.media.AudioFormat;
import android.media.AudioTrack;
import android.os.Build;
import android.util.Log;
import com.taptap.sdk.common.oaid.helper.OAIDHelper;
import java.nio.ByteBuffer;
import java.util.Arrays;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
public class AudioPlayer {
    private static final int DEFAULT_BYTES_PER_SAMPLE = 2;
    private static final int DEFAULT_CHANNEL_NUM = 2;
    private static final int DEFAULT_MODE = 1;
    private static final int DEFAULT_SAMPLE_RATE = 44100;
    private static final int DEFAULT_STREAM_TYPE = 3;
    private static final String TAG = "YoumeAudioPlayer";
    private static AudioTrack mAudioTrack = null;
    private static int mBytesPerSample = 0;
    private static int mChannelNum = 0;
    public static ByteBuffer mInBuffer = null;
    private static int mInitStatus = 100;
    private static boolean mIsLoopExit;
    private static boolean mIsPlayerStarted;
    private static int mMinBufferSize;
    private static int mMode;
    private static int mPlayStatus;
    private static Thread mPlayerThread;
    private static int mSamplerate;
    private static int mStreamType;
    private static int usageAttribute;
    private static final int DEFAULT_USAGE = getDefaultUsageAttribute();
    private static int audioRecordSessionID = -1;
    private static boolean mIsStreamVoice = true;

    public static boolean isPlayerStarted() {
        return mIsPlayerStarted;
    }

    public static int getPlayerStatus() {
        return mPlayStatus;
    }

    public static int getPlayerInitStatus() {
        return mInitStatus;
    }

    public static void initPlayer() {
        initPlayer(44100, 2, 2, false);
    }

    public static void initPlayer(int i, int i2, int i3, boolean z) {
        mStreamType = 3;
        mSamplerate = i;
        mChannelNum = i2;
        mBytesPerSample = i3;
        mMode = 1;
        mIsStreamVoice = z;
        if (z) {
            mStreamType = 0;
            Log.w(TAG, "## player set voice stream:STREAM_VOICE_CALL");
        } else {
            mStreamType = 3;
            Log.w(TAG, "## player set voice stream:STREAM_MUSIC");
        }
        int i4 = (i2 == 1 || i2 != 2) ? 4 : 12;
        int i5 = i3 != 1 ? 2 : 3;
        int minBufferSize = AudioTrack.getMinBufferSize(mSamplerate, i4, i5);
        mMinBufferSize = minBufferSize;
        if (minBufferSize == -2) {
            Log.e(TAG, "Invalid parameter !");
            mInitStatus = -2;
            return;
        }
        Log.d(TAG, "getMinBufferSize = " + mMinBufferSize + " bytes samplerate:" + mSamplerate);
        try {
            if (Build.VERSION.SDK_INT >= 21) {
                mAudioTrack = createAudioTrackOnLollipopOrHigher(mSamplerate, i4, mMinBufferSize, i5);
            } else {
                mAudioTrack = new AudioTrack(mStreamType, mSamplerate, i4, i5, mMinBufferSize, mMode, audioRecordSessionID == -1 ? 0 : audioRecordSessionID);
            }
            AudioTrack audioTrack = mAudioTrack;
            if (audioTrack != null && audioTrack.getState() == 0) {
                Log.e(TAG, "AudioPlayer initialize fail !");
                mInitStatus = 0;
                mAudioTrack.release();
            } else {
                int i6 = (((mSamplerate * mChannelNum) * mBytesPerSample) / 100) * 2;
                if (i6 > mMinBufferSize) {
                    Log.e(TAG, "Error play buffer overflow!");
                }
                mInBuffer = ByteBuffer.allocateDirect(i6);
            }
        } catch (Throwable th) {
            th.printStackTrace();
            Log.e(TAG, "AudioPlayer initialize fail !");
            mInitStatus = 0;
            AudioTrack audioTrack2 = mAudioTrack;
            if (audioTrack2 != null) {
                audioTrack2.release();
            }
        }
    }

    public static void setAudioRecordSessionID(int i) {
        audioRecordSessionID = i;
        if (mIsPlayerStarted) {
            OnAudioPlayer(0);
            initPlayer(mSamplerate, mChannelNum, mBytesPerSample, mIsStreamVoice);
            OnAudioPlayer(1);
        }
    }

    private static AudioTrack createAudioTrackOnLollipopOrHigher(int i, int i2, int i3, int i4) {
        Log.d(TAG, "createAudioTrackOnLollipopOrHigher");
        int nativeOutputSampleRate = AudioTrack.getNativeOutputSampleRate(0);
        Log.d(TAG, "nativeOutputSampleRate: " + nativeOutputSampleRate);
        if (i != nativeOutputSampleRate) {
            Log.w(TAG, "Unable to use fast mode since requested sample rate is not native,native rate:" + nativeOutputSampleRate);
        }
        int defaultUsageAttribute = getDefaultUsageAttribute();
        usageAttribute = defaultUsageAttribute;
        if (defaultUsageAttribute != DEFAULT_USAGE) {
            Log.w(TAG, "A non default usage attribute is used: " + usageAttribute);
        }
        int i5 = 2;
        int i6 = 1;
        if (mStreamType != 0) {
            i5 = 1;
            i6 = 2;
        }
        AudioAttributes audioAttributesBuild = new AudioAttributes.Builder().setUsage(i5).setContentType(i6).build();
        AudioFormat audioFormatBuild = new AudioFormat.Builder().setEncoding(i4).setSampleRate(i).setChannelMask(i2).build();
        int i7 = mMode;
        int i8 = audioRecordSessionID;
        return new AudioTrack(audioAttributesBuild, audioFormatBuild, i3, i7, i8 == -1 ? 0 : i8);
    }

    public static boolean startPlayer() {
        int i;
        AudioTrack audioTrack = mAudioTrack;
        if (audioTrack == null || (i = mInitStatus) == -2 || i == 0) {
            Log.e(TAG, "Player cannot be started because initial fail !");
            mInBuffer = null;
            AudioTrack audioTrack2 = mAudioTrack;
            if (audioTrack2 != null) {
                audioTrack2.release();
            }
            return false;
        }
        if (mIsPlayerStarted) {
            Log.e(TAG, "Player already started !");
            return false;
        }
        audioTrack.play();
        mIsLoopExit = false;
        Thread thread = new Thread(new AudioPlayerRunnable());
        mPlayerThread = thread;
        thread.start();
        mIsPlayerStarted = true;
        Log.d(TAG, "Start audio player success !");
        return true;
    }

    public static void stopPlayer() {
        if (mIsPlayerStarted) {
            mIsLoopExit = true;
            try {
                mPlayerThread.interrupt();
                mPlayerThread.join(OAIDHelper.TIMEOUT);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            AudioTrack audioTrack = mAudioTrack;
            if (audioTrack != null) {
                if (audioTrack.getPlayState() == 3) {
                    mAudioTrack.stop();
                }
                mAudioTrack.release();
            }
            mIsPlayerStarted = false;
            mInBuffer = null;
            Log.d(TAG, "Stop audio player success !");
        }
    }

    private static class AudioPlayerRunnable implements Runnable {
        private AudioPlayerRunnable() {
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                int i = (((AudioPlayer.mSamplerate * AudioPlayer.mChannelNum) * AudioPlayer.mBytesPerSample) / 100) * 2;
                while (!AudioPlayer.mIsLoopExit && !Thread.interrupted()) {
                    if (i > AudioPlayer.mMinBufferSize) {
                        Log.e(AudioPlayer.TAG, "Error play buffer overflow!");
                    }
                    if (AudioPlayer.mInBuffer == null) {
                        AudioPlayer.mInBuffer = ByteBuffer.allocateDirect(i);
                    }
                    int iWrite = 0;
                    Arrays.fill(AudioPlayer.mInBuffer.array(), (byte) 0);
                    AudioPlayer.mInBuffer.clear();
                    AudioPlayer.OnAudioPlayerRefresh(AudioPlayer.mInBuffer, AudioPlayer.mSamplerate, AudioPlayer.mChannelNum, AudioPlayer.mBytesPerSample);
                    if (AudioPlayer.mAudioTrack != null) {
                        try {
                            if (Build.VERSION.SDK_INT >= 21) {
                                iWrite = AudioPlayer.mAudioTrack.write(AudioPlayer.mInBuffer, AudioPlayer.mInBuffer.capacity(), 0);
                            } else {
                                AudioPlayer.mAudioTrack.write(AudioPlayer.mInBuffer.array(), 0, i);
                            }
                        } catch (NoSuchMethodError unused) {
                            AudioPlayer.mAudioTrack.write(AudioPlayer.mInBuffer.array(), 0, i);
                        }
                        if (iWrite == -3) {
                            Log.e(AudioPlayer.TAG, "Error ERROR_INVALID_OPERATION");
                            int unused2 = AudioPlayer.mPlayStatus = -3;
                        } else if (iWrite != -2) {
                            int unused3 = AudioPlayer.mPlayStatus = AudioPlayer.mAudioTrack.getPlayState();
                        } else {
                            Log.e(AudioPlayer.TAG, "Error ERROR_BAD_VALUE");
                            int unused4 = AudioPlayer.mPlayStatus = -2;
                        }
                    }
                }
            } catch (Throwable th) {
                th.printStackTrace();
                Log.e(AudioPlayer.TAG, "AudioPlayer thread exit!");
            }
        }
    }

    private static int getDefaultUsageAttribute() {
        if (Build.VERSION.SDK_INT >= 21) {
            return getDefaultUsageAttributeOnLollipopOrHigher();
        }
        return 0;
    }

    private static int getDefaultUsageAttributeOnLollipopOrHigher() {
        return mStreamType == 3 ? 1 : 2;
    }

    public static void OnAudioPlayer(int i) {
        Log.d("AudioRecorder", "AudioRecorder : " + i);
        if (i != 0) {
            startPlayer();
        } else {
            stopPlayer();
        }
    }

    public static void OnAudioPlayerRefresh(ByteBuffer byteBuffer, int i, int i2, int i3) {
        NativeEngine.AudioPlayerBufRefresh(byteBuffer, i, i2, i3);
    }
}
