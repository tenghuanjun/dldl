package com.ss.android.socialbase.downloader.thread;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes.dex */
public final class DownloadWatchDog implements Handler.Callback {
    private static final int MSG_SCHEDULE_WATCH = 0;
    private volatile Handler handler = new Handler(Holder.LOOPER, this);

    public interface IWatcher {
        long onScheduleWatch();
    }

    private static class Holder {
        private static final Looper LOOPER;

        private Holder() {
        }

        static {
            HandlerThread handlerThread = new HandlerThread("DownloadWatchDog");
            handlerThread.start();
            LOOPER = handlerThread.getLooper();
        }
    }

    public static Looper getThreadLooper() {
        return Holder.LOOPER;
    }

    public void addWatcher(IWatcher iWatcher, long j) {
        Handler handler = this.handler;
        if (handler == null) {
            return;
        }
        Message messageObtain = Message.obtain();
        messageObtain.what = 0;
        messageObtain.obj = iWatcher;
        handler.sendMessageDelayed(messageObtain, j);
    }

    public void removeWatcher(IWatcher iWatcher) {
        Handler handler = this.handler;
        if (handler == null) {
            return;
        }
        handler.removeMessages(0, iWatcher);
    }

    public void release() {
        Handler handler = this.handler;
        if (handler == null) {
            return;
        }
        this.handler = null;
        handler.removeCallbacksAndMessages(null);
    }

    @Override // android.os.Handler.Callback
    public boolean handleMessage(Message message) {
        if (message.what != 0) {
            return true;
        }
        try {
            IWatcher iWatcher = (IWatcher) message.obj;
            long jOnScheduleWatch = iWatcher.onScheduleWatch();
            if (jOnScheduleWatch <= 0) {
                return true;
            }
            addWatcher(iWatcher, jOnScheduleWatch);
            return true;
        } catch (Throwable th) {
            th.printStackTrace();
            return true;
        }
    }
}
