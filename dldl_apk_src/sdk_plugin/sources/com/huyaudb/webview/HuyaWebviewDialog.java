package com.huyaudb.webview;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.widget.Toast;
import com.huyaudb.webview.inter.IWebViewCallback;
import com.huyaudbunify.R;
import com.huyaudbunify.dialog.msg.AuthLoginWebviewBean;
import com.j256.ormlite.stmt.query.SimpleComparison;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class HuyaWebviewDialog extends Dialog {
    private final AuthLoginWebviewBean mAuthLoginWebviewBean;
    private final IWebViewCallback mIWebViewCallback;
    private final String requestUrl;
    private HuyaAuthWebView webView;

    @Override // android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        requestWindowFeature(1);
        View viewInflate = View.inflate(getContext(), R.layout.huya_udb_login_webview, null);
        setContentView(viewInflate);
        setCanceledOnTouchOutside(true);
        getWindow().setBackgroundDrawable(new ColorDrawable(0));
        getWindow().setLayout(-1, -1);
        this.webView = (HuyaAuthWebView) viewInflate.findViewById(R.id.huya_udb_webview);
        viewInflate.findViewById(R.id.huya_udb_cancel_btn).setOnClickListener(new View.OnClickListener() { // from class: com.huyaudb.webview.HuyaWebviewDialog.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (HuyaWebviewDialog.this.mIWebViewCallback != null) {
                    HuyaWebviewDialog.this.mIWebViewCallback.resposneCallback(1, "");
                }
                HuyaWebviewDialog.this.dismiss();
            }
        });
        WebSettings settings = this.webView.getSettings();
        settings.setDefaultTextEncodingName("utf-8");
        settings.setCacheMode(-1);
        settings.setRenderPriority(WebSettings.RenderPriority.HIGH);
        this.webView.addJavascriptObject(this, null);
        this.webView.loadUrl(getPackUrl(this.requestUrl, this.mAuthLoginWebviewBean.getMapData()));
    }

    private void showToast(String str) {
        Toast.makeText(getContext(), str, 1).show();
    }

    @JavascriptInterface
    public String authResponseCallback(Object obj) {
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

    String getPackUrl(String str, Map<String, String> map) {
        return str + "?" + parseParams(map, "utf-8");
    }

    private static String parseParams(Map<String, String> map, String str) {
        if (map != null && !map.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            try {
                for (Map.Entry<String, String> entry : map.entrySet()) {
                    sb.append(URLEncoder.encode(entry.getKey(), str));
                    sb.append(SimpleComparison.EQUAL_TO_OPERATION);
                    sb.append(URLEncoder.encode(entry.getValue(), str));
                    sb.append("&");
                }
                return sb.substring(0, sb.length() - 1);
            } catch (UnsupportedEncodingException unused) {
            }
        }
        return "";
    }

    @Override // android.app.Dialog, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4) {
            if (this.webView.canGoBack()) {
                this.webView.goBack();
                return true;
            }
            IWebViewCallback iWebViewCallback = this.mIWebViewCallback;
            if (iWebViewCallback != null) {
                iWebViewCallback.resposneCallback(1, "");
            }
        }
        return super.onKeyDown(i, keyEvent);
    }

    public HuyaWebviewDialog(Context context, String str, AuthLoginWebviewBean authLoginWebviewBean, IWebViewCallback iWebViewCallback) {
        super(context);
        this.requestUrl = str;
        this.mAuthLoginWebviewBean = authLoginWebviewBean;
        this.mIWebViewCallback = iWebViewCallback;
    }
}
