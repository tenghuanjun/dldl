package com.huya.live.service;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Process;
import android.text.TextUtils;
import com.duowan.auk.util.L;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class ServiceCenter {
    public static final String TAG = "ServiceCenter";
    private static volatile ServiceCenter sInstance;
    private Handler mHandler;
    private HandlerThread mInitThread;
    private Map<String, AbsService> mServices = new HashMap();
    private Map<String, String> mServicesMap = new HashMap();
    private final Object mServiceLock = new Object();

    public void addServicesMap() {
    }

    public void initServiceAsync() {
    }

    public void initServiceSync() {
    }

    private ServiceCenter() {
        addServicesMap();
    }

    public void initService(final Context context) {
        Handler handler;
        L.info(TAG, "ServiceCenter initService, start time=%d", Long.valueOf(System.currentTimeMillis()));
        if (Utils.isMainProcess(context)) {
            initServiceSync();
        }
        L.info(TAG, "ServiceCenter initService, initServiceSync time=%d", Long.valueOf(System.currentTimeMillis()));
        synchronized (this.mServiceLock) {
            HandlerThread handlerThread = new HandlerThread(TAG);
            this.mInitThread = handlerThread;
            handlerThread.start();
            handler = new Handler(this.mInitThread.getLooper());
            this.mHandler = handler;
        }
        handler.post(new Runnable() { // from class: com.huya.live.service.ServiceCenter.1
            @Override // java.lang.Runnable
            public void run() {
                String processName = Utils.getProcessName(Process.myPid());
                if (processName == null || !processName.equals(context.getPackageName())) {
                    return;
                }
                L.info(ServiceCenter.TAG, "ServiceCenter initService, initServiceAsync start time=%d", Long.valueOf(System.currentTimeMillis()));
                ServiceCenter.this.initServiceAsync();
                L.info(ServiceCenter.TAG, "ServiceCenter initService, initServiceAsync end time=%d", Long.valueOf(System.currentTimeMillis()));
            }
        });
    }

    public void asyncInit(Runnable runnable) {
        synchronized (this.mServiceLock) {
            if (this.mHandler != null) {
                this.mHandler.post(runnable);
            }
        }
    }

    public static ServiceCenter instance() {
        if (sInstance == null) {
            synchronized (ServiceCenter.class) {
                if (sInstance == null) {
                    sInstance = new ServiceCenter();
                }
            }
        }
        return sInstance;
    }

    public void addService(String str, AbsService absService) {
        if (str == null || absService == null) {
            return;
        }
        synchronized (this.mServiceLock) {
            if (this.mServices.containsKey(str)) {
                return;
            }
            this.mServices.put(str, absService);
        }
    }

    public <T> T getService(Class<T> cls) {
        if (cls == null) {
            return null;
        }
        T t = (T) getService(cls.getName());
        if (t != null) {
            return t;
        }
        L.error(TAG, "getService result == null service name = %s", cls.getName());
        return null;
    }

    public AbsService getService(String str) {
        AbsService absService;
        if (TextUtils.isEmpty(str)) {
            L.error(TAG, "getService serviceName == null");
            return null;
        }
        synchronized (this.mServiceLock) {
            absService = this.mServices.get(str);
            if (absService == null && this.mServicesMap.containsKey(str)) {
                try {
                    AbsService absService2 = (AbsService) Class.forName(this.mServicesMap.get(str)).newInstance();
                    absService2.onCreate();
                    addService(str, absService2);
                    absService = absService2;
                } catch (Exception e) {
                    L.error(TAG, "getService exception " + e.toString());
                }
            }
        }
        return absService;
    }

    public void removeService(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        synchronized (this.mServiceLock) {
            this.mServices.remove(str);
        }
    }

    public void removeService(Class cls) {
        if (cls == null) {
            return;
        }
        synchronized (this.mServiceLock) {
            this.mServices.remove(cls.getName());
        }
    }
}
