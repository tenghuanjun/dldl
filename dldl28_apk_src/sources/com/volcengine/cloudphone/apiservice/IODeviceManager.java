package com.volcengine.cloudphone.apiservice;

import com.volcengine.androidcloud.common.model.BriefTouchEvent;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public interface IODeviceManager {

    public interface BriefTouchListener {
        void onBriefTouchEvent(List<BriefTouchEvent> list);
    }

    public interface RemoteMouseListener {
        void onRemoteStateChange(boolean z);
    }

    int sendInputCursorPos(float f, float f2);

    int sendInputMouseKey(int i, int i2);

    int sendInputMouseMove(int i, int i2);

    int sendInputMouseWheel(int i, int i2);

    int sendKeyboardKey(int i, int i2);

    void setInterceptTouchSend(boolean z);

    void setMouseStateListener(RemoteMouseListener remoteMouseListener);

    void setTouchListener(BriefTouchListener briefTouchListener);
}
