package com.sy37sdk.share;

import com.sqwan.common.annotation.UrlUpdate;
import com.sqwan.msdk.config.MultiSdkManager;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
public class UrlConstant {

    @UrlUpdate(value = KEY_SHARE, xValue = "x_share")
    public static String GET_SHARE_SOURCE = "https://s-api." + MultiSdkManager.APP_HOST + "/go/sdk/share";
    public static final String KEY_SHARE = "share";
}
