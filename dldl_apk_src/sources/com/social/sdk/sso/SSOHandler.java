package com.social.sdk.sso;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.social.sdk.common.listener.OnAuthListener;
import com.social.sdk.common.listener.ShareListener;
import com.social.sdk.platform.Platform;
import com.social.sdk.share.media.IShareMedia;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public abstract class SSOHandler {
    public void authorize(Activity activity, OnAuthListener onAuthListener) {
    }

    public abstract String getAppId();

    public boolean isInstall() {
        return true;
    }

    public void onActivityResult(int i, int i2, Intent intent) {
    }

    public void onCreate(Context context, Platform platform) {
    }

    public void share(Activity activity, IShareMedia iShareMedia, ShareListener shareListener) {
    }

    public boolean isSupport() {
        return !TextUtils.isEmpty(getAppId());
    }
}
