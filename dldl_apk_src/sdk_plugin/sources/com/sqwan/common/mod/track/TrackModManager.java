package com.sqwan.common.mod.track;

import android.content.Context;
import com.sqwan.common.mod.ModHelper;
import java.util.HashMap;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class TrackModManager {
    public static void init(Context context) {
        ModHelper.getTrackMod().init(context);
    }

    public static void track(String str, HashMap<String, String> map) {
        ModHelper.getTrackMod().track(str, map);
    }

    public static void userSet(String str, String str2) {
        ModHelper.getTrackMod().userSet(str, str2);
    }

    public static void userSetOnce(String str, String str2) {
        ModHelper.getTrackMod().userSetOnce(str, str2);
    }

    public static void setUserId(String str) {
        ModHelper.getTrackMod().setUserId(str);
    }
}
