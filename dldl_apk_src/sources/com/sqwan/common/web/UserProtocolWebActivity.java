package com.sqwan.common.web;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import com.host.SqResUtils;
import com.host.StatusBarUtil;
import com.host.WebViewToolBar;
import com.mobile.auth.gatewayauth.Constant;
import com.plugin.core.loader.ApkClassLoader;
import com.sqwan.msdk.SQApplication;
import com.ss.android.socialbase.downloader.constants.DownloadErrorCode;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public class UserProtocolWebActivity extends Activity {
    private static final int SCROLL_DOWN = 0;
    private static final int SCROLL_UP = 1;
    private static final int threshold = 50;
    private float curPosX;
    private float curPosY;
    private WebView mWebView;
    private float posX;
    private float posY;
    private boolean showToolBar;
    private WebViewToolBar webToolbar;
    private int curScrollDirection = -1;
    private int scrollDirection = -1;

    @Override // android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        String stringExtra = getIntent().getStringExtra("name");
        if (!TextUtils.isEmpty(stringExtra) && stringExtra.contains("联通统一")) {
            setTheme(SqResUtils.getStyleId(this, "protocol_activity"));
        } else {
            setTheme(SqResUtils.getStyleId(this, "protocol_activity_dialog"));
        }
        setContentView(SqResUtils.getLayoutId(this, "sysq_host_user_protocol"));
        initWindow();
        this.mWebView = (WebView) findViewById(SqResUtils.getId(this, "webView"));
        this.webToolbar = (WebViewToolBar) findViewById(SqResUtils.getId(this, "web_tool_bar"));
        initWebView();
        initWebToolBar();
        String stringExtra2 = getIntent().getStringExtra("url");
        setRequestedOrientation(getIntent().getIntExtra(Constant.PROTOCOL_WEB_VIEW_ORIENTATION, 1));
        if (TextUtils.isEmpty(stringExtra2)) {
            return;
        }
        this.mWebView.loadUrl(stringExtra2);
        this.mWebView.addJavascriptInterface(new JsObj(this), "fee");
        this.showToolBar = !stringExtra2.contains("useragreement");
        this.webToolbar.setVisibility(this.showToolBar ? 0 : 8);
    }

    private void initWebToolBar() {
        this.webToolbar.setWebToolBarClickListener(new WebViewToolBar.WebToolBarClickListener() { // from class: com.sqwan.common.web.UserProtocolWebActivity.1
            @Override // com.host.WebViewToolBar.WebToolBarClickListener
            public void onClickBack() {
                if (UserProtocolWebActivity.this.mWebView.canGoBack()) {
                    UserProtocolWebActivity.this.mWebView.goBack();
                }
            }

            @Override // com.host.WebViewToolBar.WebToolBarClickListener
            public void onClickForward() {
                if (UserProtocolWebActivity.this.mWebView.canGoForward()) {
                    UserProtocolWebActivity.this.mWebView.goForward();
                }
            }

            @Override // com.host.WebViewToolBar.WebToolBarClickListener
            public void onClickRefresh() {
                UserProtocolWebActivity.this.mWebView.reload();
            }

            @Override // com.host.WebViewToolBar.WebToolBarClickListener
            public void onClickClose() {
                UserProtocolWebActivity.this.runOnUiThread(new Runnable() { // from class: com.sqwan.common.web.UserProtocolWebActivity.1.1
                    @Override // java.lang.Runnable
                    public void run() {
                        UserProtocolWebActivity.this.finish();
                    }
                });
            }
        });
        handleGesture();
    }

    @SuppressLint({"ClickableViewAccessibility"})
    private void handleGesture() {
        this.mWebView.setOnTouchListener(new View.OnTouchListener() { // from class: com.sqwan.common.web.UserProtocolWebActivity.2
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int action = motionEvent.getAction();
                if (action == 0) {
                    UserProtocolWebActivity.this.posX = motionEvent.getX();
                    UserProtocolWebActivity.this.posY = motionEvent.getY();
                } else if (action == 2) {
                    UserProtocolWebActivity.this.curPosX = motionEvent.getX();
                    UserProtocolWebActivity.this.curPosY = motionEvent.getY();
                    if (UserProtocolWebActivity.this.curPosY - UserProtocolWebActivity.this.posY > 0.0f && Math.abs(UserProtocolWebActivity.this.curPosY - UserProtocolWebActivity.this.posY) > 50.0f) {
                        UserProtocolWebActivity.this.scrollDirection = 0;
                        if (UserProtocolWebActivity.this.curScrollDirection != UserProtocolWebActivity.this.scrollDirection && UserProtocolWebActivity.this.showToolBar) {
                            UserProtocolWebActivity.this.webToolbar.showAnimation();
                            UserProtocolWebActivity userProtocolWebActivity = UserProtocolWebActivity.this;
                            userProtocolWebActivity.curScrollDirection = userProtocolWebActivity.scrollDirection;
                        }
                    } else if (UserProtocolWebActivity.this.curPosY - UserProtocolWebActivity.this.posY < 0.0f && Math.abs(UserProtocolWebActivity.this.curPosY - UserProtocolWebActivity.this.posY) > 50.0f) {
                        UserProtocolWebActivity.this.scrollDirection = 1;
                        if (UserProtocolWebActivity.this.curScrollDirection != UserProtocolWebActivity.this.scrollDirection && UserProtocolWebActivity.this.showToolBar) {
                            UserProtocolWebActivity.this.webToolbar.hideAnimation();
                            UserProtocolWebActivity userProtocolWebActivity2 = UserProtocolWebActivity.this;
                            userProtocolWebActivity2.curScrollDirection = userProtocolWebActivity2.scrollDirection;
                        }
                    }
                }
                return false;
            }
        });
    }

    private void initWindow() {
        getWindow().getDecorView().setSystemUiVisibility(DownloadErrorCode.ERROR_SAVE_PATH_EMPTY);
        if (Build.VERSION.SDK_INT >= 28) {
            WindowManager.LayoutParams attributes = getWindow().getAttributes();
            attributes.layoutInDisplayCutoutMode = 1;
            getWindow().setAttributes(attributes);
        }
        Window window = getWindow();
        window.setGravity(17);
        window.getDecorView().setPadding(0, 0, 0, 0);
        WindowManager.LayoutParams attributes2 = window.getAttributes();
        attributes2.width = -1;
        attributes2.height = -1;
        window.setAttributes(attributes2);
        StatusBarUtil.hideSystemUI(getWindow());
    }

    private void initWebView() {
        this.mWebView.setBackgroundColor(0);
        this.mWebView.setHorizontalScrollBarEnabled(false);
        this.mWebView.setVerticalScrollBarEnabled(false);
        WebSettings settings = this.mWebView.getSettings();
        settings.setBlockNetworkImage(false);
        settings.setBlockNetworkLoads(false);
        settings.setJavaScriptEnabled(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setCacheMode(-1);
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        settings.setDomStorageEnabled(true);
        settings.setBuiltInZoomControls(false);
        settings.setSupportZoom(false);
        settings.setDisplayZoomControls(false);
        settings.setAllowFileAccess(true);
        settings.setDatabaseEnabled(true);
        settings.setAppCacheEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setDatabaseEnabled(true);
        settings.setTextZoom(100);
    }

    public class JsObj {
        private Context mContext;

        public JsObj(Context context) {
            this.mContext = context;
        }

        @JavascriptInterface
        public void enClose(String str, String str2) {
            Context context = this.mContext;
            if (context instanceof Activity) {
                ((Activity) context).runOnUiThread(new Runnable() { // from class: com.sqwan.common.web.UserProtocolWebActivity.JsObj.1
                    @Override // java.lang.Runnable
                    public void run() {
                        UserProtocolWebActivity.this.finish();
                    }
                });
            }
        }
    }

    @Override // android.view.ContextThemeWrapper, android.content.ContextWrapper, android.content.Context
    public Resources getResources() {
        return SQApplication.mPlugin.mResource;
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public ApkClassLoader getClassLoader() {
        return SQApplication.mPlugin.mClassLoader;
    }
}
