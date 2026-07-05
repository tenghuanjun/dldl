package com.huya.mtp.nsdt.adr;

import android.os.Handler;
import android.os.HandlerThread;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import com.huya.data.MonitorReqData;
import com.huya.mtp.api.MTPApi;
import com.huya.mtp.nsdt.NSDT;
import com.huya.mtp.nsdt.PingConfig;
import com.huya.mtp.nsdt.TcpConfig;
import com.huya.mtp.nsdt.TraceConfig;
import com.snail.antifake.deviceid.ShellAdbUtils;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class NSDTWrapper {
    private static final int ERR_TYPE_TCP_FILDS_ERROR = 99;
    private static final String HYMTP_DETECT_METRIC = "hymtp.network.detect.client";
    private static final String NSDT_BAD_RTT_COUNT = "ns.nsdt_bad_rtt_count";
    private static final String NSDT_FAIL_COUNT_THRESHOLD = "ns.nsdt_fail_count_threshold";
    private static final String NSDT_FORCE_CLOSE = "ns.nsdt_force_close";
    private static final String NSDT_GOOD_RTT_COUNT = "ns.nsdt_good_rtt_count";
    private static final String NSDT_MAX_RTT_THRESHOLD = "ns.nsdt_max_rtt_threshold";
    private static final String NSDT_MIN_RTT_THRESHOLD = "ns.nsdt_min_rtt_threshold";
    private static final String NSDT_REPORT = "ns.nsdt_is_report";
    private static final String NSDT_TIME_INTERVAL = "ns.nsdt_time_interval";
    public static final String TAG = "NSDT";
    private static final String THREAD_NAME_PREFIX = "NSDKThread";
    private int mBadRttCountThreshold;
    private int mFailCountThreshold;
    private int mForceClosed;
    private int mGoodRttCountThreshold;
    private int mIsReport;
    private int mMaxRttThreshold;
    private int mMinRttThreshold;
    private int mTimeInterval;
    private HandlerThreadWrapper sDetectThread;
    private HandlerThreadWrapper sDispatchThread;
    private HandlerThreadWrapper sTraceThread;
    private static AtomicInteger ai = new AtomicInteger(0);
    private static Map<Integer, OnTraceCallback> sTraceCallbackMap = new ConcurrentHashMap();
    private static final Map<String, List<OnPingCallback>> sPingCallbackMap = new ConcurrentHashMap();
    private static final Map<String, Long> sPingDnsCostMap = new ConcurrentHashMap();
    private static final Map<Integer, Long> sTcpDnsCostMap = new ConcurrentHashMap();
    private static final Map<Integer, List<OnTcpCallback>> sTcpCallbackMap = new ConcurrentHashMap();

    public interface OnPingCallback {
        void onPingResult(int i, int i2, String str, String str2, long j, long j2, double d);
    }

    public interface OnTcpCallback {
        void onTcpResponse(int i, List<NSDT.TCPResult> list);
    }

    public interface OnTraceCallback {
        void onTraceError(String[] strArr, String str);

        void onTraceResponse(String[] strArr);
    }

    private NSDTWrapper() {
        this.mForceClosed = 0;
        this.sTraceThread = new HandlerThreadWrapper("TraceStart");
        this.sDispatchThread = new HandlerThreadWrapper("TraceCallBack");
        this.sDetectThread = new HandlerThreadWrapper("DetectThread");
        NSDT.init();
        NSDT.setCallBack(new NSDT.NSDTCallBack() { // from class: com.huya.mtp.nsdt.adr.NSDTWrapper.1
            @Override // com.huya.mtp.nsdt.NSDT.NSDTCallBack
            public void traceReturnFromNative(int i, boolean z, String[] strArr, String str) {
                NSDTWrapper.this.traceReturn(i, z, strArr, str);
            }

            @Override // com.huya.mtp.nsdt.NSDT.NSDTCallBack
            public void pingReturnFromNative(int i, String str, String str2, int i2, long j, double d) {
                NSDTWrapper.this.pingReturn(i, str, str2, i2, j, d);
            }

            @Override // com.huya.mtp.nsdt.NSDT.NSDTCallBack
            public void tcpReturnFromNative(int i, List<NSDT.TCPResult> list) {
                NSDTWrapper.this.tcpReturn(i, list);
            }

            @Override // com.huya.mtp.nsdt.NSDT.NSDTCallBack
            public void planReturnFromNative(String str, int i, int i2, int i3) {
                planReturnFromNative(str, i, i2, i3);
            }

            @Override // com.huya.mtp.nsdt.NSDT.NSDTCallBack
            public void reportPlanResult(String str) {
                if (TextUtils.isEmpty(str)) {
                    return;
                }
                HashMap map = new HashMap();
                for (String str2 : str.split(ShellAdbUtils.COMMAND_LINE_END)) {
                    String[] strArrSplit = str2.split(":");
                    if (strArrSplit.length == 2) {
                        map.put(strArrSplit[0], strArrSplit[1]);
                    }
                }
                MonitorReqData monitorReqData = new MonitorReqData();
                monitorReqData.iTS = System.currentTimeMillis();
                monitorReqData.sMetricName = NSDTWrapper.HYMTP_DETECT_METRIC;
                monitorReqData.vDimension.add(new MonitorReqData.DimensionWrapper("result", (String) map.get("result")));
                monitorReqData.vDimension.add(new MonitorReqData.DimensionWrapper("net_state", (String) map.get("net_state")));
                monitorReqData.vDimension.add(new MonitorReqData.DimensionWrapper("ip", (String) map.get("ip")));
                monitorReqData.vDimension.add(new MonitorReqData.DimensionWrapper("status", (String) map.get("status")));
                monitorReqData.vDimension.add(new MonitorReqData.DimensionWrapper("max_rtt_threshold", (String) map.get("max_rtt_threshold")));
                monitorReqData.vDimension.add(new MonitorReqData.DimensionWrapper("min_rtt_threshold", (String) map.get("min_rtt_threshold")));
                monitorReqData.vDimension.add(new MonitorReqData.DimensionWrapper("fail_count_threshold", (String) map.get("fail_count_threshold")));
                monitorReqData.vDimension.add(new MonitorReqData.DimensionWrapper("time_inteval", (String) map.get("time_inteval")));
                monitorReqData.vDimension.add(new MonitorReqData.DimensionWrapper("good_rtt_count_threshold", (String) map.get("good_rtt_count_threshold")));
                monitorReqData.vDimension.add(new MonitorReqData.DimensionWrapper("bad_rtt_count_threshold", (String) map.get("bad_rtt_count_threshold")));
                monitorReqData.vDimension.add(new MonitorReqData.DimensionWrapper("net_type", (String) map.get("net_type")));
                monitorReqData.vDimension.add(new MonitorReqData.DimensionWrapper("inner_tcp_retry", (String) map.get("inner_tcp_retry")));
                monitorReqData.vDimension.add(new MonitorReqData.DimensionWrapper("platform", "adr"));
                try {
                    monitorReqData.vField.add(new MonitorReqData.FieldWrapper("cur_good_rtt_count", ConfigParseUtils.parseDouble((String) map.get("cur_good_rtt_count"))));
                    monitorReqData.vField.add(new MonitorReqData.FieldWrapper("cur_bad_rtt_count", ConfigParseUtils.parseDouble((String) map.get("cur_bad_rtt_count"))));
                    monitorReqData.vField.add(new MonitorReqData.FieldWrapper("cur_fail_count", ConfigParseUtils.parseDouble((String) map.get("cur_fail_count"))));
                    monitorReqData.vField.add(new MonitorReqData.FieldWrapper("tcp_inner_rtt", ConfigParseUtils.parseDouble((String) map.get("tcp_inner_rtt"))));
                    monitorReqData.vField.add(new MonitorReqData.FieldWrapper("ping_inner_rtt", ConfigParseUtils.parseDouble((String) map.get("ping_inner_rtt"))));
                    monitorReqData.vField.add(new MonitorReqData.FieldWrapper("ping_tencent_rtt", ConfigParseUtils.parseDouble((String) map.get("ping_tencent_rtt"))));
                    monitorReqData.vField.add(new MonitorReqData.FieldWrapper("ping_baidu_rtt", ConfigParseUtils.parseDouble((String) map.get("ping_baidu_rtt"))));
                } catch (Exception e) {
                    MTPApi.LOGGER.error(NSDTWrapper.TAG, e);
                }
                if (NSDTWrapper.this.mIsReport != 0) {
                    MTPApi.MONITOR.request(monitorReqData);
                }
            }
        });
    }

    public static NSDTWrapper getInstance() {
        return Holder.INSTANCE;
    }

    private static class Holder {
        private static final NSDTWrapper INSTANCE = new NSDTWrapper();

        private Holder() {
        }
    }

    public void setDyConfig(Map<String, String> map) {
        try {
            this.mForceClosed = ConfigParseUtils.parseInt(map.get(NSDT_FORCE_CLOSE));
            this.mIsReport = ConfigParseUtils.parseInt(map.get(NSDT_REPORT));
            this.mMaxRttThreshold = ConfigParseUtils.parseInt(map.get(NSDT_MAX_RTT_THRESHOLD));
            this.mMinRttThreshold = ConfigParseUtils.parseInt(map.get(NSDT_MIN_RTT_THRESHOLD));
            this.mFailCountThreshold = ConfigParseUtils.parseInt(map.get(NSDT_FAIL_COUNT_THRESHOLD));
            this.mGoodRttCountThreshold = ConfigParseUtils.parseInt(map.get(NSDT_GOOD_RTT_COUNT));
            this.mBadRttCountThreshold = ConfigParseUtils.parseInt(map.get(NSDT_BAD_RTT_COUNT));
            this.mTimeInterval = ConfigParseUtils.parseInt(map.get(NSDT_TIME_INTERVAL));
        } catch (Exception e) {
            MTPApi.LOGGER.error(TAG, e);
        }
    }

    public int ping(final PingConfig pingConfig, final OnPingCallback onPingCallback) {
        if (!checkPingConfig(pingConfig)) {
            onPingErrResult(pingConfig, onPingCallback, -990, 0L);
            return -990;
        }
        if (onPingCallback == null) {
            MTPApi.LOGGER.info(TAG, "ping OnPingCallback is null ");
            return -991;
        }
        final String host = pingConfig.getHost();
        if (host == null || host.isEmpty()) {
            onPingErrResult(pingConfig, onPingCallback, -992, 0L);
            return -992;
        }
        MTPApi.LOGGER.info(TAG, "ping: %s", pingConfig.toString());
        synchronized (sPingCallbackMap) {
            List<OnPingCallback> list = sPingCallbackMap.get(host);
            if (list == null) {
                ArrayList arrayList = new ArrayList();
                arrayList.add(onPingCallback);
                sPingCallbackMap.put(host, arrayList);
                this.sDetectThread.getHandler().post(new Runnable() { // from class: com.huya.mtp.nsdt.adr.NSDTWrapper.2
                    @Override // java.lang.Runnable
                    public void run() {
                        String str;
                        String host2 = pingConfig.getHost();
                        if (!NSDTWrapper.this.isIpAddress(host)) {
                            try {
                                long jCurrentTimeMillis = System.currentTimeMillis();
                                InetAddress[] allByName = InetAddress.getAllByName(host);
                                long jCurrentTimeMillis2 = System.currentTimeMillis() - jCurrentTimeMillis;
                                if (allByName != null && allByName.length != 0) {
                                    NSDTWrapper.sPingDnsCostMap.put(host, Long.valueOf(jCurrentTimeMillis2));
                                    String hostAddress = allByName[0].getHostAddress();
                                    MTPApi.LOGGER.debug(NSDTWrapper.TAG, "ping host:" + host + " dns result: %s", Arrays.toString(allByName));
                                    str = hostAddress;
                                }
                                NSDTWrapper.this.onPingErrResult(pingConfig, onPingCallback, -993, jCurrentTimeMillis2);
                                return;
                            } catch (Exception unused) {
                                NSDTWrapper.this.onPingErrResult(pingConfig, onPingCallback, -994, 0L);
                                return;
                            }
                        }
                        str = host2;
                        NSDT.ping(host.hashCode(), host, str, pingConfig.getCount(), pingConfig.getInterval(), pingConfig.getTimeout());
                    }
                });
                return 0;
            }
            list.add(onPingCallback);
            MTPApi.LOGGER.info(TAG, "ping host:" + host + " merge request");
            return 1;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onPingErrResult(PingConfig pingConfig, OnPingCallback onPingCallback, int i, long j) {
        if (onPingCallback == null) {
            return;
        }
        onPingCallback.onPingResult(0, i, pingConfig.getHost(), pingConfig.getHost(), 0L, j, 0.0d);
    }

    private boolean checkPingConfig(PingConfig pingConfig) {
        if (pingConfig != null) {
            return pingConfig.getHost() != null && !pingConfig.getHost().isEmpty() && pingConfig.getCount() >= 0 && pingConfig.getInterval() >= 0 && pingConfig.getTimeout() >= 0;
        }
        MTPApi.LOGGER.error(TAG, "checkPingConfig is null");
        return false;
    }

    public void tracerouteCmd(final TraceConfig traceConfig, OnTraceCallback onTraceCallback) {
        final int iIncrementAndGet = ai.incrementAndGet();
        MTPApi.LOGGER.info(TAG, "taskId:%d, trace:%s", Integer.valueOf(iIncrementAndGet), traceConfig.toString());
        if (onTraceCallback != null) {
            sTraceCallbackMap.put(Integer.valueOf(iIncrementAndGet), onTraceCallback);
        }
        getInstance().sTraceThread.getHandler().post(new Runnable() { // from class: com.huya.mtp.nsdt.adr.NSDTWrapper.3
            @Override // java.lang.Runnable
            public void run() {
                MTPApi.LOGGER.debug(NSDTWrapper.class.getName(), "run: traceroute start");
                NSDT.traceroute(iIncrementAndGet, traceConfig.toCmds());
                MTPApi.LOGGER.debug(NSDTWrapper.class.getName(), "run: traceroute end");
            }
        });
    }

    public int tcpCmd(final TcpConfig tcpConfig, final OnTcpCallback onTcpCallback) {
        if (!checkTcpConfig(tcpConfig)) {
            MTPApi.LOGGER.error(TAG, "checkTcpConfig failed");
            onTcpErrResult(tcpConfig, onTcpCallback, -990, 0L);
            return -990;
        }
        if (onTcpCallback == null) {
            MTPApi.LOGGER.error(TAG, "tcpCmd onTcpCallback is null");
            onTcpErrResult(tcpConfig, onTcpCallback, -991, 0L);
            return -991;
        }
        final String host = tcpConfig.getHost();
        MTPApi.LOGGER.debug(TAG, "TcpCmd: %s", tcpConfig.toString());
        synchronized (sTcpCallbackMap) {
            List<OnTcpCallback> list = sTcpCallbackMap.get(Integer.valueOf(host.hashCode()));
            if (list == null) {
                ArrayList arrayList = new ArrayList();
                arrayList.add(onTcpCallback);
                sTcpCallbackMap.put(Integer.valueOf(host.hashCode()), arrayList);
                this.sDetectThread.getHandler().post(new Runnable() { // from class: com.huya.mtp.nsdt.adr.NSDTWrapper.4
                    @Override // java.lang.Runnable
                    public void run() {
                        String str;
                        String host2 = tcpConfig.getHost();
                        if (!NSDTWrapper.this.isIpAddress(host)) {
                            try {
                                long jCurrentTimeMillis = System.currentTimeMillis();
                                InetAddress[] allByName = InetAddress.getAllByName(host);
                                long jCurrentTimeMillis2 = System.currentTimeMillis() - jCurrentTimeMillis;
                                if (allByName != null && allByName.length != 0) {
                                    NSDTWrapper.sTcpDnsCostMap.put(Integer.valueOf(host.hashCode()), Long.valueOf(jCurrentTimeMillis2));
                                    String hostAddress = allByName[0].getHostAddress();
                                    MTPApi.LOGGER.debug(NSDTWrapper.TAG, "ping host:" + host + " dns result: %s", Arrays.toString(allByName));
                                    str = hostAddress;
                                }
                                NSDTWrapper.this.onTcpErrResult(tcpConfig, onTcpCallback, -993, jCurrentTimeMillis2);
                                return;
                            } catch (Exception unused) {
                                NSDTWrapper.this.onTcpErrResult(tcpConfig, onTcpCallback, -994, 0L);
                                return;
                            }
                        }
                        str = host2;
                        NSDT.tcp(host.hashCode(), tcpConfig.getHost(), str, tcpConfig.getPort(), tcpConfig.getTimeout(), tcpConfig.getBody());
                    }
                });
                return 0;
            }
            list.add(onTcpCallback);
            MTPApi.LOGGER.info(TAG, "tcpCmd host: %s merge request", host);
            return 1;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onTcpErrResult(TcpConfig tcpConfig, OnTcpCallback onTcpCallback, int i, long j) {
        if (onTcpCallback == null) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        NSDT.TCPResult tCPResult = new NSDT.TCPResult(tcpConfig.getHost(), tcpConfig.getHost(), 0, 0L, 0L, 0L, 0L, 0L, 0L, 99, i);
        tCPResult.setDnsCost(j);
        arrayList.add(tCPResult);
        onTcpCallback.onTcpResponse(0, arrayList);
    }

    private boolean checkTcpConfig(TcpConfig tcpConfig) {
        if (tcpConfig == null) {
            MTPApi.LOGGER.error(TAG, "checkTcpConfig is null");
            return false;
        }
        if (tcpConfig.getHost() == null || tcpConfig.getHost().isEmpty()) {
            MTPApi.LOGGER.error(TAG, "checkTcpConfig ips is empty");
            return false;
        }
        if (tcpConfig.getPort() <= 0 || tcpConfig.getTimeout() < 0) {
            MTPApi.LOGGER.error(TAG, "checkTcpConfig port<=0 or timeout < 0");
            return false;
        }
        if (tcpConfig.getBody() != null && !tcpConfig.getBody().isEmpty()) {
            return true;
        }
        MTPApi.LOGGER.error(TAG, "checkTcpConfig  body is null");
        return false;
    }

    public void detectIP(NSDTPlanTask nSDTPlanTask) {
        if (this.mForceClosed != 0 || nSDTPlanTask == null) {
            return;
        }
        MTPApi.LOGGER.info(TAG, nSDTPlanTask.toString());
        NSDT.detectIP(nSDTPlanTask.ips, nSDTPlanTask.ports, nSDTPlanTask.timeSpanSeconds, this.mTimeInterval, nSDTPlanTask.task_type, nSDTPlanTask.tcp_timeout, this.mMaxRttThreshold, this.mMinRttThreshold, this.mGoodRttCountThreshold, this.mBadRttCountThreshold, this.mFailCountThreshold, nSDTPlanTask.request_body);
    }

    public int getIPStatus(String str) {
        return NSDT.getIPStatus(str);
    }

    public int getIPRtt(String str) {
        return NSDT.getIPRtt(str);
    }

    void traceReturn(int i, final boolean z, final String[] strArr, final String str) {
        final OnTraceCallback onTraceCallbackRemove = sTraceCallbackMap.remove(Integer.valueOf(i));
        if (onTraceCallbackRemove != null) {
            this.sDispatchThread.getHandler().post(new Runnable() { // from class: com.huya.mtp.nsdt.adr.NSDTWrapper.5
                @Override // java.lang.Runnable
                public void run() {
                    if (z) {
                        onTraceCallbackRemove.onTraceResponse(strArr);
                    } else {
                        onTraceCallbackRemove.onTraceError(strArr, str);
                    }
                }
            });
        }
    }

    void pingReturn(final int i, final String str, final String str2, final int i2, final long j, final double d) {
        MTPApi.LOGGER.info(TAG, "pingReturn taskId:%d, host:%s, ip:%s, ret:%d, avgRtt:%d, lossRate:%s", Integer.valueOf(i), str, str2, Integer.valueOf(i2), Long.valueOf(j), Double.valueOf(d));
        if (str == null || str.isEmpty()) {
            MTPApi.LOGGER.info(TAG, "pingReturn failed, host is empty");
            return;
        }
        List<OnPingCallback> listRemove = sPingCallbackMap.remove(str);
        if (listRemove == null) {
            MTPApi.LOGGER.info(TAG, "pingReturn failed, not found callbacks");
            return;
        }
        Log.i(TAG, "ping task callback count: " + listRemove.size());
        for (final OnPingCallback onPingCallback : listRemove) {
            if (onPingCallback != null) {
                this.sDispatchThread.getHandler().post(new Runnable() { // from class: com.huya.mtp.nsdt.adr.NSDTWrapper.6
                    @Override // java.lang.Runnable
                    public void run() {
                        Long l = (Long) NSDTWrapper.sPingDnsCostMap.remove(str);
                        onPingCallback.onPingResult(i, i2, str, str2, j, l != null ? l.longValue() : 0L, d);
                    }
                });
            }
        }
    }

    void tcpReturn(final int i, final List<NSDT.TCPResult> list) {
        MTPApi.LOGGER.info(TAG, "tcpReturn taskId:%d, msg:%s", Integer.valueOf(i), list.toString());
        final List<OnTcpCallback> listRemove = sTcpCallbackMap.remove(Integer.valueOf(i));
        if (listRemove == null || listRemove.isEmpty()) {
            MTPApi.LOGGER.error(TAG, "tcpReturn callbacks is empty");
        } else {
            this.sDispatchThread.getHandler().post(new Runnable() { // from class: com.huya.mtp.nsdt.adr.NSDTWrapper.7
                @Override // java.lang.Runnable
                public void run() {
                    Long l = (Long) NSDTWrapper.sTcpDnsCostMap.remove(Integer.valueOf(i));
                    long jLongValue = l != null ? l.longValue() : 0L;
                    for (OnTcpCallback onTcpCallback : listRemove) {
                        if (onTcpCallback != null) {
                            Iterator it = list.iterator();
                            while (it.hasNext()) {
                                ((NSDT.TCPResult) it.next()).setDnsCost(jLongValue);
                            }
                            onTcpCallback.onTcpResponse(i, list);
                        }
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isIpAddress(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        return Patterns.IP_ADDRESS.matcher(str).matches() || str.contains("::");
    }

    private static class HandlerThreadWrapper {
        private Handler mHandler;
        private HandlerThread mThread;

        HandlerThreadWrapper(String str) {
            this(str, null);
        }

        HandlerThreadWrapper(String str, Handler.Callback callback) {
            HandlerThread handlerThread = new HandlerThread(NSDTWrapper.THREAD_NAME_PREFIX + str);
            handlerThread.setPriority(10);
            this.mThread = handlerThread;
            handlerThread.start();
            this.mHandler = new Handler(handlerThread.getLooper(), callback);
        }

        Handler getHandler() {
            return this.mHandler;
        }

        Thread getThread() {
            return this.mThread;
        }
    }
}
