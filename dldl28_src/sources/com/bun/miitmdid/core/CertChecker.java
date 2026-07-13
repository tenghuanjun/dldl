package com.bun.miitmdid.core;

import android.content.Context;

/* JADX INFO: loaded from: classes2.dex */
public class CertChecker {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final CertChecker f149a = new CertChecker();

    static {
        try {
            System.loadLibrary("msaoaidauth");
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }

    public static native CertChecker a();

    public native boolean verifyCert(Context context, String str);
}
