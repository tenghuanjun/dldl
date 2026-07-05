package com.sq.webview.view;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.Toast;
import com.sq.webview.SQBaseJSInterface;
import com.sq.webview.SQWebAgent;
import com.sq.webview.WebFunctionWrapper;
import com.sq.webview.WebHook;
import com.sq.webview.util.ViewUtil;
import com.sq.webview.util.WebLogUtil;
import com.sq.webview.util.WebResUtil;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class SQWebView extends FrameLayout implements IWebViewLoading, IWebViewError {
    private static final int SCROLL_DOWN = 0;
    private static final int SCROLL_THRESHOLD = 50;
    private static final int SCROLL_UP = 1;
    private int mCurScrollDirection;
    private DefaultWebToolBar mDefaultWebToolBar;
    private boolean mIsHalfScreen;
    private LoadingDialog mLoadingDialog;
    private boolean mShowBar;
    private float mViewDownY;
    private WebFunctionWrapper mWebFunctionWrapper;
    private WebView mWebView;
    private WebViewTitleBar mWebViewTitleBar;

    @Override // com.sq.webview.view.IWebViewLoading
    public void onProgress(Context context, int progress) {
    }

    public SQWebView(Context context) {
        this(context, null);
    }

    public SQWebView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SQWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mShowBar = false;
        this.mIsHalfScreen = false;
        this.mCurScrollDirection = -1;
        initView(context);
    }

    private void initView(Context context) {
        LayoutInflater.from(context).inflate(WebResUtil.getLayoutId(context, "sy37_web_view_layout_sq_web"), (ViewGroup) this, true);
        this.mWebView = (WebView) findViewById(WebResUtil.getId(context, "default_web_view"));
        this.mDefaultWebToolBar = (DefaultWebToolBar) findViewById(WebResUtil.getId(context, "default_web_tool_bar"));
        WebViewTitleBar webViewTitleBar = (WebViewTitleBar) findViewById(WebResUtil.getId(context, "title_bar"));
        this.mWebViewTitleBar = webViewTitleBar;
        webViewTitleBar.setRefreshListener(new View.OnClickListener() { // from class: com.sq.webview.view.-$$Lambda$SQWebView$LY8egaF9Ud9ExtbCy339wsKZcJA
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$initView$0$SQWebView(view);
            }
        });
        this.mWebViewTitleBar.setBackListener(new View.OnClickListener() { // from class: com.sq.webview.view.-$$Lambda$SQWebView$chGryMJO98EXJwoy7IhIAymLlQM
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$initView$1$SQWebView(view);
            }
        });
        this.mLoadingDialog = new LoadingDialog(context);
        this.mDefaultWebToolBar.getRefreshView().setOnClickListener(new View.OnClickListener() { // from class: com.sq.webview.view.-$$Lambda$SQWebView$neGDYWHwwoflywdb_qnxJuPB_xw
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$initView$2$SQWebView(view);
            }
        });
        this.mDefaultWebToolBar.getForwardView().setOnClickListener(new View.OnClickListener() { // from class: com.sq.webview.view.-$$Lambda$SQWebView$x9RgELB8cyfXnpfAYfcprRG5fdA
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$initView$3$SQWebView(view);
            }
        });
        this.mDefaultWebToolBar.getBackView().setOnClickListener(new View.OnClickListener() { // from class: com.sq.webview.view.-$$Lambda$SQWebView$RilK01XPDe5M4vcA19DaUZHQOpI
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$initView$4$SQWebView(view);
            }
        });
    }

    public /* synthetic */ void lambda$initView$0$SQWebView(View view) {
        reload();
    }

    public /* synthetic */ void lambda$initView$1$SQWebView(View v) {
        if (this.mWebView.canGoBack()) {
            this.mWebView.goBack();
        }
    }

    public /* synthetic */ void lambda$initView$2$SQWebView(View v) {
        this.mWebView.reload();
    }

    public /* synthetic */ void lambda$initView$3$SQWebView(View v) {
        if (this.mWebView.canGoForward()) {
            this.mWebView.goForward();
        }
    }

    public /* synthetic */ void lambda$initView$4$SQWebView(View v) {
        if (this.mWebView.canGoBack()) {
            this.mWebView.goBack();
        }
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent event) {
        int action = event.getAction();
        if (action == 0) {
            this.mViewDownY = event.getY();
        } else if (action == 2) {
            float y = event.getY();
            float f = this.mViewDownY;
            if (y - f > 0.0f && Math.abs(y - f) > 50.0f) {
                if (this.mCurScrollDirection != 0 && this.mShowBar) {
                    this.mDefaultWebToolBar.show(getContext());
                    this.mCurScrollDirection = 0;
                }
            } else {
                float f2 = this.mViewDownY;
                if (y - f2 < 0.0f && Math.abs(y - f2) > 50.0f && this.mCurScrollDirection != 1 && this.mShowBar) {
                    this.mDefaultWebToolBar.hide(getContext());
                    this.mCurScrollDirection = 1;
                }
            }
        }
        return super.onInterceptTouchEvent(event);
    }

    public void reload() {
        this.mWebView.reload();
    }

    public WebView getRealWebView() {
        return this.mWebView;
    }

    public void addJavascriptInterface(SQBaseJSInterface obj, String interfaceName) {
        this.mWebView.getSettings().setJavaScriptEnabled(true);
        this.mWebView.addJavascriptInterface(obj, interfaceName);
    }

    public void loadUrl(String url, Map<String, String> additionalHttpHeaders) {
        if (!TextUtils.isEmpty(url)) {
            this.mWebView.loadUrl(url, additionalHttpHeaders);
        } else {
            showWebError(getContext(), url, "url illegal", 20003);
        }
    }

    public void loadUrl(String url) {
        if (!TextUtils.isEmpty(url)) {
            this.mWebView.loadUrl(url);
        } else {
            showWebError(getContext(), url, "url illegal", 20003);
        }
    }

    public void setToolbarClickCloseListener(View.OnClickListener onClickListener) {
        this.mDefaultWebToolBar.setCloseClickListener(onClickListener);
    }

    @Override // com.sq.webview.view.IWebViewLoading
    public void startLoading(Context context) {
        this.mLoadingDialog.show();
    }

    @Override // com.sq.webview.view.IWebViewLoading
    public void stopLoading(Context context) {
        this.mLoadingDialog.dismiss();
    }

    @Override // com.sq.webview.view.IWebViewError
    public void showWebError(Context context, String url, String msg, int errorCode) {
        handleError(context, errorCode, msg);
    }

    private void handleError(Context context, int errorCode, String msg) {
        Toast.makeText(context, "[" + errorCode + "] " + msg, 0).show();
        if (this.mIsHalfScreen) {
            refreshTitleBar(true);
        } else {
            refreshToolBar(true);
        }
    }

    public void setWebAgent(SQWebAgent webAgent) {
        this.mWebFunctionWrapper = webAgent.bind(this.mWebView).setLoading(this).setErrorView(this).setLoadTimeout(new ILoadTimeout() { // from class: com.sq.webview.view.-$$Lambda$SQWebView$k2NmXX4hXrinYM1YjT9DXP1zQNQ
            @Override // com.sq.webview.view.ILoadTimeout
            public final void onLoadTimeout() {
                this.f$0.lambda$setWebAgent$5$SQWebView();
            }
        }).enableFileUpload(ViewUtil.getActivity(this.mWebView));
    }

    public /* synthetic */ void lambda$setWebAgent$5$SQWebView() {
        handleError(getContext(), -20002, "load time out");
    }

    public void addWebHook(WebHook webHook) {
        WebFunctionWrapper webFunctionWrapper = this.mWebFunctionWrapper;
        if (webFunctionWrapper != null) {
            webFunctionWrapper.getWebHookDispatcher().addWebHook(webHook);
        } else {
            WebLogUtil.e("还未绑定Agent，addWebHook无效");
        }
    }

    public WebFunctionWrapper getWebFunctionWrapper() {
        WebFunctionWrapper webFunctionWrapper = this.mWebFunctionWrapper;
        if (webFunctionWrapper != null) {
            return webFunctionWrapper;
        }
        throw new NullPointerException("WebFunctionWrapper is null, please setWebAgent first");
    }

    public void setTitleCloseListener(View.OnClickListener listener) {
        this.mDefaultWebToolBar.setCloseClickListener(listener);
        this.mWebViewTitleBar.setCloseListener(listener);
    }

    public void setTitle(String title) {
        this.mWebViewTitleBar.setTitle(title);
    }

    public void setHalfScreen(boolean isHalfScreen) {
        this.mIsHalfScreen = isHalfScreen;
    }

    public void refreshToolBar(boolean visible) {
        this.mShowBar = visible;
        DefaultWebToolBar defaultWebToolBar = this.mDefaultWebToolBar;
        if (defaultWebToolBar == null) {
            return;
        }
        defaultWebToolBar.setVisibility(visible ? 0 : 8);
    }

    public void refreshTitleBar(boolean visible) {
        WebViewTitleBar webViewTitleBar = this.mWebViewTitleBar;
        if (webViewTitleBar == null) {
            return;
        }
        webViewTitleBar.setVisibility(visible ? 0 : 8);
    }

    public WebViewTitleBar getTitleBar() {
        return this.mWebViewTitleBar;
    }
}
