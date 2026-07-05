package com.sqnetwork.voly.toolbox;

import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public interface LocalDNS {

    public static class LocalDNSIps extends ArrayList<String> {
    }

    String getIp(String host, List<String> excludeIps);

    boolean isEnable();
}
