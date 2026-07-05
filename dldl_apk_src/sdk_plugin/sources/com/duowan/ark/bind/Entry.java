package com.duowan.ark.bind;

import com.duowan.ark.api.ApiHolder;
import com.duowan.ark.api.DebugApiDelegate;
import com.duowan.ark.api.LogApi;
import com.duowan.ark.api.LogApiDelegate;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class Entry {
    private static final LogApiDelegate LOG_API = new LogApiDelegate();
    private static final DebugApiDelegate DEBUG_API = new DebugApiDelegate();

    public static void init(ApiHolder apiHolder) {
        LOG_API.setLogApi(apiHolder.getLogApi());
        DEBUG_API.setDebugApi(apiHolder.getDebugApi());
    }

    public static LogApi getLogApi() {
        return LOG_API;
    }

    public static DebugApiDelegate getDebugApi() {
        return DEBUG_API;
    }
}
