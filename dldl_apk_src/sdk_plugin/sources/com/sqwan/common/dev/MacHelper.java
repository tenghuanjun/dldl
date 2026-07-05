package com.sqwan.common.dev;

import android.content.Context;
import com.sq.tools.manager.SensitiveInfoManager;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class MacHelper {
    public static String getMac(Context context) {
        return getMac(context, true);
    }

    public static String getMac(Context context, boolean z) {
        return !z ? "" : SensitiveInfoManager.getInstance().getMacAddress(context);
    }
}
