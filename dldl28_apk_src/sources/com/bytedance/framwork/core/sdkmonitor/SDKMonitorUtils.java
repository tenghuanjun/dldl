package com.bytedance.framwork.core.sdkmonitor;

import android.content.Context;
import android.text.TextUtils;
import com.bytedance.framwork.core.sdkmonitor.SDKMonitor;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class SDKMonitorUtils {
    private static ConcurrentHashMap<String, SDKMonitor> sAidToMonitorCommon = new ConcurrentHashMap<>();

    public static SDKMonitor getInstance(String str) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("param 'aid' is not allowed to assigned empty string");
        }
        if (sAidToMonitorCommon.get(str) == null) {
            synchronized (SDKMonitorUtils.class) {
                if (sAidToMonitorCommon.get(str) == null) {
                    sAidToMonitorCommon.put(str, new SDKMonitor(str));
                }
            }
        }
        return sAidToMonitorCommon.get(str);
    }

    public static String getSdkVersion() {
        return "2.0.9";
    }

    @Deprecated
    public static synchronized void init(Context context, String str, JSONObject jSONObject, SDKMonitor.IGetCommonParams iGetCommonParams) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("param 'aid' is not allowed to assigned empty string");
        }
        if (jSONObject == null) {
            throw new IllegalArgumentException("param 'headerInfo' is not allowed to be null");
        }
        if (sAidToMonitorCommon.get(str) == null || !sAidToMonitorCommon.get(str).isHasInit()) {
            if (sAidToMonitorCommon.get(str) == null) {
                SDKMonitor sDKMonitor = new SDKMonitor(str);
                sDKMonitor.init(context, jSONObject, iGetCommonParams);
                sAidToMonitorCommon.put(str, sDKMonitor);
            } else {
                sAidToMonitorCommon.get(str).init(context, jSONObject, iGetCommonParams);
            }
        }
    }

    public static synchronized void initMonitor(Context context, String str, JSONObject jSONObject, SDKMonitor.IGetExtendParams iGetExtendParams) {
        if (context == null) {
            return;
        }
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("param 'aid' is not allowed to assigned empty string");
        }
        if (jSONObject == null) {
            throw new IllegalArgumentException("param 'headerInfo' is not allowed to be null");
        }
        if (sAidToMonitorCommon.get(str) == null || !sAidToMonitorCommon.get(str).isHasInit()) {
            if (sAidToMonitorCommon.get(str) == null) {
                SDKMonitor sDKMonitor = new SDKMonitor(str);
                sDKMonitor.init(context, jSONObject, iGetExtendParams);
                sAidToMonitorCommon.put(str, sDKMonitor);
            } else {
                sAidToMonitorCommon.get(str).init(context, jSONObject, iGetExtendParams);
            }
        }
    }

    public static synchronized void setConfigUrl(String str, List<String> list) {
        if (!TextUtils.isEmpty(str) && !ListUtils.isEmpty(list)) {
            SDKMonitor.setConfigUrl(str, list);
        }
    }

    @Deprecated
    public static synchronized void setDeafultReportUrl(String str, List<String> list) {
        if (!TextUtils.isEmpty(str) && !ListUtils.isEmpty(list)) {
            SDKMonitor.setDeafultReportUrl(str, list);
        }
    }

    public static synchronized void setDefaultReportUrl(String str, List<String> list) {
        if (!TextUtils.isEmpty(str) && !ListUtils.isEmpty(list)) {
            SDKMonitor.setDeafultReportUrl(str, list);
        }
    }
}
