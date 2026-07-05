package hy.org.webrtc.voiceengine;

import android.media.AudioTrack;
import android.os.Build;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
public class HyAudioLatencyTuner {
    private static final int STATE_LOWERING = 1;
    private static final int STATE_PRIMING = 0;
    private static final int STATE_RAISING = 2;
    private static final String TAG = "HyAudioLatencyTuner";
    private static boolean mLowLatencySupported;
    private final AudioTrack mAudioTrack;
    private final int mFramesPerBlock;
    private final int mInitialSize;
    private int mPreviousUnderrunCount;
    private int mState = 0;

    static {
        mLowLatencySupported = Build.VERSION.SDK_INT >= 24;
    }

    public HyAudioLatencyTuner(AudioTrack audioTrack, int i) {
        this.mAudioTrack = audioTrack;
        this.mInitialSize = audioTrack.getBufferSizeInFrames();
        this.mFramesPerBlock = i;
        reset();
    }

    public int getUnderrunCount() {
        if (mLowLatencySupported) {
            return this.mAudioTrack.getUnderrunCount();
        }
        return 0;
    }

    public int getBufferCapacityInFrames() {
        if (mLowLatencySupported) {
            return this.mAudioTrack.getBufferCapacityInFrames();
        }
        return this.mInitialSize;
    }

    public int setBufferSizeInFrames(int i) {
        if (mLowLatencySupported) {
            return this.mAudioTrack.setBufferSizeInFrames(i);
        }
        return this.mInitialSize;
    }

    public int getBufferSizeInFrames() {
        return this.mAudioTrack.getBufferSizeInFrames();
    }

    public static boolean isLowLatencySupported() {
        return mLowLatencySupported;
    }

    public static int getLowLatencyFlag() {
        return mLowLatencySupported ? 256 : 0;
    }

    public void reset() {
        this.mState = 0;
        setBufferSizeInFrames(this.mInitialSize);
    }

    public void update() {
        if (mLowLatencySupported) {
            int i = this.mState;
            if (i != 0) {
                if (i == 1) {
                    int underrunCount = getUnderrunCount();
                    if (underrunCount > this.mPreviousUnderrunCount || incrementThreshold(-1)) {
                        i = 2;
                    }
                    this.mPreviousUnderrunCount = underrunCount;
                } else if (i == 2) {
                    int underrunCount2 = getUnderrunCount();
                    if (underrunCount2 > this.mPreviousUnderrunCount) {
                        incrementThreshold(1);
                    }
                    this.mPreviousUnderrunCount = underrunCount2;
                }
            } else if (this.mAudioTrack.getPlaybackHeadPosition() > this.mFramesPerBlock * 8) {
                this.mPreviousUnderrunCount = getUnderrunCount();
                i = 1;
            }
            this.mState = i;
        }
    }

    private boolean incrementThreshold(int i) {
        int bufferSizeInFrames = getBufferSizeInFrames();
        int i2 = this.mFramesPerBlock;
        int bufferSizeInFrames2 = setBufferSizeInFrames(((bufferSizeInFrames / i2) + i) * i2);
        DoLog("Buffer size changed from " + bufferSizeInFrames + " to " + bufferSizeInFrames2);
        return bufferSizeInFrames2 == bufferSizeInFrames;
    }

    private void DoLog(String str) {
        AudioManagerAndroid.DoLog(TAG + str);
    }
}
