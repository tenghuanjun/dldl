package com.sqwan.common.util;

import android.app.Activity;
import com.sqwan.common.util.PermissionHelper;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class PermissionSimpleHelper {
    public static final String[] STORAGE_PERMISSION = {"android.permission.WRITE_EXTERNAL_STORAGE"};
    public static final String STORAGE_PERMISSION_BY_SHARE = "申请存储权限：用于保存图片到本地，以实现图片分享功能";
    public static final String STORAGE_PERMISSION_BY_WEB = "申请存储权限：用于保存图片到本地，以实现网页保存图片功能";
    public static final String STORAGE_PERMISSION_NAME = "存储权限";

    public interface OnPermissionCallback {
        void onDenied();

        void onGranted();
    }

    public static void requestPermission(String[] strArr, String str, String str2, final OnPermissionCallback onPermissionCallback) {
        Activity resumedActivity = ActivityLifeCycleUtils.getInstance().getResumedActivity();
        if (resumedActivity == null) {
            resumedActivity = null;
        }
        if (resumedActivity == null) {
            resumedActivity = SQContextWrapper.getActivity();
        }
        Activity activity = resumedActivity;
        if (activity == null) {
            if (onPermissionCallback == null) {
                return;
            }
            onPermissionCallback.onDenied();
            return;
        }
        PermissionHelper permissionHelper = PermissionHelper.getInstance();
        String[] strArr2 = {"权限说明\n" + str2};
        if (!permissionHelper.checkPermissions(strArr)) {
            permissionHelper.requestPermissions(activity, strArr, strArr2, 1110, new PermissionHelper.PermissionCallback() { // from class: com.sqwan.common.util.-$$Lambda$PermissionSimpleHelper$wzVpphpYj5cj8egbwJzFWvjiwCA
                @Override // com.sqwan.common.util.PermissionHelper.PermissionCallback
                public final void onRequestPermissionsResult(String[] strArr3, int[] iArr) {
                    PermissionSimpleHelper.lambda$requestPermission$0(onPermissionCallback, strArr3, iArr);
                }
            });
        } else {
            if (onPermissionCallback == null) {
                return;
            }
            onPermissionCallback.onGranted();
        }
    }

    static /* synthetic */ void lambda$requestPermission$0(OnPermissionCallback onPermissionCallback, String[] strArr, int[] iArr) {
        int length = iArr.length;
        boolean z = false;
        int i = 0;
        while (true) {
            if (i >= length) {
                z = true;
                break;
            } else if (iArr[i] != 0) {
                break;
            } else {
                i++;
            }
        }
        if (z) {
            if (onPermissionCallback != null) {
                onPermissionCallback.onGranted();
            }
        } else if (onPermissionCallback != null) {
            onPermissionCallback.onDenied();
        }
    }
}
