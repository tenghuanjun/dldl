package com.sqwan.common.web;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import com.plugin.standard.BaseActivity;
import com.sqwan.common.util.SQContextWrapper;
import com.sqwan.common.util.SqResUtils;
import com.sqwan.common.util.StatusBarUtil;
import com.sqwan.common.web.WebViewToolBar;
import com.sqwan.common.webview.SQCommonJsInterface;
import com.sqwan.common.webview.SQWeb;
import com.sqwan.common.webview.SQWebView;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class UserProtocolWebActivity extends BaseActivity {
    private static final int SCROLL_DOWN = 0;
    private static final int SCROLL_UP = 1;
    private static final int threshold = 50;
    private float curPosX;
    private float curPosY;
    private SQWebView mWebView;
    private float posX;
    private float posY;
    private boolean showToolBar;
    private WebViewToolBar webToolbar;
    private int curScrollDirection = -1;
    private int scrollDirection = -1;
    private final Handler mHandler = new Handler(Looper.getMainLooper());

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        String stringExtra = getIntent().getStringExtra("name");
        if (!TextUtils.isEmpty(stringExtra) && stringExtra.contains("联通")) {
            setTheme(SqResUtils.getStyleId(SQContextWrapper.getActivity(), "protocol_activity"));
        } else {
            setTheme(SqResUtils.getStyleId(SQContextWrapper.getActivity(), "protocol_activity_dialog"));
        }
        setContentView(SqResUtils.getLayoutId(SQContextWrapper.getActivity(), "sysq_user_protocol"));
        initWindow();
        this.mWebView = (SQWebView) findViewById(SqResUtils.getId(SQContextWrapper.getActivity(), "webView"));
        this.webToolbar = (WebViewToolBar) findViewById(SqResUtils.getId(SQContextWrapper.getActivity(), "web_tool_bar"));
        initWebView();
        initWebToolBar();
        String stringExtra2 = getIntent().getStringExtra("url");
        setRequestedOrientation(getIntent().getIntExtra("orientation", 1));
        if (TextUtils.isEmpty(stringExtra2)) {
            return;
        }
        this.mWebView.loadUrl(stringExtra2);
        SQWebView sQWebView = this.mWebView;
        sQWebView.addJavascriptInterface(new CustomJsObj(sQWebView), SQCommonJsInterface.INTERFACE_NAME);
        boolean z = !stringExtra2.contains("useragreement");
        this.showToolBar = z;
        this.webToolbar.setVisibility(z ? 0 : 8);
    }

    /* JADX INFO: renamed from: com.sqwan.common.web.UserProtocolWebActivity$1, reason: invalid class name */
    class AnonymousClass1 implements WebViewToolBar.WebToolBarClickListener {
        AnonymousClass1() {
        }

        @Override // com.sqwan.common.web.WebViewToolBar.WebToolBarClickListener
        public void onClickBack() {
            if (UserProtocolWebActivity.this.mWebView.canGoBack()) {
                UserProtocolWebActivity.this.mWebView.goBack();
            }
        }

        @Override // com.sqwan.common.web.WebViewToolBar.WebToolBarClickListener
        public void onClickForward() {
            if (UserProtocolWebActivity.this.mWebView.canGoForward()) {
                UserProtocolWebActivity.this.mWebView.goForward();
            }
        }

        @Override // com.sqwan.common.web.WebViewToolBar.WebToolBarClickListener
        public void onClickRefresh() {
            UserProtocolWebActivity.this.mWebView.reload();
        }

        public /* synthetic */ void lambda$onClickClose$0$UserProtocolWebActivity$1() {
            UserProtocolWebActivity.this.finish();
        }

        @Override // com.sqwan.common.web.WebViewToolBar.WebToolBarClickListener
        public void onClickClose() {
            UserProtocolWebActivity.this.mHandler.post(new Runnable() { // from class: com.sqwan.common.web.-$$Lambda$UserProtocolWebActivity$1$Qcbf4sJlW09c1A0MHXrkhEN3emk
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onClickClose$0$UserProtocolWebActivity$1();
                }
            });
        }
    }

    private void initWebToolBar() {
        this.webToolbar.setWebToolBarClickListener(new AnonymousClass1());
    }

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
        getWindow().getDecorView().setSystemUiVisibility(1028);
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
        SQWeb.getInstance().bind(this.mWebView).disableLocalH5();
        SQWeb.enableCache(this.mWebView);
        this.mWebView.setBackgroundColor(0);
        WebSettings settings = this.mWebView.getSettings();
        settings.setBlockNetworkLoads(false);
        settings.setDatabaseEnabled(true);
    }

    public class CustomJsObj extends SQCommonJsInterface {
        public CustomJsObj(SQWebView sQWebView) {
            super(sQWebView);
        }

        @JavascriptInterface
        public void enClose(String str, String str2) {
            log("enClose", "tag = " + str + ", data = " + str2);
            UserProtocolWebActivity.this.mHandler.post(new Runnable() { // from class: com.sqwan.common.web.-$$Lambda$UserProtocolWebActivity$CustomJsObj$LLG4lFQ23EVETGk7bQzbzPUwF6Q
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$enClose$0$UserProtocolWebActivity$CustomJsObj();
                }
            });
        }

        public /* synthetic */ void lambda$enClose$0$UserProtocolWebActivity$CustomJsObj() {
            UserProtocolWebActivity.this.finish();
        }
    }
}
