package com.huya.berry.webview.jssdk.callhandler;

import android.content.Context;
import android.os.Build;
import com.duowan.auk.ArkValue;
import com.duowan.auk.util.L;
import com.duowan.live.common.webview.jssdk.callhandler.base.HandlerBase;
import com.duowan.live.common.webview.jssdk.callhandler.base.WrapUtils;
import com.duowan.live.one.module.uploadLog.FeedBackConstants;
import com.huya.berry.client.HuyaBerry;
import com.huya.berry.gamesdk.SdkProperties;
import com.huya.berry.gamesdk.utils.ResourceUtil;
import com.huya.berry.gamesdk.wup.WupHelper;
import com.sqwan.liveshow.huya.SqR;
import java.util.HashMap;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class GetAppInfo extends HandlerBase {
    public String getFuncName() {
        return "getAppInfo";
    }

    public Object call(Object obj, Context context) {
        L.info("GetAppInfo", "GetAppInfo:" + obj);
        HashMap map = new HashMap();
        map.put("status", SqR.string.ok);
        map.put("systemVersion", Build.VERSION.RELEASE);
        map.put(FeedBackConstants.KEY_FB_APPVERSION, WupHelper.getVersion());
        map.put("err_code", "0");
        map.put("appName", ArkValue.gContext.getResources().getString(ResourceUtil.getStringResIDByName("app_name")));
        map.put("huyaUA", WupHelper.getSHuYaUA());
        map.put("privilegeUseIAP", "1");
        map.put("gameId", String.valueOf(SdkProperties.gameId.get()));
        map.put(HuyaBerry.BerryEvent.BERRYEVENT_GAMEACCOUNTID, SdkProperties.gameAccountID.get());
        map.put(FeedBackConstants.KEY_FB_APPID, SdkProperties.appId.get());
        return WrapUtils.wrap(map, SqR.string.ok);
    }
}
