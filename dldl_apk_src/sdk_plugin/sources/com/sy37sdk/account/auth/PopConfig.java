package com.sy37sdk.account.auth;

import com.taptap.sdk.db.constant.Common;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class PopConfig {
    private int code;
    private int needStop;
    private int remainingTime;
    private long timestamp;
    private String url;

    public int getRemainingTime() {
        return this.remainingTime;
    }

    public void setRemainingTime(int i) {
        this.remainingTime = i;
    }

    public boolean getNeedStop() {
        return this.needStop == 1;
    }

    public void setNeedStop(int i) {
        this.needStop = i;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String str) {
        this.url = str;
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int i) {
        this.code = i;
    }

    public boolean isShow() {
        int i = this.code;
        return i == 1 || i == 2;
    }

    public boolean isFocus() {
        return this.code == 2;
    }

    public long getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(long j) {
        this.timestamp = j;
    }

    public static PopConfig parseFromJson(JSONObject jSONObject) {
        PopConfig popConfig = new PopConfig();
        try {
            popConfig.setCode(jSONObject.optInt("code"));
            popConfig.setUrl(jSONObject.optString("url"));
            popConfig.setNeedStop(jSONObject.optInt("needStop"));
            popConfig.setRemainingTime(jSONObject.optInt("remainingTime"));
            popConfig.setRemainingTime(jSONObject.optInt("remainingTime"));
            popConfig.setTimestamp(jSONObject.optLong(Common.Predefined.SUB_TIMESTAMP));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return popConfig;
    }
}
