package com.duowan.live.common;

import android.os.Build;
import com.sqwan.common.util.RomUtil;
import java.util.HashMap;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class VivoNotifyUtils {
    public static final long INTERVAL_TIME = 500;
    private static HashMap<String, Long> mLastNotifyTimeMap = new HashMap<>();

    public static boolean isVivo() {
        return Build.MODEL.toUpperCase().startsWith(RomUtil.ROM_VIVO);
    }

    public static boolean isOverSpeedNotify(String str) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        boolean z = false;
        if (mLastNotifyTimeMap.containsKey(str) && jCurrentTimeMillis - mLastNotifyTimeMap.get(str).longValue() <= 500) {
            z = true;
        }
        if (!z) {
            mLastNotifyTimeMap.put(str, Long.valueOf(jCurrentTimeMillis));
        }
        return z;
    }

    public static void removeItem(String str) {
        if (mLastNotifyTimeMap.containsKey(str)) {
            mLastNotifyTimeMap.remove(str);
        }
    }
}
