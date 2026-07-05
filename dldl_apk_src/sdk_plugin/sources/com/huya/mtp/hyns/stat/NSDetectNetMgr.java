package com.huya.mtp.hyns.stat;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.SystemClock;
import com.huya.data.MonitorReqData;
import com.huya.hal.Hal;
import com.huya.hysignal.util.HySignalLog;
import com.huya.mtp.api.MTPApi;
import com.huya.mtp.hycloudgame.base.utils.NetworkUtil;
import com.huya.mtp.hyns.Constants;
import com.huya.mtp.nsdt.NSDT;
import com.huya.mtp.nsdt.PingConfig;
import com.huya.mtp.nsdt.TcpConfig;
import com.huya.mtp.nsdt.adr.NSDTWrapper;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import master.flame.danmaku.danmaku.parser.IDataSource;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class NSDetectNetMgr {
    private static final String DETECT_CHINA_OUT_HOST = "www.baidu.com";
    private static final String DETECT_OVERSEA_OUT_HOST = "www.google.com";
    private static final String DETECT_PING_HUYA_DNS = "ping_hy_dns";
    private static final String DETECT_PING_HUYA_HOST = "ping_hy_host";
    private static final String DETECT_PING_HUYA_IP = "ping_hy_ip";
    private static final String DETECT_PING_HUYA_LOSS_RATE = "ping_hy_loss_rate";
    private static final String DETECT_PING_HUYA_RTT = "ping_hy_rtt";
    private static final String DETECT_PING_HUYA_SUCCESS = "ping_hy_suc";
    private static final String DETECT_PING_PUB_DNS = "ping_pub_dns";
    private static final String DETECT_PING_PUB_HOST = "ping_pub_host";
    private static final String DETECT_PING_PUB_IP = "ping_pub_ip";
    private static final String DETECT_PING_PUB_LOSS_RATE = "ping_pub_loss_rate";
    private static final String DETECT_PING_PUB_RTT = "ping_pub_rtt";
    private static final String DETECT_PING_PUB_SUCCESS = "ping_pub_suc";
    private static final String DETECT_TCP_HUYA_ALL_COST = "tcp_hy_all";
    private static final String DETECT_TCP_HUYA_CONN_COST = "tcp_hy_conn";
    private static final String DETECT_TCP_HUYA_DNS_COST = "tcp_hy_dns";
    private static final String DETECT_TCP_HUYA_HOST = "tcp_hy_host";
    private static final String DETECT_TCP_HUYA_IP = "tcp_hy_ip";
    private static final String DETECT_TCP_HUYA_PORT = "tcp_hy_port";
    private static final String DETECT_TCP_HUYA_RECV_COST = "tcp_hy_recv";
    private static final String DETECT_TCP_HUYA_RETCODE = "tcp_hy_ret";
    private static final String DETECT_TCP_HUYA_RTT = "tcp_hy_rtt";
    private static final String DETECT_TCP_HUYA_SEND_COST = "tcp_hy_send";
    private static final String DETECT_TCP_HUYA_SUCCESS_CODE = "tcp_hy_suc";
    private static final String DETECT_TCP_PUB_ALL_COST = "tcp_pub_all";
    private static final String DETECT_TCP_PUB_CONN_COST = "tcp_pub_conn";
    private static final String DETECT_TCP_PUB_DNS_COST = "tcp_pub_dns";
    private static final String DETECT_TCP_PUB_HOST = "tcp_pub_host";
    private static final String DETECT_TCP_PUB_IP = "tcp_pub_ip";
    private static final String DETECT_TCP_PUB_PORT = "tcp_pub_port";
    private static final String DETECT_TCP_PUB_RECV_COST = "tcp_pub_recv";
    private static final String DETECT_TCP_PUB_RETCODE = "tcp_pub_ret";
    private static final String DETECT_TCP_PUB_RTT = "tcp_pub_rtt";
    private static final String DETECT_TCP_PUB_SEND_COST = "tcp_pub_send";
    private static final String DETECT_TCP_PUB_SUCCESS_CODE = "tcp_pub_suc";
    private static final String DETECT_USER_NET_FINE = "net_fine";
    public static final String TAG = "NetService-NSDetectNetMgr";
    private static NSDetectNetMgr mInstance;
    private HandlerThreadWrapper sDispatchthread = new HandlerThreadWrapper("detectDispatch");
    private final Map<String, List<MonitorReqData>> mNSRequestMonitorDataCache = new ConcurrentHashMap();
    private boolean isPingHuyaSuccess = false;
    private final int DETECT_FREQUENT_LIMIT_PER_MINUTE = 60;
    private final int DETECT_FREQUENCY_CHECK_DUR = 60000;
    private long lastAddDetectTime = 0;
    private long lastDetectCount = 0;

    static /* synthetic */ long access$108(NSDetectNetMgr nSDetectNetMgr) {
        long j = nSDetectNetMgr.lastDetectCount;
        nSDetectNetMgr.lastDetectCount = 1 + j;
        return j;
    }

    public static NSDetectNetMgr getInstance() {
        if (mInstance == null) {
            synchronized (NSDetectNetMgr.class) {
                if (mInstance == null) {
                    mInstance = new NSDetectNetMgr();
                }
            }
        }
        return mInstance;
    }

    public void addNewFailedRequestData(final MonitorReqData monitorReqData) {
        if (monitorReqData == null) {
            return;
        }
        try {
            if (!NetworkUtil.isNetworkAvailable(MTPApi.CONTEXT.getApplication())) {
                HySignalLog.error(TAG, "net not available");
                return;
            }
        } catch (Exception e) {
            HySignalLog.error(TAG, "check net available failed, e: %s", e.toString());
        }
        dispatch(new Runnable() { // from class: com.huya.mtp.hyns.stat.NSDetectNetMgr.1
            @Override // java.lang.Runnable
            public void run() {
                String str;
                ArrayList<MonitorReqData.DimensionWrapper> arrayList = monitorReqData.vDimension;
                if (arrayList == null || arrayList.isEmpty()) {
                    return;
                }
                String str2 = null;
                String str3 = null;
                String str4 = null;
                for (MonitorReqData.DimensionWrapper dimensionWrapper : arrayList) {
                    if ("success".equals(dimensionWrapper.sName)) {
                        str2 = dimensionWrapper.sValue;
                    } else if (NSStatReporter.NS_RETCODE.equals(dimensionWrapper.sName)) {
                        String str5 = dimensionWrapper.sValue;
                    } else if ("marssuc".equals(dimensionWrapper.sName)) {
                        String str6 = dimensionWrapper.sValue;
                    } else if ("ipType".equals(dimensionWrapper.sName)) {
                        String str7 = dimensionWrapper.sValue;
                    } else if ("ip".equals(dimensionWrapper.sName)) {
                        str4 = dimensionWrapper.sValue;
                    } else if ("channel".equals(dimensionWrapper.sName)) {
                        str3 = dimensionWrapper.sValue;
                    }
                }
                if ("1".equals(str2)) {
                    long jElapsedRealtime = SystemClock.elapsedRealtime();
                    if (jElapsedRealtime - NSDetectNetMgr.this.lastAddDetectTime > 60000) {
                        NSDetectNetMgr.this.lastAddDetectTime = jElapsedRealtime;
                        NSDetectNetMgr.this.lastDetectCount = 1L;
                    } else {
                        if (NSDetectNetMgr.this.lastDetectCount > 60) {
                            HySignalLog.info(NSDetectNetMgr.TAG, "add detect %d/min, skip", Long.valueOf(NSDetectNetMgr.this.lastDetectCount));
                            return;
                        }
                        NSDetectNetMgr.access$108(NSDetectNetMgr.this);
                    }
                    if (Hal.isOverSeaEnv()) {
                        str = "wsapi.master.live";
                    } else {
                        str = IDataSource.SCHEME_HTTP_TAG.equals(str3) ? "cdn.wup.huya.com" : Constants.NATIONAL_LONG_LINK_HOST;
                    }
                    if (str4 == null || str4.isEmpty()) {
                        str4 = str;
                    }
                    synchronized (NSDetectNetMgr.this.mNSRequestMonitorDataCache) {
                        List list = (List) NSDetectNetMgr.this.mNSRequestMonitorDataCache.get(str4);
                        if (list == null) {
                            ArrayList arrayList2 = new ArrayList();
                            arrayList2.add(monitorReqData);
                            NSDetectNetMgr.this.mNSRequestMonitorDataCache.put(str4, arrayList2);
                            NSDetectNetMgr.this.startNetDetect(str, str4);
                        } else {
                            list.add(monitorReqData);
                        }
                    }
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startNetDetect(String str, final String str2) {
        final MonitorReqData monitorReqData = new MonitorReqData();
        monitorReqData.iTS = System.currentTimeMillis();
        NSDTWrapper.getInstance().tcpCmd(new TcpConfig.Builder().setHost(str2).setPort(80).setTimeout(8000).setBody("GET /monitor/monitor.jsp HTTP/1.1 \r\nHost: " + str + " \r\n\r\n").build(), new NSDTWrapper.OnTcpCallback() { // from class: com.huya.mtp.hyns.stat.NSDetectNetMgr.2
            @Override // com.huya.mtp.nsdt.adr.NSDTWrapper.OnTcpCallback
            public void onTcpResponse(int i, List<NSDT.TCPResult> list) {
                if (list == null || list.isEmpty()) {
                    MTPApi.LOGGER.error(NSDetectNetMgr.TAG, "tcp huya IP: %s failed, tcpResult is empty", str2);
                    return;
                }
                NSDT.TCPResult tCPResult = list.get(0);
                if (tCPResult.getErrCode() != 0) {
                    NSDetectNetMgr.this.onTcpHuyaFailed(tCPResult, str2, monitorReqData);
                } else {
                    NSDetectNetMgr.this.onTcpHuyaSuccess(tCPResult, str2, monitorReqData);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onTcpHuyaSuccess(NSDT.TCPResult tCPResult, String str, MonitorReqData monitorReqData) {
        monitorReqData.vDimension.add(new MonitorReqData.DimensionWrapper(DETECT_USER_NET_FINE, "0"));
        addTcpHuyaMonitorData(tCPResult, monitorReqData);
        reportDetectResult(str, monitorReqData);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onTcpHuyaFailed(NSDT.TCPResult tCPResult, final String str, final MonitorReqData monitorReqData) {
        MTPApi.LOGGER.info("TCP RES huya", "tcp dt result: IP: " + tCPResult.getIp() + " , " + tCPResult.toString());
        addTcpHuyaMonitorData(tCPResult, monitorReqData);
        NSDTWrapper.getInstance().ping(new PingConfig.Builder().setHost(tCPResult.getIp()).setCount(2).setInterval(1).setTimeout(10).build(), new NSDTWrapper.OnPingCallback() { // from class: com.huya.mtp.hyns.stat.NSDetectNetMgr.3
            @Override // com.huya.mtp.nsdt.adr.NSDTWrapper.OnPingCallback
            public void onPingResult(int i, int i2, String str2, String str3, long j, long j2, double d) {
                MTPApi.LOGGER.info("PING RES huya", "ping taskid: " + i + " ret:" + i2 + ",host:" + str2 + " ,ip:" + str3 + ",rtt:" + j + ",dnsCost:" + j2 + ",lossRate:" + d);
                NSDetectNetMgr.this.isPingHuyaSuccess = i2 == 0;
                monitorReqData.vDimension.add(new MonitorReqData.DimensionWrapper(NSDetectNetMgr.DETECT_PING_HUYA_HOST, str2));
                monitorReqData.vDimension.add(new MonitorReqData.DimensionWrapper(NSDetectNetMgr.DETECT_PING_HUYA_IP, str3));
                monitorReqData.vDimension.add(new MonitorReqData.DimensionWrapper(NSDetectNetMgr.DETECT_PING_HUYA_SUCCESS, Integer.toString(i2)));
                monitorReqData.vField.add(new MonitorReqData.FieldWrapper(NSDetectNetMgr.DETECT_PING_HUYA_RTT, j));
                monitorReqData.vField.add(new MonitorReqData.FieldWrapper(NSDetectNetMgr.DETECT_PING_HUYA_DNS, j2));
                monitorReqData.vField.add(new MonitorReqData.FieldWrapper(NSDetectNetMgr.DETECT_PING_HUYA_LOSS_RATE, d));
                String str4 = Hal.isOverSeaEnv() ? NSDetectNetMgr.DETECT_OVERSEA_OUT_HOST : "www.baidu.com";
                NSDTWrapper.getInstance().tcpCmd(new TcpConfig.Builder().setHost(str4).setPort(80).setTimeout(8000).setBody("GET / HTTP/1.1 \r\nHost: " + str4 + " \r\n\r\n").build(), new NSDTWrapper.OnTcpCallback() { // from class: com.huya.mtp.hyns.stat.NSDetectNetMgr.3.1
                    @Override // com.huya.mtp.nsdt.adr.NSDTWrapper.OnTcpCallback
                    public void onTcpResponse(int i3, List<NSDT.TCPResult> list) {
                        if (list == null || list.isEmpty()) {
                            MTPApi.LOGGER.error(NSDetectNetMgr.TAG, "tcp out failed, tcpResult is empty");
                            return;
                        }
                        NSDT.TCPResult tCPResult2 = list.get(0);
                        if (tCPResult2.getErrCode() != 0) {
                            NSDetectNetMgr.this.onTcpOutFailed(tCPResult2, str, monitorReqData);
                        } else {
                            NSDetectNetMgr.this.onTcpOutSuccess(tCPResult2, str, monitorReqData);
                        }
                    }
                });
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onTcpOutSuccess(NSDT.TCPResult tCPResult, String str, MonitorReqData monitorReqData) {
        monitorReqData.vDimension.add(new MonitorReqData.DimensionWrapper(DETECT_USER_NET_FINE, Integer.toString(this.isPingHuyaSuccess ? 1000 : 1100)));
        addTcpPubResultMonitorData(tCPResult, monitorReqData);
        reportDetectResult(str, monitorReqData);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onTcpOutFailed(NSDT.TCPResult tCPResult, final String str, final MonitorReqData monitorReqData) {
        MTPApi.LOGGER.info("TCP RES", "tcp dt result: IP: " + tCPResult.getIp() + " , " + tCPResult.toString());
        addTcpPubResultMonitorData(tCPResult, monitorReqData);
        NSDTWrapper.getInstance().ping(new PingConfig.Builder().setHost(Hal.isOverSeaEnv() ? DETECT_OVERSEA_OUT_HOST : "www.baidu.com").setCount(2).setInterval(1).setTimeout(10).build(), new NSDTWrapper.OnPingCallback() { // from class: com.huya.mtp.hyns.stat.NSDetectNetMgr.4
            @Override // com.huya.mtp.nsdt.adr.NSDTWrapper.OnPingCallback
            public void onPingResult(int i, int i2, String str2, String str3, long j, long j2, double d) {
                MTPApi.LOGGER.info("PING RES", "ping taskid: " + i + " ret:" + i2 + ",host:" + str2 + ",ip:" + str3 + ",rtt:" + j + ",dnsCost:" + j2 + ",lossRate:" + d);
                if (i2 != 0) {
                    NSDetectNetMgr.this.onPingPublicFailed(i2, str2, str3, j, j2, d, str, monitorReqData);
                } else {
                    NSDetectNetMgr.this.onPingPublicSuccess(i2, str2, str3, j, j2, d, str, monitorReqData);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onPingPublicSuccess(int i, String str, String str2, long j, long j2, double d, String str3, MonitorReqData monitorReqData) {
        monitorReqData.vDimension.add(new MonitorReqData.DimensionWrapper(DETECT_USER_NET_FINE, Integer.toString(this.isPingHuyaSuccess ? 1010 : 1110)));
        addPingPubMonitorData(i, str, str2, j, j2, d, str3, monitorReqData);
        reportDetectResult(str3, monitorReqData);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onPingPublicFailed(int i, String str, String str2, long j, long j2, double d, String str3, MonitorReqData monitorReqData) {
        monitorReqData.vDimension.add(new MonitorReqData.DimensionWrapper(DETECT_USER_NET_FINE, Integer.toString(this.isPingHuyaSuccess ? 1011 : 1111)));
        addPingPubMonitorData(i, str, str2, j, j2, d, str3, monitorReqData);
        reportDetectResult(str3, monitorReqData);
    }

    private void addPingPubMonitorData(int i, String str, String str2, long j, long j2, double d, String str3, MonitorReqData monitorReqData) {
        monitorReqData.vDimension.add(new MonitorReqData.DimensionWrapper(DETECT_PING_PUB_HOST, str));
        monitorReqData.vDimension.add(new MonitorReqData.DimensionWrapper(DETECT_PING_PUB_IP, str2));
        monitorReqData.vDimension.add(new MonitorReqData.DimensionWrapper(DETECT_PING_PUB_SUCCESS, Integer.toString(i)));
        monitorReqData.vField.add(new MonitorReqData.FieldWrapper(DETECT_PING_PUB_RTT, j));
        monitorReqData.vField.add(new MonitorReqData.FieldWrapper(DETECT_PING_PUB_DNS, j2));
        monitorReqData.vField.add(new MonitorReqData.FieldWrapper(DETECT_PING_PUB_LOSS_RATE, d));
    }

    private void addTcpPubResultMonitorData(NSDT.TCPResult tCPResult, MonitorReqData monitorReqData) {
        monitorReqData.vDimension.add(new MonitorReqData.DimensionWrapper(DETECT_TCP_PUB_SUCCESS_CODE, Integer.toString(tCPResult.getErrCode())));
        monitorReqData.vDimension.add(new MonitorReqData.DimensionWrapper(DETECT_TCP_PUB_RETCODE, Integer.toString(tCPResult.getErrType())));
        monitorReqData.vDimension.add(new MonitorReqData.DimensionWrapper(DETECT_TCP_PUB_HOST, tCPResult.getHost()));
        monitorReqData.vDimension.add(new MonitorReqData.DimensionWrapper(DETECT_TCP_PUB_IP, tCPResult.getIp()));
        monitorReqData.vDimension.add(new MonitorReqData.DimensionWrapper(DETECT_TCP_PUB_PORT, Integer.toString(tCPResult.getPort())));
        monitorReqData.vField.add(new MonitorReqData.FieldWrapper(DETECT_TCP_PUB_DNS_COST, tCPResult.getDnsCost()));
        monitorReqData.vField.add(new MonitorReqData.FieldWrapper(DETECT_TCP_PUB_RTT, tCPResult.getRtt()));
        monitorReqData.vField.add(new MonitorReqData.FieldWrapper(DETECT_TCP_PUB_CONN_COST, tCPResult.getConnectTime()));
    }

    private void addTcpHuyaMonitorData(NSDT.TCPResult tCPResult, MonitorReqData monitorReqData) {
        monitorReqData.vDimension.add(new MonitorReqData.DimensionWrapper(DETECT_TCP_HUYA_SUCCESS_CODE, Integer.toString(tCPResult.getErrCode())));
        monitorReqData.vDimension.add(new MonitorReqData.DimensionWrapper(DETECT_TCP_HUYA_RETCODE, Integer.toString(tCPResult.getErrType())));
        monitorReqData.vDimension.add(new MonitorReqData.DimensionWrapper(DETECT_TCP_HUYA_HOST, tCPResult.getHost()));
        monitorReqData.vDimension.add(new MonitorReqData.DimensionWrapper(DETECT_TCP_HUYA_IP, tCPResult.getIp()));
        monitorReqData.vDimension.add(new MonitorReqData.DimensionWrapper(DETECT_TCP_HUYA_PORT, Integer.toString(tCPResult.getPort())));
        monitorReqData.vField.add(new MonitorReqData.FieldWrapper(DETECT_TCP_HUYA_DNS_COST, tCPResult.getDnsCost()));
        monitorReqData.vField.add(new MonitorReqData.FieldWrapper(DETECT_TCP_HUYA_RTT, tCPResult.getRtt()));
        monitorReqData.vField.add(new MonitorReqData.FieldWrapper(DETECT_TCP_HUYA_CONN_COST, tCPResult.getConnectTime()));
    }

    private int isInternetAvailable(Context context) {
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            if (connectivityManager == null) {
                return -1;
            }
            if (Build.VERSION.SDK_INT >= 23) {
                NetworkCapabilities networkCapabilities = connectivityManager.getNetworkCapabilities(connectivityManager.getActiveNetwork());
                if (networkCapabilities == null) {
                    return -2;
                }
                if (networkCapabilities.hasTransport(1)) {
                    return 1;
                }
                if (networkCapabilities.hasTransport(0)) {
                    return 0;
                }
                return networkCapabilities.hasTransport(3) ? 3 : -3;
            }
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            if (activeNetworkInfo == null) {
                return -2;
            }
            int type = activeNetworkInfo.getType();
            if (type == 1) {
                return 1;
            }
            if (type == 0) {
                return 0;
            }
            return type == 9 ? 3 : -3;
        } catch (Exception e) {
            MTPApi.LOGGER.error(TAG, "isInternetAvailable failed", e);
            return -4;
        }
    }

    private void reportDetectResult(String str, MonitorReqData monitorReqData) {
        List<MonitorReqData> listRemove = this.mNSRequestMonitorDataCache.remove(str);
        if (listRemove == null || listRemove.isEmpty()) {
            MTPApi.LOGGER.error(TAG, "reportDetectResult IP: %s failed, ns request monitor data is empty", str);
            return;
        }
        for (MonitorReqData monitorReqData2 : listRemove) {
            if (monitorReqData2 != null) {
                monitorReqData2.sMetricName = "hymtp.hyns.monitor.client.nsdt";
                monitorReqData2.vDimension.addAll(monitorReqData.vDimension);
                monitorReqData2.vField.addAll(monitorReqData.vField);
                MTPApi.MONITOR.request(monitorReqData2);
            }
        }
    }

    private void dispatch(Runnable runnable) {
        if (Thread.currentThread() == this.sDispatchthread.getThread()) {
            runnable.run();
        } else {
            this.sDispatchthread.getHandler().post(runnable);
        }
    }

    private static class HandlerThreadWrapper {
        private Handler mHandler;
        private HandlerThread mThread;

        HandlerThreadWrapper(String str) {
            this(str, null);
        }

        HandlerThreadWrapper(String str, Handler.Callback callback) {
            HandlerThread handlerThread = new HandlerThread("NSDetectNetMgr" + str);
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
