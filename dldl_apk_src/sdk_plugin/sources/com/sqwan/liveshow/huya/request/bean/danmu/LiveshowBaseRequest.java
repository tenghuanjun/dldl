package com.sqwan.liveshow.huya.request.bean.danmu;

import com.google.sqgson.Gson;
import com.google.sqgson.GsonBuilder;
import com.google.sqgson.reflect.TypeToken;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class LiveshowBaseRequest {
    public Map<String, String> toMap() {
        return (Map) new GsonBuilder().enableComplexMapKeySerialization().create().fromJson(new Gson().toJson(this), new TypeToken<Map<String, String>>() { // from class: com.sqwan.liveshow.huya.request.bean.danmu.LiveshowBaseRequest.1
        }.getType());
    }
}
