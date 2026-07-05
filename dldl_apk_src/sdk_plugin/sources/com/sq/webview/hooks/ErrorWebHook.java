package com.sq.webview.hooks;

import android.webkit.WebView;
import com.sq.webview.SimpleWebHook;
import com.sq.webview.util.WebLogUtil;
import com.sq.webview.view.IWebViewError;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class ErrorWebHook extends SimpleWebHook {
    private final IWebViewError mWebViewError;

    public ErrorWebHook(IWebViewError webViewError) {
        this.mWebViewError = webViewError;
    }

    @Override // com.sq.webview.SimpleWebHook, com.sq.webview.WebHook
    public void onReceivedError(WebView webView, String url, int errorCode, String description) {
        super.onReceivedError(webView, url, errorCode, description);
        this.mWebViewError.showWebError(getContext(), url, description, errorCode);
        WebLogUtil.d("onReceivedError url : " + url + "  errorCode : " + errorCode + "  description : " + description);
    }
}
