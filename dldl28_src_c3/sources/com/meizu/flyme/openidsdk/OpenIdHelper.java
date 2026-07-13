package com.meizu.flyme.openidsdk;

import android.content.Context;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public class OpenIdHelper {
    private static final String TAG = "OpenIdHelper";
    private static Method sContextMethod;

    public static native String getAAID(Context context);

    public static native String getOAID(Context context);

    public static native String getUDID(Context context);

    public static native String getVAID(Context context);

    public static final native boolean isSupported();

    public static native void setLogEnable(boolean z);
}
