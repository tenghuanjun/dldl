package com.sy37sdk.account.config;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import com.sqwan.common.util.LogUtil;
import com.taptap.sdk.core.TapTapSdk;
import com.taptap.sdk.core.TapTapSdkOptions;
import com.taptap.sdk.review.TapTapReview;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class OpenReview {
    public static boolean TapTapInit;

    public static boolean toHaoYouKuaiBaoMarketDetails(Context context, String str) {
        try {
            Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("hykb://openTopic?type=gamedetail&gameId=" + str));
            intent.addFlags(268435456);
            context.startActivity(intent);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean toTapTapMarketDetails() {
        try {
            TapTapReview.openReview();
            return false;
        } catch (Exception e) {
            LogUtil.e("toTapTapMarketDetails异常：" + e);
            e.printStackTrace();
            return false;
        }
    }

    public static void initTapTap(Context context, String str, String str2) {
        if (TapTapInit) {
            return;
        }
        TapTapSdkOptions tapTapSdkOptions = new TapTapSdkOptions(str, str2, 0);
        tapTapSdkOptions.setEnableLog(false);
        TapTapSdk.init(context, tapTapSdkOptions);
        TapTapInit = true;
    }

    public static void getTapTapInfo(Context context) {
        String strOptString;
        String strOptString2;
        String strOptString3 = "";
        try {
            JSONObject jSONObject = new JSONObject(new JSONObject(ConfigManager.getInstance().getCommentInfo(context)).optString("url"));
            strOptString = jSONObject.optString("market");
            try {
                strOptString2 = jSONObject.optString("clientId");
                try {
                    strOptString3 = jSONObject.optString("clientToken");
                } catch (JSONException e) {
                    e = e;
                    e.printStackTrace();
                }
            } catch (JSONException e2) {
                e = e2;
                strOptString2 = "";
            }
        } catch (JSONException e3) {
            e = e3;
            strOptString = "";
            strOptString2 = strOptString;
        }
        if (strOptString.isEmpty() || !strOptString.equals("TapTap")) {
            return;
        }
        initTapTap(context, strOptString2, strOptString3);
    }
}
