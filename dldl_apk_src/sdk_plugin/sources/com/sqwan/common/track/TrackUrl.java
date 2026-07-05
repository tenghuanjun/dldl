package com.sqwan.common.track;

import com.sqwan.common.annotation.UrlUpdate;
import com.sqwan.msdk.config.MultiSdkManager;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class TrackUrl {
    public static final String KEY_TRACK = "track";

    @UrlUpdate("track")
    public static String TRACK_URL = "http://track." + MultiSdkManager.APP_HOST + "/api/event/";
}
