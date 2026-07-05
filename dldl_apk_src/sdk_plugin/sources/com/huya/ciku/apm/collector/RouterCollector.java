package com.huya.ciku.apm.collector;

import android.text.TextUtils;
import com.duowan.monitor.jce.EUnit;
import com.duowan.monitor.utility.MonitorLog;
import com.huya.ciku.apm.MonitorCenter;
import com.huya.ciku.apm.util.HuyaNetworkUtils;
import com.huya.ciku.apm.util.RouteTracer;
import com.taptap.sdk.common.oaid.helper.OAIDHelper;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class RouterCollector extends CycleCollector implements RouteTracer.RouteTraceCallback {
    private static final int INVALID_TIME = -1;
    private static final String METRIC_BASE_STATION_PING_TIME = "nowifibasestationtime";
    private static final String METRIC_DESTINATION_IP_PING_TIME = "dstippingtime";
    private static final String METRIC_DESTINATION_MOBILE_PING_TIME = "nowifidstiptime";
    private static final String METRIC_OUT_IP_PING_TIME = "outippingtime";
    public static final String METRIC_ROUTER_DEVICE_COUNT = "routerdevicecount";
    private static final String METRIC_ROUTER_IP_PING_TIME = "routerippingtime";
    private static final String TAG = RouterCollector.class.getSimpleName();
    private String mUpstreamAddress;

    @Override // com.huya.ciku.apm.collector.CycleCollector, com.duowan.monitor.core.OnStatusChangeListener
    public void onStart() {
    }

    public RouterCollector() {
        super(OAIDHelper.TIMEOUT);
    }

    public void start(String str) {
        String domainFromUrl = getDomainFromUrl(str);
        this.mUpstreamAddress = domainFromUrl;
        if (TextUtils.isEmpty(domainFromUrl)) {
            return;
        }
        if (this.mStopped) {
            this.mStopped = false;
        }
        update();
    }

    @Override // com.huya.ciku.apm.collector.CycleCollector
    public void doCollect() {
        readArp();
        RouteTracer.getInstance().startTrace(this.mUpstreamAddress, 0, this);
    }

    @Override // com.huya.ciku.apm.util.RouteTracer.RouteTraceCallback
    public void onComplete(List<RouteTracer.RouteTrace> list) {
        String netWorkType = HuyaNetworkUtils.getNetWorkType(MonitorCenter.getInstance().getContext());
        if ("wifi".equals(netWorkType)) {
            reportPintTimeForWifi(list);
        } else if ("3G".equals(netWorkType) || "4G".equals(netWorkType)) {
            reportPintTimeForMobile(list);
        }
        if (list != null) {
            list.clear();
        }
    }

    private void reportPintTimeForWifi(List<RouteTracer.RouteTrace> list) {
        if (list != null && !list.isEmpty()) {
            int i = 0;
            RouteTracer.RouteTrace routeTrace = list.get(0);
            reportPingTime(routeTrace.getElapsedTime(), METRIC_ROUTER_IP_PING_TIME);
            String firstDomain = getFirstDomain(routeTrace.getIp());
            while (true) {
                if (i >= list.size()) {
                    i = -1;
                    break;
                } else if (!firstDomain.equalsIgnoreCase(getFirstDomain(list.get(i).getIp()))) {
                    break;
                } else {
                    i++;
                }
            }
            if (i > -1) {
                reportPingTime(list.get(i).getElapsedTime(), METRIC_OUT_IP_PING_TIME);
            } else {
                reportPingTime(0.0d, METRIC_OUT_IP_PING_TIME);
            }
            if (list.size() > 2) {
                reportPingTime(list.get(list.size() - 1).getElapsedTime(), METRIC_DESTINATION_IP_PING_TIME);
                return;
            } else {
                reportPingTime(0.0d, METRIC_DESTINATION_IP_PING_TIME);
                return;
            }
        }
        reportPingTime(-1.0d, METRIC_ROUTER_IP_PING_TIME);
        reportPingTime(-1.0d, METRIC_OUT_IP_PING_TIME);
        reportPingTime(-1.0d, METRIC_DESTINATION_IP_PING_TIME);
    }

    private void reportPintTimeForMobile(List<RouteTracer.RouteTrace> list) {
        if (list != null && !list.isEmpty()) {
            RouteTracer.RouteTrace routeTrace = list.get(0);
            if (list.size() == 1) {
                reportPingTime(routeTrace.getElapsedTime(), METRIC_DESTINATION_MOBILE_PING_TIME);
                reportPingTime(0.0d, METRIC_BASE_STATION_PING_TIME);
                return;
            } else {
                reportPingTime(routeTrace.getElapsedTime(), METRIC_BASE_STATION_PING_TIME);
                reportPingTime(list.get(list.size() - 1).getElapsedTime(), METRIC_DESTINATION_MOBILE_PING_TIME);
                return;
            }
        }
        reportPingTime(-1.0d, METRIC_BASE_STATION_PING_TIME);
        reportPingTime(-1.0d, METRIC_DESTINATION_MOBILE_PING_TIME);
    }

    private String getFirstDomain(String str) {
        String[] strArrSplit;
        return (TextUtils.isEmpty(str) || (strArrSplit = str.split("\\.")) == null || strArrSplit.length <= 0) ? "" : strArrSplit[0];
    }

    private void readArp() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("/proc/net/arp"));
            int i = 0;
            while (true) {
                String line = bufferedReader.readLine();
                if (line != null) {
                    try {
                        String strTrim = line.trim();
                        if (strTrim.length() >= 63 && !strTrim.toUpperCase(Locale.US).contains("IP")) {
                            strTrim.substring(0, 17).trim();
                            strTrim.substring(29, 32).trim();
                            if (!strTrim.substring(41, 63).trim().contains("00:00:00:00:00:00")) {
                                i++;
                            }
                        }
                    } catch (Exception unused) {
                    }
                } else {
                    reportRouterDeviceCount(i);
                    bufferedReader.close();
                    return;
                }
            }
        } catch (Exception e) {
            MonitorLog.e(TAG, e.getMessage());
        }
    }

    private void reportRouterDeviceCount(int i) {
        MonitorCenter.getInstance().request(METRIC_ROUTER_DEVICE_COUNT, i, EUnit.EUnit_Count);
    }

    private void reportPingTime(double d, String str) {
        MonitorCenter.getInstance().request(str, Math.round(d * 10.0d) / 50.0d, EUnit.EUnit_Milliseconds);
    }

    private String getDomainFromUrl(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        Matcher matcher = Pattern.compile("(?<=://)([\\w-]+\\.)+[\\w-]+(?<=/?)").matcher(str);
        if (matcher.find()) {
            return matcher.group();
        }
        return null;
    }
}
