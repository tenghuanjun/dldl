package com.alipay.sdk.widget;

import android.view.animation.Animation;
import com.alipay.sdk.widget.j;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
class l extends j.a {
    final /* synthetic */ WebViewWindow a;
    final /* synthetic */ j b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    l(j jVar, WebViewWindow webViewWindow) {
        super(jVar, null);
        this.b = jVar;
        this.a = webViewWindow;
    }

    @Override // com.alipay.sdk.widget.j.a, android.view.animation.Animation.AnimationListener
    public void onAnimationEnd(Animation animation) {
        this.a.a();
        this.b.v = false;
    }
}
