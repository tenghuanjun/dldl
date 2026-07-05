package com.duowan.live.one.module.report;

import android.app.Activity;
import com.huya.live.common.api.report.ReportApi;
import com.huya.statistics.core.StatisticsContent;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class ReportApiImpl implements ReportApi {
    @Override // com.huya.live.common.api.report.ReportApi
    public void init(String str, String str2, String str3, String str4) {
        Report.init(str, str2, str3, str4);
    }

    @Override // com.huya.live.common.api.report.ReportApi
    public void startLive(long j, long j2, long j3, String str) {
        Report.startLive(j, j2, j3, str);
    }

    @Override // com.huya.live.common.api.report.ReportApi
    public void stopLive() {
        Report.stopLive();
    }

    @Override // com.huya.live.common.api.report.ReportApi
    public void loginSuccess(long j) {
        Report.loginSuccess(j);
    }

    @Override // com.huya.live.common.api.report.ReportApi
    public void reportGuid(String str) {
        Report.reportGuid(str);
    }

    @Override // com.huya.live.common.api.report.ReportApi
    public void changeHuyaSessionId() {
        Report.changeHuyaSessionId();
    }

    @Override // com.huya.live.common.api.report.ReportApi
    public void eventHuya(String str, String str2) {
        Report.eventHuya(str, str2);
    }

    @Override // com.huya.live.common.api.report.ReportApi
    public void event(String str) {
        Report.event(str, null);
    }

    @Override // com.huya.live.common.api.report.ReportApi
    public void event(String str, String str2) {
        Report.event(str, str2);
    }

    @Override // com.huya.live.common.api.report.ReportApi
    public void event(String str, String str2, String str3) {
        Report.event(str, str2, str3);
    }

    @Override // com.huya.live.common.api.report.ReportApi
    public void event(String str, String str2, String str3, Map<String, String> map) {
        Report.event(str, str2, str3, map2Sc(map));
    }

    private StatisticsContent map2Sc(Map<String, String> map) {
        if (map == null) {
            return null;
        }
        StatisticsContent statisticsContent = new StatisticsContent();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            statisticsContent.put(entry.getKey(), entry.getValue());
        }
        return statisticsContent;
    }

    @Override // com.huya.live.common.api.report.ReportApi
    public void event(String str, String str2, String str3, String str4) {
        Report.event(str, str2, str3, str4);
    }

    @Override // com.huya.live.common.api.report.ReportApi
    public void event(String str, String str2, String str3, String str4, Map<String, String> map) {
        Report.event(str, str2, str3, str4, map2Sc(map));
    }

    @Override // com.huya.live.common.api.report.ReportApi
    public void value(String str, int i) {
        Report.value(str, i);
    }

    @Override // com.huya.live.common.api.report.ReportApi
    public void value(String str, String str2, int i) {
        Report.value(str, str2, i);
    }

    @Override // com.huya.live.common.api.report.ReportApi
    public void error(String str, String str2, String str3) {
        Report.error(str, str2, str3);
    }

    @Override // com.huya.live.common.api.report.ReportApi
    public void resume(Activity activity) {
        Report.resume(activity);
    }

    @Override // com.huya.live.common.api.report.ReportApi
    public void pause(Activity activity) {
        Report.pause(activity);
    }
}
