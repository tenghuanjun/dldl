package com.sq.webview.view;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;
import com.sq.webview.SQBaseJSInterface;
import com.sq.webview.SQWebAgent;
import com.sq.webview.WebHook;
import com.sq.webview.util.StatusBarUtil;
import com.sq.webview.util.ViewUtil;
import com.sq.webview.util.WebResUtil;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class SQWebViewDialog extends FullScreenDialog implements JSPageOperation {
    private int mBackgroundColor;
    protected Context mContext;
    private boolean mIsShowTitleBar;
    private boolean mIsShowTooleBar;
    private int mPortraitHeightWeight;
    private LinearLayout mRootLayout;
    private SQBaseJSInterface mSQBaseJSInterface;
    private SQWebAgent mSQWebAgent;
    private SQWebView mSQWebView;
    private String mTitle;
    private String mUrl;
    private List<WebHook> mWebHooks;

    public SQWebViewDialog(Context context) {
        super(context);
        this.mWebHooks = new ArrayList();
        this.mBackgroundColor = 0;
        this.mPortraitHeightWeight = 100;
        this.mContext = context;
    }

    public SQWebViewDialog(Context context, int themeResId) {
        super(context, themeResId);
        this.mWebHooks = new ArrayList();
        this.mBackgroundColor = 0;
        this.mPortraitHeightWeight = 100;
        this.mContext = context;
    }

    public SQWebViewDialog(Context context, boolean cancelable, DialogInterface.OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        this.mWebHooks = new ArrayList();
        this.mBackgroundColor = 0;
        this.mPortraitHeightWeight = 100;
        this.mContext = context;
    }

    @Override // com.sq.webview.view.FullScreenDialog, android.app.Dialog
    protected void onCreate(Bundle savedInstanceState) {
        LinearLayout.LayoutParams layoutParams;
        super.onCreate(savedInstanceState);
        Window window = getWindow();
        if (window != null) {
            View decorView = window.getDecorView();
            decorView.setSystemUiVisibility(1028);
            decorView.setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() { // from class: com.sq.webview.view.SQWebViewDialog.1
                @Override // android.view.View.OnSystemUiVisibilityChangeListener
                public void onSystemUiVisibilityChange(int visibility) {
                    StatusBarUtil.hideSystemUI(SQWebViewDialog.this.getWindow());
                }
            });
        }
        window.setFlags(16777216, 16777216);
        LinearLayout linearLayout = (LinearLayout) ((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(WebResUtil.getLayoutId(getContext(), "sy37_web_view_layout_dialog"), (ViewGroup) null);
        this.mRootLayout = linearLayout;
        setContentView(linearLayout);
        initView(this.mRootLayout);
        if (this.mPortraitHeightWeight != 100) {
            LinearLayout linearLayout2 = (LinearLayout) findViewById(WebResUtil.getId(getContext(), "ll_web_dialog_view"));
            int screenHeight = ViewUtil.getScreenHeight(this.mContext);
            if (this.mContext.getResources().getConfiguration().orientation == 2) {
                linearLayout2.setOrientation(0);
                linearLayout2.setGravity(GravityCompat.START);
                layoutParams = new LinearLayout.LayoutParams(screenHeight, this.mSQWebView.getLayoutParams().height);
            } else {
                linearLayout2.setOrientation(1);
                linearLayout2.setGravity(80);
                layoutParams = new LinearLayout.LayoutParams(this.mSQWebView.getLayoutParams().width, 0, this.mPortraitHeightWeight);
            }
            this.mSQWebView.setLayoutParams(layoutParams);
        }
        this.mSQWebView.getRealWebView().setBackgroundColor(this.mBackgroundColor);
        this.mSQWebView.setHalfScreen(this.mPortraitHeightWeight != 100);
        this.mSQWebView.loadUrl(this.mUrl);
    }

    public void setPortraitHeightWeight(int portraitHeightWeight) {
        this.mPortraitHeightWeight = portraitHeightWeight;
    }

    public void setSQWebAgent(SQWebAgent agent) {
        this.mSQWebAgent = agent;
    }

    public void setUrl(String url) {
        this.mUrl = url;
    }

    public void setDialogTitle(String title) {
        this.mTitle = title;
    }

    public void setWebViewBackground(int color) {
        this.mBackgroundColor = color;
        SQWebView sQWebView = this.mSQWebView;
        if (sQWebView != null) {
            sQWebView.getRealWebView().setBackgroundColor(color);
        }
    }

    private void initView(View view) {
        SQWebView sQWebView = (SQWebView) view.findViewById(WebResUtil.getId(getContext(), "sq_wv"));
        this.mSQWebView = sQWebView;
        SQWebAgent sQWebAgent = this.mSQWebAgent;
        if (sQWebAgent != null) {
            sQWebView.setWebAgent(sQWebAgent);
            this.mSQWebView.getWebFunctionWrapper().addWebHooks(this.mWebHooks);
        }
        this.mSQWebView.setTitleCloseListener(new View.OnClickListener() { // from class: com.sq.webview.view.-$$Lambda$SQWebViewDialog$q0i0M7J7Rd-HPMTTEnVaaSbUssM
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                this.f$0.lambda$initView$0$SQWebViewDialog(view2);
            }
        });
        if (this.mSQBaseJSInterface == null) {
            this.mSQBaseJSInterface = new SQBaseJSInterface("Tools", getContext());
        }
        if (this.mContext.getResources().getConfiguration().orientation == 1 && this.mPortraitHeightWeight != 100) {
            this.mSQWebView.getTitleBar().setBackgroundResource(WebResUtil.getDrawableId(getContext(), "sy37_web_view_round_title_bar_bg"));
        } else {
            this.mSQWebView.getTitleBar().setBackgroundColor(Color.parseColor("#FFFFFF"));
        }
        this.mSQBaseJSInterface.setSQWebAgent(this.mSQWebAgent);
        this.mSQBaseJSInterface.setJSPageOperation(this);
        SQWebView sQWebView2 = this.mSQWebView;
        SQBaseJSInterface sQBaseJSInterface = this.mSQBaseJSInterface;
        sQWebView2.addJavascriptInterface(sQBaseJSInterface, sQBaseJSInterface.getInterfaceName());
        this.mSQBaseJSInterface.setWebView(this.mSQWebView.getRealWebView());
        refreshTitleBar(this.mIsShowTitleBar);
        refreshToolBar(this.mIsShowTooleBar);
        this.mSQWebView.setTitle(this.mTitle);
    }

    public /* synthetic */ void lambda$initView$0$SQWebViewDialog(View v) {
        dismiss();
    }

    public void setSQBaseJSInterface(SQBaseJSInterface jsInterface) {
        this.mSQBaseJSInterface = jsInterface;
    }

    @Override // com.sq.webview.view.JSPageOperation
    public void refreshToolBar(boolean visible) {
        this.mSQWebView.refreshToolBar(visible);
    }

    @Override // com.sq.webview.view.JSPageOperation
    public void refreshTitleBar(boolean visible) {
        this.mSQWebView.refreshTitleBar(visible);
    }

    @Override // com.sq.webview.view.JSPageOperation
    public void close() {
        dismiss();
    }

    @Override // com.sq.webview.view.JSPageOperation
    public void refresh() {
        this.mSQWebView.reload();
    }

    @Override // com.sq.webview.view.JSPageOperation
    public void invalidateBack(boolean enable) {
        setCancelable(enable);
    }

    public void setShowTitleBar(boolean showTitleBar) {
        this.mIsShowTitleBar = showTitleBar;
    }

    public void setShowTooleBar(boolean showTooleBar) {
        this.mIsShowTooleBar = showTooleBar;
    }

    public void addWebHook(WebHook webHook) {
        this.mWebHooks.add(webHook);
    }

    public void addWebHooks(List<WebHook> webHooks) {
        this.mWebHooks.addAll(webHooks);
    }
}
