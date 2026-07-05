package com.huya.mtp.hyns.stat;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.LruCache;
import com.huya.data.MonitorReqData;
import com.huya.mtp.api.MTPApi;
import com.huya.mtp.hyns.stat.NSStatData;
import com.huya.mtp.hyns.util.EasyTimer;
import com.huya.mtp.hyns.util.Pools;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class NSStatManager {
    private static final int CACHE_GET_MAX_TIMES = 10;
    private static final int CHECK_SUSPEND_INTERNAL = 5000;
    public static final int ERROR_REASON_BIZ = 0;
    public static final int ERROR_REASON_SIGNAL = 1;
    private static final int MAX_SUSPEND_DURATION = 10000;
    private static NSStatManager mInstance;
    private Handler mReportHandler;
    private HandlerThread mReportThread;
    private long mResumeTime;
    private long mSuspendStartTime;
    private int mTimerCount;
    private static Pools.SynchronizedPool<NSStatData> sPool = new Pools.SynchronizedPool<>(200);
    private static volatile Object LOCK = new Object();
    private LruCache<String, MonitorReqData> mMonitorDataLruCache = new LruCache<>(300);
    private LruCache<String, Integer> mMonitorDataReTryGetTimeMap = new LruCache<>(300);
    private int count = 0;
    private final EasyTimer mUserHeartTimer = new EasyTimer();

    static /* synthetic */ int access$008(NSStatManager nSStatManager) {
        int i = nSStatManager.count;
        nSStatManager.count = i + 1;
        return i;
    }

    static /* synthetic */ int access$010(NSStatManager nSStatManager) {
        int i = nSStatManager.count;
        nSStatManager.count = i - 1;
        return i;
    }

    static /* synthetic */ int access$208(NSStatManager nSStatManager) {
        int i = nSStatManager.mTimerCount;
        nSStatManager.mTimerCount = i + 1;
        return i;
    }

    public static NSStatManager getInstance() {
        if (mInstance == null) {
            synchronized (LOCK) {
                if (mInstance == null) {
                    NSStatManager nSStatManager = new NSStatManager();
                    mInstance = nSStatManager;
                    return nSStatManager;
                }
            }
        }
        return mInstance;
    }

    public Handler getReportHandler() {
        return this.mReportHandler;
    }

    public void addMonitorData(String str, MonitorReqData monitorReqData) {
        this.mMonitorDataLruCache.put(str, monitorReqData);
    }

    public MonitorReqData getMonitorData(String str) {
        return this.mMonitorDataLruCache.get(str);
    }

    public void removeMonotorDataFromCache(String str) {
        this.mMonitorDataLruCache.remove(str);
        this.mMonitorDataReTryGetTimeMap.remove(str);
    }

    public boolean waitForMonitoDataCache(String str) {
        Integer numValueOf;
        Integer num = this.mMonitorDataReTryGetTimeMap.get(str);
        if (num == null) {
            numValueOf = 1;
            this.mMonitorDataReTryGetTimeMap.put(str, numValueOf);
        } else {
            LruCache<String, Integer> lruCache = this.mMonitorDataReTryGetTimeMap;
            numValueOf = Integer.valueOf(num.intValue() + 1);
            lruCache.put(str, numValueOf);
        }
        return numValueOf.intValue() <= 10;
    }

    public NSStatManager() {
        HandlerThread handlerThread = new HandlerThread("hyns-report-checker");
        this.mReportThread = handlerThread;
        handlerThread.start();
        this.mReportHandler = new Handler(this.mReportThread.getLooper());
        MTPApi.CONTEXT.getApplication().registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() { // from class: com.huya.mtp.hyns.stat.NSStatManager.1
            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityCreated(Activity activity, Bundle bundle) {
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityDestroyed(Activity activity) {
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityPaused(Activity activity) {
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityResumed(Activity activity) {
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityStarted(Activity activity) {
                if (NSStatManager.this.count == 0) {
                    NSStatManager.this.suspendTimeMark(true);
                }
                NSStatManager.access$008(NSStatManager.this);
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityStopped(Activity activity) {
                NSStatManager.access$010(NSStatManager.this);
                if (NSStatManager.this.count == 0) {
                    NSStatManager.this.suspendTimeMark(false);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void suspendTimeMark(boolean z) {
        if (z) {
            this.mUserHeartTimer.stop();
        } else {
            this.mTimerCount = 0;
            this.mUserHeartTimer.resetAndStart(5000, new Runnable() { // from class: com.huya.mtp.hyns.stat.NSStatManager.2
                @Override // java.lang.Runnable
                public void run() {
                    NSStatManager.access$208(NSStatManager.this);
                    if (NSStatManager.this.mTimerCount == 1) {
                        return;
                    }
                    long jCurrentTimeMillis = System.currentTimeMillis();
                    if (NSStatManager.this.mTimerCount == 2) {
                        NSStatManager.this.mSuspendStartTime = jCurrentTimeMillis;
                    }
                    if (jCurrentTimeMillis - NSStatManager.this.mSuspendStartTime > 10000) {
                        NSStatManager.this.mResumeTime = jCurrentTimeMillis;
                        NSStatManager.this.mTimerCount = 0;
                    } else {
                        NSStatManager.this.mSuspendStartTime = jCurrentTimeMillis;
                    }
                }
            });
        }
    }

    public long getSuspendDuration(long j, long j2) {
        long j3 = this.mSuspendStartTime;
        long j4 = this.mResumeTime;
        if (j3 >= j4 || j3 <= j || j4 >= j2) {
            return 0L;
        }
        return j4 - j3;
    }

    public NSStatData create() {
        NSStatData nSStatDataAcquire = sPool.acquire();
        return nSStatDataAcquire != null ? nSStatDataAcquire : new NSStatData();
    }

    public void recycle(NSStatData nSStatData) {
        if (nSStatData != null) {
            nSStatData.clear();
            sPool.release(nSStatData);
        }
    }

    public void wup(String str) {
        create().dataType = NSStatData.DataType.wup;
    }
}
