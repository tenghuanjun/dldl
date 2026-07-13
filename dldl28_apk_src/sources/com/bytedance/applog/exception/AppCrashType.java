package com.bytedance.applog.exception;

/* JADX INFO: loaded from: classes2.dex */
public class AppCrashType {
    public static final int ALL = Integer.MAX_VALUE;
    public static final int JAVA = 1;

    public static boolean hasCrashType(int i, int i2) {
        return (i & i2) != 0;
    }

    public static boolean hasJavaCrashType(int i) {
        return hasCrashType(i, 1);
    }
}
