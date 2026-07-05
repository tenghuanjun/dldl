package com.duowan.networkmars.data;

import com.duowan.auk.ArkValue;
import com.duowan.auk.util.Config;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class MarsConfig {
    public static final String KEY_IS_NEED_DOLAUNCH = "isNeedDolaunch";

    public static void setIsNeedLaunch(boolean z) {
        config().setBoolean(KEY_IS_NEED_DOLAUNCH, z);
    }

    public static boolean getIsNeedLaunch() {
        return config().getBoolean(KEY_IS_NEED_DOLAUNCH, false);
    }

    public static Config config() {
        return Config.getInstance(ArkValue.gContext);
    }
}
