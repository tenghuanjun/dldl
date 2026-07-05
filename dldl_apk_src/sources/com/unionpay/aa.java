package com.unionpay;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes6.dex */
final class aa implements Runnable {
    final /* synthetic */ String a;
    final /* synthetic */ WebViewJavascriptBridge b;

    aa(WebViewJavascriptBridge webViewJavascriptBridge, String str) {
        this.b = webViewJavascriptBridge;
        this.a = str;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.b.mWebView.loadUrl(this.a);
    }
}
