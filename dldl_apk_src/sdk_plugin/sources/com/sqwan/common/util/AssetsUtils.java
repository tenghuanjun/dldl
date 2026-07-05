package com.sqwan.common.util;

import android.content.Context;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class AssetsUtils {
    public static final String PRO_SHOW_SCREENSHOT = "isShowScreenshot";
    public static final String PRO_SHOW_SPLASH_WHEN_SCUT = "isShowSplashWhenSCut";
    public static final String SQ_MULTI_CONFIG = "multiconfig";

    public static Properties readProperties(Context context, String str) {
        try {
            InputStream inputStreamOpen = context.getResources().getAssets().open(str);
            Properties properties = new Properties();
            properties.load(inputStreamOpen);
            return properties;
        } catch (IOException unused) {
            return null;
        }
    }
}
