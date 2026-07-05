package com.taptap.sdk.gid.service.memory;

import com.taptap.sdk.gid.service.GidService;
import java.util.concurrent.atomic.AtomicReference;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: GidMemoryCacheService.kt */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R$\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u00048V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00040\u000bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/taptap/sdk/gid/service/memory/GidMemoryCacheService;", "Lcom/taptap/sdk/gid/service/GidService;", "()V", "value", "", "gid", "getGid", "()Ljava/lang/String;", "setGid", "(Ljava/lang/String;)V", "gidRef", "Ljava/util/concurrent/atomic/AtomicReference;", "tap-gid_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final class GidMemoryCacheService implements GidService {
    private final AtomicReference<String> gidRef = new AtomicReference<>("");

    @Override // com.taptap.sdk.gid.service.GidService
    public String getGid() {
        String str = this.gidRef.get();
        Intrinsics.checkNotNullExpressionValue(str, "gidRef.get()");
        return str;
    }

    @Override // com.taptap.sdk.gid.service.GidService
    public void setGid(String value) {
        Intrinsics.checkNotNullParameter(value, "value");
        this.gidRef.set(value);
    }
}
