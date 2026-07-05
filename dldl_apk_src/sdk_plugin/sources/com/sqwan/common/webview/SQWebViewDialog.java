package com.sqwan.common.webview;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.AnimationDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.view.GravityCompat;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.DownloadListener;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.sq.sdk.tool.util.DisplayUtil;
import com.sq.sdk.tool.util.NetworkUtils;
import com.sq.tools.Logger;
import com.sq.webview.SimpleWebHook;
import com.sq.webview.WebFunctionWrapper;
import com.sq.webview.local.ConfigCallback;
import com.sqwan.common.dialog.FullScreenDialog;
import com.sqwan.common.util.AndroidBug5497Workaround;
import com.sqwan.common.util.AppUtils;
import com.sqwan.common.util.SqResUtils;
import com.sqwan.common.util.StatusBarUtil;
import com.sqwan.common.util.ToastUtil;
import com.sqwan.common.web.WebViewToolBar;
import com.sqwan.msdk.config.ConfigManager;
import notchtools.geek.com.notchtools.NotchTools;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class SQWebViewDialog extends FullScreenDialog implements View.OnTouchListener, WebViewToolBar.WebToolBarClickListener {
    private static final Handler HANDLER = new Handler(Looper.getMainLooper());
    private static final int SCROLL_DOWN = 0;
    private static final int SCROLL_THRESHOLD = 50;
    private static final int SCROLL_UP = 1;
    private boolean mAdaptNotchScreen;
    private boolean mAllowJumpURL;
    private AnimationDrawable mAnimationDrawable;
    private boolean mCancelable;
    protected Context mContext;
    private int mCurScrollDirection;
    protected View mErrorNetView;
    private FrameLayout mFlVideoContainer;
    private boolean mLoadingFinish;
    private ImageView mLoadingIcon;
    private RelativeLayout mLoadingLayout;
    private int mPortraitHeightWeight;
    private LinearLayout mRootLayout;
    private boolean mShowBar;
    private final Runnable mShowLoadingRunnable;
    private boolean mShowVideo;
    private boolean mShowWebBar;
    private Runnable mTimeOutRunnable;
    protected String mUrl;
    private float mViewDownY;
    private WebViewToolBar.WebToolBarClickListener mWebBarClickListener;
    protected SQWebView mWebView;
    private int mWebViewBackgroundColor;
    private WebViewToolBar mWebViewToolBar;
    private boolean needUnionCloseRetry;
    private boolean unionCloseCalled;

    protected String getLayoutName() {
        return "sy37_web_view_dialog";
    }

    public String getPopData() {
        return "";
    }

    protected void jsCertificate(String str, String str2) {
    }

    protected void jsEnLogin() {
    }

    @Override // android.view.View.OnTouchListener
    public boolean onTouch(View view, MotionEvent motionEvent) {
        return false;
    }

    protected void timeOut() {
    }

    public SQWebViewDialog(Context context) {
        super(context);
        this.mLoadingFinish = true;
        this.mCurScrollDirection = -1;
        this.mPortraitHeightWeight = 100;
        this.mWebViewBackgroundColor = 0;
        this.mAllowJumpURL = true;
        this.mShowLoadingRunnable = new Runnable() { // from class: com.sqwan.common.webview.SQWebViewDialog.1
            @Override // java.lang.Runnable
            public void run() {
                SQWebViewDialog.this.showGifLoadingImage();
            }
        };
        this.unionCloseCalled = false;
        this.needUnionCloseRetry = false;
        this.mAdaptNotchScreen = false;
        this.mCancelable = true;
        this.mContext = context;
    }

    public SQWebViewDialog(Context context, int i) {
        super(context, i);
        this.mLoadingFinish = true;
        this.mCurScrollDirection = -1;
        this.mPortraitHeightWeight = 100;
        this.mWebViewBackgroundColor = 0;
        this.mAllowJumpURL = true;
        this.mShowLoadingRunnable = new Runnable() { // from class: com.sqwan.common.webview.SQWebViewDialog.1
            @Override // java.lang.Runnable
            public void run() {
                SQWebViewDialog.this.showGifLoadingImage();
            }
        };
        this.unionCloseCalled = false;
        this.needUnionCloseRetry = false;
        this.mAdaptNotchScreen = false;
        this.mCancelable = true;
        this.mContext = context;
    }

    public SQWebViewDialog(Context context, boolean z, DialogInterface.OnCancelListener onCancelListener) {
        super(context, z, onCancelListener);
        this.mLoadingFinish = true;
        this.mCurScrollDirection = -1;
        this.mPortraitHeightWeight = 100;
        this.mWebViewBackgroundColor = 0;
        this.mAllowJumpURL = true;
        this.mShowLoadingRunnable = new Runnable() { // from class: com.sqwan.common.webview.SQWebViewDialog.1
            @Override // java.lang.Runnable
            public void run() {
                SQWebViewDialog.this.showGifLoadingImage();
            }
        };
        this.unionCloseCalled = false;
        this.needUnionCloseRetry = false;
        this.mAdaptNotchScreen = false;
        this.mCancelable = true;
        this.mContext = context;
    }

    @Override // com.sqwan.common.dialog.FullScreenDialog, android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Window window = getWindow();
        if (window != null) {
            View decorView = window.getDecorView();
            decorView.setSystemUiVisibility(1028);
            decorView.setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() { // from class: com.sqwan.common.webview.SQWebViewDialog.2
                @Override // android.view.View.OnSystemUiVisibilityChangeListener
                public void onSystemUiVisibilityChange(int i) {
                    StatusBarUtil.hideSystemUI(SQWebViewDialog.this.getWindow());
                }
            });
            if (Build.VERSION.SDK_INT >= 28) {
                WindowManager.LayoutParams attributes = window.getAttributes();
                attributes.layoutInDisplayCutoutMode = 1;
                window.setAttributes(attributes);
            }
        }
        window.setFlags(16777216, 16777216);
        LinearLayout linearLayout = (LinearLayout) ((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(SqResUtils.getLayoutId(getContext(), getLayoutName()), (ViewGroup) null);
        this.mRootLayout = linearLayout;
        setContentView(linearLayout);
        initView(this.mRootLayout);
        initData();
    }

    @Override // android.app.Dialog
    protected void onStart() {
        super.onStart();
        Context context = this.mContext;
        if (context instanceof Activity) {
            AndroidBug5497Workaround.assistActivity((Activity) context, this.mRootLayout);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showGifLoadingImage() {
        if (this.mAnimationDrawable != null && !ConfigManager.getInstance(this.mContext).isSimplifiedSDK()) {
            this.mAnimationDrawable.start();
        }
        this.mLoadingLayout.setVisibility(0);
    }

    private void hideGifLoadingImage() {
        if (this.mAnimationDrawable != null && !ConfigManager.getInstance(this.mContext).isSimplifiedSDK()) {
            this.mAnimationDrawable.stop();
        }
        this.mLoadingLayout.setVisibility(8);
    }

    public boolean isCancelable() {
        return this.mCancelable;
    }

    @Override // android.app.Dialog
    public void setCancelable(boolean z) {
        super.setCancelable(z);
        this.mCancelable = z;
    }

    @Override // android.app.Dialog
    public void setCanceledOnTouchOutside(boolean z) {
        super.setCanceledOnTouchOutside(z);
        this.mCancelable = z;
    }

    private void initWebView(SQWebView sQWebView) {
        final WebFunctionWrapper webFunctionWrapperAddWebHook = SQWeb.getInstance().bind(sQWebView).replace(new CustomUrlWebHook(), UrlWebHook.class).addWebHook(new CustomWebViewClient()).addWebHook(new SimpleWebHook() { // from class: com.sqwan.common.webview.SQWebViewDialog.3
            @Override // com.sq.webview.SimpleWebHook, com.sq.webview.WebHook
            public void onShowCustomView(View view, WebChromeClient.CustomViewCallback customViewCallback) {
                super.onShowCustomView(view, customViewCallback);
                SQWebViewDialog.this.mShowVideo = true;
                SQWebViewDialog.this.mFlVideoContainer.setVisibility(0);
                SQWebViewDialog.this.mFlVideoContainer.addView(view);
            }

            @Override // com.sq.webview.SimpleWebHook, com.sq.webview.WebHook
            public void onHideCustomView() {
                super.onHideCustomView();
                SQWebViewDialog.this.mFlVideoContainer.removeAllViews();
                SQWebViewDialog.this.mFlVideoContainer.setVisibility(8);
                SQWebViewDialog.this.mShowVideo = false;
            }
        });
        SQWeb.getInstance().checkLocalH5Enable(this.mUrl, new ConfigCallback() { // from class: com.sqwan.common.webview.-$$Lambda$SQWebViewDialog$6RGEPNFOE-lRVWq-hWGze2-fvFc
            @Override // com.sq.webview.local.ConfigCallback
            public final void onConfigGet(boolean z) {
                this.f$0.lambda$initWebView$0$SQWebViewDialog(webFunctionWrapperAddWebHook, z);
            }
        });
        SQWeb.enableCache(sQWebView);
        WebView.setWebContentsDebuggingEnabled(true);
        WebSettings settings = sQWebView.getSettings();
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        settings.setTextZoom(100);
        sQWebView.setOnTouchListener(this);
        sQWebView.setBackgroundColor(this.mWebViewBackgroundColor);
        sQWebView.addJavascriptInterface(new CustomJsObj(sQWebView), SQCommonJsInterface.INTERFACE_NAME);
        sQWebView.setDownloadListener(new CustomDownloadListener());
    }

    public /* synthetic */ void lambda$initWebView$0$SQWebViewDialog(WebFunctionWrapper webFunctionWrapper, boolean z) {
        if (!z) {
            webFunctionWrapper.disableLocalH5();
        }
        loadUrl();
    }

    protected void initView(View view) {
        LinearLayout.LayoutParams layoutParams;
        this.mWebView = (SQWebView) view.findViewById(SqResUtils.getId(getContext(), "wb_web_view_dialog_webview"));
        this.mLoadingLayout = (RelativeLayout) findViewById(SqResUtils.getId(getContext(), "rl_web_view_dialog_loading_layout"));
        this.mLoadingIcon = (ImageView) findViewById(SqResUtils.getId(getContext(), "iv_web_view_dialog_loading_icon"));
        this.mErrorNetView = view.findViewById(SqResUtils.getId(getContext(), "nev_web_view_dialog_loading_error"));
        this.mWebViewToolBar = (WebViewToolBar) view.findViewById(SqResUtils.getId(getContext(), "wvtb_web_view_dialog_tool_bar"));
        this.mFlVideoContainer = (FrameLayout) view.findViewById(SqResUtils.getId(getContext(), "fl_video_container"));
        initWebView(this.mWebView);
        this.mWebViewToolBar.setWebToolBarClickListener(this);
        this.mRootLayout.setOnClickListener(new View.OnClickListener() { // from class: com.sqwan.common.webview.SQWebViewDialog.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                if (SQWebViewDialog.this.isCancelable()) {
                    SQWebViewDialog.this.dismiss();
                }
            }
        });
        if (this.mPortraitHeightWeight != 100) {
            LinearLayout linearLayout = (LinearLayout) findViewById(SqResUtils.getId(getContext(), "ll_web_dialog_view"));
            ViewGroup viewGroup = (ViewGroup) findViewById(SqResUtils.getId(getContext(), "fl_web_view_dialog_content"));
            int screenWidth = DisplayUtil.getScreenWidth(this.mContext);
            int screenHeight = DisplayUtil.getScreenHeight(this.mContext);
            log("屏幕宽度；" + screenWidth + " 屏幕高度：" + screenHeight);
            if (this.mContext.getResources().getConfiguration().orientation == 2) {
                linearLayout.setOrientation(0);
                linearLayout.setGravity(GravityCompat.START);
                layoutParams = new LinearLayout.LayoutParams(screenHeight, viewGroup.getLayoutParams().height);
            } else {
                linearLayout.setOrientation(1);
                linearLayout.setGravity(80);
                layoutParams = new LinearLayout.LayoutParams(viewGroup.getLayoutParams().width, 0, this.mPortraitHeightWeight);
            }
            View viewFindViewById = findViewById(SqResUtils.getId(getContext(), "v_web_view_dialog_notch"));
            if (isNotchAffected()) {
                viewFindViewById.setVisibility(0);
                int notchHeight = NotchTools.getFullScreenTools().getNotchHeight(getWindow());
                if (notchHeight > 0) {
                    LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) viewFindViewById.getLayoutParams();
                    layoutParams2.width = notchHeight;
                    viewFindViewById.setLayoutParams(layoutParams2);
                }
            }
            viewGroup.setLayoutParams(layoutParams);
        }
        if (this.mAdaptNotchScreen) {
            View viewFindViewById2 = findViewById(SqResUtils.getId(getContext(), "v_status_bar_place"));
            viewFindViewById2.setVisibility(0);
            int statusHeight = NotchTools.getFullScreenTools().getStatusHeight(getWindow());
            if (statusHeight > 0) {
                LinearLayout.LayoutParams layoutParams3 = (LinearLayout.LayoutParams) viewFindViewById2.getLayoutParams();
                layoutParams3.height = statusHeight;
                layoutParams3.width = 0;
                viewFindViewById2.setLayoutParams(layoutParams3);
            }
        }
    }

    public void setAdaptNotchScreen(boolean z) {
        this.mAdaptNotchScreen = z;
    }

    protected void initData() {
        refreshWebBar(isShowWebBar());
        this.mWebView.loadUrl("javascript:window.packageName('" + this.mContext.getPackageName() + "')");
        if (this.mLoadingIcon == null || ConfigManager.getInstance(this.mContext).isSimplifiedSDK()) {
            return;
        }
        this.mLoadingIcon.setBackgroundResource(SqResUtils.getDrawableId(getContext(), "webview_loading"));
        this.mAnimationDrawable = (AnimationDrawable) this.mLoadingIcon.getBackground();
    }

    @Override // com.sqwan.common.dialog.FullScreenDialog, android.app.Dialog
    public void show() {
        if (TextUtils.isEmpty(this.mUrl)) {
            ToastUtil.showToast(this.mContext, "打开失败，网址是空的");
        } else {
            super.show();
        }
    }

    @Override // android.app.Dialog, android.content.DialogInterface
    public void dismiss() {
        removeCallbacks(this.mShowLoadingRunnable);
        if (this.mPortraitHeightWeight == 100) {
            super.dismiss();
            return;
        }
        Window window = getWindow();
        if (window == null) {
            return;
        }
        animExit(window.getDecorView());
    }

    private void animExit(View view) {
        ObjectAnimator objectAnimatorOfFloat = this.mContext.getResources().getConfiguration().orientation == 1 ? ObjectAnimator.ofFloat(view, "translationY", DisplayUtil.getScreenHeight(getContext())) : ObjectAnimator.ofFloat(view, "translationX", -DisplayUtil.getScreenWidth(getContext()));
        objectAnimatorOfFloat.setDuration(500L);
        objectAnimatorOfFloat.addListener(new AnimatorListenerAdapter() { // from class: com.sqwan.common.webview.SQWebViewDialog.5
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                SQWebViewDialog.super.dismiss();
            }
        });
        objectAnimatorOfFloat.start();
    }

    @Override // android.app.Dialog, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        SQWebView sQWebView;
        if (i == 4 && (sQWebView = this.mWebView) != null && sQWebView.canGoBack() && !this.mShowVideo) {
            this.mWebView.goBack();
            return true;
        }
        return super.onKeyDown(i, keyEvent);
    }

    public void setPortraitHeightWeight(int i) {
        this.mPortraitHeightWeight = i;
    }

    public void setWebViewBackgroundColor(int i) {
        this.mWebViewBackgroundColor = i;
        SQWebView sQWebView = this.mWebView;
        if (sQWebView == null) {
            return;
        }
        sQWebView.setBackgroundColor(i);
    }

    public void setAllowJumpURL(boolean z) {
        this.mAllowJumpURL = z;
    }

    public void post(Runnable runnable) {
        HANDLER.post(runnable);
    }

    public void postDelayed(Runnable runnable, long j) {
        HANDLER.postDelayed(runnable, j);
    }

    public void removeCallbacks(Runnable runnable) {
        HANDLER.removeCallbacks(runnable);
    }

    public void log(String str) {
        Logger.info("SQWebViewDialog", str);
    }

    public SQWebView getWebView() {
        return this.mWebView;
    }

    protected void loadUrl() {
        if (NetworkUtils.isNetworkConnected(this.mContext)) {
            if (this.mWebView != null) {
                log("SQWebViewDialog loadUrl");
                this.mWebView.loadUrl(this.mUrl);
                timeOutCheck();
                return;
            }
            return;
        }
        View view = this.mErrorNetView;
        if (view != null) {
            view.setVisibility(0);
        }
    }

    public void setWebToolBarClickListener(WebViewToolBar.WebToolBarClickListener webToolBarClickListener) {
        this.mWebBarClickListener = webToolBarClickListener;
    }

    private void timeOutCheck() {
        if (this.mTimeOutRunnable == null) {
            this.mTimeOutRunnable = new Runnable() { // from class: com.sqwan.common.webview.SQWebViewDialog.6
                @Override // java.lang.Runnable
                public void run() {
                    if (SQWebViewDialog.this.mLoadingFinish) {
                        return;
                    }
                    SQWebViewDialog.this.log("网络连接7.5秒超时");
                    SQWebViewDialog.this.timeOut();
                }
            };
        }
        HANDLER.postDelayed(this.mTimeOutRunnable, 7500L);
    }

    protected void jsClose(String str, String str2) {
        dismiss();
        if (!TextUtils.isEmpty(str) && str.equals("exitGame")) {
            log("退出游戏");
            Context context = this.mContext;
            if (context instanceof Activity) {
                ((Activity) context).finish();
            }
            System.exit(0);
        }
    }

    protected void jsOpenUrl(String str) {
        AppUtils.toSdkUrl(getContext(), str);
    }

    protected void onPageStarted(WebView webView, String str, Bitmap bitmap) {
        postDelayed(this.mShowLoadingRunnable, 1000L);
    }

    protected void onPageFinished(WebView webView, String str) {
        HANDLER.removeCallbacks(this.mShowLoadingRunnable);
        hideGifLoadingImage();
    }

    public void setUrl(String str) {
        this.mUrl = str;
    }

    @Override // com.sqwan.common.web.WebViewToolBar.WebToolBarClickListener
    public void onClickBack() {
        if (this.mWebView.canGoBack()) {
            this.mWebView.goBack();
        }
        WebViewToolBar.WebToolBarClickListener webToolBarClickListener = this.mWebBarClickListener;
        if (webToolBarClickListener != null) {
            webToolBarClickListener.onClickBack();
        }
    }

    @Override // com.sqwan.common.web.WebViewToolBar.WebToolBarClickListener
    public void onClickForward() {
        if (this.mWebView.canGoForward()) {
            this.mWebView.goForward();
        }
        WebViewToolBar.WebToolBarClickListener webToolBarClickListener = this.mWebBarClickListener;
        if (webToolBarClickListener != null) {
            webToolBarClickListener.onClickForward();
        }
    }

    @Override // com.sqwan.common.web.WebViewToolBar.WebToolBarClickListener
    public void onClickRefresh() {
        this.mWebView.reload();
        WebViewToolBar.WebToolBarClickListener webToolBarClickListener = this.mWebBarClickListener;
        if (webToolBarClickListener != null) {
            webToolBarClickListener.onClickRefresh();
        }
    }

    @Override // com.sqwan.common.web.WebViewToolBar.WebToolBarClickListener
    public void onClickClose() {
        dismiss();
        WebViewToolBar.WebToolBarClickListener webToolBarClickListener = this.mWebBarClickListener;
        if (webToolBarClickListener != null) {
            webToolBarClickListener.onClickClose();
        }
    }

    public class CustomJsObj extends SQCommonJsInterface {
        public CustomJsObj(SQWebView sQWebView) {
            super(sQWebView);
        }

        @JavascriptInterface
        public void enClose(final String str, final String str2) {
            log("enClose", "tag = " + str + ", data = " + str2);
            post(new Runnable() { // from class: com.sqwan.common.webview.SQWebViewDialog.CustomJsObj.1
                @Override // java.lang.Runnable
                public void run() {
                    SQWebViewDialog.this.jsClose(str, str2);
                }
            });
        }

        @Override // com.sqwan.common.webview.SQCommonJsInterface
        @JavascriptInterface
        public void sqOpenUrl(final String str) {
            log("sqOpenUrl", "url = " + str);
            post(new Runnable() { // from class: com.sqwan.common.webview.SQWebViewDialog.CustomJsObj.2
                @Override // java.lang.Runnable
                public void run() {
                    SQWebViewDialog.this.jsOpenUrl(str);
                }
            });
        }

        @Override // com.sqwan.common.webview.SQCommonJsInterface
        @JavascriptInterface
        public void enClose() {
            log("enClose", "");
            post(new Runnable() { // from class: com.sqwan.common.webview.SQWebViewDialog.CustomJsObj.3
                @Override // java.lang.Runnable
                public void run() {
                    SQWebViewDialog.this.jsClose("", "");
                }
            });
        }

        @JavascriptInterface
        public void sqOpenUrl() {
            log("sqOpenUrl", "");
            post(new Runnable() { // from class: com.sqwan.common.webview.SQWebViewDialog.CustomJsObj.4
                @Override // java.lang.Runnable
                public void run() {
                    SQWebViewDialog.this.jsOpenUrl("");
                }
            });
        }

        @JavascriptInterface
        public String getPopData() {
            log("getPopData", "");
            return SQWebViewDialog.this.getPopData();
        }

        @JavascriptInterface
        public void handleToolbar(final String str) {
            log("handleToolbar", "visible = " + str);
            post(new Runnable() { // from class: com.sqwan.common.webview.SQWebViewDialog.CustomJsObj.5
                @Override // java.lang.Runnable
                public void run() {
                    if ("0".equals(str)) {
                        SQWebViewDialog.this.refreshWebBar(false);
                    } else if ("1".equals(str)) {
                        SQWebViewDialog.this.refreshWebBar(true);
                    }
                }
            });
        }

        @Override // com.sqwan.common.webview.SQCommonJsInterface
        @JavascriptInterface
        public void enLogin() {
            super.enLogin();
            post(new Runnable() { // from class: com.sqwan.common.webview.SQWebViewDialog.CustomJsObj.6
                @Override // java.lang.Runnable
                public void run() {
                    SQWebViewDialog.this.jsEnLogin();
                }
            });
        }

        @JavascriptInterface
        public void certificate(final String str, final String str2) {
            log("certificate", "code = " + str + ", msg = " + str2);
            post(new Runnable() { // from class: com.sqwan.common.webview.SQWebViewDialog.CustomJsObj.7
                @Override // java.lang.Runnable
                public void run() {
                    SQWebViewDialog.this.jsCertificate(str, str2);
                }
            });
        }

        @JavascriptInterface
        public void unionClose(String str) {
            log("unionClose", "unionClose = " + str);
            SQWebViewDialog.this.unionCloseCalled = true;
            SQWebViewDialog.this.needUnionCloseRetry = !TextUtils.isEmpty(str) && "1".equals(str);
        }
    }

    public boolean isUnionCloseCalled() {
        return this.unionCloseCalled;
    }

    public boolean isNeedUnionCloseRetry() {
        return this.needUnionCloseRetry;
    }

    public class CustomDownloadListener implements DownloadListener {
        public CustomDownloadListener() {
        }

        @Override // android.webkit.DownloadListener
        public void onDownloadStart(String str, String str2, String str3, String str4, long j) {
            SQWebViewDialog.this.log("WebView 收到下载请求，url = " + str);
            Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(str));
            intent.setFlags(268435456);
            SQWebViewDialog.this.mContext.startActivity(intent);
        }
    }

    public void setShowWebBar(boolean z) {
        this.mShowWebBar = z;
    }

    protected boolean isShowWebBar() {
        return this.mShowWebBar;
    }

    protected void refreshWebBar(boolean z) {
        this.mShowBar = z;
        WebViewToolBar webViewToolBar = this.mWebViewToolBar;
        if (webViewToolBar == null) {
            return;
        }
        webViewToolBar.setVisibility(z ? 0 : 8);
    }

    private class CustomWebViewClient extends SimpleWebHook {
        private CustomWebViewClient() {
        }

        @Override // com.sq.webview.SimpleWebHook, com.sq.webview.WebHook
        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            super.onPageStarted(webView, str, bitmap);
            SQWebViewDialog.this.mLoadingFinish = false;
            SQWebViewDialog.this.onPageStarted(webView, str, bitmap);
        }

        @Override // com.sq.webview.SimpleWebHook, com.sq.webview.WebHook
        public void onPageFinished(WebView webView, String str) {
            super.onPageFinished(webView, str);
            SQWebViewDialog.this.mLoadingFinish = true;
            SQWebViewDialog.HANDLER.removeCallbacks(SQWebViewDialog.this.mTimeOutRunnable);
            SQWebViewDialog.this.onPageFinished(webView, str);
        }

        @Override // com.sq.webview.SimpleWebHook, com.sq.webview.WebHook
        public void onLoadResource(WebView webView, String str) {
            super.onLoadResource(webView, str);
            SQWebViewDialog.this.handleJump();
        }
    }

    private class CustomUrlWebHook extends UrlWebHook {
        private CustomUrlWebHook() {
        }

        @Override // com.sqwan.common.webview.UrlWebHook, com.sq.webview.SimpleWebHook, com.sq.webview.WebHook
        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            if (SQWebViewDialog.this.mAllowJumpURL) {
                return super.shouldOverrideUrlLoading(webView, str);
            }
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleJump() {
        if (this.mWebView.canGoBack()) {
            this.mWebViewToolBar.setBackBarSrc(SqResUtils.getIdByName("sy37_web_back_enable", "drawable", this.mContext));
        } else {
            this.mWebViewToolBar.setBackBarSrc(SqResUtils.getIdByName("sy37_web_back_disable", "drawable", this.mContext));
        }
        if (this.mWebView.canGoForward()) {
            this.mWebViewToolBar.setForwardBarSrc(SqResUtils.getIdByName("sy37_web_forward_enable", "drawable", this.mContext));
        } else {
            this.mWebViewToolBar.setForwardBarSrc(SqResUtils.getIdByName("sy37_web_forward_disable", "drawable", this.mContext));
        }
    }

    private boolean isNotchAffected() {
        Context context = this.mContext;
        if (context == null) {
            return false;
        }
        boolean z = context.getResources().getConfiguration().orientation == 2;
        return !(this.mContext instanceof Activity) ? z : NotchTools.getFullScreenTools().isNotchScreen(((Activity) this.mContext).getWindow()) && z;
    }
}
