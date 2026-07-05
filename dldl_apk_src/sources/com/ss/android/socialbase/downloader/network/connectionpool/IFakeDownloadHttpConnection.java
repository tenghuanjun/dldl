package com.ss.android.socialbase.downloader.network.connectionpool;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes.dex */
public interface IFakeDownloadHttpConnection {
    void execute() throws Exception;

    boolean isRequesting();

    boolean isSuccessful();

    boolean isValid();

    void joinExecute() throws InterruptedException;
}
