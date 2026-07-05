package com.sqwan.common.webview;

import com.sq.webview.SimpleWebHook;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class UrlWebHook extends SimpleWebHook {
    /* JADX WARN: Removed duplicated region for block: B:16:0x0030  */
    @Override // com.sq.webview.SimpleWebHook, com.sq.webview.WebHook
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean shouldOverrideUrlLoading(android.webkit.WebView r7, java.lang.String r8) {
        /*
            r6 = this;
            android.net.Uri r0 = android.net.Uri.parse(r8)
            java.lang.String r0 = r0.getScheme()
            r1 = 0
            if (r0 != 0) goto Lc
            return r1
        Lc:
            r2 = -1
            int r3 = r0.hashCode()
            r4 = 3213448(0x310888, float:4.503E-39)
            r5 = 1
            if (r3 == r4) goto L27
            r1 = 99617003(0x5f008eb, float:2.2572767E-35)
            if (r3 == r1) goto L1d
            goto L30
        L1d:
            java.lang.String r1 = "https"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L30
            r1 = 1
            goto L31
        L27:
            java.lang.String r3 = "http"
            boolean r0 = r0.equals(r3)
            if (r0 == 0) goto L30
            goto L31
        L30:
            r1 = -1
        L31:
            if (r1 == 0) goto L36
            if (r1 == r5) goto L36
            goto L39
        L36:
            r7.loadUrl(r8)
        L39:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sqwan.common.webview.UrlWebHook.shouldOverrideUrlLoading(android.webkit.WebView, java.lang.String):boolean");
    }
}
