package com.huya.mtp.hyns;

import com.huya.mtp.data.exception.DataException;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class NSException extends DataException {
    private boolean mFromCache;

    public NSException() {
    }

    public NSException(String str) {
        super(str);
    }

    public NSException(String str, Throwable th) {
        super(str, th);
    }

    public NSException(Throwable th) {
        super(th);
    }

    public NSException(Throwable th, boolean z) {
        super(th);
        this.mFromCache = z;
    }

    void setFromCache(boolean z) {
        this.mFromCache = z;
    }

    public boolean isFromCache() {
        return this.mFromCache;
    }
}
