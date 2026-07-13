package com.volcengine.common.innerapi;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public interface SDKSwitchSetting {
    public static final String LOCATION_NETWORK = "location_network";

    public interface SwitchSettingLoadedListener {
        void onSwitchSettingLoaded();
    }

    void addSwitchSettingLoadedListener(SwitchSettingLoadedListener switchSettingLoadedListener);

    boolean getEnable(String str, boolean z);
}
