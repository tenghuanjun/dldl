package com.gyf.immersionbar;

import android.app.Application;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes3.dex */
final class NavigationBarObserver extends ContentObserver {
    private Application mApplication;
    private Boolean mIsRegister;
    private ArrayList<OnNavigationBarListener> mListeners;

    static NavigationBarObserver getInstance() {
        return NavigationBarObserverInstance.INSTANCE;
    }

    private NavigationBarObserver() {
        super(new Handler(Looper.getMainLooper()));
        this.mIsRegister = false;
    }

    void register(Application application) {
        Uri uriFor;
        this.mApplication = application;
        Application application2 = this.mApplication;
        if (application2 == null || application2.getContentResolver() == null || this.mIsRegister.booleanValue()) {
            return;
        }
        if (OSUtils.isMIUI()) {
            uriFor = Settings.Global.getUriFor("force_fsg_nav_bar");
        } else if (!OSUtils.isEMUI()) {
            uriFor = null;
        } else if (OSUtils.isEMUI3_x()) {
            uriFor = Settings.System.getUriFor("navigationbar_is_min");
        } else {
            uriFor = Settings.Global.getUriFor("navigationbar_is_min");
        }
        if (uriFor != null) {
            this.mApplication.getContentResolver().registerContentObserver(uriFor, true, this);
            this.mIsRegister = true;
        }
    }

    @Override // android.database.ContentObserver
    public void onChange(boolean z) {
        ArrayList<OnNavigationBarListener> arrayList;
        int i;
        super.onChange(z);
        Application application = this.mApplication;
        if (application == null || application.getContentResolver() == null || (arrayList = this.mListeners) == null || arrayList.isEmpty()) {
            return;
        }
        if (OSUtils.isMIUI()) {
            i = Settings.Global.getInt(this.mApplication.getContentResolver(), "force_fsg_nav_bar", 0);
        } else if (!OSUtils.isEMUI()) {
            i = 0;
        } else if (OSUtils.isEMUI3_x()) {
            i = Settings.System.getInt(this.mApplication.getContentResolver(), "navigationbar_is_min", 0);
        } else {
            i = Settings.Global.getInt(this.mApplication.getContentResolver(), "navigationbar_is_min", 0);
        }
        for (OnNavigationBarListener onNavigationBarListener : this.mListeners) {
            boolean z2 = true;
            if (i == 1) {
                z2 = false;
            }
            onNavigationBarListener.onNavigationBarChange(z2);
        }
    }

    void addOnNavigationBarListener(OnNavigationBarListener onNavigationBarListener) {
        if (onNavigationBarListener == null) {
            return;
        }
        if (this.mListeners == null) {
            this.mListeners = new ArrayList<>();
        }
        if (this.mListeners.contains(onNavigationBarListener)) {
            return;
        }
        this.mListeners.add(onNavigationBarListener);
    }

    void removeOnNavigationBarListener(OnNavigationBarListener onNavigationBarListener) {
        ArrayList<OnNavigationBarListener> arrayList;
        if (onNavigationBarListener == null || (arrayList = this.mListeners) == null) {
            return;
        }
        arrayList.remove(onNavigationBarListener);
    }

    private static class NavigationBarObserverInstance {
        private static final NavigationBarObserver INSTANCE = new NavigationBarObserver();

        private NavigationBarObserverInstance() {
        }
    }
}
