package com.huya.berry.sdklivelist.help;

import android.content.Context;
import com.duowan.auk.ArkValue;
import com.huya.berry.gamesdk.utils.CommonUtil;
import com.huya.berry.gamesdk.utils.UIUtil;
import com.huya.berry.gamesdk.wup.WupHelper;
import com.huya.berry.webview.WebviewApi;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class LiveListHelper {
    public static void usercenter(Context context) {
        float dp;
        float dp2;
        String str = ArkValue.debuggable() ? "http://test.hd.huya.com/h5/gamesdkcenter/index.html" : "https://hd.huya.com/h5/gamesdkcenter/index.html";
        String version = WupHelper.getVersion();
        if (version.contains("SNAPSHOT")) {
            version = version.substring(0, version.indexOf("SNAPSHOT") - 1);
        }
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append("?isPortrait=");
        sb.append(CommonUtil.isScreenLandScape() ? "0" : "1");
        sb.append("&version=");
        sb.append(version);
        String string = sb.toString();
        if (CommonUtil.isScreenLandScape()) {
            dp = UIUtil.getDp(470.0f);
            dp2 = UIUtil.getDp(290.0f);
        } else {
            dp = UIUtil.getDp(310.0f);
            dp2 = UIUtil.getDp(406.0f);
        }
        WebviewApi.openWebview(context, "资料", string, true, true, dp, dp2);
    }

    public static void anchorRecruit(Context context, String str, float f, float f2) {
        WebviewApi.openWebview(context, "主播招募", str, false, true, f, f2);
    }

    public static void component(Context context, String str, String str2, float f, float f2) {
        WebviewApi.openWebview(context, str, str2, false, true, f, f2);
    }
}
