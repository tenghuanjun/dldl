package com.sq.sdk.tool.floatingpermission;

import android.app.AppOpsManager;
import android.content.Context;
import android.os.Binder;
import android.os.Build;
import android.util.Log;
import com.sq.sdk.tool.floatingpermission.impl.Api23CompatImpl;
import com.sq.sdk.tool.floatingpermission.impl.BelowApi23CompatImpl;
import com.sq.sdk.tool.floatingpermission.impl.HuaweiCompatImpl;
import com.sq.sdk.tool.floatingpermission.impl.MeizuCompatImpl;
import com.sq.sdk.tool.floatingpermission.impl.MiuiCompatImpl;
import com.sq.sdk.tool.floatingpermission.impl.QihooCompatImpl;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class FloatingPermissionCompat {
    private static final String TAG = "FloatPermissionCompat";
    private static FloatingPermissionCompat sInstance;
    private CompatImpl compat;

    public interface CompatImpl {
        boolean apply(Context context);

        boolean check(Context context);

        boolean isSupported();
    }

    private FloatingPermissionCompat() {
        if (Build.VERSION.SDK_INT < 23) {
            if (RoomUtils.isMiui()) {
                this.compat = new MiuiCompatImpl();
                return;
            }
            if (RoomUtils.isMeizu()) {
                this.compat = new MeizuCompatImpl();
                return;
            }
            if (RoomUtils.isHuawei()) {
                this.compat = new HuaweiCompatImpl();
                return;
            } else if (RoomUtils.isQihoo()) {
                this.compat = new QihooCompatImpl();
                return;
            } else {
                this.compat = new BelowApi23CompatImpl() { // from class: com.sq.sdk.tool.floatingpermission.FloatingPermissionCompat.1
                    @Override // com.sq.sdk.tool.floatingpermission.FloatingPermissionCompat.CompatImpl
                    public boolean apply(Context context) {
                        return false;
                    }

                    @Override // com.sq.sdk.tool.floatingpermission.FloatingPermissionCompat.CompatImpl
                    public boolean isSupported() {
                        return false;
                    }
                };
                return;
            }
        }
        if (RoomUtils.isMeizu()) {
            this.compat = new MeizuCompatImpl();
        } else {
            this.compat = new Api23CompatImpl();
        }
    }

    public static FloatingPermissionCompat get() {
        if (sInstance == null) {
            sInstance = new FloatingPermissionCompat();
        }
        return sInstance;
    }

    public boolean check(Context context) {
        return this.compat.check(context);
    }

    public boolean isSupported() {
        return this.compat.isSupported();
    }

    public boolean apply(Context context) {
        if (isSupported()) {
            return this.compat.apply(context);
        }
        return false;
    }

    public static boolean checkOp(Context context, int i) {
        int i2 = Build.VERSION.SDK_INT;
        if (context == null) {
            return false;
        }
        if (i2 >= 19) {
            try {
                AppOpsManager appOpsManager = (AppOpsManager) context.getSystemService("appops");
                if (appOpsManager == null) {
                    return false;
                }
                return ((Integer) AppOpsManager.class.getDeclaredMethod("checkOp", Integer.TYPE, Integer.TYPE, String.class).invoke(appOpsManager, Integer.valueOf(i), Integer.valueOf(Binder.getCallingUid()), context.getPackageName())).intValue() == 0;
            } catch (Exception e) {
                Log.e(TAG, Log.getStackTraceString(e));
            }
        } else {
            Log.e(TAG, "Below API 19 cannot invoke!");
        }
        return false;
    }
}
