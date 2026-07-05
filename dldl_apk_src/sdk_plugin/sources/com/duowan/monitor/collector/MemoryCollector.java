package com.duowan.monitor.collector;

import com.duowan.monitor.MonitorSDK;
import com.duowan.monitor.core.DeviceInfo;
import com.duowan.monitor.jce.EUnit;
import com.duowan.monitor.utility.MonitorLog;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class MemoryCollector extends CycleCollector {
    private static final int DEFAULT_INTERVAL = 60000;
    private static final String TAG = "MemoryCollector";

    @Override // com.duowan.monitor.collector.CycleCollector, com.duowan.monitor.core.OnConfigListener
    public /* bridge */ /* synthetic */ void onConfig(JSONObject jSONObject) {
        super.onConfig(jSONObject);
    }

    @Override // com.duowan.monitor.collector.CycleCollector, com.duowan.monitor.core.OnStatusChangeListener
    public /* bridge */ /* synthetic */ void onStart() {
        super.onStart();
    }

    @Override // com.duowan.monitor.collector.CycleCollector, com.duowan.monitor.core.OnStatusChangeListener
    public /* bridge */ /* synthetic */ void onStop() {
        super.onStop();
    }

    public MemoryCollector() {
        super(60000L);
    }

    @Override // com.duowan.monitor.collector.CycleCollector
    public void doCollect() {
        MonitorLog.d(TAG, "doCollect");
        long maxMemory = DeviceInfo.getInstance().getMaxMemory();
        MonitorSDK.request(MonitorSDK.createMetric("performance", "allocate_heap_size", DeviceInfo.getInstance().getAllocateMemory(), EUnit.EUnit_Bytes));
        MonitorSDK.request(MonitorSDK.createMetric("performance", "max_heap_size", maxMemory, EUnit.EUnit_Bytes));
        MonitorSDK.request(MonitorSDK.createMetric("performance", "app_heap_size", DeviceInfo.getInstance().getAppMemory(), EUnit.EUnit_Bytes));
        MonitorSDK.request(MonitorSDK.createMetric("performance", "total_heap_size", DeviceInfo.getInstance().getTotalMemory(), EUnit.EUnit_Bytes));
        MonitorSDK.request(MonitorSDK.createMetric("performance", "free_heap_size", DeviceInfo.getInstance().getFreeMemory(), EUnit.EUnit_Bytes));
        MonitorSDK.request(MonitorSDK.createMetric("performance", "memory", Math.round(DeviceInfo.getInstance().getPssRatio() * 10000.0d) / 100, EUnit.EUnit_Percent));
        MonitorSDK.request(MonitorSDK.createMetric("performance", "java_memory", Math.round((r2 / r0) * 10000.0d) / 100, EUnit.EUnit_Percent));
    }
}
