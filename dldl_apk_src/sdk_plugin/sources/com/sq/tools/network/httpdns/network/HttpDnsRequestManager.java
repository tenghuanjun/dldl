package com.sq.tools.network.httpdns.network;

import android.content.Context;
import com.sq.tools.network.httpdns.SqHttpDns;
import com.sq.tools.network.httpdns.callback.IDnsRequestListener;
import com.sq.tools.network.httpdns.log.HttpDnsLog;
import com.sq.tools.network.httpdns.third.IThirdDns;
import com.sq.tools.network.httpdns.util.ExceptionReporter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class HttpDnsRequestManager {
    private static final int REQUEST_TIME_INTERVAL = 15000;
    private static volatile HttpDnsRequestManager instance;
    public static HashMap<String, Long> requestDnsByHostMap = new HashMap<>();
    private IGetJsonRequest mGetJsonRequest;
    private IThirdDns mThirdDns;

    private HttpDnsRequestManager() {
    }

    public static HttpDnsRequestManager getInstance() {
        if (instance == null) {
            synchronized (HttpDnsRequestManager.class) {
                if (instance == null) {
                    instance = new HttpDnsRequestManager();
                }
            }
        }
        return instance;
    }

    public void setGetJsonRequest(IGetJsonRequest request) {
        this.mGetJsonRequest = request;
    }

    public void setThirdDns(IThirdDns thirdDns) {
        this.mThirdDns = thirdDns;
    }

    public void requestDnsServer(Context context, Map<String, String> requestMap, IDnsRequestListener dnsRequestListener) {
        new HttpDnsNetRequest(this.mGetJsonRequest, SqHttpDns.getInstance().getHttpDnsConfig().dnsServerHost, this.mThirdDns).setExceptionReporter(new ExceptionReporter(SqHttpDns.getInstance().getExceptionReporter())).requestDnsServer(context, requestMap, dnsRequestListener);
    }

    public void requestDnsSingle(Context context, String host, IDnsRequestListener dnsRequestListener) {
        if (checkTimeLimitValid(host)) {
            new HttpDnsNetRequest(this.mGetJsonRequest, SqHttpDns.getInstance().getHttpDnsConfig().dnsServerHost, this.mThirdDns).setExceptionReporter(new ExceptionReporter(SqHttpDns.getInstance().getExceptionReporter())).requestDnsSingle(context, host, dnsRequestListener);
        }
    }

    public void requestDnsMulti(Context context, ArrayList<String> hosts, IDnsRequestListener dnsRequestListener) {
        if (hosts == null || hosts.isEmpty()) {
            return;
        }
        ArrayList<String> arrayList = new ArrayList<>();
        for (String str : hosts) {
            if (checkTimeLimitValid(str)) {
                arrayList.add(str);
            }
        }
        if (arrayList.isEmpty()) {
            return;
        }
        new HttpDnsNetRequest(this.mGetJsonRequest, SqHttpDns.getInstance().getHttpDnsConfig().dnsServerHost, this.mThirdDns).setExceptionReporter(new ExceptionReporter(SqHttpDns.getInstance().getExceptionReporter())).requestDnsMulti(context, arrayList, dnsRequestListener);
    }

    private boolean checkTimeLimitValid(String host) {
        if (!requestDnsByHostMap.containsKey(host)) {
            requestDnsByHostMap.put(host, Long.valueOf(System.currentTimeMillis()));
            return true;
        }
        Long l = requestDnsByHostMap.get(host);
        if (l == null) {
            l = 0L;
        }
        if (!(System.currentTimeMillis() - l.longValue() > 15000)) {
            HttpDnsLog.v(host + " 请求dns时间间隔未到, 不能请求");
            return false;
        }
        requestDnsByHostMap.put(host, Long.valueOf(System.currentTimeMillis()));
        return true;
    }
}
