package com.sq.tools.network.httpdns.network;

import android.content.Context;
import com.sq.tools.network.httpdns.SqHttpDns;
import com.sq.tools.network.httpdns.data.DnsServerData;
import com.sq.tools.network.httpdns.dns.HttpDnsCache;
import com.sq.tools.network.httpdns.log.HttpDnsLog;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class HttpDnsUrl {
    public static String getHttpDnsServerUrl(Context context) {
        String str = "http://" + getHttpDnsIP(HttpDnsCache.getHttpDnsServerFromCache(context)) + "/record/ext";
        HttpDnsLog.d("httpDns服务接口 " + str);
        return str;
    }

    private static String getHttpDnsIP(DnsServerData dnsServerData) {
        List serviceIps = dnsServerData.getServiceIps();
        HttpDnsLog.v("getHttpDnsIP start " + serviceIps);
        if (serviceIps == null || serviceIps.isEmpty()) {
            serviceIps = Arrays.asList(SqHttpDns.getInstance().getHttpDnsConfig().dnsServerIps);
        }
        if (serviceIps.size() == 1) {
            HttpDnsLog.v("getHttpDnsIP: 使用唯一的ip：" + ((String) serviceIps.get(0)));
            return (String) serviceIps.get(0);
        }
        int iNextInt = new Random().nextInt(serviceIps.size());
        String str = (String) serviceIps.get(iNextInt);
        HttpDnsLog.v("getHttpDnsIP: 获取第" + iNextInt + " 个：" + str);
        return str;
    }

    public static String getSingleDnsUrl(Context context) {
        String str = "http://" + getHttpDnsIP(HttpDnsCache.getHttpDnsServerFromCache(context)) + "/record";
        HttpDnsLog.v("单域名请求的接口 " + str);
        return str;
    }

    public static String getMultiDnsUrl(Context context) {
        String str = "http://" + getHttpDnsIP(HttpDnsCache.getHttpDnsServerFromCache(context)) + "/record/batch";
        HttpDnsLog.i("多域名请求的接口 " + str);
        return str;
    }
}
