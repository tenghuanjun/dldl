package com.duowan.auk.app;

import android.app.Activity;
import com.duowan.auk.util.L;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class ArkReport {
    public static ReportCallback sReportCallback;

    public interface ReportCallback {
        void onEvent(String str);

        void onEvent(String str, String str2);
    }

    public static void error(String str) {
    }

    public static void setReportCallback(ReportCallback reportCallback) {
        sReportCallback = reportCallback;
    }

    public static void event(String str) {
        ReportCallback reportCallback = sReportCallback;
        if (reportCallback != null) {
            reportCallback.onEvent(str);
        }
        L.info(str, "report:event %s", str);
    }

    public static void event(String str, String str2) {
        ReportCallback reportCallback = sReportCallback;
        if (reportCallback != null) {
            reportCallback.onEvent(str, str2);
        }
        L.info(str, "report:event %s %s", str, str2);
    }

    public static void event(String str, Map<String, String> map) {
        L.info(str, "report:event %s", str);
    }

    public static void resume(Activity activity) {
        L.info(activity, "report:activity resume");
    }

    public static void pause(Activity activity) {
        L.info(activity, "report:activity pause");
    }
}
