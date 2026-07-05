package com.sq.diagnostic.assistant.ui.action;

import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public interface HandlerAction {
    public static final Handler HANDLER = new Handler(Looper.getMainLooper());

    boolean post(Runnable runnable);

    boolean postAtTime(Runnable runnable, long j);

    boolean postDelayed(Runnable runnable, long j);

    void removeCallbacks();

    void removeCallbacks(Runnable runnable);

    /* JADX INFO: renamed from: com.sq.diagnostic.assistant.ui.action.HandlerAction$-CC, reason: invalid class name */
    public final /* synthetic */ class CC {
        public static Handler getHandler() {
            return HandlerAction.HANDLER;
        }

        public static boolean $default$postDelayed(HandlerAction _this, Runnable runnable, long j) {
            if (j < 0) {
                j = 0;
            }
            return _this.postAtTime(runnable, SystemClock.uptimeMillis() + j);
        }
    }
}
