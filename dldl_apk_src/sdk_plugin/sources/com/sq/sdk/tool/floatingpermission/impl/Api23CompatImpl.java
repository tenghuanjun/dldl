package com.sq.sdk.tool.floatingpermission.impl;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.util.Log;
import com.sq.sdk.tool.floatingpermission.FloatingPermissionCompat;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class Api23CompatImpl implements FloatingPermissionCompat.CompatImpl {
    private static final String TAG = "Api23CompatImpl";

    @Override // com.sq.sdk.tool.floatingpermission.FloatingPermissionCompat.CompatImpl
    public boolean isSupported() {
        return true;
    }

    @Override // com.sq.sdk.tool.floatingpermission.FloatingPermissionCompat.CompatImpl
    public boolean check(Context context) {
        if (Build.VERSION.SDK_INT < 23) {
            return true;
        }
        try {
            return ((Boolean) Settings.class.getDeclaredMethod("canDrawOverlays", Context.class).invoke(null, context)).booleanValue();
        } catch (Exception e) {
            Log.e(TAG, Log.getStackTraceString(e));
            return true;
        }
    }

    @Override // com.sq.sdk.tool.floatingpermission.FloatingPermissionCompat.CompatImpl
    public boolean apply(Context context) {
        try {
            Intent intent = new Intent(Settings.class.getDeclaredField("ACTION_MANAGE_OVERLAY_PERMISSION").get(null).toString());
            intent.setFlags(268435456);
            intent.setData(Uri.parse("package:" + context.getPackageName()));
            context.startActivity(intent);
            return true;
        } catch (Exception e) {
            Log.e(TAG, Log.getStackTraceString(e));
            return false;
        }
    }
}
