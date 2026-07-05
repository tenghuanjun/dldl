package com.huya.ciku.apm.collector;

import android.text.TextUtils;
import com.duowan.monitor.MonitorSDK;
import com.duowan.monitor.jce.Dimension;
import com.duowan.monitor.jce.EUnit;
import com.duowan.monitor.jce.Metric;
import com.duowan.monitor.utility.StringUtil;
import java.util.ArrayList;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class WebViewCollector extends BaseCollector {
    public void reportWebviewLoadTime(int i, String str, int i2, int i3, String str2) {
        if (!isEnabled() || TextUtils.isEmpty(str)) {
            return;
        }
        String string = StringUtil.formatString(str);
        ArrayList<Dimension> arrayList = new ArrayList<>();
        arrayList.add(new Dimension("url", string));
        arrayList.add(new Dimension("type", str2));
        Metric metricCreateMetric = MonitorSDK.createMetric("webview", "load_time", i, EUnit.EUnit_Milliseconds);
        metricCreateMetric.vDimension = arrayList;
        metricCreateMetric.iSuccess = i2;
        metricCreateMetric.iRetCode = i3;
        MonitorSDK.request(metricCreateMetric);
    }

    public void reportWebviewDelayTime(String str, int i, String str2, int i2, int i3, String str3) {
        if (!isEnabled() || TextUtils.isEmpty(str2)) {
            return;
        }
        String string = StringUtil.formatString(str2);
        String str4 = String.format("delay_%s", str);
        ArrayList<Dimension> arrayList = new ArrayList<>();
        arrayList.add(new Dimension("url", string));
        arrayList.add(new Dimension("type", str3));
        Metric metricCreateMetric = MonitorSDK.createMetric("webview", str4, i, EUnit.EUnit_Milliseconds);
        metricCreateMetric.vDimension = arrayList;
        metricCreateMetric.iSuccess = i2;
        metricCreateMetric.iRetCode = i3;
        MonitorSDK.request(metricCreateMetric);
    }

    public void reportLocalWebviewLoadTime(int i, String str, int i2, int i3, String str2, String str3) {
        if (!isEnabled() || TextUtils.isEmpty(str2)) {
            return;
        }
        ArrayList<Dimension> arrayList = new ArrayList<>();
        arrayList.add(new Dimension("type", str2));
        arrayList.add(new Dimension("page", str3));
        Metric metricCreateMetric = MonitorSDK.createMetric("webview", "local_load_time", i, EUnit.EUnit_Milliseconds);
        metricCreateMetric.vDimension = arrayList;
        metricCreateMetric.iSuccess = i2;
        metricCreateMetric.iRetCode = i3;
        metricCreateMetric.sExtDesc = str;
        MonitorSDK.request(metricCreateMetric);
    }
}
