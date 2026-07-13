package com.volcengine.cloudphone.apiservice;

/* JADX INFO: loaded from: classes3.dex */
public interface GameGroundSwitchManager {

    public interface GameGroundSwitchedListener {
        void onRemoteGameSwitchedBackground(int i);

        void onRemoteGameSwitchedFailed(int i, String str);

        void onRemoteGameSwitchedForeground(int i);
    }

    void setGroundChangeListener(GameGroundSwitchedListener gameGroundSwitchedListener);

    void setRemoteGameForeground();
}
