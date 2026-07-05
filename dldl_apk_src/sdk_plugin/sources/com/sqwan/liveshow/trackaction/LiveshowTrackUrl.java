package com.sqwan.liveshow.trackaction;

import com.sqwan.common.annotation.UrlUpdate;
import com.sqwan.msdk.config.MultiSdkManager;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class LiveshowTrackUrl {

    @UrlUpdate("track")
    public static String LIVE_SHOW_TRACK_URL = "http://track." + MultiSdkManager.APP_HOST + "/api/event/";
}
