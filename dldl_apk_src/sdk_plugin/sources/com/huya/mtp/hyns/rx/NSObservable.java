package com.huya.mtp.hyns.rx;

import com.huya.mtp.hyns.NSSettings;
import io.reactivex.Observable;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public abstract class NSObservable<T> extends Observable<T> {
    private NSSettings mSettings = NSSettings.DEFAULT_SETTINGS;

    public NSSettings getNSSettings() {
        return this.mSettings;
    }

    public NSObservable<T> setNSSettings(NSSettings nSSettings) {
        this.mSettings = nSSettings;
        return this;
    }
}
