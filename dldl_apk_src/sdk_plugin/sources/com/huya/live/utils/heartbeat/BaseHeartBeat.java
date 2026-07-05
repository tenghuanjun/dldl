package com.huya.live.utils.heartbeat;

import android.util.Log;
import com.android.volley.VolleyError;
import com.huya.live.utils.ExecutorTimer;
import java.util.TimerTask;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public abstract class BaseHeartBeat<Req> {
    private static final long DEFAULT_HEART_BEAT_TIME = 60000;
    protected static final String TAG = "HeartBeat";
    private long mHeartBeatDuration;
    protected HeartBeatListener mHeartBeatListener;
    protected Req mHeartBeatReq;
    private final Object mHeartBeatLock = new Object();
    private ExecutorTimer mHeartBeatTimer = new ExecutorTimer("heartbeat_");
    private TimerTask mHeartBeatTimerTask = new TimerTask() { // from class: com.huya.live.utils.heartbeat.BaseHeartBeat.1
        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            try {
                if (BaseHeartBeat.this.mHeartBeatReq == null) {
                    BaseHeartBeat.this.mHeartBeatReq = (Req) BaseHeartBeat.this.initHeartBeatReq();
                } else {
                    BaseHeartBeat.this.mHeartBeatReq = (Req) BaseHeartBeat.this.updateHeartBeatReq();
                }
                BaseHeartBeat.this.onHeartBeat();
                if (BaseHeartBeat.this.mHeartBeatListener != null) {
                    BaseHeartBeat.this.mHeartBeatListener.afterHeartBeat();
                }
            } catch (Exception unused) {
                Log.e(BaseHeartBeat.TAG, "heart beat error");
            }
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
        this.mHeartBeatDuration = j;
        this.mHeartBeatListener = heartBeatListener;
    }

    public void startHeartBeat() {
        synchronized (this.mHeartBeatLock) {
            if (this.mHeartBeatTimer != null && this.mHeartBeatTimerTask != null) {
                this.mHeartBeatTimer.schedule(this.mHeartBeatTimerTask, 0L, this.mHeartBeatDuration == 0 ? 60000L : this.mHeartBeatDuration);
            }
        }
    }

    public void stopHeartBeat() {
        synchronized (this.mHeartBeatLock) {
            if (this.mHeartBeatTimer != null) {
                this.mHeartBeatTimer.stop();
                this.mHeartBeatTimer = null;
            }
            this.mHeartBeatReq = null;
        }
    }
}
