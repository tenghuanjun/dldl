package com.huyaudb.webview;

import android.R;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.webkit.ConsoleMessage;
import android.webkit.CookieManager;
import android.webkit.GeolocationPermissions;
import android.webkit.JavascriptInterface;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.PermissionRequest;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebStorage;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.FrameLayout;
import com.sq.tool.sqtools.detector.common.SqTrackCommonKey;
import com.sqwan.common.route.FunctionRouter;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class HuyaUdbBridgeWebView extends WebView {
    private static final String BRIDGE_NAME = "_dsbridge";
    private static final String LOG_TAG = "dsBridge";
    private static boolean isDebug;
    private String APP_CACHE_DIRNAME;
    private volatile boolean alertBoxBlock;
    private int callID;
    private ArrayList<CallInfo> callInfoList;
    Map<Integer, OnReturnValue> handlerMap;
    private InnerJavascriptInterface innerJavascriptInterface;
    private Map<String, Object> javaScriptNamespaceInterfaces;
    private JavascriptCloseWindowListener javascriptCloseWindowListener;
    private WebChromeClient mWebChromeClient;
    private Handler mainHandler;
    private WebChromeClient webChromeClient;

    public interface CompletionHandler<T> {
        void complete();

        void complete(T t);

        void setProgressData(T t);
    }

    @Deprecated
    public interface FileChooser {
        void openFileChooser(ValueCallback valueCallback, String str);

        void openFileChooser(ValueCallback<Uri> valueCallback, String str, String str2);
    }

    public interface JavascriptCloseWindowListener {
        boolean onClose();
    }

    public interface OnReturnValue<T> {
        void onValue(T t);
    }

    private class InnerJavascriptInterface {
        private InnerJavascriptInterface() {
        }

        private void PrintDebugInfo(String str) {
            Log.d(HuyaUdbBridgeWebView.LOG_TAG, str);
            if (HuyaUdbBridgeWebView.isDebug) {
                HuyaUdbBridgeWebView.this.evaluateJavascript(String.format("alert('%s')", "DEBUG ERR MSG:\\n" + str.replaceAll("\\'", "\\\\'")));
            }
        }

        /* JADX WARN: Removed duplicated region for block: B:25:0x007a  */
        /* JADX WARN: Removed duplicated region for block: B:27:0x0098  */
        @android.webkit.JavascriptInterface
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public java.lang.String callHandler(java.lang.String r13, java.lang.String r14) {
            /*
                Method dump skipped, instruction units count: 285
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.huyaudb.webview.HuyaUdbBridgeWebView.InnerJavascriptInterface.callHandler(java.lang.String, java.lang.String):java.lang.String");
        }
    }

    public HuyaUdbBridgeWebView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.javaScriptNamespaceInterfaces = new HashMap();
        this.callID = 0;
        this.alertBoxBlock = true;
        this.javascriptCloseWindowListener = null;
        this.innerJavascriptInterface = new InnerJavascriptInterface();
        this.mainHandler = new Handler(Looper.getMainLooper());
        this.handlerMap = new HashMap();
        this.mWebChromeClient = new WebChromeClient() { // from class: com.huyaudb.webview.HuyaUdbBridgeWebView.6
            @Override // android.webkit.WebChromeClient
            public void onProgressChanged(WebView webView, int i) {
                if (HuyaUdbBridgeWebView.this.webChromeClient != null) {
                    HuyaUdbBridgeWebView.this.webChromeClient.onProgressChanged(webView, i);
                } else {
                    super.onProgressChanged(webView, i);
                }
            }

            @Override // android.webkit.WebChromeClient
            public void onReceivedTitle(WebView webView, String str) {
                if (HuyaUdbBridgeWebView.this.webChromeClient != null) {
                    HuyaUdbBridgeWebView.this.webChromeClient.onReceivedTitle(webView, str);
                } else {
                    super.onReceivedTitle(webView, str);
                }
            }

            @Override // android.webkit.WebChromeClient
            public void onReceivedIcon(WebView webView, Bitmap bitmap) {
                if (HuyaUdbBridgeWebView.this.webChromeClient != null) {
                    HuyaUdbBridgeWebView.this.webChromeClient.onReceivedIcon(webView, bitmap);
                } else {
                    super.onReceivedIcon(webView, bitmap);
                }
            }

            @Override // android.webkit.WebChromeClient
            public void onReceivedTouchIconUrl(WebView webView, String str, boolean z) {
                if (HuyaUdbBridgeWebView.this.webChromeClient != null) {
                    HuyaUdbBridgeWebView.this.webChromeClient.onReceivedTouchIconUrl(webView, str, z);
                } else {
                    super.onReceivedTouchIconUrl(webView, str, z);
                }
            }

            @Override // android.webkit.WebChromeClient
            public void onShowCustomView(View view, WebChromeClient.CustomViewCallback customViewCallback) {
                if (HuyaUdbBridgeWebView.this.webChromeClient != null) {
                    HuyaUdbBridgeWebView.this.webChromeClient.onShowCustomView(view, customViewCallback);
                } else {
                    super.onShowCustomView(view, customViewCallback);
                }
            }

            @Override // android.webkit.WebChromeClient
            public void onShowCustomView(View view, int i, WebChromeClient.CustomViewCallback customViewCallback) {
                if (HuyaUdbBridgeWebView.this.webChromeClient != null) {
                    HuyaUdbBridgeWebView.this.webChromeClient.onShowCustomView(view, i, customViewCallback);
                } else {
                    super.onShowCustomView(view, i, customViewCallback);
                }
            }

            @Override // android.webkit.WebChromeClient
            public void onHideCustomView() {
                if (HuyaUdbBridgeWebView.this.webChromeClient != null) {
                    HuyaUdbBridgeWebView.this.webChromeClient.onHideCustomView();
                } else {
                    super.onHideCustomView();
                }
            }

            @Override // android.webkit.WebChromeClient
            public boolean onCreateWindow(WebView webView, boolean z, boolean z2, Message message) {
                if (HuyaUdbBridgeWebView.this.webChromeClient != null) {
                    return HuyaUdbBridgeWebView.this.webChromeClient.onCreateWindow(webView, z, z2, message);
                }
                return super.onCreateWindow(webView, z, z2, message);
            }

            @Override // android.webkit.WebChromeClient
            public void onRequestFocus(WebView webView) {
                if (HuyaUdbBridgeWebView.this.webChromeClient != null) {
                    HuyaUdbBridgeWebView.this.webChromeClient.onRequestFocus(webView);
                } else {
                    super.onRequestFocus(webView);
                }
            }

            @Override // android.webkit.WebChromeClient
            public void onCloseWindow(WebView webView) {
                if (HuyaUdbBridgeWebView.this.webChromeClient != null) {
                    HuyaUdbBridgeWebView.this.webChromeClient.onCloseWindow(webView);
                } else {
                    super.onCloseWindow(webView);
                }
            }

            @Override // android.webkit.WebChromeClient
            public boolean onJsAlert(WebView webView, String str, String str2, final JsResult jsResult) {
                if (!HuyaUdbBridgeWebView.this.alertBoxBlock) {
                    jsResult.confirm();
                }
                if (HuyaUdbBridgeWebView.this.webChromeClient != null && HuyaUdbBridgeWebView.this.webChromeClient.onJsAlert(webView, str, str2, jsResult)) {
                    return true;
                }
                new AlertDialog.Builder(HuyaUdbBridgeWebView.this.getContext()).setMessage(str2).setCancelable(false).setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() { // from class: com.huyaudb.webview.HuyaUdbBridgeWebView.6.1
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        if (HuyaUdbBridgeWebView.this.alertBoxBlock) {
                            jsResult.confirm();
                        }
                    }
                }).create().show();
                return true;
            }

            @Override // android.webkit.WebChromeClient
            public boolean onJsConfirm(WebView webView, String str, String str2, final JsResult jsResult) {
                if (!HuyaUdbBridgeWebView.this.alertBoxBlock) {
                    jsResult.confirm();
                }
                if (HuyaUdbBridgeWebView.this.webChromeClient != null && HuyaUdbBridgeWebView.this.webChromeClient.onJsConfirm(webView, str, str2, jsResult)) {
                    return true;
                }
                DialogInterface.OnClickListener onClickListener = new DialogInterface.OnClickListener() { // from class: com.huyaudb.webview.HuyaUdbBridgeWebView.6.2
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (HuyaUdbBridgeWebView.this.alertBoxBlock) {
                            if (i == -1) {
                                jsResult.confirm();
                            } else {
                                jsResult.cancel();
                            }
                        }
                    }
                };
                new AlertDialog.Builder(HuyaUdbBridgeWebView.this.getContext()).setMessage(str2).setCancelable(false).setPositiveButton(R.string.ok, onClickListener).setNegativeButton(R.string.cancel, onClickListener).show();
                return true;
            }

            @Override // android.webkit.WebChromeClient
            public boolean onJsPrompt(WebView webView, String str, String str2, String str3, final JsPromptResult jsPromptResult) {
                if (Build.VERSION.SDK_INT > 16 || !str2.startsWith("_dsbridge=")) {
                    if (!HuyaUdbBridgeWebView.this.alertBoxBlock) {
                        jsPromptResult.confirm();
                    }
                    if (HuyaUdbBridgeWebView.this.webChromeClient != null && HuyaUdbBridgeWebView.this.webChromeClient.onJsPrompt(webView, str, str2, str3, jsPromptResult)) {
                        return true;
                    }
                    final EditText editText = new EditText(HuyaUdbBridgeWebView.this.getContext());
                    editText.setText(str3);
                    if (str3 != null) {
                        editText.setSelection(str3.length());
                    }
                    float f = HuyaUdbBridgeWebView.this.getContext().getResources().getDisplayMetrics().density;
                    DialogInterface.OnClickListener onClickListener = new DialogInterface.OnClickListener() { // from class: com.huyaudb.webview.HuyaUdbBridgeWebView.6.3
                        @Override // android.content.DialogInterface.OnClickListener
                        public void onClick(DialogInterface dialogInterface, int i) {
                            if (HuyaUdbBridgeWebView.this.alertBoxBlock) {
                                if (i == -1) {
                                    jsPromptResult.confirm(editText.getText().toString());
                                } else {
                                    jsPromptResult.cancel();
                                }
                            }
                        }
                    };
                    new AlertDialog.Builder(HuyaUdbBridgeWebView.this.getContext()).setTitle(str2).setView(editText).setCancelable(false).setPositiveButton(R.string.ok, onClickListener).setNegativeButton(R.string.cancel, onClickListener).show();
                    FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -2);
                    int i = (int) (16.0f * f);
                    layoutParams.setMargins(i, 0, i, 0);
                    layoutParams.gravity = 1;
                    editText.setLayoutParams(layoutParams);
                    int i2 = (int) (15.0f * f);
                    editText.setPadding(i2 - ((int) (f * 5.0f)), i2, i2, i2);
                    return true;
                }
                jsPromptResult.confirm(HuyaUdbBridgeWebView.this.innerJavascriptInterface.callHandler(str2.substring(10), str3));
                return true;
            }

            @Override // android.webkit.WebChromeClient
            public boolean onJsBeforeUnload(WebView webView, String str, String str2, JsResult jsResult) {
                if (HuyaUdbBridgeWebView.this.webChromeClient != null) {
                    return HuyaUdbBridgeWebView.this.webChromeClient.onJsBeforeUnload(webView, str, str2, jsResult);
                }
                return super.onJsBeforeUnload(webView, str, str2, jsResult);
            }

            @Override // android.webkit.WebChromeClient
            public void onExceededDatabaseQuota(String str, String str2, long j, long j2, long j3, WebStorage.QuotaUpdater quotaUpdater) {
                if (HuyaUdbBridgeWebView.this.webChromeClient != null) {
                    HuyaUdbBridgeWebView.this.webChromeClient.onExceededDatabaseQuota(str, str2, j, j2, j3, quotaUpdater);
                } else {
                    super.onExceededDatabaseQuota(str, str2, j, j2, j3, quotaUpdater);
                }
            }

            public void onReachedMaxAppCacheSize(long j, long j2, WebStorage.QuotaUpdater quotaUpdater) {
                if (HuyaUdbBridgeWebView.this.webChromeClient != null) {
                    HuyaUdbBridgeWebView.this.webChromeClient.onReachedMaxAppCacheSize(j, j2, quotaUpdater);
                }
                super.onReachedMaxAppCacheSize(j, j2, quotaUpdater);
            }

            @Override // android.webkit.WebChromeClient
            public void onGeolocationPermissionsShowPrompt(String str, GeolocationPermissions.Callback callback) {
                if (HuyaUdbBridgeWebView.this.webChromeClient != null) {
                    HuyaUdbBridgeWebView.this.webChromeClient.onGeolocationPermissionsShowPrompt(str, callback);
                } else {
                    super.onGeolocationPermissionsShowPrompt(str, callback);
                }
            }

            @Override // android.webkit.WebChromeClient
            public void onGeolocationPermissionsHidePrompt() {
                if (HuyaUdbBridgeWebView.this.webChromeClient != null) {
                    HuyaUdbBridgeWebView.this.webChromeClient.onGeolocationPermissionsHidePrompt();
                } else {
                    super.onGeolocationPermissionsHidePrompt();
                }
            }

            @Override // android.webkit.WebChromeClient
            public void onPermissionRequest(PermissionRequest permissionRequest) {
                if (HuyaUdbBridgeWebView.this.webChromeClient != null) {
                    HuyaUdbBridgeWebView.this.webChromeClient.onPermissionRequest(permissionRequest);
                } else {
                    super.onPermissionRequest(permissionRequest);
                }
            }

            @Override // android.webkit.WebChromeClient
            public void onPermissionRequestCanceled(PermissionRequest permissionRequest) {
                if (HuyaUdbBridgeWebView.this.webChromeClient != null) {
                    HuyaUdbBridgeWebView.this.webChromeClient.onPermissionRequestCanceled(permissionRequest);
                } else {
                    super.onPermissionRequestCanceled(permissionRequest);
                }
            }

            @Override // android.webkit.WebChromeClient
            public boolean onJsTimeout() {
                if (HuyaUdbBridgeWebView.this.webChromeClient != null) {
                    return HuyaUdbBridgeWebView.this.webChromeClient.onJsTimeout();
                }
                return super.onJsTimeout();
            }

            @Override // android.webkit.WebChromeClient
            public void onConsoleMessage(String str, int i, String str2) {
                if (HuyaUdbBridgeWebView.this.webChromeClient != null) {
                    HuyaUdbBridgeWebView.this.webChromeClient.onConsoleMessage(str, i, str2);
                } else {
                    super.onConsoleMessage(str, i, str2);
                }
            }

            @Override // android.webkit.WebChromeClient
            public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
                if (HuyaUdbBridgeWebView.this.webChromeClient != null) {
                    return HuyaUdbBridgeWebView.this.webChromeClient.onConsoleMessage(consoleMessage);
                }
                return super.onConsoleMessage(consoleMessage);
            }

            @Override // android.webkit.WebChromeClient
            public Bitmap getDefaultVideoPoster() {
                if (HuyaUdbBridgeWebView.this.webChromeClient != null) {
                    return HuyaUdbBridgeWebView.this.webChromeClient.getDefaultVideoPoster();
                }
                return super.getDefaultVideoPoster();
            }

            @Override // android.webkit.WebChromeClient
            public View getVideoLoadingProgressView() {
                if (HuyaUdbBridgeWebView.this.webChromeClient != null) {
                    return HuyaUdbBridgeWebView.this.webChromeClient.getVideoLoadingProgressView();
                }
                return super.getVideoLoadingProgressView();
            }

            @Override // android.webkit.WebChromeClient
            public void getVisitedHistory(ValueCallback<String[]> valueCallback) {
                if (HuyaUdbBridgeWebView.this.webChromeClient != null) {
                    HuyaUdbBridgeWebView.this.webChromeClient.getVisitedHistory(valueCallback);
                } else {
                    super.getVisitedHistory(valueCallback);
                }
            }

            @Override // android.webkit.WebChromeClient
            public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> valueCallback, WebChromeClient.FileChooserParams fileChooserParams) {
                if (HuyaUdbBridgeWebView.this.webChromeClient != null) {
                    return HuyaUdbBridgeWebView.this.webChromeClient.onShowFileChooser(webView, valueCallback, fileChooserParams);
                }
                return super.onShowFileChooser(webView, valueCallback, fileChooserParams);
            }

            public void openFileChooser(ValueCallback valueCallback, String str) {
                if (HuyaUdbBridgeWebView.this.webChromeClient instanceof FileChooser) {
                    ((FileChooser) HuyaUdbBridgeWebView.this.webChromeClient).openFileChooser(valueCallback, str);
                }
            }

            public void openFileChooser(ValueCallback<Uri> valueCallback, String str, String str2) {
                if (HuyaUdbBridgeWebView.this.webChromeClient instanceof FileChooser) {
                    ((FileChooser) HuyaUdbBridgeWebView.this.webChromeClient).openFileChooser(valueCallback, str, str2);
                }
            }
        };
        init();
    }

    public HuyaUdbBridgeWebView(Context context) {
        super(context);
        this.javaScriptNamespaceInterfaces = new HashMap();
        this.callID = 0;
        this.alertBoxBlock = true;
        this.javascriptCloseWindowListener = null;
        this.innerJavascriptInterface = new InnerJavascriptInterface();
        this.mainHandler = new Handler(Looper.getMainLooper());
        this.handlerMap = new HashMap();
        this.mWebChromeClient = new WebChromeClient() { // from class: com.huyaudb.webview.HuyaUdbBridgeWebView.6
            @Override // android.webkit.WebChromeClient
            public void onProgressChanged(WebView webView, int i) {
                if (HuyaUdbBridgeWebView.this.webChromeClient != null) {
                    HuyaUdbBridgeWebView.this.webChromeClient.onProgressChanged(webView, i);
                } else {
                    super.onProgressChanged(webView, i);
                }
            }

            @Override // android.webkit.WebChromeClient
            public void onReceivedTitle(WebView webView, String str) {
                if (HuyaUdbBridgeWebView.this.webChromeClient != null) {
                    HuyaUdbBridgeWebView.this.webChromeClient.onReceivedTitle(webView, str);
                } else {
                    super.onReceivedTitle(webView, str);
                }
            }

            @Override // android.webkit.WebChromeClient
            public void onReceivedIcon(WebView webView, Bitmap bitmap) {
                if (HuyaUdbBridgeWebView.this.webChromeClient != null) {
                    HuyaUdbBridgeWebView.this.webChromeClient.onReceivedIcon(webView, bitmap);
                } else {
                    super.onReceivedIcon(webView, bitmap);
                }
            }

            @Override // android.webkit.WebChromeClient
            public void onReceivedTouchIconUrl(WebView webView, String str, boolean z) {
                if (HuyaUdbBridgeWebView.this.webChromeClient != null) {
                    HuyaUdbBridgeWebView.this.webChromeClient.onReceivedTouchIconUrl(webView, str, z);
                } else {
                    super.onReceivedTouchIconUrl(webView, str, z);
                }
            }

            @Override // android.webkit.WebChromeClient
            public void onShowCustomView(View view, WebChromeClient.CustomViewCallback customViewCallback) {
                if (HuyaUdbBridgeWebView.this.webChromeClient != null) {
                    HuyaUdbBridgeWebView.this.webChromeClient.onShowCustomView(view, customViewCallback);
                } else {
                    super.onShowCustomView(view, customViewCallback);
                }
            }

            @Override // android.webkit.WebChromeClient
            public void onShowCustomView(View view, int i, WebChromeClient.CustomViewCallback customViewCallback) {
                if (HuyaUdbBridgeWebView.this.webChromeClient != null) {
                    HuyaUdbBridgeWebView.this.webChromeClient.onShowCustomView(view, i, customViewCallback);
                } else {
                    super.onShowCustomView(view, i, customViewCallback);
                }
            }

            @Override // android.webkit.WebChromeClient
            public void onHideCustomView() {
                if (HuyaUdbBridgeWebView.this.webChromeClient != null) {
                    HuyaUdbBridgeWebView.this.webChromeClient.onHideCustomView();
                } else {
                    super.onHideCustomView();
                }
            }

            @Override // android.webkit.WebChromeClient
            public boolean onCreateWindow(WebView webView, boolean z, boolean z2, Message message) {
                if (HuyaUdbBridgeWebView.this.webChromeClient != null) {
                    return HuyaUdbBridgeWebView.this.webChromeClient.onCreateWindow(webView, z, z2, message);
                }
                return super.onCreateWindow(webView, z, z2, message);
            }

            @Override // android.webkit.WebChromeClient
            public void onRequestFocus(WebView webView) {
                if (HuyaUdbBridgeWebView.this.webChromeClient != null) {
                    HuyaUdbBridgeWebView.this.webChromeClient.onRequestFocus(webView);
                } else {
                    super.onRequestFocus(webView);
                }
            }

            @Override // android.webkit.WebChromeClient
            public void onCloseWindow(WebView webView) {
                if (HuyaUdbBridgeWebView.this.webChromeClient != null) {
                    HuyaUdbBridgeWebView.this.webChromeClient.onCloseWindow(webView);
                } else {
                    super.onCloseWindow(webView);
                }
            }

            @Override // android.webkit.WebChromeClient
            public boolean onJsAlert(WebView webView, String str, String str2, final JsResult jsResult) {
                if (!HuyaUdbBridgeWebView.this.alertBoxBlock) {
                    jsResult.confirm();
                }
                if (HuyaUdbBridgeWebView.this.webChromeClient != null && HuyaUdbBridgeWebView.this.webChromeClient.onJsAlert(webView, str, str2, jsResult)) {
                    return true;
                }
                new AlertDialog.Builder(HuyaUdbBridgeWebView.this.getContext()).setMessage(str2).setCancelable(false).setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() { // from class: com.huyaudb.webview.HuyaUdbBridgeWebView.6.1
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        if (HuyaUdbBridgeWebView.this.alertBoxBlock) {
                            jsResult.confirm();
                        }
                    }
                }).create().show();
                return true;
            }

            @Override // android.webkit.WebChromeClient
            public boolean onJsConfirm(WebView webView, String str, String str2, final JsResult jsResult) {
                if (!HuyaUdbBridgeWebView.this.alertBoxBlock) {
                    jsResult.confirm();
                }
                if (HuyaUdbBridgeWebView.this.webChromeClient != null && HuyaUdbBridgeWebView.this.webChromeClient.onJsConfirm(webView, str, str2, jsResult)) {
                    return true;
                }
                DialogInterface.OnClickListener onClickListener = new DialogInterface.OnClickListener() { // from class: com.huyaudb.webview.HuyaUdbBridgeWebView.6.2
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (HuyaUdbBridgeWebView.this.alertBoxBlock) {
                            if (i == -1) {
                                jsResult.confirm();
                            } else {
                                jsResult.cancel();
                            }
                        }
                    }
                };
                new AlertDialog.Builder(HuyaUdbBridgeWebView.this.getContext()).setMessage(str2).setCancelable(false).setPositiveButton(R.string.ok, onClickListener).setNegativeButton(R.string.cancel, onClickListener).show();
                return true;
            }

            @Override // android.webkit.WebChromeClient
            public boolean onJsPrompt(WebView webView, String str, String str2, String str3, final JsPromptResult jsPromptResult) {
                if (Build.VERSION.SDK_INT > 16 || !str2.startsWith("_dsbridge=")) {
                    if (!HuyaUdbBridgeWebView.this.alertBoxBlock) {
                        jsPromptResult.confirm();
                    }
                    if (HuyaUdbBridgeWebView.this.webChromeClient != null && HuyaUdbBridgeWebView.this.webChromeClient.onJsPrompt(webView, str, str2, str3, jsPromptResult)) {
                        return true;
                    }
                    final EditText editText = new EditText(HuyaUdbBridgeWebView.this.getContext());
                    editText.setText(str3);
                    if (str3 != null) {
                        editText.setSelection(str3.length());
                    }
                    float f = HuyaUdbBridgeWebView.this.getContext().getResources().getDisplayMetrics().density;
                    DialogInterface.OnClickListener onClickListener = new DialogInterface.OnClickListener() { // from class: com.huyaudb.webview.HuyaUdbBridgeWebView.6.3
                        @Override // android.content.DialogInterface.OnClickListener
                        public void onClick(DialogInterface dialogInterface, int i) {
                            if (HuyaUdbBridgeWebView.this.alertBoxBlock) {
                                if (i == -1) {
                                    jsPromptResult.confirm(editText.getText().toString());
                                } else {
                                    jsPromptResult.cancel();
                                }
                            }
                        }
                    };
                    new AlertDialog.Builder(HuyaUdbBridgeWebView.this.getContext()).setTitle(str2).setView(editText).setCancelable(false).setPositiveButton(R.string.ok, onClickListener).setNegativeButton(R.string.cancel, onClickListener).show();
                    FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -2);
                    int i = (int) (16.0f * f);
                    layoutParams.setMargins(i, 0, i, 0);
                    layoutParams.gravity = 1;
                    editText.setLayoutParams(layoutParams);
                    int i2 = (int) (15.0f * f);
                    editText.setPadding(i2 - ((int) (f * 5.0f)), i2, i2, i2);
                    return true;
                }
                jsPromptResult.confirm(HuyaUdbBridgeWebView.this.innerJavascriptInterface.callHandler(str2.substring(10), str3));
                return true;
            }

            @Override // android.webkit.WebChromeClient
            public boolean onJsBeforeUnload(WebView webView, String str, String str2, JsResult jsResult) {
                if (HuyaUdbBridgeWebView.this.webChromeClient != null) {
                    return HuyaUdbBridgeWebView.this.webChromeClient.onJsBeforeUnload(webView, str, str2, jsResult);
                }
                return super.onJsBeforeUnload(webView, str, str2, jsResult);
            }

            @Override // android.webkit.WebChromeClient
            public void onExceededDatabaseQuota(String str, String str2, long j, long j2, long j3, WebStorage.QuotaUpdater quotaUpdater) {
                if (HuyaUdbBridgeWebView.this.webChromeClient != null) {
                    HuyaUdbBridgeWebView.this.webChromeClient.onExceededDatabaseQuota(str, str2, j, j2, j3, quotaUpdater);
                } else {
                    super.onExceededDatabaseQuota(str, str2, j, j2, j3, quotaUpdater);
                }
            }

            public void onReachedMaxAppCacheSize(long j, long j2, WebStorage.QuotaUpdater quotaUpdater) {
                if (HuyaUdbBridgeWebView.this.webChromeClient != null) {
                    HuyaUdbBridgeWebView.this.webChromeClient.onReachedMaxAppCacheSize(j, j2, quotaUpdater);
                }
                super.onReachedMaxAppCacheSize(j, j2, quotaUpdater);
            }

            @Override // android.webkit.WebChromeClient
            public void onGeolocationPermissionsShowPrompt(String str, GeolocationPermissions.Callback callback) {
                if (HuyaUdbBridgeWebView.this.webChromeClient != null) {
                    HuyaUdbBridgeWebView.this.webChromeClient.onGeolocationPermissionsShowPrompt(str, callback);
                } else {
                    super.onGeolocationPermissionsShowPrompt(str, callback);
                }
            }

            @Override // android.webkit.WebChromeClient
            public void onGeolocationPermissionsHidePrompt() {
                if (HuyaUdbBridgeWebView.this.webChromeClient != null) {
                    HuyaUdbBridgeWebView.this.webChromeClient.onGeolocationPermissionsHidePrompt();
                } else {
                    super.onGeolocationPermissionsHidePrompt();
                }
            }

            @Override // android.webkit.WebChromeClient
            public void onPermissionRequest(PermissionRequest permissionRequest) {
                if (HuyaUdbBridgeWebView.this.webChromeClient != null) {
                    HuyaUdbBridgeWebView.this.webChromeClient.onPermissionRequest(permissionRequest);
                } else {
                    super.onPermissionRequest(permissionRequest);
                }
            }

            @Override // android.webkit.WebChromeClient
            public void onPermissionRequestCanceled(PermissionRequest permissionRequest) {
                if (HuyaUdbBridgeWebView.this.webChromeClient != null) {
                    HuyaUdbBridgeWebView.this.webChromeClient.onPermissionRequestCanceled(permissionRequest);
                } else {
                    super.onPermissionRequestCanceled(permissionRequest);
                }
            }

            @Override // android.webkit.WebChromeClient
            public boolean onJsTimeout() {
                if (HuyaUdbBridgeWebView.this.webChromeClient != null) {
                    return HuyaUdbBridgeWebView.this.webChromeClient.onJsTimeout();
                }
                return super.onJsTimeout();
            }

            @Override // android.webkit.WebChromeClient
            public void onConsoleMessage(String str, int i, String str2) {
                if (HuyaUdbBridgeWebView.this.webChromeClient != null) {
                    HuyaUdbBridgeWebView.this.webChromeClient.onConsoleMessage(str, i, str2);
                } else {
                    super.onConsoleMessage(str, i, str2);
                }
            }

            @Override // android.webkit.WebChromeClient
            public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
                if (HuyaUdbBridgeWebView.this.webChromeClient != null) {
                    return HuyaUdbBridgeWebView.this.webChromeClient.onConsoleMessage(consoleMessage);
                }
                return super.onConsoleMessage(consoleMessage);
            }

            @Override // android.webkit.WebChromeClient
            public Bitmap getDefaultVideoPoster() {
                if (HuyaUdbBridgeWebView.this.webChromeClient != null) {
                    return HuyaUdbBridgeWebView.this.webChromeClient.getDefaultVideoPoster();
                }
                return super.getDefaultVideoPoster();
            }

            @Override // android.webkit.WebChromeClient
            public View getVideoLoadingProgressView() {
                if (HuyaUdbBridgeWebView.this.webChromeClient != null) {
                    return HuyaUdbBridgeWebView.this.webChromeClient.getVideoLoadingProgressView();
                }
                return super.getVideoLoadingProgressView();
            }

            @Override // android.webkit.WebChromeClient
            public void getVisitedHistory(ValueCallback<String[]> valueCallback) {
                if (HuyaUdbBridgeWebView.this.webChromeClient != null) {
                    HuyaUdbBridgeWebView.this.webChromeClient.getVisitedHistory(valueCallback);
                } else {
                    super.getVisitedHistory(valueCallback);
                }
            }

            @Override // android.webkit.WebChromeClient
            public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> valueCallback, WebChromeClient.FileChooserParams fileChooserParams) {
                if (HuyaUdbBridgeWebView.this.webChromeClient != null) {
                    return HuyaUdbBridgeWebView.this.webChromeClient.onShowFileChooser(webView, valueCallback, fileChooserParams);
                }
                return super.onShowFileChooser(webView, valueCallback, fileChooserParams);
            }

            public void openFileChooser(ValueCallback valueCallback, String str) {
                if (HuyaUdbBridgeWebView.this.webChromeClient instanceof FileChooser) {
                    ((FileChooser) HuyaUdbBridgeWebView.this.webChromeClient).openFileChooser(valueCallback, str);
                }
            }

            public void openFileChooser(ValueCallback<Uri> valueCallback, String str, String str2) {
                if (HuyaUdbBridgeWebView.this.webChromeClient instanceof FileChooser) {
                    ((FileChooser) HuyaUdbBridgeWebView.this.webChromeClient).openFileChooser(valueCallback, str, str2);
                }
            }
        };
        init();
    }

    public static void setWebContentsDebuggingEnabled(boolean z) {
        if (Build.VERSION.SDK_INT >= 19) {
            WebView.setWebContentsDebuggingEnabled(z);
        }
        isDebug = z;
    }

    private void init() {
        this.APP_CACHE_DIRNAME = getContext().getFilesDir().getAbsolutePath() + "/webcache";
        WebSettings settings = getSettings();
        settings.setDomStorageEnabled(true);
        if (Build.VERSION.SDK_INT >= 21) {
            CookieManager.getInstance().setAcceptThirdPartyCookies(this, true);
            settings.setMixedContentMode(0);
        }
        settings.setAllowFileAccess(false);
        settings.setAppCacheEnabled(false);
        settings.setCacheMode(2);
        settings.setJavaScriptEnabled(true);
        settings.setLoadWithOverviewMode(true);
        settings.setAppCachePath(this.APP_CACHE_DIRNAME);
        settings.setUseWideViewPort(true);
        super.setWebChromeClient(this.mWebChromeClient);
        addInternalJavascriptObject();
        if (Build.VERSION.SDK_INT > 16) {
            super.addJavascriptInterface(this.innerJavascriptInterface, BRIDGE_NAME);
        } else {
            settings.setUserAgentString(settings.getUserAgentString() + " _dsbridge");
        }
        setWebViewClient(new BaseWebViewClient(this));
    }

    class BaseWebViewClient extends WebViewClient {
        private WebView mView;

        public BaseWebViewClient(WebView webView) {
            this.mView = webView;
        }

        @Override // android.webkit.WebViewClient
        public void onPageFinished(WebView webView, String str) {
            super.onPageFinished(webView, str);
            this.mView.loadUrl("javascript:eval(function(p,a,c,k,e,r){e=function(c){return(c<a?'':e(parseInt(c/a)))+((c=c%a)>35?String.fromCharCode(c+29):c.toString(36))};if(!''.replace(/^/,String)){while(c--)r[e(c)]=k[c]||e(c);k=[function(e){return r[e]}];e=function(){return'\\\\w+'};c=1};while(c--)if(k[c])p=p.replace(new RegExp('\\\\b'+e(c)+'\\\\b','g'),k[c]);return p}('1l(1e(e,f,a,d,c,g){c=1e(b){1f(b<f?\"\":c(1m(b/f)))+(1n<(b%=f)?1h.1o(b+29):b.1p(1q))};1i(!\"\".1j(/^/,1h)){1g(;a--;)g[c(a)]=d[a]||c(a);d=[1e(b){1f g[b]}];c=1e(){1f\"\\\\\\\\w+\"};a=1}1g(;a--;)d[a]&&(e=e.1j(1r 1s(\"\\\\\\\\b\"+c(a)+\"\\\\\\\\b\",\"g\"),d[a]));1f e}(\\'o 9={M:4,6:3(d,a,b){o c=\"\";\"3\"==s a&&(b=a,a={});a={t:N 0===a?O:a};7(\"3\"==s b){o g=\"w\"+5.w++;5[g]=b;a.P=g}a=x.Q(a);7(5.u)c=u.6(d,a);y 7(5.R||-1!=S.T.U(\"u\"))c=V(\"u=\"+d,a);v x.B(c||\"{}\").t},z:3(d,a,b){b=b?5.i:5.8;5.C||(5.C=!0,W(3(){9.6(\"r.X\")},0));\"Y\"==s a?b.j[d]=a:b[d]=a},Z:3(d,a){4.z(d,a,!0)},D:3(d,a){v 4.6(\"r.D\",{10:d,11:a||\"12\"})},E:3(d){4.6(\"r.E\",{13:!1!==d})}};!3(){7(!5.8){o d={8:{j:{}},i:{j:{}},w:0,14:9,15:3(){9.6(\"r.16\")},17:3(b){o c=x.B(b.t),g={18:b.19,F:!0},f=4.8[b.A],e=4.i[b.A],m=3(k,l){g.t=k.G(l,c);9.6(\"r.H\",g)},n=3(k,l){c.1a(3(p,q){g.t=p;g.F=!1!==q;9.6(\"r.H\",g)});k.G(l,c)};7(f)m(f,4.8);y 7(e)n(e,4.i);y 7(f=b.A.I(\".\"),!(2>f.J)){b=f.K();f=f.L(\".\");e=4.8.j;e=e[f]||{};o h=e[b];h&&\"3\"==s h?m(h,e):(e=4.i.j,e=e[f]||{},(h=e[b])&&\"3\"==s h&&n(h,e))}}},a;1b(a 1c d)5[a]=d[a];9.z(\"1d\",3(b,c){c=b.I(\".\");7(2>c.J)v!(!8[c]&&!i[c]);b=c.K();c=c.L(\".\");v(c=8.j[c]||i.j[c])&&!!c[b]})}}();\\',1t,1u,\"   1e 1v 1w 1x 1i 1y 1z         1A 1B     1C   1D 1E 1F 1G 1f 1H 1I 1J 1K 1L 1M 1N 1O 1P 1Q 1R 1S 1k 1T 1U 1V 1W 1X 1Y 1Z 20 21 22 23 24 25 26 27 28 2a 2b 2c 2d 2e 2f 2g 2h 2i 2j 2k 2l 1g 2m 2n\".1k(\" \"),0,{}));',62,148,'||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||function|return|for|String|if|replace|split|eval|parseInt|35|fromCharCode|toString|36|new|RegExp|62|76|this|window|callHandler|_dsf|bridge|_dsaf|_obs|var|_dsb|typeof|data|_dsbridge|dscb|JSON|else|register|method|parse|_dsInit|hasNativeMethod|disableJavascriptDialogBlock|complete|apply|returnValue|length|pop|join|default|void|null|_dscbstub|stringify|_dswk|navigator|userAgent|indexOf|prompt|setTimeout|dsinit|object||registerAsyn|name|type|all|disable|WebViewJavascriptBridge|close|closePage|_handleMessageFromNative|id|callbackId|push|in|_hasJavascriptMethod'.split('|'),0,{}))");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String[] parseNamespace(String str) {
        String strSubstring;
        int iLastIndexOf = str.lastIndexOf(46);
        if (iLastIndexOf != -1) {
            strSubstring = str.substring(0, iLastIndexOf);
            str = str.substring(iLastIndexOf + 1);
        } else {
            strSubstring = "";
        }
        return new String[]{strSubstring, str};
    }

    private void addInternalJavascriptObject() {
        addJavascriptObject(new Object() { // from class: com.huyaudb.webview.HuyaUdbBridgeWebView.1
            /* JADX WARN: Removed duplicated region for block: B:10:0x0053  */
            @android.webkit.JavascriptInterface
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public boolean hasNativeMethod(java.lang.Object r9) throws org.json.JSONException {
                /*
                    r8 = this;
                    org.json.JSONObject r9 = (org.json.JSONObject) r9
                    java.lang.String r0 = "name"
                    java.lang.String r0 = r9.getString(r0)
                    java.lang.String r0 = r0.trim()
                    java.lang.String r1 = "type"
                    java.lang.String r9 = r9.getString(r1)
                    java.lang.String r9 = r9.trim()
                    com.huyaudb.webview.HuyaUdbBridgeWebView r1 = com.huyaudb.webview.HuyaUdbBridgeWebView.this
                    java.lang.String[] r0 = com.huyaudb.webview.HuyaUdbBridgeWebView.access$200(r1, r0)
                    com.huyaudb.webview.HuyaUdbBridgeWebView r1 = com.huyaudb.webview.HuyaUdbBridgeWebView.this
                    java.util.Map r1 = com.huyaudb.webview.HuyaUdbBridgeWebView.access$300(r1)
                    r2 = 0
                    r3 = r0[r2]
                    java.lang.Object r1 = r1.get(r3)
                    if (r1 == 0) goto L81
                    java.lang.Class r1 = r1.getClass()
                    r3 = 0
                    r4 = 1
                    r5 = r0[r4]     // Catch: java.lang.Exception -> L44
                    r6 = 2
                    java.lang.Class[] r6 = new java.lang.Class[r6]     // Catch: java.lang.Exception -> L44
                    java.lang.Class<java.lang.Object> r7 = java.lang.Object.class
                    r6[r2] = r7     // Catch: java.lang.Exception -> L44
                    java.lang.Class<com.huyaudb.webview.HuyaUdbBridgeWebView$CompletionHandler> r7 = com.huyaudb.webview.HuyaUdbBridgeWebView.CompletionHandler.class
                    r6[r4] = r7     // Catch: java.lang.Exception -> L44
                    java.lang.reflect.Method r3 = r1.getMethod(r5, r6)     // Catch: java.lang.Exception -> L44
                    r0 = 1
                    goto L51
                L44:
                    r0 = r0[r4]     // Catch: java.lang.Exception -> L50
                    java.lang.Class[] r5 = new java.lang.Class[r4]     // Catch: java.lang.Exception -> L50
                    java.lang.Class<java.lang.Object> r6 = java.lang.Object.class
                    r5[r2] = r6     // Catch: java.lang.Exception -> L50
                    java.lang.reflect.Method r3 = r1.getMethod(r0, r5)     // Catch: java.lang.Exception -> L50
                L50:
                    r0 = 0
                L51:
                    if (r3 == 0) goto L81
                    int r1 = android.os.Build.VERSION.SDK_INT
                    r5 = 17
                    if (r1 < r5) goto L64
                    java.lang.Class<android.webkit.JavascriptInterface> r1 = android.webkit.JavascriptInterface.class
                    java.lang.annotation.Annotation r1 = r3.getAnnotation(r1)
                    android.webkit.JavascriptInterface r1 = (android.webkit.JavascriptInterface) r1
                    if (r1 != 0) goto L64
                    return r2
                L64:
                    java.lang.String r1 = "all"
                    boolean r1 = r1.equals(r9)
                    if (r1 != 0) goto L80
                    if (r0 == 0) goto L76
                    java.lang.String r1 = "asyn"
                    boolean r1 = r1.equals(r9)
                    if (r1 != 0) goto L80
                L76:
                    if (r0 != 0) goto L81
                    java.lang.String r0 = "syn"
                    boolean r9 = r0.equals(r9)
                    if (r9 == 0) goto L81
                L80:
                    return r4
                L81:
                    return r2
                */
                throw new UnsupportedOperationException("Method not decompiled: com.huyaudb.webview.HuyaUdbBridgeWebView.AnonymousClass1.hasNativeMethod(java.lang.Object):boolean");
            }

            @JavascriptInterface
            public String closePage(Object obj) throws JSONException {
                HuyaUdbBridgeWebView.this.runOnMainThread(new Runnable() { // from class: com.huyaudb.webview.HuyaUdbBridgeWebView.1.1
                    @Override // java.lang.Runnable
                    public void run() {
                        if (HuyaUdbBridgeWebView.this.javascriptCloseWindowListener == null || HuyaUdbBridgeWebView.this.javascriptCloseWindowListener.onClose()) {
                            Context context = HuyaUdbBridgeWebView.this.getContext();
                            if (context instanceof Activity) {
                                ((Activity) context).onBackPressed();
                            }
                        }
                    }
                });
                return null;
            }

            @JavascriptInterface
            public void disableJavascriptDialogBlock(Object obj) throws JSONException {
                HuyaUdbBridgeWebView.this.alertBoxBlock = !((JSONObject) obj).getBoolean("disable");
            }

            @JavascriptInterface
            public void dsinit(Object obj) {
                HuyaUdbBridgeWebView.this.dispatchStartupQueue();
            }

            @JavascriptInterface
            public void returnValue(final Object obj) {
                HuyaUdbBridgeWebView.this.runOnMainThread(new Runnable() { // from class: com.huyaudb.webview.HuyaUdbBridgeWebView.1.2
                    @Override // java.lang.Runnable
                    public void run() {
                        JSONObject jSONObject = (JSONObject) obj;
                        try {
                            int i = jSONObject.getInt(SqTrackCommonKey.id);
                            boolean z = jSONObject.getBoolean("complete");
                            OnReturnValue onReturnValue = HuyaUdbBridgeWebView.this.handlerMap.get(Integer.valueOf(i));
                            Object obj2 = jSONObject.has(FunctionRouter.KEY_DATA) ? jSONObject.get(FunctionRouter.KEY_DATA) : null;
                            if (onReturnValue != null) {
                                onReturnValue.onValue(obj2);
                                if (z) {
                                    HuyaUdbBridgeWebView.this.handlerMap.remove(Integer.valueOf(i));
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        }, "_dsb");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void evaluateSelfJavascript(String str) {
        if (Build.VERSION.SDK_INT >= 19) {
            super.evaluateJavascript(str, null);
            return;
        }
        super.loadUrl("javascript:" + str);
    }

    public void evaluateJavascript(final String str) {
        runOnMainThread(new Runnable() { // from class: com.huyaudb.webview.HuyaUdbBridgeWebView.2
            @Override // java.lang.Runnable
            public void run() {
                HuyaUdbBridgeWebView.this.evaluateSelfJavascript(str);
            }
        });
    }

    @Override // android.webkit.WebView
    public void loadUrl(final String str) {
        runOnMainThread(new Runnable() { // from class: com.huyaudb.webview.HuyaUdbBridgeWebView.3
            @Override // java.lang.Runnable
            public void run() {
                String str2 = str;
                if (str2 != null && str2.startsWith("javascript:")) {
                    HuyaUdbBridgeWebView.super.loadUrl(str);
                    return;
                }
                HuyaUdbBridgeWebView.this.callInfoList = new ArrayList();
                HuyaUdbBridgeWebView.super.loadUrl(str);
            }
        });
    }

    @Override // android.webkit.WebView
    public void loadUrl(final String str, final Map<String, String> map) {
        runOnMainThread(new Runnable() { // from class: com.huyaudb.webview.HuyaUdbBridgeWebView.4
            @Override // java.lang.Runnable
            public void run() {
                String str2 = str;
                if (str2 != null && str2.startsWith("javascript:")) {
                    HuyaUdbBridgeWebView.super.loadUrl(str, map);
                    return;
                }
                HuyaUdbBridgeWebView.this.callInfoList = new ArrayList();
                HuyaUdbBridgeWebView.super.loadUrl(str, map);
            }
        });
    }

    @Override // android.webkit.WebView
    public void reload() {
        runOnMainThread(new Runnable() { // from class: com.huyaudb.webview.HuyaUdbBridgeWebView.5
            @Override // java.lang.Runnable
            public void run() {
                HuyaUdbBridgeWebView.this.callInfoList = new ArrayList();
                HuyaUdbBridgeWebView.super.reload();
            }
        });
    }

    public void setJavascriptCloseWindowListener(JavascriptCloseWindowListener javascriptCloseWindowListener) {
        this.javascriptCloseWindowListener = javascriptCloseWindowListener;
    }

    private static class CallInfo {
        private int callbackId;
        private String data;
        private String method;

        CallInfo(String str, int i, Object[] objArr) {
            this.data = new JSONArray((Collection) Arrays.asList(objArr == null ? new Object[0] : objArr)).toString();
            this.callbackId = i;
            this.method = str;
        }

        public String toString() {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("method", this.method);
                jSONObject.put("callbackId", this.callbackId);
                jSONObject.put(FunctionRouter.KEY_DATA, this.data);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return jSONObject.toString();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void dispatchStartupQueue() {
        if (this.callInfoList != null) {
            Iterator<CallInfo> it = this.callInfoList.iterator();
            while (it.hasNext()) {
                dispatchJavascriptCall(it.next());
            }
            this.callInfoList = null;
        }
    }

    private void dispatchJavascriptCall(CallInfo callInfo) {
        evaluateJavascript(String.format("window._handleMessageFromNative(%s)", callInfo.toString()));
    }

    public synchronized <T> void callHandler(String str, Object[] objArr, OnReturnValue<T> onReturnValue) {
        int i = this.callID + 1;
        this.callID = i;
        CallInfo callInfo = new CallInfo(str, i, objArr);
        if (onReturnValue != null) {
            this.handlerMap.put(Integer.valueOf(callInfo.callbackId), onReturnValue);
        }
        if (this.callInfoList != null) {
            this.callInfoList.add(callInfo);
        } else {
            dispatchJavascriptCall(callInfo);
        }
    }

    public void callHandler(String str, Object[] objArr) {
        callHandler(str, objArr, null);
    }

    public <T> void callHandler(String str, OnReturnValue<T> onReturnValue) {
        callHandler(str, null, onReturnValue);
    }

    public void hasJavascriptMethod(String str, OnReturnValue<Boolean> onReturnValue) {
        callHandler("_hasJavascriptMethod", new Object[]{str}, onReturnValue);
    }

    public void addJavascriptObject(Object obj, String str) {
        if (str == null) {
            str = "";
        }
        if (obj != null) {
            this.javaScriptNamespaceInterfaces.put(str, obj);
        }
    }

    public void removeJavascriptObject(String str) {
        if (str == null) {
            str = "";
        }
        this.javaScriptNamespaceInterfaces.remove(str);
    }

    public void disableJavascriptDialogBlock(boolean z) {
        this.alertBoxBlock = !z;
    }

    @Override // android.webkit.WebView
    public void setWebChromeClient(WebChromeClient webChromeClient) {
        this.webChromeClient = webChromeClient;
    }

    @Override // android.webkit.WebView
    public void clearCache(boolean z) {
        super.clearCache(z);
        CookieManager.getInstance().removeAllCookie();
        Context context = getContext();
        try {
            context.deleteDatabase("webview.db");
            context.deleteDatabase("webviewCache.db");
        } catch (Exception e) {
            e.printStackTrace();
        }
        File file = new File(this.APP_CACHE_DIRNAME);
        File file2 = new File(context.getCacheDir().getAbsolutePath() + "/webviewCache");
        if (file2.exists()) {
            deleteFile(file2);
        }
        if (file.exists()) {
            deleteFile(file);
        }
    }

    public void deleteFile(File file) {
        if (file.exists()) {
            if (file.isFile()) {
                file.delete();
            } else if (file.isDirectory()) {
                for (File file2 : file.listFiles()) {
                    deleteFile(file2);
                }
            }
            file.delete();
            return;
        }
        Log.e("Webview", "delete file no exists " + file.getAbsolutePath());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void runOnMainThread(Runnable runnable) {
        if (Looper.getMainLooper() == Looper.myLooper()) {
            runnable.run();
        } else {
            this.mainHandler.post(runnable);
        }
    }
}
