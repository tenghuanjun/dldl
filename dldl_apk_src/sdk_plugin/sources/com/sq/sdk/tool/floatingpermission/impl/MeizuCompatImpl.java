package com.sq.sdk.tool.floatingpermission.impl;

import android.content.Context;
import android.content.Intent;
import com.taptap.sdk.db.biz.iap.lib2plus.BillingClientConstants;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class MeizuCompatImpl extends BelowApi23CompatImpl {
    @Override // com.sq.sdk.tool.floatingpermission.FloatingPermissionCompat.CompatImpl
    public boolean isSupported() {
        return true;
    }

    @Override // com.sq.sdk.tool.floatingpermission.FloatingPermissionCompat.CompatImpl
    public boolean apply(Context context) {
        Intent intent = new Intent("com.meizu.safe.security.SHOW_APPSEC");
        intent.setClassName("com.meizu.safe", "com.meizu.safe.security.AppSecActivity");
        intent.putExtra(BillingClientConstants.PACKAGE_NAME, context.getPackageName());
        intent.setFlags(268435456);
        context.startActivity(intent);
        return true;
    }
}
