package com.huya.berry.webview;

import android.content.Context;
import android.content.Intent;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class WebviewApi {
    private static Callback sCallback;

    public interface Callback {
        void bindFinish(String str);

        void closeLoading();

        void logout();

        void showLogin();

        void showModifyNickname();
    }

    public static void init(Callback callback) {
        sCallback = callback;
    }

    public static Callback getCallback() {
        return sCallback;
    }

    public static void qqAuthorization(Context context, String str) {
        openWebview(context, "QQ授权", str, false, false);
    }

    public static void openWebview(Context context, String str, String str2, boolean z) {
        openWebview(context, str, str2, z, true);
    }

    public static void openWebview(Context context, String str, String str2, boolean z, boolean z2) {
        openWebview(context, str, str2, z, z2, -1.0f, -1.0f);
    }

    public static void openWebview(Context context, String str, String str2, boolean z, boolean z2, float f, float f2) {
        try {
            Intent intent = new Intent(context, (Class<?>) WebViewActivity.class);
            intent.setFlags(268435456);
            WebViewInfo webViewInfo = new WebViewInfo();
            webViewInfo.mTitle = str;
            webViewInfo.mUrl = str2;
            webViewInfo.isBindLogin = z;
            webViewInfo.isBusiurlBindLogin = z2;
            webViewInfo.width = f;
            webViewInfo.height = f2;
            intent.putExtra(WebViewActivity.WEBVIEW_INFO, webViewInfo);
            context.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
