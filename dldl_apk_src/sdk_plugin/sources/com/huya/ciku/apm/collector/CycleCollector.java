package com.huya.ciku.apm.collector;

import com.duowan.monitor.core.OnStatusChangeListener;
import com.duowan.monitor.utility.MonitorLog;
import com.huya.ciku.apm.util.MonitorThreadExecutor;
import java.util.concurrent.ScheduledFuture;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public abstract class CycleCollector implements OnStatusChangeListener {
    private static final int MIN_INTERVAL = 500;
    private static final String TAG = "CycleCollector";
    private final long mDefaultInterval;
    private boolean mEnabled;
    private long mInterval;
    ScheduledFuture<Runnable> mRunnableScheduledFuture;
    protected boolean mStopped = true;
    private boolean stopInBackground = false;
    private Runnable mRunnable = new Runnable() { // from class: com.huya.ciku.apm.collector.CycleCollector.1
        @Override // java.lang.Runnable
        public void run() {
            if (CycleCollector.this.mStopped || !CycleCollector.this.mEnabled) {
                return;
            }
            try {
                CycleCollector.this.doCollect();
            } catch (Exception e) {
                MonitorLog.e(CycleCollector.TAG, "run", e);
            }
        }
    };

    abstract void doCollect();

    @Override // com.duowan.monitor.core.OnStatusChangeListener
    public void onStop() {
    }

    CycleCollector(long j) {
        if (j < 500) {
            throw new IllegalArgumentException("defaultInterval must >= 500");
        }
        this.mDefaultInterval = j;
        this.mInterval = j;
    }

    @Override // com.duowan.monitor.core.OnStatusChangeListener
    public void onStart() {
        if (this.mStopped) {
            this.mStopped = false;
            update();
        }
    }

    @Override // com.duowan.monitor.core.OnConfigListener
    public void onConfig(JSONObject jSONObject) {
        boolean zOptBoolean;
        long jOptInt;
        if (jSONObject != null) {
            zOptBoolean = jSONObject.optBoolean("enabled");
            jOptInt = jSONObject.optInt("interval");
        } else {
            zOptBoolean = false;
            jOptInt = 0;
        }
        long realInterval = getRealInterval(jOptInt);
        if (this.mInterval == realInterval && this.mEnabled == zOptBoolean) {
            return;
        }
        this.mInterval = realInterval;
        this.mEnabled = zOptBoolean;
        update();
    }

    void setInterval(long j) {
        long realInterval = getRealInterval(j);
        if (this.mInterval != realInterval) {
            this.mInterval = realInterval;
            update();
        }
    }

    long getInterval() {
        return this.mInterval;
    }

    protected long getRealInterval(long j) {
        if (j == 0) {
            j = this.mDefaultInterval;
        }
        if (j < 500) {
            return 500L;
        }
        return j;
    }

    void setEnabled(boolean z) {
        if (this.mEnabled != z) {
            this.mEnabled = z;
            update();
        }
    }

    boolean isEnabled() {
        return this.mEnabled;
    }

    protected void update() {
        ScheduledFuture<Runnable> scheduledFuture = this.mRunnableScheduledFuture;
        if (scheduledFuture != null) {
            scheduledFuture.cancel(true);
        }
        MonitorThreadExecutor.remove(this.mRunnable);
        if (this.mStopped || !this.mEnabled) {
            return;
        }
        this.mRunnableScheduledFuture = MonitorThreadExecutor.execute(this.mRunnable, this.mInterval);
    }
}
