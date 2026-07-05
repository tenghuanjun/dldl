package com.duowan.live.one.util;

import android.os.Handler;
import android.os.Message;
import com.duowan.auk.NoProguard;
import com.duowan.auk.util.L;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class EasyTimer implements NoProguard {
    private int mDuration;
    private Handler mHandler = new Handler() { // from class: com.duowan.live.one.util.EasyTimer.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (EasyTimer.this.mRunnable != null) {
                try {
                    EasyTimer.this.mRunnable.run();
                } catch (Exception e) {
                    L.error(e.toString());
                }
                removeMessages(0);
                sendEmptyMessageDelayed(0, EasyTimer.this.mDuration);
            }
        }
    };
    private Runnable mRunnable;

    public void setDuration(int i) {
        this.mDuration = i;
    }

    public void setRunnable(Runnable runnable) {
        this.mRunnable = runnable;
    }

    public void stop() {
        this.mHandler.removeMessages(0);
        this.mRunnable = null;
    }

    public void start() {
        this.mHandler.sendEmptyMessage(0);
    }

    public void resetAndStart(int i, Runnable runnable) {
        stop();
        setRunnable(runnable);
        setDuration(i);
        start();
    }
}
