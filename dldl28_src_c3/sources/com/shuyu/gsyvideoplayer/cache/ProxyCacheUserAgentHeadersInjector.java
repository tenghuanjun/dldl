package com.shuyu.gsyvideoplayer.cache;

import com.danikula.videocache.headers.HeaderInjector;
import com.shuyu.gsyvideoplayer.utils.Debuger;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public class ProxyCacheUserAgentHeadersInjector implements HeaderInjector {
    public static final Map<String, String> mMapHeadData = new HashMap();

    @Override // com.danikula.videocache.headers.HeaderInjector
    public Map<String, String> addHeaders(String str) {
        StringBuilder sb = new StringBuilder("****** proxy addHeaders ****** ");
        Map<String, String> map = mMapHeadData;
        sb.append(map.size());
        Debuger.printfLog(sb.toString());
        return map;
    }
}
