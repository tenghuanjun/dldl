package com.bun.miitmdid.interfaces;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public interface IIdProvider extends IdSupplier {
    void doStart();

    void doStartInThreadPool(IIdentifierListener iIdentifierListener);

    void doStartSync(IIdentifierListener iIdentifierListener);

    boolean isSync();

    void setGetIdFlag(boolean z, boolean z2, boolean z3);

    void shutDown();
}
