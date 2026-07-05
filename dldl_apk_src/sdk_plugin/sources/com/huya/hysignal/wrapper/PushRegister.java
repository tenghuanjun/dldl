package com.huya.hysignal.wrapper;

import com.huya.hysignal.wrapper.listener.RegisterPushMsgListener;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public final class PushRegister {
    boolean hasNotify = false;
    RegisterPushMsgListener registerPushMsgListener;

    public PushRegister(RegisterPushMsgListener registerPushMsgListener) {
        this.registerPushMsgListener = registerPushMsgListener;
    }

    public boolean isHasNotify() {
        return this.hasNotify;
    }

    public void setHasNotify(boolean z) {
        this.hasNotify = z;
    }

    public RegisterPushMsgListener getRegisterPushMsgListener() {
        return this.registerPushMsgListener;
    }
}
