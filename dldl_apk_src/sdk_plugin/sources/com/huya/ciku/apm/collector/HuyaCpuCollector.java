package com.huya.ciku.apm.collector;

import com.duowan.monitor.jce.EUnit;
import com.duowan.monitor.utility.MonitorLog;
import com.huya.ciku.apm.MonitorCenter;
import com.huya.ciku.apm.util.DeviceInfo;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class HuyaCpuCollector extends CycleCollector implements DeviceInfo.CollectCpuCallback {
    private static final String METRIC_CPU_USAGE = "cpuusage";
    private static final String METRIC_TOTAL_CPU_USAGE = "totalcpuusage";
    private boolean isOnlyLivingEnabled;
    private long mDuration;

    private long getRealDuration(long j) {
        if (j == 0) {
            j = 500;
        }
        if (j < 100) {
            return 500L;
        }
        return j;
    }

    public HuyaCpuCollector() {
        super(2000L);
        this.mDuration = 500L;
        this.isOnlyLivingEnabled = false;
    }

    @Override // com.huya.ciku.apm.collector.CycleCollector, com.duowan.monitor.core.OnConfigListener
    public void onConfig(JSONObject jSONObject) {
        super.onConfig(jSONObject);
        long realDuration = getRealDuration(jSONObject != null ? jSONObject.optLong("duration") : 0L);
        if (this.mDuration != realDuration) {
            this.mDuration = realDuration;
        }
        if (jSONObject != null) {
            this.isOnlyLivingEnabled = jSONObject.optBoolean("isOnlyLivingEnabled");
        }
    }

    @Override // com.huya.ciku.apm.collector.CycleCollector
    public void doCollect() {
        boolean z = this.isOnlyLivingEnabled;
        if (!z || (z && MonitorCenter.getInstance().isLiving())) {
            MonitorLog.d("CpuCollector", "doCollect");
            DeviceInfo.getInstance().collectCpu(this.mDuration, this);
        }
    }

    @Override // com.huya.ciku.apm.util.DeviceInfo.CollectCpuCallback
    public void onComplete(DeviceInfo.CpuInfo cpuInfo) {
        if (cpuInfo == null) {
            return;
        }
        if (cpuInfo.app > 0.0f) {
            MonitorCenter.getInstance().request(METRIC_CPU_USAGE, cpuInfo.app, EUnit.EUnit_Percent);
        }
        if (cpuInfo.cpu > 0) {
            MonitorCenter.getInstance().request(METRIC_TOTAL_CPU_USAGE, cpuInfo.cpu, EUnit.EUnit_Percent);
        }
    }
}
