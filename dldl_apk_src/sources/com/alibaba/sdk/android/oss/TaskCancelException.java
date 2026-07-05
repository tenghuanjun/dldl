package com.alibaba.sdk.android.oss;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public class TaskCancelException extends Exception {
    public TaskCancelException() {
    }

    public TaskCancelException(String str) {
        super("[ErrorMessage]: " + str);
    }

    public TaskCancelException(Throwable th) {
        super(th);
    }
}
