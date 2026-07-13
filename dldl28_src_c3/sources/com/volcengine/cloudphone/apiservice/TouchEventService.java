package com.volcengine.cloudphone.apiservice;

import com.volcengine.androidcloud.common.model.SimpleTouchEvent;
import java.util.List;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public interface TouchEventService {

    public interface SimpleTouchEventListener {
        void onSimpleTouchEvent(List<SimpleTouchEvent> list);
    }

    int sendMotionEvent(int i, float f, float f2);

    void setInterceptSendTouchEvent(boolean z);

    void setSimpleTouchEventListener(SimpleTouchEventListener simpleTouchEventListener);
}
