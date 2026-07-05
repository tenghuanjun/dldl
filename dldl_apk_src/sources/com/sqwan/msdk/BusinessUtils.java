package com.sqwan.msdk;

import android.content.Context;
import android.content.res.AssetManager;
import android.text.TextUtils;
import java.util.Properties;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public class BusinessUtils {
    private static final String MULTI_CONFIG = "multiconfig";

    public static int getScreenOrientation(Context context) {
        return getScreenOrientation(context.getResources().getAssets());
    }

    public static int getScreenOrientation(AssetManager assetManager) throws Throwable {
        Properties propertites = PropertiesUtils.readPropertites(assetManager, MULTI_CONFIG);
        if (propertites == null) {
            return 2;
        }
        String property = propertites.getProperty("isLandScape");
        return (TextUtils.isEmpty(property) || !property.equals("0")) ? 2 : 1;
    }
}
