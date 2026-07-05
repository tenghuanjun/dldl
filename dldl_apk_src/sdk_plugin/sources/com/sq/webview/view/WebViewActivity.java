package com.sq.webview.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import com.plugin.standard.BaseActivity;
import com.sq.webview.SQBaseJSInterface;
import com.sq.webview.SQWebAgent;
import com.sq.webview.SimpleWebHook;
import com.sq.webview.WebHook;
import com.sq.webview.util.StatusBarUtil;
import com.sq.webview.util.WebLogUtil;
import com.sq.webview.util.WebResUtil;
import java.util.List;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class WebViewActivity extends BaseActivity implements JSPageOperation {
    public static final String KEY_BACKGROUND_COLOR = "key_background_color";
    public static final String KEY_HIDE_SYSTEM_BAR = "key_hide_system_bar";
    public static final String KEY_SHOW_TITLE_BAR = "key_show_title_bar";
    public static final String KEY_SHOW_TOOL_BAR = "key_show_tool_bar";
    public static final String KEY_TITLE = "key_title";
    public static final String KEY_URL = "key_url";
    protected static SQWebAgent sAgent;
    protected static SQBaseJSInterface sSQBaseJSInterface;
    protected static List<WebHook> sWebHooks;
    private boolean isRunSaveInstanceState = false;
    private SQWebView mSQWebView;

    @Override // com.sq.webview.view.JSPageOperation
    public void invalidateBack(boolean enable) {
    }

    public static void startPage(Context context, String url, String title, SQWebAgent agent, boolean showToolbar, boolean showTitleBar, boolean hideSystemBar, SQBaseJSInterface jsInterface, List<WebHook> webHooks, int bgColor) {
        Intent intent = new Intent(context, (Class<?>) WebViewActivity.class);
        intent.putExtra(KEY_URL, url);
        intent.putExtra(KEY_TITLE, title);
        intent.putExtra(KEY_SHOW_TITLE_BAR, showTitleBar);
        intent.putExtra(KEY_SHOW_TOOL_BAR, showToolbar);
        intent.putExtra(KEY_HIDE_SYSTEM_BAR, hideSystemBar);
        intent.putExtra(KEY_BACKGROUND_COLOR, bgColor);
        sSQBaseJSInterface = jsInterface;
        sAgent = agent;
        sWebHooks = webHooks;
        context.startActivity(intent);
    }

    /* JADX WARN: Multi-variable type inference failed */
    protected void onCreate(Bundle savedInstanceState) {
        super/*android.app.Activity*/.onCreate(savedInstanceState);
        setContentView(WebResUtil.getLayoutId(this, "activity_web_view"));
        String stringExtra = getIntent().getStringExtra(KEY_TITLE);
        String stringExtra2 = getIntent().getStringExtra(KEY_URL);
        boolean booleanExtra = getIntent().getBooleanExtra(KEY_SHOW_TITLE_BAR, false);
        boolean booleanExtra2 = getIntent().getBooleanExtra(KEY_SHOW_TOOL_BAR, false);
        boolean booleanExtra3 = getIntent().getBooleanExtra(KEY_HIDE_SYSTEM_BAR, false);
        int intExtra = getIntent().getIntExtra(KEY_BACKGROUND_COLOR, 0);
        SQWebView sQWebView = (SQWebView) findViewById(WebResUtil.getId(this, "sq_web_view"));
        this.mSQWebView = sQWebView;
        SQWebAgent sQWebAgent = sAgent;
        if (sQWebAgent != null) {
            sQWebView.setWebAgent(sQWebAgent);
            this.mSQWebView.getWebFunctionWrapper().addWebHooks(sWebHooks);
        }
        if (sSQBaseJSInterface == null) {
            sSQBaseJSInterface = new SQBaseJSInterface("Tools", this);
        }
        SQWebAgent sQWebAgent2 = sAgent;
        if (sQWebAgent2 == null) {
            WebLogUtil.w("WebViewActivity onCreate but sAgent is null, finishing...");
            finish();
            return;
        }
        sSQBaseJSInterface.setSQWebAgent(sQWebAgent2);
        sSQBaseJSInterface.setWebView(this.mSQWebView.getRealWebView());
        sSQBaseJSInterface.setJSPageOperation(this);
        SQWebView sQWebView2 = this.mSQWebView;
        SQBaseJSInterface sQBaseJSInterface = sSQBaseJSInterface;
        sQWebView2.addJavascriptInterface(sQBaseJSInterface, sQBaseJSInterface.getInterfaceName());
        this.mSQWebView.setTitleCloseListener(new View.OnClickListener() { // from class: com.sq.webview.view.-$$Lambda$WebViewActivity$VZfZdqXV6Wz3CmtoKvzl9X-MHis
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$onCreate$0$WebViewActivity(view);
            }
        });
        this.mSQWebView.setToolbarClickCloseListener(new View.OnClickListener() { // from class: com.sq.webview.view.-$$Lambda$WebViewActivity$L9EC35nsGSoLK1jbAIUl9_TETUU
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$onCreate$1$WebViewActivity(view);
            }
        });
        this.mSQWebView.setHalfScreen(false);
        refreshTitleBar(booleanExtra);
        refreshToolBar(booleanExtra2);
        this.mSQWebView.setTitle(stringExtra);
        this.mSQWebView.setBackgroundColor(intExtra);
        if (booleanExtra3) {
            this.mSQWebView.getWebFunctionWrapper().addWebHook(new SimpleWebHook() { // from class: com.sq.webview.view.WebViewActivity.1
                @Override // com.sq.webview.SimpleWebHook, com.sq.webview.WebHook
                public void onPageFinished(WebView webView, String url) {
                    super.onPageFinished(webView, url);
                    StatusBarUtil.hideSystemUI(WebViewActivity.this.getWindow());
                }
            });
        }
        this.mSQWebView.loadUrl(stringExtra2);
    }

    public /* synthetic */ void lambda$onCreate$0$WebViewActivity(View v) {
        finish();
    }

    public /* synthetic */ void lambda$onCreate$1$WebViewActivity(View v) {
        finish();
    }

    /* JADX WARN: Multi-variable type inference failed */
    protected void onSaveInstanceState(Bundle outState) {
        super/*android.app.Activity*/.onSaveInstanceState(outState);
        this.isRunSaveInstanceState = true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    protected void onDestroy() {
        super/*android.app.Activity*/.onDestroy();
        if (this.isRunSaveInstanceState) {
            return;
        }
        sAgent = null;
        sWebHooks = null;
        sSQBaseJSInterface = null;
        this.isRunSaveInstanceState = true;
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
        finish();
    }

    @Override // com.sq.webview.view.JSPageOperation
    public void refresh() {
        this.mSQWebView.reload();
    }
}
