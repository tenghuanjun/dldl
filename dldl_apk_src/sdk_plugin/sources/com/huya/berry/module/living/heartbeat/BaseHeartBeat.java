package com.huya.berry.module.living.heartbeat;

import com.android.volley.VolleyError;
import com.duowan.auk.util.L;
import com.huya.berry.module.help.TimeHeartHelper;
import java.util.TimerTask;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public abstract class BaseHeartBeat<Req> {
    private static final long DEFAULT_HEART_BEAT_TIME = 60000;
    protected static final String TAG = "HeartBeat";
    private static final String THREAD_NAME_PREFIX = "HaBaseTimerThread-";
    private long mHeartBeatDuration;
    protected HeartBeatListener mHeartBeatListener;
    protected Req mHeartBeatReq;
    private TimeHeartHelper mHeartService = new TimeHeartHelper(THREAD_NAME_PREFIX);
    private final Object mHeartBeatLock = new Object();
    private TimerTask mHeartBeatTimerTask = new TimerTask() { // from class: com.huya.berry.module.living.heartbeat.BaseHeartBeat.1
        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            BaseHeartBeat.this.sendHeartBeat();
        }
    };

    public interface HeartBeatListener {
        void afterHeartBeat();

        void onHeartBeatError(VolleyError volleyError);
    }

    protected abstract Req initHeartBeatReq();

    protected abstract void onHeartBeat();

    protected abstract Req updateHeartBeatReq();

    protected BaseHeartBeat(long j, HeartBeatListener heartBeatListener) {
        this.mHeartBeatDuration = 60000L;
        this.mHeartBeatDuration = j;
        this.mHeartBeatListener = heartBeatListener;
    }

    public void sendHeartBeat() {
        try {
            if (this.mHeartBeatReq == null) {
                this.mHeartBeatReq = initHeartBeatReq();
            } else {
                this.mHeartBeatReq = updateHeartBeatReq();
            }
            onHeartBeat();
            if (this.mHeartBeatListener != null) {
                this.mHeartBeatListener.afterHeartBeat();
            }
        } catch (Exception unused) {
            L.error(TAG, "heart beat error");
        }
    }

    public void startHeartBeat() {
        synchronized (this.mHeartBeatLock) {
            if (this.mHeartService != null && this.mHeartBeatTimerTask != null) {
                this.mHeartService.start(this.mHeartBeatTimerTask, 0L, this.mHeartBeatDuration);
            }
        }
    }

    public void stopHeartBeat() {
        synchronized (this.mHeartBeatLock) {
            if (this.mHeartService != null) {
                this.mHeartService.stop();
            }
            this.mHeartBeatReq = null;
        }
    }
}
