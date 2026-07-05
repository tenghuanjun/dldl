package com.huya.mtp.hyns.stat;

import android.net.Uri;
import android.text.TextUtils;
import com.android.volley.VolleyError;
import com.huya.data.MonitorReqData;
import com.huya.hysignal.core.HySignalException;
import com.huya.mtp.data.exception.DataException;
import com.huya.mtp.data.transporter.http.HttpTransporter;
import com.huya.mtp.hyns.MtpMarsTransporter;
import com.huya.mtp.hyns.stat.NSStatData;
import com.sqwan.bugless.util.FileUtil;
import java.util.ArrayList;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class NSStatUtil {
    public static final String DEFAULT_APP = "huya";
    public static String mAppId = "";
    public static boolean mEnabled = true;
    private static final Pattern APP_ID_PATTERN = Pattern.compile("^[A-Za-z][_0-9A-Za-z]*$");
    private static final Pattern NAMESPACE_PATTERN = Pattern.compile("^[A-Za-z][._0-9A-Za-z]*$");

    public static String formatDimensionValue(String str) {
        return str != null ? str : "none";
    }

    public static int getTransportType(HttpTransporter httpTransporter) {
        return httpTransporter instanceof MtpMarsTransporter ? 3 : 2;
    }

    public static MonitorReqData createMetricDetail() {
        MonitorReqData monitorReqData = new MonitorReqData();
        monitorReqData.iTS = System.currentTimeMillis();
        monitorReqData.sMetricName = "hymtp.hyns.monitor.client";
        return monitorReqData;
    }

    public static String createMetricName(String str, String str2, String str3) {
        String str4;
        if (DEFAULT_APP.equals(str)) {
            str4 = "";
        } else {
            str4 = str + FileUtil.FILE_EXTENSION_SEPARATOR;
        }
        return str4 + str2 + FileUtil.FILE_EXTENSION_SEPARATOR + str3;
    }

    public static boolean isValidNamespace(String str) {
        return str != null && NAMESPACE_PATTERN.matcher(str).matches();
    }

    public static ArrayList<MonitorReqData.DimensionWrapper> createApiDimension(NSStatData nSStatData) {
        ArrayList<MonitorReqData.DimensionWrapper> arrayList = new ArrayList<>();
        arrayList.add(new MonitorReqData.DimensionWrapper("schema", nSStatData.scheme != null ? nSStatData.scheme.toString() : "none"));
        arrayList.add(new MonitorReqData.DimensionWrapper("authority", formatDimensionValue(nSStatData.authority)));
        arrayList.add(new MonitorReqData.DimensionWrapper("dataType", nSStatData.dataType != null ? nSStatData.dataType.toString() : "none"));
        return arrayList;
    }

    public static boolean shouldReport(NSStatData nSStatData) {
        if (TextUtils.isEmpty(nSStatData.path) || nSStatData.dataType != NSStatData.DataType.wup) {
        }
        return true;
    }

    public static Throwable parseThrowable(DataException dataException) {
        for (Throwable cause = dataException.getCause(); cause != null; cause = cause.getCause()) {
            if ((cause instanceof VolleyError) || (cause instanceof DataException) || (cause instanceof HySignalException)) {
                dataException = cause;
            }
        }
        return dataException;
    }

    public static void initStat(int i, String str, String str2, String str3, String str4, int i2, int i3, int i4, int i5, String str5, int i6, boolean z, NSStatData nSStatData, long j) {
        String path;
        String str6 = "none";
        if (i2 == 2) {
            nSStatData.scheme = NSStatData.Scheme.http;
            try {
                Uri uri = Uri.parse(str);
                nSStatData.authority = uri.getAuthority().replaceFirst(":", "_");
                if (z) {
                    path = str3 + '/' + str4;
                } else {
                    path = uri.getPath();
                }
                nSStatData.path = path;
            } catch (Exception unused) {
                nSStatData.authority = "none";
                if (z) {
                    str6 = str3 + '/' + str4;
                }
                nSStatData.path = str6;
            }
        } else if (i2 == 3) {
            nSStatData.scheme = NSStatData.Scheme.hysignal;
            nSStatData.authority = "none";
            if (z) {
                str6 = str3 + '/' + str4;
            }
            nSStatData.path = str6;
        }
        nSStatData.dataType = z ? NSStatData.DataType.wup : NSStatData.DataType.json;
        nSStatData.success = i3;
        nSStatData.retCode = i4;
        nSStatData.errorInfo = str5;
        nSStatData.requestSize = i;
        nSStatData.responseSize = i5;
        nSStatData.responseTime = i6;
        nSStatData.suspendTime = j;
        nSStatData.reportId = str2;
    }
}
