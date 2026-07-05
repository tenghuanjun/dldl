package com.sqwan.common.net.sq;

import com.sqwan.common.annotation.UrlUpdate;
import com.sqwan.msdk.config.MultiSdkManager;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class CommonUrlConstant {
    public static final String KEY_GET_SKIP_APPLET_INFO_URL = "get_skip_applet_info";

    @UrlUpdate(value = KEY_GET_SKIP_APPLET_INFO_URL, xValue = "x_get_skip_applet_info")
    public static String SKIP_APPLET_INFO_URL = "https://sdk-api-secure." + MultiSdkManager.APP_HOST + "/api/mapi-service/v1/sdk/skip-applet-info";
}
