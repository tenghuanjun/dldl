package layaair.game.browser;

import android.graphics.Bitmap;
import android.os.Build;
import android.util.Log;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.nirvana.tools.crash.CustomLogInfoBuilder;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import layaair.game.conch.LayaConch5;
import layaair.game.config.config;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public final class an extends WebViewClient {
    public am a = null;

    @Override // android.webkit.WebViewClient
    public final void onLoadResource(WebView webView, String str) {
        super.onLoadResource(webView, str);
    }

    @Override // android.webkit.WebViewClient
    public final void onPageFinished(WebView webView, String str) {
        InputStream inputStreamOpen;
        ByteArrayOutputStream byteArrayOutputStream;
        Log.i("LayaWebView", "onPageFinished");
        String str2 = "javascript:";
        try {
            inputStreamOpen = LayaConch5.GetInstance().m_AM.open("scripts/webviewInit.js");
            byteArrayOutputStream = new ByteArrayOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (true) {
            int i = inputStreamOpen.read();
            if (i == -1) {
                break;
            } else {
                byteArrayOutputStream.write(i);
            }
            Log.i("LayaWebView", str2);
            this.a.b().loadUrl(str2);
            super.onPageFinished(webView, str);
        }
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        byteArrayOutputStream.close();
        inputStreamOpen.close();
        str2 = "javascript:" + new String(byteArray, "UTF-8");
        Log.i("LayaWebView", str2);
        this.a.b().loadUrl(str2);
        super.onPageFinished(webView, str);
    }

    @Override // android.webkit.WebViewClient
    public final void onPageStarted(WebView webView, String str, Bitmap bitmap) {
        Log.d("LayaWebView", "onPageStarted url=" + str);
        super.onPageStarted(webView, str, bitmap);
    }

    @Override // android.webkit.WebViewClient
    public final WebResourceResponse shouldInterceptRequest(WebView webView, String str) {
        Log.i("LayaWebView", "shouldInterceptRequest url=" + str);
        if (config.GetInstance().m_sConchGameUrl != null && str.indexOf(config.GetInstance().m_sConchGameUrl) >= 0) {
            try {
                am.b("document.setReferrer", config.GetInstance().m_sWebviewUrl, "");
                am.b("window.loadConchUrl", str, "");
                ExportJavaFunction.hideExternalLink();
                if (Build.VERSION.SDK_INT >= 11) {
                    return new WebResourceResponse("html", "UTF-8", null);
                }
            } catch (Exception e) {
                Log.i("0", CustomLogInfoBuilder.LOG_TYPE + e.toString());
            }
        }
        return super.shouldInterceptRequest(webView, str);
    }

    @Override // android.webkit.WebViewClient
    public final boolean shouldOverrideUrlLoading(WebView webView, String str) {
        Log.i("LayaWebView", "shouldOverrideUrlLoading url=" + str);
        if (config.GetInstance().m_sConchGameUrl != null) {
            if (str.indexOf(config.GetInstance().m_sConchGameUrl) >= 0) {
                am.b("window.loadConchUrl", str, "");
                ExportJavaFunction.closeExternalLink();
            } else {
                webView.loadUrl(str);
            }
        }
        webView.loadUrl(str);
        return true;
    }
}
