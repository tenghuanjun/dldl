package com.huya.hyhttpdns.dns;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public interface HttpDnsBiz {
    boolean addHostsChangeListener(HttpDnsHostsChangeListener httpDnsHostsChangeListener);

    String[] getHostByName(String str, long j, boolean z);

    Map<String, String[]> getHostByNames(ArrayList<String> arrayList, long j, boolean z);

    HttpDnsHostsWithSource getHostWithSource(String str, long j, boolean z);

    boolean removeHostsChangeListener(HttpDnsHostsChangeListener httpDnsHostsChangeListener);

    boolean removeIps(List<String> list);
}
