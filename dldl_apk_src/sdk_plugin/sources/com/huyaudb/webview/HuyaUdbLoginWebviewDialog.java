package com.huyaudb.webview;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.Toast;
import com.huyaudb.webview.inter.IWebViewCallback;
import com.huyaudbunify.R;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class HuyaUdbLoginWebviewDialog extends Dialog {
    private final IWebViewCallback mIWebViewCallback;
    private final String requestUrl;
    private TextView titleTextView;
    private HuyaAuthWebView webView;

    @Override // android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        requestWindowFeature(1);
        View viewInflate = View.inflate(getContext(), R.layout.huya_udb_login_strategy_webview, null);
        setContentView(viewInflate);
        setCanceledOnTouchOutside(true);
        getWindow().setBackgroundDrawable(new ColorDrawable(0));
        getWindow().setLayout(-1, -1);
        this.webView = (HuyaAuthWebView) viewInflate.findViewById(R.id.huya_udb_webview);
        this.titleTextView = (TextView) viewInflate.findViewById(R.id.huya_udb_title);
        viewInflate.findViewById(R.id.huya_udb_cancel_btn).setOnClickListener(new View.OnClickListener() { // from class: com.huyaudb.webview.HuyaUdbLoginWebviewDialog.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                HuyaUdbLoginWebviewDialog.this.dismiss();
            }
        });
        WebSettings settings = this.webView.getSettings();
        settings.setDefaultTextEncodingName("utf-8");
        settings.setCacheMode(-1);
        settings.setRenderPriority(WebSettings.RenderPriority.HIGH);
        this.webView.setWebChromeClient(new WebChromeClient() { // from class: com.huyaudb.webview.HuyaUdbLoginWebviewDialog.2
            @Override // android.webkit.WebChromeClient
            public void onReceivedTitle(WebView webView, String str) {
                super.onReceivedTitle(webView, str);
                HuyaUdbLoginWebviewDialog.this.titleTextView.setText(str);
            }
        });
        this.webView.addJavascriptObject(this, null);
        this.webView.loadUrl(this.requestUrl);
    }

    private void showToast(String str) {
        Toast.makeText(getContext(), str, 1).show();
    }

    @JavascriptInterface
    public String quit(Object obj) {
        String str = obj + "";
        Log.i("udbauth", "handler = authResponseCallback, data from web = " + str);
        if (str != null && !str.isEmpty()) {
            IWebViewCallback iWebViewCallback = this.mIWebViewCallback;
            if (iWebViewCallback != null) {
                iWebViewCallback.resposneCallback(0, str);
                dismiss();
            } else {
                showToast("请求初始化失败！(-1)");
            }
        } else {
            showToast("网络请求失败！(-2)");
        }
        return obj + "［syn call］";
    }

    @Override // android.app.Dialog, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4 && this.webView.canGoBack()) {
            this.webView.goBack();
            return true;
        }
        return super.onKeyDown(i, keyEvent);
    }

    public HuyaUdbLoginWebviewDialog(Context context, String str, IWebViewCallback iWebViewCallback) {
        super(context);
        this.requestUrl = str;
        this.mIWebViewCallback = iWebViewCallback;
    }
}
