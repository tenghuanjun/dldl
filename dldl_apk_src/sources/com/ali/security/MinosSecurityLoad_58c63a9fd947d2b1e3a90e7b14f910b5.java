package com.ali.security;

import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes6.dex */
public class MinosSecurityLoad_58c63a9fd947d2b1e3a90e7b14f910b5 {
    private static AtomicBoolean isLoadLibrary = new AtomicBoolean(false);

    public static void SLoad(String str) {
        if (isLoadLibrary.compareAndSet(false, true)) {
            System.loadLibrary(str);
            isLoadLibrary.set(false);
        }
    }
}
