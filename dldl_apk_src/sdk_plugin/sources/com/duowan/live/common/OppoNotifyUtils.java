package com.duowan.live.common;

import android.os.Build;
import com.sqwan.common.util.RomUtil;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class OppoNotifyUtils {
    public static boolean isOppo() {
        return Build.MODEL.toUpperCase().startsWith(RomUtil.ROM_OPPO);
    }
}
