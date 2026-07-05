package com.parameters.bean;

import android.text.TextUtils;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class MiniProgramBean {
    public static final int SKIP_TYPE_SCHEME_URL = 2;
    public static final int SKIP_TYPE_WECHAT_SDK = 1;
    public String appId;
    public String miniProgramId;
    public String miniProgramPath;
    public String schemeUrl;
    public int skipType;

    public static MiniProgramBean parseToObject(String str) {
        MiniProgramBean miniProgramBean = new MiniProgramBean();
        if (TextUtils.isEmpty(str)) {
            return miniProgramBean;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            miniProgramBean.skipType = jSONObject.optInt("skip_type");
            miniProgramBean.appId = jSONObject.optString("appid");
            miniProgramBean.miniProgramId = jSONObject.optString("mini_program_id");
            miniProgramBean.miniProgramPath = jSONObject.optString("mini_program_path");
            miniProgramBean.schemeUrl = jSONObject.optString("scheme_url");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return miniProgramBean;
    }
}
