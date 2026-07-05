package com.sq.webview;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslError;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.ConsoleMessage;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.PermissionRequest;
import android.webkit.SslErrorHandler;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class WebHookDispatcher extends SimpleWebHook {
    private boolean hasInit;
    private final List<WebHook> webHooks = new CopyOnWriteArrayList();

    public void addWebHook(WebHook webHook) {
        this.webHooks.add(webHook);
        if (this.hasInit) {
            webHook.onWebInit(this.mWebView);
        }
    }

    public void addWebHooks(Collection<WebHook> webHooks) {
        this.webHooks.addAll(webHooks);
        if (this.hasInit) {
            Iterator<WebHook> it = webHooks.iterator();
            while (it.hasNext()) {
                it.next().onWebInit(this.mWebView);
            }
        }
    }

    public void addWebHook(int position, WebHook webHook) {
        this.webHooks.add(position, webHook);
        if (this.hasInit) {
            webHook.onWebInit(this.mWebView);
        }
    }

    public void addWebHooks(int position, Collection<WebHook> webHooks) {
        this.webHooks.addAll(position, webHooks);
        if (this.hasInit) {
            Iterator<WebHook> it = webHooks.iterator();
            while (it.hasNext()) {
                it.next().onWebInit(this.mWebView);
            }
        }
    }

    public WebHook findWebHookByClass(Class<? extends WebHook> clazz) {
        for (WebHook webHook : this.webHooks) {
            if (webHook.getClass().equals(clazz)) {
                return webHook;
            }
        }
        return null;
    }

    public void removeWebHook(WebHook webHook) {
        this.webHooks.remove(webHook);
    }

    public List<WebHook> getWebHooks() {
        return this.webHooks;
    }

    public void clear() {
        this.webHooks.clear();
    }

    @Override // com.sq.webview.SimpleWebHook, com.sq.webview.WebHook
    public boolean shouldOverrideUrlLoading(WebView webView, String url) {
        Iterator<WebHook> it = this.webHooks.iterator();
        while (it.hasNext()) {
            if (it.next().shouldOverrideUrlLoading(webView, url)) {
                return true;
            }
        }
        return super.shouldOverrideUrlLoading(webView, url);
    }

    @Override // com.sq.webview.SimpleWebHook, com.sq.webview.WebHook
    public void onWebInit(WebView webView) {
        super.onWebInit(webView);
        Iterator<WebHook> it = this.webHooks.iterator();
        while (it.hasNext()) {
            it.next().onWebInit(webView);
        }
        this.hasInit = true;
    }

    @Override // com.sq.webview.SimpleWebHook, com.sq.webview.WebHook
    public void onPageFinished(WebView webView, String url) {
        Iterator<WebHook> it = this.webHooks.iterator();
        while (it.hasNext()) {
            it.next().onPageFinished(webView, url);
        }
    }

    @Override // com.sq.webview.SimpleWebHook, com.sq.webview.WebHook
    public void onReceivedTitle(WebView webView, String title) {
        Iterator<WebHook> it = this.webHooks.iterator();
        while (it.hasNext()) {
            it.next().onReceivedTitle(webView, title);
        }
    }

    @Override // com.sq.webview.SimpleWebHook, com.sq.webview.WebHook
    public void onProgressChanged(WebView webView, int newProgress) {
        Iterator<WebHook> it = this.webHooks.iterator();
        while (it.hasNext()) {
            it.next().onProgressChanged(webView, newProgress);
        }
    }

    @Override // com.sq.webview.SimpleWebHook, com.sq.webview.WebHook
    public void onPageStarted(WebView webView, String url, Bitmap favicon) {
        Iterator<WebHook> it = this.webHooks.iterator();
        while (it.hasNext()) {
            it.next().onPageStarted(webView, url, favicon);
        }
    }

    @Override // com.sq.webview.SimpleWebHook, com.sq.webview.WebHook
    public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> filePathCallback, WebChromeClient.FileChooserParams fileChooserParams) {
        Iterator<WebHook> it = this.webHooks.iterator();
        while (it.hasNext()) {
            if (it.next().onShowFileChooser(webView, filePathCallback, fileChooserParams)) {
                return true;
            }
        }
        return false;
    }

    @Override // com.sq.webview.SimpleWebHook, com.sq.webview.WebHook
    public boolean onActivityResult(int requestCode, int resultCode, Intent intent) {
        Iterator<WebHook> it = this.webHooks.iterator();
        while (it.hasNext()) {
            if (it.next().onActivityResult(requestCode, resultCode, intent)) {
                return true;
            }
        }
        return false;
    }

    @Override // com.sq.webview.SimpleWebHook, com.sq.webview.WebHook
    public void onReceivedError(WebView webView, WebResourceRequest request, WebResourceError error) {
        Iterator<WebHook> it = this.webHooks.iterator();
        while (it.hasNext()) {
            it.next().onReceivedError(webView, request, error);
        }
    }

    @Override // com.sq.webview.SimpleWebHook, com.sq.webview.WebHook
    public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
        Iterator<WebHook> it = this.webHooks.iterator();
        while (it.hasNext()) {
            WebResourceResponse webResourceResponseShouldInterceptRequest = it.next().shouldInterceptRequest(view, request);
            if (webResourceResponseShouldInterceptRequest != null) {
                return webResourceResponseShouldInterceptRequest;
            }
        }
        return super.shouldInterceptRequest(view, request);
    }

    @Override // com.sq.webview.SimpleWebHook, com.sq.webview.WebHook
    public boolean onBackPressed() {
        Iterator<WebHook> it = this.webHooks.iterator();
        while (it.hasNext()) {
            if (it.next().onBackPressed()) {
                return true;
            }
        }
        return super.onBackPressed();
    }

    @Override // com.sq.webview.SimpleWebHook, com.sq.webview.WebHook
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        Iterator<WebHook> it = this.webHooks.iterator();
        while (it.hasNext()) {
            if (it.next().onKeyUp(keyCode, event)) {
                return true;
            }
        }
        return super.onKeyUp(keyCode, event);
    }

    @Override // com.sq.webview.SimpleWebHook, com.sq.webview.WebHook
    public void onConsoleMessage(ConsoleMessage consoleMessage) {
        Iterator<WebHook> it = this.webHooks.iterator();
        while (it.hasNext()) {
            it.next().onConsoleMessage(consoleMessage);
        }
    }

    @Override // com.sq.webview.SimpleWebHook, com.sq.webview.WebHook
    public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
        Iterator<WebHook> it = this.webHooks.iterator();
        while (it.hasNext()) {
            it.next().onReceivedSslError(view, handler, error);
        }
        super.onReceivedSslError(view, handler, error);
    }

    @Override // com.sq.webview.SimpleWebHook, com.sq.webview.WebHook
    public void onLoadResource(WebView view, String url) {
        Iterator<WebHook> it = this.webHooks.iterator();
        while (it.hasNext()) {
            it.next().onLoadResource(view, url);
        }
        super.onLoadResource(view, url);
    }

    @Override // com.sq.webview.SimpleWebHook, com.sq.webview.WebHook
    public void onReceivedError(WebView webView, String url, int errorCode, String description) {
        Iterator<WebHook> it = this.webHooks.iterator();
        while (it.hasNext()) {
            it.next().onReceivedError(webView, url, errorCode, description);
        }
        super.onReceivedError(webView, url, errorCode, description);
    }

    @Override // com.sq.webview.SimpleWebHook, com.sq.webview.WebHook
    public void setActivityMode(Activity activity, boolean inActivity) {
        super.setActivityMode(activity, inActivity);
        Iterator<WebHook> it = this.webHooks.iterator();
        while (it.hasNext()) {
            it.next().setActivityMode(activity, inActivity);
        }
    }

    @Override // com.sq.webview.SimpleWebHook, com.sq.webview.WebHook
    public void onPermissionRequest(PermissionRequest request) {
        super.onPermissionRequest(request);
        Iterator<WebHook> it = this.webHooks.iterator();
        while (it.hasNext()) {
            it.next().onPermissionRequest(request);
        }
    }

    @Override // com.sq.webview.SimpleWebHook, com.sq.webview.WebHook
    public void onPageFinishedOnce(WebView webView, String url) {
        super.onPageFinishedOnce(webView, url);
        Iterator<WebHook> it = this.webHooks.iterator();
        while (it.hasNext()) {
            it.next().onPageFinishedOnce(webView, url);
        }
    }

    @Override // com.sq.webview.SimpleWebHook, com.sq.webview.WebHook
    public void onPageStartedOnce(WebView webView, String url, Bitmap favicon) {
        super.onPageStartedOnce(webView, url, favicon);
        Iterator<WebHook> it = this.webHooks.iterator();
        while (it.hasNext()) {
            it.next().onPageStartedOnce(webView, url, favicon);
        }
    }

    @Override // com.sq.webview.SimpleWebHook, com.sq.webview.WebHook
    public boolean onJsBeforeUnload(WebView view, String url, String message, JsResult result) {
        Iterator<WebHook> it = this.webHooks.iterator();
        while (it.hasNext()) {
            if (it.next().onJsBeforeUnload(view, url, message, result)) {
                return true;
            }
        }
        return super.onJsBeforeUnload(view, url, message, result);
    }

    @Override // com.sq.webview.SimpleWebHook, com.sq.webview.WebHook
    public boolean onJsPrompt(WebView view, String url, String message, String defaultValue, JsPromptResult result) {
        Iterator<WebHook> it = this.webHooks.iterator();
        while (it.hasNext()) {
            if (it.next().onJsPrompt(view, url, message, defaultValue, result)) {
                return true;
            }
        }
        return super.onJsPrompt(view, url, message, defaultValue, result);
    }

    @Override // com.sq.webview.SimpleWebHook, com.sq.webview.WebHook
    public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
        Iterator<WebHook> it = this.webHooks.iterator();
        while (it.hasNext()) {
            if (it.next().onJsAlert(view, url, message, result)) {
                return true;
            }
        }
        return super.onJsAlert(view, url, message, result);
    }

    @Override // com.sq.webview.SimpleWebHook, com.sq.webview.WebHook
    public void onReceivedHttpError(WebView view, WebResourceRequest request, WebResourceResponse errorResponse) {
        super.onReceivedHttpError(view, request, errorResponse);
        Iterator<WebHook> it = this.webHooks.iterator();
        while (it.hasNext()) {
            it.next().onReceivedHttpError(view, request, errorResponse);
        }
    }

    @Override // com.sq.webview.SimpleWebHook, com.sq.webview.WebHook
    public void onShowCustomView(View view, WebChromeClient.CustomViewCallback callback) {
        Iterator<WebHook> it = this.webHooks.iterator();
        while (it.hasNext()) {
            it.next().onShowCustomView(view, callback);
        }
    }

    @Override // com.sq.webview.SimpleWebHook, com.sq.webview.WebHook
    public void onHideCustomView() {
        Iterator<WebHook> it = this.webHooks.iterator();
        while (it.hasNext()) {
            it.next().onHideCustomView();
        }
    }
}
