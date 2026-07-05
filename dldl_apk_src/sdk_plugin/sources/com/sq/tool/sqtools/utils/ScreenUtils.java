package com.sq.tool.sqtools.utils;

import android.content.Context;
import org.slf4j.Marker;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class ScreenUtils {
    public static int getWPixels(Context context) {
        String displayScreenResolution = getDisplayScreenResolution(context);
        return Integer.parseInt(displayScreenResolution.substring(0, displayScreenResolution.indexOf(Marker.ANY_MARKER)));
    }

    public static int getHPixels(Context context) {
        String displayScreenResolution = getDisplayScreenResolution(context);
        return Integer.parseInt(displayScreenResolution.substring(displayScreenResolution.indexOf(Marker.ANY_MARKER) + 1));
    }

    private static String getDisplayScreenResolution(Context context) {
        int i;
        try {
            i = context.getResources().getDisplayMetrics().heightPixels;
            try {
                return context.getResources().getDisplayMetrics().widthPixels + Marker.ANY_MARKER + i;
            } catch (Exception unused) {
                return 0 + Marker.ANY_MARKER + i;
            }
        } catch (Exception unused2) {
            i = 0;
        }
    }
}
