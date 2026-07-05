package com.sqnetwork.voly.toolbox;

import com.sqnetwork.voly.Cache;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class NoCache implements Cache {
    @Override // com.sqnetwork.voly.Cache
    public void clear() {
    }

    @Override // com.sqnetwork.voly.Cache
    public Cache.Entry get(String key) {
        return null;
    }

    @Override // com.sqnetwork.voly.Cache
    public void initialize() {
    }

    @Override // com.sqnetwork.voly.Cache
    public void invalidate(String key, boolean fullExpire) {
    }

    @Override // com.sqnetwork.voly.Cache
    public void put(String key, Cache.Entry entry) {
    }

    @Override // com.sqnetwork.voly.Cache
    public void remove(String key) {
    }
}
