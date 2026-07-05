package com.huyaudbunify.msg.response;

import com.huyaudbunify.bean.ResLoginPhoneSms;
import com.huyaudbunify.core.AuthEvent;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class MsgLoginPhoneSmsRes {
    AuthEvent.LoginEvent loginEvent;
    ResLoginPhoneSms resLogin;

    public AuthEvent.LoginEvent getLoginEvent() {
        return this.loginEvent;
    }

    public void setLoginEvent(AuthEvent.LoginEvent loginEvent) {
        this.loginEvent = loginEvent;
    }

    public MsgLoginPhoneSmsRes(AuthEvent.LoginEvent loginEvent, ResLoginPhoneSms resLoginPhoneSms) {
        this.loginEvent = loginEvent;
        this.resLogin = resLoginPhoneSms;
    }

    public ResLoginPhoneSms getResLogin() {
        return this.resLogin;
    }

    public void setResLogin(ResLoginPhoneSms resLoginPhoneSms) {
        this.resLogin = resLoginPhoneSms;
    }
}
