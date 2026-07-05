package com.duowan.live.one.module.report;

import android.app.Activity;
import com.duowan.auk.util.L;
import com.duowan.live.one.module.report.ReportInterface;
import com.huya.statistics.core.StatisticsContent;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class Report {
    private static final String TAG = "Report";
    private static HuyaReportModule mHuyaReportModule = new HuyaReportModule();

    public static void init(String str, String str2, String str3, String str4) {
        mHuyaReportModule.init(str, str2, str3, str4);
    }

    public static void startLive(long j, long j2, long j3, String str) {
        mHuyaReportModule.onRepotStartLive(new ReportInterface.StartLive(j, j2, j3, str));
        L.info(TAG, "startLive.");
    }

    public static void stopLive() {
        mHuyaReportModule.onRepotStopLive(new ReportInterface.StopLive());
        L.info(TAG, "stopLive.");
    }

    public static void loginSuccess(long j) {
        mHuyaReportModule.onLoginSuccess(new ReportInterface.LoginSuccess(j));
        L.info(TAG, "login.");
    }

    public static void reportGuid(String str) {
        mHuyaReportModule.onReportGuid(new ReportInterface.ReportGuid(str));
        L.info(TAG, "login.");
    }

    public static void changeHuyaSessionId() {
        mHuyaReportModule.onChangeSessionID(new ReportInterface.ChangeHuyaSessionId());
        L.info(TAG, "change huya session id.");
    }

    public static void eventHuya(String str, String str2) {
        mHuyaReportModule.onReportEvent(new ReportInterface.ReportHuyaEvent(str, str2));
        L.info(TAG, "report: eid %s eDes %s", str, str2);
    }

    public static void event(String str) {
        mHuyaReportModule.onReportEvent(new ReportInterface.ReportEvent(str));
        L.info(str, "report:event %s", str);
    }

    public static void event(String str, String str2) {
        mHuyaReportModule.onReportEvent(new ReportInterface.ReportEvent(str, str2));
        L.info(str, "report:event %s %s", str, str2);
    }

    public static void event(String str, String str2, String str3) {
        mHuyaReportModule.onReportEvent(new ReportInterface.ReportEvent(str, str2, str3));
        L.info(str, "report:event %s %s %s", str, str2, str3);
    }

    public static void event(String str, String str2, String str3, StatisticsContent statisticsContent) {
        mHuyaReportModule.onReportEvent(new ReportInterface.ReportEvent(str, str2, str3, statisticsContent));
        L.info(str, "report:event %s %s %s %s", str, str2, str3, statisticsContent);
    }

    public static void event(String str, String str2, String str3, String str4) {
        event(str, str2, str3, str4, null);
    }

    public static void event(String str, String str2, String str3, String str4, StatisticsContent statisticsContent) {
        mHuyaReportModule.onReportEvent(new ReportInterface.ReportEvent(str, str2, str3, str4, statisticsContent));
        L.info(str, "report:event %s %s %s %s %s", str, str2, str3, str4, statisticsContent);
    }

    public static void value(String str, int i) {
        L.info(str, "report:event %s %d", str, Integer.valueOf(i));
    }

    public static void value(String str, String str2, int i) {
        L.info(str, "report:event %s %s %d", str, str2, Integer.valueOf(i));
    }

    public static void error(String str, String str2, String str3) {
        L.info(str, "report:error %s %s %s", str, str2, str3);
    }

    public static void resume(Activity activity) {
        L.info(activity, "report:activity resume");
    }

    public static void pause(Activity activity) {
        L.info(activity, "report:activity pause");
    }
}
