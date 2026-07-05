package com.huya.mtp.hyns.api;

import com.duowan.jce.wup.UniPacket;
import com.huya.mtp.hyns.NSApi;
import com.huya.mtp.hyns.NSCall;
import com.huya.mtp.hyns.wup.UnipacketProtocol;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
@NSApi(UnipacketProtocol.class)
public interface UnipacketRequestApi {
    NSCall request(UniPacket uniPacket);
}
