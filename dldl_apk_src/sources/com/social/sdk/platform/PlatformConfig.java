package com.social.sdk.platform;

import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public class PlatformConfig {
    public static Map<PlatformType, Platform> configs;

    static {
        HashMap map = new HashMap();
        configs = map;
        map.put(PlatformType.QQ, new QQ());
        configs.put(PlatformType.QZONE, new QQ());
        configs.put(PlatformType.SINA, new Sina());
        configs.put(PlatformType.WECHAT, new Wechat());
        configs.put(PlatformType.WECHAT_CIRCLE, new Wechat(PlatformType.WECHAT_CIRCLE));
    }

    public static void setQQ(String str) {
        ((QQ) configs.get(PlatformType.QQ)).setAppId(str);
    }

    public static void setWechat(String str, String str2) {
        Wechat wechat = (Wechat) configs.get(PlatformType.WECHAT);
        wechat.setAppId(str);
        wechat.setAppKey(str2);
        Wechat wechat2 = (Wechat) configs.get(PlatformType.WECHAT_CIRCLE);
        wechat2.setAppId(str);
        wechat2.setAppKey(str2);
    }

    public static void setSina(String str) {
        ((Sina) configs.get(PlatformType.SINA)).setAppId(str);
    }

    public static Platform getPlatformConfig(PlatformType platformType) {
        return configs.get(platformType);
    }
}
