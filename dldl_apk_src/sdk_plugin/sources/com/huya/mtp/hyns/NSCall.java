package com.huya.mtp.hyns;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public interface NSCall<T> extends Cloneable {
    void cancel();

    NSCall<T> clone();

    void enqueue();

    void enqueue(NSCallback<T> nSCallback);

    void enqueue(NSCallback<T> nSCallback, NSSettings nSSettings);

    NSResponse<T> execute() throws NSException;

    NSResponse<T> execute(NSSettings nSSettings) throws NSException;

    boolean isCanceled();
}
