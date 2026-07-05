package com.sqwan.common.webview;

import android.R;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.webkit.WebSettings;
import android.webkit.WebView;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class SQWebView extends WebView {
    public SQWebView(Context context) {
        this(context, null);
    }

    public SQWebView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.webViewStyle);
    }

    public SQWebView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        WebSettings settings = getSettings();
        settings.setDefaultTextEncodingName("utf-8");
        settings.setNeedInitialFocus(true);
        settings.setSaveFormData(false);
        settings.setSavePassword(false);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NORMAL);
        settings.setCacheMode(2);
        settings.setJavaScriptEnabled(true);
        settings.setAllowFileAccess(true);
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        settings.setSupportZoom(false);
        settings.setBuiltInZoomControls(false);
        settings.setDisplayZoomControls(false);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setLoadsImagesAutomatically(true);
        settings.setDomStorageEnabled(true);
        settings.setBlockNetworkImage(false);
        settings.setTextZoom(100);
        if (Build.VERSION.SDK_INT >= 21) {
            settings.setMixedContentMode(0);
        }
        setInitialScale(0);
        requestFocusFromTouch();
        requestFocus();
        setFocusable(true);
        setVerticalScrollBarEnabled(false);
        setHorizontalScrollBarEnabled(false);
    }

    public void onDestroy() {
        stopLoading();
        clearHistory();
        setWebChromeClient(null);
        setWebViewClient(null);
        removeAllViews();
        destroy();
    }
}
