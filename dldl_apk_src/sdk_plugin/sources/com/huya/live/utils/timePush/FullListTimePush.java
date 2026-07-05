package com.huya.live.utils.timePush;

import com.duowan.auk.util.L;
import com.huya.live.common.api.BaseApi;
import com.huya.live.utils.timePush.UITimer;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class FullListTimePush implements UITimer.OnTimeoutListener {
    private static final String TAG = "FullListTimePush";
    private boolean mHasNewObject;
    private Object mObject;
    private UITimer mUITimer;

    public FullListTimePush() {
        this.mHasNewObject = false;
        this.mUITimer = new UITimer();
        init();
    }

    public FullListTimePush(long j) {
        this.mHasNewObject = false;
        UITimer uITimer = new UITimer();
        this.mUITimer = uITimer;
        uITimer.setTimeInterval(j);
        init();
    }

    private void init() {
        this.mUITimer.setOnTimeoutListener(this);
    }

    public void onStart() {
        this.mUITimer.start();
    }

    public void onStop() {
        this.mUITimer.stop();
    }

    public void updatePush(Object obj) {
        if (obj == null) {
            L.error(TAG, "object == null");
        } else {
            this.mObject = obj;
            this.mHasNewObject = true;
        }
    }

    @Override // com.huya.live.utils.timePush.UITimer.OnTimeoutListener
    public void onTimeout() {
        if (this.mHasNewObject) {
            this.mHasNewObject = false;
            BaseApi.getSignalCenterApi().send(this.mObject);
        }
    }
}
