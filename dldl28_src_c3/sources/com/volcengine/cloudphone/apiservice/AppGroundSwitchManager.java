package com.volcengine.cloudphone.apiservice;

import java.util.List;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public interface AppGroundSwitchManager {

    public interface AppGroundSwitchedListener {
        void onRemoteAppSwitchedBackground(int i, String str);

        void onRemoteAppSwitchedForeground(int i, String str);

        void onRemoteGameSwitchedFailed(int i, String str);

        void onRevicedRemoteAppList(List<String> list);
    }

    void getRemoteBackgroundAppList();

    void setGroundChangeListener(AppGroundSwitchedListener appGroundSwitchedListener);

    void setRemoteAppForeground(String str);
}
