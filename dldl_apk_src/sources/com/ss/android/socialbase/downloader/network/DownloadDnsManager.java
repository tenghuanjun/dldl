package com.ss.android.socialbase.downloader.network;

import android.net.Uri;
import android.os.Handler;
import com.ss.android.socialbase.downloader.downloader.DownloadComponentManager;
import com.ss.android.socialbase.downloader.network.connectionpool.DownloadPreconnecter;
import com.ss.android.socialbase.downloader.setting.DownloadSetting;
import com.ss.android.socialbase.downloader.setting.DownloadSettingKeys;
import com.ss.android.socialbase.downloader.thread.DownloadWatchDog;
import com.ss.android.socialbase.downloader.utils.LruCache;
import java.net.InetAddress;
import java.util.List;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes.dex */
public class DownloadDnsManager {
    private final LruCache<String, DnsRecord> cache;
    private final Handler cpuHandler;
    private final Handler networkHandler;

    public interface Callback {
        void onDnsResolved(String str, List<InetAddress> list);
    }

    private static class Holder {
        private static final DownloadDnsManager INSTANCE = new DownloadDnsManager();

        private Holder() {
        }
    }

    private DownloadDnsManager() {
        this.cache = new LruCache<>(4, 16, false);
        this.networkHandler = new Handler(DownloadPreconnecter.getLooper());
        this.cpuHandler = new Handler(DownloadWatchDog.getThreadLooper());
    }

    public static DownloadDnsManager getInstance() {
        return Holder.INSTANCE;
    }

    public void resolveDnsAsync(final String str, final Callback callback, final long j) {
        this.networkHandler.post(new Runnable() { // from class: com.ss.android.socialbase.downloader.network.DownloadDnsManager.1
            @Override // java.lang.Runnable
            public void run() {
                DownloadDnsManager.this.resolveDns(str, callback, j);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void resolveDns(final String str, final Callback callback, long j) {
        final DnsRecord dnsRecord;
        IDownloadDns downloadDns;
        try {
            String host = Uri.parse(str).getHost();
            synchronized (this.cache) {
                dnsRecord = this.cache.get(host);
            }
            if (dnsRecord != null) {
                if (System.currentTimeMillis() - dnsRecord.timestamp < DownloadSetting.obtainGlobal().optInt(DownloadSettingKeys.DNS_EXPIRE_MIN, 10) * 60 * 1000) {
                    if (callback != null) {
                        callback.onDnsResolved(str, dnsRecord.value);
                        return;
                    }
                    return;
                }
            }
            Runnable runnable = new Runnable() { // from class: com.ss.android.socialbase.downloader.network.DownloadDnsManager.2
                @Override // java.lang.Runnable
                public void run() {
                    Callback callback2 = callback;
                    if (callback2 != null) {
                        String str2 = str;
                        DnsRecord dnsRecord2 = dnsRecord;
                        callback2.onDnsResolved(str2, dnsRecord2 == null ? null : dnsRecord2.value);
                    }
                }
            };
            this.cpuHandler.postDelayed(runnable, j);
            List<InetAddress> listLookup = null;
            if (DownloadSetting.obtainGlobal().optInt(DownloadSettingKeys.USE_HOST_DNS, 1) == 1 && (downloadDns = DownloadComponentManager.getDownloadDns()) != null) {
                try {
                    listLookup = downloadDns.lookup(host);
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }
            if (listLookup == null || listLookup.isEmpty()) {
                try {
                    listLookup = DownloadComponentManager.getDefaultDownloadDns().lookup(host);
                } catch (Throwable th2) {
                    th2.printStackTrace();
                }
            }
            if (listLookup != null && !listLookup.isEmpty()) {
                updateIpAddressToCache(host, listLookup);
            } else if (dnsRecord != null) {
                listLookup = dnsRecord.value;
            }
            this.cpuHandler.removeCallbacks(runnable);
            if (callback != null) {
                callback.onDnsResolved(str, listLookup);
            }
        } catch (Throwable th3) {
            th3.printStackTrace();
        }
    }

    private void updateIpAddressToCache(String str, List<InetAddress> list) {
        synchronized (this.cache) {
            DnsRecord dnsRecord = this.cache.get(str);
            if (dnsRecord == null) {
                dnsRecord = new DnsRecord();
                this.cache.put(str, dnsRecord);
            }
            dnsRecord.value = list;
            dnsRecord.timestamp = System.currentTimeMillis();
        }
    }

    private static class DnsRecord {
        long timestamp;
        List<InetAddress> value;

        private DnsRecord() {
        }
    }
}
