package com.ss.android.downloadlib.e;

import android.text.TextUtils;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes.dex */
public class b {

    public interface a<T> {
        T b();
    }

    public static <T> T a(boolean z, String str, a<T> aVar) {
        try {
            return aVar.b();
        } catch (Throwable th) {
            if (th instanceof com.ss.android.downloadlib.e.a) {
                throw th;
            }
            c.a().a(z, th, str);
            if (TextUtils.isEmpty(str)) {
                throw th;
            }
            return null;
        }
    }

    public static <T> T a(a<T> aVar) {
        return (T) a(true, null, aVar);
    }

    public static void a(final Runnable runnable) {
        a(new a<Void>() { // from class: com.ss.android.downloadlib.e.b.1
            @Override // com.ss.android.downloadlib.e.b.a
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public Void b() {
                runnable.run();
                return null;
            }
        });
    }
}
