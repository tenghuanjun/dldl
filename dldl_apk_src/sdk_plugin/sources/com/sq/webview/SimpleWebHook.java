package com.sq.webview;

import android.app.Activity;
import android.content.Context;
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

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class SimpleWebHook implements WebHook {
    protected Activity mActivity;
    protected boolean mInActivity;
    protected WebView mWebView;

    @Override // com.sq.webview.WebHook
    public boolean onActivityResult(int requestCode, int resultCode, Intent intent) {
        return false;
    }

    @Override // com.sq.webview.WebHook
    public boolean onBackPressed() {
        return false;
    }

    @Override // com.sq.webview.WebHook
    public void onConsoleMessage(ConsoleMessage consoleMessage) {
    }

    @Override // com.sq.webview.WebHook
    public void onHideCustomView() {
    }

    @Override // com.sq.webview.WebHook
    public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
        return false;
    }

    @Override // com.sq.webview.WebHook
    public boolean onJsBeforeUnload(WebView view, String url, String message, JsResult result) {
        return false;
    }

    @Override // com.sq.webview.WebHook
    public boolean onJsPrompt(WebView view, String url, String message, String defaultValue, JsPromptResult result) {
        return false;
    }

    @Override // com.sq.webview.WebHook
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        return false;
    }

    @Override // com.sq.webview.WebHook
    public void onLoadResource(WebView view, String url) {
    }

    @Override // com.sq.webview.WebHook
    public void onPageFinished(WebView webView, String url) {
    }

    @Override // com.sq.webview.WebHook
    public void onPageFinishedOnce(WebView webView, String url) {
    }

    @Override // com.sq.webview.WebHook
    public void onPageStarted(WebView webView, String url, Bitmap favicon) {
    }

    @Override // com.sq.webview.WebHook
    public void onPageStartedOnce(WebView webView, String url, Bitmap favicon) {
    }

    @Override // com.sq.webview.WebHook
    public void onPermissionRequest(PermissionRequest request) {
    }

    @Override // com.sq.webview.WebHook
    public void onProgressChanged(WebView webView, int newProgress) {
    }

    @Override // com.sq.webview.WebHook
    public void onReceivedError(WebView webView, WebResourceRequest request, WebResourceError error) {
    }

    @Override // com.sq.webview.WebHook
    public void onReceivedError(WebView webView, String url, int errorCode, String description) {
    }

    @Override // com.sq.webview.WebHook
    public void onReceivedHttpError(WebView view, WebResourceRequest request, WebResourceResponse errorResponse) {
    }

    @Override // com.sq.webview.WebHook
    public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
    }

    @Override // com.sq.webview.WebHook
    public void onReceivedTitle(WebView webView, String title) {
    }

    @Override // com.sq.webview.WebHook
    public void onShowCustomView(View view, WebChromeClient.CustomViewCallback callback) {
    }

    @Override // com.sq.webview.WebHook
    public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> filePathCallback, WebChromeClient.FileChooserParams fileChooserParams) {
        return false;
    }

    @Override // com.sq.webview.WebHook
    public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
        return null;
    }

    @Override // com.sq.webview.WebHook
    public boolean shouldOverrideUrlLoading(WebView webView, String url) {
        return false;
    }

    protected Context getContext() {
        Activity activity = this.mActivity;
        return activity == null ? this.mWebView.getContext() : activity;
    }

    @Override // com.sq.webview.WebHook
    public void setActivityMode(Activity activity, boolean inActivity) {
        this.mActivity = activity;
        this.mInActivity = inActivity;
    }

    @Override // com.sq.webview.WebHook
    public void onWebInit(WebView webView) {
        this.mWebView = webView;
    }
}
