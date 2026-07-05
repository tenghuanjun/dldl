package com.alipay.deviceid.module.x;

import android.content.Context;
import java2jni_do_not_delete_this.java2jni_do_not_delete_this_library_deviceid_1607;
import org.json.JSONArray;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public class zz {
    public static int CHECK_ALL;
    public static int CHECK_DEBUG;
    public static int CHECK_HOOK;
    public static int CHECK_VIRTUAL;
    public static int MODE_DETAIL;
    public static int MODE_SIMPLE;
    private static volatile zz mInstance;

    static {
        java2jni_do_not_delete_this_library_deviceid_1607.loadLibrary();
        CHECK_HOOK = 1;
        CHECK_DEBUG = 2;
        CHECK_VIRTUAL = 4;
        CHECK_ALL = -1;
        MODE_SIMPLE = 0;
        MODE_DETAIL = 1;
        mInstance = null;
    }

    private zz() {
    }

    public static native boolean cy1(Context context);

    public static native boolean cy2(Context context);

    public static native boolean cy3(Context context);

    public static native zz getInstance();

    public static native JSONArray methodToNative();

    public static native boolean scanPackage(Context context, String str);

    public static native String vir1(Context context);

    public static native boolean xp1(Context context);

    public static native boolean xp2(Context context);

    public static native String xp3(Context context);

    public static native String xp4(Context context);
}
