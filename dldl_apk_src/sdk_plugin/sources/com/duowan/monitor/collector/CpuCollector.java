package com.duowan.monitor.collector;

import com.duowan.monitor.MonitorSDK;
import com.duowan.monitor.core.DeviceInfo;
import com.duowan.monitor.jce.EUnit;
import com.duowan.monitor.utility.MonitorLog;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class CpuCollector extends CycleCollector implements DeviceInfo.CollectCpuCallback {
    private static final int DEFAULT_DURATION = 500;
    private static final int DEFAULT_INTERVAL = 60000;
    private static final int MIN_DURATION = 100;
    private static final String TAG = "CpuCollector";
    private long mDuration;

    private long getRealDuration(long j) {
        if (j == 0) {
            j = 500;
        }
        if (j < 100) {
            return 100L;
        }
        return j;
    }

    @Override // com.duowan.monitor.collector.CycleCollector, com.duowan.monitor.core.OnStatusChangeListener
    public /* bridge */ /* synthetic */ void onStart() {
        super.onStart();
    }

    @Override // com.duowan.monitor.collector.CycleCollector, com.duowan.monitor.core.OnStatusChangeListener
    public /* bridge */ /* synthetic */ void onStop() {
        super.onStop();
    }

    public CpuCollector() {
        super(60000L);
        this.mDuration = 500L;
    }

    @Override // com.duowan.monitor.collector.CycleCollector, com.duowan.monitor.core.OnConfigListener
    public void onConfig(JSONObject jSONObject) {
        super.onConfig(jSONObject);
        long realDuration = getRealDuration(jSONObject != null ? jSONObject.optLong("duration") : 0L);
        if (this.mDuration != realDuration) {
            this.mDuration = realDuration;
        }
    }

    @Override // com.duowan.monitor.collector.CycleCollector
    public void doCollect() {
        MonitorLog.d(TAG, "doCollect");
        DeviceInfo.getInstance().collectCpu(this.mDuration, this);
    }

    @Override // com.duowan.monitor.core.DeviceInfo.CollectCpuCallback
    public void onComplete(DeviceInfo.CpuInfo cpuInfo) {
        MonitorSDK.request(MonitorSDK.createMetric("performance", "cpu", cpuInfo.app, EUnit.EUnit_Percent));
    }
}
