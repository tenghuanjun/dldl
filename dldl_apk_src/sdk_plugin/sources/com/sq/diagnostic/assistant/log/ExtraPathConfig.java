package com.sq.diagnostic.assistant.log;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class ExtraPathConfig {
    public static final int ALWAYS_CACHE = -1;
    public int cacheDays;
    public String path;

    public ExtraPathConfig(String str, int i) {
        this.path = str;
        this.cacheDays = i;
    }
}
