package com.sq.webview.permission;

import java.util.List;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public interface OnPermissionCallback {

    /* JADX INFO: renamed from: com.sq.webview.permission.OnPermissionCallback$-CC, reason: invalid class name */
    public final /* synthetic */ class CC {
        public static void $default$onDenied(OnPermissionCallback onPermissionCallback, List list, boolean z) {
        }
    }

    void onDenied(List<String> permissions, boolean doNotAskAgain);

    void onGranted(List<String> permissions, boolean allGranted);
}
