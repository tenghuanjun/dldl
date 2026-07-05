package com.taptap.sdk.login;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: TapTapAccountInternal.kt */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0002¨\u0006\u0003"}, d2 = {"toTapAccount", "Lcom/taptap/sdk/login/TapTapAccount;", "Lcom/taptap/sdk/login/TapTapAccountInternal;", "tap-login-api_release"}, k = 2, mv = {1, 5, 1}, xi = 48)
public final class TapTapAccountInternalKt {
    public static final TapTapAccount toTapAccount(TapTapAccountInternal tapTapAccountInternal) {
        Intrinsics.checkNotNullParameter(tapTapAccountInternal, "<this>");
        return new TapTapAccount(tapTapAccountInternal.getAccessToken(), tapTapAccountInternal.getOpenId(), tapTapAccountInternal.getUnionId(), tapTapAccountInternal.getName(), tapTapAccountInternal.getAvatar(), tapTapAccountInternal.getEmail());
    }
}
