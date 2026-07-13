package com.volcengine.cloudphone.gamepad;

/* JADX INFO: loaded from: classes3.dex */
public interface GamePadService {

    public interface GamePadListener {
        void onVibrate(int i, int i2, int i3);
    }

    public interface GamePadRemoteStatusListener {
        void onDeviceStatusChanged(int i, boolean z);
    }

    void registerGamePadDevice(String str, int i, int i2);

    void sendGamePadRockerEvent(int i, float f, float f2, float f3, float f4, float f5, float f6);

    void sendKeyEvent(int i, int i2, int i3);

    void sendRtRbLtLb(int i, int i2, float f);

    void setGamePadListener(GamePadListener gamePadListener);

    void setGamePadRemoteStatusListener(GamePadRemoteStatusListener gamePadRemoteStatusListener);

    void unregisterGamePadDevice(String str, int i, int i2);
}
