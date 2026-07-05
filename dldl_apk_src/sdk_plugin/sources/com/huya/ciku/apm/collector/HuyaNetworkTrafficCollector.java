package com.huya.ciku.apm.collector;

import android.net.TrafficStats;
import android.os.Process;
import android.os.SystemClock;
import android.support.v4.media.session.PlaybackStateCompat;
import com.duowan.monitor.jce.EUnit;
import com.duowan.monitor.utility.MonitorLog;
import com.huya.ciku.apm.MonitorCenter;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class HuyaNetworkTrafficCollector extends CycleCollector {
    private static final String METRIC_NETWORK_DOWNLOAD = "download";
    private static final String METRIC_NETWORK_UPLOAD = "upload";
    private boolean isOnlyLivingEnabled;
    private final int mPid;
    private long mRecordRx;
    private long mRecordTime;
    private long mRecordTx;

    public HuyaNetworkTrafficCollector() {
        super(20000L);
        this.mPid = Process.myUid();
        this.isOnlyLivingEnabled = false;
    }

    @Override // com.huya.ciku.apm.collector.CycleCollector, com.duowan.monitor.core.OnConfigListener
    public void onConfig(JSONObject jSONObject) {
        super.onConfig(jSONObject);
        if (!isEnabled()) {
            this.mRecordTime = 0L;
        }
        if (jSONObject != null) {
            this.isOnlyLivingEnabled = jSONObject.optBoolean("isOnlyLivingEnabled");
        }
    }

    @Override // com.huya.ciku.apm.collector.CycleCollector
    public void doCollect() {
        boolean z = this.isOnlyLivingEnabled;
        if (!z || (z && MonitorCenter.getInstance().isLiving())) {
            MonitorLog.d("NetworkTrafficCollector", "doCollect");
            long j = this.mRecordRx;
            long j2 = this.mRecordTx;
            long j3 = this.mRecordTime;
            this.mRecordRx = TrafficStats.getUidRxBytes(this.mPid);
            this.mRecordTx = TrafficStats.getUidTxBytes(this.mPid);
            long jElapsedRealtime = SystemClock.elapsedRealtime();
            this.mRecordTime = jElapsedRealtime;
            long j4 = jElapsedRealtime - j3;
            if (j == 0 || j2 == 0) {
                return;
            }
            MonitorCenter.getInstance().request(METRIC_NETWORK_DOWNLOAD, Math.round((((this.mRecordRx - j) * 1000) / j4) / PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID), EUnit.EUnit_KilobytesPerSecond);
            MonitorCenter.getInstance().request(METRIC_NETWORK_UPLOAD, Math.round((((this.mRecordTx - j2) * 1000) / j4) / PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID), EUnit.EUnit_KilobytesPerSecond);
        }
    }
}
