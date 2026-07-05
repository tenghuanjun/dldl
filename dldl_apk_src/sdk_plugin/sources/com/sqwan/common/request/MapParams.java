package com.sqwan.common.request;

import com.sq.tools.network.ContentType;
import com.sq.tools.network.request.RequestTools;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class MapParams extends RequestTools {
    public MapParams(Map<String, String> map) {
        this((HashMap<String, String>) new HashMap(map));
    }

    public MapParams(HashMap<String, String> map) {
        this.transparent = new HashMap<>(map);
        this.isSign = false;
        this.contentType = ContentType.FORM;
    }
}
