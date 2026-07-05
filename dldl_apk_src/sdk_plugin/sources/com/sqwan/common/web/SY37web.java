package com.sqwan.common.web;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.drawable.AnimationDrawable;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.DownloadListener;
import android.webkit.JavascriptInterface;
import android.webkit.SslErrorHandler;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.plugin.standard.BaseActivity;
import com.sq.sdk.tool.util.NetworkUtils;
import com.sq.tool.sqtools.detector.common.SqTrackCommonKey;
import com.sq.tools.Logger;
import com.sq.webview.SimpleWebHook;
import com.sq.webview.WebFunctionWrapper;
import com.sq.webview.local.ConfigCallback;
import com.sqwan.base.EventDispatcher;
import com.sqwan.common.dialog.LoadingDialog;
import com.sqwan.common.eventbus.OnActivityResultEvent;
import com.sqwan.common.net.risk.RiskWebActivity;
import com.sqwan.common.track.SqTrackActionManager2;
import com.sqwan.common.track.SqTrackBtn;
import com.sqwan.common.util.AppUtils;
import com.sqwan.common.util.CutoutUtil;
import com.sqwan.common.util.LogUtil;
import com.sqwan.common.util.PermissionHelper;
import com.sqwan.common.util.SqResUtils;
import com.sqwan.common.util.StatusBarUtil;
import com.sqwan.common.util.ToastUtil;
import com.sqwan.common.web.WebViewToolBar;
import com.sqwan.common.webview.SQCommonJsInterface;
import com.sqwan.common.webview.SQWeb;
import com.sqwan.common.webview.SQWebView;
import com.sqwan.common.webview.UrlWebHook;
import com.sqwan.msdk.config.ConfigManager;
import com.taptap.sdk.common.oaid.helper.OAIDHelper;
import java.lang.reflect.Field;
import java.util.List;
import notchtools.geek.com.notchtools.NotchTools;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class SY37web extends BaseActivity implements WebViewToolBar.WebToolBarClickListener {
    private static final int FILE_CHOOSER_RESULT_CODE = 10000;
    private static final String MAILTO = "mailto:";
    private static final int MOBILEQQ_TYPE = 2;
    private static final String QQ = "qq:";
    private static final int SCROLL_DOWN = 0;
    private static final int SCROLL_THRESHOLD = 50;
    private static final int SCROLL_UP = 1;
    private static final String TEL = "tel:";
    public static final String WEB_QUIT_ANIM = "quit_anim";
    public static final String WEB_SHOW_TITLE = "showTitle";
    public static final String WEB_TITLE = "title";
    public static final String WEB_URL = "url";
    private static final String WEIXIN = "weixin:";
    private static final int WEIXIN_TYPE = 3;
    private View errorNetView;
    private ImageView gifMovieView;
    private RelativeLayout header;
    private ImageButton ibClose;
    private ImageView ivErrorView;
    private RelativeLayout loadingRelativeLayout;
    private FrameLayout mFlVideoContainer;
    private Handler mHandler;
    private ImageView mIvErrorViewBack;
    private boolean mShowBar;
    private boolean mShowVideo;
    private float mViewDownY;
    private WebViewToolBar mWebViewToolBar;
    private ViewGroup parent;
    private Runnable timeoutHandler;
    private TextView tvTitle;
    private ValueCallback<Uri[]> uploadMessageAboveL;
    private LoadingDialog waitDialog;
    private SQWebView webView;
    private boolean isQuitAnim = false;
    private String currentUrl = "";
    private String title = "";
    private boolean showTitle = false;
    private long LOAD_TIMEOUT = OAIDHelper.TIMEOUT;
    private int mCurScrollDirection = -1;
    private final Runnable mShowLoadingRunnable = new Runnable() { // from class: com.sqwan.common.web.SY37web.1
        @Override // java.lang.Runnable
        public void run() {
            SY37web.this.showGifLoadingImage();
        }
    };
    private AnimationDrawable animationDrawable = null;
    Handler showWebHandler = new Handler() { // from class: com.sqwan.common.web.SY37web.7
        @Override // android.os.Handler
        public void dispatchMessage(Message message) {
            super.dispatchMessage(message);
            if (SY37web.this.currentUrl == null || TextUtils.isEmpty(SY37web.this.currentUrl)) {
                SY37web.this.toast("主人，网址是空的，即将为您关闭..");
                SY37web.this.closeHandler.sendEmptyMessageDelayed(0, 1000L);
                return;
            }
            LogUtil.i("SY37web 打开url：" + SY37web.this.currentUrl);
            if (NetworkUtils.isNetworkConnected(SY37web.this.getContext())) {
                if (SY37web.this.webView != null) {
                    SY37web.this.webView.loadUrl(SY37web.this.currentUrl);
                    return;
                }
                return;
            }
            SY37web.this.errorNetView.setVisibility(0);
        }
    };
    Handler closeHandler = new Handler() { // from class: com.sqwan.common.web.SY37web.8
        @Override // android.os.Handler
        public void dispatchMessage(Message message) {
            super.dispatchMessage(message);
            SY37web.this.quit();
        }
    };
    Handler showErrorWebHandler = new Handler() { // from class: com.sqwan.common.web.SY37web.9
        @Override // android.os.Handler
        public void dispatchMessage(Message message) {
            super.dispatchMessage(message);
            SY37web.this.errorNetView.setVisibility(0);
        }
    };

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Logger.info("打开SY37web", new Object[0]);
        Intent intent = getIntent();
        boolean zHasNotchScreen = true;
        if (getContext() != null && (getContext() instanceof Activity) && (intent == null || intent.getStringExtra(RiskWebActivity.INTENT_KEY_IN_SCREEN_ORIENTATION) == null)) {
            Activity activity = (Activity) getContext();
            try {
                activity.setRequestedOrientation(1);
            } catch (IllegalStateException e) {
                e.printStackTrace();
            }
            activity.requestWindowFeature(1);
        }
        setContentView(getIdByName("sy37_web_dialog_portrait_full", "layout"));
        getWindow().setFlags(1024, 1024);
        getWindow().getDecorView().setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() { // from class: com.sqwan.common.web.SY37web.2
            @Override // android.view.View.OnSystemUiVisibilityChangeListener
            public void onSystemUiVisibilityChange(int i) {
                StatusBarUtil.hideSystemUI(SY37web.this.getWindow());
            }
        });
        if (getIntent() != null && getIntent().getExtras() != null) {
            this.isQuitAnim = getIntent().getBooleanExtra(WEB_QUIT_ANIM, false);
            this.currentUrl = getIntent().getExtras().getString("url");
            this.title = getIntent().getExtras().getString("title");
            this.showTitle = getIntent().getExtras().getBoolean("showTitle");
        }
        LogUtil.e("SY37web title: " + this.title + " showTitle: " + this.showTitle + " url: " + this.currentUrl);
        getWindow().setSoftInputMode(19);
        this.header = (RelativeLayout) findViewById(getIdByName("header", SqTrackCommonKey.id));
        this.tvTitle = (TextView) findViewById(getIdByName("title", SqTrackCommonKey.id));
        this.ibClose = (ImageButton) findViewById(getIdByName("togame", SqTrackCommonKey.id));
        this.mFlVideoContainer = (FrameLayout) findViewById(getIdByName("fl_video_container", SqTrackCommonKey.id));
        this.ibClose.setBackground(getContext().getResources().getDrawable(SqResUtils.getDrawableId(getContext(), "sy_icon_web_close")));
        WebViewToolBar webViewToolBar = (WebViewToolBar) findViewById(getIdByName("web_tool_bar", SqTrackCommonKey.id));
        this.mWebViewToolBar = webViewToolBar;
        webViewToolBar.setWebToolBarClickListener(this);
        ImageView imageView = (ImageView) findViewById(getIdByName("iv_error_view", SqTrackCommonKey.id));
        this.ivErrorView = imageView;
        imageView.setImageResource(getIdByName("sy37_net_wifi", "drawable"));
        this.errorNetView = findViewById(getIdByName("sy37_m_net_error_view", SqTrackCommonKey.id));
        ImageView imageView2 = (ImageView) findViewById(getIdByName("sy37_iv_back", SqTrackCommonKey.id));
        this.mIvErrorViewBack = imageView2;
        imageView2.setImageResource(getIdByName("sy37_icon_back", "drawable"));
        if (this.showTitle) {
            this.mIvErrorViewBack.setVisibility(8);
            this.header.setVisibility(0);
            this.tvTitle.setText(this.title);
            this.ibClose.setOnClickListener(new View.OnClickListener() { // from class: com.sqwan.common.web.SY37web.3
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    SY37web.this.quit();
                }
            });
        } else {
            this.mIvErrorViewBack.setVisibility(0);
            this.mIvErrorViewBack.setOnClickListener(new View.OnClickListener() { // from class: com.sqwan.common.web.SY37web.4
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    SY37web.this.quit();
                }
            });
            this.header.setVisibility(8);
        }
        this.parent = (ViewGroup) findViewById(getIdByName("web_parent", SqTrackCommonKey.id));
        findViewById(getIdByName("keyboard", SqTrackCommonKey.id)).setOnClickListener(new View.OnClickListener() { // from class: com.sqwan.common.web.SY37web.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                SY37web.this.quit();
            }
        });
        this.webView = (SQWebView) findViewById(getIdByName("webView", SqTrackCommonKey.id));
        final WebFunctionWrapper webFunctionWrapperAddWebHook = SQWeb.getInstance().bind(this.webView).replace(new CustomUrlWebHook(), UrlWebHook.class).addWebHook(new CustomWebViewClient()).addWebHook(new CustomWebChromeClient()).addWebHook(new SimpleWebHook() { // from class: com.sqwan.common.web.SY37web.6
            @Override // com.sq.webview.SimpleWebHook, com.sq.webview.WebHook
            public void onShowCustomView(View view, WebChromeClient.CustomViewCallback customViewCallback) {
                super.onShowCustomView(view, customViewCallback);
                SY37web.this.mShowVideo = true;
                SY37web.this.mFlVideoContainer.setVisibility(0);
                SY37web.this.mFlVideoContainer.addView(view);
            }

            @Override // com.sq.webview.SimpleWebHook, com.sq.webview.WebHook
            public void onHideCustomView() {
                super.onHideCustomView();
                SY37web.this.mFlVideoContainer.removeAllViews();
                SY37web.this.mShowVideo = false;
                SY37web.this.mFlVideoContainer.setVisibility(8);
            }
        });
        SQWeb.getInstance().checkLocalH5Enable(this.currentUrl, new ConfigCallback() { // from class: com.sqwan.common.web.-$$Lambda$SY37web$OC4ygbTTQXXHNC4CveuIRJ4jNCw
            @Override // com.sq.webview.local.ConfigCallback
            public final void onConfigGet(boolean z) {
                this.f$0.lambda$onCreate$0$SY37web(webFunctionWrapperAddWebHook, z);
            }
        });
        this.webView.setOnFocusChangeListener(new myOnFocusChangeListener());
        this.webView.setDownloadListener(new MyWebViewDownLoadListener());
        SQWebView sQWebView = this.webView;
        sQWebView.addJavascriptInterface(new CustomJsObj(sQWebView), SQCommonJsInterface.INTERFACE_NAME);
        this.mHandler = new Handler(Looper.getMainLooper());
        int statusHeight = NotchTools.getFullScreenTools().getStatusHeight(getWindow());
        int notchHeight = NotchTools.getFullScreenTools().getNotchHeight(getWindow());
        if (getContext() != null && (getContext() instanceof Activity)) {
            zHasNotchScreen = CutoutUtil.hasNotchScreen((Activity) getContext());
        }
        Logger.info("statusHeight=" + statusHeight + " notchHeight=" + notchHeight + " 是否刘海屏：" + zHasNotchScreen, new Object[0]);
        this.loadingRelativeLayout = (RelativeLayout) findViewById(SqResUtils.getId(getContext(), "sy37_rl_loading"));
        ImageView imageView3 = (ImageView) findViewById(SqResUtils.getId(getContext(), "sy37_iv_loading"));
        this.gifMovieView = imageView3;
        if (imageView3 == null || ConfigManager.getInstance(getContext()).isSimplifiedSDK()) {
            return;
        }
        this.gifMovieView.setBackgroundResource(SqResUtils.getDrawableId(getContext(), "webview_loading"));
        this.animationDrawable = (AnimationDrawable) this.gifMovieView.getBackground();
    }

    public /* synthetic */ void lambda$onCreate$0$SY37web(WebFunctionWrapper webFunctionWrapper, boolean z) {
        if (!z) {
            webFunctionWrapper.disableLocalH5();
        }
        this.showWebHandler.sendEmptyMessage(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showGifLoadingImage() {
        if (this.animationDrawable != null && !ConfigManager.getInstance(getContext()).isSimplifiedSDK()) {
            this.animationDrawable.start();
        }
        this.loadingRelativeLayout.setVisibility(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void hideGifLoadingImage() {
        if (this.animationDrawable != null && !ConfigManager.getInstance(getContext()).isSimplifiedSDK()) {
            this.animationDrawable.stop();
        }
        this.loadingRelativeLayout.setVisibility(8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void quit() {
        finish();
        if (this.isQuitAnim) {
            if (getResources().getConfiguration().orientation == 1) {
                overridePendingTransition(0, getIdByName("activity_bottom_close", "anim"));
            } else {
                overridePendingTransition(0, getIdByName("activity_left_close", "anim"));
            }
        }
    }

    private void showWaitDialog(Context context) {
        if (context != null) {
            if (this.waitDialog == null) {
                LoadingDialog loadingDialog = new LoadingDialog(context);
                this.waitDialog = loadingDialog;
                loadingDialog.setCanceledOnTouchOutside(false);
            }
            LoadingDialog loadingDialog2 = this.waitDialog;
            if (loadingDialog2 == null || loadingDialog2.isShowing() || isFinishing()) {
                return;
            }
            this.waitDialog.show();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void hideWaitDialog() {
        LoadingDialog loadingDialog = this.waitDialog;
        if (loadingDialog == null || !loadingDialog.isShowing() || isFinishing()) {
            return;
        }
        this.waitDialog.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateWaitDialog(String str) {
        LoadingDialog loadingDialog = this.waitDialog;
        if (loadingDialog == null || !loadingDialog.isShowing() || isFinishing()) {
            return;
        }
        this.waitDialog.setMessage(str);
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i != 4 || this.mShowVideo) {
            return true;
        }
        if (this.webView.canGoBack()) {
            this.webView.goBack();
            return true;
        }
        quit();
        return true;
    }

    protected int getIdByName(String str, String str2) {
        try {
            return SqResUtils.getIdByName(str, str2, getContext());
        } catch (Exception e) {
            e.printStackTrace();
            LogUtil.e(" TYPE:" + str2 + ",RES:" + str + " NOT FOUND!");
            return 0;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void toast(String str) {
        try {
            ToastUtil.showToast(getContext(), str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleJump() {
        if (this.webView.canGoBack()) {
            this.mWebViewToolBar.setBackBarSrc(SqResUtils.getIdByName("sy37_web_back_enable", "drawable", getContext()));
        } else {
            this.mWebViewToolBar.setBackBarSrc(SqResUtils.getIdByName("sy37_web_back_disable", "drawable", getContext()));
        }
        if (this.webView.canGoForward()) {
            this.mWebViewToolBar.setForwardBarSrc(SqResUtils.getIdByName("sy37_web_forward_enable", "drawable", getContext()));
        } else {
            this.mWebViewToolBar.setForwardBarSrc(SqResUtils.getIdByName("sy37_web_forward_disable", "drawable", getContext()));
        }
    }

    @Override // com.sqwan.common.web.WebViewToolBar.WebToolBarClickListener
    public void onClickBack() {
        if (this.webView.canGoBack()) {
            this.webView.goBack();
        }
    }

    @Override // com.sqwan.common.web.WebViewToolBar.WebToolBarClickListener
    public void onClickForward() {
        if (this.webView.canGoForward()) {
            this.webView.goForward();
        }
    }

    @Override // com.sqwan.common.web.WebViewToolBar.WebToolBarClickListener
    public void onClickRefresh() {
        this.webView.reload();
    }

    @Override // com.sqwan.common.web.WebViewToolBar.WebToolBarClickListener
    public void onClickClose() {
        quit();
    }

    public class CustomJsObj extends SQCommonJsInterface {
        public CustomJsObj(SQWebView sQWebView) {
            super(sQWebView);
        }

        @JavascriptInterface
        public void enRefresh() {
            log("enRefresh", "");
            post(new Runnable() { // from class: com.sqwan.common.web.SY37web.CustomJsObj.1
                @Override // java.lang.Runnable
                public void run() {
                    SY37web.this.showWebHandler.sendEmptyMessageDelayed(0, 200L);
                }
            });
        }

        @Override // com.sqwan.common.webview.SQCommonJsInterface
        @JavascriptInterface
        public void enClose() {
            log("enClose", "");
            post(new Runnable() { // from class: com.sqwan.common.web.SY37web.CustomJsObj.2
                @Override // java.lang.Runnable
                public void run() {
                    SY37web.this.quit();
                }
            });
        }

        @Override // com.sqwan.common.webview.SQCommonJsInterface
        @JavascriptInterface
        public void enLogin() {
            super.enLogin();
            post(new Runnable() { // from class: com.sqwan.common.web.SY37web.CustomJsObj.3
                @Override // java.lang.Runnable
                public void run() {
                    SqTrackActionManager2.getInstance().trackBtn(SqTrackBtn.SqTrackBtnId.changeAccount, SqTrackBtn.SqTrackBtnExt.CHANGE_ACCOUNT_USER_CENTER);
                }
            });
        }

        @JavascriptInterface
        public void handleToolbar(final String str) {
            log("handleToolbar", "visible = " + str);
            post(new Runnable() { // from class: com.sqwan.common.web.SY37web.CustomJsObj.4
                @Override // java.lang.Runnable
                public void run() {
                    if ("0".equals(str)) {
                        SY37web.this.refreshWebBar(false);
                    } else if ("1".equals(str)) {
                        SY37web.this.refreshWebBar(true);
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void shareTo(int i, String str) {
        String str2;
        Intent intent = new Intent("android.intent.action.SEND");
        if (i == 2) {
            copyString2System(getContext(), str, "已复制QQ号码到剪贴版");
            intent.setAction("android.intent.action.SENDTO");
            intent.addCategory("android.intent.category.DEFAULT");
            intent.setData(Uri.parse("imto://qq"));
            str2 = "已复制QQ号码到剪贴版";
        } else if (i != 3) {
            str2 = "";
        } else {
            intent.setAction("android.intent.action.SENDTO");
            intent.addCategory("android.intent.category.DEFAULT");
            intent.setData(Uri.parse(str));
            str2 = "已复制微信号码到剪贴版";
        }
        List<ResolveInfo> listQueryIntentActivities = getContext().getPackageManager().queryIntentActivities(intent, 65536);
        if (listQueryIntentActivities != null && !listQueryIntentActivities.isEmpty()) {
            startActivity(intent);
            return;
        }
        if (3 == i) {
            ToastUtil.showToast(getContext(), "请安装微信，" + str2);
            return;
        }
        ToastUtil.showToast(getContext(), "请安装移动QQ，" + str2);
    }

    private class MyWebViewDownLoadListener implements DownloadListener {
        private MyWebViewDownLoadListener() {
        }

        @Override // android.webkit.DownloadListener
        public void onDownloadStart(String str, String str2, String str3, String str4, long j) {
            AppUtils.toUri(SY37web.this.getContext(), str);
        }
    }

    public class myOnFocusChangeListener implements View.OnFocusChangeListener {
        public myOnFocusChangeListener() {
        }

        @Override // android.view.View.OnFocusChangeListener
        public void onFocusChange(View view, boolean z) {
            if (z) {
                try {
                    Field declaredField = WebView.class.getDeclaredField("mDefaultScale");
                    declaredField.setAccessible(true);
                    declaredField.setFloat(SY37web.this.webView, 1.0f);
                } catch (Exception unused) {
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void refreshWebBar(boolean z) {
        this.mShowBar = z;
        WebViewToolBar webViewToolBar = this.mWebViewToolBar;
        if (webViewToolBar == null) {
            return;
        }
        webViewToolBar.setVisibility(z ? 0 : 8);
    }

    public class CustomWebViewClient extends SimpleWebHook {
        public CustomWebViewClient() {
        }

        @Override // com.sq.webview.SimpleWebHook, com.sq.webview.WebHook
        public void onPageStarted(final WebView webView, final String str, Bitmap bitmap) {
            SY37web.this.currentUrl = str;
            SY37web.this.removeTimeoutCheckingRunnable();
            SY37web.this.timeoutHandler = new Runnable() { // from class: com.sqwan.common.web.SY37web.CustomWebViewClient.1
                @Override // java.lang.Runnable
                public void run() {
                    System.out.println("37web timeout..");
                    CustomWebViewClient.this.onReceivedError(webView, str, -8, "网络超时，请稍后再试.");
                }
            };
            SY37web.this.mHandler.postDelayed(SY37web.this.timeoutHandler, SY37web.this.LOAD_TIMEOUT);
            WebviewUtils.setUrlLoadTime(getContext(), str, System.currentTimeMillis());
            super.onPageStarted(webView, str, bitmap);
            SY37web.this.mHandler.postDelayed(SY37web.this.mShowLoadingRunnable, 1000L);
        }

        @Override // com.sq.webview.SimpleWebHook, com.sq.webview.WebHook
        public void onPageFinished(WebView webView, String str) {
            super.onPageFinished(webView, str);
            SY37web.this.removeTimeoutCheckingRunnable();
            if (WebviewUtils.isLoadOneTime(getContext(), str, SY37web.this.LOAD_TIMEOUT)) {
                LogUtil.i("当前页面:超时前加载完成");
                WebviewUtils.setUrlLoadCount(getContext(), str, 1);
            } else {
                LogUtil.i("当前页面:超时后加载完成");
            }
            SY37web.this.hideWaitDialog();
            String title = webView.getTitle();
            if (!TextUtils.isEmpty(title) && SY37web.this.tvTitle != null) {
                SY37web.this.tvTitle.setText(title);
            }
            SY37web.this.mHandler.removeCallbacks(SY37web.this.mShowLoadingRunnable);
            SY37web.this.hideGifLoadingImage();
        }

        @Override // com.sq.webview.SimpleWebHook, com.sq.webview.WebHook
        public void onReceivedError(WebView webView, String str, int i, String str2) {
            SY37web.this.hideGifLoadingImage();
        }

        @Override // com.sq.webview.SimpleWebHook, com.sq.webview.WebHook
        public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
            super.onReceivedSslError(webView, sslErrorHandler, sslError);
            SY37web.this.hideGifLoadingImage();
        }

        @Override // com.sq.webview.SimpleWebHook, com.sq.webview.WebHook
        public void onLoadResource(WebView webView, String str) {
            super.onLoadResource(webView, str);
            SY37web.this.handleJump();
        }
    }

    public class CustomUrlWebHook extends SimpleWebHook {
        public CustomUrlWebHook() {
        }

        @Override // com.sq.webview.SimpleWebHook, com.sq.webview.WebHook
        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            if (str.startsWith(SY37web.TEL)) {
                Intent intent = new Intent();
                intent.setAction("android.intent.action.DIAL");
                intent.setData(Uri.parse(str));
                SY37web.this.startActivity(intent);
            } else if (str.startsWith(SY37web.QQ)) {
                SY37web.this.shareTo(2, str.substring(3));
            } else if (str.startsWith(SY37web.MAILTO)) {
                SY37web.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
            } else if (str.startsWith(SY37web.WEIXIN)) {
                SY37web.this.shareTo(3, str);
            } else {
                webView.loadUrl(str);
            }
            SY37web.this.hideGifLoadingImage();
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void removeTimeoutCheckingRunnable() {
        Runnable runnable = this.timeoutHandler;
        if (runnable != null) {
            this.mHandler.removeCallbacks(runnable);
            this.timeoutHandler = null;
        }
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        EventDispatcher.getInstance().dispatcherActivityResultListener(new OnActivityResultEvent(i, i2, intent));
    }

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        if (i != 1110) {
            return;
        }
        PermissionHelper.getInstance().onRequestPermissionsResult(i, strArr, iArr);
    }

    public void onDestroy() {
        LogUtil.i("37Web被销毁，先关闭加载进度框");
        hideWaitDialog();
        WebviewUtils.clearWebviewPrefs(getContext());
        SQWebView sQWebView = this.webView;
        if (sQWebView != null) {
            sQWebView.onDestroy();
            this.webView = null;
        }
        this.mHandler.removeCallbacks(this.mShowLoadingRunnable);
        this.mHandler.removeCallbacks(this.timeoutHandler);
        this.showWebHandler.removeCallbacksAndMessages(null);
        this.showErrorWebHandler.removeCallbacksAndMessages(null);
        this.closeHandler.removeCallbacksAndMessages(null);
        super.onDestroy();
    }

    public class CustomWebChromeClient extends SimpleWebHook {
        public CustomWebChromeClient() {
        }

        @Override // com.sq.webview.SimpleWebHook, com.sq.webview.WebHook
        public void onProgressChanged(WebView webView, int i) {
            SY37web.this.updateWaitDialog("加载中.." + i + "%");
            super.onProgressChanged(webView, i);
        }

        @Override // com.sq.webview.SimpleWebHook, com.sq.webview.WebHook
        public void onReceivedTitle(WebView webView, String str) {
            super.onReceivedTitle(webView, str);
            if (SY37web.this.tvTitle != null) {
                SY37web.this.tvTitle.setText(str);
            }
        }
    }

    public static void copyString2System(Context context, String str, String str2) {
        if (str == null || "".equals(str)) {
            return;
        }
        try {
            ClipboardManager clipboardManager = (ClipboardManager) context.getSystemService("clipboard");
            if (clipboardManager != null) {
                clipboardManager.setPrimaryClip(ClipData.newPlainText("code", str.trim()));
                ToastUtil.showToast(context, str2);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
