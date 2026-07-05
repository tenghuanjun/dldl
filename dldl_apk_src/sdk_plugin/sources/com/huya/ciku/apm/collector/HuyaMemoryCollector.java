package com.huya.ciku.apm.collector;

import com.duowan.monitor.core.DeviceInfo;
import com.duowan.monitor.jce.EUnit;
import com.huya.ciku.apm.MonitorCenter;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class HuyaMemoryCollector extends CycleCollector {
    public static final String METRIC_MEMORY_USAGE = "memoryusage";
    private boolean isOnlyLivingEnabled;

    public HuyaMemoryCollector() {
        super(2000L);
        this.isOnlyLivingEnabled = false;
    }

    @Override // com.huya.ciku.apm.collector.CycleCollector, com.duowan.monitor.core.OnConfigListener
    public void onConfig(JSONObject jSONObject) {
        super.onConfig(jSONObject);
        if (jSONObject != null) {
            this.isOnlyLivingEnabled = jSONObject.optBoolean("isOnlyLivingEnabled");
        }
    }

    @Override // com.huya.ciku.apm.collector.CycleCollector
    public void doCollect() {
        boolean z = this.isOnlyLivingEnabled;
        if (!z || (z && MonitorCenter.getInstance().isLiving())) {
            MonitorCenter.getInstance().request(METRIC_MEMORY_USAGE, (DeviceInfo.getInstance().getAppMemory() / 1024.0d) / 1024.0d, EUnit.EUnit_MegabytesPerSecond);
        }
    }
}
