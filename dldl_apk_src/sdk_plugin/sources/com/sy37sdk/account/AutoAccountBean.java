package com.sy37sdk.account;

import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class AutoAccountBean {
    private String autostate;
    private String issave;
    private String msg;
    private String pwd;
    private String serror;
    private String ssuccess;
    private String title;
    private String uname;

    public String getAutostate() {
        return this.autostate;
    }

    public String getUname() {
        return this.uname;
    }

    public String getPwd() {
        return this.pwd;
    }

    public String getTitle() {
        return this.title;
    }

    public String getMsg() {
        return this.msg;
    }

    public String getSsuccess() {
        return this.ssuccess;
    }

    public String getSerror() {
        return this.serror;
    }

    public String getIssave() {
        return this.issave;
    }

    public boolean isAutoAccount() {
        return "1".equals(this.autostate);
    }

    public void setPwd(String str) {
        this.pwd = str;
    }

    public static AutoAccountBean fromJson(String str) throws JSONException {
        AutoAccountBean autoAccountBean = new AutoAccountBean();
        JSONObject jSONObject = new JSONObject(str);
        autoAccountBean.issave = jSONObject.optString("issave", "0");
        autoAccountBean.autostate = jSONObject.optString("autostate", "0");
        autoAccountBean.uname = jSONObject.optString("uname");
        autoAccountBean.pwd = jSONObject.optString("pwd");
        autoAccountBean.title = jSONObject.optString("title");
        autoAccountBean.msg = jSONObject.optString("msg");
        autoAccountBean.ssuccess = jSONObject.optString("ssuccess");
        return autoAccountBean;
    }
}
