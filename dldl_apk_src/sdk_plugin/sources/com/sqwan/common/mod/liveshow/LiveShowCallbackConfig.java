package com.sqwan.common.mod.liveshow;

import com.sqwan.msdk.api.SQResultListener;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class LiveShowCallbackConfig {
    public Map<String, String> data;
    public SQResultListener sqResultListener;

    public enum LiveShowCallbackType {
        joinRoom,
        leaveRoom,
        destroy,
        changeVoice
    }

    public LiveShowCallbackConfig(Map<String, String> map, SQResultListener sQResultListener) {
        this.data = map;
        this.sqResultListener = sQResultListener;
    }
}
