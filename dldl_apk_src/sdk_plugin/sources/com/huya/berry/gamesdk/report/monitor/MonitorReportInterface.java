package com.huya.berry.gamesdk.report.monitor;

import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class MonitorReportInterface {

    public static class ReportEvent {
        public String metricName;
        public Map<String, String> metricParams;

        public ReportEvent(String str, Map<String, String> map) {
            this.metricName = str;
            this.metricParams = map;
        }
    }
}
