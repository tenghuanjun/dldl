package com.alipay.sdk.widget;

import android.view.animation.Animation;
import com.alipay.sdk.widget.j;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
class m extends j.a {
    final /* synthetic */ WebViewWindow a;
    final /* synthetic */ String b;
    final /* synthetic */ j c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    m(j jVar, WebViewWindow webViewWindow, String str) {
        super(jVar, null);
        this.c = jVar;
        this.a = webViewWindow;
        this.b = str;
    }

    @Override // com.alipay.sdk.widget.j.a, android.view.animation.Animation.AnimationListener
    public void onAnimationEnd(Animation animation) {
        this.c.removeView(this.a);
        this.c.x.a(this.b);
        this.c.v = false;
    }
}
