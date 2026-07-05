package com.sq.webview.ui;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import com.sq.webview.SQBaseJSInterface;
import com.sq.webview.SQWebAgent;
import com.sq.webview.WebHook;
import com.sq.webview.view.LandWebViewActivity;
import com.sq.webview.view.PortraitWebViewActivity;
import com.sq.webview.view.SQWebFragment;
import com.sq.webview.view.SQWebView;
import com.sq.webview.view.SQWebViewDialog;
import com.sq.webview.view.WebViewActivity;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class WebUICreator {
    private boolean mIsShowTitleBar;
    private boolean mIsShowToolbar;
    private SQBaseJSInterface mJsInterface;
    private final SQWebAgent mSQWebAgent;
    private String title;
    private String url;
    private int mBackgroundColor = 0;
    private List<WebHook> mWebHooks = new ArrayList();
    private boolean mHideSystemBar = true;

    public enum DialogStyle {
        DIALOG_STYLE_HALF_SCREEN,
        DIALOG_STYLE_FULL_SCREEN
    }

    public enum PageStyle {
        PAGE_ORIENTATION_FOLLOW,
        PAGE_ORIENTATION_VERTICAL,
        PAGE_ORIENTATION_HORIZONTAL
    }

    private WebUICreator(SQWebAgent webAgent) {
        this.mSQWebAgent = webAgent;
    }

    public static WebUICreator with(SQWebAgent agent) {
        return new WebUICreator(agent);
    }

    public WebUICreator url(String url) {
        this.url = url;
        return this;
    }

    public WebUICreator title(String title) {
        this.title = title;
        return this;
    }

    public WebUICreator extraWebHook(WebHook webHook) {
        this.mWebHooks.add(webHook);
        return this;
    }

    public WebUICreator extraWebHooks(List<WebHook> webHooks) {
        this.mWebHooks.addAll(webHooks);
        return this;
    }

    public WebUICreator backgroundColor(int color) {
        this.mBackgroundColor = color;
        return this;
    }

    public WebUICreator hideSystemBar(boolean hide) {
        this.mHideSystemBar = hide;
        return this;
    }

    public WebUICreator showTitleBar(boolean isShow) {
        this.mIsShowTitleBar = isShow;
        return this;
    }

    public WebUICreator showToolBar(boolean isShow) {
        this.mIsShowToolbar = isShow;
        return this;
    }

    public WebUICreator jsInterface(SQBaseJSInterface jsInterface) {
        this.mJsInterface = jsInterface;
        return this;
    }

    public SQWebView createSQWebView(Context context, View.OnClickListener closeClickListener) {
        SQWebView sQWebView = new SQWebView(context);
        sQWebView.setTitle(this.title);
        sQWebView.setTitleCloseListener(closeClickListener);
        sQWebView.setWebAgent(this.mSQWebAgent);
        sQWebView.refreshTitleBar(this.mIsShowTitleBar);
        sQWebView.refreshToolBar(this.mIsShowToolbar);
        SQBaseJSInterface sQBaseJSInterface = this.mJsInterface;
        if (sQBaseJSInterface != null) {
            sQBaseJSInterface.setWebView(sQWebView.getRealWebView());
            SQBaseJSInterface sQBaseJSInterface2 = this.mJsInterface;
            sQWebView.addJavascriptInterface(sQBaseJSInterface2, sQBaseJSInterface2.getInterfaceName());
        }
        if (!TextUtils.isEmpty(this.url)) {
            sQWebView.loadUrl(this.url);
        }
        sQWebView.getRealWebView().setBackgroundColor(this.mBackgroundColor);
        return sQWebView;
    }

    public SQWebViewDialog createSQWebDialog(Context context, DialogStyle style) {
        SQWebViewDialog sQWebViewDialog = new SQWebViewDialog(context);
        sQWebViewDialog.setSQWebAgent(this.mSQWebAgent);
        if (style == DialogStyle.DIALOG_STYLE_FULL_SCREEN) {
            sQWebViewDialog.setPortraitHeightWeight(100);
        } else if (style == DialogStyle.DIALOG_STYLE_HALF_SCREEN) {
            sQWebViewDialog.setPortraitHeightWeight(60);
        }
        sQWebViewDialog.setSQBaseJSInterface(this.mJsInterface);
        sQWebViewDialog.setDialogTitle(this.title);
        sQWebViewDialog.setShowTitleBar(this.mIsShowTitleBar);
        sQWebViewDialog.setShowTooleBar(this.mIsShowToolbar);
        sQWebViewDialog.setWebViewBackground(this.mBackgroundColor);
        sQWebViewDialog.setUrl(this.url);
        sQWebViewDialog.addWebHooks(this.mWebHooks);
        return sQWebViewDialog;
    }

    public SQWebFragment createSQWebFragment() {
        Bundle bundle = new Bundle();
        bundle.putString("url", this.url);
        bundle.putString("title", this.title);
        SQWebFragment sQWebFragmentNewInstance = SQWebFragment.newInstance(bundle);
        sQWebFragmentNewInstance.setSQWebAgent(this.mSQWebAgent);
        sQWebFragmentNewInstance.setJSInterface(this.mJsInterface);
        sQWebFragmentNewInstance.setWebViewBackground(this.mBackgroundColor);
        sQWebFragmentNewInstance.addWebHooks(this.mWebHooks);
        return sQWebFragmentNewInstance;
    }

    public void startWebPage(Context context, PageStyle pageStyle) {
        if (pageStyle == PageStyle.PAGE_ORIENTATION_HORIZONTAL) {
            LandWebViewActivity.startPage(context, this.url, this.title, this.mSQWebAgent, this.mIsShowToolbar, this.mIsShowTitleBar, this.mHideSystemBar, this.mJsInterface, this.mWebHooks, this.mBackgroundColor);
        } else if (pageStyle == PageStyle.PAGE_ORIENTATION_VERTICAL) {
            PortraitWebViewActivity.startPage(context, this.url, this.title, this.mSQWebAgent, this.mIsShowToolbar, this.mIsShowTitleBar, this.mHideSystemBar, this.mJsInterface, this.mWebHooks, this.mBackgroundColor);
        } else {
            WebViewActivity.startPage(context, this.url, this.title, this.mSQWebAgent, this.mIsShowToolbar, this.mIsShowTitleBar, this.mHideSystemBar, this.mJsInterface, this.mWebHooks, this.mBackgroundColor);
        }
    }
}
