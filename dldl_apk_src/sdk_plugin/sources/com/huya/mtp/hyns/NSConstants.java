package com.huya.mtp.hyns;

import com.huya.mtp.api.MTPApi;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class NSConstants {
    public static final String NSTAG = "NetService";
    private static boolean isDebug = true;

    public static boolean isApkInDebug() {
        boolean z = isDebug;
        if (z) {
            z = false;
            try {
                boolean z2 = (MTPApi.CONTEXT.getApplication().getApplicationInfo().flags & 2) != 0;
                isDebug = z2;
                return z2;
            } catch (Exception unused) {
                isDebug = false;
            }
        }
        return z;
    }
}
