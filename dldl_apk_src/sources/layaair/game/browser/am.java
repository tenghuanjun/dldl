package layaair.game.browser;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.AbsoluteLayout;
import layaair.game.conch.LayaConch5;
import layaair.game.config.config;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public final class am extends AbsoluteLayout implements View.OnKeyListener {
    public ao a;
    private an b;
    private al c;
    private WebView d;
    private boolean e;
    private int f;
    private int g;
    private int h;
    private int i;
    private LayaConch5 j;

    @SuppressLint({"JavascriptInterface"})
    public am(Context context, LayaConch5 layaConch5) {
        super(context);
        this.a = new ao();
        this.b = null;
        this.c = null;
        this.d = null;
        this.e = false;
        this.f = 0;
        this.g = 0;
        this.h = 0;
        this.i = 0;
        this.j = null;
        this.j = layaConch5;
        this.b = new an();
        this.b.a = this;
        this.d = new WebView(context);
        this.d.setWebViewClient(this.b);
        this.d.getSettings().setJavaScriptEnabled(true);
        this.d.getSettings().setDefaultTextEncodingName("GBK");
        this.d.getSettings().setDomStorageEnabled(true);
        this.d.getSettings().setAppCacheMaxSize(8388608L);
        this.d.getSettings().setAppCachePath(context.getApplicationContext().getCacheDir().getAbsolutePath() + "/webviewCache");
        this.d.getSettings().setAllowFileAccess(true);
        this.d.getSettings().setAppCacheEnabled(true);
        this.d.setScrollBarStyle(0);
        this.d.setBackgroundColor(0);
        addView(this.d);
        this.c = new al(this);
        this.d.setWebChromeClient(this.c);
        this.d.addJavascriptInterface(this, com.igexin.push.core.b.Y);
        this.d.setOnKeyListener(this);
    }

    private void a(int i, int i2, int i3, int i4) {
        this.d.setLayoutParams(new AbsoluteLayout.LayoutParams(i3, i4, i, i2));
    }

    private void a(String str) {
        an anVar = this.b;
        if (anVar != null) {
            anVar.shouldOverrideUrlLoading(this.d, str);
        }
    }

    @JavascriptInterface
    public static void b(String str, String str2, String str3) {
        Log.i("LayaWebView", "CallConchJSFuncton functionName=" + str + ",sJsonParam=" + str2 + ",callbackFunction=" + str3);
        ConchJNI.callConchJSFunction(str, str2, str3);
    }

    public final void a() {
        removeAllViews();
        an anVar = this.b;
        if (anVar != null) {
            anVar.a = null;
            this.b = null;
        }
        this.j = null;
        if (this.d != null) {
            if (Build.VERSION.SDK_INT >= 11) {
                this.d.removeJavascriptInterface(com.igexin.push.core.b.Y);
            }
            this.d.setWebViewClient(null);
            this.d.setWebChromeClient(null);
            this.d.setOnKeyListener(null);
            this.d = null;
        }
    }

    @JavascriptInterface
    public final void a(String str, String str2, String str3) {
        if (this.d != null) {
            String str4 = "javascript: try{" + str + "(\"" + str2 + "\",\"" + str3 + "\");}catch(e){window.runtime.JSLog('CallJSFunction err ' + e.stack );}";
            Log.i("LayaWebView", str4);
            this.d.loadUrl(str4);
        }
    }

    public final boolean a(String str, int i, int i2, int i3, int i4) {
        int i5;
        if (this.j == null) {
            return false;
        }
        this.d.requestFocus();
        if (!this.e) {
            this.f = i;
            this.g = i2;
            this.h = i3;
            this.i = i4;
            this.j.getAbsLayout().addView(this);
            a(this.f, this.g, this.h, this.i);
            a(str);
            this.e = true;
            return true;
        }
        int i6 = this.h;
        if (i6 == i3 && (i5 = this.i) == i4 && this.f == i && this.g == i2) {
            a(i, i2, i6, i5);
            a(str);
            setVisibility(0);
            return true;
        }
        this.h = i3;
        this.i = i4;
        a(i, i2, this.h, this.i);
        a(str);
        setVisibility(0);
        return true;
    }

    public final WebView b() {
        return this.d;
    }

    public final void c() {
        this.d.requestFocus();
        setVisibility(0);
    }

    public final void d() {
        this.d.clearFocus();
        this.d.loadDataWithBaseURL(null, "", "text/html", "utf-8", null);
        setVisibility(4);
        ConchJNI.closeExternalWebView();
    }

    public final void e() {
        Log.i("LayaWebView", "javascript:window.conchBack&&window.conchBack();");
        this.d.loadUrl("javascript:window.conchBack&&window.conchBack();");
        this.d.clearFocus();
        setVisibility(4);
    }

    @Override // android.view.View.OnKeyListener
    public final boolean onKey(View view, int i, KeyEvent keyEvent) {
        if (this.a.f.booleanValue() && keyEvent.getAction() == 0) {
            if (i == 4 && this.d.canGoBack()) {
                if (config.GetInstance().m_bBackkeyWebviewHide) {
                    e();
                } else {
                    d();
                }
                return true;
            }
            if (i == 4 && !this.d.canGoBack()) {
                if (config.GetInstance().m_bBackkeyWebviewHide) {
                    e();
                } else {
                    d();
                }
                return true;
            }
        }
        return false;
    }
}
