package com.huya.mtp.utils.pack;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class UnpackException extends RuntimeException {
    private static final long serialVersionUID = -4218633413237051053L;

    public UnpackException() {
    }

    public UnpackException(String str, Throwable th) {
        super(str, th);
    }

    public UnpackException(String str) {
        super(str);
    }

    public UnpackException(Throwable th) {
        super(th);
    }
}
