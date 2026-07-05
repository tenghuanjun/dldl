package com.sy37sdk.account.config;

import android.content.Context;
import com.sq.webview.net.IRequest;
import com.sqwan.common.webview.WebRequestProxy;
import com.sqwan.msdk.api.SQAppConfig;
import com.sy37sdk.account.UrlConstant;
import java.util.HashMap;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class ConfigRequest {
    public static void getConfigInfo(Context context, IRequest.RequestCallback<JSONObject> requestCallback) {
        SQAppConfig sQAppConfig = com.sqwan.msdk.config.ConfigManager.getInstance(context).getSQAppConfig();
        WebRequestProxy webRequestProxy = new WebRequestProxy();
        HashMap map = new HashMap();
        map.put("gid", sQAppConfig.getGameid());
        map.put("pid", sQAppConfig.getPartner());
        webRequestProxy.getRequest(UrlConstant.URL_GET_CONFIG_INFO, map, requestCallback);
    }
}
