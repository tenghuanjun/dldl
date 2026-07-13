package com.hjq.permissions;

/* JADX INFO: loaded from: classes3.dex */
public interface OnPermissionPageCallback {

    /* JADX INFO: renamed from: com.hjq.permissions.OnPermissionPageCallback$-CC, reason: invalid class name */
    public final /* synthetic */ class CC {
        public static void $default$onDenied(OnPermissionPageCallback _this) {
        }
    }

    void onDenied();

    void onGranted();
}
