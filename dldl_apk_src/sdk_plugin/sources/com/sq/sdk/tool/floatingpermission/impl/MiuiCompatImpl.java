package com.sq.sdk.tool.floatingpermission.impl;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import com.sq.sdk.tool.floatingpermission.RoomUtils;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class MiuiCompatImpl extends BelowApi23CompatImpl {
    private static final String TAG = "MiuiCompatImpl";
    private int miuiVersion;

    public MiuiCompatImpl() {
        this.miuiVersion = -1;
        this.miuiVersion = RoomUtils.getMiuiVersion();
    }

    @Override // com.sq.sdk.tool.floatingpermission.FloatingPermissionCompat.CompatImpl
    public boolean isSupported() {
        int i = this.miuiVersion;
        return i >= 5 && i <= 8;
    }

    @Override // com.sq.sdk.tool.floatingpermission.FloatingPermissionCompat.CompatImpl
    public boolean apply(Context context) {
        int i = this.miuiVersion;
        if (i == 5) {
            return applyV5(context);
        }
        if (i == 6) {
            return applyV6(context);
        }
        if (i == 7) {
            return applyV7(context);
        }
        if (i == 8) {
            return applyV8(context);
        }
        Log.e(TAG, "this is a special MIUI rom version, its version code " + this.miuiVersion);
        return false;
    }

    private boolean applyV8(Context context) {
        Intent intent = new Intent("miui.intent.action.APP_PERM_EDITOR");
        intent.setClassName("com.miui.securitycenter", "com.miui.permcenter.permissions.PermissionsEditorActivity");
        intent.putExtra("extra_pkgname", context.getPackageName());
        intent.setFlags(268435456);
        if (RoomUtils.isIntentAvailable(context, intent)) {
            context.startActivity(intent);
            return true;
        }
        Intent intent2 = new Intent("miui.intent.action.APP_PERM_EDITOR");
        intent2.setPackage("com.miui.securitycenter");
        intent2.putExtra("extra_pkgname", context.getPackageName());
        intent2.setFlags(268435456);
        if (RoomUtils.isIntentAvailable(context, intent2)) {
            context.startActivity(intent2);
            return true;
        }
        return applyV5(context);
    }

    private boolean applyV7(Context context) {
        Intent intent = new Intent("miui.intent.action.APP_PERM_EDITOR");
        intent.setClassName("com.miui.securitycenter", "com.miui.permcenter.permissions.AppPermissionsEditorActivity");
        intent.putExtra("extra_pkgname", context.getPackageName());
        intent.setFlags(268435456);
        if (RoomUtils.isIntentAvailable(context, intent)) {
            context.startActivity(intent);
            return true;
        }
        Log.e(TAG, "applyV7 Intent is not available!");
        return false;
    }

    private boolean applyV6(Context context) {
        Intent intent = new Intent("miui.intent.action.APP_PERM_EDITOR");
        intent.setClassName("com.miui.securitycenter", "com.miui.permcenter.permissions.AppPermissionsEditorActivity");
        intent.putExtra("extra_pkgname", context.getPackageName());
        intent.setFlags(268435456);
        if (RoomUtils.isIntentAvailable(context, intent)) {
            context.startActivity(intent);
            return true;
        }
        Log.e(TAG, "applyV6 Intent is not available!");
        return false;
    }

    private boolean applyV5(Context context) {
        String packageName = context.getPackageName();
        Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
        intent.setData(Uri.fromParts("package", packageName, null));
        intent.setFlags(268435456);
        if (RoomUtils.isIntentAvailable(context, intent)) {
            context.startActivity(intent);
            return true;
        }
        Log.e(TAG, "applyV5 intent is not available!");
        return false;
    }
}
