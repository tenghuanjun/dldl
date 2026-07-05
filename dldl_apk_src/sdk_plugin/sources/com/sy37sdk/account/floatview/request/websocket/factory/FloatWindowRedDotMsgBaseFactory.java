package com.sy37sdk.account.floatview.request.websocket.factory;

import android.text.TextUtils;
import com.sq.websocket_engine.ARecInfMsg;
import com.sq.websocket_engine.ARecInfMsgBaseFactory;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public abstract class FloatWindowRedDotMsgBaseFactory extends ARecInfMsgBaseFactory {
    public String getFloatViewRedMsgEv() {
        return "fl.rd.nt";
    }

    @Override // com.sq.websocket_engine.ARecInfMsgBaseFactory
    protected ARecInfMsg convert(String str) {
        if (TextUtils.equals(str, getFloatViewRedMsgEv())) {
            return new FloatWindowRedDotRecInfMsg();
        }
        return null;
    }
}
