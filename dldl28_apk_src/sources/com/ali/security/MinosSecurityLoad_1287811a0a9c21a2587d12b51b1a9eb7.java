package com.ali.security;

import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes2.dex */
public class MinosSecurityLoad_1287811a0a9c21a2587d12b51b1a9eb7 {
    private static AtomicBoolean isLoadLibrary = new AtomicBoolean(false);

    public static void SLoad(String str) {
        if (isLoadLibrary.compareAndSet(false, true)) {
            System.loadLibrary(str);
            isLoadLibrary.set(false);
        }
    }
}
