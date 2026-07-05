package com.alicom.tools.serialization;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes2.dex */
public final class JSONPreconditions {
    private JSONPreconditions() {
        throw new UnsupportedOperationException();
    }

    public static void checkArgument(boolean z) {
        if (!z) {
            throw new IllegalArgumentException();
        }
    }

    public static <T> T checkNotNull(T t) {
        if (t != null) {
            return t;
        }
        throw null;
    }
}
