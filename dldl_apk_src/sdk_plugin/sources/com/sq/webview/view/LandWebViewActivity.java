package com.sq.webview.view;

import android.content.Context;
import android.content.Intent;
import com.plugin.standard.BaseActivity;
import com.sq.webview.SQBaseJSInterface;
import com.sq.webview.SQWebAgent;
import com.sq.webview.WebHook;
import java.util.List;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class LandWebViewActivity extends BaseActivity {
    public static void startPage(Context context, String url, String title, SQWebAgent agent, boolean showToolbar, boolean showTitleBar, boolean hideSystemBar, SQBaseJSInterface jsInterface, List<WebHook> webHooks, int bgColor) {
        Intent intent = new Intent(context, (Class<?>) LandWebViewActivity.class);
        intent.putExtra(WebViewActivity.KEY_URL, url);
        intent.putExtra(WebViewActivity.KEY_TITLE, title);
        intent.putExtra(WebViewActivity.KEY_SHOW_TITLE_BAR, showTitleBar);
        intent.putExtra(WebViewActivity.KEY_SHOW_TOOL_BAR, showToolbar);
        intent.putExtra(WebViewActivity.KEY_HIDE_SYSTEM_BAR, hideSystemBar);
        intent.putExtra(WebViewActivity.KEY_BACKGROUND_COLOR, bgColor);
        sSQBaseJSInterface = jsInterface;
        sAgent = agent;
        sWebHooks = webHooks;
        context.startActivity(intent);
    }
}
