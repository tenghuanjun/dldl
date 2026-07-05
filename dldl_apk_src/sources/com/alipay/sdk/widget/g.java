package com.alipay.sdk.widget;

import android.app.Activity;
import android.text.TextUtils;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.widget.FrameLayout;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public abstract class g extends FrameLayout {
    protected Activity a;

    public abstract void a();

    public abstract void a(String str);

    public abstract boolean b();

    public g(Activity activity) {
        super(activity);
        this.a = activity;
    }

    public void a(String str, String str2) {
        if (TextUtils.isEmpty(str2)) {
            return;
        }
        CookieSyncManager.createInstance(this.a.getApplicationContext()).sync();
        CookieManager.getInstance().setCookie(str, str2);
        CookieSyncManager.getInstance().sync();
    }
}
