package com.parameters.bean;

import android.graphics.Bitmap;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class SystemShareBean {
    public Bitmap bitmap;
    public String shareImageUrl;
    public String shareLinkUrl;
    public String shareText;
    public String shareTitle;
    public int shareType;

    public static SystemShareBean parseFromJson(String str) {
        SystemShareBean systemShareBean = new SystemShareBean();
        try {
            JSONObject jSONObject = new JSONObject(str);
            systemShareBean.shareType = jSONObject.optInt("shareType");
            systemShareBean.shareTitle = jSONObject.optString("shareTitle");
            systemShareBean.shareLinkUrl = jSONObject.optString("shareLinkUrl");
            systemShareBean.shareTitle = jSONObject.optString("shareTitle");
            systemShareBean.shareImageUrl = jSONObject.optString("shareImageUrl");
            systemShareBean.shareText = jSONObject.optString("shareText");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return systemShareBean;
    }
}
