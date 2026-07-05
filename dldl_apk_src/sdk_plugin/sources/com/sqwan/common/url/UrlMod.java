package com.sqwan.common.url;

import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class UrlMod {
    static HashMap<String, String> urlMod = new HashMap<>();

    public static Map<String, String> getUrlMod() {
        urlMod.put("IMUrl", "com.sqwan.msdk.api.IMUrl");
        urlMod.put("INewUrl", "com.sy37sdk.core.INewUrl");
        urlMod.put("orderUrl", "com.sy37sdk.order.OrderUrl");
        urlMod.put("accountUrl", "com.sy37sdk.account.UrlConstant");
        urlMod.put("pluginUrl", "com.sy37sdk.plugin.net.PluginUrl");
        urlMod.put("shareUrl", "com.sy37sdk.share.UrlConstant");
        urlMod.put("WebSocketCenter", "com.sq.websocket_engine.WebSocketCenter");
        urlMod.put("commonUrl", "com.sqwan.common.net.sq.CommonUrlConstant");
        urlMod.put("liveShowUrl", "com.sqwan.liveshow.LiveShowUrl");
        urlMod.put("liveShowTrackUrl", "com.sqwan.liveshow.trackaction.LiveshowTrackUrl");
        urlMod.put("liveRoomUrl", "com.sqwan.liveshow.huya.request.LiveRoomUrl");
        urlMod.put("liveShowTrackUrl", "com.sqwan.liveshow.trackaction.LiveshowTrackUrl");
        return urlMod;
    }
}
