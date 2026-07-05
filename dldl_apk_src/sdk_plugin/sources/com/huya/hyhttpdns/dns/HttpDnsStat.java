package com.huya.hyhttpdns.dns;

import com.huya.mtp.hyns.stat.NSStatReporter;
import java.util.HashMap;
import java.util.Map;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
class HttpDnsStat {
    public String domain;
    public int iRetCode;
    public String timeout;
    public String type;
    public double responseTime = 0.0d;
    public int iSuccess = 1;
    public int ipListState = 0;

    public HttpDnsStat(String str, String str2) {
        this.domain = str;
        this.timeout = str2;
    }

    public void reportHttpDnsStat() {
        HttpDnsLogProxy.getInstance().info("HyHttpDns", "reportHttpDnsStat %s", this);
        HttpDns.getInstance().reportStat("httpdns", NSStatReporter.NS_RESPONSE_TIME, createStatDimension(), createfields(), null);
    }

    private Map<String, String> createStatDimension() {
        HashMap map = new HashMap();
        map.put("domain", this.domain);
        map.put("timeout", this.timeout);
        map.put("type", this.type);
        map.put("ipListState", String.valueOf(this.ipListState));
        return map;
    }

    private Map<String, Double> createfields() {
        HashMap map = new HashMap();
        map.put("value", Double.valueOf(this.responseTime));
        map.put("success", Double.valueOf(this.iSuccess));
        map.put(NSStatReporter.NS_RETCODE, Double.valueOf(this.iRetCode));
        return map;
    }

    public String toString() {
        return "HttpDnsStat{responseTime=" + this.responseTime + ", domain='" + this.domain + "', timeout='" + this.timeout + "', type='" + this.type + "', iSuccess=" + this.iSuccess + ", iRetCode=" + this.iRetCode + ", ipListState=" + this.ipListState + AbstractJsonLexerKt.END_OBJ;
    }
}
