package com.sq.tools.network.httpdns;

import android.content.Context;
import com.sq.tools.network.httpdns.callback.IDnsRequestListener;
import com.sq.tools.network.httpdns.data.DnsServerData;
import com.sq.tools.network.httpdns.dns.HttpDnsCache;
import com.sq.tools.network.httpdns.dns.HttpDnsProxy;
import com.sq.tools.network.httpdns.log.HttpDnsLog;
import com.sq.tools.network.httpdns.network.HttpDnsRequestManager;
import com.sq.tools.network.httpdns.network.IGetJsonRequest;
import com.sq.tools.network.httpdns.third.IThirdDns;
import com.sq.tools.report.exception.IExceptionReporter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class SqHttpDns {
    private static SqHttpDns sInstance;
    private IDnsRequestListener dnsRequestListener;
    private DnsServerData dnsServerData;
    private SqHttpDnsConfig httpDnsConfig;
    private Context mContext;
    private IExceptionReporter mExceptionReporter;
    private Executor mExecutor;
    private Map<String, Boolean> mUserConsent;
    private ArrayList<String> preResolveHosts;
    private Map<String, String> requestMap;
    private int timeout;
    private boolean mIsInitialized = false;
    private boolean mGlobalEnable = true;
    private ArrayList<String> protectHosts = new ArrayList<>();
    private ArrayList<String> protectMainHosts = new ArrayList<>();

    public static SqHttpDns getInstance() {
        if (sInstance == null) {
            synchronized (SqHttpDns.class) {
                if (sInstance == null) {
                    sInstance = new SqHttpDns();
                }
            }
        }
        return sInstance;
    }

    public synchronized void init(Context context, SqHttpDnsConfig config, Map<String, String> requestMap, IGetJsonRequest request) {
        init(context, config, requestMap, request, null);
    }

    public synchronized void init(Context context, SqHttpDnsConfig config, Map<String, String> requestMap, IGetJsonRequest request, IThirdDns thirdDns) {
        if (!this.mIsInitialized) {
            if (requestMap == null || requestMap.isEmpty()) {
                throw new IllegalArgumentException("Http Dns必须要传递请求时所需参数");
            }
            Context applicationContext = context.getApplicationContext();
            this.httpDnsConfig = config;
            HttpDnsRequestManager.getInstance().setGetJsonRequest(request);
            HttpDnsRequestManager.getInstance().setThirdDns(thirdDns);
            this.mContext = applicationContext;
            this.timeout = config.timeout;
            this.requestMap = requestMap;
            this.dnsServerData = HttpDnsCache.getHttpDnsServerFromCache(this.mContext);
            this.mIsInitialized = true;
        }
    }

    public boolean hasInitialized() {
        return this.mIsInitialized;
    }

    public boolean isGlobalEnable() {
        return this.mGlobalEnable;
    }

    public void setGlobalEnable(boolean enable) {
        this.mGlobalEnable = enable;
    }

    public void setDnsRequestListener(IDnsRequestListener listener) {
        this.dnsRequestListener = listener;
    }

    public void pullDnsConfig() {
        if (!this.mIsInitialized) {
            HttpDnsLog.e("未初始化, 无法拉取配置");
        } else if (this.mGlobalEnable) {
            requestDnsServer(this.requestMap);
        } else {
            HttpDnsLog.e("全局禁用dns, 无法拉取配置");
        }
    }

    public void setPreResolveHosts(ArrayList<String> preResolveHosts) {
        this.preResolveHosts = preResolveHosts;
    }

    public SqHttpDnsConfig getHttpDnsConfig() {
        return this.httpDnsConfig;
    }

    public ArrayList<String> getPreResolveHosts() {
        return this.preResolveHosts;
    }

    public int getTimeout() {
        return this.timeout;
    }

    private void requestDnsServer(Map<String, String> requestMap) {
        HttpDnsRequestManager.getInstance().requestDnsServer(this.mContext, requestMap, this.dnsRequestListener);
    }

    public void refreshDnsSever(DnsServerData dnsServerData) {
        if (!this.mIsInitialized) {
            HttpDnsLog.e("未初始化, 无法刷新配置");
        } else {
            this.dnsServerData = dnsServerData;
        }
    }

    public boolean isDnsEnable() {
        DnsServerData dnsServerData;
        return this.mGlobalEnable && this.mIsInitialized && (dnsServerData = this.dnsServerData) != null && dnsServerData.isHttpDns();
    }

    public boolean reportNetStat() {
        DnsServerData dnsServerData;
        return this.mGlobalEnable && this.mIsInitialized && (dnsServerData = this.dnsServerData) != null && dnsServerData.isReportNetStat();
    }

    public List<String> getReportBlackList() {
        if (!this.mGlobalEnable) {
            return Collections.emptyList();
        }
        if (!this.mIsInitialized) {
            return Collections.emptyList();
        }
        DnsServerData dnsServerData = this.dnsServerData;
        return dnsServerData != null ? dnsServerData.getReportNetStatBlacklist() : Collections.emptyList();
    }

    public boolean webViewDnsEnable() {
        DnsServerData dnsServerData;
        return this.mGlobalEnable && this.mIsInitialized && (dnsServerData = this.dnsServerData) != null && dnsServerData.isWebViewDns();
    }

    public boolean reportWebviewNetStat() {
        DnsServerData dnsServerData;
        return this.mGlobalEnable && this.mIsInitialized && (dnsServerData = this.dnsServerData) != null && dnsServerData.isReportWebviewNetStat();
    }

    public boolean isGlobalIpV6Enable() {
        return isIpV6Enable() || isWebViewIpV6Enable();
    }

    public boolean isIpV6Enable() {
        DnsServerData dnsServerData;
        return this.mGlobalEnable && this.mIsInitialized && (dnsServerData = this.dnsServerData) != null && dnsServerData.isIpV6Enable();
    }

    public boolean isWebViewIpV6Enable() {
        DnsServerData dnsServerData;
        return this.mGlobalEnable && this.mIsInitialized && (dnsServerData = this.dnsServerData) != null && dnsServerData.isWebViewIpV6Enable();
    }

    public String getIpByHost(String host, List<String> exclude, boolean ipv6) {
        return (this.mGlobalEnable && this.mIsInitialized && isDnsEnable()) ? HttpDnsProxy.getIpByHost(this.mContext, host, exclude, ipv6, this.dnsRequestListener) : "";
    }

    @Deprecated
    public String getIpByHost(String host, List<String> exclude) {
        return getIpByHost(host, exclude, false);
    }

    @Deprecated
    public String getIpByHost(String host) {
        return getIpByHost(host, null);
    }

    public void setProtectHosts(ArrayList<String> protectHosts) {
        if (protectHosts == null || protectHosts.isEmpty()) {
            return;
        }
        this.protectHosts = protectHosts;
    }

    public void addProtectHost(String host) {
        if (this.protectHosts.contains(host)) {
            return;
        }
        this.protectHosts.add(host);
    }

    public ArrayList<String> getProtectHosts() {
        return this.protectHosts;
    }

    public ArrayList<String> getProtectMainHosts() {
        return this.protectMainHosts;
    }

    public void addProtectMainHost(String mainHost) {
        if (this.protectMainHosts.contains(mainHost)) {
            return;
        }
        this.protectMainHosts.add(mainHost);
    }

    public void clearDnsServer() {
        if (!this.mGlobalEnable) {
            HttpDnsLog.e("全局禁用dns, 无法清空配置缓存");
        } else if (!this.mIsInitialized) {
            HttpDnsLog.e("未初始化, 无法清空配置缓存");
        } else {
            HttpDnsCache.clearHttpDnsServerData(this.mContext);
        }
    }

    public void setExecutor(Executor executor) {
        this.mExecutor = executor;
    }

    public void executeAsync(Runnable runnable) {
        if (runnable == null) {
            return;
        }
        if (this.mExecutor == null) {
            this.mExecutor = Executors.newSingleThreadExecutor();
        }
        this.mExecutor.execute(runnable);
    }

    public IExceptionReporter getExceptionReporter() {
        return this.mExceptionReporter;
    }

    public SqHttpDns setExceptionReporter(IExceptionReporter exceptionReporter) {
        this.mExceptionReporter = exceptionReporter;
        return this;
    }

    public Context getContext() {
        return this.mContext;
    }

    public Map<String, Boolean> getUserConsent() {
        return this.mUserConsent;
    }

    public void setUserConsent(Map<String, Boolean> userConsent) {
        this.mUserConsent = userConsent;
    }
}
