package com.taptap.sdk.kit.internal.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: TapSignKit.kt */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"Lcom/taptap/sdk/kit/internal/utils/TapSignKit;", "", "()V", "getSign", "", "context", "Landroid/content/Context;", "tap-common_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final class TapSignKit {
    public static final TapSignKit INSTANCE = new TapSignKit();

    private TapSignKit() {
    }

    public final String getSign(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        try {
            PackageManager packageManager = context.getPackageManager();
            Intrinsics.checkNotNullExpressionValue(packageManager, "context.packageManager");
            String packageName = context.getApplicationContext().getPackageName();
            Intrinsics.checkNotNullExpressionValue(packageName, "context.applicationContext.packageName");
            PackageInfo packageInfo = packageManager.getPackageInfo(packageName, 64);
            Intrinsics.checkNotNullExpressionValue(packageInfo, "pm.getPackageInfo(packag…geManager.GET_SIGNATURES)");
            return CommonUtils.getMD5(packageInfo.signatures[0].toByteArray());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
