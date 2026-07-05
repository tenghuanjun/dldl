package com.huya.live.ns.protocol;

import com.duowan.auk.util.L;
import com.huya.live.ns.protocol.callback.OnParseCallback;
import com.huya.mtp.data.exception.DataException;
import com.huya.mtp.data.exception.ParseException;
import com.huya.mtp.data.transporter.Transporter;
import com.huya.mtp.hyns.NSFunction;
import com.huya.mtp.hyns.NSMethod;
import com.huya.mtp.hyns.NSTransporter;
import com.huya.mtp.hyns.wup.WupProtocol;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class LiveNSFunction<T> extends NSFunction<T> {
    public static OnParseCallback mParseCallback;

    public LiveNSFunction(NSMethod nSMethod, NSTransporter nSTransporter) {
        super(nSMethod, nSTransporter);
    }

    @Override // com.huya.mtp.hyns.NSFunction, com.huya.mtp.http.HttpFunction, com.huya.mtp.data.DataListener
    public void onError(DataException dataException, Transporter<?, ?> transporter) {
        String funcName;
        super.onError(dataException, transporter);
        if (!(dataException instanceof ParseException) || mParseCallback == null) {
            return;
        }
        String servantName = "";
        if (getNSMethod() instanceof WupProtocol.WupMethod) {
            servantName = ((WupProtocol.WupMethod) getNSMethod()).getServantName();
            funcName = ((WupProtocol.WupMethod) getNSMethod()).getFuncName();
        } else {
            funcName = "";
        }
        mParseCallback.onParseError(servantName, funcName, dataException);
        L.error("parse error servantName = " + servantName + " functionName = " + funcName + " error = " + dataException.toString());
    }
}
