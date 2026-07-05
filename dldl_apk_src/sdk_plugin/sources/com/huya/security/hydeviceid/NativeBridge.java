package com.huya.security.hydeviceid;

import android.content.Context;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class NativeBridge {
    public static boolean nativeModuleLoaded;

    public static native void afterUpload(byte[] bArr);

    public static native String getCDIDNative();

    public static native Context getContext();

    public static native byte[] getLinkBody(String str, String str2, String str3, String str4);

    public static native String getSDIDNative();

    public static native byte[] getUploadBody();

    public static native void setCollectInfo(String str);

    public static native void setOaid(String str);

    public static native void writeLog(int i, String str, int i2, String str2);

    static {
        try {
            System.loadLibrary("hydeviceid");
            nativeModuleLoaded = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
