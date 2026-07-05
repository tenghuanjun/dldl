package com.sqwan.base;

import com.sqwan.common.eventbus.OnActivityResultEvent;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class EventDispatcher {
    private static final EventDispatcher ourInstance = new EventDispatcher();
    public List<ActivityResultListener> activityResultListeners = new ArrayList();

    public static EventDispatcher getInstance() {
        return ourInstance;
    }

    private EventDispatcher() {
    }

    public void dispatcherActivityResultListener(OnActivityResultEvent onActivityResultEvent) {
        for (ActivityResultListener activityResultListener : this.activityResultListeners) {
            if (activityResultListener != null) {
                activityResultListener.onResult(onActivityResultEvent);
            }
        }
    }

    public void addActivityResultListener(ActivityResultListener activityResultListener) {
        this.activityResultListeners.add(activityResultListener);
    }

    public void removeActivityResultListener(ActivityResultListener activityResultListener) {
        this.activityResultListeners.remove(activityResultListener);
    }

    public void clearActivityResultListener() {
        this.activityResultListeners.clear();
    }

    public void clear() {
        clearActivityResultListener();
    }
}
