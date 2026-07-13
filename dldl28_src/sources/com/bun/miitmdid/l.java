package com.bun.miitmdid;

import android.content.Context;
import com.android.msasdk.FreemeIdsSupplier;
import com.android.msasdk.IConnect;

/* JADX INFO: loaded from: classes2.dex */
public class l extends m implements IConnect {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Context f161a;
    public String b;
    public FreemeIdsSupplier c;

    public l(Context context) {
        this.f161a = context;
    }

    @Override // com.android.msasdk.IConnect
    public native void connectSuccess(boolean z);

    @Override // com.bun.miitmdid.interfaces.IIdProvider
    public native void doStart();

    @Override // com.bun.miitmdid.interfaces.IIdProvider
    public native void shutDown();
}
