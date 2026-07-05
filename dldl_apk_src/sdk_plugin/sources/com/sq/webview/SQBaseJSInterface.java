package com.sq.webview;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import com.sq.diagnostic.assistant.DiagnosticAssistant;
import com.sq.tool.logger.SQLog;
import com.sq.webview.permission.OnPermissionCallback;
import com.sq.webview.permission.SqPermissionUtil;
import com.sq.webview.ui.WebUICreator;
import com.sq.webview.util.AppUtils;
import com.sq.webview.util.ViewUtil;
import com.sq.webview.util.WebLogUtil;
import com.sq.webview.view.JSPageOperation;
import com.sqwan.liveshow.huya.SqR;
import com.taptap.sdk.db.biz.iap.lib2plus.BillingClientConstants;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class SQBaseJSInterface {
    private static final Handler HANDLER = new Handler(Looper.getMainLooper());
    private static final String TAG = "SQBaseJSInterface";
    private final Context mContext;
    private final String mInterfaceName;
    private JSPageOperation mJSPageOperation;
    private SQWebAgent mSQWebAgent;
    private WebView mWebView;

    public SQBaseJSInterface(String interfaceName, Context context) {
        this.mInterfaceName = interfaceName;
        this.mContext = context;
    }

    public void setSQWebAgent(SQWebAgent sqWebAgent) {
        this.mSQWebAgent = sqWebAgent;
    }

    public void setJSPageOperation(JSPageOperation jsPageOperation) {
        this.mJSPageOperation = jsPageOperation;
    }

    public void setWebView(WebView webView) {
        this.mWebView = webView;
    }

    @JavascriptInterface
    public void handleTitlebar(final String params) {
        log("handleToolbar", "params = " + params);
        post(new Runnable() { // from class: com.sq.webview.-$$Lambda$SQBaseJSInterface$UgIo7pDomrGYI-iRP9B2SU2QTfY
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$handleTitlebar$0$SQBaseJSInterface(params);
            }
        });
    }

    public /* synthetic */ void lambda$handleTitlebar$0$SQBaseJSInterface(final String params) {
        if (this.mJSPageOperation == null) {
            return;
        }
        try {
            this.mJSPageOperation.refreshTitleBar(new JSONObject(params).optBoolean("show"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @JavascriptInterface
    public void handleToolbar(final String params) {
        log("handleToolbar", "params = " + params);
        post(new Runnable() { // from class: com.sq.webview.-$$Lambda$SQBaseJSInterface$Be0LNowGTADYbNj0CInV1fAJyZA
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$handleToolbar$1$SQBaseJSInterface(params);
            }
        });
    }

    public /* synthetic */ void lambda$handleToolbar$1$SQBaseJSInterface(final String params) {
        if (this.mJSPageOperation == null) {
            return;
        }
        try {
            this.mJSPageOperation.refreshToolBar(new JSONObject(params).optBoolean("show"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @JavascriptInterface
    public void call(String phoneNumber) {
        WebLogUtil.d("SQBaseJSInterfacecall: " + phoneNumber);
        AppUtils.call(this.mContext, phoneNumber);
    }

    @JavascriptInterface
    public void email(String sendTo, String title, String content) {
        SQLog.d("SQBaseJSInterfaceemail: " + sendTo + ", " + title + ", " + content);
        AppUtils.email(this.mContext, sendTo, title, content);
    }

    @JavascriptInterface
    public void openApp(String pkgName) {
        SQLog.d("SQBaseJSInterfaceopenApp: " + pkgName);
        AppUtils.startAppWithPackageName(this.mContext, pkgName);
    }

    @JavascriptInterface
    public void copyToClipboard(String content) {
        SQLog.d("SQBaseJSInterfacecopyToClipboard: " + content);
        AppUtils.copyToClipboard(this.mContext, content);
    }

    @JavascriptInterface
    public void close() {
        log("close", "");
        post(new Runnable() { // from class: com.sq.webview.-$$Lambda$SQBaseJSInterface$eH90wS2luGCr9RAxSFhejx-_4kU
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$close$2$SQBaseJSInterface();
            }
        });
    }

    public /* synthetic */ void lambda$close$2$SQBaseJSInterface() {
        JSPageOperation jSPageOperation = this.mJSPageOperation;
        if (jSPageOperation != null) {
            jSPageOperation.close();
        }
    }

    @JavascriptInterface
    public void refresh() {
        log(SqR.string.refresh, "");
        post(new Runnable() { // from class: com.sq.webview.-$$Lambda$SQBaseJSInterface$x4x_qFW_bS9G5JFeXqWkHZmzNn0
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$refresh$3$SQBaseJSInterface();
            }
        });
    }

    public /* synthetic */ void lambda$refresh$3$SQBaseJSInterface() {
        JSPageOperation jSPageOperation = this.mJSPageOperation;
        if (jSPageOperation != null) {
            jSPageOperation.refresh();
        }
    }

    @JavascriptInterface
    public void invalidateBack(final String enable) {
        log("invalidateBack", enable);
        post(new Runnable() { // from class: com.sq.webview.-$$Lambda$SQBaseJSInterface$OTXI68-9dgJW7cinQRJxrc_15Wk
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$invalidateBack$4$SQBaseJSInterface(enable);
            }
        });
    }

    public /* synthetic */ void lambda$invalidateBack$4$SQBaseJSInterface(final String enable) {
        if (this.mJSPageOperation != null) {
            boolean z = false;
            try {
                z = Boolean.parseBoolean(enable);
            } catch (Exception e) {
                e.printStackTrace();
            }
            this.mJSPageOperation.invalidateBack(z);
        }
    }

    @JavascriptInterface
    public void openActionBrowser(String url) {
        SQLog.d("SQBaseJSInterfaceopenActionBrowser: " + url);
        if (TextUtils.isEmpty(url)) {
            WebLogUtil.e("打开的Url为空，不显示，Url=" + url);
            return;
        }
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(url));
        intent.addFlags(268435456);
        intent.addFlags(32768);
        this.mContext.startActivity(intent);
    }

    @JavascriptInterface
    public String packageName() {
        log(BillingClientConstants.PACKAGE_NAME, "");
        return this.mContext.getPackageName();
    }

    @JavascriptInterface
    public String getDeviceInfo() {
        log("getDeviceInfo", "");
        JSONObject jSONObject = new JSONObject();
        if (this.mWebView != null) {
            try {
                int i = this.mContext.getResources().getConfiguration().orientation;
                int width = this.mWebView.getWidth();
                int height = this.mWebView.getHeight();
                int screenHeight = ViewUtil.getScreenHeight(this.mContext);
                int screenWidth = ViewUtil.getScreenWidth(this.mContext);
                jSONObject.put("deviceOrientation", i);
                jSONObject.put("webviewWidth", width);
                jSONObject.put("webviewHeight", height);
                jSONObject.put("screenWidth", screenHeight);
                jSONObject.put("screenHeight", screenWidth);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return jSONObject.toString();
    }

    @JavascriptInterface
    public void openWebview(final String paramsJson) {
        log("openWebview", paramsJson);
        post(new Runnable() { // from class: com.sq.webview.-$$Lambda$SQBaseJSInterface$vQDtF2SO6dkYECZTgllAX93TMg0
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$openWebview$5$SQBaseJSInterface(paramsJson);
            }
        });
    }

    public /* synthetic */ void lambda$openWebview$5$SQBaseJSInterface(final String paramsJson) {
        try {
            Activity activityFromWebView = getActivityFromWebView();
            if (this.mSQWebAgent == null) {
                WebLogUtil.e("openWebview, but agent is null!");
                return;
            }
            if (activityFromWebView == null) {
                WebLogUtil.e("openWebview, but context is null!");
                return;
            }
            JSONObject jSONObject = new JSONObject(paramsJson);
            String strOptString = jSONObject.optString("url", "");
            int iOptInt = jSONObject.optInt("orientation");
            boolean zOptBoolean = jSONObject.optBoolean("transparent");
            boolean zOptBoolean2 = jSONObject.optBoolean("titlebar");
            boolean zOptBoolean3 = jSONObject.optBoolean("toolbar");
            WebUICreator webUICreatorUrl = WebUICreator.with(this.mSQWebAgent).url(strOptString);
            if (zOptBoolean) {
                webUICreatorUrl.backgroundColor(0);
            }
            webUICreatorUrl.showTitleBar(zOptBoolean2);
            webUICreatorUrl.showToolBar(zOptBoolean3);
            if (iOptInt == 1) {
                webUICreatorUrl.startWebPage(activityFromWebView, WebUICreator.PageStyle.PAGE_ORIENTATION_VERTICAL);
            } else if (iOptInt == 2) {
                webUICreatorUrl.startWebPage(activityFromWebView, WebUICreator.PageStyle.PAGE_ORIENTATION_HORIZONTAL);
            } else {
                webUICreatorUrl.createSQWebDialog(activityFromWebView, WebUICreator.DialogStyle.DIALOG_STYLE_FULL_SCREEN).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @JavascriptInterface
    public void requestPermission(final String params) {
        log("requestPermission", params);
        post(new Runnable() { // from class: com.sq.webview.-$$Lambda$SQBaseJSInterface$rwp7pXjJQiw3J_vOnrn9pDKll-8
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$requestPermission$6$SQBaseJSInterface(params);
            }
        });
    }

    public /* synthetic */ void lambda$requestPermission$6$SQBaseJSInterface(final String params) {
        try {
            Activity activityFromWebView = getActivityFromWebView();
            if (activityFromWebView == null) {
                WebLogUtil.i("requestPermission , activity is null");
                callRequestPermissionsResult(false);
                return;
            }
            ArrayList arrayList = new ArrayList();
            JSONArray jSONArray = new JSONArray(params);
            for (int i = 0; i < jSONArray.length(); i++) {
                arrayList.add(jSONArray.optJSONObject(i).optString("permission"));
            }
            SqPermissionUtil.with(activityFromWebView).permissions(arrayList).request(new OnPermissionCallback() { // from class: com.sq.webview.SQBaseJSInterface.1
                @Override // com.sq.webview.permission.OnPermissionCallback
                public void onGranted(List<String> permissions, boolean allGranted) {
                    WebLogUtil.i("onGranted allGranted ? " + allGranted);
                    SQBaseJSInterface.this.callRequestPermissionsResult(true);
                }

                @Override // com.sq.webview.permission.OnPermissionCallback
                public void onDenied(List<String> permissions, boolean doNotAskAgain) {
                    WebLogUtil.i("onDenied doNotAskAgain ? " + doNotAskAgain);
                    SQBaseJSInterface.this.callRequestPermissionsResult(false);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            callRequestPermissionsResult(false);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void callRequestPermissionsResult(boolean isSuccess) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("code", isSuccess ? 1 : 0);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        this.mWebView.evaluateJavascript("javascript:window." + getInterfaceName() + ".requestPermissionCallback(" + jSONObject + ")", null);
    }

    @JavascriptInterface
    public void openDiagnosticAssistant() {
        log("openDiagnosticAssistant", "");
        post(new Runnable() { // from class: com.sq.webview.-$$Lambda$SQBaseJSInterface$tN2PUIHZFS6E0Vq2koy50cRsIDs
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$openDiagnosticAssistant$7$SQBaseJSInterface();
            }
        });
    }

    public /* synthetic */ void lambda$openDiagnosticAssistant$7$SQBaseJSInterface() {
        try {
            DiagnosticAssistant.show(this.mContext);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Activity getActivityFromWebView() {
        for (Context context = this.mWebView.getContext(); context instanceof ContextWrapper; context = ((ContextWrapper) context).getBaseContext()) {
            if (context instanceof Activity) {
                return (Activity) context;
            }
        }
        return null;
    }

    private void post(Runnable runnable) {
        HANDLER.post(runnable);
    }

    private void log(String methodName, String message) {
        WebLogUtil.i("SQBaseJSInterface " + methodName + " " + message);
    }

    public String getInterfaceName() {
        return this.mInterfaceName;
    }
}
