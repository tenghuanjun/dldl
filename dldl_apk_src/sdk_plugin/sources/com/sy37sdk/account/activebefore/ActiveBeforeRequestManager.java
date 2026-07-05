package com.sy37sdk.account.activebefore;

import com.sq.tool.network.SqHttpCallback;
import com.sq.tool.network.SqRequest;
import com.sqwan.common.request.CommonParamsV2;
import com.sqwan.common.request.CommonParamsV3;
import com.sy37sdk.account.UrlConstant;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class ActiveBeforeRequestManager {
    public void reqGetpermission(SqHttpCallback<JSONObject> sqHttpCallback) {
        SqRequest.of(UrlConstant.ACTIVE_BEFORE_PERMISSION).signV3().addParamsTransformer(new CommonParamsV2(true)).post(sqHttpCallback);
    }

    public void reqUserProtocol(SqHttpCallback<JSONObject> sqHttpCallback) {
        SqRequest.of(UrlConstant.USER_PROTOCOL_ACTIVE_BEFORE).signV3().addParamsTransformer(new CommonParamsV2(true)).post(sqHttpCallback);
    }

    public void getGameUrlList(SqHttpCallback<JSONObject> sqHttpCallback) {
        SqRequest.of(UrlConstant.GET_GAME_URL_LIST).signV3().addParamsTransformer(new CommonParamsV3()).get(sqHttpCallback);
    }

    public void requestCustomDomain(String str) {
        SqRequest.of(str).signV3().addParamsTransformer(new CommonParamsV3()).get(null);
    }
}
