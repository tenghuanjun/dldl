package com.sq.webview;

import android.app.Activity;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.webkit.WebView;
import com.sq.webview.hooks.ErrorWebHook;
import com.sq.webview.hooks.FileChoseWebHook;
import com.sq.webview.hooks.LoadTimeoutWebHook;
import com.sq.webview.hooks.LocalH5WebHook;
import com.sq.webview.hooks.ProgressWebHook;
import com.sq.webview.hooks.WebToolbarWebHook;
import com.sq.webview.util.WebLogUtil;
import com.sq.webview.view.ILoadTimeout;
import com.sq.webview.view.IWebToolBar;
import com.sq.webview.view.IWebViewError;
import com.sq.webview.view.IWebViewLoading;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class WebFunctionWrapper {
    private LoadTimeoutWebHook mLoadTimeoutWebHook;
    private ProgressWebHook mProgressWebHook;
    private final WebHookDispatcher mWebHookDispatcher;
    private WebToolbarWebHook mWebToolbarWebHook;
    private final WebView mWebView;

    WebFunctionWrapper(WebView webView, WebHookDispatcher dispatcher) {
        webView.setWebViewClient(new SQWebViewClient(dispatcher));
        webView.setWebChromeClient(new SQWebChromeClient(dispatcher));
        this.mWebHookDispatcher = dispatcher;
        this.mWebView = webView;
    }

    public WebFunctionWrapper enableFileUpload(Activity activity) {
        this.mWebHookDispatcher.addWebHook(new FileChoseWebHook(activity));
        return this;
    }

    public WebFunctionWrapper setLoading(IWebViewLoading webViewLoading, int loadingTimeout) {
        ProgressWebHook progressWebHook = new ProgressWebHook(webViewLoading, loadingTimeout);
        this.mProgressWebHook = progressWebHook;
        this.mWebHookDispatcher.addWebHook(progressWebHook);
        return this;
    }

    public WebFunctionWrapper setLoading(IWebViewLoading webViewLoading) {
        ProgressWebHook progressWebHook = new ProgressWebHook(webViewLoading);
        this.mProgressWebHook = progressWebHook;
        this.mWebHookDispatcher.addWebHook(progressWebHook);
        return this;
    }

    public WebFunctionWrapper setErrorView(IWebViewError webViewError) {
        this.mWebHookDispatcher.addWebHook(new ErrorWebHook(webViewError));
        return this;
    }

    public WebFunctionWrapper setWebToolBar(IWebToolBar webOperation) {
        WebToolbarWebHook webToolbarWebHook = new WebToolbarWebHook(webOperation);
        this.mWebToolbarWebHook = webToolbarWebHook;
        this.mWebHookDispatcher.addWebHook(webToolbarWebHook);
        return this;
    }

    public WebFunctionWrapper setWebToolBar(IWebToolBar webOperation, int showTimeout) {
        WebToolbarWebHook webToolbarWebHook = new WebToolbarWebHook(webOperation, showTimeout);
        this.mWebToolbarWebHook = webToolbarWebHook;
        this.mWebHookDispatcher.addWebHook(webToolbarWebHook);
        return this;
    }

    public WebFunctionWrapper replace(WebHook replaceWebHook, Class<? extends WebHook> targetClazz) {
        WebHook webHookFindWebHookByClass = this.mWebHookDispatcher.findWebHookByClass(targetClazz);
        if (webHookFindWebHookByClass != null) {
            this.mWebHookDispatcher.removeWebHook(webHookFindWebHookByClass);
        } else {
            WebLogUtil.w("not found " + targetClazz.getName() + " to replace ");
        }
        this.mWebHookDispatcher.addWebHook(replaceWebHook);
        return this;
    }

    public WebFunctionWrapper disableLocalH5() {
        this.mWebHookDispatcher.removeWebHook(this.mWebHookDispatcher.findWebHookByClass(LocalH5WebHook.class));
        return this;
    }

    public WebFunctionWrapper setLoadTimeout(ILoadTimeout loadTimeout, int timeout) {
        LoadTimeoutWebHook loadTimeoutWebHook = new LoadTimeoutWebHook(loadTimeout, timeout);
        this.mLoadTimeoutWebHook = loadTimeoutWebHook;
        this.mWebHookDispatcher.addWebHook(loadTimeoutWebHook);
        return this;
    }

    public WebFunctionWrapper setLoadTimeout(ILoadTimeout loadTimeout) {
        LoadTimeoutWebHook loadTimeoutWebHook = new LoadTimeoutWebHook(loadTimeout);
        this.mLoadTimeoutWebHook = loadTimeoutWebHook;
        this.mWebHookDispatcher.addWebHook(loadTimeoutWebHook);
        return this;
    }

    public WebFunctionWrapper checkUrlEmpty() {
        this.mWebHookDispatcher.addWebHook(new SimpleWebHook() { // from class: com.sq.webview.WebFunctionWrapper.1
            @Override // com.sq.webview.SimpleWebHook, com.sq.webview.WebHook
            public void onPageStarted(WebView webView, String url, Bitmap favicon) {
                super.onPageStarted(webView, url, favicon);
                if (TextUtils.isEmpty(url) || TextUtils.equals("about:blank", url)) {
                    WebFunctionWrapper.this.mWebHookDispatcher.onReceivedError(webView, url, Constants.CODE_ERR_URL_EMPTY, Constants.DES_ERR_URL_EMPTY);
                }
            }
        });
        return this;
    }

    public WebFunctionWrapper setActivityMode(Activity activity, boolean inActivity) {
        this.mWebHookDispatcher.setActivityMode(activity, inActivity);
        return this;
    }

    public WebHookDispatcher getWebHookDispatcher() {
        return this.mWebHookDispatcher;
    }

    public WebView getWebView() {
        return this.mWebView;
    }

    public WebFunctionWrapper addWebHook(WebHook webHook) {
        this.mWebHookDispatcher.addWebHook(webHook);
        return this;
    }

    public WebFunctionWrapper addWebHooks(List<WebHook> webHooks) {
        this.mWebHookDispatcher.addWebHooks(webHooks);
        return this;
    }

    public void proxyLoadUrl(String url) {
        this.mWebView.loadUrl(url);
        hookLoadUrl(url);
    }

    public void proxyLoadUrl(String url, Map<String, String> additionalHttpHeaders) {
        this.mWebView.loadUrl(url, additionalHttpHeaders);
        hookLoadUrl(url);
    }

    public void hookLoadUrl(String url) {
        ProgressWebHook progressWebHook = this.mProgressWebHook;
        if (progressWebHook != null) {
            progressWebHook.startLoading();
        }
        LoadTimeoutWebHook loadTimeoutWebHook = this.mLoadTimeoutWebHook;
        if (loadTimeoutWebHook != null) {
            loadTimeoutWebHook.startCountDown();
        }
        WebToolbarWebHook webToolbarWebHook = this.mWebToolbarWebHook;
        if (webToolbarWebHook != null) {
            webToolbarWebHook.startCountDown();
        }
    }
}
