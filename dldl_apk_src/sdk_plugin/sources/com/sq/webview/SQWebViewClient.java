package com.sq.webview;

import android.graphics.Bitmap;
import android.net.http.SslError;
import android.webkit.SslErrorHandler;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class SQWebViewClient extends WebViewClient {
    private boolean mPageStarted;
    private final WebHookDispatcher mWebHookDispatcher;

    public SQWebViewClient(WebHookDispatcher webHookDispatcher) {
        if (webHookDispatcher == null) {
            this.mWebHookDispatcher = new WebHookDispatcher();
        } else {
            this.mWebHookDispatcher = webHookDispatcher;
        }
    }

    @Override // android.webkit.WebViewClient
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        if (this.mWebHookDispatcher.shouldOverrideUrlLoading(view, url)) {
            return true;
        }
        return super.shouldOverrideUrlLoading(view, url);
    }

    @Override // android.webkit.WebViewClient
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
        this.mWebHookDispatcher.onPageFinished(view, url);
        if (view.getProgress() == 100) {
            this.mWebHookDispatcher.onPageFinishedOnce(view, url);
            this.mPageStarted = false;
        }
    }

    @Override // android.webkit.WebViewClient
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);
        this.mWebHookDispatcher.onPageStarted(view, url, favicon);
        if (this.mPageStarted) {
            return;
        }
        this.mPageStarted = true;
        this.mWebHookDispatcher.onPageStartedOnce(view, url, favicon);
    }

    @Override // android.webkit.WebViewClient
    public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
        super.onReceivedError(view, errorCode, description, failingUrl);
        this.mWebHookDispatcher.onReceivedError(view, failingUrl, errorCode, description);
    }

    @Override // android.webkit.WebViewClient
    public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
        super.onReceivedError(view, request, error);
        this.mWebHookDispatcher.onReceivedError(view, request, error);
    }

    @Override // android.webkit.WebViewClient
    public void onReceivedHttpError(WebView view, WebResourceRequest request, WebResourceResponse errorResponse) {
        super.onReceivedHttpError(view, request, errorResponse);
        this.mWebHookDispatcher.onReceivedHttpError(view, request, errorResponse);
    }

    @Override // android.webkit.WebViewClient
    public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
        WebResourceResponse webResourceResponseShouldInterceptRequest = this.mWebHookDispatcher.shouldInterceptRequest(view, request);
        return webResourceResponseShouldInterceptRequest != null ? webResourceResponseShouldInterceptRequest : super.shouldInterceptRequest(view, request);
    }

    @Override // android.webkit.WebViewClient
    public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
        this.mWebHookDispatcher.onReceivedSslError(view, handler, error);
        super.onReceivedSslError(view, handler, error);
    }

    @Override // android.webkit.WebViewClient
    public void onLoadResource(WebView view, String url) {
        this.mWebHookDispatcher.onLoadResource(view, url);
        super.onLoadResource(view, url);
    }
}
