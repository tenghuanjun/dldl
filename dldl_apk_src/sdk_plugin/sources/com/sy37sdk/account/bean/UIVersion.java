package com.sy37sdk.account.bean;

import android.text.TextUtils;
import com.sqwan.common.util.LogUtil;
import com.sqwan.msdk.config.MultiSdkManager;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class UIVersion {
    public static final int FREGISTER_CLOSE = 0;
    public static final int FREGISTER_NEW = 2;
    public static final int FREGISTER_OLD = 1;
    public static final int UI_VERSSION_1 = 1;
    public static final int UI_VERSSION_2 = 2;
    public static final int UI_VERSSION_3 = 3;
    private int ui = 2;
    private int fregister = 2;

    public int getUi() {
        if (TextUtils.isEmpty(MultiSdkManager.getInstance().getScut3())) {
            return this.ui;
        }
        return 2;
    }

    public int getFregister() {
        if (TextUtils.isEmpty(MultiSdkManager.getInstance().getScut3())) {
            return this.fregister;
        }
        return 0;
    }

    public static UIVersion fromJson(String str) {
        UIVersion uIVersion = new UIVersion();
        try {
            JSONObject jSONObject = new JSONObject(str);
            uIVersion.ui = jSONObject.optInt("ui", 2);
            uIVersion.fregister = jSONObject.optInt("fregister", 2);
        } catch (JSONException e) {
            LogUtil.e("ui版本控制数据解析异常！");
            e.printStackTrace();
        }
        return uIVersion;
    }

    public String toString() {
        return "【UIVersion】-->  ui : " + this.ui + ", fregister : " + this.fregister;
    }
}
