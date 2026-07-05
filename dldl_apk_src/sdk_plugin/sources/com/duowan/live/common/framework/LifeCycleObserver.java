package com.duowan.live.common.framework;

import android.arch.lifecycle.GenericLifecycleObserver;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;
import com.duowan.auk.util.L;
import com.duowan.live.common.framework.AbsPresenter;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class LifeCycleObserver<T extends AbsPresenter> implements GenericLifecycleObserver {
    public static final String TAG = "LifeCycleObserver";
    private WeakReference<T> mObserver;

    public LifeCycleObserver(T t) {
        this.mObserver = new WeakReference<>(t);
    }

    @Override // android.arch.lifecycle.GenericLifecycleObserver
    public void onStateChanged(LifecycleOwner lifecycleOwner, Lifecycle.Event event) {
        WeakReference<T> weakReference = this.mObserver;
        if (weakReference == null || weakReference.get() == null) {
            L.error("LifeCycleObserver", "bug, Observer is null");
            return;
        }
        int i = AnonymousClass1.$SwitchMap$android$arch$lifecycle$Lifecycle$Event[event.ordinal()];
        if (i == 1) {
            this.mObserver.get().onCreate();
            return;
        }
        if (i == 2) {
            this.mObserver.get().onResume();
            return;
        }
        if (i == 3) {
            this.mObserver.get().onPause();
        } else if (i != 4) {
            if (i == 5) {
                throw new IllegalArgumentException("ON_ANY must not been send by anybody");
            }
        } else {
            lifecycleOwner.getLifecycle().removeObserver(this);
            this.mObserver.get().onDestroy();
        }
    }

    /* JADX INFO: renamed from: com.duowan.live.common.framework.LifeCycleObserver$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$android$arch$lifecycle$Lifecycle$Event;

        static {
            int[] iArr = new int[Lifecycle.Event.values().length];
            $SwitchMap$android$arch$lifecycle$Lifecycle$Event = iArr;
            try {
                iArr[Lifecycle.Event.ON_CREATE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$android$arch$lifecycle$Lifecycle$Event[Lifecycle.Event.ON_RESUME.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$android$arch$lifecycle$Lifecycle$Event[Lifecycle.Event.ON_PAUSE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$android$arch$lifecycle$Lifecycle$Event[Lifecycle.Event.ON_DESTROY.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$android$arch$lifecycle$Lifecycle$Event[Lifecycle.Event.ON_ANY.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }
}
