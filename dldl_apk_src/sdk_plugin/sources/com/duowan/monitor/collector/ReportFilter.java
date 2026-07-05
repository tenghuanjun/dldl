package com.duowan.monitor.collector;

import android.text.TextUtils;
import com.duowan.monitor.core.MetricFilter;
import com.duowan.monitor.core.OnStatusChangeListener;
import com.duowan.monitor.jce.MetricDetail;
import com.duowan.monitor.utility.MonitorLog;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class ReportFilter implements OnStatusChangeListener, MetricFilter {
    String[] mBlackList;
    String[] mWhiteList;

    @Override // com.duowan.monitor.core.OnStatusChangeListener
    public void onStart() {
    }

    @Override // com.duowan.monitor.core.OnStatusChangeListener
    public void onStop() {
    }

    @Override // com.duowan.monitor.core.OnConfigListener
    public void onConfig(JSONObject jSONObject) {
        if (jSONObject == null) {
            return;
        }
        String jsonString = getJsonString(jSONObject, "white_list");
        String jsonString2 = getJsonString(jSONObject, "black_list");
        if (!TextUtils.isEmpty(jsonString)) {
            MonitorLog.d("MonitorLog", "white_list : " + jsonString);
            this.mWhiteList = jsonString.split(",");
        }
        if (TextUtils.isEmpty(jsonString2)) {
            return;
        }
        MonitorLog.d("MonitorLog", "black_list : " + jsonString2);
        this.mBlackList = jsonString2.split(",");
    }

    @Override // com.duowan.monitor.core.MetricFilter
    public boolean doFilter(MetricDetail metricDetail) {
        String[] strArr = this.mWhiteList;
        String[] strArr2 = this.mBlackList;
        if (strArr != null && strArr.length > 0) {
            for (String str : strArr) {
                if (like(metricDetail.sMetricName, str)) {
                    return false;
                }
            }
            return true;
        }
        if (strArr2 != null && strArr2.length > 0) {
            for (String str2 : strArr2) {
                if (like(metricDetail.sMetricName, str2)) {
                    return true;
                }
            }
        }
        MonitorLog.d("MonitorLog", metricDetail.sMetricName);
        return false;
    }

    private String getJsonString(JSONObject jSONObject, String str) {
        try {
            return jSONObject.getString(str);
        } catch (JSONException e) {
            e.printStackTrace();
            return "";
        }
    }

    private boolean like(String str, String str2) {
        if (str2.startsWith("%") && str2.endsWith("%")) {
            if (str.contains(str2.substring(1, str2.length() - 1))) {
                return true;
            }
        } else if (str2.startsWith("%")) {
            if (str.endsWith(str2.substring(1))) {
                return true;
            }
        } else if (str2.endsWith("%")) {
            if (str.startsWith(str2.substring(0, str2.length() - 1))) {
                return true;
            }
        } else if (str.equals(str2)) {
            return true;
        }
        return false;
    }
}
