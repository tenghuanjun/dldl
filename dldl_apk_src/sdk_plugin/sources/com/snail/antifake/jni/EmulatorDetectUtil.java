package com.snail.antifake.jni;

import android.content.Context;
import com.snail.antifake.deviceid.AndroidDeviceIMEIUtil;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class EmulatorDetectUtil {
    public static native boolean detect();

    public void throwNativeCrash() {
    }

    static {
        System.loadLibrary("emulator_check");
    }

    public static boolean isEmulator(Context context) {
        return AndroidDeviceIMEIUtil.isRunOnEmulator(context) || detect();
    }

    public static boolean isEmulator() {
        return detect();
    }
}
