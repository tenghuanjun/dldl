package com.sq.sdk.tool.floatingpermission.impl;

import android.content.Context;
import android.os.Build;
import com.sq.sdk.tool.floatingpermission.FloatingPermissionCompat;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public abstract class BelowApi23CompatImpl implements FloatingPermissionCompat.CompatImpl {
    @Override // com.sq.sdk.tool.floatingpermission.FloatingPermissionCompat.CompatImpl
    public boolean check(Context context) {
        if (Build.VERSION.SDK_INT >= 19) {
            return FloatingPermissionCompat.checkOp(context, 24);
        }
        return true;
    }
}
