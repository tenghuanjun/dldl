package com.huya.berry.gamesdk.utils;

import android.content.Context;
import com.duowan.auk.ArkValue;
import com.duowan.auk.util.Config;
import java.util.Arrays;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class PreferenceUtil {
    private static final String KEY_ENDLIVE_CALLBACK = "endLiveCallback";
    private static final String KEY_IS_FORBID_UDBLOG = "ForbidUdbLog";
    private static final String KEY_IS_NEED_DOLAUNCH = "isNeedDolaunch";
    private static final String KEY_IS_PREVIEW_AARNLI = "previewAarnli";
    private static final String KEY_IS_PREVIEW_GUIDE = "previewGuide";
    private static final String KEY_IS_USE_CHANNEL = "isUseChannel";
    private static final String KEY_LIVEId = "liveId";
    private static final String KEY_LIVETITLE = "livetitle";
    private static final String KEY_RESOLUTION_PRE = "resolution_";
    private static final String KEY_USERRECLISTRSP_VCONTEXT = "UserRecListRsp_vContext";
    private static Config config;

    public static void init(Context context) {
        config = Config.getInstance(context);
    }

    public static Config config() {
        return config;
    }

    protected static String getDebuggableKey(String str) {
        return str + (!ArkValue.debuggable() ? 1 : 0);
    }

    public static byte[] getVContext() {
        String string = config.getString(KEY_USERRECLISTRSP_VCONTEXT, null);
        if (string == null) {
            return null;
        }
        String[] strArrSplit = string.substring(1, string.length() - 1).split(", ");
        byte[] bArr = new byte[strArrSplit.length];
        for (int i = 0; i < strArrSplit.length; i++) {
            bArr[i] = Byte.parseByte(strArrSplit[i]);
        }
        return bArr;
    }

    public static void setVContext(byte[] bArr) {
        config.setStringAsync(KEY_USERRECLISTRSP_VCONTEXT, Arrays.toString(bArr));
    }

    public static String getLiveTitle() {
        return config.getString(KEY_LIVETITLE, "");
    }

    public static void setLiveTitle(String str) {
        config.setStringAsync(KEY_LIVETITLE, str);
    }

    public static boolean getIsUseChannel() {
        return config.getBoolean(getDebuggableKey(KEY_IS_USE_CHANNEL), true);
    }

    public static void setIsUseChannel(boolean z) {
        config.setBooleanAsync(getDebuggableKey(KEY_IS_USE_CHANNEL), z);
    }

    public static void setResolution(long j, int i) {
        config.setIntAsync(KEY_RESOLUTION_PRE + j, i);
    }

    public static int getResolution(long j) {
        return config.getInt(KEY_RESOLUTION_PRE + j, 2);
    }

    public static boolean getEndLiveCallback() {
        return config.getBoolean(KEY_ENDLIVE_CALLBACK, true);
    }

    public static void setEndLiveCallback(boolean z) {
        config.setBoolean(KEY_ENDLIVE_CALLBACK, z);
    }

    public static long getLiveId() {
        return config.getLong("liveId", -1L);
    }

    public static void setLiveId(long j) {
        config.setLongAsync("liveId", j);
    }

    public static boolean getIsNeedLaunch() {
        return config().getBoolean("isNeedDolaunch", false);
    }

    public static void setIsNeedLaunch(boolean z) {
        config().setBooleanAsync("isNeedDolaunch", z);
    }

    public static boolean getIsAarnli() {
        return config().getBoolean(KEY_IS_PREVIEW_AARNLI, false);
    }

    public static void setIsAarnli(boolean z) {
        config().setBoolean(KEY_IS_PREVIEW_AARNLI, z);
    }

    public static void setIsPreviewGuide() {
        config().setBoolean(KEY_IS_PREVIEW_GUIDE, false);
    }

    public static boolean getIsPreviewGuide() {
        return config().getBoolean(KEY_IS_PREVIEW_GUIDE, true);
    }

    public static boolean getIsForbidUdbLog() {
        return config().getBoolean(KEY_IS_FORBID_UDBLOG, true);
    }

    public static void setIsForbidUdbLog(boolean z) {
        config().setBooleanAsync(KEY_IS_FORBID_UDBLOG, z);
    }
}
