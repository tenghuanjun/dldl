package com.unionpay;

import android.app.Activity;
import android.os.Build;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import java.io.InputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes6.dex */
public class WebViewJavascriptBridge implements Serializable {
    ae _messageHandler;
    Activity mContext;
    WebView mWebView;
    private boolean mAllowScheme = false;
    Map _messageHandlers = new HashMap();
    Map _responseCallbacks = new HashMap();
    long _uniqueId = 0;

    public WebViewJavascriptBridge(Activity activity, WebView webView, ae aeVar) {
        byte b = 0;
        this.mContext = activity;
        this.mWebView = webView;
        this._messageHandler = aeVar;
        WebSettings settings = this.mWebView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setSavePassword(false);
        settings.setAllowFileAccess(false);
        settings.setDomStorageEnabled(true);
        try {
            if (Build.VERSION.SDK_INT >= 11) {
                this.mWebView.removeJavascriptInterface("accessibility");
                this.mWebView.removeJavascriptInterface("accessibilityTraversal");
                this.mWebView.removeJavascriptInterface("searchBoxJavaBridge_");
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        this.mWebView.addJavascriptInterface(this, "_WebViewJavascriptBridge");
        this.mWebView.setWebViewClient(new ad(this, b));
        this.mWebView.setWebChromeClient(new ac(this, (byte) 0));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void _callbackJs(String str, String str2) {
        HashMap map = new HashMap();
        map.put("responseId", str);
        map.put("responseData", str2);
        _dispatchMessage(map);
    }

    private void _dispatchMessage(Map map) {
        String string = new JSONObject(map).toString();
        com.unionpay.utils.j.a("test", "sending:" + string);
        this.mContext.runOnUiThread(new aa(this, String.format("javascript:WebViewJavascriptBridge._handleMessageFromJava('%s');", doubleEscapeString(string))));
    }

    private void _sendData(String str, af afVar, String str2) {
        HashMap map = new HashMap();
        map.put(com.alipay.sdk.packet.e.k, str);
        if (afVar != null) {
            StringBuilder sb = new StringBuilder("java_cb_");
            long j = this._uniqueId + 1;
            this._uniqueId = j;
            sb.append(j);
            String string = sb.toString();
            this._responseCallbacks.put(string, afVar);
            map.put("callbackId", string);
        }
        if (str2 != null) {
            map.put("handlerName", str2);
        }
        _dispatchMessage(map);
    }

    public static String convertStreamToString(InputStream inputStream) {
        String next;
        next = "";
        try {
            Scanner scannerUseDelimiter = new Scanner(inputStream, "UTF-8").useDelimiter("\\A");
            next = scannerUseDelimiter.hasNext() ? scannerUseDelimiter.next() : "";
            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return next;
    }

    private String doubleEscapeString(String str) {
        return str.replace("\\", "\\\\").replace("\"", "\\\"").replace("'", "\\'").replace("\n", "\\n").replace("\r", "\\r").replace("\f", "\\f");
    }

    private void loadWebViewJavascriptBridgeJs(WebView webView) {
        webView.loadUrl("javascript:" + convertStreamToString(getClass().getResourceAsStream("res/webviewjavascriptbridge.js")));
    }

    @JavascriptInterface
    public void _handleMessageFromJs(String str, String str2, String str3, String str4, String str5) {
        ae aeVar;
        if (str2 != null) {
            ((af) this._responseCallbacks.get(str2)).a(str3);
            this._responseCallbacks.remove(str2);
            return;
        }
        ab abVar = str4 != null ? new ab(this, str4) : null;
        if (str5 != null) {
            aeVar = (ae) this._messageHandlers.get(str5);
            if (aeVar == null) {
                com.unionpay.utils.j.c("test", "WVJB Warning: No handler for " + str5);
                return;
            }
        } else {
            aeVar = this._messageHandler;
        }
        try {
            this.mContext.runOnUiThread(new z(this, aeVar, str, abVar));
        } catch (Exception e) {
            com.unionpay.utils.j.c("test", "WebViewJavascriptBridge: WARNING: java handler threw. " + e.getMessage());
        }
    }

    public void callHandler(String str) {
        callHandler(str, null, null);
    }

    public void callHandler(String str, String str2) {
        callHandler(str, str2, null);
    }

    public void callHandler(String str, String str2, af afVar) {
        _sendData(str2, afVar, str);
    }

    public void registerHandler(String str, ae aeVar) {
        this._messageHandlers.put(str, aeVar);
    }

    public void send(String str) {
        send(str, null);
    }

    public void send(String str, af afVar) {
        _sendData(str, afVar, null);
    }

    public void setAllowScheme(boolean z) {
        this.mAllowScheme = z;
    }
}
