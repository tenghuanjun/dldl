package com.huya.mtp.hyns.rx;

import com.huya.mtp.hyns.NSResponse;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class Result<T> {
    private final Throwable error;
    private final NSResponse<T> response;

    public static <T> Result<T> error(Throwable th) {
        if (th == null) {
            throw new NullPointerException("error == null");
        }
        return new Result<>(null, th);
    }

    public static <T> Result<T> response(NSResponse<T> nSResponse) {
        if (nSResponse == null) {
            throw new NullPointerException("response == null");
        }
        return new Result<>(nSResponse, null);
    }

    private Result(NSResponse<T> nSResponse, Throwable th) {
        this.response = nSResponse;
        this.error = th;
    }

    public NSResponse<T> response() {
        return this.response;
    }

    public Throwable error() {
        return this.error;
    }

    public boolean isError() {
        return this.error != null;
    }
}
