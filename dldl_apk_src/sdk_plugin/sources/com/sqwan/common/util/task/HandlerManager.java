package com.sqwan.common.util.task;

import android.os.Handler;
import android.os.Looper;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
class HandlerManager {
    private static final HandlerManager ourInstance = new HandlerManager();
    private Set<Runnable> runnables = new HashSet();
    private Handler handler = new Handler(Looper.getMainLooper());

    public static HandlerManager getInstance() {
        return ourInstance;
    }

    private HandlerManager() {
    }

    public void release() {
        Iterator<Runnable> it = this.runnables.iterator();
        while (it.hasNext()) {
            this.handler.removeCallbacks(it.next());
        }
        this.handler = null;
    }

    public void postDelay(long j, Runnable runnable) {
        this.runnables.add(runnable);
        Handler handler = this.handler;
        if (handler == null) {
            return;
        }
        if (j == 0) {
            handler.post(runnable);
        } else {
            handler.postDelayed(runnable, j);
        }
    }

    public void remove(Runnable runnable) {
        if (runnable != null) {
            this.runnables.remove(runnable);
        }
    }

    public void stop(Runnable runnable) {
        remove(runnable);
        Handler handler = this.handler;
        if (handler != null) {
            handler.removeCallbacks(runnable);
        }
    }
}
