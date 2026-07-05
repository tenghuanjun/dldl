package com.sqwan.common.webview;

import android.content.Context;
import android.os.Build;
import android.webkit.WebSettings;
import android.webkit.WebView;
import com.sq.tool.network.EventReporter;
import com.sq.tool.network.ExceptionReporter;
import com.sq.webview.SQWebAgent;
import java.util.ArrayList;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class SQWeb {
    private static volatile SQWebAgent sInstance;

    public static SQWebAgent getInstance() {
        if (sInstance == null) {
            synchronized (SQWeb.class) {
                if (sInstance == null) {
                    sInstance = newAgent();
                }
            }
        }
        return sInstance;
    }

    private static SQWebAgent newAgent() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new UrlWebHook());
        arrayList.add(new PermissionWebHook());
        arrayList.add(new FileChooserWebHook());
        return new SQWebAgent.Builder().setCollectWhiteScreenEnable(true).enableLocalH5(true).enableMonitor().setExceptionReporter(new ExceptionReporter()).setEventReporter(new EventReporter()).setGlobalWebHooks(arrayList).configWebViewSetting(new SQWebAgent.IConfigWebView() { // from class: com.sqwan.common.webview.-$$Lambda$SQWeb$UB-uk5KcSNMtLUGYziyuR5T_VeM
            @Override // com.sq.webview.SQWebAgent.IConfigWebView
            public final void configWebView(WebView webView) {
                SQWeb.initWebViewSettings(webView);
            }
        }).build();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void initWebViewSettings(WebView webView) {
        if (webView == null) {
            return;
        }
        WebSettings settings = webView.getSettings();
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
        webView.setInitialScale(0);
        webView.requestFocusFromTouch();
        webView.requestFocus();
        webView.setFocusable(true);
        webView.setVerticalScrollBarEnabled(false);
        webView.setHorizontalScrollBarEnabled(false);
    }

    public static void enableCache(WebView webView) {
        if (webView == null || webView.getContext() == null) {
            return;
        }
        Context context = webView.getContext();
        WebSettings settings = webView.getSettings();
        webView.getSettings().setAppCacheMaxSize(5242880L);
        settings.setAppCacheEnabled(true);
        settings.setCacheMode(-1);
        settings.setAppCachePath(context.getCacheDir().getAbsolutePath());
    }
}
