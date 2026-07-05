package com.huya.hyhttpdns.dns;

import android.content.Context;
import com.duowan.jce.wup.UniPacket;
import com.huya.hyhttpdns.dns.HttpDns;
import com.huya.hyhttpdns.jce.HttpDnsItem;
import com.huya.hyhttpdns.jce.QueryHttpDnsReq;
import com.huya.hyhttpdns.jce.QueryHttpDnsRsp;
import com.huya.mtp.hyns.Constants;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Vector;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;
import org.apache.http.conn.ConnectTimeoutException;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public final class DnsTask implements Runnable {
    private static final String TAG = "DnsTask";
    static HttpDnsConfig mConfig;
    private List<String> cacheWupIps;
    private Context context;
    private HttpDns.DnsResultCallback dnsResultCallback;
    private ArrayList<String> domainNames;
    private HttpDnsNetRequestStat httpDnsNetRequestStat;
    private HttpDnsStat httpDnsStat;
    private long ipStartTime;
    private boolean isSync;
    private long startTime;
    private static final String PREFIX_HTTPS = "https://";
    protected static String DNS_HOST = "cdn.wup.huya.com";
    private static String DEFAULT_URL_RELEASE = PREFIX_HTTPS + DNS_HOST;
    private static SegmentLock sSegmentLock = new SegmentLock();
    static boolean sIsTestEnv = false;
    static HttpDnsUserInfo sHttpDnsUserInfo = null;
    private static AtomicBoolean isQueried = new AtomicBoolean(false);
    private static final Map<String, DnsTask> sExecutingDnsTaskMap = new HashMap();
    protected static String[] BACKUP_IPS = {"106.55.80.57", "150.158.229.61", "212.64.99.102", "8.134.11.130"};
    List<String> ipAddresses = new ArrayList();
    private List<String> dnsCacheIp = new Vector();
    private boolean isStop = false;
    private int currentRetryCount = 0;
    private List<HttpDns.DnsResultCallback> mCallBackList = new ArrayList();

    static void setDnsHost(boolean z, boolean z2, String str, String str2) {
        if (str2 != null && !str2.isEmpty()) {
            DNS_HOST = str2;
            DEFAULT_URL_RELEASE = PREFIX_HTTPS + DNS_HOST;
            return;
        }
        if (z2) {
            if (z) {
                DNS_HOST = "testws.master.live";
            } else {
                DNS_HOST = "wsapi.master.live";
            }
        } else if (z) {
            if (str != null && !str.isEmpty()) {
                DNS_HOST = str;
            } else {
                DNS_HOST = "testws.va.huya.com";
            }
        } else {
            DNS_HOST = "cdn.wup.huya.com";
        }
        DEFAULT_URL_RELEASE = PREFIX_HTTPS + DNS_HOST;
    }

    public DnsTask(Context context, ArrayList<String> arrayList, HttpDns.DnsResultCallback dnsResultCallback, boolean z, HttpDnsStat httpDnsStat, List<String> list) {
        this.isSync = false;
        this.context = context;
        this.domainNames = arrayList == null ? new ArrayList<>() : arrayList;
        this.dnsResultCallback = dnsResultCallback;
        this.isSync = z;
        this.httpDnsStat = httpDnsStat;
        this.cacheWupIps = list;
        HttpDnsLogProxy.getInstance().debug(TAG, "DnsTask init");
    }

    private void initIpAdress() {
        if (sIsTestEnv) {
            this.ipAddresses.add(DEFAULT_URL_RELEASE);
            return;
        }
        try {
            InetAddress[] allByName = InetAddress.getAllByName(DNS_HOST);
            this.dnsCacheIp.clear();
            if (allByName != null) {
                this.httpDnsNetRequestStat.localDnsCostTime = System.currentTimeMillis() - this.startTime;
                for (InetAddress inetAddress : allByName) {
                    String hostAddress = inetAddress.getHostAddress();
                    HttpDnsLogProxy.getInstance().debug(TAG, "DNS_HOST = %s,  DNS_HOST ip: = %s", DNS_HOST, hostAddress);
                    this.ipAddresses.add(PREFIX_HTTPS + hostAddress);
                    this.dnsCacheIp.add(PREFIX_HTTPS + hostAddress);
                    this.httpDnsNetRequestStat.localDnsIPs.add(hostAddress);
                }
            } else {
                this.ipAddresses.add(DEFAULT_URL_RELEASE);
            }
            this.httpDnsNetRequestStat.hasLocalDnsIPs = true;
        } catch (SecurityException e) {
            HttpDnsLogProxy.getInstance().error(TAG, "SecurityException se = %s", e);
        } catch (UnknownHostException e2) {
            this.httpDnsNetRequestStat.hasLocalDnsIPs = false;
            this.httpDnsNetRequestStat.localDnsCostTime = System.currentTimeMillis() - this.startTime;
            HttpDnsLogProxy.getInstance().error(TAG, "UnknownHostException  e = %s", e2);
            if (this.dnsCacheIp.size() > 0) {
                this.ipAddresses.addAll(this.dnsCacheIp);
            } else {
                this.ipAddresses.add(DEFAULT_URL_RELEASE);
            }
        }
        List<String> list = this.cacheWupIps;
        if (list != null) {
            Iterator<String> it = list.iterator();
            while (it.hasNext()) {
                this.ipAddresses.add(PREFIX_HTTPS + it.next());
            }
        }
        int iNextInt = new Random().nextInt(BACKUP_IPS.length);
        this.ipAddresses.add(PREFIX_HTTPS + BACKUP_IPS[iNextInt]);
        this.ipAddresses.add(PREFIX_HTTPS + BACKUP_IPS[(iNextInt + 1) % BACKUP_IPS.length]);
    }

    private byte[] getBody() {
        QueryHttpDnsReq queryHttpDnsReq = new QueryHttpDnsReq();
        HttpDnsUserInfo httpDnsUserInfo = sHttpDnsUserInfo;
        if (httpDnsUserInfo != null) {
            queryHttpDnsReq.setLUid(httpDnsUserInfo.getUid());
        }
        HttpDnsConfig httpDnsConfig = mConfig;
        if (httpDnsConfig != null) {
            queryHttpDnsReq.setSUA(httpDnsConfig.ua);
            queryHttpDnsReq.setSAppSrc(mConfig.appSrc);
        }
        queryHttpDnsReq.setVDomain(this.domainNames);
        HashMap map = new HashMap();
        map.put("tReq", queryHttpDnsReq);
        UniPacket uniPacket = new UniPacket();
        uniPacket.useVersion3();
        uniPacket.setServantName("launch");
        uniPacket.setFuncName("queryHttpDns");
        for (String str : map.keySet()) {
            uniPacket.put(str, map.get(str));
        }
        return uniPacket.encode();
    }

    @Override // java.lang.Runnable
    public void run() {
        boolean z;
        HttpsURLConnection httpsURLConnection;
        int responseCode;
        String key = getKey();
        sSegmentLock.lock(key);
        try {
            DnsTask dnsTask = sExecutingDnsTaskMap.get(key);
            HttpDnsLogProxy.getInstance().debug(TAG, "DnsTask  key = %s dnsTask = %s", key, dnsTask);
            if (dnsTask != null) {
                HttpDnsLogProxy.getInstance().debug(TAG, "dnsTask is to be merged");
                if (this.httpDnsStat != null) {
                    this.httpDnsStat.type = HttpDnsConst.TYPE_SYNC_WAIT;
                }
                z = true;
            } else {
                if (this.httpDnsStat != null) {
                    this.httpDnsStat.type = HttpDnsConst.TYPE_NET;
                }
                sExecutingDnsTaskMap.put(key, this);
                z = false;
                dnsTask = this;
            }
            dnsTask.mCallBackList.add(this.dnsResultCallback);
            if (z) {
                if (this.isSync) {
                    synchronized (dnsTask) {
                        try {
                            try {
                                try {
                                    HttpDnsLogProxy.getInstance().debug(TAG, "dnsTask run: before wait");
                                    dnsTask.wait(10000L);
                                    HttpDnsLogProxy.getInstance().debug(TAG, "dnsTask run: after wait");
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                    return;
                                }
                            } catch (Throwable unused) {
                                return;
                            }
                        } finally {
                        }
                    }
                    return;
                }
                return;
            }
            this.httpDnsNetRequestStat = new HttpDnsNetRequestStat(this.domainNames.size() > 0 ? this.domainNames.get(0) : "allDomains");
            if (isQueried.compareAndSet(false, true)) {
                this.httpDnsNetRequestStat.isFirstTime = true;
            }
            this.httpDnsNetRequestStat.isNetworkAvailable = NetworkUtil.isNetworkAvailable(this.context);
            this.startTime = System.currentTimeMillis();
            initIpAdress();
            while (!this.isStop) {
                try {
                    try {
                        this.ipStartTime = System.currentTimeMillis();
                        final URL url = new URL(getUrl());
                        String str = isBackupIp(url.getHost()) ? HttpDnsConst.WUP_HOST : DNS_HOST;
                        httpsURLConnection = (HttpsURLConnection) url.openConnection();
                        httpsURLConnection.addRequestProperty("Host", str);
                        httpsURLConnection.setSSLSocketFactory(new TlsSniSocketFactory(httpsURLConnection));
                        httpsURLConnection.setHostnameVerifier(new HostnameVerifier() { // from class: com.huya.hyhttpdns.dns.DnsTask.1
                            @Override // javax.net.ssl.HostnameVerifier
                            public boolean verify(String str2, SSLSession sSLSession) {
                                if (url.getHost().equals(str2)) {
                                    return true;
                                }
                                return HttpsURLConnection.getDefaultHostnameVerifier().verify(str2, sSLSession);
                            }
                        });
                        httpsURLConnection.setConnectTimeout(10000);
                        httpsURLConnection.setReadTimeout(10000);
                        httpsURLConnection.setUseCaches(false);
                        httpsURLConnection.setDoInput(true);
                        httpsURLConnection.setRequestMethod("POST");
                        httpsURLConnection.setDoOutput(true);
                        httpsURLConnection.addRequestProperty("Content-Type", "application/octet-stream");
                        try {
                            DataOutputStream dataOutputStream = new DataOutputStream(httpsURLConnection.getOutputStream());
                            this.httpDnsNetRequestStat.connectCostTime = System.currentTimeMillis() - this.startTime;
                            this.httpDnsNetRequestStat.ipConnectCostTime = System.currentTimeMillis() - this.ipStartTime;
                            dataOutputStream.write(getBody());
                            dataOutputStream.close();
                            this.httpDnsNetRequestStat.sendCostTime = System.currentTimeMillis() - this.startTime;
                            this.httpDnsNetRequestStat.ipSendCostTime = System.currentTimeMillis() - this.ipStartTime;
                            responseCode = httpsURLConnection.getResponseCode();
                            HttpDnsLogProxy.getInstance().info(TAG, "statusCode " + responseCode);
                            setRetcode(responseCode);
                            this.httpDnsNetRequestStat.iRetCode = responseCode;
                        } catch (ArrayIndexOutOfBoundsException e2) {
                            throw new IOException(e2);
                        } catch (NullPointerException e3) {
                            throw new IOException(e3);
                        } catch (RuntimeException e4) {
                            throw new IOException(e4);
                        }
                    } catch (Exception e5) {
                        setRetcode(-5);
                        HttpDnsLogProxy.getInstance().error(TAG, "UnknownException e = %s", e5);
                        this.httpDnsNetRequestStat.error = e5.toString();
                        this.httpDnsNetRequestStat.iRetCode = -6;
                        attemptRetryOnException();
                    }
                } catch (SecurityException e6) {
                    setRetcode(-5);
                    HttpDnsLogProxy.getInstance().error(TAG, "SecurityException e = %s", e6);
                    this.httpDnsNetRequestStat.error = e6.toString();
                    this.httpDnsNetRequestStat.iRetCode = -5;
                    attemptRetryOnException();
                } catch (MalformedURLException e7) {
                    setRetcode(-3);
                    HttpDnsLogProxy.getInstance().error(TAG, "MalformedURLException e = %s", e7);
                    this.httpDnsNetRequestStat.error = e7.toString();
                    this.httpDnsNetRequestStat.iRetCode = -3;
                    attemptRetryOnException();
                } catch (SocketTimeoutException e8) {
                    HttpDnsLogProxy.getInstance().error(TAG, "SocketTimeoutException e = %s", e8);
                    setRetcode(-1);
                    this.httpDnsNetRequestStat.iRetCode = -1;
                    this.httpDnsNetRequestStat.error = e8.toString();
                    attemptRetryOnException();
                } catch (ConnectTimeoutException e9) {
                    setRetcode(-2);
                    HttpDnsLogProxy.getInstance().error(TAG, "ConnectTimeoutException e = %s", e9);
                    this.httpDnsNetRequestStat.error = e9.toString();
                    this.httpDnsNetRequestStat.iRetCode = -2;
                    attemptRetryOnException();
                } catch (IOException e10) {
                    HttpDnsStat httpDnsStat = this.httpDnsStat;
                    if (httpDnsStat != null && httpDnsStat.iRetCode == 0) {
                        setRetcode(-4);
                    }
                    if (this.httpDnsNetRequestStat.iRetCode == 0) {
                        this.httpDnsNetRequestStat.iRetCode = -4;
                    }
                    this.httpDnsNetRequestStat.error = e10.toString();
                    HttpDnsLogProxy.getInstance().error(TAG, "IOException  e = %s", e10);
                    attemptRetryOnException();
                }
                if (responseCode < 200 || responseCode > 299) {
                    try {
                        byte[] bytesFromInputStream = getBytesFromInputStream(httpsURLConnection.getErrorStream());
                        HttpDnsLogProxy.getInstance().error(TAG, "get from net code:" + responseCode + ", errString:" + new String(bytesFromInputStream));
                    } catch (Exception unused2) {
                        HttpDnsLogProxy.getInstance().error(TAG, "server rsp error & get err string failed");
                    }
                    throw new IOException();
                }
                try {
                    try {
                        if (decodeResponse(getBytesFromInputStream(httpsURLConnection.getInputStream()))) {
                            return;
                        }
                    } finally {
                        httpsURLConnection.disconnect();
                    }
                } catch (NullPointerException e11) {
                    throw new IOException(e11);
                }
            }
        } finally {
            sSegmentLock.unlock(key);
        }
    }

    private void setRetcode(int i) {
        HttpDnsStat httpDnsStat = this.httpDnsStat;
        if (httpDnsStat != null) {
            httpDnsStat.iRetCode = i;
        }
    }

    private String getKey() {
        Collections.sort(this.domainNames);
        return this.domainNames.toString();
    }

    private boolean decodeResponse(byte[] bArr) {
        QueryHttpDnsRsp queryHttpDnsRsp;
        try {
            this.httpDnsNetRequestStat.responseTime = System.currentTimeMillis() - this.startTime;
            this.httpDnsNetRequestStat.ipResponseTime = System.currentTimeMillis() - this.ipStartTime;
            UniPacket uniPacket = new UniPacket();
            uniPacket.decode(bArr);
            queryHttpDnsRsp = (QueryHttpDnsRsp) uniPacket.getByClass("tRsp", new QueryHttpDnsRsp());
        } catch (Exception e) {
            HttpDnsLogProxy.getInstance().error(TAG, "UniPacket decode error = %s", e);
            this.httpDnsNetRequestStat.error = e.toString();
            deliverResult(null);
            this.isStop = true;
        }
        if (queryHttpDnsRsp == null) {
            deliverResult(null);
            return true;
        }
        Map<String, HttpDnsItem> mDomain2Ip = queryHttpDnsRsp.getMDomain2Ip();
        if (mDomain2Ip == null) {
            deliverResult(null);
            return true;
        }
        HttpDnsLogProxy.getInstance().debug(TAG, "from net queryHttpDnsRsp = " + queryHttpDnsRsp);
        this.httpDnsNetRequestStat.iSuccess = 0;
        if (this.httpDnsStat != null) {
            this.httpDnsStat.iSuccess = 0;
        }
        deliverResult(mDomain2Ip);
        HttpDns.getInstance().notifyHostsChange();
        this.isStop = true;
        return false;
    }

    private void deliverResult(Map<String, HttpDnsItem> map) {
        this.httpDnsNetRequestStat.responseTime = System.currentTimeMillis() - this.startTime;
        this.httpDnsNetRequestStat.currentRetryCount = this.currentRetryCount;
        String key = getKey();
        HttpDnsLogProxy.getInstance().debug(TAG, "deliverResult DnsTask  key = %s dnsTask = %s", getKey(), this);
        sSegmentLock.lock(key);
        try {
            sExecutingDnsTaskMap.remove(key);
            for (HttpDns.DnsResultCallback dnsResultCallback : this.mCallBackList) {
                if (dnsResultCallback != null) {
                    dnsResultCallback.onResult(map);
                }
            }
            synchronized (this) {
                notifyAll();
            }
            if (map != null) {
                HttpDnsItem httpDnsItem = map.get(Constants.NATIONAL_LONG_LINK_HOST);
                if (httpDnsItem != null) {
                    this.httpDnsNetRequestStat.longIPListState = empty(httpDnsItem.getVIp()) ? 1 : 2;
                }
                HttpDnsItem httpDnsItem2 = map.get("cdn.wup.huya.com");
                if (httpDnsItem2 != null) {
                    this.httpDnsNetRequestStat.shortIPListState = empty(httpDnsItem2.getVIp()) ? 1 : 2;
                }
            }
            this.httpDnsNetRequestStat.reportNetRequestStat();
        } finally {
            sSegmentLock.unlock(key);
        }
    }

    private byte[] getBytesFromInputStream(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[1024];
        while (true) {
            int i = inputStream.read(bArr);
            if (i != -1) {
                byteArrayOutputStream.write(bArr, 0, i);
            } else {
                return byteArrayOutputStream.toByteArray();
            }
        }
    }

    private String getUrl() {
        String str = this.ipAddresses.get(this.currentRetryCount % this.ipAddresses.size());
        HttpDnsLogProxy.getInstance().info(TAG, "getUrl ipAddresses " + str);
        this.httpDnsNetRequestStat.currentIp = str;
        return str;
    }

    private void attemptRetryOnException() {
        this.currentRetryCount++;
        HttpDnsLogProxy.getInstance().debug(TAG, "attemptRetryOnException currentRetryCount = %d", Integer.valueOf(this.currentRetryCount));
        if (this.currentRetryCount >= this.ipAddresses.size()) {
            long jCurrentTimeMillis = System.currentTimeMillis();
            this.httpDnsNetRequestStat.responseTime = jCurrentTimeMillis - this.startTime;
            this.httpDnsNetRequestStat.ipResponseTime = jCurrentTimeMillis - this.ipStartTime;
            deliverResult(null);
            this.isStop = true;
        }
    }

    private boolean isBackupIp(String str) {
        for (String str2 : BACKUP_IPS) {
            if (str2.equals(str)) {
                return true;
            }
        }
        return false;
    }

    boolean empty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }
}
