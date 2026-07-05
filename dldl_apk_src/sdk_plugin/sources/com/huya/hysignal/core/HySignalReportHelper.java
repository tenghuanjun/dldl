package com.huya.hysignal.core;

import android.content.Context;
import android.os.Build;
import com.duowan.live.one.module.uploadLog.FeedBackConstants;
import com.huya.hysignal.listener.HySignalReportListener;
import com.huya.hysignal.util.FieldsCache;
import com.huya.hysignal.util.HySignalLog;
import com.huya.hysignal.wrapper.HySignalWrapper;
import com.huya.mtp.hyns.stat.NSStatReporter;
import com.snail.antifake.deviceid.ShellAdbUtils;
import com.sqwan.bugless.core.Constant;
import com.tencent.mars.comm.NetStatusUtil;
import com.tencent.mars.comm.PlatformComm;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
class HySignalReportHelper {
    private static final String TAG = "HySignalReportHelper";
    private static HySignalReportHelper sInstance;
    private final int EVENT_TYPE_CONNECT_TX = 0;
    private final int EVENT_TYPE_CONNECT_IP = 1;
    private String appId = "";
    private long mInitStartTime = 0;

    private HySignalReportHelper() {
    }

    static HySignalReportHelper getsInstance() {
        if (sInstance == null) {
            sInstance = new HySignalReportHelper();
        }
        return sInstance;
    }

    void init(String str) {
        updateAppSrc(str);
        this.mInitStartTime = HySignalWrapper.getInstance().getInitTime();
    }

    void updateAppSrc(String str) {
        if (str == null || "".equals(str)) {
            HySignalLog.error("set report app src empty");
            return;
        }
        String str2 = str.split("&")[0];
        if (str2 == null || "".equals(str2)) {
            HySignalLog.error("set report app Id empty");
        } else {
            this.appId = str2;
        }
    }

    void reportTaskProfile(Context context, String str, HySignalReportListener hySignalReportListener) {
        String str2 = NSStatReporter.NS_TRACE_ID;
        if (hySignalReportListener == null) {
            HySignalLog.error("report listener is null, return");
            return;
        }
        if (str == null || "".equals(str)) {
            HySignalLog.error("report string is empty, return");
            return;
        }
        try {
            HashMap map = new HashMap();
            String[] strArrSplit = str.split(ShellAdbUtils.COMMAND_LINE_END);
            int length = strArrSplit.length;
            int i = 0;
            while (i < length) {
                int i2 = length;
                String[] strArr = strArrSplit;
                String[] strArrSplit2 = strArrSplit[i].split(":");
                String str3 = str2;
                if (strArrSplit2.length == 2) {
                    map.put(strArrSplit2[0], strArrSplit2[1]);
                }
                i++;
                strArrSplit = strArr;
                length = i2;
                str2 = str3;
            }
            String str4 = str2;
            HashMap map2 = new HashMap();
            String iSPName = NetStatusUtil.getISPName(context);
            if ("".equals(iSPName)) {
                iSPName = "none";
            }
            map2.put("sdkVersion", "1.9.105-exvolley");
            map2.put(FeedBackConstants.KEY_FB_APPID, this.appId);
            map2.put("logVer", "2");
            map2.put(Constant.DEVICE_ID, FieldsCache.getInstance().getDeviceId());
            map2.put("device", Build.BRAND + "_" + Build.MODEL);
            map2.put("osVer", String.valueOf(Build.VERSION.SDK_INT));
            map2.put("ispName", iSPName);
            map2.put("ispCode", String.valueOf(NetStatusUtil.getISPCode(context)));
            map2.put("netType", getNetType());
            map2.put("taskid", map.get("taskid"));
            map2.put("cmdId", map.get("cmdId"));
            map2.put("tryCount", map.get("tryCount"));
            map2.put(NSStatReporter.NS_RETCODE, map.get("errCode"));
            map2.put("success", map.get("errType"));
            map2.put("ipType", map.get("ipType"));
            map2.put("ip", map.get("ip"));
            map2.put("ipfamilyv6", map.get("ipfamilyv6"));
            map2.put("ipindex", map.get("ipindex"));
            map2.put("cgi", map.get("cgi"));
            map2.put("channel", map.get("channel"));
            map2.put(str4, map.get(str4));
            map2.put("keepalive", map.get("keepalive"));
            map2.put("shortmaxcount", map.get("shortmaxcount"));
            map2.put("sslver", map.get("sslver"));
            map2.put("enableRefine", map.get("enableRefine"));
            map2.put("cipsorts", map.get("cipsorts"));
            map2.put("cipstack", map.get("cipstack"));
            Map<String, Double> map3 = new HashMap<>();
            map3.put("value", Double.valueOf(parseDouble((String) map.get("value"), 0.0d)));
            map3.put("queue", Double.valueOf(parseDouble((String) map.get("queue"), 0.0d)));
            map3.put("wait", Double.valueOf(parseDouble((String) map.get("wait"), 0.0d)));
            map3.put("connect", Double.valueOf(parseDouble((String) map.get("connect"), 0.0d)));
            map3.put("pack", Double.valueOf(parseDouble((String) map.get("pack"), 0.0d)));
            map3.put("beforesend", Double.valueOf(parseDouble((String) map.get("beforeSend"), 0.0d)));
            map3.put("ttfb", Double.valueOf(parseDouble((String) map.get("ttfb"), 0.0d)));
            map3.put("recv", Double.valueOf(parseDouble((String) map.get("recv"), 0.0d)));
            map3.put("unpack", Double.valueOf(parseDouble((String) map.get("unpack"), 0.0d)));
            map3.put("afterrecv", Double.valueOf(parseDouble((String) map.get("afterRecv"), 0.0d)));
            map3.put("sendlen", Double.valueOf(parseDouble((String) map.get("sendlen"), 0.0d)));
            map3.put("recvlen", Double.valueOf(parseDouble((String) map.get("recvlen"), 0.0d)));
            hySignalReportListener.report("huya.hysignal", "tx_response_time", map2, map3, new HashMap<>());
        } catch (Exception e) {
            HySignalLog.error("decode report string failed, %s", e.getMessage());
        }
    }

    void onLinkConnectError(Context context, String str, int i, long j, int i2, int i3, String str2, int i4, HySignalReportListener hySignalReportListener) {
        if (hySignalReportListener == null) {
            HySignalLog.error("report long error listener is null, return");
            return;
        }
        String str3 = i == 0 ? "tx_connect_time" : i == 1 ? "connect_time" : "";
        String iSPName = NetStatusUtil.getISPName(context);
        if ("".equals(iSPName)) {
            iSPName = "none";
        }
        HashMap map = new HashMap();
        map.put("sdkVersion", "1.9.105-exvolley");
        map.put(FeedBackConstants.KEY_FB_APPID, this.appId);
        map.put("device", Build.BRAND + "_" + Build.MODEL);
        map.put("netType", getNetType());
        map.put("ispName", iSPName);
        map.put("ispCode", String.valueOf(NetStatusUtil.getISPCode(context)));
        map.put("host", String.format(Locale.US, "%s_%d", str2, Integer.valueOf(i4)));
        map.put("channel", str);
        HashMap map2 = new HashMap();
        map2.put("value", Double.valueOf(j));
        map2.put("success", Double.valueOf(i2));
        map2.put(NSStatReporter.NS_RETCODE, Double.valueOf(i3));
        hySignalReportListener.report("hysignal", str3, map, map2, new HashMap());
    }

    private String getNetType() {
        switch (PlatformComm.C2Java.getStatisticsNetType()) {
            case 0:
                return "NOT_WIFI";
            case 1:
                return "WIFI";
            case 2:
                return "WAP";
            case 3:
                return "2G";
            case 4:
                return "3G";
            case 5:
                return "4G";
            case 6:
                return "UNKNOWN";
            case 7:
                return "NON";
            default:
                return "GET_ERR";
        }
    }

    private double parseDouble(String str, double d) {
        return str == null ? d : Double.parseDouble(str);
    }

    private long parseLong(String str, long j) {
        return str == null ? j : Long.parseLong(str);
    }
}
