package com.danikula.videocache;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public class ProxyCacheException extends Exception {
    public ProxyCacheException(String str) {
        super(str + ". Version: 8.1.2");
    }

    public ProxyCacheException(String str, Throwable th) {
        super(str + ". Version: 8.1.2", th);
    }

    public ProxyCacheException(Throwable th) {
        super("No explanation error. Version: 8.1.2", th);
    }
}
