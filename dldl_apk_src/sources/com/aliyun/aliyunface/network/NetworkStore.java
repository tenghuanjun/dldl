package com.aliyun.aliyunface.network;

import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public class NetworkStore {
    private static NetworkStore s_instance = new NetworkStore();
    private Map<String, String> networkStore = new HashMap();

    public static NetworkStore getInstance() {
        return s_instance;
    }

    public void save(String str, String str2) {
        this.networkStore.put(str, str2);
    }

    public Map get() {
        return this.networkStore;
    }

    public void clear() {
        this.networkStore.clear();
    }
}
