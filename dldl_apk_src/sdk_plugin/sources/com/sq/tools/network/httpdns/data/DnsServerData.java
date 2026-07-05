package com.sq.tools.network.httpdns.data;

import android.text.TextUtils;
import com.sq.tool.logger.SQLog;
import com.sq.tools.network.httpdns.SqHttpDns;
import com.sq.tools.network.httpdns.SqHttpDnsConfig;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class DnsServerData {
    private static final String KEY_HTTP_DNS = "http_dns";
    private static final String KEY_IPS = "service_ips";
    private static final String KEY_IPV6_ENABLE = "ipv6_enable";
    private static final String KEY_REPORT_BLACKLIST = "report_black_list";
    private static final String KEY_REPORT_REQUEST = "report_sdk_request";
    private static final String KEY_REPORT_WEBVIEW = "report_webview_request";
    private static final String KEY_TX_IPS = "tx_service_ips";
    private static final String KEY_WEBVIEW = "webview";
    private static final String KEY_WEBVIEW_IPV6_ENABLE = "webview_ipv6_enable";
    private boolean httpDns;
    private boolean ipV6Enable;
    private boolean reportNetStat;
    private List<String> reportNetStatBlacklist;
    private boolean reportWebviewNetStat;
    private ArrayList<String> serviceIps;
    private ArrayList<String> txServiceIps;
    private boolean webViewDns;
    private boolean webviewIpV6Enable;

    public boolean isHttpDns() {
        return this.httpDns;
    }

    public void setHttpDns(boolean httpDns) {
        this.httpDns = httpDns;
    }

    public ArrayList<String> getServiceIps() {
        return this.serviceIps;
    }

    public void setServiceIps(ArrayList<String> serviceIps) {
        this.serviceIps = serviceIps;
    }

    public boolean isWebViewDns() {
        return this.webViewDns;
    }

    public void setWebViewDns(boolean webViewDns) {
        this.webViewDns = webViewDns;
    }

    public ArrayList<String> getTxServiceIps() {
        return this.txServiceIps;
    }

    public void setTxServiceIps(ArrayList<String> txServiceIps) {
        this.txServiceIps = txServiceIps;
    }

    public boolean isReportNetStat() {
        return this.reportNetStat;
    }

    public void setReportNetStat(boolean reportNetStat) {
        this.reportNetStat = reportNetStat;
    }

    public List<String> getReportNetStatBlacklist() {
        return this.reportNetStatBlacklist;
    }

    public void setReportNetStatBlacklist(List<String> reportNetStatBlacklist) {
        this.reportNetStatBlacklist = reportNetStatBlacklist;
    }

    public boolean isReportWebviewNetStat() {
        return this.reportWebviewNetStat;
    }

    public void setReportWebviewNetStat(boolean reportWebviewNetStat) {
        this.reportWebviewNetStat = reportWebviewNetStat;
    }

    public boolean isIpV6Enable() {
        return this.ipV6Enable;
    }

    public void setIpV6Enable(boolean ipV6Enable) {
        this.ipV6Enable = ipV6Enable;
    }

    public boolean isWebViewIpV6Enable() {
        return this.webviewIpV6Enable;
    }

    public void setWebViewIpV6Enable(boolean ipV6Enable) {
        this.webviewIpV6Enable = ipV6Enable;
    }

    public DnsServerData() {
    }

    public DnsServerData(boolean httpDns, ArrayList<String> serviceIps, ArrayList<String> txServiceIps, boolean webViewDns) {
        this.httpDns = httpDns;
        this.serviceIps = serviceIps;
        this.txServiceIps = txServiceIps;
        this.webViewDns = webViewDns;
    }

    public String toString() {
        return toJson();
    }

    public static DnsServerData defaultConfig() {
        SqHttpDnsConfig httpDnsConfig = SqHttpDns.getInstance().getHttpDnsConfig();
        ArrayList arrayList = new ArrayList();
        if (httpDnsConfig.dnsServerIps != null) {
            Collections.addAll(arrayList, httpDnsConfig.dnsServerIps);
        }
        return new DnsServerData(httpDnsConfig.enable, arrayList, new ArrayList(), false);
    }

    public static DnsServerData parse(String json) {
        if (TextUtils.isEmpty(json)) {
            return defaultConfig();
        }
        try {
            DnsServerData dnsServerData = new DnsServerData();
            JSONObject jSONObject = new JSONObject(json);
            dnsServerData.setHttpDns(jSONObject.optBoolean(KEY_HTTP_DNS));
            dnsServerData.setWebViewDns(jSONObject.optBoolean(KEY_WEBVIEW));
            dnsServerData.setReportNetStat(jSONObject.optBoolean(KEY_REPORT_REQUEST));
            dnsServerData.setReportWebviewNetStat(jSONObject.optBoolean(KEY_REPORT_WEBVIEW));
            dnsServerData.setIpV6Enable(jSONObject.optBoolean(KEY_IPV6_ENABLE));
            dnsServerData.setWebViewIpV6Enable(jSONObject.optBoolean(KEY_WEBVIEW_IPV6_ENABLE));
            JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray(KEY_IPS);
            if (jSONArrayOptJSONArray != null && jSONArrayOptJSONArray.length() > 0) {
                ArrayList<String> arrayList = new ArrayList<>();
                for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                    arrayList.add(jSONArrayOptJSONArray.getString(i));
                }
                dnsServerData.setServiceIps(arrayList);
            }
            JSONArray jSONArrayOptJSONArray2 = jSONObject.optJSONArray(KEY_TX_IPS);
            if (jSONArrayOptJSONArray2 != null && jSONArrayOptJSONArray2.length() > 0) {
                ArrayList<String> arrayList2 = new ArrayList<>();
                for (int i2 = 0; i2 < jSONArrayOptJSONArray2.length(); i2++) {
                    arrayList2.add(jSONArrayOptJSONArray2.getString(i2));
                }
                dnsServerData.setTxServiceIps(arrayList2);
            }
            JSONArray jSONArrayOptJSONArray3 = jSONObject.optJSONArray(KEY_REPORT_BLACKLIST);
            if (jSONArrayOptJSONArray3 != null && jSONArrayOptJSONArray3.length() > 0) {
                ArrayList arrayList3 = new ArrayList();
                for (int i3 = 0; i3 < jSONArrayOptJSONArray3.length(); i3++) {
                    arrayList3.add(jSONArrayOptJSONArray3.getString(i3));
                }
                dnsServerData.setReportNetStatBlacklist(arrayList3);
            }
            return dnsServerData;
        } catch (Exception e) {
            SQLog.e("DNS配置解析异常", e);
            return defaultConfig();
        }
    }

    public String toJson() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(KEY_HTTP_DNS, isHttpDns());
            jSONObject.put(KEY_WEBVIEW, isWebViewDns());
            jSONObject.put(KEY_REPORT_REQUEST, isReportNetStat());
            jSONObject.put(KEY_REPORT_WEBVIEW, isReportWebviewNetStat());
            jSONObject.put(KEY_IPV6_ENABLE, isIpV6Enable());
            jSONObject.put(KEY_WEBVIEW_IPV6_ENABLE, isWebViewIpV6Enable());
            ArrayList<String> serviceIps = getServiceIps();
            JSONArray jSONArray = new JSONArray();
            if (serviceIps != null && serviceIps.size() > 0) {
                Iterator<String> it = serviceIps.iterator();
                while (it.hasNext()) {
                    jSONArray.put(it.next());
                }
            }
            jSONObject.put(KEY_IPS, jSONArray);
            ArrayList<String> txServiceIps = getTxServiceIps();
            JSONArray jSONArray2 = new JSONArray();
            if (txServiceIps != null && txServiceIps.size() > 0) {
                Iterator<String> it2 = txServiceIps.iterator();
                while (it2.hasNext()) {
                    jSONArray2.put(it2.next());
                }
            }
            jSONObject.put(KEY_TX_IPS, jSONArray2);
            List<String> reportNetStatBlacklist = getReportNetStatBlacklist();
            JSONArray jSONArray3 = new JSONArray();
            if (reportNetStatBlacklist != null && reportNetStatBlacklist.size() > 0) {
                Iterator<String> it3 = reportNetStatBlacklist.iterator();
                while (it3.hasNext()) {
                    jSONArray3.put(it3.next());
                }
            }
            jSONObject.put(KEY_REPORT_BLACKLIST, jSONArray3);
            return jSONObject.toString();
        } catch (Exception e) {
            SQLog.e("DNS配置解析异常", e);
            return "";
        }
    }
}
