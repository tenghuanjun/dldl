package com.sq.webview.hooks;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;
import android.webkit.WebView;
import com.sq.webview.SimpleWebHook;
import com.sq.webview.util.WebLogUtil;
import com.sq.webview.view.IWebViewLoading;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class ProgressWebHook extends SimpleWebHook {
    private static final int DEFAULT_TIMEOUT = 15000;
    private final Handler mHandler;
    private boolean mHasFinishLoading;
    private boolean mHasShown;
    private final Runnable mLoadTimeoutRunnable;
    private final int mTimeout;
    private final IWebViewLoading mWebViewLoading;

    public ProgressWebHook(IWebViewLoading loading) {
        this(loading, 15000);
    }

    public ProgressWebHook(IWebViewLoading loading, int timeout) {
        this.mHasFinishLoading = true;
        this.mHasShown = false;
        this.mLoadTimeoutRunnable = new Runnable() { // from class: com.sq.webview.hooks.-$$Lambda$ProgressWebHook$kszleog2ayuuQtP-MHgaTa3IIJE
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.stopLoading();
            }
        };
        this.mWebViewLoading = loading;
        this.mHandler = new Handler(Looper.getMainLooper());
        this.mTimeout = timeout;
    }

    @Override // com.sq.webview.SimpleWebHook, com.sq.webview.WebHook
    public void onPageStarted(WebView webView, String url, Bitmap favicon) {
        super.onPageStarted(webView, url, favicon);
        startLoading();
    }

    @Override // com.sq.webview.SimpleWebHook, com.sq.webview.WebHook
    public void onPageFinished(WebView webView, String url) {
        super.onPageFinished(webView, url);
        stopLoading();
    }

    @Override // com.sq.webview.SimpleWebHook, com.sq.webview.WebHook
    public void onReceivedError(WebView webView, String url, int errorCode, String description) {
        super.onReceivedError(webView, url, errorCode, description);
        stopLoading();
    }

    @Override // com.sq.webview.SimpleWebHook, com.sq.webview.WebHook
    public void onProgressChanged(WebView webView, int newProgress) {
        super.onProgressChanged(webView, newProgress);
        this.mWebViewLoading.onProgress(getContext(), newProgress);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void stopLoading() {
        if (this.mHasFinishLoading) {
            return;
        }
        this.mWebViewLoading.stopLoading(getContext());
        WebLogUtil.d("ProgressWebHook onStopLoading");
        this.mHasFinishLoading = true;
    }

    public void startLoading() {
        if (this.mHasShown) {
            WebLogUtil.i("loading just show once, return.");
            return;
        }
        this.mHasFinishLoading = false;
        this.mHasShown = true;
        WebLogUtil.d("ProgressWebHook onStartLoading");
        this.mWebViewLoading.startLoading(getContext());
        this.mHandler.postDelayed(this.mLoadTimeoutRunnable, this.mTimeout);
    }
}
