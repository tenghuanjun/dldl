package com.sq.tools.network.httpdns.callback;

import com.sq.tools.network.httpdns.network.HttpDnsRequestStat;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public interface IDnsRequestListener {
    void onDnsRequest(HttpDnsRequestStat dnsRequestStat);

    void onFail(HttpDnsRequestStat dnsRequestStat);

    void onForceClose();

    void onInvalid(HttpDnsRequestStat dnsRequestStat);

    void onRecordThirdDns(String host, String url, int code, String reason);

    void onSuccess(HttpDnsRequestStat dnsRequestStat);

    void onThirdDns(String dnsIp, String host, String[] ips, long elapse);
}
