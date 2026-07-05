package com.huya.live.ns.protocol;

import com.huya.mtp.hyns.NSFunction;
import com.huya.mtp.hyns.NSMethod;
import com.huya.mtp.hyns.NSTransporter;
import com.huya.mtp.hyns.wup.WupProtocol;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class MaxRetryProtocol extends WupProtocol {
    @Override // com.huya.mtp.hyns.wup.WupProtocol, com.huya.mtp.hyns.NSHttpProtocol
    public <T> NSFunction<T> getFunction(NSMethod nSMethod, NSTransporter nSTransporter) {
        return new LiveNSFunction<T>(nSMethod, nSTransporter) { // from class: com.huya.live.ns.protocol.MaxRetryProtocol.1
            @Override // com.huya.mtp.hyns.NSFunction, com.huya.mtp.http.HttpFunction, com.huya.mtp.data.transporter.param.HttpParams
            public int getMaxRetryTimes() {
                return 3;
            }
        };
    }
}
