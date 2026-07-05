package com.parameters.bean;

import android.text.TextUtils;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class WebDialogBean {
    private boolean showToolBar;
    private String url;

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String str) {
        this.url = str;
    }

    public boolean isShowToolBar() {
        return this.showToolBar;
    }

    public void setShowToolBar(boolean z) {
        this.showToolBar = z;
    }

    public static WebDialogBean parseToObject(String str) {
        WebDialogBean webDialogBean = new WebDialogBean();
        if (TextUtils.isEmpty(str)) {
            return webDialogBean;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            webDialogBean.setUrl(jSONObject.optString("url"));
            webDialogBean.setShowToolBar(jSONObject.optBoolean("showToolBar"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return webDialogBean;
    }
}
