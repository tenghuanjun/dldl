package com.sqwan.common.dialog;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.JavascriptInterface;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.sq.sdk.tool.util.NetworkUtils;
import com.sqwan.common.mod.share.IShareResultListener;
import com.sqwan.common.util.AndroidBug5497Workaround;
import com.sqwan.common.util.LogUtil;
import com.sqwan.common.util.SqResUtils;
import com.sqwan.common.util.StatusBarUtil;
import com.sqwan.common.util.ToastUtil;
import com.sqwan.common.web.WebShareUtil;
import com.sqwan.common.webview.SQCommonJsInterface;
import com.sy37sdk.share.UrlConstant;
import java.util.HashMap;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
@Deprecated
public class BaseNormalDialog extends FullScreenDialog {
    private static final long TIME_OUT_STEMP = 7500;
    private boolean isNetCon;
    protected Context mContext;
    protected Handler mHandler;
    private String mUrl;
    protected WebView mWebView;
    private Runnable timeOutRunnable;
    protected View view;

    public String getPopData() {
        return "";
    }

    protected void jsOpenUrl(String str) {
    }

    protected void onPageFinished(WebView webView, String str) {
    }

    protected void onPageStarted(WebView webView, String str, Bitmap bitmap) {
    }

    protected void onReceivedError(WebView webView, WebResourceRequest webResourceRequest, WebResourceError webResourceError) {
    }

    protected void timeOut() {
    }

    public BaseNormalDialog(Context context) {
        super(context);
        this.isNetCon = true;
        this.mContext = context;
        this.mHandler = new Handler(Looper.getMainLooper());
    }

    public BaseNormalDialog(Context context, int i) {
        super(context, i);
        this.isNetCon = true;
        this.mContext = context;
        this.mHandler = new Handler(Looper.getMainLooper());
    }

    public BaseNormalDialog(Context context, boolean z, DialogInterface.OnCancelListener onCancelListener) {
        super(context, z, onCancelListener);
        this.isNetCon = true;
        this.mContext = context;
        this.mHandler = new Handler(Looper.getMainLooper());
    }

    @Override // com.sqwan.common.dialog.FullScreenDialog, android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getWindow().getDecorView().setSystemUiVisibility(1028);
        if (Build.VERSION.SDK_INT >= 28) {
            WindowManager.LayoutParams attributes = getWindow().getAttributes();
            attributes.layoutInDisplayCutoutMode = 1;
            getWindow().setAttributes(attributes);
        }
        getWindow().getDecorView().setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() { // from class: com.sqwan.common.dialog.BaseNormalDialog.1
            @Override // android.view.View.OnSystemUiVisibilityChangeListener
            public void onSystemUiVisibilityChange(int i) {
                StatusBarUtil.hideSystemUI(BaseNormalDialog.this.getWindow());
            }
        });
        View viewInflate = ((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(SqResUtils.getLayoutId(getContext(), "sy37_base_web_normal_dialog"), (ViewGroup) null);
        this.view = viewInflate;
        this.mWebView = (WebView) viewInflate.findViewById(SqResUtils.getId(getContext(), "webView"));
        setContentView(this.view);
        AndroidBug5497Workaround();
        initWebView();
    }

    protected void initWebView() {
        this.mWebView.setBackgroundColor(0);
        this.mWebView.getSettings().setAppCacheMaxSize(5242880L);
        this.mWebView.getSettings().setAppCachePath(this.mContext.getApplicationContext().getCacheDir().getAbsolutePath());
        this.mWebView.getSettings().setAllowFileAccess(true);
        this.mWebView.getSettings().setAppCacheEnabled(true);
        this.mWebView.getSettings().setBuiltInZoomControls(false);
        this.mWebView.getSettings().setSupportZoom(false);
        this.mWebView.getSettings().setUseWideViewPort(true);
        this.mWebView.getSettings().setLoadWithOverviewMode(true);
        this.mWebView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        this.mWebView.getSettings().setJavaScriptEnabled(true);
        this.mWebView.addJavascriptInterface(new JsObj(this.mContext), SQCommonJsInterface.INTERFACE_NAME);
        this.mWebView.loadUrl("javascript:window.packageName('" + this.mContext.getPackageName() + "')");
        this.mWebView.setWebViewClient(new SqWebViewClient());
        this.mWebView.getSettings().setTextZoom(100);
        loadUrl();
    }

    protected void loadUrl() {
        WebView webView;
        if (!NetworkUtils.isNetworkConnected(this.mContext) || (webView = this.mWebView) == null) {
            return;
        }
        webView.loadUrl(this.mUrl);
        timeOutCheck();
    }

    private void timeOutCheck() {
        if (this.timeOutRunnable == null) {
            this.timeOutRunnable = new Runnable() { // from class: com.sqwan.common.dialog.BaseNormalDialog.2
                @Override // java.lang.Runnable
                public void run() {
                    if (BaseNormalDialog.this.isNetCon) {
                        return;
                    }
                    LogUtil.e("网络连接7.5秒超时");
                    BaseNormalDialog.this.timeOut();
                }
            };
        }
        this.mHandler.postDelayed(this.timeOutRunnable, TIME_OUT_STEMP);
    }

    protected void jsClose(String str, String str2) {
        dismiss();
    }

    protected void jsToast(final String str) {
        this.mHandler.post(new Runnable() { // from class: com.sqwan.common.dialog.BaseNormalDialog.3
            @Override // java.lang.Runnable
            public void run() {
                ToastUtil.showToast(BaseNormalDialog.this.mContext, str);
            }
        });
    }

    public void setUrl(String str) {
        this.mUrl = str;
    }

    public class JsObj {
        private Context jsContext;

        public JsObj(Context context) {
            this.jsContext = context;
        }

        @JavascriptInterface
        public void enClose(String str, String str2) {
            LogUtil.i("enClose --> tag:" + str + ",data:" + str2);
            BaseNormalDialog.this.jsClose(str, str2);
        }

        @JavascriptInterface
        public void enToast(String str) {
            LogUtil.i("enToast --> " + str);
            BaseNormalDialog.this.jsToast(str);
        }

        @JavascriptInterface
        public void sqOpenUrl(String str) {
            LogUtil.i("sqOpenUrl --> " + str);
            BaseNormalDialog.this.jsOpenUrl(str);
        }

        @JavascriptInterface
        public void enClose() {
            LogUtil.i("enClose");
            BaseNormalDialog.this.jsClose("", "");
        }

        @JavascriptInterface
        public void sqOpenUrl() {
            LogUtil.i("sqOpenUrl");
            BaseNormalDialog.this.jsOpenUrl("");
        }

        @JavascriptInterface
        public String getPopData() {
            LogUtil.i("getPopData");
            return BaseNormalDialog.this.getPopData();
        }

        @JavascriptInterface
        public void handleToolbar(String str) {
            LogUtil.i("wap 调用handleToolbar " + str);
        }

        /* JADX INFO: renamed from: com.sqwan.common.dialog.BaseNormalDialog$JsObj$1, reason: invalid class name */
        class AnonymousClass1 implements Runnable {
            final /* synthetic */ String val$json;

            AnonymousClass1(String str) {
                this.val$json = str;
            }

            @Override // java.lang.Runnable
            public void run() {
                WebShareUtil.parseJsShare(BaseNormalDialog.this.getContext(), this.val$json, new IShareResultListener() { // from class: com.sqwan.common.dialog.BaseNormalDialog.JsObj.1.1
                    @Override // com.sqwan.common.mod.share.IShareResultListener
                    public void onSuccess(Bundle bundle) {
                        LogUtil.i(UrlConstant.KEY_SHARE, "分享成功");
                        ((Activity) BaseNormalDialog.this.mContext).runOnUiThread(new Runnable() { // from class: com.sqwan.common.dialog.BaseNormalDialog.JsObj.1.1.1
                            @Override // java.lang.Runnable
                            public void run() {
                                BaseNormalDialog.this.mWebView.loadUrl("javascript:window.fee.shareSuccessCallback('" + new JSONObject() + "')");
                            }
                        });
                    }

                    @Override // com.sqwan.common.mod.share.IShareResultListener
                    public void onFailture(int i, String str) {
                        LogUtil.i(UrlConstant.KEY_SHARE, "分享失败 code = " + i + ", msg = " + str);
                        final HashMap map = new HashMap();
                        map.put("code", Integer.valueOf(i));
                        map.put("msg", str);
                        ((Activity) BaseNormalDialog.this.mContext).runOnUiThread(new Runnable() { // from class: com.sqwan.common.dialog.BaseNormalDialog.JsObj.1.1.2
                            @Override // java.lang.Runnable
                            public void run() {
                                BaseNormalDialog.this.mWebView.loadUrl("javascript:window.fee.shareFailureCallback('" + new JSONObject(map) + "')");
                            }
                        });
                    }
                });
            }
        }

        @JavascriptInterface
        public void share(String str) {
            LogUtil.i("wap share " + str);
            ((Activity) BaseNormalDialog.this.mContext).runOnUiThread(new AnonymousClass1(str));
        }
    }

    private class SqWebViewClient extends WebViewClient {
        private SqWebViewClient() {
        }

        @Override // android.webkit.WebViewClient
        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            super.onPageStarted(webView, str, bitmap);
            BaseNormalDialog.this.isNetCon = false;
            BaseNormalDialog.this.onPageStarted(webView, str, bitmap);
        }

        @Override // android.webkit.WebViewClient
        public void onPageFinished(WebView webView, String str) {
            super.onPageFinished(webView, str);
            BaseNormalDialog.this.isNetCon = true;
            BaseNormalDialog.this.mHandler.removeCallbacks(BaseNormalDialog.this.timeOutRunnable);
            BaseNormalDialog.this.onPageFinished(webView, str);
        }

        @Override // android.webkit.WebViewClient
        public void onReceivedError(WebView webView, WebResourceRequest webResourceRequest, WebResourceError webResourceError) {
            super.onReceivedError(webView, webResourceRequest, webResourceError);
            BaseNormalDialog.this.onReceivedError(webView, webResourceRequest, webResourceError);
        }

        @Override // android.webkit.WebViewClient
        public void onLoadResource(WebView webView, String str) {
            super.onLoadResource(webView, str);
        }
    }

    protected void AndroidBug5497Workaround() {
        View view;
        Context context = this.mContext;
        if (context == null || (view = this.view) == null || !(context instanceof Activity)) {
            return;
        }
        AndroidBug5497Workaround.assistActivity((Activity) context, view);
    }
}
