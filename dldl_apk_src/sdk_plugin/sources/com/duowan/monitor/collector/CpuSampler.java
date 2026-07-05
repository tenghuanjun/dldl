package com.duowan.monitor.collector;

import com.duowan.monitor.core.DeviceInfo;
import com.snail.antifake.deviceid.ShellAdbUtils;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
class CpuSampler extends CycleCollector implements DeviceInfo.CollectCpuCallback {
    private static final int DEFAULT_INTERVAL = 1000;
    private static final int MAX_ENTRY_COUNT = 10;
    private static final int MIN_DURATION = 100;
    private static final SimpleDateFormat TIME_FORMATTER = new SimpleDateFormat("MM-dd HH:mm:ss.SSS", Locale.US);
    private final LinkedHashMap<Long, String> mCpuInfoEntries;
    private long mDuration;

    @Override // com.duowan.monitor.collector.CycleCollector, com.duowan.monitor.core.OnConfigListener
    public void onConfig(JSONObject jSONObject) {
    }

    CpuSampler() {
        super(1000L);
        this.mCpuInfoEntries = new LinkedHashMap<>();
        this.mDuration = 100L;
    }

    String getCpuRateInfo(long j) {
        StringBuilder sb = new StringBuilder();
        synchronized (this.mCpuInfoEntries) {
            for (Map.Entry<Long, String> entry : this.mCpuInfoEntries.entrySet()) {
                long jLongValue = entry.getKey().longValue();
                if (jLongValue >= j) {
                    sb.append(TIME_FORMATTER.format(Long.valueOf(jLongValue)));
                    sb.append(' ');
                    sb.append(entry.getValue());
                    sb.append(ShellAdbUtils.COMMAND_LINE_END);
                }
            }
        }
        return sb.toString();
    }

    @Override // com.duowan.monitor.collector.CycleCollector
    public void doCollect() {
        DeviceInfo.getInstance().collectCpu(this.mDuration, this);
    }

    void setDuration(long j) {
        if (j < 100) {
            j = 100;
        }
        this.mDuration = j;
    }

    @Override // com.duowan.monitor.core.DeviceInfo.CollectCpuCallback
    public void onComplete(DeviceInfo.CpuInfo cpuInfo) {
        synchronized (this.mCpuInfoEntries) {
            this.mCpuInfoEntries.put(Long.valueOf(System.currentTimeMillis()), "cpu:" + cpuInfo.cpu + "% app:" + cpuInfo.app + "% [user:" + cpuInfo.user + "% system:" + cpuInfo.system + "% ioWait:" + cpuInfo.ioWait + "% ]");
            if (this.mCpuInfoEntries.size() > 10) {
                Iterator<Map.Entry<Long, String>> it = this.mCpuInfoEntries.entrySet().iterator();
                if (it.hasNext()) {
                    this.mCpuInfoEntries.remove(it.next().getKey());
                }
            }
        }
    }
}
