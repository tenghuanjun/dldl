package com.sqwan.bugless.core;

import android.os.Handler;
import android.os.Message;
import com.sqwan.bugless.util.LogUtil;
import master.flame.danmaku.danmaku.model.android.DanmakuFactory;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class ANRWatchDog extends Thread {
    private static final int ACTIVITY_ANR_TIMEOUT = 4000;
    private static final int MESSAGE_WATCHDOG_TIME_TICK = 37;
    private static int lastTimeTick = -1;
    private static int timeTick;
    private final Handler watchDogHandler;

    static /* synthetic */ int access$108() {
        int i = timeTick;
        timeTick = i + 1;
        return i;
    }

    public ANRWatchDog() {
        super("bugless-anr");
        this.watchDogHandler = new WatchDogHandler();
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        while (true) {
            this.watchDogHandler.sendEmptyMessage(37);
            try {
                Thread.sleep(DanmakuFactory.MIN_DANMAKU_DURATION);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            int i = timeTick;
            if (i == lastTimeTick) {
                Bugless.getInstance().reportCrash(new ANRException());
            } else {
                lastTimeTick = i;
            }
        }
    }

    private static class WatchDogHandler extends Handler {
        private WatchDogHandler() {
        }

        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            ANRWatchDog.access$108();
            ANRWatchDog.timeTick %= Integer.MAX_VALUE;
            LogUtil.i("timeTick = " + ANRWatchDog.timeTick);
            LogUtil.i("lastTimeTick = " + ANRWatchDog.lastTimeTick);
        }
    }
}
