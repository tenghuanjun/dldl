package com.taptap.sdk.review.extensions;

import com.taptap.sdk.kit.internal.TapTapKit;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;

/* JADX INFO: compiled from: SystemExt.kt */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\u001a\b\u0010\u0002\u001a\u00020\u0003H\u0000\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000¨\u0006\u0004"}, d2 = {"PACKAGE_NAME_CN", "", "isTapInstalled", "", "tap-review_release"}, k = 2, mv = {1, 5, 1}, xi = 48)
public final class SystemExtKt {
    public static final String PACKAGE_NAME_CN = "com.taptap";

    public static final boolean isTapInstalled() {
        try {
            Result.Companion companion = Result.INSTANCE;
            return TapTapKit.INSTANCE.getContext().getPackageManager().getPackageInfo(PACKAGE_NAME_CN, 0) != null;
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            Result.m52constructorimpl(ResultKt.createFailure(th));
            return false;
        }
    }
}
