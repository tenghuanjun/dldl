package com.sy37sdk.share.bean;

import android.graphics.Bitmap;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
public class ShareBean {
    private Bitmap bitmap;
    private String desc;
    private String img;
    private String landingPageUrl;
    private List<Integer> sharePlatforms;
    private String text;
    private String title;
    private int type = 1;

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

    public String getImg() {
        return this.img;
    }

    public void setImg(String str) {
        this.img = str;
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

    public List<Integer> getShareWays() {
        return this.sharePlatforms;
    }

    public void setShareWays(List<Integer> list) {
        this.sharePlatforms = list;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String str) {
        this.text = str;
    }

    public void parse(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            String strOptString = jSONObject.optString("img");
            String strOptString2 = jSONObject.optString("title");
            String strOptString3 = jSONObject.optString("desc");
            String strOptString4 = jSONObject.optString("landingPageUrl");
            setDesc(strOptString3);
            setImg(strOptString);
            setTitle(strOptString2);
            setLandingPageUrl(strOptString4);
            parseShareType(jSONObject);
            parseShareWay(jSONObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void parseShareType(JSONObject jSONObject) {
        try {
            if (Integer.parseInt(jSONObject.optString("type")) == 2) {
                setType(2);
            } else {
                setType(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
            setType(1);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x0052  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void parseShareWay(org.json.JSONObject r10) {
        /*
            r9 = this;
            java.util.ArrayList r0 = new java.util.ArrayList     // Catch: java.lang.Exception -> L63
            r0.<init>()     // Catch: java.lang.Exception -> L63
            java.lang.String r1 = "way"
            org.json.JSONArray r10 = r10.optJSONArray(r1)     // Catch: java.lang.Exception -> L63
            r1 = 0
            r2 = 0
        Ld:
            int r3 = r10.length()     // Catch: java.lang.Exception -> L63
            if (r2 >= r3) goto L5f
            java.lang.Object r3 = r10.get(r2)     // Catch: java.lang.Exception -> L63
            java.lang.String r3 = (java.lang.String) r3     // Catch: java.lang.Exception -> L63
            r4 = -1
            int r5 = r3.hashCode()     // Catch: java.lang.Exception -> L63
            r6 = -791770330(0xffffffffd0ce8b26, float:-2.7721806E10)
            r7 = 2
            r8 = 1
            if (r5 == r6) goto L43
            r6 = 3616(0xe20, float:5.067E-42)
            if (r5 == r6) goto L39
            r6 = 1235271283(0x49a0be73, float:1316814.4)
            if (r5 == r6) goto L2f
            goto L4c
        L2f:
            java.lang.String r5 = "moments"
            boolean r3 = r3.equals(r5)     // Catch: java.lang.Exception -> L63
            if (r3 == 0) goto L4c
            r4 = 1
            goto L4c
        L39:
            java.lang.String r5 = "qq"
            boolean r3 = r3.equals(r5)     // Catch: java.lang.Exception -> L63
            if (r3 == 0) goto L4c
            r4 = 2
            goto L4c
        L43:
            java.lang.String r5 = "wechat"
            boolean r3 = r3.equals(r5)     // Catch: java.lang.Exception -> L63
            if (r3 == 0) goto L4c
            r4 = 0
        L4c:
            if (r4 == 0) goto L52
            if (r4 == r8) goto L54
            if (r4 == r7) goto L55
        L52:
            r7 = 0
            goto L55
        L54:
            r7 = 1
        L55:
            java.lang.Integer r3 = java.lang.Integer.valueOf(r7)     // Catch: java.lang.Exception -> L63
            r0.add(r3)     // Catch: java.lang.Exception -> L63
            int r2 = r2 + 1
            goto Ld
        L5f:
            r9.setShareWays(r0)     // Catch: java.lang.Exception -> L63
            goto L67
        L63:
            r10 = move-exception
            r10.printStackTrace()
        L67:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sy37sdk.share.bean.ShareBean.parseShareWay(org.json.JSONObject):void");
    }
}
