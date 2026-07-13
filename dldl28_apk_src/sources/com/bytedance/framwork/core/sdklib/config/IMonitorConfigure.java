package com.bytedance.framwork.core.sdklib.config;

import java.util.List;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public interface IMonitorConfigure {
    long getMonitorLogMaxSaveCount();

    boolean getRemoveSwitch();

    boolean isLogSendSwitch();

    int reportCount();

    int reportFailRepeatBaseTime();

    int reportFailRepeatCount();

    int reportInterval();

    JSONObject reportJsonHeaderInfo();

    List<String> reportUrl(String str);

    long stopMoreChannelInterval();
}
