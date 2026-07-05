package com.duowan.monitor.core;

import android.content.Context;
import android.text.TextUtils;
import com.duowan.monitor.jce.Dimension;
import com.duowan.monitor.jce.EUnit;
import com.duowan.monitor.jce.Field;
import com.duowan.monitor.jce.Metric;
import com.duowan.monitor.jce.MetricDetail;
import com.duowan.monitor.utility.MonitorThread;
import com.duowan.monitor.utility.StringUtil;
import com.huya.mtp.hyns.stat.NSStatReporter;
import com.sqwan.bugless.util.FileUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public final class Monitor implements OnConfigListener {
    private static final String DEFAULT_APP = "huya";
    private static final String TAG = "Monitor";
    private MetricDetail instance;
    private final String mAppId;
    private final NetworkConfigLoader mConfigLoader;
    private Context mContext;
    private MetricFilter mFilter;
    private final UserInfoProvider mUserInfoProvider;
    private static final Pattern APP_ID_PATTERN = Pattern.compile("^[A-Za-z][_0-9A-Za-z]*$");
    private static final Pattern NAMESPACE_PATTERN = Pattern.compile("^[A-Za-z][._0-9A-Za-z]*$");
    private final List<Dispatcher> mDispatchers = new ArrayList();
    private final Map<String, OnStatusChangeListener> mListeners = new HashMap();
    private final MetricFilter mPageFilter = new MetricFilter() { // from class: com.duowan.monitor.core.Monitor.1
        @Override // com.duowan.monitor.core.MetricFilter
        public boolean doFilter(MetricDetail metricDetail) {
            Monitor monitor = Monitor.this;
            if (!monitor.matchNamespace(monitor.mAppId, "performance", metricDetail.sMetricName)) {
                return false;
            }
            if (metricDetail.vDimension == null) {
                metricDetail.vDimension = new ArrayList<>();
            }
            metricDetail.vDimension.add(new Dimension("page", Monitor.this.mPageName));
            return false;
        }
    };
    private boolean mStopped = true;
    private List<MetricFilter> mMetricFilterList = new ArrayList();
    private String mPageName = "none";

    public Monitor(Context context, String str, String str2, long j, UserInfoProvider userInfoProvider) {
        if (context == null) {
            throw new NullPointerException("context can't be nul");
        }
        if (!isValidAppId(str)) {
            throw new IllegalArgumentException("appId is invalid");
        }
        if (userInfoProvider == null) {
            throw new NullPointerException("userInfoProvider can't be null");
        }
        this.mContext = context;
        DeviceInfo.init(context);
        this.mAppId = str;
        this.mUserInfoProvider = userInfoProvider;
        this.mConfigLoader = new NetworkConfigLoader(this, str2, j);
    }

    public String getAppId() {
        return this.mAppId;
    }

    public Context getContext() {
        return this.mContext;
    }

    public void setPageName(String str) {
        this.mPageName = str;
    }

    public UserInfoProvider getUserInfoProvider() {
        return this.mUserInfoProvider;
    }

    public void setConfigParams(Map<String, String> map) {
        this.mConfigLoader.setParams(map);
    }

    public synchronized void addFilter(MetricFilter metricFilter) {
        if (this.mMetricFilterList.contains(metricFilter)) {
            return;
        }
        ArrayList arrayList = new ArrayList(this.mMetricFilterList.size() + 1);
        arrayList.addAll(this.mMetricFilterList);
        arrayList.add(metricFilter);
        this.mMetricFilterList = arrayList;
    }

    public synchronized void removeFilter(MetricFilter metricFilter) {
        int iIndexOf = this.mMetricFilterList.indexOf(metricFilter);
        if (iIndexOf != -1) {
            ArrayList arrayList = new ArrayList(this.mMetricFilterList.size() - 1);
            arrayList.addAll(this.mMetricFilterList.subList(0, iIndexOf));
            arrayList.addAll(this.mMetricFilterList.subList(iIndexOf + 1, this.mMetricFilterList.size()));
            this.mMetricFilterList = arrayList;
        }
    }

    public void addListener(final String str, final OnStatusChangeListener onStatusChangeListener) {
        MonitorThread.runOnMonitorThread(new Runnable() { // from class: com.duowan.monitor.core.Monitor.2
            @Override // java.lang.Runnable
            public void run() {
                Monitor.this.removeListener(str);
                Monitor.this.mListeners.put(str, onStatusChangeListener);
                if (onStatusChangeListener instanceof Dispatcher) {
                    Monitor.this.mDispatchers.add((Dispatcher) onStatusChangeListener);
                }
                if (!Monitor.this.mStopped) {
                    onStatusChangeListener.onStart();
                } else {
                    onStatusChangeListener.onStop();
                }
                JSONObject config = Monitor.this.mConfigLoader.getConfig();
                if (config != null) {
                    onStatusChangeListener.onConfig((JSONObject) config.opt(str));
                }
            }
        });
    }

    public void removeListener(final String str) {
        MonitorThread.runOnMonitorThread(new Runnable() { // from class: com.duowan.monitor.core.Monitor.3
            @Override // java.lang.Runnable
            public void run() {
                OnStatusChangeListener onStatusChangeListener = (OnStatusChangeListener) Monitor.this.mListeners.remove(str);
                if (onStatusChangeListener != null) {
                    Monitor.this.mDispatchers.remove(onStatusChangeListener);
                    onStatusChangeListener.onStop();
                }
            }
        });
    }

    private void notifyListeners(final JSONObject jSONObject) {
        MonitorThread.runOnMonitorThread(new Runnable() { // from class: com.duowan.monitor.core.Monitor.4
            @Override // java.lang.Runnable
            public void run() {
                for (Map.Entry entry : Monitor.this.mListeners.entrySet()) {
                    OnStatusChangeListener onStatusChangeListener = (OnStatusChangeListener) entry.getValue();
                    JSONObject jSONObject2 = jSONObject;
                    onStatusChangeListener.onConfig(jSONObject2 == null ? null : (JSONObject) jSONObject2.opt((String) entry.getKey()));
                }
            }
        });
    }

    public Metric createMetric(String str, String str2, double d, EUnit eUnit) {
        if (!isValidNamespace(str)) {
            throw new IllegalArgumentException("namespace is invalid");
        }
        if (StringUtil.isEmpty(str2)) {
            throw new IllegalArgumentException("metricName is empty");
        }
        Metric metric = new Metric();
        metric.sMetricName = createMetricName(this.mAppId, str, str2);
        metric.fValue = d;
        metric.eUnit = eUnit.value();
        metric.iTS = System.currentTimeMillis();
        return metric;
    }

    public MetricDetail createMetricDetail(String str, String str2) {
        if (!isValidNamespace(str)) {
            throw new IllegalArgumentException("namespace is invalid");
        }
        if (StringUtil.isEmpty(str2)) {
            throw new IllegalArgumentException("metricName is empty");
        }
        MetricDetail metricDetail = new MetricDetail();
        metricDetail.sMetricName = createMetricName(this.mAppId, str, str2);
        metricDetail.iTS = System.currentTimeMillis();
        return metricDetail;
    }

    public void request(final Metric metric) {
        if (metric == null) {
            return;
        }
        MonitorThread.runOnMonitorThread(new Runnable() { // from class: com.duowan.monitor.core.Monitor.5
            @Override // java.lang.Runnable
            public void run() {
                if (!Monitor.this.isValidNamespace(metric.sMetricName)) {
                    throw new IllegalArgumentException("metricName is invalid");
                }
                MetricDetail metricDetail = new MetricDetail();
                metricDetail.sMetricName = metric.sMetricName;
                metricDetail.iTS = metric.iTS;
                ArrayList<Dimension> arrayList = new ArrayList<>();
                if (metric.getVExLog() != null) {
                    arrayList.addAll(metric.getVExLog());
                }
                if (!TextUtils.isEmpty(metric.sExtDesc)) {
                    arrayList.add(new Dimension("extdesc", metric.sExtDesc));
                }
                ArrayList<Dimension> arrayList2 = new ArrayList<>();
                if (metric.vDimension != null) {
                    arrayList2.addAll(metric.vDimension);
                }
                arrayList2.add(new Dimension("success", metric.iSuccess + ""));
                arrayList2.add(new Dimension(NSStatReporter.NS_RETCODE, metric.iRetCode + ""));
                ArrayList<Field> arrayList3 = new ArrayList<>();
                if (metric.tStatsSet != null) {
                    arrayList3.add(new Field("_sum", metric.tStatsSet.getFSum()));
                    arrayList3.add(new Field("_samplecnt", metric.tStatsSet.getLSampleCnt()));
                } else {
                    arrayList3.add(new Field("value", metric.fValue));
                }
                metricDetail.vExLog = arrayList;
                metricDetail.vDimension = arrayList2;
                metricDetail.vFiled = arrayList3;
                Monitor.this.request(metricDetail);
            }
        });
    }

    public void request(final MetricDetail metricDetail) {
        if (metricDetail == null) {
            return;
        }
        MonitorThread.runOnMonitorThread(new Runnable() { // from class: com.duowan.monitor.core.Monitor.6
            @Override // java.lang.Runnable
            public void run() {
                if (metricDetail.vDimension != null) {
                    metricDetail.vDimension.removeAll(Collections.singleton(null));
                }
                if (Monitor.this.mPageFilter.doFilter(metricDetail)) {
                    return;
                }
                List list = Monitor.this.mMetricFilterList;
                if (list.size() > 0) {
                    for (int i = 0; i < list.size(); i++) {
                        if (((MetricFilter) list.get(i)).doFilter(metricDetail)) {
                            return;
                        }
                    }
                }
                if (Monitor.this.mDispatchers.size() == 1) {
                    ((Dispatcher) Monitor.this.mDispatchers.get(0)).request(metricDetail);
                    return;
                }
                Iterator it = Monitor.this.mDispatchers.iterator();
                while (it.hasNext()) {
                    ((Dispatcher) it.next()).request(metricDetail);
                }
            }
        });
    }

    public void updateConfig() {
        this.mConfigLoader.updateConfig();
    }

    public void start() {
        MonitorThread.runOnMonitorThread(new Runnable() { // from class: com.duowan.monitor.core.Monitor.7
            @Override // java.lang.Runnable
            public void run() {
                if (Monitor.this.mStopped) {
                    Monitor.this.mStopped = false;
                    Iterator it = Monitor.this.mListeners.values().iterator();
                    while (it.hasNext()) {
                        ((OnStatusChangeListener) it.next()).onStart();
                    }
                    Monitor.this.updateConfig();
                }
            }
        });
    }

    public void stop() {
        MonitorThread.runOnMonitorThread(new Runnable() { // from class: com.duowan.monitor.core.Monitor.8
            @Override // java.lang.Runnable
            public void run() {
                if (Monitor.this.mStopped) {
                    return;
                }
                Monitor.this.mStopped = true;
                Iterator it = Monitor.this.mListeners.values().iterator();
                while (it.hasNext()) {
                    ((OnStatusChangeListener) it.next()).onStop();
                }
            }
        });
    }

    private boolean isValidAppId(String str) {
        return str != null && APP_ID_PATTERN.matcher(str).matches();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isValidNamespace(String str) {
        return str != null && NAMESPACE_PATTERN.matcher(str).matches();
    }

    private String createMetricName(String str, String str2, String str3) {
        String str4;
        if ("huya".equals(str)) {
            str4 = "";
        } else {
            str4 = str + FileUtil.FILE_EXTENSION_SEPARATOR;
        }
        return str4 + str2 + FileUtil.FILE_EXTENSION_SEPARATOR + str3;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean matchNamespace(String str, String str2, String str3) {
        String str4;
        if (StringUtil.isEmpty(str2) || StringUtil.isEmpty(str3)) {
            return false;
        }
        if ("huya".equals(str)) {
            str4 = "";
        } else {
            str4 = str + FileUtil.FILE_EXTENSION_SEPARATOR;
        }
        return str3.startsWith(str4 + str2 + FileUtil.FILE_EXTENSION_SEPARATOR);
    }

    @Override // com.duowan.monitor.core.OnConfigListener
    public void onConfig(JSONObject jSONObject) {
        notifyListeners(jSONObject);
    }
}
