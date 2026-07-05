package com.huya.live.module;

import android.app.Activity;
import android.app.Fragment;
import android.arch.lifecycle.GenericLifecycleObserver;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;
import android.view.View;
import com.duowan.auk.util.L;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class SModule {
    public static final String TAG = "ShareModule";
    private ConcurrentHashMap<String, Object> mModuleList;

    private static final class SModuleHolder {
        static SModule sInstance = new SModule();

        private SModuleHolder() {
        }
    }

    public static SModule instance() {
        return SModuleHolder.sInstance;
    }

    private SModule() {
        this.mModuleList = new ConcurrentHashMap<>();
    }

    public <T> T get(Class<T> cls) {
        if (cls == null) {
            return null;
        }
        return (T) this.mModuleList.get(cls.getName());
    }

    public void add(Object obj, LifecycleOwner lifecycleOwner) {
        if (obj == null || lifecycleOwner == null) {
            L.error(TAG, "impl == null || lifecycleOwner == null");
            return;
        }
        if ((obj instanceof Activity) || (obj instanceof Fragment) || (obj instanceof View)) {
            L.error(TAG, "bug bug bug 界面元素不能添加到单例中");
            return;
        }
        final ShareApi shareApi = (ShareApi) obj.getClass().getAnnotation(ShareApi.class);
        if (shareApi == null) {
            L.info(TAG, "shareApi is null, ignore!" + obj.getClass().getName());
            return;
        }
        lifecycleOwner.getLifecycle().addObserver(new GenericLifecycleObserver() { // from class: com.huya.live.module.SModule.1
            @Override // android.arch.lifecycle.GenericLifecycleObserver
            public void onStateChanged(LifecycleOwner lifecycleOwner2, Lifecycle.Event event) {
                if (event == Lifecycle.Event.ON_DESTROY) {
                    SModule.this.mModuleList.remove(shareApi.getClass().getName());
                }
            }
        });
        this.mModuleList.put(shareApi.value().getName(), obj);
    }
}
