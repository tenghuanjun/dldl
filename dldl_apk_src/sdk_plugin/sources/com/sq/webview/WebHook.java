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

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public interface WebHook {
    boolean onActivityResult(int requestCode, int resultCode, Intent intent);

    boolean onBackPressed();

    void onConsoleMessage(ConsoleMessage consoleMessage);

    void onHideCustomView();

    boolean onJsAlert(WebView view, String url, String message, JsResult result);

    boolean onJsBeforeUnload(WebView view, String url, String message, JsResult result);

    boolean onJsPrompt(WebView view, String url, String message, String defaultValue, JsPromptResult result);

    boolean onKeyUp(int keyCode, KeyEvent event);

    void onLoadResource(WebView view, String url);

    void onPageFinished(WebView webView, String url);

    void onPageFinishedOnce(WebView webView, String url);

    void onPageStarted(WebView webView, String url, Bitmap favicon);

    void onPageStartedOnce(WebView webView, String url, Bitmap favicon);

    void onPermissionRequest(PermissionRequest request);

    void onProgressChanged(WebView webView, int newProgress);

    void onReceivedError(WebView webView, WebResourceRequest request, WebResourceError error);

    void onReceivedError(WebView webView, String url, int errorCode, String description);

    void onReceivedHttpError(WebView view, WebResourceRequest request, WebResourceResponse errorResponse);

    void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error);

    void onReceivedTitle(WebView webView, String title);

    void onShowCustomView(View view, WebChromeClient.CustomViewCallback callback);

    boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> filePathCallback, WebChromeClient.FileChooserParams fileChooserParams);

    void onWebInit(WebView webView);

    void setActivityMode(Activity activity, boolean inActivity);

    WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request);

    boolean shouldOverrideUrlLoading(WebView webView, String url);
}
