package com.sdk.sq.net;

import com.sq.tools.network.httpdns.SqHttpDns;
import com.sqnetwork.voly.toolbox.LocalDNS;
import java.util.List;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class HttpDns implements LocalDNS {
    public static final LocalDNS DEFAULT = new HttpDns();

    @Override // com.sqnetwork.voly.toolbox.LocalDNS
    public boolean isEnable() {
        try {
            return SqHttpDns.getInstance().isDnsEnable();
        } catch (Throwable unused) {
            return false;
        }
    }

    @Override // com.sqnetwork.voly.toolbox.LocalDNS
    public String getIp(String host, List<String> excludeIps) {
        try {
            return SqHttpDns.getInstance().getIpByHost(host, excludeIps, SqHttpDns.getInstance().isIpV6Enable());
        } catch (Throwable unused) {
            return null;
        }
    }
}
