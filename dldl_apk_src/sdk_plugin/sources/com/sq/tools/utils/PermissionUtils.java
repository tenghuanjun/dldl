package com.sq.tools.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.text.TextUtils;
import com.sq.tools.Logger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public final class PermissionUtils {
    private static volatile boolean isRequesting;
    private static final List<PermissionBundle> permissionsList = new ArrayList();

    public interface PermissionCallback {
        void onDenied(String str);

        void onGranted(String str);
    }

    public static boolean hasAndroidPermission(Context context, String str) {
        return SoftwareUtils.getAndroidVersion() < 23 || context.checkSelfPermission(str) == 0;
    }

    public static boolean isPermissionDefine(Activity activity, String str) {
        try {
            PackageInfo packageInfo = activity.getApplication().getPackageManager().getPackageInfo(activity.getApplication().getPackageName(), 4096);
            if (packageInfo != null) {
                for (String str2 : packageInfo.requestedPermissions) {
                    if (str2.equals(str)) {
                        return true;
                    }
                }
            } else {
                Logger.error("PackageInfo is null, can not figure out permission define or not", new Object[0]);
            }
            return false;
        } catch (PackageManager.NameNotFoundException e) {
            Logger.error("Can not figure out permission define or not since can not get PackageInfo from application", e);
            return false;
        }
    }

    public static void jumpAppSetting(Activity activity) {
        activity.startActivity(new Intent("android.settings.APPLICATION_DETAILS_SETTINGS", Uri.parse("package:" + activity.getPackageName())));
    }

    public static void requestAndroidPermission(Activity activity, String str, PermissionCallback permissionCallback) {
        requestAndroidPermission(activity, new String[]{str}, permissionCallback);
    }

    public static void requestAndroidPermission(Activity activity, String[] strArr, PermissionCallback permissionCallback) {
        PermissionCallback[] permissionCallbackArr = new PermissionCallback[strArr.length];
        for (int i = 0; i < strArr.length; i++) {
            permissionCallbackArr[i] = permissionCallback;
        }
        requestAndroidPermission(activity, strArr, permissionCallbackArr);
    }

    public static void requestAndroidPermission(final Activity activity, final String[] strArr, final PermissionCallback[] permissionCallbackArr) {
        activity.runOnUiThread(new Runnable() { // from class: com.sq.tools.utils.-$$Lambda$PermissionUtils$QvR1RIAmLEIkZ6ycMKVJFKUgGeE
            @Override // java.lang.Runnable
            public final void run() {
                PermissionUtils.requestPermissions(activity, strArr, permissionCallbackArr);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void requestPermissions(Activity activity, String[] strArr, PermissionCallback[] permissionCallbackArr) {
        if (permissionCallbackArr.length != strArr.length) {
            Logger.error("Something went wrong in Permission utils, the length of permissions is not same as callbacks, do nothing now", new Object[0]);
            return;
        }
        if (SoftwareUtils.getAndroidVersion() < 23) {
            Logger.info("Request Permission on Device with Android version under M, grant permission without request", new Object[0]);
            onResult(permissionCallbackArr, true, strArr);
            return;
        }
        for (int i = 0; i < strArr.length; i++) {
            if (!TextUtils.isEmpty(strArr[i])) {
                if (hasAndroidPermission(activity.getApplicationContext(), strArr[i])) {
                    onResult(permissionCallbackArr[i], true, strArr[i]);
                } else if (!isPermissionDefine(activity, strArr[i])) {
                    onResult(permissionCallbackArr[i], false, strArr[i]);
                    Logger.error("please define permission %s in AndroidManifest before request, will not process to request permission", strArr[i]);
                } else {
                    permissionsList.add(new PermissionBundle(strArr[i], permissionsList.size() + 100, permissionCallbackArr[i]));
                }
            }
        }
        requestSinglePermission(activity);
    }

    private static void requestSinglePermission(Activity activity) {
        if (permissionsList.size() == 0) {
            return;
        }
        PermissionBundle permissionBundle = permissionsList.get(0);
        if (hasAndroidPermission(activity.getApplicationContext(), permissionBundle.permission)) {
            onResult(permissionBundle.callback, true, permissionBundle.permission);
            permissionsList.remove(permissionBundle);
        } else {
            if (isRequesting) {
                return;
            }
            isRequesting = true;
            Logger.debug("Start request permission %s, request code: %d", permissionBundle.permission, Integer.valueOf(permissionBundle.requestCode));
            Intent intent = new Intent(activity, (Class<?>) PermissionActivity.class);
            intent.putExtra("permission_request", permissionBundle.permission);
            intent.putExtra("permission_code", permissionBundle.requestCode);
            activity.startActivity(intent);
        }
    }

    protected static void onRequestPermissionsResult(Activity activity, int i, String[] strArr, int[] iArr) {
        if (permissionsList.size() == 0) {
            return;
        }
        Logger.debug("PermissionUtil onRequestPermissionsResult, code: %d, permissions: %s, grant result: %s", Integer.valueOf(i), Arrays.toString(strArr), Arrays.toString(iArr));
        for (int i2 = 0; i2 < permissionsList.size(); i2++) {
            PermissionBundle permissionBundle = permissionsList.get(i2);
            if (permissionBundle.requestCode == i) {
                permissionsList.remove(permissionBundle);
                onResult(permissionBundle.callback, iArr.length > 0 && iArr[0] == 0, permissionBundle.permission);
            }
        }
        isRequesting = false;
        if (permissionsList.isEmpty()) {
            return;
        }
        requestSinglePermission(activity);
    }

    private static void onResult(PermissionCallback permissionCallback, boolean z, String str) {
        onResult(new PermissionCallback[]{permissionCallback}, z, new String[]{str});
    }

    private static void onResult(PermissionCallback[] permissionCallbackArr, boolean z, String[] strArr) {
        for (int i = 0; i < permissionCallbackArr.length; i++) {
            if (permissionCallbackArr[i] != null) {
                if (z) {
                    permissionCallbackArr[i].onGranted(strArr[i]);
                } else {
                    permissionCallbackArr[i].onDenied(strArr[i]);
                }
            }
        }
    }

    static class PermissionBundle {
        PermissionCallback callback;
        String permission;
        int requestCode;

        PermissionBundle(String str, int i, PermissionCallback permissionCallback) {
            this.permission = str;
            this.requestCode = i;
            this.callback = permissionCallback;
        }
    }
}
