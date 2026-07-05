package com.duowan.kiwi.barrage.utils;

import android.os.SystemClock;
import java.util.LinkedList;
import java.util.Locale;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class BarrageFpsHelper {
    private static final int MAX_RECORD_SIZE = 50;
    private static final int ONE_SECOND = 1000;
    private LinkedList<Long> mDrawTimes;
    private long mLastTime;

    public static BarrageFpsHelper create() {
        return new BarrageFpsHelper();
    }

    public void update() {
        this.mLastTime = SystemClock.uptimeMillis();
        if (this.mDrawTimes == null) {
            this.mDrawTimes = new LinkedList<>();
        }
        this.mDrawTimes.addLast(Long.valueOf(this.mLastTime));
    }

    public float fps() {
        Long lPeekFirst = this.mDrawTimes.peekFirst();
        if (lPeekFirst == null) {
            return 0.0f;
        }
        float fLongValue = this.mLastTime - lPeekFirst.longValue();
        if (this.mDrawTimes.size() > 50) {
            this.mDrawTimes.removeFirst();
        }
        if (fLongValue > 0.0f) {
            return (this.mDrawTimes.size() * 1000) / fLongValue;
        }
        return 0.0f;
    }

    public String getFpsStr() {
        return String.format(Locale.getDefault(), "fps %.2f", Float.valueOf(fps()));
    }
}
