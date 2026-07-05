package com.huya.berry.webview.jssdk.callhandler;

import android.content.Context;
import com.duowan.auk.util.L;
import com.duowan.live.common.webview.jssdk.callhandler.base.HandlerBase;
import com.huya.hyhttpdns.dns.NetworkUtil;
import com.hysdkproxy.LoginProxy;
import com.sqwan.liveshow.huya.SqR;
import java.util.HashMap;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class HyUdbSdkCommon extends HandlerBase {
    private static final String TAG = "HyUdbSdkVersion";

    public String getFuncName() {
        return "HYUDBMSDKCommon";
    }

    public Object call(Object obj, Context context) {
        String h5InfoEx = LoginProxy.getInstance().getH5InfoEx();
        L.info("commonData:" + h5InfoEx);
        HashMap map = new HashMap();
        map.put(NetworkUtil.NET_TYPE_COMMON, h5InfoEx);
        map.put("status", SqR.string.ok);
        return map;
    }
}
