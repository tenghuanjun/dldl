package com.bytedance.framwork.core.sdklib.config;

import android.text.TextUtils;
import com.bytedance.framwork.core.sdklib.MonitorCommonConstants;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class MonitorConfigure {
    private static ConcurrentHashMap<String, IMonitorConfigure> sAid2MonitorCogfigMap;
    private static IMonitorConfigure sDefCommonConfig = new DefaultMonitorConfigure();

    static {
        ConcurrentHashMap<String, IMonitorConfigure> concurrentHashMap = new ConcurrentHashMap<>();
        sAid2MonitorCogfigMap = concurrentHashMap;
        concurrentHashMap.put(MonitorCommonConstants.DEFAULT_AID, sDefCommonConfig);
    }

    public static boolean getLogRemoveSwitch(String str) {
        return ((TextUtils.isEmpty(str) || sAid2MonitorCogfigMap.get(str) == null) ? sDefCommonConfig : sAid2MonitorCogfigMap.get(str)).getRemoveSwitch();
    }

    public static long getMaxMonitorLogSaveCount(String str) {
        return ((TextUtils.isEmpty(str) || sAid2MonitorCogfigMap.get(str) == null) ? sDefCommonConfig : sAid2MonitorCogfigMap.get(str)).getMonitorLogMaxSaveCount();
    }

    public static int getReportCount(String str) {
        return ((TextUtils.isEmpty(str) || sAid2MonitorCogfigMap.get(str) == null) ? sDefCommonConfig : sAid2MonitorCogfigMap.get(str)).reportCount();
    }

    public static int getReportFailBaseTime(String str) {
        return ((TextUtils.isEmpty(str) || sAid2MonitorCogfigMap.get(str) == null) ? sDefCommonConfig : sAid2MonitorCogfigMap.get(str)).reportFailRepeatBaseTime() * 1000;
    }

    public static int getReportFailRepeatCount(String str) {
        return ((TextUtils.isEmpty(str) || sAid2MonitorCogfigMap.get(str) == null) ? sDefCommonConfig : sAid2MonitorCogfigMap.get(str)).reportFailRepeatCount();
    }

    public static int getReportInterval(String str) {
        return ((TextUtils.isEmpty(str) || sAid2MonitorCogfigMap.get(str) == null) ? sDefCommonConfig : sAid2MonitorCogfigMap.get(str)).reportInterval();
    }

    public static JSONObject getReportJsonHeaderInfo(String str) {
        return ((TextUtils.isEmpty(str) || sAid2MonitorCogfigMap.get(str) == null) ? sDefCommonConfig : sAid2MonitorCogfigMap.get(str)).reportJsonHeaderInfo();
    }

    public static List<String> getReportUrl(String str, String str2) {
        return ((TextUtils.isEmpty(str) || sAid2MonitorCogfigMap.get(str) == null) ? sDefCommonConfig : sAid2MonitorCogfigMap.get(str)).reportUrl(str2);
    }

    public static long getStopMoreChannelInterval(String str) {
        return ((TextUtils.isEmpty(str) || sAid2MonitorCogfigMap.get(str) == null) ? sDefCommonConfig : sAid2MonitorCogfigMap.get(str)).stopMoreChannelInterval();
    }

    public static void setCommonConfig(IMonitorConfigure iMonitorConfigure) {
        sDefCommonConfig = iMonitorConfigure;
        setCommonConfig(MonitorCommonConstants.DEFAULT_AID, iMonitorConfigure);
    }

    public static void setCommonConfig(String str, IMonitorConfigure iMonitorConfigure) {
        if (iMonitorConfigure == null) {
            return;
        }
        sAid2MonitorCogfigMap.put(str, iMonitorConfigure);
    }
}
