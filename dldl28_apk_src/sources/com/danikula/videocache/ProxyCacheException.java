package com.danikula.videocache;

import shuyu.com.androidvideocache.BuildConfig;

/* JADX INFO: loaded from: classes3.dex */
public class ProxyCacheException extends Exception {
    public ProxyCacheException(String str) {
        super(str + BuildConfig.LIBRARY_VERSION);
    }

    public ProxyCacheException(String str, Throwable th) {
        super(str + BuildConfig.LIBRARY_VERSION, th);
    }

    public ProxyCacheException(Throwable th) {
        super("No explanation error. Version: 8.1.2", th);
    }
}
