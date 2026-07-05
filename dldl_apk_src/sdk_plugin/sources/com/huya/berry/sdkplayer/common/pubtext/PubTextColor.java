package com.huya.berry.sdkplayer.common.pubtext;

import android.support.v4.view.ViewCompat;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class PubTextColor {
    public static final int DEFAULT_COLOR = -1;
    public static final int DefaultColorBarrage = -8947849;

    public static int getReceiveColor(int i, int i2) {
        if (i == -1) {
            i = i2;
        }
        return i | (-16777216);
    }

    public static int getSendColor(int i) {
        if (i == -8947849) {
            return -1;
        }
        return i & ViewCompat.MEASURED_SIZE_MASK;
    }

    public static int getReceiveColor(int i) {
        return getReceiveColor(i, -8947849);
    }
}
