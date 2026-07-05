package com.ss.android.downloadlib.addownload.compliance;

import com.ss.android.socialbase.downloader.utils.LruCache;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes.dex */
public class c extends LruCache<Long, com.ss.android.downloadlib.addownload.b.b> {

    private static class a {
        private static c a = new c();
    }

    public static c a() {
        return a.a;
    }

    private c() {
        super(16, 16);
    }

    public void a(com.ss.android.downloadlib.addownload.b.b bVar) {
        if (bVar == null) {
            return;
        }
        put(Long.valueOf(bVar.a()), bVar);
    }

    public com.ss.android.downloadlib.addownload.b.b a(long j, long j2) {
        return (com.ss.android.downloadlib.addownload.b.b) get(get(Long.valueOf(j)) != null ? Long.valueOf(j) : Long.valueOf(j2));
    }

    public com.ss.android.downloadlib.addownload.b.b a(long j) {
        return (com.ss.android.downloadlib.addownload.b.b) get(Long.valueOf(j));
    }
}
