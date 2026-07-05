package com.sq.tools.network.httpdns.data;

import com.sq.tool.logger.SQLog;
import com.sq.tools.network.httpdns.util.HttpDnsUtil;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class IPData {
    private static final String KEY_IP = "ip";
    private static final String KEY_IP_W = "w";
    private String ip;
    private int weight;

    public String getIp() {
        return this.ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getWeight() {
        return this.weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String toString() {
        return toJson().toString();
    }

    public JSONObject toJson() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("ip", this.ip);
            jSONObject.put(KEY_IP_W, this.weight);
        } catch (Exception e) {
            SQLog.e("DNS配置解析异常", e);
        }
        return jSONObject;
    }

    public static IPData parse(JSONObject json) {
        if (json == null) {
            return null;
        }
        String strOptString = json.optString("ip");
        int iOptInt = json.optInt(KEY_IP_W);
        if (!HttpDnsUtil.isIpAddress(strOptString)) {
            return null;
        }
        IPData iPData = new IPData();
        iPData.setIp(strOptString);
        iPData.setWeight(iOptInt);
        return iPData;
    }
}
