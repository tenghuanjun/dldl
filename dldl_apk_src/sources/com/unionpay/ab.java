package com.unionpay;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes6.dex */
final class ab implements af {
    final /* synthetic */ WebViewJavascriptBridge a;
    private final String b;

    public ab(WebViewJavascriptBridge webViewJavascriptBridge, String str) {
        this.a = webViewJavascriptBridge;
        this.b = str;
    }

    @Override // com.unionpay.af
    public final void a(String str) {
        this.a._callbackJs(this.b, str);
    }
}
