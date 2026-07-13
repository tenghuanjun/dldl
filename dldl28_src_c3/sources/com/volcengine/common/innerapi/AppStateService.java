package com.volcengine.common.innerapi;

import com.volcengine.common.util.CompatConsumer;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public interface AppStateService {
    boolean isAppForeground();

    void registerAppSwitchObserver(CompatConsumer<Boolean> compatConsumer);

    void reportAppState(boolean z);

    void unregisterAppSwitchObserver(CompatConsumer<Boolean> compatConsumer);
}
