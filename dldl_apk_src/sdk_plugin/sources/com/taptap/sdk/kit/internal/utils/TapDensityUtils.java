package com.taptap.sdk.kit.internal.utils;

import android.content.res.Resources;
import android.util.TypedValue;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;

/* JADX INFO: compiled from: TapDensityUtils.kt */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\b\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004H\u0007J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0007"}, d2 = {"Lcom/taptap/sdk/kit/internal/utils/TapDensityUtils;", "", "()V", "dpToPx", "", "dp", "", "tap-common_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final class TapDensityUtils {
    public static final TapDensityUtils INSTANCE = new TapDensityUtils();

    private TapDensityUtils() {
    }

    @JvmStatic
    public static final float dpToPx(int dp) {
        return TypedValue.applyDimension(1, dp, Resources.getSystem().getDisplayMetrics());
    }

    @JvmStatic
    public static final float dpToPx(float dp) {
        return TypedValue.applyDimension(1, dp, Resources.getSystem().getDisplayMetrics());
    }
}
