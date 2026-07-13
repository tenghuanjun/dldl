package com.danikula.videocache.headers;

import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public class EmptyHeadersInjector implements HeaderInjector {
    @Override // com.danikula.videocache.headers.HeaderInjector
    public Map<String, String> addHeaders(String str) {
        return new HashMap();
    }
}
