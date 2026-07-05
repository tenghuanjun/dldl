package com.aliyun.aliyunface.ui;

import android.R;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.webkit.JsPromptResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.AbsoluteLayout;
import android.widget.ProgressBar;
import com.alipay.zoloz.toyger.ToygerLog;
import com.aliyun.aliyunface.ToygerConst;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public class ToygerWebView extends WebView {
    private Handler mHandler;
    private ProgressBar mProgressBar;

    public ToygerWebView(Context context, AttributeSet attributeSet) {
        super(getFixedContext(context), attributeSet);
        ProgressBar progressBar = new ProgressBar(context, null, R.attr.progressBarStyleHorizontal);
        this.mProgressBar = progressBar;
        progressBar.setLayoutParams(new AbsoluteLayout.LayoutParams(-1, 3, 0, 0));
        addView(this.mProgressBar);
        init();
    }

    private static Context getFixedContext(Context context) {
        return (Build.VERSION.SDK_INT < 21 || Build.VERSION.SDK_INT >= 23) ? context : context.createConfigurationContext(new Configuration());
    }

    public void init() {
        removeJavascriptInterface("searchBoxJavaBridge_");
        removeJavascriptInterface("accessibility");
        removeJavascriptInterface("accessibilityTraversal");
        WebSettings settings = getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setCacheMode(-1);
        settings.setDomStorageEnabled(true);
        settings.setDatabaseEnabled(true);
        settings.setAllowFileAccess(false);
        settings.setSavePassword(false);
        settings.setSupportZoom(false);
        settings.setBuiltInZoomControls(false);
        setWebChromeClient(new ToygerWebChromeClient());
    }

    public void setHandler(Handler handler) {
        this.mHandler = handler;
    }

    public class ToygerWebChromeClient extends WebChromeClient {
        public ToygerWebChromeClient() {
        }

        @Override // android.webkit.WebChromeClient
        public void onProgressChanged(WebView webView, int i) {
            if (i == 100) {
                ToygerWebView.this.mProgressBar.setVisibility(8);
            } else {
                if (ToygerWebView.this.mProgressBar.getVisibility() == 8) {
                    ToygerWebView.this.mProgressBar.setVisibility(0);
                }
                ToygerWebView.this.mProgressBar.setProgress(i);
            }
            super.onProgressChanged(webView, i);
        }

        @Override // android.webkit.WebChromeClient
        public boolean onJsPrompt(WebView webView, String str, String str2, String str3, JsPromptResult jsPromptResult) {
            ToygerLog.e("onJsPrompt:" + str2);
            if ("face_auth".endsWith(str2)) {
                super.onJsPrompt(webView, str, str2, str3, jsPromptResult);
                jsPromptResult.cancel();
                ToygerWebView.this.mHandler.sendMessage(ToygerWebView.this.mHandler.obtainMessage(ToygerConst.TOYGER_UI_MSG_GUID_FACE_AUTH));
                return true;
            }
            if ("navi_close".endsWith(str2)) {
                super.onJsPrompt(webView, str, str2, str3, jsPromptResult);
                jsPromptResult.cancel();
                ToygerWebView.this.mHandler.sendMessage(ToygerWebView.this.mHandler.obtainMessage(ToygerConst.TOYGER_UI_MSG_GUID_CLOSE));
                return true;
            }
            if ("guide_log".endsWith(str2)) {
                super.onJsPrompt(webView, str, str2, str3, jsPromptResult);
                jsPromptResult.cancel();
                Message messageObtainMessage = ToygerWebView.this.mHandler.obtainMessage(ToygerConst.TOYGER_UI_MSG_GUID_LOG);
                messageObtainMessage.obj = str3;
                ToygerWebView.this.mHandler.sendMessage(messageObtainMessage);
                return true;
            }
            return super.onJsPrompt(webView, str, str2, str3, jsPromptResult);
        }

        @Override // android.webkit.WebChromeClient
        public void onReceivedTitle(WebView webView, String str) {
            super.onReceivedTitle(webView, str);
            if (str != null) {
                if (str.contains("404") || str.contains("500")) {
                    ToygerWebView.this.mHandler.sendMessage(ToygerWebView.this.mHandler.obtainMessage(ToygerConst.TOYGER_UI_MSG_GUID_LOAD_LOCAL));
                }
            }
        }
    }

    @Override // android.webkit.WebView, android.view.View
    protected void onScrollChanged(int i, int i2, int i3, int i4) {
        AbsoluteLayout.LayoutParams layoutParams = (AbsoluteLayout.LayoutParams) this.mProgressBar.getLayoutParams();
        layoutParams.x = i;
        layoutParams.y = i2;
        this.mProgressBar.setLayoutParams(layoutParams);
        super.onScrollChanged(i, i2, i3, i4);
    }
}
