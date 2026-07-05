package com.huya.mtp.hyns.hysignal;

import com.huya.hal.Hal;
import com.huya.hyhttpdns.dns.HttpDnsBiz;
import com.huya.hyhttpdns.dns.HttpDnsHostsChangeListener;
import com.huya.hyhttpdns.dns.HttpDnsHostsWithSource;
import com.huya.hyhttpdns.dns.HttpDnsSource;
import com.huya.mtp.hyns.api.NSDnsApi;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class HyDns implements NSDnsApi {
    private HttpDnsBiz mHttpDnsBiz = Hal.getHttpDnsBiz();
    private Map<NSDnsApi.NSHttpDnsHostsChangeListener, HttpDnsHostsChangeListener> mListenerCache = new ConcurrentHashMap();

    @Override // com.huya.mtp.hyns.api.NSDnsApi
    public String[] getHostByName(String str, long j, boolean z) {
        return this.mHttpDnsBiz.getHostByName(str, j, z);
    }

    @Override // com.huya.mtp.hyns.api.NSDnsApi
    public Map<String, String[]> getHostByNames(ArrayList<String> arrayList, long j, boolean z) {
        return this.mHttpDnsBiz.getHostByNames(arrayList, j, z);
    }

    @Override // com.huya.mtp.hyns.api.NSDnsApi
    public NSDnsApi.NSHttpDnsHostsWithSource getHostWithSource(String str, long j, boolean z) {
        HttpDnsHostsWithSource hostWithSource = this.mHttpDnsBiz.getHostWithSource(str, j, z);
        NSDnsApi.NSHttpDnsHostsWithSource nSHttpDnsHostsWithSource = new NSDnsApi.NSHttpDnsHostsWithSource();
        nSHttpDnsHostsWithSource.setHosts(hostWithSource.getHosts());
        switch (AnonymousClass2.$SwitchMap$com$huya$hyhttpdns$dns$HttpDnsSource[hostWithSource.getSource().ordinal()]) {
            case 1:
                nSHttpDnsHostsWithSource.setSource(NSDnsApi.NSHttpDnsSource.Cache);
                return nSHttpDnsHostsWithSource;
            case 2:
                nSHttpDnsHostsWithSource.setSource(NSDnsApi.NSHttpDnsSource.Net);
                return nSHttpDnsHostsWithSource;
            case 3:
                nSHttpDnsHostsWithSource.setSource(NSDnsApi.NSHttpDnsSource.Timeout);
                return nSHttpDnsHostsWithSource;
            case 4:
                nSHttpDnsHostsWithSource.setSource(NSDnsApi.NSHttpDnsSource.Backup);
                return nSHttpDnsHostsWithSource;
            case 5:
                nSHttpDnsHostsWithSource.setSource(NSDnsApi.NSHttpDnsSource.Domain);
                return nSHttpDnsHostsWithSource;
            case 6:
                nSHttpDnsHostsWithSource.setSource(NSDnsApi.NSHttpDnsSource.NullDomain);
                return nSHttpDnsHostsWithSource;
            case 7:
                nSHttpDnsHostsWithSource.setSource(NSDnsApi.NSHttpDnsSource.Uninited);
                return nSHttpDnsHostsWithSource;
            default:
                nSHttpDnsHostsWithSource.setSource(NSDnsApi.NSHttpDnsSource.None);
                return nSHttpDnsHostsWithSource;
        }
    }

    /* JADX INFO: renamed from: com.huya.mtp.hyns.hysignal.HyDns$2, reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$huya$hyhttpdns$dns$HttpDnsSource;

        static {
            int[] iArr = new int[HttpDnsSource.values().length];
            $SwitchMap$com$huya$hyhttpdns$dns$HttpDnsSource = iArr;
            try {
                iArr[HttpDnsSource.Cache.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$huya$hyhttpdns$dns$HttpDnsSource[HttpDnsSource.Net.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$huya$hyhttpdns$dns$HttpDnsSource[HttpDnsSource.Timeout.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$huya$hyhttpdns$dns$HttpDnsSource[HttpDnsSource.Backup.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$huya$hyhttpdns$dns$HttpDnsSource[HttpDnsSource.Domain.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$huya$hyhttpdns$dns$HttpDnsSource[HttpDnsSource.NullDomain.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$huya$hyhttpdns$dns$HttpDnsSource[HttpDnsSource.Uninited.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$huya$hyhttpdns$dns$HttpDnsSource[HttpDnsSource.None.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
        }
    }

    @Override // com.huya.mtp.hyns.api.NSDnsApi
    public boolean addHostsChangeListener(final NSDnsApi.NSHttpDnsHostsChangeListener nSHttpDnsHostsChangeListener) {
        HttpDnsHostsChangeListener httpDnsHostsChangeListener = new HttpDnsHostsChangeListener() { // from class: com.huya.mtp.hyns.hysignal.HyDns.1
            @Override // com.huya.hyhttpdns.dns.HttpDnsHostsChangeListener
            public void onHostsChange() {
                nSHttpDnsHostsChangeListener.onHostsChange();
            }
        };
        this.mListenerCache.put(nSHttpDnsHostsChangeListener, httpDnsHostsChangeListener);
        return this.mHttpDnsBiz.addHostsChangeListener(httpDnsHostsChangeListener);
    }

    @Override // com.huya.mtp.hyns.api.NSDnsApi
    public boolean removeHostsChangeListener(NSDnsApi.NSHttpDnsHostsChangeListener nSHttpDnsHostsChangeListener) {
        return this.mHttpDnsBiz.removeHostsChangeListener(this.mListenerCache.remove(nSHttpDnsHostsChangeListener));
    }

    @Override // com.huya.mtp.hyns.api.NSDnsApi
    public boolean removeIps(List<String> list) {
        return this.mHttpDnsBiz.removeIps(list);
    }
}
