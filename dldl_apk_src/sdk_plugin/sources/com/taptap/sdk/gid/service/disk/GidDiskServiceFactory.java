package com.taptap.sdk.gid.service.disk;

import android.content.Context;
import android.content.SharedPreferences;
import com.taptap.sdk.gid.service.GidService;
import com.taptap.sdk.gid.service.disk.cn.TapCNGidDiskService;
import com.taptap.sdk.gid.service.disk.decorator.CompatGidDiskService;
import com.taptap.sdk.gid.service.disk.intl.TapINTLGidDiskService;
import com.taptap.sdk.gid.service.disk.legacy.LegacyTapGidDiskService;
import com.taptap.sdk.initializer.api.model.RegionType;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: GidDiskServiceFactory.kt */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n¨\u0006\u000b"}, d2 = {"Lcom/taptap/sdk/gid/service/disk/GidDiskServiceFactory;", "", "()V", "create", "Lcom/taptap/sdk/gid/service/GidService;", "context", "Landroid/content/Context;", "region", "Lcom/taptap/sdk/initializer/api/model/RegionType;", "useMigration", "", "tap-gid_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final class GidDiskServiceFactory {
    public static final GidDiskServiceFactory INSTANCE = new GidDiskServiceFactory();

    /* JADX INFO: compiled from: GidDiskServiceFactory.kt */
    @Metadata(k = 3, mv = {1, 5, 1}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[RegionType.values().length];
            try {
                iArr[RegionType.CN.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[RegionType.GLOBAL.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    private GidDiskServiceFactory() {
    }

    public final GidService create(Context context, RegionType region, boolean useMigration) {
        TapCNGidDiskService tapCNGidDiskService;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(region, "region");
        int i = WhenMappings.$EnumSwitchMapping$0[region.ordinal()];
        if (i == 1) {
            SharedPreferences sharedPreferences = context.getSharedPreferences("tap_sdk_sp", 0);
            Intrinsics.checkNotNullExpressionValue(sharedPreferences, "context.getSharedPrefere…ATE\n                    )");
            tapCNGidDiskService = new TapCNGidDiskService(sharedPreferences);
        } else {
            if (i != 2) {
                throw new NoWhenBranchMatchedException();
            }
            SharedPreferences sharedPreferences2 = context.getSharedPreferences("tap_sdk_sp", 0);
            Intrinsics.checkNotNullExpressionValue(sharedPreferences2, "context.getSharedPrefere…ATE\n                    )");
            tapCNGidDiskService = new TapINTLGidDiskService(sharedPreferences2);
        }
        if (!useMigration) {
            return tapCNGidDiskService;
        }
        SharedPreferences sharedPreferences3 = context.getSharedPreferences("tap_gid_share_preference", 0);
        Intrinsics.checkNotNullExpressionValue(sharedPreferences3, "context.getSharedPrefere…ODE_PRIVATE\n            )");
        return new CompatGidDiskService(tapCNGidDiskService, new LegacyTapGidDiskService(sharedPreferences3));
    }
}
