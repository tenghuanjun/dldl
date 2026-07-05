package com.duowan.monitor.collector;

import android.net.TrafficStats;
import android.os.Process;
import android.os.SystemClock;
import com.duowan.monitor.MonitorSDK;
import com.duowan.monitor.jce.EUnit;
import com.duowan.monitor.utility.MonitorLog;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class NetworkTrafficCollector extends CycleCollector {
    private static final int DEFAULT_INTERVAL = 20000;
    private static final String TAG = "NetworkTrafficCollector";
    private final int mPid;
    private long mRecordRx;
    private long mRecordTime;
    private long mRecordTx;

    @Override // com.duowan.monitor.collector.CycleCollector, com.duowan.monitor.core.OnStatusChangeListener
    public /* bridge */ /* synthetic */ void onStart() {
        super.onStart();
    }

    public NetworkTrafficCollector() {
        super(20000L);
        this.mPid = Process.myUid();
    }

    @Override // com.duowan.monitor.collector.CycleCollector, com.duowan.monitor.core.OnStatusChangeListener
    public void onStop() {
        super.onStop();
        this.mRecordTime = 0L;
    }

    @Override // com.duowan.monitor.collector.CycleCollector, com.duowan.monitor.core.OnConfigListener
    public void onConfig(JSONObject jSONObject) {
        super.onConfig(jSONObject);
        if (isEnabled()) {
            return;
        }
        this.mRecordTime = 0L;
    }

    @Override // com.duowan.monitor.collector.CycleCollector
    public void doCollect() {
        MonitorLog.d(TAG, "doCollect");
        long j = this.mRecordRx;
        long j2 = this.mRecordTx;
        long j3 = this.mRecordTime;
        this.mRecordRx = TrafficStats.getUidRxBytes(this.mPid);
        this.mRecordTx = TrafficStats.getUidTxBytes(this.mPid);
        long jUptimeMillis = SystemClock.uptimeMillis();
        this.mRecordTime = jUptimeMillis;
        long j4 = jUptimeMillis - j3;
        if (j == 0 || j2 == 0 || j4 < 1000) {
            return;
        }
        MonitorSDK.request(MonitorSDK.createMetric("performance", "net_rx", Math.round(((this.mRecordRx - j) * 1000) / j4), EUnit.EUnit_BytesPerSecond));
        MonitorSDK.request(MonitorSDK.createMetric("performance", "net_tx", Math.round(((this.mRecordTx - j2) * 1000) / j4), EUnit.EUnit_BytesPerSecond));
    }
}
