package com.sq.websocket_engine;

import android.text.TextUtils;
import com.sq.websocket_engine.parse.ResponseDataParse;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public abstract class ARecInfMsgBaseFactory {
    public static final int inf_msg = 9;

    protected abstract ARecInfMsg convert(String str);

    public abstract String getTargetAppid();

    public Object convert(ResponseDataParse responseDataParse) {
        if (responseDataParse.op == 9) {
            ARecInfMsg aRecInfMsgConvert = TextUtils.equals(getTargetAppid(), responseDataParse.body.appid) ? convert(responseDataParse.body.ev) : null;
            if (aRecInfMsgConvert != null) {
                aRecInfMsgConvert.init(responseDataParse);
                return aRecInfMsgConvert;
            }
        }
        return null;
    }
}
