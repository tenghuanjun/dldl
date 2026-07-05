package com.huya.mtp.hyns.api;

import com.huya.mtp.hyns.NSApi;
import com.huya.mtp.hyns.protocol.NSDnsProtocol;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
@NSApi(NSDnsProtocol.class)
public interface NSDnsApi {

    public interface NSHttpDnsHostsChangeListener {
        void onHostsChange();
    }

    public enum NSHttpDnsSource {
        Cache,
        Net,
        Timeout,
        Backup,
        Domain,
        NullDomain,
        Uninited,
        None
    }

    boolean addHostsChangeListener(NSHttpDnsHostsChangeListener nSHttpDnsHostsChangeListener);

    String[] getHostByName(String str, long j, boolean z);

    Map<String, String[]> getHostByNames(ArrayList<String> arrayList, long j, boolean z);

    NSHttpDnsHostsWithSource getHostWithSource(String str, long j, boolean z);

    boolean removeHostsChangeListener(NSHttpDnsHostsChangeListener nSHttpDnsHostsChangeListener);

    boolean removeIps(List<String> list);

    public static class NSHttpDnsHostsWithSource {
        private String[] hosts = null;
        private NSHttpDnsSource source = NSHttpDnsSource.None;

        public String[] getHosts() {
            return this.hosts;
        }

        public void setHosts(String[] strArr) {
            this.hosts = strArr;
        }

        public NSHttpDnsSource getSource() {
            return this.source;
        }

        public void setSource(NSHttpDnsSource nSHttpDnsSource) {
            this.source = nSHttpDnsSource;
        }
    }
}
