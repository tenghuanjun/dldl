package com.plugin.core.manifest;

import java.util.List;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public final class AndroidManifestInfo {
    public List<ReceiverInfo> receivers;

    public static final class ReceiverInfo {
        public List<String> actions;
        public String name;
        public int priority;
    }
}
