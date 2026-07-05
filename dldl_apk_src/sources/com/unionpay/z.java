package com.unionpay;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes6.dex */
final class z implements Runnable {
    final /* synthetic */ ae a;
    final /* synthetic */ String b;
    final /* synthetic */ af c;
    final /* synthetic */ WebViewJavascriptBridge d;

    z(WebViewJavascriptBridge webViewJavascriptBridge, ae aeVar, String str, af afVar) {
        this.d = webViewJavascriptBridge;
        this.a = aeVar;
        this.b = str;
        this.c = afVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        ae aeVar = this.a;
        if (aeVar != null) {
            aeVar.a(this.b, this.c);
        }
    }
}
