package com.cmic.sso.sdk.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.cmic.sso.sdk.AuthThemeConfig;
import com.mobile.auth.k.n;
import com.mobile.auth.k.w;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes6.dex */
public class a extends Dialog {
    private WebView a;
    private String b;
    private String c;
    private LinearLayout d;

    /* JADX INFO: renamed from: com.cmic.sso.sdk.widget.a$a, reason: collision with other inner class name */
    class ViewOnClickListenerC0034a implements View.OnClickListener {
        ViewOnClickListenerC0034a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            a.this.a.stopLoading();
            a.this.cancel();
        }
    }

    class b implements View.OnClickListener {
        b() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            a.this.a.stopLoading();
            a.this.cancel();
        }
    }

    class c extends WebViewClient {
        c() {
        }

        @Override // android.webkit.WebViewClient
        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            a.this.a.loadUrl(str);
            return true;
        }
    }

    public a(Context context, int i, String str, String str2) {
        super(context, i);
        try {
            this.c = str;
            this.b = str2;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private ViewGroup b() {
        View viewFindViewById;
        LinearLayout linearLayout = new LinearLayout(getContext());
        this.d = linearLayout;
        linearLayout.setOrientation(1);
        this.d.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        AuthThemeConfig authThemeConfigA = com.mobile.auth.e.a.a((Context) null).a();
        int clauseLayoutResID = authThemeConfigA.getClauseLayoutResID();
        String str = TextUtils.isEmpty(this.c) ? "服务条款" : this.c;
        if (clauseLayoutResID != -1) {
            RelativeLayout relativeLayoutA = w.a(getContext(), getLayoutInflater().inflate(clauseLayoutResID, (ViewGroup) this.d, false), 1118481, 0, str, (View.OnClickListener) null);
            String clauseLayoutReturnID = authThemeConfigA.getClauseLayoutReturnID();
            if (!TextUtils.isEmpty(clauseLayoutReturnID) && (viewFindViewById = relativeLayoutA.findViewById(n.a(getContext(), clauseLayoutReturnID))) != null) {
                viewFindViewById.setOnClickListener(new ViewOnClickListenerC0034a());
            }
            this.d.addView(relativeLayoutA);
        } else {
            this.d.addView(w.a(getContext(), (View) null, 1118481, 2236962, str, new b()));
        }
        return this.d;
    }

    private void c() {
        WebView webView = new WebView(getContext());
        this.a = webView;
        WebSettings settings = webView.getSettings();
        settings.setAllowFileAccess(false);
        settings.setAllowContentAccess(false);
        settings.setSavePassword(false);
        settings.setJavaScriptEnabled(true);
        this.d.addView(this.a, new LinearLayout.LayoutParams(-1, -1));
        if (Build.VERSION.SDK_INT < 17) {
            this.a.removeJavascriptInterface("searchBoxJavaBridge_");
            this.a.removeJavascriptInterface("accessibility");
            this.a.removeJavascriptInterface("accessibilityTraversal");
        }
        this.a.setWebViewClient(new c());
        this.a.loadUrl(this.b);
    }

    protected void a() {
        View decorView;
        requestWindowFeature(1);
        int i = 0;
        getWindow().setFeatureDrawableAlpha(0, 0);
        AuthThemeConfig authThemeConfigA = com.mobile.auth.e.a.a((Context) null).a();
        if (Build.VERSION.SDK_INT >= 21 && authThemeConfigA.getStatusBarColor() != 0) {
            getWindow().addFlags(Integer.MIN_VALUE);
            getWindow().clearFlags(67108864);
            getWindow().setStatusBarColor(authThemeConfigA.getStatusBarColor());
            getWindow().setNavigationBarColor(authThemeConfigA.getStatusBarColor());
        }
        if (Build.VERSION.SDK_INT >= 23) {
            if (authThemeConfigA.isLightColor()) {
                decorView = getWindow().getDecorView();
                i = 8192;
            } else {
                decorView = getWindow().getDecorView();
            }
            decorView.setSystemUiVisibility(i);
        }
        setContentView(b());
    }

    @Override // android.app.Dialog, android.content.DialogInterface
    public void dismiss() {
        super.dismiss();
        WebView webView = this.a;
        if (webView != null) {
            webView.stopLoading();
        }
    }

    @Override // android.app.Dialog
    public void show() {
        if (this.d == null) {
            a();
        }
        if (this.a == null) {
            c();
        }
        super.show();
    }
}
