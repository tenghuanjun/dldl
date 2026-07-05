package com.huyaudbunify.msg.response;

import com.huyaudbunify.bean.ResLogin;
import com.huyaudbunify.core.AuthEvent;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class MsgLoginInfoRes {
    AuthEvent.LoginEvent loginEvent;
    ResLogin resLogin;

    public AuthEvent.LoginEvent getLoginEvent() {
        return this.loginEvent;
    }

    public void setLoginEvent(AuthEvent.LoginEvent loginEvent) {
        this.loginEvent = loginEvent;
    }

    public ResLogin getResLogin() {
        return this.resLogin;
    }

    public void setResLogin(ResLogin resLogin) {
        this.resLogin = resLogin;
    }

    public MsgLoginInfoRes(AuthEvent.LoginEvent loginEvent, ResLogin resLogin) {
        this.loginEvent = loginEvent;
        this.resLogin = resLogin;
    }
}
