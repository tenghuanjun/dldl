package com.duowan.monitor;

import android.app.Activity;
import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.util.ArrayMap;
import com.duowan.monitor.collector.CpuCollector;
import com.duowan.monitor.collector.FpsCollector;
import com.duowan.monitor.collector.MemoryCollector;
import com.duowan.monitor.collector.NetworkTrafficCollector;
import com.duowan.monitor.collector.ReportFilter;
import com.duowan.monitor.core.ILog;
import com.duowan.monitor.core.MetricFilter;
import com.duowan.monitor.core.Monitor;
import com.duowan.monitor.core.OnStatusChangeListener;
import com.duowan.monitor.core.UserInfoProvider;
import com.duowan.monitor.core.WupWriter;
import com.duowan.monitor.jce.EUnit;
import com.duowan.monitor.jce.Metric;
import com.duowan.monitor.jce.MetricDetail;
import com.duowan.monitor.utility.MonitorLog;
import com.duowan.monitor.utility.StringUtil;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public final class MonitorSDK {
    private static final int CONFIG_CACHE_TIME = 300000;
    private static final String TAG = "MonitorSDK";
    private static BroadcastReceiver mNetworkReceiver = new BroadcastReceiver() { // from class: com.duowan.monitor.MonitorSDK.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            MonitorSDK.sMonitor.updateConfig();
        }
    };
    private static boolean sInited;
    private static Monitor sMonitor;
    private static ReportFilter sReportFilter;

    private MonitorSDK() {
    }

    public static synchronized void init(MonitorConfig monitorConfig) {
        checkConfig(monitorConfig);
        if (sInited) {
            return;
        }
        Application application = (Application) monitorConfig.context.getApplicationContext();
        Monitor monitor = new Monitor(application, monitorConfig.appId, monitorConfig.configUrl, 300000L, monitorConfig.listener);
        sMonitor = monitor;
        monitor.addListener("wupWriter", new WupWriter(monitorConfig.listener, monitorConfig.reportUrl));
        sMonitor.addListener("cpuCollector", new CpuCollector());
        sMonitor.addListener("fpsCollector", new FpsCollector());
        sMonitor.addListener("memoryCollector", new MemoryCollector());
        sMonitor.addListener("networkTrafficCollector", new NetworkTrafficCollector());
        ReportFilter reportFilter = new ReportFilter();
        sReportFilter = reportFilter;
        sMonitor.addListener("reportFilter", reportFilter);
        sMonitor.addFilter(sReportFilter);
        application.registerActivityLifecycleCallbacks(new MonitorActivityCallbacks());
        sInited = true;
    }

    public static void addListener(String str, OnStatusChangeListener onStatusChangeListener) {
        if (sInited) {
            sMonitor.addListener(str, onStatusChangeListener);
        }
    }

    public static void removeListener(String str) {
        if (sInited) {
            sMonitor.removeListener(str);
        }
    }

    public static void setConfigParams(Map<String, String> map) {
        if (sInited) {
            sMonitor.setConfigParams(map);
        }
    }

    public static void addFilter(MetricFilter metricFilter) {
        sMonitor.addFilter(metricFilter);
    }

    public static void removeFilter(MetricFilter metricFilter) {
        sMonitor.removeFilter(metricFilter);
    }

    public static void setLog(ILog iLog) {
        if (sInited) {
            MonitorLog.setLog(iLog);
        }
    }

    public static void request(Metric metric) {
        if (sInited) {
            sMonitor.request(metric);
        }
    }

    public static void request(MetricDetail metricDetail) {
        if (sInited) {
            sMonitor.request(metricDetail);
        }
    }

    public static Metric createMetric(String str, String str2, double d, EUnit eUnit) {
        if (sInited) {
            return sMonitor.createMetric(str, str2, d, eUnit);
        }
        return null;
    }

    public static MetricDetail createMetricDetail(String str, String str2) {
        if (sInited) {
            return sMonitor.createMetricDetail(str, str2);
        }
        return null;
    }

    private static void checkConfig(MonitorConfig monitorConfig) {
        if (monitorConfig == null) {
            throw new NullPointerException("config can't be null");
        }
        if (monitorConfig.context == null) {
            throw new NullPointerException("config.context can't be null");
        }
        if (StringUtil.isEmpty(monitorConfig.appId)) {
            throw new IllegalArgumentException("config.appId can't be empty");
        }
        if (StringUtil.isEmpty(monitorConfig.configUrl)) {
            throw new IllegalArgumentException("config.configUrl can't be empty");
        }
        if (StringUtil.isEmpty(monitorConfig.reportUrl)) {
            throw new IllegalArgumentException("config.reportUrl can't be empty");
        }
        if (monitorConfig.listener == null) {
            throw new NullPointerException("config.listener can't be null");
        }
    }

    private static class MonitorActivityCallbacks implements Application.ActivityLifecycleCallbacks {
        private int mCount;

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityCreated(Activity activity, Bundle bundle) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityDestroyed(Activity activity) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        }

        private int getStartedActivityCount() {
            Map map;
            int i = 0;
            try {
                Class<?> cls = Class.forName("android.app.ActivityThread");
                Object objInvoke = cls.getMethod("currentActivityThread", new Class[0]).invoke(null, new Object[0]);
                Field declaredField = cls.getDeclaredField("mActivities");
                declaredField.setAccessible(true);
                if (Build.VERSION.SDK_INT < 19) {
                    map = (HashMap) declaredField.get(objInvoke);
                } else {
                    map = (ArrayMap) declaredField.get(objInvoke);
                }
                for (Object obj : map.values()) {
                    Field declaredField2 = obj.getClass().getDeclaredField("stopped");
                    declaredField2.setAccessible(true);
                    if (!declaredField2.getBoolean(obj)) {
                        i++;
                    }
                }
            } catch (Exception e) {
                MonitorLog.e(MonitorSDK.TAG, "getStartedActivityCount fail", e);
            }
            return i;
        }

        public MonitorActivityCallbacks() {
            this.mCount = 0;
            int startedActivityCount = getStartedActivityCount();
            this.mCount = startedActivityCount;
            if (startedActivityCount > 0) {
                start();
            }
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStarted(Activity activity) {
            if (this.mCount == 0) {
                start();
            }
            this.mCount++;
        }

        private void start() {
            MonitorSDK.sMonitor.getContext().registerReceiver(MonitorSDK.mNetworkReceiver, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
            MonitorSDK.sMonitor.start();
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityResumed(Activity activity) {
            MonitorSDK.sMonitor.setPageName(activity.getClass().getSimpleName());
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityPaused(Activity activity) {
            MonitorSDK.sMonitor.setPageName("none");
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStopped(Activity activity) {
            int i = this.mCount - 1;
            this.mCount = i;
            if (i == 0) {
                stop();
            }
        }

        private void stop() {
            MonitorSDK.sMonitor.stop();
            MonitorSDK.sMonitor.getContext().unregisterReceiver(MonitorSDK.mNetworkReceiver);
        }
    }

    public static class MonitorConfig {
        String appId;
        String configUrl;
        Context context;
        UserInfoProvider listener;
        String reportUrl;

        public MonitorConfig(Context context, String str, String str2, String str3, UserInfoProvider userInfoProvider) {
            this.context = context;
            this.appId = str;
            this.configUrl = str2;
            this.reportUrl = str3;
            this.listener = userInfoProvider;
        }
    }
}
