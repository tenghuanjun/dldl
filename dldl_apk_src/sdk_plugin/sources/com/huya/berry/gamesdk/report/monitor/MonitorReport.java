package com.huya.berry.gamesdk.report.monitor;

import com.duowan.auk.ArkUtils;
import com.duowan.auk.util.L;
import com.huya.berry.gamesdk.report.monitor.MonitorReportInterface;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class MonitorReport {
    private static final String TAG = "MonitorReport";

    public static void event(String str, Map<String, String> map) {
        ArkUtils.send(new MonitorReportInterface.ReportEvent(str, map));
        L.info(TAG, "report:event %s", "metricName:" + str + ",metricParams:" + map);
    }

    public static String formatVersion(String str) {
        if (str.contains("SNAPSHOT")) {
            str = str.substring(0, str.indexOf("SNAPSHOT") - 1);
        }
        String[] strArrSplit = str.split("\\.");
        if (strArrSplit.length == 0) {
            return "0";
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (String str2 : strArrSplit) {
            if (str2.length() == 2) {
                stringBuffer.append(str2);
            } else if (str2.length() == 1) {
                if (stringBuffer.length() == 0) {
                    stringBuffer.append(str2);
                } else {
                    stringBuffer.append(0);
                    stringBuffer.append(str2);
                }
            }
        }
        if (strArrSplit.length == 4) {
            stringBuffer.append(strArrSplit[3]);
            stringBuffer.append(strArrSplit[3]);
        } else {
            stringBuffer.append("0000");
        }
        return stringBuffer.toString();
    }
}
