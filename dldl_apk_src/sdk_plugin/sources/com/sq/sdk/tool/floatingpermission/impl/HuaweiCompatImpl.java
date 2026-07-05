package com.sq.sdk.tool.floatingpermission.impl;

import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.sq.sdk.tool.floatingpermission.RoomUtils;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class HuaweiCompatImpl extends BelowApi23CompatImpl {
    private static final String TAG = "HuaweiCompatImpl";

    @Override // com.sq.sdk.tool.floatingpermission.FloatingPermissionCompat.CompatImpl
    public boolean isSupported() {
        return true;
    }

    @Override // com.sq.sdk.tool.floatingpermission.FloatingPermissionCompat.CompatImpl
    public boolean apply(Context context) {
        try {
            Intent intent = new Intent();
            intent.setFlags(268435456);
            intent.setComponent(new ComponentName("com.huawei.systemmanager", "com.huawei.systemmanager.addviewmonitor.AddViewMonitorActivity"));
            if (RoomUtils.getEmuiVersion() == 3.1d) {
                context.startActivity(intent);
            } else {
                intent.setComponent(new ComponentName("com.huawei.systemmanager", "com.huawei.notificationmanager.ui.NotificationManagmentActivity"));
                context.startActivity(intent);
            }
            return false;
        } catch (ActivityNotFoundException e) {
            Intent intent2 = new Intent();
            intent2.setFlags(268435456);
            intent2.setComponent(new ComponentName("com.Android.settings", "com.android.settings.permission.TabItem"));
            context.startActivity(intent2);
            Log.e(TAG, Log.getStackTraceString(e));
            return false;
        } catch (SecurityException e2) {
            Intent intent3 = new Intent();
            intent3.setFlags(268435456);
            intent3.setComponent(new ComponentName("com.huawei.systemmanager", "com.huawei.permissionmanager.ui.MainActivity"));
            context.startActivity(intent3);
            Log.e(TAG, Log.getStackTraceString(e2));
            return false;
        } catch (Exception e3) {
            Log.e(TAG, Log.getStackTraceString(e3));
            return false;
        }
    }
}
