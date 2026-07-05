package com.sq.tools.network.httpdns.data;

import android.text.TextUtils;
import com.sq.tool.logger.SQLog;
import com.sqwan.bugless.util.DateUtil;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class DnsData {
    private static final String KEY_HOST = "host";
    private static final String KEY_IPV4S = "ips";
    private static final String KEY_IPV6S = "ipv6s";
    private static final String KEY_TIME = "time";
    private static final String KEY_TTL = "ttl";
    private String host;
    private List<IPData> ipV6s;
    private List<IPData> ips;
    private long timeStamp;
    private long ttl;

    public DnsData() {
    }

    public DnsData(String host, List<IPData> ips, List<IPData> ipsV6, long ttl, long timeStamp) {
        this.host = host;
        this.ips = ips;
        this.ipV6s = ipsV6;
        this.ttl = ttl;
        this.timeStamp = timeStamp;
    }

    public long getTimeStamp() {
        return this.timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getHost() {
        return this.host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public List<IPData> getIps() {
        return this.ips;
    }

    public void setIps(List<IPData> ips) {
        this.ips = ips;
    }

    public List<IPData> getIpV6s() {
        return this.ipV6s;
    }

    public void setIpV6s(List<IPData> ipsV6) {
        this.ipV6s = ipsV6;
    }

    public long getTtl() {
        return this.ttl;
    }

    public void setTtl(long ttl) {
        this.ttl = ttl;
    }

    public static DnsData empty(String host) {
        return new DnsData(host, new ArrayList(), new ArrayList(), 0L, System.currentTimeMillis());
    }

    public boolean overTime() {
        return System.currentTimeMillis() > this.timeStamp + (((this.ttl * 1000) * 3) / 4);
    }

    public String toString() {
        return toJson().toString();
    }

    public static String stampToDate(long time) {
        return new SimpleDateFormat(DateUtil.DEFAULT_DATE_TIME_FORMAT, Locale.US).format(new Date(time));
    }

    public static DnsData parse(String jsonString) {
        if (TextUtils.isEmpty(jsonString)) {
            return null;
        }
        try {
            DnsData dnsData = new DnsData();
            JSONObject jSONObject = new JSONObject(jsonString);
            dnsData.setHost(jSONObject.optString("host"));
            dnsData.ips = parseIpDataArray(jSONObject.optJSONArray(KEY_IPV4S));
            dnsData.setIpV6s(parseIpDataArray(jSONObject.optJSONArray(KEY_IPV6S)));
            dnsData.setTtl(jSONObject.optLong(KEY_TTL));
            dnsData.setTimeStamp(jSONObject.optLong("time", System.currentTimeMillis()));
            return dnsData;
        } catch (Exception e) {
            SQLog.e("DNS配置解析异常", e);
            return null;
        }
    }

    private static List<IPData> parseIpDataArray(JSONArray array) {
        if (array == null) {
            return new ArrayList();
        }
        ArrayList arrayList = new ArrayList();
        if (array.length() > 0) {
            for (int i = 0; i < array.length(); i++) {
                IPData iPData = IPData.parse(array.optJSONObject(i));
                if (iPData != null) {
                    arrayList.add(iPData);
                }
            }
        }
        return arrayList;
    }

    public JSONObject toJson() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("host", this.host);
            JSONArray jSONArray = new JSONArray();
            if (this.ips != null && this.ips.size() > 0) {
                Iterator<IPData> it = this.ips.iterator();
                while (it.hasNext()) {
                    jSONArray.put(it.next().toJson());
                }
            }
            jSONObject.put(KEY_IPV4S, jSONArray);
            JSONArray jSONArray2 = new JSONArray();
            if (this.ipV6s != null && this.ipV6s.size() > 0) {
                Iterator<IPData> it2 = this.ipV6s.iterator();
                while (it2.hasNext()) {
                    jSONArray2.put(it2.next().toJson());
                }
            }
            jSONObject.put(KEY_IPV6S, jSONArray2);
            jSONObject.put(KEY_TTL, this.ttl);
            jSONObject.put("time", this.timeStamp);
        } catch (Exception e) {
            SQLog.e("DNS配置解析异常", e);
        }
        return jSONObject;
    }
}
