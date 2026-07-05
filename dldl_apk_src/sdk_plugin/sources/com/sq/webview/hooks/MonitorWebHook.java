package com.sq.webview.hooks;

import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Build;
import android.os.SystemClock;
import android.text.TextUtils;
import android.webkit.SslErrorHandler;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import com.sq.webview.SimpleWebHook;
import com.sq.webview.report.WebErrorReporter;
import com.sq.webview.report.WebEventReporter;
import com.sq.webview.report.WebViewTrackManager;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class MonitorWebHook extends SimpleWebHook {
    private static final Map<String, Long> LOAD_CONSUMING_MAP = new HashMap();
    private boolean mCollectWhiteScreenEnable = true;
    private final WebViewTrackManager mWebViewTrackManager;

    public MonitorWebHook(WebErrorReporter webErrorReporter, WebEventReporter webEventReporter, Executor executor) {
        WebViewTrackManager webViewTrackManager = new WebViewTrackManager(webErrorReporter, webEventReporter);
        this.mWebViewTrackManager = webViewTrackManager;
        webViewTrackManager.setExecutor(executor);
    }

    @Override // com.sq.webview.SimpleWebHook, com.sq.webview.WebHook
    public void onPageStarted(WebView webView, String url, Bitmap favicon) {
        super.onPageStarted(webView, url, favicon);
        if (TextUtils.isEmpty(url)) {
            return;
        }
        LOAD_CONSUMING_MAP.put(getKey(url), Long.valueOf(SystemClock.uptimeMillis()));
    }

    @Override // com.sq.webview.SimpleWebHook, com.sq.webview.WebHook
    public void onPageFinished(WebView webView, String url) {
        Long lRemove;
        super.onPageFinished(webView, url);
        if (TextUtils.isEmpty(url) || (lRemove = LOAD_CONSUMING_MAP.remove(getKey(url))) == null) {
            return;
        }
        this.mWebViewTrackManager.trackWebLoadFinish(webView, url, SystemClock.uptimeMillis() - lRemove.longValue());
        if (this.mCollectWhiteScreenEnable) {
            this.mWebViewTrackManager.trackWebLoadWhiteScreenError(webView, url);
        }
    }

    @Override // com.sq.webview.SimpleWebHook, com.sq.webview.WebHook
    public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
        super.onReceivedSslError(view, handler, error);
        this.mWebViewTrackManager.trackWebLoadSslError(view, view.getUrl(), error.getPrimaryError());
    }

    @Override // com.sq.webview.SimpleWebHook, com.sq.webview.WebHook
    public void onReceivedError(WebView webView, String url, int errorCode, String description) {
        super.onReceivedError(webView, url, errorCode, description);
        this.mWebViewTrackManager.trackWebLoadFail(webView, url, errorCode, description);
    }

    @Override // com.sq.webview.SimpleWebHook, com.sq.webview.WebHook
    public void onReceivedHttpError(WebView view, WebResourceRequest request, WebResourceResponse errorResponse) {
        super.onReceivedHttpError(view, request, errorResponse);
        if (Build.VERSION.SDK_INT < 21) {
            return;
        }
        this.mWebViewTrackManager.trackWebHttpCodeError(view, String.valueOf(request.getUrl()), errorResponse.getStatusCode());
    }

    public void setCollectWhiteScreenEnable(boolean enable) {
        this.mCollectWhiteScreenEnable = enable;
    }

    private String getKey(String url) {
        Uri uri = Uri.parse(url);
        return uri.getScheme() + "://" + uri.getHost() + uri.getPath();
    }
}
