package com.bytedance.framwork.core.sdklib.thread;

import android.os.Message;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArraySet;

/* JADX INFO: loaded from: classes2.dex */
public class AsyncEventManager {
    public static final String ASYNC_EVENT_MANAGER_THREAD = "AsyncEventManager-Thread";
    private static long WAIT_INTERVAL = 30000;
    private ThreadWithHandler mEventHandler;
    private final Runnable mTimerRunnable;
    private volatile boolean mTimerTaskSwitchOn;
    CopyOnWriteArraySet<IMonitorTimeTask> monitorTimeTaskList;

    private static final class Holder {
        static final AsyncEventManager INSTANCE = new AsyncEventManager();

        private Holder() {
        }
    }

    public interface IMonitorTimeTask {
        void onTimeEvent(long j);
    }

    private AsyncEventManager() {
        this.mTimerTaskSwitchOn = true;
        this.mTimerRunnable = new Runnable() { // from class: com.bytedance.framwork.core.sdklib.thread.AsyncEventManager.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    Iterator<IMonitorTimeTask> it = AsyncEventManager.this.monitorTimeTaskList.iterator();
                    while (it.hasNext()) {
                        it.next().onTimeEvent(System.currentTimeMillis());
                    }
                    if (AsyncEventManager.this.mTimerTaskSwitchOn) {
                        AsyncEventManager.this.mEventHandler.postDelayed(this, AsyncEventManager.WAIT_INTERVAL);
                    }
                } catch (OutOfMemoryError unused) {
                }
            }
        };
        this.monitorTimeTaskList = new CopyOnWriteArraySet<>();
        ThreadWithHandler threadWithHandler = new ThreadWithHandler(ASYNC_EVENT_MANAGER_THREAD);
        this.mEventHandler = threadWithHandler;
        threadWithHandler.start();
    }

    public static AsyncEventManager getInstance() {
        return Holder.INSTANCE;
    }

    public void addTimeTask(IMonitorTimeTask iMonitorTimeTask) {
        if (iMonitorTimeTask != null) {
            try {
                this.monitorTimeTaskList.add(iMonitorTimeTask);
                if (this.mTimerTaskSwitchOn) {
                    this.mEventHandler.removeCallbacks(this.mTimerRunnable);
                    this.mEventHandler.postDelayed(this.mTimerRunnable, WAIT_INTERVAL);
                }
            } catch (Throwable unused) {
            }
        }
    }

    public void post(Runnable runnable) {
        if (runnable == null) {
            return;
        }
        this.mEventHandler.post(runnable);
    }

    public void postDelay(Runnable runnable, long j) {
        if (runnable == null) {
            return;
        }
        this.mEventHandler.postDelayed(runnable, j);
    }

    public void removeCallbacks(Runnable runnable) {
        if (runnable == null) {
            return;
        }
        this.mEventHandler.removeCallbacks(runnable);
    }

    public void removeTimeTask(IMonitorTimeTask iMonitorTimeTask) {
        if (iMonitorTimeTask != null) {
            try {
                this.monitorTimeTaskList.remove(iMonitorTimeTask);
            } catch (Throwable unused) {
            }
        }
    }

    public void restore() {
        this.mTimerTaskSwitchOn = true;
        if (this.mEventHandler == null || this.monitorTimeTaskList.isEmpty()) {
            return;
        }
        this.mEventHandler.removeCallbacks(this.mTimerRunnable);
        this.mEventHandler.postDelayed(this.mTimerRunnable, WAIT_INTERVAL);
    }

    public void sendMessage(Message message) {
        this.mEventHandler.sendMessage(message);
    }

    public void stopLoop() {
        this.mTimerTaskSwitchOn = false;
        ThreadWithHandler threadWithHandler = this.mEventHandler;
        if (threadWithHandler != null) {
            threadWithHandler.removeCallbacks(this.mTimerRunnable);
        }
    }
}
