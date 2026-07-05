package com.sq.sdk.tool.floatingpermission.impl;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.sq.sdk.tool.floatingpermission.RoomUtils;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class QihooCompatImpl extends BelowApi23CompatImpl {
    private static final String TAG = "QihooCompatImpl";

    @Override // com.sq.sdk.tool.floatingpermission.FloatingPermissionCompat.CompatImpl
    public boolean isSupported() {
        return true;
    }

    @Override // com.sq.sdk.tool.floatingpermission.FloatingPermissionCompat.CompatImpl
    public boolean apply(Context context) {
        Intent intent = new Intent();
        intent.setClassName("com.android.settings", "com.android.settings.Settings$OverlaySettingsActivity");
        intent.setFlags(268435456);
        if (RoomUtils.isIntentAvailable(context, intent)) {
            context.startActivity(intent);
            return true;
        }
        intent.setClassName("com.qihoo360.mobilesafe", "com.qihoo360.mobilesafe.ui.index.appEnterActivity");
        if (RoomUtils.isIntentAvailable(context, intent)) {
            context.startActivity(intent);
            return true;
        }
        Log.e(TAG, "can't open permission page with particular name, please use \"adb shell dumpsys activity\" command and tell me the name of the float window permission page");
        return false;
    }
}
