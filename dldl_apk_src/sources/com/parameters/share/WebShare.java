package com.parameters.share;

import android.graphics.Bitmap;
import com.tencent.open.SocialConstants;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes6.dex */
public class WebShare {
    public static final String JS_SHARE_CHANNEL_MOMENT = "moments";
    public static final String JS_SHARE_CHANNEL_QQ = "qq";
    public static final String JS_SHARE_CHANNEL_WECAHT = "wechat";
    public static final int JS_SHARE_TYPE_H5 = 2;
    public static final int JS_SHARE_TYPE_IMG = 1;
    private Bitmap bitmap;
    private String desc;
    private String img;
    private String landingPageUrl;
    private String title;
    private int type;
    private String way;

    public Bitmap getBitmap() {
        return this.bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int i) {
        this.type = i;
    }

    public String getWay() {
        return this.way;
    }

    public void setWay(String str) {
        this.way = str;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public String getDesc() {
        return this.desc;
    }

    public void setDesc(String str) {
        this.desc = str;
    }

    public String getLandingPageUrl() {
        return this.landingPageUrl;
    }

    public void setLandingPageUrl(String str) {
        this.landingPageUrl = str;
    }

    public String getImg() {
        return this.img;
    }

    public void setImg(String str) {
        this.img = str;
    }

    public static WebShare parseFromJson(String str) {
        WebShare webShare = new WebShare();
        try {
            JSONObject jSONObject = new JSONObject(str);
            int iOptInt = jSONObject.optInt("type");
            String strOptString = jSONObject.optString("way");
            String strOptString2 = jSONObject.optString("title");
            String strOptString3 = jSONObject.optString(SocialConstants.PARAM_APP_DESC);
            String strOptString4 = jSONObject.optString("landingPageUrl");
            String strOptString5 = jSONObject.optString(SocialConstants.PARAM_IMG_URL);
            webShare.setWay(strOptString);
            webShare.setType(iOptInt);
            webShare.setTitle(strOptString2);
            webShare.setDesc(strOptString3);
            webShare.setLandingPageUrl(strOptString4);
            webShare.setImg(strOptString5);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return webShare;
    }

    public static String parseToJson(WebShare webShare) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.putOpt("type", Integer.valueOf(webShare.getType()));
            jSONObject.putOpt("way", webShare.getWay());
            jSONObject.putOpt("title", webShare.getTitle());
            jSONObject.putOpt(SocialConstants.PARAM_APP_DESC, webShare.getDesc());
            jSONObject.putOpt("landingPageUrl", webShare.getLandingPageUrl());
            jSONObject.putOpt(SocialConstants.PARAM_IMG_URL, webShare.getImg());
            return jSONObject.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
