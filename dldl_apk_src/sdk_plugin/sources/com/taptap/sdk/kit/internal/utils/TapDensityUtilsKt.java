package com.taptap.sdk.kit.internal.utils;

import kotlin.Metadata;

/* JADX INFO: compiled from: TapDensityUtils.kt */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\b\n\u0002\u0010\u0007\n\u0002\b\b\"\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004\"\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00018F¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0005\"\u0015\u0010\u0006\u001a\u00020\u0002*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0007\u0010\b\"\u0015\u0010\u0006\u001a\u00020\u0002*\u00020\u00018F¢\u0006\u0006\u001a\u0004\b\u0007\u0010\t¨\u0006\n"}, d2 = {"dip", "", "", "getDip", "(F)I", "(I)I", "dp", "getDp", "(F)F", "(I)F", "tap-common_release"}, k = 2, mv = {1, 5, 1}, xi = 48)
public final class TapDensityUtilsKt {
    public static final int getDip(int i) {
        return (int) getDp(i);
    }

    public static final float getDp(int i) {
        return TapDensityUtils.dpToPx(i);
    }

    public static final int getDip(float f) {
        return (int) getDp(f);
    }

    public static final float getDp(float f) {
        return TapDensityUtils.dpToPx(f);
    }
}
