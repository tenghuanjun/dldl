package com.cmic.sso.sdk.view;

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
import androidx.core.view.accessibility.AccessibilityEventCompat;

/* JADX INFO: loaded from: classes2.dex */
public class h extends Dialog {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private WebView f428a;
    private String b;
    private String c;
    private LinearLayout d;

    public h(Context context, int i, String str, String str2) {
        super(context, i);
        try {
            this.c = str;
            this.b = str2;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private ViewGroup c() {
        View viewFindViewById;
        try {
            LinearLayout linearLayout = new LinearLayout(getContext());
            this.d = linearLayout;
            linearLayout.setOrientation(1);
            this.d.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
            a aVarA = com.mobile.auth.f.a.a(getContext()).a();
            int iE = aVarA.e();
            String str = TextUtils.isEmpty(this.c) ? com.cmic.sso.sdk.c.d[aVarA.ap()] : this.c;
            if (iE != -1) {
                RelativeLayout relativeLayoutA = i.a(getContext(), getLayoutInflater().inflate(iE, (ViewGroup) this.d, false), 1118481, 0, str, (View.OnClickListener) null);
                String strF = aVarA.f();
                if (!TextUtils.isEmpty(strF) && (viewFindViewById = relativeLayoutA.findViewById(g.a(getContext(), strF))) != null) {
                    viewFindViewById.setOnClickListener(new View.OnClickListener() { // from class: com.cmic.sso.sdk.view.h.1
                        @Override // android.view.View.OnClickListener
                        public void onClick(View view) {
                            h.this.f428a.stopLoading();
                            h.this.b();
                        }
                    });
                }
                this.d.addView(relativeLayoutA);
            } else {
                this.d.addView(i.a(getContext(), (View) null, 1118481, 2236962, str, new View.OnClickListener() { // from class: com.cmic.sso.sdk.view.h.2
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        h.this.f428a.stopLoading();
                        h.this.b();
                    }
                }));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this.d;
    }

    private void d() {
        WebView webView = new WebView(getContext());
        this.f428a = webView;
        WebSettings settings = webView.getSettings();
        settings.setAllowFileAccess(false);
        settings.setAllowContentAccess(false);
        settings.setSavePassword(false);
        settings.setJavaScriptEnabled(true);
        this.d.addView(this.f428a, new LinearLayout.LayoutParams(-1, -1));
        this.f428a.setWebViewClient(new WebViewClient() { // from class: com.cmic.sso.sdk.view.h.3
            @Override // android.webkit.WebViewClient
            public boolean shouldOverrideUrlLoading(WebView webView2, String str) {
                h.this.f428a.loadUrl(str);
                return true;
            }
        });
        this.f428a.loadUrl(this.b);
    }

    protected void a() {
        View decorView;
        requestWindowFeature(1);
        int i = 0;
        getWindow().setFeatureDrawableAlpha(0, 0);
        a aVarA = com.mobile.auth.f.a.a(getContext()).a();
        if (aVarA.a() != 0) {
            getWindow().addFlags(Integer.MIN_VALUE);
            getWindow().clearFlags(AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL);
            getWindow().setStatusBarColor(aVarA.a());
            getWindow().setNavigationBarColor(aVarA.a());
        }
        if (Build.VERSION.SDK_INT >= 23) {
            if (aVarA.b()) {
                decorView = getWindow().getDecorView();
                i = 8192;
            } else {
                decorView = getWindow().getDecorView();
            }
            decorView.setSystemUiVisibility(i);
        }
        setContentView(c());
    }

    public void b() {
        if (this.f428a.canGoBack()) {
            this.f428a.goBack();
        } else {
            dismiss();
        }
    }

    @Override // android.app.Dialog, android.content.DialogInterface
    public void dismiss() {
        super.dismiss();
        WebView webView = this.f428a;
        if (webView != null) {
            webView.stopLoading();
        }
    }

    @Override // android.app.Dialog
    public void show() {
        if (this.d == null) {
            a();
        }
        if (this.f428a == null) {
            d();
        }
        super.show();
    }
}
