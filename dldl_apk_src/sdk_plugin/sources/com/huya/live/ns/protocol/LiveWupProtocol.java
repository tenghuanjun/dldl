package com.huya.live.ns.protocol;

import com.huya.mtp.hyns.NSFunction;
import com.huya.mtp.hyns.NSMethod;
import com.huya.mtp.hyns.NSTransporter;
import com.huya.mtp.hyns.wup.WupProtocol;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class LiveWupProtocol extends WupProtocol {
    @Override // com.huya.mtp.hyns.wup.WupProtocol, com.huya.mtp.hyns.NSHttpProtocol
    public <T> NSFunction<T> getFunction(NSMethod nSMethod, NSTransporter nSTransporter) {
        return new LiveNSFunction(nSMethod, nSTransporter);
    }
}
