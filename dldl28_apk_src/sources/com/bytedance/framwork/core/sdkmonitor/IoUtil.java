package com.bytedance.framwork.core.sdkmonitor;

import java.io.Closeable;

/* JADX INFO: loaded from: classes2.dex */
public class IoUtil {
    public static void close(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Throwable unused) {
            }
        }
    }
}
