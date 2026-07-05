package com.nirvana.tools.jsoner;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes6.dex */
public final class JsonPreconditions {
    private JsonPreconditions() {
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
