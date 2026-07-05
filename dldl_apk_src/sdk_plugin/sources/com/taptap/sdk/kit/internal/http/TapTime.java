package com.taptap.sdk.kit.internal.http;

import android.os.SystemClock;
import com.taptap.sdk.kit.internal.TapLogger;
import kotlin.Metadata;

/* JADX INFO: compiled from: TapTime.kt */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0015\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f¢\u0006\u0002\u0010\u0010R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u0011\u0010\u0005\u001a\u00020\u00068F¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lcom/taptap/sdk/kit/internal/http/TapTime;", "", "()V", "TAG", "", "currentTimeInMillis", "", "getCurrentTimeInMillis", "()J", "isServerTime", "", "timeDiff", "calibrateServerTime", "", "serverNow", "", "(Ljava/lang/Integer;)V", "tap-common_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final class TapTime {
    public static final TapTime INSTANCE = new TapTime();
    private static final String TAG = "TapTime";
    private static boolean isServerTime;
    private static long timeDiff;

    private TapTime() {
    }

    public final long getCurrentTimeInMillis() {
        return isServerTime ? timeDiff + SystemClock.elapsedRealtime() : System.currentTimeMillis();
    }

    public final synchronized void calibrateServerTime(Integer serverNow) {
        if (serverNow != null) {
            timeDiff = (((long) serverNow.intValue()) * 1000) - SystemClock.elapsedRealtime();
            TapLogger.logi(TAG, "calibrateServerTime: system elapsed=" + SystemClock.elapsedRealtime() + ", time difference=" + timeDiff);
            isServerTime = true;
        }
    }
}
