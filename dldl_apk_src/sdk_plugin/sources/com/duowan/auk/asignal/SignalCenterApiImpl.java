package com.duowan.auk.asignal;

import com.duowan.auk.signal.SignalType;
import com.huya.live.common.api.signal.SignalCenterApi;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class SignalCenterApiImpl implements SignalCenterApi {
    @Override // com.huya.live.common.api.signal.SignalCenterApi
    public <T> void register(T t) {
        SignalCenter.register(t);
    }

    @Override // com.huya.live.common.api.signal.SignalCenterApi
    public <T> void unregister(T t) {
        SignalCenter.unregister(t);
    }

    @Override // com.huya.live.common.api.signal.SignalCenterApi
    public <T> void send(T t) {
        SignalCenter.send(t);
    }

    @Override // com.huya.live.common.api.signal.SignalCenterApi
    public <T> void send(T t, Object obj) {
        SignalCenter.send(t, obj);
    }

    @Override // com.huya.live.common.api.signal.SignalCenterApi
    public <T> void send(T t, int i) {
        SignalCenter.send((Object) t, SignalType.values()[i]);
    }

    @Override // com.huya.live.common.api.signal.SignalCenterApi
    public <T> void send(T t, int i, String str) {
        SignalCenter.send(t, SignalType.values()[i], str);
    }
}
