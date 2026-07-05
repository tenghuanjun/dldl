package com.taptap.sdk.login;

import kotlin.Metadata;

/* JADX INFO: compiled from: TapTapLoginCallback.kt */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\u0006"}, d2 = {"Lcom/taptap/sdk/login/TapTapLoginCallback;", "", "onLoginStatusChange", "", "status", "", "tap-login-api_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
public interface TapTapLoginCallback {

    /* JADX INFO: compiled from: TapTapLoginCallback.kt */
    @Metadata(k = 3, mv = {1, 5, 1}, xi = 48)
    public static final class DefaultImpls {
        public static void onLoginStatusChange(TapTapLoginCallback tapTapLoginCallback, int i) {
        }
    }

    void onLoginStatusChange(int status);
}
