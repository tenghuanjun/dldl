package com.huya.live.module;

import android.arch.lifecycle.GenericLifecycleObserver;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LifecycleRegistry;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public abstract class BaseModule implements LifecycleOwner {
    private LifecycleRegistry mLifecycleRegistry = new LifecycleRegistry(this);

    public BaseModule(LifecycleOwner lifecycleOwner) {
        SModule.instance().add(this, this);
        if (lifecycleOwner != null) {
            lifecycleOwner.getLifecycle().addObserver(new GenericLifecycleObserver() { // from class: com.huya.live.module.BaseModule.1
                @Override // android.arch.lifecycle.GenericLifecycleObserver
                public void onStateChanged(LifecycleOwner lifecycleOwner2, Lifecycle.Event event) {
                    int i = AnonymousClass2.$SwitchMap$android$arch$lifecycle$Lifecycle$Event[event.ordinal()];
                    if (i == 1) {
                        BaseModule.this.onCreate();
                        return;
                    }
                    if (i == 2) {
                        BaseModule.this.onResume();
                        return;
                    }
                    if (i == 3) {
                        BaseModule.this.onPause();
                    } else if (i != 4) {
                        if (i == 5) {
                            throw new IllegalArgumentException("ON_ANY must not been send by anybody");
                        }
                    } else {
                        lifecycleOwner2.getLifecycle().removeObserver(this);
                        BaseModule.this.onDestroy();
                    }
                }
            });
        }
    }

    /* JADX INFO: renamed from: com.huya.live.module.BaseModule$2, reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
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

    public void onResume() {
        this.mLifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_RESUME);
    }

    public void onPause() {
        this.mLifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_PAUSE);
    }

    public void onCreate() {
        this.mLifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_CREATE);
    }

    public void onDestroy() {
        this.mLifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_DESTROY);
    }

    @Override // android.arch.lifecycle.LifecycleOwner
    public Lifecycle getLifecycle() {
        return this.mLifecycleRegistry;
    }
}
