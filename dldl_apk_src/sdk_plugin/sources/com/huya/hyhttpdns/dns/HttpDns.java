package com.huya.hyhttpdns.dns;

import android.content.Context;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.os.PowerManager;
import com.huya.hyhttpdns.dns.ConnectionReceiver;
import com.huya.hyhttpdns.jce.HttpDnsItem;
import com.huya.hyhttpdns.jce.QueryHttpDnsRsp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public final class HttpDns implements HttpDnsBiz {
    private static final int CLEAR_EXPIRED_DOMAIN_IP_ITEM = 1;
    private static final int SAVING_DISK_WINDOW_TIME = 500;
    static final String TAG = "HyHttpDns";
    private static HttpDns sInstance;
    private Context mContext;
    private HttpDnsDiskCache mDiskCache;
    private SegmentLock mSegmentLock;
    private Handler mThreadHandler;
    private static final HandlerThread sHandlerThread = new HandlerThread("HyHttpDnsThread");
    private static boolean sIsInitialized = false;
    private static final Map<String, GetIpsSyncTask> sExecutingGetIpSyncTaskMap = new HashMap();
    private final Pattern PATTERN_IS_IP_ADDRESS = Pattern.compile("\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}");
    private final int CLEAR_EXPIRE_CACHE_INTERVAL = 300000;
    private final int UPDATE_DOMAIN_IP_MAP = 0;
    private volatile boolean mNeedTriggleSave = true;
    private boolean mIsTestEnv = false;
    private boolean mIsOverSea = false;
    private volatile long mUpdateDelayTime = 180;
    private Map<String, HttpDnsItem> mHttpDnsItemMap = new ConcurrentHashMap();
    private HttpDnsReportListener httpDnsreportListener = null;
    private List<HttpDnsHostsChangeListener> hostsChangeListeners = Collections.emptyList();

    interface DnsResultCallback {
        void onResult(Map<String, HttpDnsItem> map);
    }

    private long getWaitTimeout(long j) {
        return j > 2000 ? j - 100 : j;
    }

    static {
        sHandlerThread.start();
    }

    private HttpDns() {
    }

    public static synchronized HttpDns getInstance() {
        if (sInstance == null) {
            sInstance = new HttpDns();
        }
        return sInstance;
    }

    public synchronized void init(HttpDnsConfig httpDnsConfig) {
        if (sIsInitialized) {
            return;
        }
        Context context = httpDnsConfig.mContext;
        if (context == null) {
            throw new NullPointerException("context must be not null");
        }
        this.httpDnsreportListener = httpDnsConfig.httpDnsreportListener;
        HttpDnsLogProxy.getInstance().init(httpDnsConfig.mHttpDnsLog, httpDnsConfig.isTestEnv());
        this.mIsTestEnv = httpDnsConfig.isTestEnv();
        this.mIsOverSea = httpDnsConfig.ismIsOverSea();
        DnsTask.sIsTestEnv = this.mIsTestEnv;
        DnsTask.mConfig = httpDnsConfig;
        DnsTask.sHttpDnsUserInfo = httpDnsConfig.getHyHttpDnsReqParam();
        DnsTask.setDnsHost(httpDnsConfig.mIsTestEnv, httpDnsConfig.mIsOverSea, httpDnsConfig.mUserSetTestUrl, httpDnsConfig.mUserSetHost);
        this.mContext = context;
        this.mSegmentLock = new SegmentLock();
        this.mDiskCache = new HttpDnsDiskCache(httpDnsConfig.getCacheDir(), context);
        this.mThreadHandler = new Handler(sHandlerThread.getLooper()) { // from class: com.huya.hyhttpdns.dns.HttpDns.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                int i = message.what;
                if (i == 0) {
                    HttpDns.this.updateDnsRegularly();
                } else {
                    if (i != 1) {
                        return;
                    }
                    HttpDns.this.clearExpiredItemRegularly();
                }
            }
        };
        loadDiskCache();
        updateDnsRegularly();
        context.registerReceiver(new ConnectionReceiver(new ConnectionReceiver.NetworkListener() { // from class: com.huya.hyhttpdns.dns.HttpDns.2
            @Override // com.huya.hyhttpdns.dns.ConnectionReceiver.NetworkListener
            public void onNetworkChange() {
                HttpDns.this.dispatch(new Runnable() { // from class: com.huya.hyhttpdns.dns.HttpDns.2.1
                    @Override // java.lang.Runnable
                    public void run() {
                        HttpDnsLogProxy.getInstance().info(HttpDns.TAG, "onNetworkChange");
                        HttpDns.this.mHttpDnsItemMap.clear();
                        if (NetworkUtil.isNetworkAvailable(HttpDns.this.mContext)) {
                            HttpDns.this.loadDiskCache();
                            HttpDns.this.updateDnsRegularly();
                        }
                    }
                });
            }
        }), new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
        sIsInitialized = true;
    }

    public void updateUserInfo(HttpDnsUserInfo httpDnsUserInfo) {
        DnsTask.sHttpDnsUserInfo = httpDnsUserInfo;
    }

    @Override // com.huya.hyhttpdns.dns.HttpDnsBiz
    public String[] getHostByName(String str, long j, boolean z) throws Throwable {
        HttpDnsStat httpDnsStat;
        String str2;
        GetIpsSyncTask getIpsSyncTask;
        boolean z2;
        HttpDnsStat httpDnsStat2;
        if (!sIsInitialized) {
            HttpDnsLogProxy.getInstance().error(TAG, "syncGetHostByName need init");
            return null;
        }
        long jCurrentTimeMillis = System.currentTimeMillis();
        HttpDnsLogProxy httpDnsLogProxy = HttpDnsLogProxy.getInstance();
        Object[] objArr = new Object[3];
        objArr[0] = str;
        objArr[1] = Long.valueOf(j);
        objArr[2] = z ? "true" : "fasle";
        httpDnsLogProxy.debug(TAG, "syncGetHostByName domainName = %s, timeout=%d needCheck=%s", objArr);
        if (isIpAddress(str)) {
            HttpDnsLogProxy.getInstance().debug(TAG, "getHostByName domain is ip, return=%s", str);
            return new String[]{str};
        }
        if (str == null) {
            HttpDnsLogProxy.getInstance().debug(TAG, "getHostByName domain is null");
            return new String[0];
        }
        HttpDnsStat httpDnsStat3 = new HttpDnsStat(str, String.valueOf(j));
        String[] strArrFilterExpiredIps = filterExpiredIps(this.mHttpDnsItemMap.get(str), z);
        if (strArrFilterExpiredIps != null) {
            httpDnsStat3.type = HttpDnsConst.TYPE_CACHE;
            httpDnsStat3.iSuccess = 0;
            httpDnsStat3.responseTime = System.currentTimeMillis() - jCurrentTimeMillis;
            httpDnsStat3.ipListState = strArrFilterExpiredIps.length <= 0 ? 1 : 2;
            httpDnsStat3.reportHttpDnsStat();
            HttpDnsLogProxy.getInstance().debug(TAG, "getHostByName from cache , ips=%s", Arrays.toString(strArrFilterExpiredIps));
            return strArrFilterExpiredIps;
        }
        if (j < 0) {
            HttpDnsLogProxy.getInstance().info(TAG, "syncGetHostByName timeout<0, domain:%s, return", str);
            return new String[0];
        }
        String str3 = HttpDnsConst.KEY_SYNC_PREFIX + str;
        if (j > 0) {
            this.mSegmentLock.lock(str3);
            try {
                getIpsSyncTask = sExecutingGetIpSyncTaskMap.get(str3);
                if (getIpsSyncTask != null) {
                    HttpDnsLogProxy.getInstance().debug(TAG, "add to httpDnsStatList");
                    getIpsSyncTask.add(httpDnsStat3);
                    httpDnsStat3.type = HttpDnsConst.TYPE_SYNC_WAIT;
                    httpDnsStat = httpDnsStat3;
                    str2 = str3;
                    z2 = true;
                } else {
                    ArrayList arrayList = new ArrayList();
                    arrayList.add(str);
                    httpDnsStat = httpDnsStat3;
                    str2 = str3;
                    try {
                        GetIpsSyncTask getIpsSyncTask2 = new GetIpsSyncTask(arrayList, httpDnsStat3, jCurrentTimeMillis);
                        sExecutingGetIpSyncTaskMap.put(str2, getIpsSyncTask2);
                        getIpsSyncTask = getIpsSyncTask2;
                        z2 = false;
                    } catch (Throwable th) {
                        th = th;
                        throw th;
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                str2 = str3;
            }
        } else {
            httpDnsStat = httpDnsStat3;
            str2 = str3;
            ArrayList arrayList2 = new ArrayList();
            arrayList2.add(str);
            getIpsSyncTask = new GetIpsSyncTask(arrayList2, httpDnsStat, jCurrentTimeMillis);
            z2 = false;
        }
        if (z2) {
            synchronized (getIpsSyncTask) {
                try {
                    getIpsSyncTask.wait(getWaitTimeout(j));
                } catch (InterruptedException unused) {
                    HttpDnsLogProxy.getInstance().debug(TAG, "getIpsSyncTask merge request timeout");
                }
            }
            httpDnsStat2 = httpDnsStat;
        } else {
            try {
                HttpDnsThreadPool.submit(getIpsSyncTask).get(getWaitTimeout(j), TimeUnit.MILLISECONDS);
            } catch (Exception unused2) {
                HttpDnsLogProxy.getInstance().error(TAG, "network request timeout");
            }
            this.mSegmentLock.lock(str2);
            try {
                sExecutingGetIpSyncTaskMap.remove(str2);
                httpDnsStat2 = httpDnsStat;
                HttpDnsLogProxy.getInstance().debug(TAG, "getIpsSyncTask.httpDnsStat = %s", httpDnsStat2);
                for (HttpDnsStat httpDnsStat4 : getIpsSyncTask.httpDnsStatList) {
                    if (httpDnsStat4 != null) {
                        httpDnsStat4.iSuccess = httpDnsStat2.iSuccess;
                        httpDnsStat4.iRetCode = httpDnsStat2.iRetCode;
                    }
                }
                synchronized (getIpsSyncTask) {
                    getIpsSyncTask.notifyAll();
                }
            } finally {
                this.mSegmentLock.unlock(str2);
            }
        }
        httpDnsStat2.responseTime = System.currentTimeMillis() - jCurrentTimeMillis;
        String[] strArrFilterExpiredIps2 = filterExpiredIps(this.mHttpDnsItemMap.get(str), z);
        if (strArrFilterExpiredIps2 != null) {
            httpDnsStat2.ipListState = strArrFilterExpiredIps2.length > 0 ? 2 : 1;
            httpDnsStat2.reportHttpDnsStat();
            HttpDnsLogProxy.getInstance().debug(TAG, "getHostByName from net, ips=%s", Arrays.toString(strArrFilterExpiredIps2));
            return strArrFilterExpiredIps2;
        }
        httpDnsStat2.reportHttpDnsStat();
        HttpDnsLogProxy.getInstance().debug(TAG, "getHostByName finally, return null");
        return new String[0];
    }

    @Override // com.huya.hyhttpdns.dns.HttpDnsBiz
    public Map<String, String[]> getHostByNames(ArrayList<String> arrayList, long j, boolean z) {
        GetIpsSyncTask getIpsSyncTask;
        if (!sIsInitialized || arrayList == null) {
            return new HashMap();
        }
        long jCurrentTimeMillis = System.currentTimeMillis();
        ArrayList arrayList2 = new ArrayList(arrayList);
        Collections.sort(arrayList2);
        boolean z2 = true;
        boolean z3 = false;
        HttpDnsLogProxy.getInstance().info(TAG, "asyncGetHostByNames domains = %s", arrayList2);
        String string = arrayList2.toString();
        if (j > 0) {
            this.mSegmentLock.lock(string);
            try {
                getIpsSyncTask = sExecutingGetIpSyncTaskMap.get(string);
                if (getIpsSyncTask != null) {
                    HttpDnsLogProxy.getInstance().debug(TAG, "add to httpDnsStatList");
                } else {
                    GetIpsSyncTask getIpsSyncTask2 = new GetIpsSyncTask(arrayList2, new HttpDnsStat(arrayList.toString(), String.valueOf(j)), jCurrentTimeMillis);
                    sExecutingGetIpSyncTaskMap.put(string, getIpsSyncTask2);
                    getIpsSyncTask = getIpsSyncTask2;
                    z2 = false;
                }
                this.mSegmentLock.unlock(string);
                z3 = z2;
            } finally {
            }
        } else {
            getIpsSyncTask = new GetIpsSyncTask(arrayList2, new HttpDnsStat(arrayList.toString(), String.valueOf(j)), jCurrentTimeMillis);
        }
        if (z3) {
            synchronized (getIpsSyncTask) {
                try {
                    getIpsSyncTask.wait(getWaitTimeout(j));
                } catch (InterruptedException unused) {
                    HttpDnsLogProxy.getInstance().debug(TAG, "getIpsSyncTask merge request timeout");
                }
            }
        } else {
            try {
                HttpDnsThreadPool.submit(getIpsSyncTask).get(getWaitTimeout(j), TimeUnit.MILLISECONDS);
            } catch (Exception unused2) {
                HttpDnsLogProxy.getInstance().error(TAG, "network request timeout");
            }
            this.mSegmentLock.lock(string);
            try {
                sExecutingGetIpSyncTaskMap.remove(string);
                synchronized (getIpsSyncTask) {
                    getIpsSyncTask.notifyAll();
                }
            } finally {
            }
        }
        HashMap map = new HashMap();
        for (String str : arrayList) {
            String[] strArrFilterExpiredIps = filterExpiredIps(this.mHttpDnsItemMap.get(str), z);
            if (strArrFilterExpiredIps != null) {
                map.put(str, strArrFilterExpiredIps);
            }
        }
        return map;
    }

    @Override // com.huya.hyhttpdns.dns.HttpDnsBiz
    public HttpDnsHostsWithSource getHostWithSource(String str, long j, boolean z) throws Throwable {
        HttpDnsStat httpDnsStat;
        String str2;
        GetIpsSyncTask getIpsSyncTask;
        boolean z2;
        HttpDnsStat httpDnsStat2;
        HttpDnsHostsWithSource httpDnsHostsWithSource = new HttpDnsHostsWithSource();
        if (!sIsInitialized) {
            HttpDnsLogProxy.getInstance().error(TAG, "syncGetHostByName need init");
            return httpDnsHostsWithSource;
        }
        long jCurrentTimeMillis = System.currentTimeMillis();
        HttpDnsLogProxy.getInstance().debug(TAG, "syncGetHostByName domainName = %s", str);
        if (isIpAddress(str)) {
            httpDnsHostsWithSource.setHosts(new String[]{str});
            httpDnsHostsWithSource.setSource(HttpDnsSource.Domain);
            return httpDnsHostsWithSource;
        }
        if (str == null) {
            httpDnsHostsWithSource.setHosts(new String[0]);
            httpDnsHostsWithSource.setSource(HttpDnsSource.NullDomain);
            return httpDnsHostsWithSource;
        }
        HttpDnsStat httpDnsStat3 = new HttpDnsStat(str, String.valueOf(j));
        String[] strArrFilterExpiredIps = filterExpiredIps(this.mHttpDnsItemMap.get(str), z);
        if (strArrFilterExpiredIps != null) {
            httpDnsStat3.type = HttpDnsConst.TYPE_CACHE;
            httpDnsStat3.iSuccess = 0;
            httpDnsStat3.responseTime = System.currentTimeMillis() - jCurrentTimeMillis;
            httpDnsStat3.ipListState = strArrFilterExpiredIps.length > 0 ? 2 : 1;
            httpDnsStat3.reportHttpDnsStat();
            httpDnsHostsWithSource.setHosts(strArrFilterExpiredIps);
            httpDnsHostsWithSource.setSource(HttpDnsSource.Cache);
            HttpDnsLogProxy.getInstance().debug(TAG, "getHostWithSource from cache, ips=%s", Arrays.toString(strArrFilterExpiredIps));
            return httpDnsHostsWithSource;
        }
        if (j < 0) {
            HttpDnsLogProxy.getInstance().info(TAG, "getHostWithSource timeout<0, domain: %s return", str);
            return httpDnsHostsWithSource;
        }
        String str3 = HttpDnsConst.KEY_SYNC_PREFIX + str;
        if (j > 0) {
            this.mSegmentLock.lock(str3);
            try {
                getIpsSyncTask = sExecutingGetIpSyncTaskMap.get(str3);
                if (getIpsSyncTask != null) {
                    HttpDnsLogProxy.getInstance().debug(TAG, "add to httpDnsStatList");
                    getIpsSyncTask.add(httpDnsStat3);
                    httpDnsStat3.type = HttpDnsConst.TYPE_SYNC_WAIT;
                    httpDnsStat = httpDnsStat3;
                    str2 = str3;
                    z2 = true;
                } else {
                    ArrayList arrayList = new ArrayList();
                    arrayList.add(str);
                    httpDnsStat = httpDnsStat3;
                    str2 = str3;
                    try {
                        GetIpsSyncTask getIpsSyncTask2 = new GetIpsSyncTask(arrayList, httpDnsStat3, jCurrentTimeMillis);
                        sExecutingGetIpSyncTaskMap.put(str2, getIpsSyncTask2);
                        getIpsSyncTask = getIpsSyncTask2;
                        z2 = false;
                    } catch (Throwable th) {
                        th = th;
                        throw th;
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                str2 = str3;
            }
        } else {
            httpDnsStat = httpDnsStat3;
            str2 = str3;
            ArrayList arrayList2 = new ArrayList();
            arrayList2.add(str);
            getIpsSyncTask = new GetIpsSyncTask(arrayList2, httpDnsStat, jCurrentTimeMillis);
            z2 = false;
        }
        if (z2) {
            synchronized (getIpsSyncTask) {
                try {
                    getIpsSyncTask.wait(getWaitTimeout(j));
                } catch (InterruptedException unused) {
                    HttpDnsLogProxy.getInstance().debug(TAG, "getIpsSyncTask merge request timeout");
                }
            }
            httpDnsStat2 = httpDnsStat;
        } else {
            try {
                HttpDnsThreadPool.submit(getIpsSyncTask).get(getWaitTimeout(j), TimeUnit.MILLISECONDS);
            } catch (Exception unused2) {
                HttpDnsLogProxy.getInstance().error(TAG, "network request timeout");
            }
            this.mSegmentLock.lock(str2);
            try {
                sExecutingGetIpSyncTaskMap.remove(str2);
                httpDnsStat2 = httpDnsStat;
                HttpDnsLogProxy.getInstance().debug(TAG, "getIpsSyncTask.httpDnsStat = %s", httpDnsStat2);
                for (HttpDnsStat httpDnsStat4 : getIpsSyncTask.httpDnsStatList) {
                    if (httpDnsStat4 != null) {
                        httpDnsStat4.iSuccess = httpDnsStat2.iSuccess;
                        httpDnsStat4.iRetCode = httpDnsStat2.iRetCode;
                    }
                }
                synchronized (getIpsSyncTask) {
                    getIpsSyncTask.notifyAll();
                }
            } finally {
                this.mSegmentLock.unlock(str2);
            }
        }
        httpDnsStat2.responseTime = System.currentTimeMillis() - jCurrentTimeMillis;
        String[] strArrFilterExpiredIps2 = filterExpiredIps(this.mHttpDnsItemMap.get(str), z);
        if (strArrFilterExpiredIps2 != null) {
            httpDnsStat2.ipListState = strArrFilterExpiredIps2.length > 0 ? 2 : 1;
            httpDnsStat2.reportHttpDnsStat();
            httpDnsHostsWithSource.setHosts(strArrFilterExpiredIps2);
            httpDnsHostsWithSource.setSource(HttpDnsSource.Net);
            HttpDnsLogProxy.getInstance().debug(TAG, "getHostWithSource from net, ips=%s", Arrays.toString(strArrFilterExpiredIps2));
            return httpDnsHostsWithSource;
        }
        httpDnsStat2.reportHttpDnsStat();
        httpDnsHostsWithSource.setHosts(new String[0]);
        httpDnsHostsWithSource.setSource(HttpDnsSource.None);
        HttpDnsLogProxy.getInstance().debug(TAG, "getHostWithSource finally, ips=%s", Arrays.toString(strArrFilterExpiredIps2));
        return httpDnsHostsWithSource;
    }

    @Override // com.huya.hyhttpdns.dns.HttpDnsBiz
    public synchronized boolean addHostsChangeListener(HttpDnsHostsChangeListener httpDnsHostsChangeListener) {
        if (!this.hostsChangeListeners.contains(httpDnsHostsChangeListener)) {
            ArrayList arrayList = new ArrayList(this.hostsChangeListeners.size() + 1);
            arrayList.addAll(this.hostsChangeListeners);
            arrayList.add(httpDnsHostsChangeListener);
            this.hostsChangeListeners = arrayList;
            HttpDnsLogProxy.getInstance().info(TAG, "add host change listener success");
            return true;
        }
        HttpDnsLogProxy.getInstance().info(TAG, "add host change listener failed");
        return false;
    }

    @Override // com.huya.hyhttpdns.dns.HttpDnsBiz
    public synchronized boolean removeHostsChangeListener(HttpDnsHostsChangeListener httpDnsHostsChangeListener) {
        HttpDnsLogProxy.getInstance().info(TAG, "remove host change listener");
        int iIndexOf = this.hostsChangeListeners.indexOf(httpDnsHostsChangeListener);
        if (iIndexOf == -1) {
            return false;
        }
        ArrayList arrayList = new ArrayList(this.hostsChangeListeners.size() - 1);
        arrayList.addAll(this.hostsChangeListeners.subList(0, iIndexOf));
        arrayList.addAll(this.hostsChangeListeners.subList(iIndexOf + 1, this.hostsChangeListeners.size()));
        this.hostsChangeListeners = arrayList;
        return true;
    }

    @Override // com.huya.hyhttpdns.dns.HttpDnsBiz
    public boolean removeIps(List<String> list) {
        if (list == null || list.isEmpty()) {
            return false;
        }
        Iterator<Map.Entry<String, HttpDnsItem>> it = this.mHttpDnsItemMap.entrySet().iterator();
        while (it.hasNext()) {
            removeIp(list, it.next().getValue());
        }
        this.mDiskCache.removeIpsFromDisk(list);
        updateDnsRegularly();
        return true;
    }

    void notifyHostsChange() {
        for (HttpDnsHostsChangeListener httpDnsHostsChangeListener : this.hostsChangeListeners) {
            if (httpDnsHostsChangeListener != null) {
                httpDnsHostsChangeListener.onHostsChange();
            }
        }
    }

    void reportStat(String str, String str2, Map<String, String> map, Map<String, Double> map2, Map<String, String> map3) {
        HttpDnsReportListener httpDnsReportListener = this.httpDnsreportListener;
        if (httpDnsReportListener == null) {
            HttpDnsLogProxy.getInstance().info(TAG, "report listener is null");
        } else {
            httpDnsReportListener.report(str, str2, map, map2, map3);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearExpiredItemRegularly() {
        HttpDnsLogProxy.getInstance().debug(TAG, "clearExpiredItemRegularly");
        long jCurrentTimeMillis = System.currentTimeMillis();
        Iterator<Map.Entry<String, HttpDnsItem>> it = this.mHttpDnsItemMap.entrySet().iterator();
        while (it.hasNext()) {
            if (jCurrentTimeMillis > it.next().getValue().getIExpireTime()) {
                it.remove();
            }
        }
        saveToDisk();
        this.mThreadHandler.removeMessages(1);
        this.mThreadHandler.sendEmptyMessageDelayed(1, 300000L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateDnsRegularly() {
        HttpDnsLogProxy.getInstance().debug(TAG, "updateDnsRegularly mUpdateDelayTime = %s", Long.valueOf(this.mUpdateDelayTime));
        getIpsFromNet(null, new DnsResultCallback() { // from class: com.huya.hyhttpdns.dns.HttpDns.3
            @Override // com.huya.hyhttpdns.dns.HttpDns.DnsResultCallback
            public void onResult(Map<String, HttpDnsItem> map) {
                HttpDns.this.saveHttpdnsResult(map);
            }
        });
        this.mThreadHandler.removeMessages(0);
        this.mThreadHandler.sendEmptyMessageDelayed(0, this.mUpdateDelayTime * 1000);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void loadDiskCache() {
        dispatch(new Runnable() { // from class: com.huya.hyhttpdns.dns.HttpDns.4
            @Override // java.lang.Runnable
            public void run() {
                QueryHttpDnsRsp queryHttpDnsRsp;
                HttpDns.this.mDiskCache.loadKeysFromDisk();
                if (HttpDns.this.mHttpDnsItemMap.size() == 0) {
                    String networkName = NetworkUtil.getNetworkName(HttpDns.this.mContext);
                    if ("none".equals(networkName) || (queryHttpDnsRsp = HttpDns.this.mDiskCache.get(HttpDns.this.getDiskCacheKey(networkName))) == null || queryHttpDnsRsp.getMDomain2Ip() == null) {
                        return;
                    }
                    HttpDns.this.mHttpDnsItemMap.putAll(queryHttpDnsRsp.getMDomain2Ip());
                    HttpDnsLogProxy.getInstance().debug(HttpDns.TAG, "loadDiskCache mHttpDnsItem = %s", HttpDns.this.mHttpDnsItemMap);
                    HttpDns.this.clearExpiredItemRegularly();
                }
            }
        });
    }

    private String[] filterExpiredIps(HttpDnsItem httpDnsItem, boolean z) {
        if (httpDnsItem == null) {
            return null;
        }
        if (z && isExpire(httpDnsItem.getIExpireTime())) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        if (httpDnsItem.getVIp() != null) {
            arrayList.addAll(httpDnsItem.getVIp());
        }
        if (httpDnsItem.getVIpv6() != null) {
            arrayList.addAll(httpDnsItem.getVIpv6());
        }
        return (String[]) arrayList.toArray(new String[0]);
    }

    private boolean isExpire(long j) {
        return System.currentTimeMillis() > j;
    }

    private boolean isIdleMode(Context context) {
        try {
            PowerManager powerManager = (PowerManager) context.getSystemService("power");
            if (Build.VERSION.SDK_INT >= 23 && powerManager != null) {
                return powerManager.isDeviceIdleMode();
            }
        } catch (Exception unused) {
        }
        return false;
    }

    private void getIpsFromNet(ArrayList<String> arrayList, DnsResultCallback dnsResultCallback) {
        if (isIdleMode(this.mContext)) {
            HttpDnsLogProxy.getInstance().info(TAG, "in Idle mode, skip");
        } else {
            HttpDnsThreadPool.execute(new DnsTask(this.mContext, arrayList != null ? new ArrayList(arrayList) : null, dnsResultCallback, false, null, getCacheWupIps()));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public List<String> getCacheWupIps() {
        HttpDnsItem httpDnsItem = this.mHttpDnsItemMap.get(DnsTask.DNS_HOST);
        if (httpDnsItem == null || isExpire(httpDnsItem.getIExpireTime())) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        if (httpDnsItem.getVIp() != null) {
            arrayList.addAll(httpDnsItem.getVIp());
        }
        if (httpDnsItem.getVIpv6() != null) {
            arrayList.addAll(httpDnsItem.getVIpv6());
        }
        return arrayList;
    }

    private boolean isIpAddress(String str) {
        if (str == null) {
            return false;
        }
        return this.PATTERN_IS_IP_ADDRESS.matcher(str).matches();
    }

    private void saveToDisk() {
        HttpDnsLogProxy.getInstance().debug(TAG, "saveToDisk mNeedTrrigleSave = %s", Boolean.valueOf(this.mNeedTriggleSave));
        if (this.mNeedTriggleSave) {
            this.mNeedTriggleSave = false;
            this.mThreadHandler.postDelayed(new Runnable() { // from class: com.huya.hyhttpdns.dns.HttpDns.5
                @Override // java.lang.Runnable
                public void run() {
                    String networkName = NetworkUtil.getNetworkName(HttpDns.this.mContext);
                    HttpDnsLogProxy.getInstance().debug(HttpDns.TAG, "real saveToDisk networkName = %s", networkName);
                    if (!"none".equals(networkName) && !HttpDns.this.mHttpDnsItemMap.isEmpty()) {
                        QueryHttpDnsRsp queryHttpDnsRsp = new QueryHttpDnsRsp();
                        queryHttpDnsRsp.setMDomain2Ip(HttpDns.this.mHttpDnsItemMap);
                        HttpDns.this.mDiskCache.put(HttpDns.this.getDiskCacheKey(networkName), queryHttpDnsRsp.toByteArray());
                    }
                    HttpDns.this.mNeedTriggleSave = true;
                }
            }, 500L);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getDiskCacheKey(String str) {
        if (this.mIsTestEnv) {
            str = String.format("%s_%s", "debug_", str);
        }
        if (!this.mIsOverSea) {
            return str;
        }
        return str + "_oversea";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void saveHttpdnsResult(Map<String, HttpDnsItem> map) {
        if (map == null) {
            return;
        }
        long jCurrentTimeMillis = System.currentTimeMillis();
        long j = 180;
        for (Map.Entry<String, HttpDnsItem> entry : map.entrySet()) {
            HttpDnsItem value = entry.getValue();
            String key = entry.getKey();
            long j2 = value.iExpireTime;
            if (j2 < j && j2 > 20) {
                j = j2;
            }
            value.iExpireTime = (value.iExpireTime * 1000) + jCurrentTimeMillis;
            this.mHttpDnsItemMap.put(key, value);
        }
        saveToDisk();
        this.mUpdateDelayTime = j;
    }

    private void removeIp(List<String> list, HttpDnsItem httpDnsItem) {
        ArrayList<String> vIp;
        if (httpDnsItem == null || (vIp = httpDnsItem.getVIp()) == null || vIp.isEmpty()) {
            return;
        }
        synchronized (vIp) {
            ArrayList<String> arrayList = new ArrayList<>(vIp);
            Iterator<String> it = arrayList.iterator();
            while (it.hasNext()) {
                String next = it.next();
                Iterator<String> it2 = list.iterator();
                while (true) {
                    if (it2.hasNext()) {
                        String next2 = it2.next();
                        if (next2.contains(":")) {
                            next2 = next2.substring(0, next2.indexOf(":"));
                        }
                        if (next != null && next.equals(next2)) {
                            it.remove();
                            break;
                        }
                    }
                }
            }
            httpDnsItem.vIp = arrayList;
        }
        ArrayList<String> vIpv6 = httpDnsItem.getVIpv6();
        if (vIpv6 == null || vIpv6.isEmpty()) {
            return;
        }
        synchronized (vIpv6) {
            ArrayList<String> arrayList2 = new ArrayList<>(vIpv6);
            Iterator<String> it3 = arrayList2.iterator();
            while (it3.hasNext()) {
                String next3 = it3.next();
                Iterator<String> it4 = list.iterator();
                while (true) {
                    if (it4.hasNext()) {
                        String next4 = it4.next();
                        if (next4.contains(":")) {
                            next4 = next4.substring(0, next4.indexOf(":"));
                        }
                        if (next3 != null && next3.equals(next4)) {
                            it3.remove();
                            break;
                        }
                    }
                }
            }
            httpDnsItem.vIpv6 = arrayList2;
        }
    }

    private class GetIpsSyncTask implements Runnable {
        ArrayList<String> domainNames;
        HttpDnsStat httpDnsStat;
        List<HttpDnsStat> httpDnsStatList = new ArrayList();
        long startTime;

        GetIpsSyncTask(ArrayList<String> arrayList, HttpDnsStat httpDnsStat, long j) {
            this.domainNames = arrayList;
            this.httpDnsStat = httpDnsStat;
            this.startTime = j;
        }

        void add(HttpDnsStat httpDnsStat) {
            this.httpDnsStatList.add(httpDnsStat);
        }

        @Override // java.lang.Runnable
        public void run() {
            HttpDnsLogProxy.getInstance().debug(HttpDns.TAG, "GetIpsSyncTask run  domainName = %s", this.domainNames.toString());
            new DnsTask(HttpDns.this.mContext, this.domainNames, new DnsResultCallback() { // from class: com.huya.hyhttpdns.dns.HttpDns.GetIpsSyncTask.1
                @Override // com.huya.hyhttpdns.dns.HttpDns.DnsResultCallback
                public void onResult(Map<String, HttpDnsItem> map) {
                    if (map == null || map.isEmpty()) {
                        HttpDnsLogProxy.getInstance().error(HttpDns.TAG, "GetIpsSyncTask result is empty");
                    } else {
                        HttpDnsLogProxy.getInstance().debug(HttpDns.TAG, "GetIpsSyncTask get from net HttpDnsItem %s = ", map.keySet().toString());
                    }
                    HttpDns.this.saveHttpdnsResult(map);
                }
            }, true, this.httpDnsStat, HttpDns.this.getCacheWupIps()).run();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dispatch(Runnable runnable) {
        if (Thread.currentThread() == sHandlerThread) {
            runnable.run();
        } else {
            this.mThreadHandler.post(runnable);
        }
    }
}
