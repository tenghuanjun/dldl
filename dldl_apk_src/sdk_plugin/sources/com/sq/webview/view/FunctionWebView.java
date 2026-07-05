package com.sq.webview.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.sq.webview.R;
import com.sq.webview.SQWebAgent;
import com.sq.webview.WebFunctionWrapper;
import com.sq.webview.WebHook;
import com.sq.webview.util.WebLogUtil;
import com.sq.webview.util.WebResUtil;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class FunctionWebView extends RelativeLayout implements IWebViewLoading, IWebViewError {
    private DefaultWebToolBar mDefaultWebToolBar;
    private ErrorHintProvider mErrorHintProvider;
    private RelativeLayout mErrorView;
    private String mLoadingHint;
    private RelativeLayout mLoadingView;
    private TextView mTvErrorHint;
    private TextView mTvLoadingHint;
    private WebFunctionWrapper mWebFunctionWrapper;
    private WebView mWebView;

    public interface ErrorHintProvider {
        String provideErrorHint(String url, String msg, int errCode);
    }

    @Override // com.sq.webview.view.IWebViewLoading
    public void onProgress(Context context, int progress) {
    }

    public FunctionWebView(Context context) {
        this(context, null);
    }

    public FunctionWebView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FunctionWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attrs, R.styleable.FunctionWebView);
        this.mLoadingHint = typedArrayObtainStyledAttributes.getString(R.styleable.FunctionWebView_loading_hint);
        typedArrayObtainStyledAttributes.recycle();
        initView(context);
    }

    private void initView(Context context) {
        LayoutInflater.from(context).inflate(WebResUtil.getLayoutId(context, "sy37_web_view_layout_function_web"), (ViewGroup) this, true);
        this.mWebView = (WebView) findViewById(WebResUtil.getId(context, "default_web_view"));
        this.mDefaultWebToolBar = (DefaultWebToolBar) findViewById(WebResUtil.getId(context, "default_web_tool_bar"));
        this.mLoadingView = (RelativeLayout) findViewById(WebResUtil.getId(context, "rl_loading"));
        this.mErrorView = (RelativeLayout) findViewById(WebResUtil.getId(context, "rl_error_view"));
        this.mTvLoadingHint = (TextView) findViewById(WebResUtil.getId(context, "tv_loading_hint"));
        this.mTvErrorHint = (TextView) findViewById(WebResUtil.getId(context, "tv_error_hint"));
        this.mTvLoadingHint.setText(this.mLoadingHint);
    }

    public void reload() {
        this.mWebView.reload();
    }

    public WebView getRealWebView() {
        return this.mWebView;
    }

    public void addJavascriptInterface(Object obj, String interfaceName) {
        this.mWebView.getSettings().setJavaScriptEnabled(true);
        this.mWebView.addJavascriptInterface(obj, interfaceName);
    }

    public void setLoadingHint(String loadingHint) {
        this.mLoadingHint = loadingHint;
        this.mTvLoadingHint.setText(loadingHint);
    }

    public void setErrorHint(ErrorHintProvider errorHintProvider) {
        this.mErrorHintProvider = errorHintProvider;
    }

    public void loadUrl(String url, Map<String, String> additionalHttpHeaders) {
        this.mWebView.loadUrl(url, additionalHttpHeaders);
    }

    public void loadUrl(String url) {
        this.mWebView.loadUrl(url);
    }

    public void setErrorViewOnClickListener(View.OnClickListener onClickListener) {
        this.mErrorView.setOnClickListener(onClickListener);
    }

    public void setToolbarClickCloseListener(View.OnClickListener onClickListener) {
        this.mDefaultWebToolBar.setCloseClickListener(onClickListener);
    }

    @Override // com.sq.webview.view.IWebViewLoading
    public void startLoading(Context context) {
        this.mLoadingView.setVisibility(0);
    }

    @Override // com.sq.webview.view.IWebViewLoading
    public void stopLoading(Context context) {
        this.mLoadingView.setVisibility(8);
    }

    @Override // com.sq.webview.view.IWebViewError
    public void showWebError(Context context, String url, String msg, int errorCode) {
        this.mErrorView.setVisibility(0);
        this.mTvErrorHint.setText(this.mErrorHintProvider.provideErrorHint(url, msg, errorCode));
    }

    public void setWebAgent(SQWebAgent webAgent) {
        this.mWebFunctionWrapper = webAgent.bind(this.mWebView).setLoading(this).setErrorView(this).setWebToolBar(this.mDefaultWebToolBar);
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
}
