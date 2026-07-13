package com.volcengine.common.sdkmonitor;

import android.util.ArrayMap;
import com.bytedance.framwork.core.sdkmonitor.SDKMonitor;
import com.bytedance.framwork.core.sdkmonitor.SDKMonitorUtils;
import com.volcengine.androidcloud.common.log.AcLog;
import com.volcengine.common.SDKContext;
import com.volcengine.common.innerapi.ISDKMonitor;
import com.volcengine.j.j;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
class SDKMonitorImpl implements ISDKMonitor {
    private static final String SDK_MONITOR_AID = "1010";
    public static final String TAG = "SDKMonitor";
    private SDKMonitor sdkMonitor = null;

    class a implements SDKMonitor.IGetExtendParams {
        a(SDKMonitorImpl sDKMonitorImpl) {
        }

        public Map<String, String> getCommonParams() {
            return new ArrayMap();
        }

        public String getSessionId() {
            return null;
        }
    }

    private List<String> getDefaultMonitorConfigUrl(int i) {
        return Arrays.asList(i == 1 ? new String[]{"https://vegamemon.volces.com/monitor/appmonitor/v2/settings"} : new String[]{"https://vephonemon.volces.com/monitor/appmonitor/v2/settings"});
    }

    private List<String> getDefaultMonitorReportUrl(int i) {
        return Collections.singletonList(i == 1 ? "https://vegamemon.volces.com/monitor/collect/" : "https://vephonemon.volces.com/monitor/collect/");
    }

    @Override // com.volcengine.common.innerapi.ISDKMonitor
    public void init(int i, Map<String, String> map) {
        if (this.sdkMonitor != null) {
            return;
        }
        AcLog.d(TAG, "init initParams: " + map);
        List<String> defaultMonitorConfigUrl = getDefaultMonitorConfigUrl(i);
        List<String> defaultMonitorReportUrl = getDefaultMonitorReportUrl(i);
        JSONObject jSONObject = new JSONObject(map);
        SDKMonitorUtils.setConfigUrl(SDK_MONITOR_AID, defaultMonitorConfigUrl);
        SDKMonitorUtils.setDefaultReportUrl(SDK_MONITOR_AID, defaultMonitorReportUrl);
        SDKMonitorUtils.initMonitor(SDKContext.getContext(), SDK_MONITOR_AID, jSONObject, new a(this));
        this.sdkMonitor = SDKMonitorUtils.getInstance(SDK_MONITOR_AID);
    }

    @Override // com.volcengine.common.innerapi.ISDKMonitor
    public void monitorCommonLog(String str, JSONObject jSONObject) {
        SDKMonitor sDKMonitor = this.sdkMonitor;
        if (sDKMonitor != null) {
            sDKMonitor.monitorCommonLog(str, j.a(jSONObject));
        }
    }

    @Override // com.volcengine.common.innerapi.ISDKMonitor
    public void monitorEvent(String str, JSONObject jSONObject, JSONObject jSONObject2, JSONObject jSONObject3) {
        SDKMonitor sDKMonitor = this.sdkMonitor;
        if (sDKMonitor != null) {
            sDKMonitor.monitorEvent(str, j.a(jSONObject), j.a(jSONObject2), j.a(jSONObject3));
        }
    }
}
