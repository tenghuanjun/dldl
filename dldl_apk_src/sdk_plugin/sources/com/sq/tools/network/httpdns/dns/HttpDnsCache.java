package com.sq.tools.network.httpdns.dns;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.sq.tools.network.httpdns.data.DnsData;
import com.sq.tools.network.httpdns.data.DnsServerData;
import com.sq.tools.network.httpdns.data.IPData;
import com.sq.tools.network.httpdns.log.HttpDnsLog;
import java.util.ArrayList;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class HttpDnsCache {
    private static final String SP_HTTP_DNS_DATA_KEY = "sq_http_dns_data_key_";
    private static final String SP_HTTP_DNS_NAME = "sq_http_dns_data";
    private static final String SP_HTTP_DNS_SERVER_DATA = "http_dns_server_data";
    private static final String SP_HTTP_DNS_SERVER_FAIL_COUNT = "http_dns_server_fail_count";

    public static void accumulateHttpDnsServerFailCount(Context context) {
        int httpDnsServerFailCount = getHttpDnsServerFailCount(context) + 1;
        SharedPreferences.Editor editorEdit = context.getSharedPreferences(SP_HTTP_DNS_NAME, 0).edit();
        editorEdit.putInt(SP_HTTP_DNS_SERVER_FAIL_COUNT, httpDnsServerFailCount);
        editorEdit.apply();
    }

    public static int getHttpDnsServerFailCount(Context context) {
        return context.getSharedPreferences(SP_HTTP_DNS_NAME, 0).getInt(SP_HTTP_DNS_SERVER_FAIL_COUNT, 0);
    }

    public static void clearHttpDnsServerFailCount(Context context) {
        SharedPreferences.Editor editorEdit = context.getSharedPreferences(SP_HTTP_DNS_NAME, 0).edit();
        editorEdit.putInt(SP_HTTP_DNS_SERVER_FAIL_COUNT, 0);
        editorEdit.apply();
    }

    public static void saveHttpDnsServerByJsonStr(Context context, String dnsJsonStr) {
        if (TextUtils.isEmpty(dnsJsonStr)) {
            HttpDnsLog.i("saveHttpDnsIP: dnsJsonStr is empty");
            return;
        }
        SharedPreferences.Editor editorEdit = context.getSharedPreferences(SP_HTTP_DNS_NAME, 0).edit();
        editorEdit.putString(SP_HTTP_DNS_SERVER_DATA, dnsJsonStr);
        editorEdit.apply();
    }

    public static void saveHttpDnsServerByData(Context context, DnsServerData dnsServerData) {
        if (dnsServerData == null) {
            HttpDnsLog.i("saveHttpDnsIP: dnsServerData is empty");
            return;
        }
        SharedPreferences.Editor editorEdit = context.getSharedPreferences(SP_HTTP_DNS_NAME, 0).edit();
        editorEdit.putString(SP_HTTP_DNS_SERVER_DATA, dnsServerData.toJson());
        editorEdit.apply();
    }

    public static void refreshHttpDnsEnable(Context context, boolean enable) {
        DnsServerData httpDnsServerFromCache = getHttpDnsServerFromCache(context);
        if (httpDnsServerFromCache.isHttpDns() != enable) {
            httpDnsServerFromCache.setHttpDns(enable);
            saveHttpDnsServerByJsonStr(context, httpDnsServerFromCache.toJson());
        }
    }

    public static DnsServerData getHttpDnsServerFromCache(Context context) {
        return DnsServerData.parse(context.getSharedPreferences(SP_HTTP_DNS_NAME, 0).getString(SP_HTTP_DNS_SERVER_DATA, ""));
    }

    public static DnsData getHttpDnsDataByHost(Context context, String host) {
        DnsData dnsData = DnsData.parse(context.getSharedPreferences(SP_HTTP_DNS_NAME, 0).getString(SP_HTTP_DNS_DATA_KEY + host, ""));
        return dnsData != null ? dnsData : DnsData.empty(host);
    }

    public static void saveHttpDnsDataByHost(Context context, String host, String dnsJsonStr) {
        try {
            JSONObject jSONObject = new JSONObject(dnsJsonStr);
            jSONObject.put("time", System.currentTimeMillis());
            SharedPreferences.Editor editorEdit = context.getSharedPreferences(SP_HTTP_DNS_NAME, 0).edit();
            editorEdit.putString(SP_HTTP_DNS_DATA_KEY + host, jSONObject.toString());
            editorEdit.apply();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void saveHttpDnsIPV4DataByHost(Context context, String host, String[] ipv4s) {
        try {
            DnsData dnsData = new DnsData();
            dnsData.setHost(host);
            ArrayList arrayList = new ArrayList();
            for (String str : ipv4s) {
                IPData iPData = new IPData();
                iPData.setIp(str);
                iPData.setWeight(1);
                arrayList.add(iPData);
            }
            dnsData.setIps(arrayList);
            dnsData.setTtl(60L);
            String string = dnsData.toJson().toString();
            SharedPreferences.Editor editorEdit = context.getSharedPreferences(SP_HTTP_DNS_NAME, 0).edit();
            editorEdit.putString(SP_HTTP_DNS_DATA_KEY + host, string);
            editorEdit.apply();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void clearHttpDnsServerData(Context context) {
        SharedPreferences.Editor editorEdit = context.getSharedPreferences(SP_HTTP_DNS_NAME, 0).edit();
        editorEdit.putString(SP_HTTP_DNS_SERVER_DATA, "");
        editorEdit.apply();
    }
}
