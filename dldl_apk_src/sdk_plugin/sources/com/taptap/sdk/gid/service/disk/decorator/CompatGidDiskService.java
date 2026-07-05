package com.taptap.sdk.gid.service.disk.decorator;

import android.util.Log;
import com.taptap.sdk.gid.service.GidService;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CompatGidDiskService.kt */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0007\u0018\u0000 \r2\u00020\u0001:\u0001\rB\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005R$\u0010\b\u001a\u00020\u00072\u0006\u0010\u0006\u001a\u00020\u00078V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\f¨\u0006\u000e"}, d2 = {"Lcom/taptap/sdk/gid/service/disk/decorator/CompatGidDiskService;", "Lcom/taptap/sdk/gid/service/disk/decorator/GidDiskServiceDecorator;", "newStorageService", "Lcom/taptap/sdk/gid/service/GidService;", "legacyStorageService", "(Lcom/taptap/sdk/gid/service/GidService;Lcom/taptap/sdk/gid/service/GidService;)V", "value", "", "gid", "getGid", "()Ljava/lang/String;", "setGid", "(Ljava/lang/String;)V", "Companion", "tap-gid_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final class CompatGidDiskService extends GidDiskServiceDecorator {
    private static final String TAG = "CompatGidDiskService";

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CompatGidDiskService(GidService newStorageService, GidService legacyStorageService) {
        super(new GidMigrationDecorator(newStorageService, legacyStorageService, true));
        Intrinsics.checkNotNullParameter(newStorageService, "newStorageService");
        Intrinsics.checkNotNullParameter(legacyStorageService, "legacyStorageService");
    }

    @Override // com.taptap.sdk.gid.service.disk.decorator.GidDiskServiceDecorator, com.taptap.sdk.gid.service.GidService
    public String getGid() {
        String gid = super.getGid();
        if (gid.length() > 0) {
            Log.d(TAG, "Successfully retrieved GID");
        }
        return gid;
    }

    @Override // com.taptap.sdk.gid.service.disk.decorator.GidDiskServiceDecorator, com.taptap.sdk.gid.service.GidService
    public void setGid(String value) {
        Intrinsics.checkNotNullParameter(value, "value");
        Log.d(TAG, "Saving GID to storage");
        super.setGid(value);
    }
}
