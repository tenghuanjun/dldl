package com.sy37sdk.account.auth;

import com.sqwan.common.mod.ModHelper;
import com.sqwan.common.mod.config.IConfigMod;
import com.taptap.sdk.db.constant.Common;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class AuthBean {
    public static int DEFAULT_INTERVAL = 1;
    private int age;
    private int banshuAuthState;
    private int code;
    private String durl;
    public boolean isAgeLimited;
    private int isAuth;
    private boolean isPlayableDate;
    private int needAccumulateDuration;
    private int needAddiction;
    private String playableTimerange;
    private String timerangeLimtUrl;
    private long timestamp;
    private String url;
    private int interval = DEFAULT_INTERVAL;
    private int allowRecharge = 1;
    private int remainingTime = 0;
    private int needReportOnline = 0;
    public String ageLimitedMsg = "";

    public int getNeedReportOnline() {
        return this.needReportOnline;
    }

    public void setNeedReportOnline(int i) {
        this.needReportOnline = i;
    }

    public int getRemainingTime() {
        return this.remainingTime;
    }

    public void setRemainingTime(int i) {
        this.remainingTime = i;
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int i) {
        this.code = i;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String str) {
        this.url = str;
    }

    public String getDurl() {
        return this.durl;
    }

    public void setDurl(String str) {
        this.durl = str;
    }

    public boolean getNeedAddiction() {
        return this.needAddiction == 1;
    }

    public void setNeedAddiction(int i) {
        this.needAddiction = i;
    }

    public boolean getNeedAccumulateDuration() {
        return this.needAccumulateDuration == 1;
    }

    public void setNeedAccumulateDuration(int i) {
        this.needAccumulateDuration = i;
    }

    public boolean getIsAuth() {
        return this.isAuth == 1;
    }

    public void setIsAuth(int i) {
        this.isAuth = i;
    }

    public boolean needAuth() {
        int i = this.code;
        return i == 1 || i == 2;
    }

    public boolean isFocus() {
        return this.code == 2;
    }

    public int getInterval() {
        return this.interval;
    }

    public void setInterval(int i) {
        this.interval = i;
    }

    public boolean getAllowRecharge() {
        return this.allowRecharge == 1;
    }

    public void setAllowRecharge(int i) {
        this.allowRecharge = i;
    }

    public String getTimerangeLimtUrl() {
        return this.timerangeLimtUrl;
    }

    public void setTimerangeLimtUrl(String str) {
        this.timerangeLimtUrl = str;
    }

    public long getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(long j) {
        this.timestamp = j;
        IConfigMod config = ModHelper.getConfig();
        if (config != null) {
            config.getCommonConfig().setTimestamp(j);
        }
    }

    public boolean isPlayableDate() {
        return this.isPlayableDate;
    }

    public void setPlayableDate(boolean z) {
        this.isPlayableDate = z;
    }

    public String getPlayableTimerange() {
        return this.playableTimerange;
    }

    public void setPlayableTimerange(String str) {
        this.playableTimerange = str;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int i) {
        this.age = i;
    }

    public int getBanshuAuthState() {
        return this.banshuAuthState;
    }

    public void setBanshuAuthState(int i) {
        this.banshuAuthState = i;
    }

    public static AuthBean parseFromJson(JSONObject jSONObject) {
        AuthBean authBean = new AuthBean();
        try {
            authBean.setCode(jSONObject.optInt("code"));
            authBean.setUrl(jSONObject.optString("url"));
            authBean.setDurl(jSONObject.optString("durl"));
            authBean.setNeedAddiction(jSONObject.optInt("needAddiction"));
            authBean.setNeedAccumulateDuration(jSONObject.optInt("needAccumulateDuration"));
            authBean.setIsAuth(jSONObject.optInt("isAuth"));
            authBean.setInterval(jSONObject.optInt("interval"));
            authBean.setAllowRecharge(jSONObject.optInt("allowRecharge"));
            int iOptInt = jSONObject.optInt("remainingTime");
            if (iOptInt > 0) {
                iOptInt += 120;
            }
            authBean.setRemainingTime(iOptInt);
            authBean.setNeedReportOnline(jSONObject.optInt("needReportOnline"));
            authBean.isAgeLimited = jSONObject.optInt("isAgeLimited") == 1;
            authBean.ageLimitedMsg = jSONObject.optString("ageLimitedMsg");
            authBean.setTimerangeLimtUrl(jSONObject.optString("timerangeLimtUrl"));
            authBean.setTimestamp(jSONObject.optLong(Common.Predefined.SUB_TIMESTAMP));
            authBean.setPlayableDate(jSONObject.optInt("isPlayableDate") == 1);
            authBean.setPlayableTimerange(jSONObject.optString("playableTimerange"));
            authBean.setAge(jSONObject.optInt("age", -1));
            authBean.setBanshuAuthState(jSONObject.optInt("banshuAuthState", -1));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return authBean;
    }

    public boolean isNeddReportOnline() {
        return getNeedReportOnline() == 1;
    }
}
