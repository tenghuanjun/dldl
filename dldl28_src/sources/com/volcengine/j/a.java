package com.volcengine.j;

import android.app.Activity;
import android.app.Application;
import android.content.ComponentCallbacks2;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import com.volcengine.androidcloud.common.log.AcLog;
import com.volcengine.common.SDKContext;
import com.volcengine.common.contant.CommonConstants;
import com.volcengine.common.innerapi.AppStateService;
import com.volcengine.common.innerapi.MonitorService;
import com.volcengine.common.util.CompatConsumer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public final class a implements Application.ActivityLifecycleCallbacks, ComponentCallbacks2, AppStateService {
    private boolean d;
    private MonitorService f;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private int f1144a = 0;
    private int b = 0;
    private boolean c = false;
    private volatile boolean g = false;
    private final List<CompatConsumer<Boolean>> e = new ArrayList();

    private void a(Activity activity, boolean z) {
        AcLog.v("AppStateObserver", "postStatus: activity = [" + activity + "], isForeground = [" + z + "]");
        Iterator<CompatConsumer<Boolean>> it = this.e.iterator();
        while (it.hasNext()) {
            it.next().accept(Boolean.valueOf(z));
        }
        SDKContext.getMonitorService().reportOnlyEvent(z ? CommonConstants.event_onAppEnterForeground : CommonConstants.event_onAppEnterBackground);
    }

    private void a(String str, Object obj) {
        if (!this.g || this.f == null) {
            return;
        }
        HashMap map = new HashMap(4);
        map.put(CommonConstants.KEY_ACTIVITY_EVENT, str);
        map.put(CommonConstants.KEY_ACTIVITY_DATA, obj.toString());
        this.f.reportCategory(CommonConstants.event_activityState, map);
    }

    public void a(Context context) {
        if (this.d) {
            return;
        }
        Application application = (Application) context;
        application.registerActivityLifecycleCallbacks(this);
        application.registerComponentCallbacks(this);
        this.g = SDKContext.getConfigService().getConfigJson("monitor_config").optBoolean(MonitorService.APP_STATE_REPORT, false);
        this.f = SDKContext.getMonitorService();
        this.d = true;
    }

    @Override // com.volcengine.common.innerapi.AppStateService
    public boolean isAppForeground() {
        return !this.c;
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityCreated(Activity activity, Bundle bundle) {
        a("onActivityCreated", activity);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityDestroyed(Activity activity) {
        a("onActivityDestroyed", activity);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityPaused(Activity activity) {
        a("onActivityPaused", activity);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityResumed(Activity activity) {
        if (this.c) {
            this.c = false;
            a(activity, true);
        }
        a("onActivityResumed", activity);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        a("onActivitySaveInstanceState", activity);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStarted(Activity activity) {
        int i = this.b;
        if (i < 0) {
            this.b = i + 1;
        } else {
            this.f1144a++;
        }
        a("onActivityStarted", activity);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStopped(Activity activity) {
        if (activity.isChangingConfigurations()) {
            this.b--;
        } else {
            int i = this.f1144a - 1;
            this.f1144a = i;
            if (i <= 0) {
                this.c = true;
                a(activity, false);
            }
        }
        a("onActivityStopped", activity);
    }

    @Override // android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        SDKContext.getMonitorService().reportCategory(CommonConstants.event_onConfigurationChanged, Collections.singletonMap("orientation", Integer.valueOf(configuration.orientation)));
    }

    @Override // android.content.ComponentCallbacks
    public void onLowMemory() {
        AcLog.w("AppStateObserver", "onLowMemory");
        SDKContext.getMonitorService().reportOnlyEvent(CommonConstants.event_onLowMemory);
    }

    @Override // android.content.ComponentCallbacks2
    public void onTrimMemory(int i) {
        String str = (i == 5 || i == 10 || i == 15) ? "app正常运行，系统可能根据LRU缓存规则杀掉缓存的进程" : i != 20 ? (i == 40 || i == 60 || i == 80) ? "手机内存很低，系统开始杀app" : "" : "app的所有ui被隐藏";
        AcLog.w("AppStateObserver", "onTrimMemory: level:" + i + ", msg:" + str);
        HashMap map = new HashMap(3);
        map.put("level", Integer.valueOf(i));
        map.put(CommonConstants.KEY_MESSAGE, str);
        SDKContext.getMonitorService().reportCategory(CommonConstants.event_onTrimMemory, map);
    }

    @Override // com.volcengine.common.innerapi.AppStateService
    public void registerAppSwitchObserver(CompatConsumer<Boolean> compatConsumer) {
        if (this.e.contains(compatConsumer)) {
            return;
        }
        this.e.add(compatConsumer);
    }

    @Override // com.volcengine.common.innerapi.AppStateService
    public void reportAppState(boolean z) {
        this.g = z;
    }

    @Override // com.volcengine.common.innerapi.AppStateService
    public void unregisterAppSwitchObserver(CompatConsumer<Boolean> compatConsumer) {
        this.e.remove(compatConsumer);
    }
}
