package com.huya.hyhttpdns.dns;

import com.huya.mtp.hyns.stat.NSStatReporter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
class HttpDnsNetRequestStat {
    public String currentIp;
    public String domainName;
    public String error;
    public int iRetCode;
    public double responseTime = 0.0d;
    public int iSuccess = 1;
    public long localDnsCostTime = 0;
    public long connectCostTime = 0;
    public long sendCostTime = 0;
    public long ipConnectCostTime = 0;
    public long ipSendCostTime = 0;
    public long ipResponseTime = 0;
    public int currentRetryCount = 0;
    public boolean hasLocalDnsIPs = false;
    public boolean isNetworkAvailable = true;
    public boolean isFirstTime = false;
    public int longIPListState = 0;
    public int shortIPListState = 0;
    public List<String> localDnsIPs = new ArrayList();

    public HttpDnsNetRequestStat(String str) {
        this.domainName = "";
        this.domainName = str;
    }

    public void reportNetRequestStat() {
        HttpDnsLogProxy.getInstance().info("HyHttpDns", "reportNetRequestStat %s", this);
        HttpDns.getInstance().reportStat("httpdns", "net_response_time", createStatDimension(), createFields(), createStatExLog());
    }

    private Map<String, String> createStatDimension() {
        HashMap map = new HashMap();
        map.put("domain", this.domainName);
        map.put("local_dns_success", String.valueOf(this.hasLocalDnsIPs));
        map.put("is_network_available", String.valueOf(this.isNetworkAvailable));
        map.put("longIPListState", String.valueOf(this.longIPListState));
        map.put("shortIPListState", String.valueOf(this.shortIPListState));
        return map;
    }

    private Map<String, Double> createFields() {
        HashMap map = new HashMap();
        map.put("value", Double.valueOf(this.responseTime));
        map.put("success", Double.valueOf(this.iSuccess));
        map.put(NSStatReporter.NS_RETCODE, Double.valueOf(this.iRetCode));
        return map;
    }

    private Map<String, String> createStatExLog() {
        HashMap map = new HashMap();
        map.put("local_dns_ips", this.localDnsIPs.toString());
        map.put("local_Dns_costtime", String.valueOf(this.localDnsCostTime));
        map.put("connect_costtime", String.valueOf(this.connectCostTime));
        map.put("send_costtime", String.valueOf(this.sendCostTime));
        map.put("ip_connect_costtime", String.valueOf(this.ipConnectCostTime));
        map.put("ip_send_costtime", String.valueOf(this.ipSendCostTime));
        map.put("ip_responsetime", String.valueOf(this.ipResponseTime));
        map.put("error", String.valueOf(this.error));
        map.put("current_ip", String.valueOf(this.currentIp));
        map.put("current_retry_count", String.valueOf(this.currentRetryCount));
        map.put("is_first_time", String.valueOf(this.isFirstTime));
        return map;
    }

    public String toString() {
        return "HttpDnsNetRequestStat{responseTime=" + this.responseTime + ", domainName='" + this.domainName + "', iSuccess=" + this.iSuccess + ", iRetCode=" + this.iRetCode + ", error='" + this.error + "', localDnsIPs=" + this.localDnsIPs + ", localDnsCostTime=" + this.localDnsCostTime + ", connectCostTime=" + this.connectCostTime + ", sendCostTime=" + this.sendCostTime + ", ipConnectCostTime=" + this.ipConnectCostTime + ", ipSendCostTime=" + this.ipSendCostTime + ", ipResponseTime=" + this.ipResponseTime + ", currentRetryCount=" + this.currentRetryCount + ", currentIp='" + this.currentIp + "', hasLocalDnsIPs=" + this.hasLocalDnsIPs + ", isNetworkAvailable=" + this.isNetworkAvailable + ", longIPListState=" + this.longIPListState + ", shortIPListState=" + this.shortIPListState + AbstractJsonLexerKt.END_OBJ;
    }
}
