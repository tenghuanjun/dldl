package com.sq.webview;

import android.net.Uri;
import android.view.View;
import android.webkit.ConsoleMessage;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.PermissionRequest;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class SQWebChromeClient extends WebChromeClient {
    private final WebHookDispatcher mWebHookDispatcher;

    public SQWebChromeClient(WebHookDispatcher webHookDispatcher) {
        if (webHookDispatcher == null) {
            this.mWebHookDispatcher = new WebHookDispatcher();
        } else {
            this.mWebHookDispatcher = webHookDispatcher;
        }
    }

    @Override // android.webkit.WebChromeClient
    public void onPermissionRequest(PermissionRequest request) {
        this.mWebHookDispatcher.onPermissionRequest(request);
    }

    @Override // android.webkit.WebChromeClient
    public void onReceivedTitle(WebView view, String title) {
        super.onReceivedTitle(view, title);
        this.mWebHookDispatcher.onReceivedTitle(view, title);
    }

    @Override // android.webkit.WebChromeClient
    public void onProgressChanged(WebView view, int newProgress) {
        super.onProgressChanged(view, newProgress);
        this.mWebHookDispatcher.onProgressChanged(view, newProgress);
    }

    @Override // android.webkit.WebChromeClient
    public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> filePathCallback, WebChromeClient.FileChooserParams fileChooserParams) {
        return this.mWebHookDispatcher.onShowFileChooser(webView, filePathCallback, fileChooserParams);
    }

    @Override // android.webkit.WebChromeClient
    public View getVideoLoadingProgressView() {
        return super.getVideoLoadingProgressView();
    }

    @Override // android.webkit.WebChromeClient
    public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
        this.mWebHookDispatcher.onConsoleMessage(consoleMessage);
        return super.onConsoleMessage(consoleMessage);
    }

    @Override // android.webkit.WebChromeClient
    public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
        if (this.mWebHookDispatcher.onJsAlert(view, url, message, result)) {
            return true;
        }
        return super.onJsAlert(view, url, message, result);
    }

    @Override // android.webkit.WebChromeClient
    public boolean onJsPrompt(WebView view, String url, String message, String defaultValue, JsPromptResult result) {
        if (this.mWebHookDispatcher.onJsPrompt(view, url, message, defaultValue, result)) {
            return true;
        }
        return super.onJsPrompt(view, url, message, defaultValue, result);
    }

    @Override // android.webkit.WebChromeClient
    public boolean onJsBeforeUnload(WebView view, String url, String message, JsResult result) {
        if (this.mWebHookDispatcher.onJsBeforeUnload(view, url, message, result)) {
            return true;
        }
        return super.onJsBeforeUnload(view, url, message, result);
    }

    @Override // android.webkit.WebChromeClient
    public void onShowCustomView(View view, WebChromeClient.CustomViewCallback callback) {
        super.onShowCustomView(view, callback);
        this.mWebHookDispatcher.onShowCustomView(view, callback);
    }

    @Override // android.webkit.WebChromeClient
    public void onHideCustomView() {
        super.onHideCustomView();
        this.mWebHookDispatcher.onHideCustomView();
    }
}
