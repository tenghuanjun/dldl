package com.huya.ciku.apm.util;

import android.os.Build;
import android.os.SystemClock;
import com.duowan.auk.util.L;
import com.duowan.monitor.jce.Dimension;
import com.duowan.monitor.jce.Field;
import com.duowan.monitor.jce.MetricDetail;
import com.huya.ciku.apm.MonitorCenter;
import java.util.ArrayList;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public enum LaunchTimeReport {
    INSTANCE;

    private static final String TAG = LaunchTimeReport.class.getSimpleName();
    private long timeStart = 0;
    private long timeEnd = 0;
    private long timeApp = 0;
    private long timeSplash = 0;
    private long timeLogin = 0;
    private long timeMainActivity = 0;
    private long timeMainFragment = 0;

    LaunchTimeReport() {
    }

    public static LaunchTimeReport getInstance() {
        return INSTANCE;
    }

    public void setTimeApp() {
        if (this.timeApp == 0) {
            long jUptimeMillis = SystemClock.uptimeMillis();
            this.timeApp = jUptimeMillis;
            this.timeStart = jUptimeMillis;
            return;
        }
        this.timeApp = SystemClock.uptimeMillis() - this.timeApp;
    }

    public void setTimeSplash() {
        if (this.timeSplash == 0) {
            this.timeSplash = SystemClock.uptimeMillis();
        } else {
            this.timeSplash = SystemClock.uptimeMillis() - this.timeSplash;
        }
    }

    public void setTimeLogin() {
        if (this.timeLogin == 0) {
            this.timeLogin = SystemClock.uptimeMillis();
        } else {
            this.timeLogin = SystemClock.uptimeMillis() - this.timeLogin;
        }
    }

    public void setTimeMainActivity() {
        if (this.timeMainActivity == 0) {
            this.timeMainActivity = SystemClock.uptimeMillis();
        } else {
            this.timeMainActivity = SystemClock.uptimeMillis() - this.timeMainActivity;
        }
    }

    public void setTimeMainFragment() {
        if (this.timeMainFragment == 0) {
            this.timeMainFragment = SystemClock.uptimeMillis();
        } else {
            this.timeMainFragment = SystemClock.uptimeMillis() - this.timeMainFragment;
            this.timeEnd = SystemClock.uptimeMillis();
        }
    }

    public void reportLaunchTime(String str, String str2, String str3) {
        long j;
        ArrayList<Dimension> arrayList = new ArrayList<>();
        arrayList.add(new Dimension("version", str));
        arrayList.add(new Dimension("platform", str2));
        arrayList.add(new Dimension("uid", str3));
        arrayList.add(new Dimension("device", Build.MODEL.toLowerCase() + ""));
        MetricDetail metricDetail = new MetricDetail();
        metricDetail.setITS(System.currentTimeMillis());
        metricDetail.setSMetricName("show.apm.app.launch");
        ArrayList<Field> arrayList2 = new ArrayList<>();
        long j2 = this.timeApp;
        if (j2 > 0) {
            arrayList2.add(new Field("time_app", j2));
        }
        long j3 = this.timeSplash;
        if (j3 > 0) {
            arrayList2.add(new Field("time_splash", j3));
        }
        long j4 = this.timeLogin;
        if (j4 > 0) {
            arrayList2.add(new Field("time_login", j4));
        }
        long j5 = this.timeMainActivity;
        if (j5 > 0) {
            arrayList2.add(new Field("time_main_activity", j5));
        }
        long j6 = this.timeMainFragment;
        if (j6 > 0) {
            arrayList2.add(new Field("time_main_fragment", j6));
        }
        long j7 = this.timeApp;
        if (j7 > 0) {
            j = j7 + this.timeSplash + this.timeLogin + this.timeMainActivity + this.timeMainFragment;
            arrayList2.add(new Field("time_total", j));
        } else {
            j = 0;
        }
        metricDetail.setVFiled(arrayList2);
        metricDetail.setVDimension(arrayList);
        MonitorCenter.getInstance().request(metricDetail);
        long j8 = this.timeStart;
        L.info(TAG, "reportLaunchTime total_sum:%s,total:%s,app:%s,splash:%s,login:%s,main_activity:%s,main_fragment:%s", Long.valueOf(j), Long.valueOf(j8 > 0 ? this.timeEnd - j8 : 0L), Long.valueOf(this.timeApp), Long.valueOf(this.timeSplash), Long.valueOf(this.timeLogin), Long.valueOf(this.timeMainActivity), Long.valueOf(this.timeMainFragment));
        this.timeStart = 0L;
        this.timeEnd = 0L;
        this.timeApp = 0L;
        this.timeSplash = 0L;
        this.timeLogin = 0L;
        this.timeMainActivity = 0L;
        this.timeMainFragment = 0L;
    }
}
