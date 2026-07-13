package com.bytedance.framwork.core.sdklib.config;

import com.bytedance.framwork.core.sdklib.MonitorCommonConstants;
import java.util.List;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class DefaultMonitorConfigure implements IMonitorConfigure {
    @Override // com.bytedance.framwork.core.sdklib.config.IMonitorConfigure
    public long getMonitorLogMaxSaveCount() {
        return 5000L;
    }

    @Override // com.bytedance.framwork.core.sdklib.config.IMonitorConfigure
    public boolean getRemoveSwitch() {
        return false;
    }

    @Override // com.bytedance.framwork.core.sdklib.config.IMonitorConfigure
    public boolean isLogSendSwitch() {
        return true;
    }

    @Override // com.bytedance.framwork.core.sdklib.config.IMonitorConfigure
    public int reportCount() {
        return 100;
    }

    @Override // com.bytedance.framwork.core.sdklib.config.IMonitorConfigure
    public int reportFailRepeatBaseTime() {
        return 15;
    }

    @Override // com.bytedance.framwork.core.sdklib.config.IMonitorConfigure
    public int reportFailRepeatCount() {
        return 4;
    }

    @Override // com.bytedance.framwork.core.sdklib.config.IMonitorConfigure
    public int reportInterval() {
        return 120;
    }

    @Override // com.bytedance.framwork.core.sdklib.config.IMonitorConfigure
    public JSONObject reportJsonHeaderInfo() {
        return null;
    }

    @Override // com.bytedance.framwork.core.sdklib.config.IMonitorConfigure
    public List<String> reportUrl(String str) {
        return null;
    }

    @Override // com.bytedance.framwork.core.sdklib.config.IMonitorConfigure
    public long stopMoreChannelInterval() {
        return MonitorCommonConstants.STOP_MORE_CHANNEL_INTERVAL;
    }
}
