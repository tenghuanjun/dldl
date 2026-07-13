package com.bytedance.framwork.core.sdkmonitor;

import android.text.TextUtils;
import com.bytedance.framwork.core.sdklib.thread.AsyncEventManager;
import java.util.Iterator;
import java.util.LinkedList;

/* JADX INFO: loaded from: classes2.dex */
public class CacheData {
    private boolean mHasHandleCache;
    private final LinkedList<ServiceMonitorData> mServiceMonitorData = new LinkedList<>();
    private final LinkedList<CommonLogData> mCommonLogData = new LinkedList<>();
    private final LinkedList<ApiData> mApiData = new LinkedList<>();
    private int MAX_LIMIT_SIZE = 200;

    /* JADX INFO: Access modifiers changed from: private */
    public void handleApiData(SDKMonitor sDKMonitor, ApiData apiData) {
        if (apiData == null || TextUtils.isEmpty(apiData.apiType)) {
            return;
        }
        if (apiData.apiType.equals(MonitorConstants.MONITOR_TYPE_API_ERROR)) {
            sDKMonitor.monitorApiError(apiData.duration, apiData.sendTime, apiData.sendUrl, apiData.sendIp, apiData.traceCode, apiData.status, apiData.extJson);
        } else if (apiData.apiType.equals(MonitorConstants.MONITOR_TYPE_API_ALL)) {
            sDKMonitor.monitorSLA(apiData.duration, apiData.sendTime, apiData.sendUrl, apiData.sendIp, apiData.traceCode, apiData.status, apiData.extJson);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleCommonLogData(SDKMonitor sDKMonitor, CommonLogData commonLogData) {
        if (commonLogData == null) {
            return;
        }
        sDKMonitor.monitorCommonLogInternal(commonLogData.logType, commonLogData.logData, commonLogData.timestamp);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleServiceMonitorData(SDKMonitor sDKMonitor, ServiceMonitorData serviceMonitorData) {
        if (serviceMonitorData == null || TextUtils.isEmpty(serviceMonitorData.serviceName)) {
            return;
        }
        sDKMonitor.monitorService(serviceMonitorData.serviceName, serviceMonitorData.status, serviceMonitorData.value, serviceMonitorData.category, serviceMonitorData.metric, serviceMonitorData.extrJson, serviceMonitorData.timestamp);
    }

    public void handleCacheData(final SDKMonitor sDKMonitor) {
        if (this.mHasHandleCache) {
            return;
        }
        this.mHasHandleCache = true;
        AsyncEventManager.getInstance().post(new Runnable() { // from class: com.bytedance.framwork.core.sdkmonitor.CacheData.1
            @Override // java.lang.Runnable
            public void run() {
                LinkedList linkedList;
                LinkedList linkedList2;
                LinkedList linkedList3;
                try {
                    synchronized (CacheData.this.mServiceMonitorData) {
                        linkedList = new LinkedList(CacheData.this.mServiceMonitorData);
                        CacheData.this.mServiceMonitorData.clear();
                    }
                    Iterator it = linkedList.iterator();
                    while (it.hasNext()) {
                        CacheData.this.handleServiceMonitorData(sDKMonitor, (ServiceMonitorData) it.next());
                    }
                    synchronized (CacheData.this.mCommonLogData) {
                        linkedList2 = new LinkedList(CacheData.this.mCommonLogData);
                        CacheData.this.mCommonLogData.clear();
                    }
                    Iterator it2 = linkedList2.iterator();
                    while (it2.hasNext()) {
                        CacheData.this.handleCommonLogData(sDKMonitor, (CommonLogData) it2.next());
                    }
                    synchronized (CacheData.this.mApiData) {
                        linkedList3 = new LinkedList(CacheData.this.mApiData);
                        CacheData.this.mApiData.clear();
                    }
                    Iterator it3 = linkedList3.iterator();
                    while (it3.hasNext()) {
                        CacheData.this.handleApiData(sDKMonitor, (ApiData) it3.next());
                    }
                } catch (Throwable unused) {
                }
            }
        });
    }

    public void insertApiData(ApiData apiData) {
        if (apiData == null) {
            return;
        }
        synchronized (this.mApiData) {
            if (this.mApiData.size() > this.MAX_LIMIT_SIZE) {
                this.mApiData.poll();
            }
            this.mApiData.add(apiData);
        }
    }

    public void insertCommonLogData(CommonLogData commonLogData) {
        if (commonLogData == null) {
            return;
        }
        synchronized (this.mCommonLogData) {
            if (this.mCommonLogData.size() > this.MAX_LIMIT_SIZE) {
                this.mCommonLogData.poll();
            }
            this.mCommonLogData.add(commonLogData);
        }
    }

    public void insertServiceMonitorData(ServiceMonitorData serviceMonitorData) {
        if (serviceMonitorData == null) {
            return;
        }
        synchronized (this.mServiceMonitorData) {
            if (this.mServiceMonitorData.size() > this.MAX_LIMIT_SIZE) {
                this.mServiceMonitorData.poll();
            }
            this.mServiceMonitorData.add(serviceMonitorData);
        }
    }
}
