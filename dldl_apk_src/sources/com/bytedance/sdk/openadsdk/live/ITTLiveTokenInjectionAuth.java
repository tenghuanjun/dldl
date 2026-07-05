package com.bytedance.sdk.openadsdk.live;

import android.app.Activity;
import java.io.Serializable;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public interface ITTLiveTokenInjectionAuth extends Serializable {
    TTLiveToken getTokenInfo();

    boolean isLogin();

    void onTokenInvalid(TTLiveToken tTLiveToken, TTLiveAuthCallback tTLiveAuthCallback, Activity activity, Map<String, String> map);
}
