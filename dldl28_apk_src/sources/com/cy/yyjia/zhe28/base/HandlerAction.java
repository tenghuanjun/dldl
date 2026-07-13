package com.cy.yyjia.zhe28.base;

import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;

/* JADX INFO: loaded from: classes2.dex */
public interface HandlerAction {
    public static final Handler HANDLER = new Handler(Looper.getMainLooper());

    Handler getHandler();

    boolean post(Runnable r);

    boolean postAtTime(Runnable r, long uptimeMillis);

    boolean postDelayed(Runnable r, long delayMillis);

    void removeCallbacks();

    /* JADX INFO: renamed from: com.cy.yyjia.zhe28.base.HandlerAction$-CC, reason: invalid class name */
    public final /* synthetic */ class CC {
        public static boolean $default$postDelayed(HandlerAction _this, Runnable r, long delayMillis) {
            if (delayMillis < 0) {
                delayMillis = 0;
            }
            return _this.postAtTime(r, SystemClock.uptimeMillis() + delayMillis);
        }
    }
}
