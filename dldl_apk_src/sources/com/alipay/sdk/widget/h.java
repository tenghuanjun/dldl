package com.alipay.sdk.widget;

import android.app.Activity;
import android.content.Context;
import android.webkit.WebSettings;
import android.webkit.WebView;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public class h extends g {
    private com.alipay.sdk.app.b b;
    private WebView c;

    public h(Activity activity) {
        super(activity);
        WebView webView = new WebView(activity);
        this.c = webView;
        a(webView, activity);
        addView(this.c);
        com.alipay.sdk.app.b bVar = new com.alipay.sdk.app.b(activity);
        this.b = bVar;
        this.c.setWebViewClient(bVar);
    }

    @Override // com.alipay.sdk.widget.g
    public boolean b() {
        if (this.c.canGoBack()) {
            if (!this.b.b()) {
                return true;
            }
            com.alipay.sdk.app.k kVarB = com.alipay.sdk.app.k.b(com.alipay.sdk.app.k.NETWORK_ERROR.a());
            com.alipay.sdk.app.j.a(com.alipay.sdk.app.j.a(kVarB.a(), kVarB.b(), ""));
            this.a.finish();
            return true;
        }
        com.alipay.sdk.app.j.a(com.alipay.sdk.app.j.c());
        this.a.finish();
        return true;
    }

    @Override // com.alipay.sdk.widget.g
    public void a() {
        this.b.a();
        removeAllViews();
    }

    private void a(WebView webView, Context context) {
        WebSettings settings = this.c.getSettings();
        settings.setUserAgentString(settings.getUserAgentString() + com.alipay.sdk.util.n.c(context));
        settings.setRenderPriority(WebSettings.RenderPriority.HIGH);
        settings.setSupportMultipleWindows(true);
        settings.setJavaScriptEnabled(true);
        settings.setSavePassword(false);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setMinimumFontSize(settings.getMinimumFontSize() + 8);
        settings.setAllowFileAccess(false);
        settings.setTextSize(WebSettings.TextSize.NORMAL);
        settings.setDomStorageEnabled(true);
        settings.setCacheMode(1);
        this.c.resumeTimers();
        this.c.setVerticalScrollbarOverlay(true);
        this.c.setDownloadListener(new i(this));
        try {
            try {
                this.c.removeJavascriptInterface("searchBoxJavaBridge_");
                this.c.removeJavascriptInterface("accessibility");
                this.c.removeJavascriptInterface("accessibilityTraversal");
            } catch (Throwable unused) {
                Method method = this.c.getClass().getMethod("removeJavascriptInterface", new Class[0]);
                if (method != null) {
                    method.invoke(this.c, "searchBoxJavaBridge_");
                    method.invoke(this.c, "accessibility");
                    method.invoke(this.c, "accessibilityTraversal");
                }
            }
        } catch (Throwable unused2) {
        }
    }

    @Override // com.alipay.sdk.widget.g
    public void a(String str) {
        this.c.loadUrl(str);
    }
}
