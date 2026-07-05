package com.sqwan.liveshow;

import com.sqwan.common.annotation.UrlUpdate;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class LiveShowUrl {
    public static final String KEY_RADIO_CONFIG = "android_radio_config";
    private static String LIVE_ROOM_HOST = "https://live-api-secure.37.com.cn";

    @UrlUpdate(value = KEY_RADIO_CONFIG, xValue = "x_radio_radio_config")
    public static String radio_config = LIVE_ROOM_HOST + "/api/live-service/v1/radio/radio_config";
    public static String rlapi = "";
    public static String rlcapi = "";
}
