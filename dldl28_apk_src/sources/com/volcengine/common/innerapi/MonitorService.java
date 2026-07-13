package com.volcengine.common.innerapi;

import com.volcengine.cloudphone.base.CloudConfig;
import java.util.Map;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes3.dex */
public interface MonitorService {
    public static final String APP_STATE_REPORT = "app_state_report";
    public static final String START_TRACE = "start_trace";
    public static final String STOP_TRACE = "stop_trace";

    void addLowPriorityEvents(String... strArr);

    void addMonitor(ISDKMonitor iSDKMonitor);

    void addSessionExtra(String str, String str2);

    void beginSession(int i, CloudConfig cloudConfig);

    boolean checkReportStateInSession(String str);

    boolean checkReportStateInTime(String str, int i);

    void endSession(int i, boolean z);

    Map<String, Object> getCommonExtra();

    String getCurrentSessionId();

    long getPlayElapsedTime();

    long getPlayTime();

    void init(int i);

    boolean isLowPriorityEvent(String str);

    void onEvent(String str, JSONObject jSONObject);

    void removeMonitor(ISDKMonitor iSDKMonitor);

    void reportCategory(String str, int i, String str2);

    void reportCategory(String str, Map<String, Object> map);

    void reportCommon(String str, Map<String, Object> map, Map<String, Object> map2, Map<String, Object> map3);

    void reportError(String str, int i, String str2, int i2, String str3);

    void reportError(String str, int i, String str2, int i2, String str3, String str4);

    void reportMetric(String str, Map<String, Object> map);

    void reportOnlyEvent(String str);
}
