package com.bytedance.sdk.openadsdk;

import android.content.Context;
import android.os.Bundle;
import android.os.Looper;
import android.text.TextUtils;
import com.bykv.vk.openvk.api.proto.ValueSet;
import com.bytedance.sdk.openadsdk.api.a;
import com.bytedance.sdk.openadsdk.api.c;
import com.bytedance.sdk.openadsdk.api.plugin.f;
import com.bytedance.sdk.openadsdk.live.b;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public final class TTAdSdk {
    public static final String BRANCH = "v6300";
    public static final String BUILT_IN_PLUGIN_NAME = "com.byted.pangle";
    public static final String C_H = "620c6b4";
    public static final int EXT_API_VERSION_CODE = 999;
    public static final boolean INCLUDE_LIVE = true;
    public static final String INITIALIZER_CLASS_NAME = "com.bytedance.sdk.openadsdk.core.AdSdkInitializerHolder";
    public static final boolean IS_BOOST = false;
    public static final boolean IS_P = true;
    public static final String LIVE_PLUGIN_PACKAGE_NAME = "com.byted.live.lite";
    public static final boolean ONLY_API = false;
    public static final int SDK_VERSION_CODE = 6310;
    public static final String SDK_VERSION_NAME = "6.3.1.0";
    public static final String S_C = "main";
    private static final a a = new f();
    private static volatile TTAdConfig b;

    public interface Callback extends InitCallback {
    }

    @Deprecated
    public interface InitCallback {
        void fail(int i, String str);

        void success();
    }

    @Deprecated
    public static boolean isInitSuccess() {
        a aVar = a;
        if (aVar != null) {
            return aVar.a();
        }
        return false;
    }

    public static boolean isSdkReady() {
        a aVar = a;
        if (aVar != null) {
            return aVar.a();
        }
        return false;
    }

    public static boolean init(Context context, TTAdConfig tTAdConfig) {
        b = tTAdConfig;
        a(context, b);
        return true;
    }

    public static void start(Callback callback) {
        a(b, "TTAdConfig is null, please exec TTAdSdk.init before TTAdSdk.start.");
        a aVar = a;
        if (aVar == null) {
            callback.fail(4100, "Load initializer failed");
        } else {
            aVar.a(TTAppContextHolder.getContext(), b, callback);
        }
    }

    private static void a(Context context, TTAdConfig tTAdConfig) {
        if (tTAdConfig != null && tTAdConfig.isDebug()) {
            c.a();
        }
        if (Looper.getMainLooper() != Looper.myLooper()) {
            c.a("Wrong Thread ! Please exec TTAdSdk.init in main thread.");
        }
        a(context, "Context is null, please check.");
        a(tTAdConfig, "TTAdConfig is null, please check.");
        TTAppContextHolder.setContext(context);
        updateConfigAuth(tTAdConfig);
    }

    public static TTAdManager getAdManager() {
        a aVar = a;
        if (aVar != null) {
            return aVar.b();
        }
        return null;
    }

    public static void updateAdConfig(TTAdConfig tTAdConfig) {
        a.c cVarB;
        if (tTAdConfig == null || (cVarB = a.b()) == null) {
            return;
        }
        Bundle bundle = new Bundle();
        if (!TextUtils.isEmpty(tTAdConfig.getData())) {
            bundle.putString("extra_data", tTAdConfig.getData());
        }
        if (!TextUtils.isEmpty(tTAdConfig.getKeywords())) {
            bundle.putString("keywords", tTAdConfig.getKeywords());
        }
        if (bundle.keySet().isEmpty()) {
            return;
        }
        cVarB.getExtra(ValueSet.class, bundle);
    }

    public static void updateConfigAuth(TTAdConfig tTAdConfig) {
        Object obj;
        if (tTAdConfig == null) {
            return;
        }
        b.a().a(tTAdConfig.getInjectionAuth());
        Map<String, Object> initExtra = tTAdConfig.getInitExtra();
        if (initExtra == null || (obj = initExtra.get(TTAdConstant.KEY_INIT_FOR_LIVE)) == null || !(obj instanceof Map)) {
            return;
        }
        b.a().a((Map<String, String>) obj);
    }

    public static void updatePaid(boolean z) {
        a.c cVarB = a.b();
        if (cVarB == null) {
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putBoolean("is_paid", z);
        if (bundle.keySet().isEmpty()) {
            return;
        }
        cVarB.getExtra(ValueSet.class, bundle);
    }

    private static void a(Object obj, String str) {
        if (obj == null) {
            throw new IllegalArgumentException(str);
        }
    }

    public static boolean isOnePointFiveAdType(TTNativeExpressAd tTNativeExpressAd) {
        Map<String, Object> mediaExtraInfo;
        if (tTNativeExpressAd == null) {
            mediaExtraInfo = null;
        } else {
            try {
                mediaExtraInfo = tTNativeExpressAd.getMediaExtraInfo();
            } catch (Throwable th) {
                th.printStackTrace();
                return false;
            }
        }
        if (mediaExtraInfo == null || !mediaExtraInfo.containsKey("_tt_ad_type_onepointfive")) {
            return false;
        }
        return ((Boolean) mediaExtraInfo.get("_tt_ad_type_onepointfive")).booleanValue();
    }
}
