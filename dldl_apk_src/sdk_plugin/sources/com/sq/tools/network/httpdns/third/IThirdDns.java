package com.sq.tools.network.httpdns.third;

import android.content.Context;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public interface IThirdDns {

    public interface IThirdDnsCallback {
        void onDns(String host, String[] ipV4, long elapse);
    }

    String getDnsServerIp();

    void getIp(String host, IThirdDnsCallback callback);

    void init(Context context);
}
