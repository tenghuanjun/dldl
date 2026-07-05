package com.sqwan.msdk;

import android.util.Log;
import java.util.HashMap;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public class SDKTrackUtils {
    private static final String TRACK_CLASS = "com.sqwan.common.track.SqTrackActionManager2";

    public static void trackAction(String str, String str2) {
        trackAction(str, str2, null);
    }

    public static void trackAction(String str, String str2, HashMap<String, String> map) {
        try {
            Class clsLoadClass = PluginLoader.getInstance().get().mClassLoader.loadClass(TRACK_CLASS);
            clsLoadClass.getMethod("trackAction", String.class, String.class, HashMap.class).invoke(clsLoadClass.getMethod("getInstance", new Class[0]).invoke(null, new Object[0]), str, str2, map);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("sqsdk_m", "erro  SqTrackActionManager2加载失败");
        }
    }
}
