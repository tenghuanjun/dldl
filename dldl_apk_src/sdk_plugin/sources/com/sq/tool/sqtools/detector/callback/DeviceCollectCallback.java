package com.sq.tool.sqtools.detector.callback;

import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public interface DeviceCollectCallback {
    void onFail();

    void onSuccess(Map<String, String> map);
}
