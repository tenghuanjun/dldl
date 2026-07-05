package com.sy37sdk.share;

import com.sq.tool.network.SqHttpCallback;
import com.sq.tool.network.SqRequest;
import com.sqwan.common.request.CommonParamsV2;
import com.sqwan.msdk.api.IMUrl;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
public class ShareRequestManager {
    public void getShareSources(String str, String str2, SqHttpCallback<String> sqHttpCallback) {
        SqRequest.of(UrlConstant.GET_SHARE_SOURCE).signV3().addParam("invitecode", str).addParam("os", IMUrl.OS).addParam("img_id", str2).addParamsTransformer(new CommonParamsV2()).post(sqHttpCallback, String.class);
    }
}
