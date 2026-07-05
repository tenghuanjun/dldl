package com.sy37sdk.order.nat.pay;

import java.util.Map;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class Result {
    public static final String STATUS_CODE_CANCEL = "6001";
    public static final String STATUS_CODE_SUCCESS = "9000";
    public static final int STATUS_CODE_URL = 8989;
    private String memo;
    private String result;
    private String resultStatus;
    private String returnUrl;

    public Result(Object obj) {
        if (obj instanceof Map) {
            JSONObject jSONObject = new JSONObject((Map) obj);
            this.resultStatus = jSONObject.optString("resultStatus");
            this.result = jSONObject.optString("result");
            this.memo = jSONObject.optString("memo");
            this.returnUrl = jSONObject.optString("returnUrl");
        }
    }

    public String getResultStatus() {
        return this.resultStatus;
    }

    public String getMemo() {
        return this.memo;
    }

    public String getResult() {
        return this.result;
    }

    public String getReturnUrl() {
        return this.returnUrl;
    }

    public void setMemo(String str) {
        this.memo = str;
    }

    public void setResult(String str) {
        this.result = str;
    }

    public void setResultStatus(String str) {
        this.resultStatus = str;
    }

    public void setReturnUrl(String str) {
        this.returnUrl = str;
    }
}
