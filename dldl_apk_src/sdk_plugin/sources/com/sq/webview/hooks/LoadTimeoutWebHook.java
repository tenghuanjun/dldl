package com.sq.webview.hooks;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;
import android.webkit.WebView;
import com.sq.webview.SimpleWebHook;
import com.sq.webview.util.WebLogUtil;
import com.sq.webview.view.ILoadTimeout;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class LoadTimeoutWebHook extends SimpleWebHook {
    private static final int DEFAULT_TIMEOUT = 15000;
    private final Handler mHandler;
    private final ILoadTimeout mLoadTimeout;
    private final Runnable mLoadTimeoutRunnable;
    private boolean mStartCountDown;
    private final int mTimeout;

    public LoadTimeoutWebHook(ILoadTimeout loading) {
        this(loading, 15000);
    }

    public LoadTimeoutWebHook(ILoadTimeout loading, int timeout) {
        this.mLoadTimeoutRunnable = new Runnable() { // from class: com.sq.webview.hooks.LoadTimeoutWebHook.1
            @Override // java.lang.Runnable
            public void run() {
                if (LoadTimeoutWebHook.this.mLoadTimeout != null) {
                    LoadTimeoutWebHook.this.mLoadTimeout.onLoadTimeout();
                }
            }
        };
        this.mLoadTimeout = loading;
        this.mHandler = new Handler(Looper.getMainLooper());
        this.mTimeout = timeout;
    }

    public void startCountDown() {
        if (!this.mStartCountDown) {
            this.mHandler.postDelayed(this.mLoadTimeoutRunnable, this.mTimeout);
            this.mStartCountDown = true;
        } else {
            WebLogUtil.w("LoadTimeoutWebHook is counting down");
        }
    }

    @Override // com.sq.webview.SimpleWebHook, com.sq.webview.WebHook
    public void onPageStarted(WebView webView, String url, Bitmap favicon) {
        super.onPageStarted(webView, url, favicon);
        startCountDown();
    }

    @Override // com.sq.webview.SimpleWebHook, com.sq.webview.WebHook
    public void onPageFinished(WebView webView, String url) {
        super.onPageFinished(webView, url);
        this.mHandler.removeCallbacks(this.mLoadTimeoutRunnable);
        this.mStartCountDown = false;
    }

    @Override // com.sq.webview.SimpleWebHook, com.sq.webview.WebHook
    public void onReceivedError(WebView webView, String url, int errorCode, String description) {
        super.onReceivedError(webView, url, errorCode, description);
        this.mHandler.removeCallbacks(this.mLoadTimeoutRunnable);
        this.mStartCountDown = false;
    }
}
