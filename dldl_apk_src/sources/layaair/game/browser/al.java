package layaair.game.browser;

import android.util.Log;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public final class al extends WebChromeClient {
    public al(am amVar) {
    }

    @Override // android.webkit.WebChromeClient
    public final boolean onJsAlert(WebView webView, String str, String str2, JsResult jsResult) {
        Log.e("LayaWebView", "onJsAlert message=" + str2);
        return false;
    }
}
