package com.huya.ciku.apm.collector;

import com.duowan.monitor.jce.EUnit;
import com.huya.ciku.apm.MonitorCenter;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class HuyaMarsStatusCollector extends CycleCollector {
    private static final String METRIC_MARS_STATUS = "marsstatus";
    private static final int STATUS_CONNECTED = 4;
    private int mStatus;

    @Override // com.huya.ciku.apm.collector.CycleCollector, com.duowan.monitor.core.OnStatusChangeListener
    public void onStart() {
    }

    public HuyaMarsStatusCollector() {
        super(1000L);
        this.mStatus = 4;
    }

    public void start() {
        if (this.mStopped) {
            this.mStopped = false;
            update();
        }
    }

    @Override // com.huya.ciku.apm.collector.CycleCollector
    public void doCollect() {
        MonitorCenter.getInstance().request(METRIC_MARS_STATUS, this.mStatus == 4 ? 1.0d : 0.0d, EUnit.EUnit_Count);
    }

    public void collectStatus(int i) {
        this.mStatus = i;
    }
}
