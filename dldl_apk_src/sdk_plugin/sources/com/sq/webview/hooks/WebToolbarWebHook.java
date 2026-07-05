package com.sq.webview.hooks;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import com.sq.webview.SimpleWebHook;
import com.sq.webview.util.ViewUtil;
import com.sq.webview.util.WebLogUtil;
import com.sq.webview.view.IWebToolBar;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class WebToolbarWebHook extends SimpleWebHook {
    private static final int DEFAULT_TIMEOUT = 10000;
    private final Handler mHandler;
    private final IWebToolBar mIWebToolBar;
    private final Runnable mLoadTimeoutRunnable;
    private boolean mStartCountDown;
    private final int mTimeout;

    public WebToolbarWebHook(IWebToolBar webOperation) {
        this(webOperation, 10000);
    }

    public WebToolbarWebHook(IWebToolBar webOperation, int timeout) {
        this.mLoadTimeoutRunnable = new Runnable() { // from class: com.sq.webview.hooks.-$$Lambda$WebToolbarWebHook$G4zXENfpnNqKcQfRhlWo0MDaQlI
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.showToolbarIfNeed();
            }
        };
        this.mIWebToolBar = webOperation;
        this.mHandler = new Handler(Looper.getMainLooper());
        this.mTimeout = timeout;
        if (this.mIWebToolBar.getRefreshView() != null) {
            final View.OnClickListener onClickListener = ViewUtil.getOnClickListener(this.mIWebToolBar.getRefreshView());
            this.mIWebToolBar.getRefreshView().setOnClickListener(new View.OnClickListener() { // from class: com.sq.webview.hooks.-$$Lambda$WebToolbarWebHook$PGUjpGgRnDAR9GbooDzdBUbn4Bw
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f$0.lambda$new$0$WebToolbarWebHook(onClickListener, view);
                }
            });
        }
        if (this.mIWebToolBar.getBackView() != null) {
            final View.OnClickListener onClickListener2 = ViewUtil.getOnClickListener(this.mIWebToolBar.getBackView());
            this.mIWebToolBar.getBackView().setOnClickListener(new View.OnClickListener() { // from class: com.sq.webview.hooks.-$$Lambda$WebToolbarWebHook$rlm-7hdydPVStJLBKX5Ytp0YL1U
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f$0.lambda$new$1$WebToolbarWebHook(onClickListener2, view);
                }
            });
        }
        if (this.mIWebToolBar.getForwardView() != null) {
            final View.OnClickListener onClickListener3 = ViewUtil.getOnClickListener(this.mIWebToolBar.getForwardView());
            this.mIWebToolBar.getForwardView().setOnClickListener(new View.OnClickListener() { // from class: com.sq.webview.hooks.-$$Lambda$WebToolbarWebHook$3GU5gzzEc15FT-qm1UubYvr-mQs
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f$0.lambda$new$2$WebToolbarWebHook(onClickListener3, view);
                }
            });
        }
    }

    public /* synthetic */ void lambda$new$0$WebToolbarWebHook(final View.OnClickListener originalClickListener, View v) {
        if (originalClickListener != null) {
            originalClickListener.onClick(v);
        }
        this.mWebView.reload();
    }

    public /* synthetic */ void lambda$new$1$WebToolbarWebHook(final View.OnClickListener originalClickListener, View v) {
        if (originalClickListener != null) {
            originalClickListener.onClick(v);
        }
        if (this.mWebView.canGoBack()) {
            this.mWebView.goBack();
        }
    }

    public /* synthetic */ void lambda$new$2$WebToolbarWebHook(final View.OnClickListener originalClickListener, View v) {
        if (originalClickListener != null) {
            originalClickListener.onClick(v);
        }
        if (this.mWebView.canGoForward()) {
            this.mWebView.goForward();
        }
    }

    @Override // com.sq.webview.SimpleWebHook, com.sq.webview.WebHook
    public void onPageStarted(WebView webView, String url, Bitmap favicon) {
        super.onPageStarted(webView, url, favicon);
        this.mHandler.removeCallbacks(this.mLoadTimeoutRunnable);
        this.mHandler.postDelayed(this.mLoadTimeoutRunnable, this.mTimeout);
    }

    @Override // com.sq.webview.SimpleWebHook, com.sq.webview.WebHook
    public void onPageFinished(WebView webView, String url) {
        super.onPageFinished(webView, url);
        this.mHandler.removeCallbacks(this.mLoadTimeoutRunnable);
    }

    @Override // com.sq.webview.SimpleWebHook, com.sq.webview.WebHook
    public void onReceivedError(WebView webView, WebResourceRequest request, WebResourceError error) {
        super.onReceivedError(webView, request, error);
        showToolbarIfNeed();
        this.mStartCountDown = false;
    }

    @Override // com.sq.webview.SimpleWebHook, com.sq.webview.WebHook
    public void onReceivedError(WebView webView, String url, int errorCode, String description) {
        super.onReceivedError(webView, url, errorCode, description);
        showToolbarIfNeed();
        this.mStartCountDown = false;
    }

    public void startCountDown() {
        if (!this.mStartCountDown) {
            this.mHandler.postDelayed(this.mLoadTimeoutRunnable, this.mTimeout);
            this.mStartCountDown = true;
        } else {
            WebLogUtil.w("LoadTimeoutWebHook is counting down");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showToolbarIfNeed() {
        if (!this.mIWebToolBar.isShow()) {
            this.mIWebToolBar.show(getContext());
        } else {
            WebLogUtil.w("WebToolBar is showing");
        }
    }
}
