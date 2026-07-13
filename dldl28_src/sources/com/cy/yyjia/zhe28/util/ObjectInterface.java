package com.cy.yyjia.zhe28.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import com.bytedance.applog.util.WebViewJsUtil;
import com.cy.yyjia.zhe28.ui.activity.InviteActivity;
import com.cy.yyjia.zhe28.ui.activity.LoginActivity;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ObjectInterface.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\b\u0007\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0018\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000eH\u0007J\b\u0010\u0010\u001a\u00020\fH\u0007J\b\u0010\u0011\u001a\u00020\fH\u0007J\u0010\u0010\u0012\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0007J\u0010\u0010\u0013\u001a\u00020\f2\u0006\u0010\u0014\u001a\u00020\u000eH\u0007J\b\u0010\u0015\u001a\u00020\fH\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0016"}, d2 = {"Lcom/cy/yyjia/zhe28/util/ObjectInterface;", "", "mContext", "Landroid/content/Context;", "wv", "Landroid/webkit/WebView;", "(Landroid/content/Context;Landroid/webkit/WebView;)V", "getMContext", "()Landroid/content/Context;", "getWv", "()Landroid/webkit/WebView;", "callBackJSMethod", "", "data", "", "methodName", "checkWebLogin", "finish", "setLoginSuccessData", "showGameDetail", "gid", "toInvite", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class ObjectInterface {
    public static final int $stable = 8;
    private final Context mContext;
    private final WebView wv;

    public ObjectInterface(Context mContext, WebView wv) {
        Intrinsics.checkNotNullParameter(mContext, "mContext");
        Intrinsics.checkNotNullParameter(wv, "wv");
        this.mContext = mContext;
        this.wv = wv;
    }

    public final Context getMContext() {
        return this.mContext;
    }

    public final WebView getWv() {
        return this.wv;
    }

    @JavascriptInterface
    public final void checkWebLogin() {
        Log.e("checkWebLogin: ", "logout");
        Constant.INSTANCE.logout(this.mContext);
        this.mContext.startActivity(new Intent(this.mContext, (Class<?>) LoginActivity.class));
    }

    @JavascriptInterface
    public final void showGameDetail(String gid) {
        Intrinsics.checkNotNullParameter(gid, "gid");
        Util.gotoGame(this.mContext, Integer.parseInt(gid));
    }

    @JavascriptInterface
    public final void setLoginSuccessData(String data) {
        Intrinsics.checkNotNullParameter(data, "data");
        callBackJSMethod(data, "loginSuccess");
    }

    @JavascriptInterface
    public final void callBackJSMethod(String data, String methodName) {
        Intrinsics.checkNotNullParameter(data, "data");
        Intrinsics.checkNotNullParameter(methodName, "methodName");
        this.wv.loadUrl(WebViewJsUtil.JS_URL_PREFIX + methodName + "('" + data + "')");
    }

    @JavascriptInterface
    public final void toInvite() {
        Util.skipWithLogin(this.mContext, InviteActivity.class);
    }

    @JavascriptInterface
    public final void finish() {
        Context context = this.mContext;
        if (context instanceof Activity) {
            ((Activity) context).finish();
        }
    }
}
