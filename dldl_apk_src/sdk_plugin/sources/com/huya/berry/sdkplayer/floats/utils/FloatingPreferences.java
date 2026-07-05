package com.huya.berry.sdkplayer.floats.utils;

import android.text.TextUtils;
import com.duowan.auk.ArkValue;
import com.duowan.auk.util.Config;
import com.duowan.auk.util.L;
import com.huya.berry.sdkplayer.floats.data.FloatingPositionInfo;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class FloatingPreferences {
    private static final String CONFIG_NAME = "FloatingPreferences";
    private static final String KEY_FLOATING_POSITION_INFO = "floatingPositionInfo";
    private static final String KEY_IS_SHOW_FLOATING_ON_OTHER_APP = "isShowFloatingOnOtherAppVersion3";
    private static final String KEY_SHOW_FLOATING_CLOSE_PROMPT = "isNeedShowFloatingClosePromptNew";
    private static final String KEY_SHOW_FLOATING_VERSION_CODE = "3";
    private static final String TAG = "FloatingPreferences";

    public static boolean isNeedShowFloatingClosePrompt() {
        return Config.getInstance(ArkValue.gContext, "FloatingPreferences").getBoolean(KEY_SHOW_FLOATING_CLOSE_PROMPT, true);
    }

    public static void saveShowFloatingClosePrompt() {
        Config.getInstance(ArkValue.gContext, "FloatingPreferences").setBoolean(KEY_SHOW_FLOATING_CLOSE_PROMPT, false);
    }

    public static boolean isFloatingShowOtherApp() {
        return Config.getInstance(ArkValue.gContext, "FloatingPreferences").getBoolean(KEY_IS_SHOW_FLOATING_ON_OTHER_APP, true);
    }

    public static void saveFloatingPositionInfo(FloatingPositionInfo floatingPositionInfo) {
        if (floatingPositionInfo == null) {
            L.info("FloatingPreferences", "info is null");
        } else {
            L.debug("FloatingPreferences", "enter saveFloatingPositionInfo, %s", floatingPositionInfo.saveToString());
            Config.getInstance(ArkValue.gContext, "FloatingPreferences").setString(KEY_FLOATING_POSITION_INFO, floatingPositionInfo.saveToString());
        }
    }

    public static FloatingPositionInfo getFloatingPositionInfo() {
        String string = Config.getInstance(ArkValue.gContext, "FloatingPreferences").getString(KEY_FLOATING_POSITION_INFO, null);
        if (TextUtils.isEmpty(string)) {
            return null;
        }
        FloatingPositionInfo floatingPositionInfo = new FloatingPositionInfo();
        floatingPositionInfo.parseFromString(string);
        L.debug("FloatingPreferences", "enter getFloatingPositionInfo, %s", floatingPositionInfo.toString());
        return floatingPositionInfo;
    }
}
