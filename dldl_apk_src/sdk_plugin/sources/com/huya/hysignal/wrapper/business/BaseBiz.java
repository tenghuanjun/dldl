package com.huya.hysignal.wrapper.business;

import com.huya.hysignal.core.Call;
import com.huya.hysignal.wrapper.SignalWrapConfig;
import com.huya.mtp.hyns.api.Request;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public interface BaseBiz {
    boolean init(SignalWrapConfig signalWrapConfig);

    boolean isInited();

    Call newCall(Request request);

    void updateExperimentConfig(Map<String, String> map);
}
