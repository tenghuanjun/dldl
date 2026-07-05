package com.taptap.sdk.gid.service.disk.decorator;

import android.util.Log;
import com.taptap.sdk.gid.service.GidService;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: GidMigrationDecorator.kt */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0007\u0018\u0000 \u000f2\u00020\u0001:\u0001\u000fB\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R$\u0010\n\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\t8V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u000e\u0010\u0004\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcom/taptap/sdk/gid/service/disk/decorator/GidMigrationDecorator;", "Lcom/taptap/sdk/gid/service/disk/decorator/GidDiskServiceDecorator;", "newStorageService", "Lcom/taptap/sdk/gid/service/GidService;", "legacyStorageService", "clearLegacyAfterMigration", "", "(Lcom/taptap/sdk/gid/service/GidService;Lcom/taptap/sdk/gid/service/GidService;Z)V", "value", "", "gid", "getGid", "()Ljava/lang/String;", "setGid", "(Ljava/lang/String;)V", "Companion", "tap-gid_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final class GidMigrationDecorator extends GidDiskServiceDecorator {
    private static final String TAG = "GidMigration";
    private final boolean clearLegacyAfterMigration;
    private final GidService legacyStorageService;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public GidMigrationDecorator(GidService newStorageService, GidService legacyStorageService, boolean z) {
        super(newStorageService);
        Intrinsics.checkNotNullParameter(newStorageService, "newStorageService");
        Intrinsics.checkNotNullParameter(legacyStorageService, "legacyStorageService");
        this.legacyStorageService = legacyStorageService;
        this.clearLegacyAfterMigration = z;
    }

    @Override // com.taptap.sdk.gid.service.disk.decorator.GidDiskServiceDecorator, com.taptap.sdk.gid.service.GidService
    public String getGid() {
        String gid = getWrapped().getGid();
        if (gid.length() == 0) {
            String gid2 = this.legacyStorageService.getGid();
            if (gid2.length() > 0) {
                Log.d(TAG, "Migrating GID from legacy storage");
                getWrapped().setGid(gid2);
                if (this.clearLegacyAfterMigration) {
                    Log.d(TAG, "Clearing legacy storage after migration");
                    this.legacyStorageService.setGid("");
                }
                return gid2;
            }
        }
        return gid;
    }

    @Override // com.taptap.sdk.gid.service.disk.decorator.GidDiskServiceDecorator, com.taptap.sdk.gid.service.GidService
    public void setGid(String value) {
        Intrinsics.checkNotNullParameter(value, "value");
        getWrapped().setGid(value);
    }
}
