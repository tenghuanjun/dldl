package com.google.sqgson.internal;

/* JADX INFO: renamed from: com.google.sqgson.internal.$Gson$Preconditions, reason: invalid class name */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public final class C$Gson$Preconditions {
    private C$Gson$Preconditions() {
        throw new UnsupportedOperationException();
    }

    public static <T> T checkNotNull(T t) {
        if (t != null) {
            return t;
        }
        throw null;
    }

    public static void checkArgument(boolean z) {
        if (!z) {
            throw new IllegalArgumentException();
        }
    }
}
