package com.alipay.sdk.widget;

import android.view.View;
import com.alipay.sdk.widget.WebViewWindow;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
class q implements View.OnClickListener {
    final /* synthetic */ WebViewWindow a;

    q(WebViewWindow webViewWindow) {
        this.a = webViewWindow;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        WebViewWindow.c cVar = this.a.i;
        if (cVar != null) {
            view.setEnabled(false);
            WebViewWindow.f.postDelayed(new r(this, view), 256L);
            if (view != this.a.a) {
                if (view == this.a.c) {
                    cVar.b(this.a);
                    return;
                }
                return;
            }
            cVar.a(this.a);
        }
    }
}
