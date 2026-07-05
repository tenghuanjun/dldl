package com.sqwan.common.dialog.pop;

import com.sqwan.common.util.UrlUtils;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class PopupDialogBean {
    private String id;
    private String url;
    private int enforce = 0;
    private int action_id = 0;

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String str) {
        this.url = str;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String str) {
        this.id = str;
    }

    public int getEnforce() {
        return this.enforce;
    }

    public void setEnforce(int i) {
        this.enforce = i;
    }

    public int getAction_id() {
        return this.action_id;
    }

    public void setAction_id(int i) {
        this.action_id = i;
    }

    public boolean isForce() {
        return this.enforce == 1;
    }

    public static PopupDialogBean decodeFromJson(JSONObject jSONObject) {
        PopupDialogBean popupDialogBean = new PopupDialogBean();
        String strOptString = jSONObject.optString("url");
        popupDialogBean.setUrl(strOptString);
        popupDialogBean.setId(UrlUtils.readValueFromUrlStrByParamName(strOptString, "pop_id"));
        popupDialogBean.setEnforce(jSONObject.optInt("enforce"));
        popupDialogBean.setAction_id(jSONObject.optInt("action_id"));
        return popupDialogBean;
    }
}
