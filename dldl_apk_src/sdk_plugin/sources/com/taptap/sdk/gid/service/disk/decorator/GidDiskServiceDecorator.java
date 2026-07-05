package com.taptap.sdk.gid.service.disk.decorator;

import com.taptap.sdk.gid.service.GidService;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: GidDiskServiceDecorator.kt */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\b\b&\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0001¢\u0006\u0002\u0010\u0003R$\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u00058V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u0014\u0010\u0002\u001a\u00020\u0001X\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\r"}, d2 = {"Lcom/taptap/sdk/gid/service/disk/decorator/GidDiskServiceDecorator;", "Lcom/taptap/sdk/gid/service/GidService;", "wrapped", "(Lcom/taptap/sdk/gid/service/GidService;)V", "value", "", "gid", "getGid", "()Ljava/lang/String;", "setGid", "(Ljava/lang/String;)V", "getWrapped", "()Lcom/taptap/sdk/gid/service/GidService;", "tap-gid_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
public abstract class GidDiskServiceDecorator implements GidService {
    private final GidService wrapped;

    public GidDiskServiceDecorator(GidService wrapped) {
        Intrinsics.checkNotNullParameter(wrapped, "wrapped");
        this.wrapped = wrapped;
    }

    protected final GidService getWrapped() {
        return this.wrapped;
    }

    @Override // com.taptap.sdk.gid.service.GidService
    public String getGid() {
        return this.wrapped.getGid();
    }

    @Override // com.taptap.sdk.gid.service.GidService
    public void setGid(String value) {
        Intrinsics.checkNotNullParameter(value, "value");
        this.wrapped.setGid(value);
    }
}
