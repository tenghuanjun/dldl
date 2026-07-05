package com.ss.android.downloadlib.g;

import android.os.AsyncTask;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes.dex */
public class b {
    static final a a = new C0099b();

    private static class a {
        private a() {
        }

        public <T> void a(AsyncTask<T, ?, ?> asyncTask, T... tArr) {
            try {
                asyncTask.execute(tArr);
            } catch (Throwable unused) {
            }
        }
    }

    /* JADX INFO: renamed from: com.ss.android.downloadlib.g.b$b, reason: collision with other inner class name */
    private static class C0099b extends a {
        private C0099b() {
            super();
        }

        @Override // com.ss.android.downloadlib.g.b.a
        public <T> void a(AsyncTask<T, ?, ?> asyncTask, T... tArr) {
            try {
                asyncTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, tArr);
            } catch (Throwable unused) {
            }
        }
    }

    public static <T> void a(AsyncTask<T, ?, ?> asyncTask, T... tArr) {
        a.a(asyncTask, tArr);
    }
}
