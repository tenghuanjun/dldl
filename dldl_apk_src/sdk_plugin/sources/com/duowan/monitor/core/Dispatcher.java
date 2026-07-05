package com.duowan.monitor.core;

import com.duowan.monitor.jce.MetricDetail;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public interface Dispatcher extends OnStatusChangeListener {
    void request(MetricDetail metricDetail);
}
