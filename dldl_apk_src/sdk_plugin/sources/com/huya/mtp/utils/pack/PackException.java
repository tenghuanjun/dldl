package com.huya.mtp.utils.pack;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class PackException extends RuntimeException {
    private static final long serialVersionUID = -4218633413237051053L;

    public PackException() {
    }

    public PackException(String str, Throwable th) {
        super(str, th);
    }

    public PackException(String str) {
        super(str);
    }

    public PackException(Throwable th) {
        super(th);
    }
}
