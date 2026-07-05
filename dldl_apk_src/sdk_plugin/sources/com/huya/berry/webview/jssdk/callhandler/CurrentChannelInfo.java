package com.huya.berry.webview.jssdk.callhandler;

import android.content.Context;
import com.duowan.auk.util.L;
import com.duowan.live.common.webview.jssdk.callhandler.base.HandlerBase;
import com.huya.berry.gamesdk.SdkProperties;
import com.huya.component.login.LoginProperties;
import com.huya.component.user.UserProperties;
import java.util.HashMap;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class CurrentChannelInfo extends HandlerBase {
    public String getFuncName() {
        return "currentChannelInfo";
    }

    public Object call(Object obj, Context context) {
        L.info("CurrentChannelInfo", "CurrentChannelInfo:" + obj);
        HashMap map = new HashMap();
        map.put("aSid", LoginProperties.uid.get());
        map.put("topSid", LoginProperties.uid.get());
        map.put("subSid", LoginProperties.uid.get());
        map.put("presenterId", LoginProperties.uid.get());
        map.put("presenterName", UserProperties.nickName.get());
        map.put("currentNickName", UserProperties.nickName.get());
        map.put("currentUid", LoginProperties.uid.get());
        map.put("currentLogoUrl", UserProperties.avatarUrl.get());
        map.put("presenterLogoUrl", UserProperties.avatarUrl.get());
        map.put("durationAfterJoinSuccess", String.valueOf(System.currentTimeMillis() - SdkProperties.startLiveTime.get().longValue()));
        map.put("gameId", SdkProperties.gameId.get());
        return map;
    }
}
