package com.huya.force.collect;

import android.os.SystemClock;
import com.huya.force.log.ForceLog;
import java.nio.ByteBuffer;
import java.util.Locale;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class FpsCounter {
    private static final String TAG = "FpsCounter";
    private final String mTag;
    private long mLastTime = 0;
    private long mBitrate = 0;
    private long mFps = 0;

    public FpsCounter(String str) {
        this.mTag = str + "_" + TAG;
    }

    public void addFrame(byte[] bArr) {
        addFrame(bArr != null ? bArr.length * 8 : 0);
    }

    public void addFrame(ByteBuffer byteBuffer) {
        addFrame(byteBuffer != null ? byteBuffer.remaining() * 8 : 0);
    }

    public void addFrame() {
        addFrame(0);
    }

    public void addFrame(int i) {
        long jUptimeMillis = SystemClock.uptimeMillis();
        long j = this.mLastTime;
        if (jUptimeMillis - j >= 1000) {
            if (j > 0) {
                ForceLog.info(this.mTag, String.format(Locale.US, "mBitrate=%d, mFps=%d", Long.valueOf(this.mBitrate / 1000), Long.valueOf(this.mFps)));
            }
            this.mLastTime = jUptimeMillis;
            this.mBitrate = 0L;
            this.mFps = 0L;
        }
        this.mFps++;
        this.mBitrate += (long) i;
    }
}
