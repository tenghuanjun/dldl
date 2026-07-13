package com.volcengine.common.sdkmonitor;

import android.text.TextUtils;
import com.volcengine.androidcloud.common.log.AcLog;
import com.volcengine.common.contant.InternalConstants;
import com.volcengine.common.innerapi.ISDKMonitor;
import com.volcengine.common.plugin.c;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes3.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final List<ISDKMonitor> f1126a;
    private Map<String, String> b;
    private int c;

    public a() {
        ArrayList arrayList = new ArrayList();
        this.f1126a = arrayList;
        arrayList.add(new SDKMonitorImpl());
    }

    private void a(String str) {
        ISDKMonitor iSDKMonitor;
        if (b(str) || (iSDKMonitor = (ISDKMonitor) c.c().loadClass(str, new Object[0])) == null) {
            return;
        }
        a(iSDKMonitor);
    }

    private boolean b(String str) {
        Iterator<ISDKMonitor> it = this.f1126a.iterator();
        while (it.hasNext()) {
            if (TextUtils.equals(it.next().getClass().getCanonicalName(), str)) {
                return true;
            }
        }
        return false;
    }

    void a(int i) {
        if (i != 1) {
            if (i == 2) {
                a(InternalConstants.SDKReporterImpl);
            }
            a(InternalConstants.SDKMonitorImpl);
        } else {
            a(InternalConstants.SDKReporterImpl);
        }
        boolean z = this.b != null;
        if (z) {
            Iterator<ISDKMonitor> it = this.f1126a.iterator();
            while (it.hasNext()) {
                it.next().init(this.c, this.b);
            }
        }
        AcLog.d("MonitorManager", "setEnabledMonitor: " + i + ", needInit: " + z);
    }

    public void a(int i, Map<String, String> map) {
        this.c = i;
        this.b = map;
        Iterator<ISDKMonitor> it = this.f1126a.iterator();
        while (it.hasNext()) {
            it.next().init(i, map);
        }
    }

    public void a(ISDKMonitor iSDKMonitor) {
        for (ISDKMonitor iSDKMonitor2 : this.f1126a) {
            if (iSDKMonitor2 == iSDKMonitor || iSDKMonitor2.getClass() == iSDKMonitor.getClass()) {
                return;
            }
        }
        this.f1126a.add(iSDKMonitor);
    }

    public void a(String str, JSONObject jSONObject) {
        Iterator<ISDKMonitor> it = this.f1126a.iterator();
        while (it.hasNext()) {
            it.next().monitorCommonLog(str, jSONObject);
        }
    }

    public void a(String str, JSONObject jSONObject, JSONObject jSONObject2, JSONObject jSONObject3) {
        Iterator<ISDKMonitor> it = this.f1126a.iterator();
        while (it.hasNext()) {
            it.next().monitorEvent(str, jSONObject, jSONObject2, jSONObject3);
        }
    }

    public void b(ISDKMonitor iSDKMonitor) {
        this.f1126a.remove(iSDKMonitor);
    }
}
