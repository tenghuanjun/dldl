package com.huya.hysignal.wrapper;

import com.huya.hysignal.wrapper.listener.UnRegisterPushMsgListener;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public final class PushUnRegister {
    boolean hasNotify = false;
    UnRegisterPushMsgListener registerPushMsgListener;

    public PushUnRegister(UnRegisterPushMsgListener unRegisterPushMsgListener) {
        this.registerPushMsgListener = unRegisterPushMsgListener;
    }

    public boolean isHasNotify() {
        return this.hasNotify;
    }

    public void setHasNotify(boolean z) {
        this.hasNotify = z;
    }

    public UnRegisterPushMsgListener getRegisterPushMsgListener() {
        return this.registerPushMsgListener;
    }
}
