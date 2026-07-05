package com.sqwan.common.data.cache;

import com.sq.tools.manager.SensitiveInfoManager;
import com.sqwan.common.util.SQContextWrapper;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class DevManager {
    public static String getAndroidId(boolean z) {
        return !z ? "" : SensitiveInfoManager.getInstance().getAndroidId(SQContextWrapper.getApplicationContext());
    }
}
