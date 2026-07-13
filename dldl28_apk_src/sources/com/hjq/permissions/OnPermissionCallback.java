package com.hjq.permissions;

import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public interface OnPermissionCallback {

    /* JADX INFO: renamed from: com.hjq.permissions.OnPermissionCallback$-CC, reason: invalid class name */
    public final /* synthetic */ class CC {
        public static void $default$onDenied(OnPermissionCallback _this, List list, boolean z) {
        }
    }

    void onDenied(List<String> list, boolean z);

    void onGranted(List<String> list, boolean z);
}
