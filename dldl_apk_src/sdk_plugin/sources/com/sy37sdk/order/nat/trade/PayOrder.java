package com.sy37sdk.order.nat.trade;

import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class PayOrder {
    private String action;
    private String method;
    private String mwebUrl;
    private List<String> para;
    private int refundswitch;
    private String tn;
    private String trade;
    private String uuid;
    private String wxReferer;

    public List<String> getPara() {
        return this.para;
    }

    public String getAction() {
        return this.action;
    }

    public String getMethod() {
        return this.method;
    }

    public String getTn() {
        return this.tn;
    }

    public String getTrade() {
        return this.trade;
    }

    public String getMwebUrl() {
        return this.mwebUrl;
    }

    public String getWxReferer() {
        return this.wxReferer;
    }

    public String getUuid() {
        return this.uuid;
    }

    public int getRefundswitch() {
        return this.refundswitch;
    }

    public String toString() {
        return "action:" + this.action + ", method:" + this.method + ", tn:" + this.tn + ", trade:" + this.trade + ", uuid:" + this.uuid + ", refundswitch:" + this.refundswitch;
    }

    public static PayOrder fromJson(String str) throws JSONException {
        PayOrder payOrder = new PayOrder();
        JSONObject jSONObject = new JSONObject(str);
        payOrder.action = jSONObject.optString("action");
        payOrder.method = jSONObject.optString("method");
        payOrder.tn = jSONObject.optString("tn");
        payOrder.trade = jSONObject.optString("trade");
        payOrder.uuid = jSONObject.optString("uuid");
        payOrder.refundswitch = jSONObject.optInt("refundswitch");
        payOrder.mwebUrl = jSONObject.optString("mweb_url");
        payOrder.wxReferer = jSONObject.optString("wx_referer");
        return payOrder;
    }
}
