package com.sqwan.common.mod.track;

import android.content.Context;
import com.sqwan.common.mod.ModHelper;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class TrackModManager2 {
    public static void init(Context context) {
        ModHelper.getTrackMod2().init(context);
    }

    public static void track(String str, Map<String, String> map) {
        ModHelper.getTrackMod2().track(str, map);
    }

    public static void userSet(String str, String str2) {
        ModHelper.getTrackMod2().userSet(str, str2);
    }

    public static void userSetOnce(String str, String str2) {
        ModHelper.getTrackMod2().userSetOnce(str, str2);
    }

    public static void setUserId(String str) {
        ModHelper.getTrackMod2().setUserId(str);
    }

    public static void flush() {
        ModHelper.getTrackMod2().flush();
    }
}
