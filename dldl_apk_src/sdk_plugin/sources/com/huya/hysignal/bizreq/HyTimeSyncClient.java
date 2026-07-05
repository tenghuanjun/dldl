package com.huya.hysignal.bizreq;

import android.os.Handler;
import android.os.SystemClock;
import com.duowan.jce.wup.TafUniPacket;
import com.duowan.jce.wup.UniPacket;
import com.huya.hysignal.core.Callback;
import com.huya.hysignal.core.HySignalClient;
import com.huya.hysignal.core.HySignalError;
import com.huya.hysignal.jce.WSTimeSyncReq;
import com.huya.hysignal.jce.WSTimeSyncRsp;
import com.huya.hysignal.listener.TimeAdjustListener;
import com.huya.hysignal.util.FieldsCache;
import com.huya.hysignal.util.HySignalLog;
import com.huya.hysignal.util.ThreadManager;
import com.huya.hysignal.wrapper.business.TimeSyncBiz;
import com.huya.mtp.hyns.Constants;
import com.huya.mtp.hyns.api.Request;
import com.tencent.bugly.Bugly;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class HyTimeSyncClient implements TimeSyncBiz {
    private static final long DOMESTIC_MAX_OFFSET = 50;
    private static final int MAX_LOOP_DURATION = 300000;
    private static final int MAX_SYNC_RETRY_COUNT = 3;
    private static final int MIN_LOOP_DURATION = 5000;
    private static final long OVERSEA_MAX_OFFSET = 300;
    private static final String TAG = "HyTimeSyncClient 对时";
    private static HyTimeSyncClient instance;
    private static AtomicBoolean isSyncOrAdjust = new AtomicBoolean(false);
    private final int WUP_CMD_ID = 3;
    private boolean inited = false;
    private boolean isOversea = false;
    private boolean isSyncedOnce = false;
    private int currentLoopDuration = 5000;
    private long endSyncTime = 0;
    private long syncedEpochTime = 0;
    private long offsetMax = 0;
    private long offsetMin = 0;
    private long overallOffset = 0;
    private long originSyncOffsetMax = 0;
    private long originSyncOffsetMin = 0;
    private List<TimeAdjustListener> adjustListeners = new CopyOnWriteArrayList();
    private Handler syncScheduleHandler = new Handler();
    private Runnable loopTask = new Runnable() { // from class: com.huya.hysignal.bizreq.HyTimeSyncClient.1
        @Override // java.lang.Runnable
        public void run() {
            HyTimeSyncClient.this.startLoopTask();
        }
    };

    private HyTimeSyncClient() {
    }

    public static HyTimeSyncClient getInstance() {
        if (instance == null) {
            instance = new HyTimeSyncClient();
        }
        return instance;
    }

    public synchronized void init(String str) {
        if (!this.inited) {
            boolean z = (Constants.NATIONAL_LONG_LINK_HOST.equals(str) || "testws.va.huya.com".equals(str) || Constants.CHINA_NEW_REQUEST_LONG_HOST.equals(str)) ? false : true;
            this.isOversea = z;
            this.inited = true;
            Object[] objArr = new Object[1];
            objArr[0] = z ? "true" : Bugly.SDK_IS_DEV;
            HySignalLog.info(TAG, "init isOversea: %s", objArr);
            startLoopTask();
        }
    }

    @Override // com.huya.hysignal.wrapper.business.TimeSyncBiz
    public long getEpochTime() {
        if (!this.isSyncedOnce) {
            long currentSystemTime = getCurrentSystemTime();
            HySignalLog.debug(TAG, "get epoch time before init: %d", Long.valueOf(currentSystemTime));
            return currentSystemTime;
        }
        return this.syncedEpochTime + pendTimeFromFinishSync();
    }

    @Override // com.huya.hysignal.wrapper.business.TimeSyncBiz
    public String getFormattedTime(Locale locale) {
        HySignalLog.debug(TAG, "start get format time");
        if (locale == null) {
            return getFormattedTime(getEpochTime(), Locale.SIMPLIFIED_CHINESE);
        }
        return getFormattedTime(getEpochTime(), locale);
    }

    @Override // com.huya.hysignal.wrapper.business.TimeSyncBiz
    public synchronized boolean addTimeAdjustListener(TimeAdjustListener timeAdjustListener) {
        HySignalLog.debug(TAG, "start add adjust listener");
        if (timeAdjustListener == null) {
            HySignalLog.error(TAG, "add empty listener, return");
            return false;
        }
        boolean zAdd = this.adjustListeners.add(timeAdjustListener);
        if (zAdd) {
            HySignalLog.info(TAG, "add listener success");
            if (this.isSyncedOnce) {
                timeAdjustListener.onTime(getEpochTime());
            }
        }
        return zAdd;
    }

    @Override // com.huya.hysignal.wrapper.business.TimeSyncBiz
    public synchronized boolean removeTimeAdjustListener(TimeAdjustListener timeAdjustListener) {
        HySignalLog.debug(TAG, "start remove adjust listener");
        return this.adjustListeners.remove(timeAdjustListener);
    }

    @Override // com.huya.hysignal.wrapper.business.TimeSyncBiz
    public long getCurrentMaxOffset() {
        return this.overallOffset;
    }

    private String getCurrentOffsetInterval() {
        if (this.offsetMax == 0 && this.offsetMin == 0) {
            return "-infinite ~ +infinite";
        }
        return this.offsetMin + " ~ " + this.offsetMax;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startLoopTask() {
        if (!this.isSyncedOnce) {
            requestSyncTime(3);
        } else {
            requestAdjustTime();
        }
        this.syncScheduleHandler.postDelayed(this.loopTask, getNextDuration());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void requestSyncTime(final int i) {
        HySignalLog.debug(TAG, "start request sync time, retryMax:%d", Integer.valueOf(i));
        if (!isSyncOrAdjust.compareAndSet(false, true)) {
            HySignalLog.info(TAG, "is syncing, return");
            return;
        }
        final long jElapsedRealtime = SystemClock.elapsedRealtime();
        HySignalLog.debug(TAG, "tempStartSyncTime is: %d", Long.valueOf(jElapsedRealtime));
        requestWsTimeSync(new Callback() { // from class: com.huya.hysignal.bizreq.HyTimeSyncClient.2
            @Override // com.huya.hysignal.core.Callback
            public void onResponse(final byte[] bArr, final HySignalError hySignalError) {
                final long jElapsedRealtime2 = SystemClock.elapsedRealtime();
                HySignalLog.debug(HyTimeSyncClient.TAG, "tempEndSyncTime is: %d", Long.valueOf(jElapsedRealtime2));
                ThreadManager.deliverOnResponseThread(new Runnable() { // from class: com.huya.hysignal.bizreq.HyTimeSyncClient.2.1
                    @Override // java.lang.Runnable
                    public void run() {
                        if (hySignalError.getErrType() != 0) {
                            HySignalLog.error(HyTimeSyncClient.TAG, "sync failed, errorCode:%d, errorType:%d", Integer.valueOf(hySignalError.getErrCode()), Integer.valueOf(hySignalError.getErrType()));
                            HyTimeSyncClient.isSyncOrAdjust.set(false);
                            return;
                        }
                        WSTimeSyncRsp wsTimeSyncRsp = HyTimeSyncClient.this.getWsTimeSyncRsp(bArr);
                        if (wsTimeSyncRsp == null) {
                            HySignalLog.error(HyTimeSyncClient.TAG, "decode sync rsp failed");
                            HyTimeSyncClient.isSyncOrAdjust.set(false);
                            return;
                        }
                        long lServerTime = wsTimeSyncRsp.getLServerTime();
                        HySignalLog.info(HyTimeSyncClient.TAG, "requestSyncTime server time: %d", Long.valueOf(lServerTime));
                        long j = jElapsedRealtime2 - jElapsedRealtime;
                        HySignalLog.debug(HyTimeSyncClient.TAG, "requestSyncTime currRoundTripOffset:%d", Long.valueOf(j));
                        HyTimeSyncClient.this.updateCurrentTime(jElapsedRealtime2, lServerTime, j);
                        boolean zIsOffsetOverPrecision = HyTimeSyncClient.this.isOffsetOverPrecision(j);
                        HyTimeSyncClient.isSyncOrAdjust.set(false);
                        if (!zIsOffsetOverPrecision || i <= 1) {
                            return;
                        }
                        HySignalLog.debug(HyTimeSyncClient.TAG, "retry requestSyncTime count:%d", Integer.valueOf(i - 1));
                        HyTimeSyncClient.this.requestSyncTime(i - 1);
                    }
                });
            }
        });
    }

    private void requestAdjustTime() {
        HySignalLog.debug(TAG, "start requestAdjustTime");
        if (!isSyncOrAdjust.compareAndSet(false, true)) {
            HySignalLog.info(TAG, "is syncing, return");
            return;
        }
        final long epochTime = getEpochTime();
        final long jElapsedRealtime = SystemClock.elapsedRealtime();
        HySignalLog.debug(TAG, "requestAdjustTime, startTime: %d, startBootTime:%d", Long.valueOf(epochTime), Long.valueOf(jElapsedRealtime));
        requestWsTimeSync(new Callback() { // from class: com.huya.hysignal.bizreq.HyTimeSyncClient.3
            @Override // com.huya.hysignal.core.Callback
            public void onResponse(final byte[] bArr, final HySignalError hySignalError) {
                final long epochTime2 = HyTimeSyncClient.this.getEpochTime();
                final long jElapsedRealtime2 = SystemClock.elapsedRealtime();
                HySignalLog.debug(HyTimeSyncClient.TAG, "requestAdjustTime, endTime: %d, endBootTime:%d", Long.valueOf(epochTime2), Long.valueOf(jElapsedRealtime2));
                ThreadManager.deliverOnResponseThread(new Runnable() { // from class: com.huya.hysignal.bizreq.HyTimeSyncClient.3.1
                    /* JADX WARN: Removed duplicated region for block: B:19:0x0094  */
                    @Override // java.lang.Runnable
                    /*
                        Code decompiled incorrectly, please refer to instructions dump.
                        To view partially-correct add '--show-bad-code' argument
                    */
                    public void run() {
                        /*
                            Method dump skipped, instruction units count: 330
                            To view this dump add '--comments-level debug' option
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.huya.hysignal.bizreq.HyTimeSyncClient.AnonymousClass3.AnonymousClass1.run():void");
                    }
                });
            }
        });
    }

    private void requestWsTimeSync(final Callback callback) {
        ThreadManager.deliverOnRequestThread(new Runnable() { // from class: com.huya.hysignal.bizreq.HyTimeSyncClient.4
            @Override // java.lang.Runnable
            public void run() {
                try {
                    WSTimeSyncReq wSTimeSyncReq = new WSTimeSyncReq();
                    wSTimeSyncReq.setSGuid(FieldsCache.getInstance().getGuid());
                    wSTimeSyncReq.setLClientTime(HyTimeSyncClient.this.getEpochTime());
                    UniPacket uniPacket = new UniPacket(true);
                    uniPacket.setServantName("launch");
                    uniPacket.setFuncName("wsTimeSync");
                    uniPacket.put("tReq", wSTimeSyncReq);
                    HySignalClient.getInstance().newCall(new Request.Builder().cmdId(3).cgi("/launch/wsTimeSync").networkStatusSensitive(true).body(uniPacket.encode()).channel(2).build()).enqueue(callback);
                } catch (Exception e) {
                    HySignalLog.error(HyTimeSyncClient.TAG, "buid adjust req failed" + e.getMessage());
                    callback.onResponse(null, new HySignalError(9, -100));
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public WSTimeSyncRsp getWsTimeSyncRsp(byte[] bArr) {
        try {
            TafUniPacket tafUniPacket = new TafUniPacket();
            tafUniPacket.decode(bArr);
            if (tafUniPacket.getTafStatus() != null) {
                HySignalLog.debug(TAG, "rsp status: %d, desc: %s", Integer.valueOf(tafUniPacket.getTafResultCode()), tafUniPacket.getTafResultDesc());
            }
            return (WSTimeSyncRsp) tafUniPacket.getByClass("tRsp", new WSTimeSyncRsp());
        } catch (Exception e) {
            HySignalLog.error(TAG, "get rsp failed " + e.getMessage());
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isOffsetOverPrecision(long j) {
        boolean z = !this.isOversea ? j <= DOMESTIC_MAX_OFFSET : j <= OVERSEA_MAX_OFFSET;
        Object[] objArr = new Object[1];
        objArr[0] = z ? "true" : Bugly.SDK_IS_DEV;
        HySignalLog.debug(TAG, "isOffsetOverPrecision: %s", objArr);
        return z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateCurrentTime(long j, long j2, long j3) {
        HySignalLog.debug(TAG, "start update current time");
        long j4 = this.overallOffset;
        if (j4 == 0 || j3 < j4) {
            this.endSyncTime = j;
            this.overallOffset = j3;
            long j5 = (-j3) / 2;
            this.offsetMin = j5;
            long j6 = j3 / 2;
            this.offsetMax = j6;
            this.originSyncOffsetMin = j5;
            this.originSyncOffsetMax = j6;
            this.syncedEpochTime = j2 + (j3 / 2);
            this.isSyncedOnce = true;
            notifyTimeAdjust();
            HySignalLog.debug(TAG, "updateCurrentTime, endSyncTime:%d, overallOffset:%d, syncedEpochTime:%d", Long.valueOf(j), Long.valueOf(this.overallOffset), Long.valueOf(this.syncedEpochTime));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void adjustCurrentTime() {
        long j = this.offsetMin;
        this.originSyncOffsetMin = j;
        long j2 = this.offsetMax;
        this.originSyncOffsetMax = j2;
        long j3 = this.syncedEpochTime;
        this.syncedEpochTime = ((j3 - j) + (j3 - j2)) / 2;
        notifyTimeAdjust();
        HySignalLog.debug(TAG, "adjustCurrentTime, originSyncOffsetMin:%d, originSyncOffsetMax:%d, syncedEpochTime:%d", Long.valueOf(this.originSyncOffsetMin), Long.valueOf(this.originSyncOffsetMax), Long.valueOf(this.syncedEpochTime));
    }

    private void notifyTimeAdjust() {
        Iterator<TimeAdjustListener> it = this.adjustListeners.iterator();
        while (it.hasNext()) {
            it.next().onTime(getEpochTime());
        }
    }

    private long pendTimeFromFinishSync() {
        long jElapsedRealtime = SystemClock.elapsedRealtime() - this.endSyncTime;
        HySignalLog.debug(TAG, "pendTimeFromFinishSync: %d", Long.valueOf(jElapsedRealtime));
        return jElapsedRealtime;
    }

    private String getFormattedTime(long j, Locale locale) {
        HySignalLog.debug(TAG, "getFormattedTime epoch:%d, local:%s", Long.valueOf(j), locale.toString());
        return new SimpleDateFormat("yyyy/MM/dd HH:mm:ss:SSS", locale).format(new Date(j));
    }

    private long getCurrentSystemTime() {
        long jCurrentTimeMillis = System.currentTimeMillis();
        HySignalLog.debug(TAG, "getCurrentSystemTime: %d", Long.valueOf(jCurrentTimeMillis));
        return jCurrentTimeMillis;
    }

    private int getNextDuration() {
        if (this.currentLoopDuration >= MAX_LOOP_DURATION || (!isOffsetOverPrecision(this.overallOffset) && this.isSyncedOnce)) {
            HySignalLog.debug(TAG, "getNextDuration(max): %d", Integer.valueOf(MAX_LOOP_DURATION));
            return MAX_LOOP_DURATION;
        }
        int i = this.currentLoopDuration;
        this.currentLoopDuration = i * 3;
        HySignalLog.debug(TAG, "getNextDuration: %d", Integer.valueOf(i));
        return i;
    }
}
