package com.huya.mtp.hyns.report;

import android.text.TextUtils;
import com.huya.mtp.api.MTPApi;
import com.huya.mtp.hyns.Constants;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class NSPushReporter {
    public static final String NS_PUSH_ALL_CONSUME_TIME_KEY = "all_consume_time";
    public static final String NS_PUSH_APPID_KEY = "appid";
    public static final String NS_PUSH_CLIENT_RECV_TIME_KEY = "client_recv_time";
    public static final String NS_PUSH_CMDID_KEY = "cmdid";
    public static final String NS_PUSH_COUNT = "count";
    public static final String NS_PUSH_DEVICE_KEY = "device";
    public static final String NS_PUSH_HOST_KEY = "host";
    public static final String NS_PUSH_LENGTH_KEY = "length";
    public static final String NS_PUSH_MESGID_KEY = "mesgid";
    public static final String NS_PUSH_MSGID_KEY = "msgid";
    public static final String NS_PUSH_NEED_ACK = "needack";
    public static final String NS_PUSH_NETTYPE_KEY = "nettype";
    public static final String NS_PUSH_NET_RECV_CONSUME_TIME_KEY = "net_recv_consume_time";
    public static final String NS_PUSH_NS_VERSION_KEY = "ns_version";
    public static final String NS_PUSH_OSVER_KEY = "osver";
    public static final String NS_PUSH_PLATFORM_KEY = "platform";
    public static final String NS_PUSH_REPORT_METRIC_NAME = "hysignal.push_state_report";
    public static final int NS_PUSH_REPORT_PERS = 10000;
    public static final String NS_PUSH_SDKVERSION_KEY = "sdkversion";
    public static final String NS_PUSH_SERVER_SEND_EPOCH_TIME_KEY = "server_send_epoch_time";
    public static final String NS_PUSH_SYS_CONSUME_TIME_KEY = "sys_consume_time";
    public static final String NS_PUSH_URI_KEY = "uri";
    public static final String TAG = "NSPushReporter";
    private static AtomicLong reportId = new AtomicLong(0);
    private int mPercentage = 0;

    public static NSPushReporter getInstance() {
        return Holder.instance;
    }

    private static class Holder {
        private static NSPushReporter instance = new NSPushReporter();

        private Holder() {
        }
    }

    public int getPercentage() {
        return this.mPercentage;
    }

    private void setPercentage(int i) {
        this.mPercentage = i;
    }

    public void updateReportMsgIdRatio(Map<String, String> map) {
        if (map != null && map.containsKey(Constants.PUSH_REPORT_ENABLE)) {
            String str = map.get(Constants.PUSH_REPORT_ENABLE);
            MTPApi.LOGGER.info(TAG, "will updateReportMsgIdRatio: %s", str);
            if (!TextUtils.isEmpty(str) && !"0".equals(str)) {
                try {
                    getInstance().setPercentage(Integer.parseInt(str));
                    return;
                } catch (Throwable th) {
                    getInstance().setPercentage(0);
                    MTPApi.LOGGER.error(TAG, th);
                    return;
                }
            }
            getInstance().setPercentage(0);
        }
    }

    public boolean isNeedReport(long j) {
        return ((long) getInstance().getPercentage()) > j % 10000;
    }
}
