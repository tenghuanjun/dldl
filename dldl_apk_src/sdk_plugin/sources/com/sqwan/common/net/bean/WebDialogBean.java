package com.sqwan.common.net.bean;

import android.text.TextUtils;
import com.sqwan.common.util.UrlUtils;
import java.net.URL;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class WebDialogBean {
    private boolean focus;
    private boolean retry;
    private int screenOrientation;
    private String webUrl;

    public String getWebUrl() {
        return this.webUrl;
    }

    public void setWebUrl(String str) {
        this.webUrl = str;
    }

    public boolean isFocus() {
        return this.focus;
    }

    public void setFocus(boolean z) {
        this.focus = z;
    }

    public boolean isRetry() {
        return this.retry;
    }

    public void setRetry(boolean z) {
        this.retry = z;
    }

    public int getScreenOrientation() {
        return this.screenOrientation;
    }

    public void setScreenOrientation(int i) {
        this.screenOrientation = i;
    }

    public String getWebPath() {
        if (TextUtils.isEmpty(this.webUrl)) {
            return "";
        }
        try {
            URL url = new URL(this.webUrl);
            return url.getProtocol() + "://" + url.getAuthority() + url.getPath();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static WebDialogBean parseWebDialogBean(String str) {
        WebDialogBean webDialogBean = new WebDialogBean();
        webDialogBean.setWebUrl(str);
        String valueFromUrlStrByParamName = UrlUtils.readValueFromUrlStrByParamName(str, "enforce");
        String valueFromUrlStrByParamName2 = UrlUtils.readValueFromUrlStrByParamName(str, "retry");
        String valueFromUrlStrByParamName3 = UrlUtils.readValueFromUrlStrByParamName(str, "portrait");
        if (TextUtils.equals("1", valueFromUrlStrByParamName3)) {
            webDialogBean.setScreenOrientation(1);
        } else if (TextUtils.equals("0", valueFromUrlStrByParamName3)) {
            webDialogBean.setScreenOrientation(0);
        } else {
            webDialogBean.setScreenOrientation(3);
        }
        webDialogBean.setRetry("1".equals(valueFromUrlStrByParamName2));
        webDialogBean.setFocus("1".equals(valueFromUrlStrByParamName));
        return webDialogBean;
    }
}
