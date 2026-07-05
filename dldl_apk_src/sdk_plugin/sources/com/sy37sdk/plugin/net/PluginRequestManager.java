package com.sy37sdk.plugin.net;

import com.sq.tool.network.SqHttpCallback;
import com.sq.tool.network.SqRequest;
import com.sqwan.common.constants.SqConstants;
import com.sqwan.common.request.CommonParamsV1;
import com.sqwan.common.request.CommonParamsV2;
import com.sqwan.common.util.VersionUtil;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class PluginRequestManager {
    public void getPluginConfig(int i, SqHttpCallback<JSONObject> sqHttpCallback) {
        SqRequest.of(PluginUrl.PLUGIN_CONFIG_URL).signV3().addParam("plugin_version", Integer.valueOf(i)).addParamsTransformer(new CommonParamsV2()).post(sqHttpCallback);
    }

    public void pluginDownloadReport(int i, int i2, int i3, int i4, String str, SqHttpCallback<Void> sqHttpCallback) {
        SqRequest.of(PluginUrl.PLUGIN_REPORT_URL).signV3().addParam("plugin_version", Integer.valueOf(i)).addParam("ret", Integer.valueOf(i2)).addParam("type", Integer.valueOf(i3)).addParam("msg", str).addParam("conf_id", Integer.valueOf(i4)).addParam(SqConstants.HOST_SDK_VERSION, VersionUtil.getOriginalVersion()).addParamsTransformer(new CommonParamsV1()).post(sqHttpCallback, Void.class);
    }
}
