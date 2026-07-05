package com.huya.mtp.hyns.miniprogram.jce.api;

import com.huya.mtp.hyns.NSApi;
import com.huya.mtp.hyns.NSCall;
import com.huya.mtp.hyns.wup.WupProtocol;
import com.huya.mtp.hyns.wup.WupServant;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
@NSApi(WupProtocol.class)
@WupServant("hysignalconfig")
public interface WSProxy {
    NSCall<Void> test();
}
